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

package fr.hsyl20.sunburn.geometry

import fr.hsyl20.sunburn.core._
import fr.hsyl20.sunburn.colors._
import fr.hsyl20.sunburn.materials._

abstract class GeometricObject {
    var material : Material = new ColorMaterial(RGBColor.Black)
    var kEpsilon: Double = 0.00001

    def hit(ray: Ray): Option[HitInfo]
}
