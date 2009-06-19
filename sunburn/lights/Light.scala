/*
 * Light.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.lights

import sunburn.core._
import sunburn.geometry._
import sunburn.colors._

abstract class Light {
    def getDirection(hi: HitInfo): Vector3D
    def L(hi: HitInfo) : RGBColor

    protected var shadows : Boolean = false
}
