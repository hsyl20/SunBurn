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

package fr.hsyl20.sunburn.tracers

import fr.hsyl20.sunburn.colors._
import fr.hsyl20.sunburn.geometry._
import fr.hsyl20.sunburn.core._

abstract class Tracer(world: World) {

    /* Traces a ray through */
    def trace(r: Ray) : RGBColor = {
        trace(r, 0)
    }

    def trace(r: Ray, depth: Int) : RGBColor = {
        trace(r)
    }

    def trace(r: Ray, minDistance: Int, depth:Int) : RGBColor= {
        RGBColor.Black
    }
}
