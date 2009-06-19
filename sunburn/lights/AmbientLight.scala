/*
 * AmbientLight.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.lights

import sunburn.core._
import sunburn.geometry._
import sunburn.colors._
import sunburn.colors.RGBColor._

abstract class AmbientLight extends Light

object NoAmbientLight extends AmbientLight {
    def getDirection(hi: HitInfo): Vector3D = Vector3D(0,0,0)
    def L(hi: HitInfo) : RGBColor = Black
}
