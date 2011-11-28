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
import fr.hsyl20.sunburn.colors._

class OrthographicCamera(
  val world:World,
  val viewPlane:ViewPlane,
  val eye:Point3D,
  val lookat:Point3D,
  val up:Vector3D) extends Camera {

    //FIXME: this camera only works if eye is on Z axis
    protected def computeRay(x: Double, y:Double) : Ray = {
        val origin = Point3D(x,y,eye.z)
        Ray(origin, -w)
    }
}
  
object OrthographicCamera {
  def apply(world:World,
            viewPlane:ViewPlane,
            eye:Point3D = Point3D(0,10,10),
            lookat:Point3D = Point3D(0,0,0),
            up:Vector3D = Vector3D(0,1,0)) = {
    new OrthographicCamera(world,viewPlane,eye,lookat,up)
  }
}
