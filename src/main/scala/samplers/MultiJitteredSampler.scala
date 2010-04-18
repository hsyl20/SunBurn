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

class MultiJitteredSampler(sampleCount: Int) extends Sampler(sampleCount)  {
   import scala.math._

    private val n = sqrt(sampleCount).round.toInt
    private val width = 1.0 / n
    private val subwidth = 1.0 / (n*n)

    override def generate: Seq[Sample] = {
        val sa = new Array[Sample](n*n)

        for (i <- 0 until n; j <- 0 until n)
            sa(i+j*n) = Sample(i * width + j*subwidth + random*subwidth, j * width + i*subwidth + random*subwidth)

        shuffleX(sa, n)
        shuffleY(sa, n)
        sa
    }

    def randomInt(max: Int) : Int = (random * max).toInt

    def shuffleY(s : Array[Sample], n: Int) {
        for (i <- 0 until n; j <- 0 until n) {
            val target = i*n + randomInt(n)
            val temp = s(i*n + j).y
            s(i*n + j) = Sample(s(i*n +j).x, s(target).y)
            s(target) = Sample(s(target).x, temp)
        }
    }

    def shuffleX(s : Array[Sample], n: Int) {
        for (i <- 0 until n; j <- 0 until n) {
            val target = j + n*randomInt(n)
            val temp = s(i*n +j).x
            s(i*n +j) = Sample(s(target).x, s(i*n+j).y)
            s(target) = Sample(temp, s(target).y)
        }
    }
}
