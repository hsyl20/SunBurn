/*
 * SimpleAmbientLight.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.lights

import sunburn.core._
import sunburn.geometry._
import sunburn.colors._
import sunburn.colors.RGBColor._

class SimpleAmbientLight extends AmbientLight {

    var intensity : Double = 1.0
    var color : RGBColor = RGBColor.White

    /**
     * @return (0,0,0) as it is an ambient light
     */
    def getDirection(hi: HitInfo): Vector3D = {
        Vector3D(0.0)
    }

    /**
     * @param   h Hit point information
     * @return  Color at hit point h
     */
    def L(h: HitInfo) : RGBColor = {
        intensity * color
    }
}
