/*
 * HammersleySampler.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.samplers

import sunburn.core._

class HammersleySampler(sampleCount : Int) extends Sampler(sampleCount) {

    def phi(j: Int, f:Double, x: Double): Double = {
        if (j == 0) x else phi(j / 2, f*0.5, x +f * (j & 1).toDouble)
    }

    override def generate = for(i <- 0 until sampleCount)
          yield Sample(i.toDouble/sampleCount.toDouble, phi(i, 0.5, 0.0))

}
