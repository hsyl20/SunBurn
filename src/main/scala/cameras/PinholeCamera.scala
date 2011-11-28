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

class PinholeCamera(
  val world:World,
  val viewPlane:ViewPlane,
  val eye:Point3D,
  val lookat:Point3D,
  val up:Vector3D,
  val viewDistance:Double) extends Camera {

    protected def computeRay(x: Double, y:Double) : Ray = {
        val dir = (x * u + y * v - viewDistance *  w).normalize
        Ray(eye, dir)
    }
}

object PinholeCamera {
  def apply(world:World,
            viewPlane:ViewPlane,
            eye:Point3D = Point3D(0,10,10),
            lookat:Point3D = Point3D(0,0,0),
            up:Vector3D = Vector3D(0,1,0),
            viewDistance:Double = 1.0) = {
    new PinholeCamera(world,viewPlane,eye,lookat,up,viewDistance)
  }
}
