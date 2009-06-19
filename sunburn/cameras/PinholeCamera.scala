/*
 * PinholeCamera.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.cameras

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
