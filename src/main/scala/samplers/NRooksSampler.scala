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

class NRooksSampler(sampleCount : Int) extends Sampler(sampleCount) {

    override def generate = {
        val sa = new Array[Sample](sampleCount)
        for (j <- 0 until sampleCount)
            sa(j) = Sample((j + Math.random) / sampleCount, (j + Math.random) / sampleCount)
         shuffleX(sa)
         shuffleY(sa)
         sa
    }

    def randomInt(max: Int) : Int = (Math.random * max).toInt

    def shuffleY(s : Array[Sample]) {
        for (i <- 0 until sampleCount) {
            val target = randomInt(sampleCount)
            val temp = s(i).y
            s(i) = Sample(s(i).x, s(target).y)
            s(target) = Sample(s(target).x, temp)
        }
    }

    def shuffleX(s : Array[Sample]) {
        for (i <- 0 until sampleCount) {
            val target = randomInt(sampleCount)
            val temp = s(i).x
            s(i) = Sample(s(target).x, s(i).y)
            s(target) = Sample(temp, s(target).y)
        }
    }

}
