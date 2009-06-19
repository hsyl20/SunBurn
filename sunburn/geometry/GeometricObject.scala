/*
 * GeometricObject.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.geometry

import sunburn.core._
import sunburn.colors._
import sunburn.materials._

abstract class GeometricObject {
    var material : Material = new ColorMaterial(RGBColor.Black)
    var kEpsilon: Double = 0.00001

    def hit(ray: Ray): Option[HitInfo]
}
