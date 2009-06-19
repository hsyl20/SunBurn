/*
 * NRooksSampler.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.samplers

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
