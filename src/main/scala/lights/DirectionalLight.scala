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

case class DirectionalLight(ls: Double, color: RGBColor, direction: Vector3D) extends Light{

    def this(direction: Vector3D) = this(1.0, RGBColor.White, direction)

    def getDirection(hi: HitInfo): Vector3D = {
        -direction
    }

    def L(hi: HitInfo) : RGBColor = {
        ls * color
    }
}
