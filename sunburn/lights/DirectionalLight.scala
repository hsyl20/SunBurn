/*
 * DirectionalLight.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.lights

import sunburn.core._
import sunburn.geometry._
import sunburn.colors._
import sunburn.colors.RGBColor._

case class DirectionalLight(ls: Double, color: RGBColor, direction: Vector3D) extends Light{

    def this(direction: Vector3D) = this(1.0, RGBColor.White, direction)

    def getDirection(hi: HitInfo): Vector3D = {
        -direction
    }

    def L(hi: HitInfo) : RGBColor = {
        ls * color
    }
}