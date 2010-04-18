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

package fr.hsyl20.sunburn.samplers

import fr.hsyl20.sunburn.geometry.Point3D

class HemisphereSampler(sampler: Sampler, e: Double) {

    def generate(): Seq[Point3D] = {
        import scala.math._
        val samples = sampler.generate()

        for (s <- samples) yield {
            val phi = 2.0 * Pi * s.x
            val cos_phi = cos(phi)
            val sin_phi = sin(phi)
            val cos_theta = pow((1.0 - s.y), 1.0 / (e + 1.0))
            val sin_theta = sqrt(1.0 - cos_theta * cos_theta)
            val pu = sin_theta * cos_phi
            val pv = sin_theta * sin_phi
            val pw = cos_theta
            Point3D(pu,pv,pw)
        }
    }
}
