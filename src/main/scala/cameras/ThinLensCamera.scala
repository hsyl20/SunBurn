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

import fr.hsyl20.sunburn.core._
import fr.hsyl20.sunburn.geometry._
import fr.hsyl20.sunburn.geometry.Vector3D._
import fr.hsyl20.sunburn.samplers._

class ThinLensCamera(
  val world:World,
  val viewPlane:ViewPlane,
  val eye:Point3D,
  val lookat:Point3D,
  val up:Vector3D,
  val viewDistance:Double,
  val focalDistance:Double,
  val lensRadius:Double,
  val sampler:DiscSampler) extends Camera {

    protected var samples = sampler.generate

    protected def computeRay(x: Double, y:Double) : Ray = {
        if (samples.isEmpty)
            samples = sampler.generate
        val dp = samples.head

        samples = samples.drop(1)

        val lp = dp * lensRadius
        val origin = eye + lp.x * u + lp.y * v
        //hit point on focal plane
        val px = x * focalDistance / viewDistance
        val py = y * focalDistance / viewDistance
        val direction = ((px - lp.x) * u + (py - lp.y) * v - focalDistance * w).normalize
        Ray(origin, direction)
    }

}

object ThinLensCamera {
  def apply(world:World,
            viewPlane:ViewPlane,
            eye:Point3D = Point3D(0,10,10),
            lookat:Point3D = Point3D(0,0,0),
            up:Vector3D = Vector3D(0,1,0),
            viewDistance:Double = 10.0,
            focalDistance:Double = 10.0,
            lensRadius:Double = 0.5,
            sampler:DiscSampler = null) = {
    val _sampler = if (sampler != null) sampler else new RotationDiscSampler(new MultiJitteredSampler(viewPlane.sampler.sampleCount))
    new ThinLensCamera(world,viewPlane,eye,lookat,up,viewDistance,focalDistance,lensRadius,_sampler)
  }
}
