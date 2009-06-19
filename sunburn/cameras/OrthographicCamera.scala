/*
 * OrthographicCamera.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.cameras

import java.awt.image.BufferedImage

import sunburn.geometry._
import sunburn.core._
import sunburn.colors._

class OrthographicCamera(world: World, viewPlane: ViewPlane) extends Camera(world, viewPlane) {

    //FIXME: this camera only works if eye is on Z axis
    protected def computeRay(x: Double, y:Double) : Ray = {
        val origin = Point3D(x,y,eye.z)
        Ray(origin, -w)
    }
}
