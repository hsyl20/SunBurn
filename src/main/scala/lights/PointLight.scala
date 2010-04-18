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

package fr.hsyl20.sunburn.lights

import fr.hsyl20.sunburn.core._
import fr.hsyl20.sunburn.geometry._
import fr.hsyl20.sunburn.colors._
import fr.hsyl20.sunburn.colors.RGBColor._


class PointLight extends Light {

    var intensity: Double = 1.0
    var color: RGBColor   = RGBColor.White
    var location: Point3D = Point3D(0,20,0)

    def getDirection(hi: HitInfo): Vector3D = {
        (location - hi.hitPoint).normalize
    }

    def L(hi: HitInfo) : RGBColor = {
        intensity * color
    }
}
