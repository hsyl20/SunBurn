/*
 * RegularSampler.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.samplers

import sunburn.core._

class RegularSampler(sampleCount: Int) extends Sampler(sampleCount) {
    
    private val n = Math.sqrt(sampleCount)
    private val sampleWidth = 1.0 / n

    override def generate =
        for (p <- 0 until n ; q <- 0 until n)
            yield Sample((0.5 + p) * sampleWidth, (0.5+q) * sampleWidth)
}
