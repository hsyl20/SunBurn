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

import sunburn.colors._
import sunburn.core._

class RayCastTracer(world: World) extends Tracer(world) {

    override def trace(ray: Ray, depth: Int) : RGBColor = {
        val hit = world.hitObjects(ray)
        hit match {
            case None => world.backgroundColor
            case Some(hh) => hh.material.shade(world, hh)
        }
    }
}
