/*                  ______                  
**                  | ___ \                 
**   ___ _   _ _ __ | |_/ /_   _ _ __ _ __  
**  / __| | | | '_ \| ___ \ | | | '__| '_ \ 
**  \__ \ |_| | | | | |_/ / |_| | |  | | | |
**  |___/\__,_|_| |_\____/ \__,_|_|  |_| |_|
**
**             SunBurn RayTracer
**        http://www.hsyl20.fr/sunburn
**                   GPLv3
*/

package fr.hsyl20.sunburn.cameras

import fr.hsyl20.sunburn.core._
import fr.hsyl20.sunburn.geometry._
import fr.hsyl20.sunburn.geometry.Vector3D._
import fr.hsyl20.sunburn.samplers._

class ThinsLensCamera(world: World, viewPlane: ViewPlane) extends Camera(world, viewPlane) {


    var viewDistance: Double = 10.0
    var focalDistance: Double = 10.0
    var lensRadius: Double = 0.5
    var sampler: DiscSampler = new RotationDiscSampler(new MultiJitteredSampler(viewPlane.sampler.sampleCount))

    var samples = sampler.generate

    protected def computeRay(x: Double, y:Double) : Ray = {
        if (samples.isEmpty)
            samples = sampler.generate
        val dp = samples.head

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
