/*
 * PointLight.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.lights

import sunburn.core._
import sunburn.geometry._
import sunburn.colors._
import sunburn.colors.RGBColor._


class PointLight extends Light {

    var intensity: Double = 1.0
    var color: RGBColor   = RGBColor.White
    var location: Point3D = Point3D(0,20,0)

    def getDirection(hi: HitInfo): Vector3D = {
        (location - hi.hitPoint).normalize
    }

    def L(hi: HitInfo) : RGBColor = {
        intensity * color
    }
}
