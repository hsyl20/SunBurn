/*                  ______                  
**                  | ___ \                 
**   ___ _   _ _ __ | |_/ /_   _ _ __ _ __  
**  / __| | | | '_ \| ___ \ | | | '__| '_ \ 
**  \__ \ |_| | | | | |_/ / |_| | |  | | | |
**  |___/\__,_|_| |_\____/ \__,_|_|  |_| |_|
**
**             SunBurn RayTracer
**        http://www.hsyl20.fr/sunburn
**                   GPLv3
*/

package fr.hsyl20.sunburn.cameras

import java.awt.image.BufferedImage
import fr.hsyl20.sunburn.geometry._
import fr.hsyl20.sunburn.core._
import fr.hsyl20.sunburn.samplers._
import fr.hsyl20.sunburn.colors._
import fr.hsyl20.sunburn.tracers._

import scala.actors.Futures._
import scala.actors._


//TODO: implement roll
trait Camera {
   import scala.math._

    val lookat:Point3D
    val eye:Point3D
    val up:Vector3D
    val world:World
    val viewPlane:ViewPlane

    private val vertical = abs((eye - lookat) * up) == 1
    protected lazy val w = if (vertical) Vector3D(0,1,0) else (eye -  lookat).normalize
    protected lazy val u = if (vertical) Vector3D(0,0,1) else (up ^ w).normalize
    protected lazy val v = if (vertical) Vector3D(1,0,0) else w ^ u

    private var rendered : Boolean = false
    def isRendered : Boolean  = rendered

    private var image = new BufferedImage(viewPlane.width, viewPlane.height, BufferedImage.TYPE_INT_ARGB)
    def getImage : BufferedImage = image

    var tracer = new RayCastTracer(world)

    /* Convert a set of sample to a pixel color */
    var samplesToPixel : Seq[Tuple2[Sample, RGBColor]] => RGBColor = Pixelizers.average

    /* Render the world with ViewPlane settings */
    def render {
        rendered = false
        val t1 = System.currentTimeMillis
        val coloredPixels =
            for (y <- 0 until viewPlane.height; x <- 0 until viewPlane.width)
                yield (x,viewPlane.height-y-1,renderPixel(x,y))

        val r = renderColors(coloredPixels)
        r  foreach(p => image.setRGB(p._1, p._2, p._3.toInt))
        println("Rendering time: " + (System.currentTimeMillis - t1)+ "msecs")
        rendered = true
    }

    /* Performs last transformation on colors (gama, clamp...)
     * Returned RGBColors must have r,g and b in [0.0, 1.0]
     * fitScale by default
     */
    protected def renderColors(pix: Seq[Tuple3[Int,Int,RGBColor]]) : Seq[Tuple3[Int,Int,RGBColor]] = {
        pix map (p => (p._1, p._2, p._3.fitScale))
    }

    /* Render one pixel of the ViewPlane*/
    protected def renderPixel(x:Int, y:Int) : RGBColor= {
        val samples = viewPlane.sampler.generate

        samplesToPixel(samples map (s => (s, renderSample(x, y, s))))
    }

    /* Render one sample of pixel (x,y) of the ViewPlane */
    protected def renderSample(x: Int, y:Int, s: Sample) : RGBColor = {
        val ppx = viewPlane.resolution * (x - 0.5 * viewPlane.width + s.x)
        val ppy = viewPlane.resolution * (y - 0.5 * viewPlane.height + s.y)
        val ray = computeRay(ppx,ppy)
        tracer.trace(ray)
    }

    protected def computeRay(x: Double, y:Double) : Ray
}
