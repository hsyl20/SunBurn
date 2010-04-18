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

import sunburn.core._
import sunburn.geometry._
import sunburn.colors._
import sunburn.colors.RGBColor._

abstract class AmbientLight extends Light

object NoAmbientLight extends AmbientLight {
    def getDirection(hi: HitInfo): Vector3D = Vector3D(0,0,0)
    def L(hi: HitInfo) : RGBColor = Black
}
