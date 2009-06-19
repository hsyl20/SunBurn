/*
 * Tracer.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.tracers

import sunburn.colors._
import sunburn.geometry._
import sunburn.core._

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
