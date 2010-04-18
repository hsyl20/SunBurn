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

import fr.hsyl20.sunburn.core._

class RegularSampler(sampleCount: Int) extends Sampler(sampleCount) {
    
    private val n = scala.math.sqrt(sampleCount).round.toInt
    private val sampleWidth = 1.0 / n

    override def generate =
        for (p <- 0 until n ; q <- 0 until n)
            yield Sample((0.5 + p) * sampleWidth, (0.5+q) * sampleWidth)
}
