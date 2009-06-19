/*
 * JitteredSampler.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.samplers

import sunburn.core._

class JitteredSampler(sampleCount : Int) extends Sampler(sampleCount) {

    override def generate = {
        val n = Math.sqrt(sampleCount)
        val s = 1.0 / n

        for (y<- 0 until n; x <- 0 until n)
            yield Sample(s * x + Math.random / n, s * y + Math.random / n)
    }
    
}
