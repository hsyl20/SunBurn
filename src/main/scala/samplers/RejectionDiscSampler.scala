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

import sunburn.core._

class RejectionDiscSampler(sampler: Sampler) extends Sampler(sampler.sampleCount) with DiscSampler {
    override def generate() = sampler.generate().map(s => Sample(s.x*2.0 -1.0, s.y*2.0 - 1.0)).filter(s => s.x * s.x + s.y * s.y < 1.0)
}
