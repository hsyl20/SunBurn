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

class JitteredSampler(sampleCount : Int) extends Sampler(sampleCount) {

    override def generate = {
        val n = scala.math.sqrt(sampleCount).round.toInt
        val s = 1.0 / n

        for (y<- 0 until n; x <- 0 until n)
            yield Sample(s * x + scala.math.random / n, s * y + scala.math.random / n)
    }
    
}
