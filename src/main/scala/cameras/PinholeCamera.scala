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

import sunburn.core._
import sunburn.geometry._
import sunburn.geometry.Vector3D._

class PinholeCamera(world: World, viewPlane: ViewPlane) extends Camera(world: World, viewPlane: ViewPlane) {
    var viewDistance: Double = 1.0

    protected def computeRay(x: Double, y:Double) : Ray = {
        val dir = (x * u + y * v - viewDistance *  w).normalize
        Ray(eye, dir)
    }
}
