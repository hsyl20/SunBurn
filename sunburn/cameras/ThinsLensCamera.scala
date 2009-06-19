/*
 * ThinsLensCamera.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.cameras

import sunburn.core._
import sunburn.geometry._
import sunburn.geometry.Vector3D._
import sunburn.samplers._

class ThinsLensCamera(world: World, viewPlane: ViewPlane) extends Camera(world, viewPlane) {


    var viewDistance: Double = 10.0
    var focalDistance: Double = 10.0
    var lensRadius: Double = 0.5
    var sampler: DiscSampler = new RotationDiscSampler(new MultiJitteredSampler(viewPlane.sampler.sampleCount))

    var samples = sampler.generate

    protected def computeRay(x: Double, y:Double) : Ray = {
        if (samples.isEmpty)
            samples = sampler.generate
        val dp = samples.first

        samples = samples.drop(1)

        val lp = dp * lensRadius
        val origin = eye + lp.x * u + lp.y * v
        //hit point on focal plane
        val px = x * focalDistance / viewDistance
        val py = y * focalDistance / viewDistance
        val direction = ((px - lp.x) * u + (py - lp.y) * v - focalDistance * w).normalize
        Ray(origin, direction)
    }

}
