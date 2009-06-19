/*
 * RandomSampler.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.samplers

import sunburn.core._

class RandomSampler(sampleCount: Int) extends Sampler(sampleCount) {

    override def generate = for (p <- 0 until sampleCount)
           yield  Sample(Math.random, Math.random)
}
