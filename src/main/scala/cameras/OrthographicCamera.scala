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

class OrthographicCamera(world: World, viewPlane: ViewPlane) extends Camera(world, viewPlane) {

    //FIXME: this camera only works if eye is on Z axis
    protected def computeRay(x: Double, y:Double) : Ray = {
        val origin = Point3D(x,y,eye.z)
        Ray(origin, -w)
    }
}
