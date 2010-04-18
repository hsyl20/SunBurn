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

class HammersleySampler(sampleCount : Int) extends Sampler(sampleCount) {

    def phi(j: Int, f:Double, x: Double): Double = {
        if (j == 0) x else phi(j / 2, f*0.5, x +f * (j & 1).toDouble)
    }

    override def generate = for(i <- 0 until sampleCount)
          yield Sample(i.toDouble/sampleCount.toDouble, phi(i, 0.5, 0.0))

}
