/*
 * RayCastTracer.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.tracers

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
