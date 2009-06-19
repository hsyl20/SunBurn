/*
 * RejectionDiscSampler.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.samplers

import sunburn.core._

class RejectionDiscSampler(sampler: Sampler) extends Sampler(sampler.sampleCount) with DiscSampler {
    override def generate() = sampler.generate().map(s => Sample(s.x*2.0 -1.0, s.y*2.0 - 1.0)).filter(s => s.x * s.x + s.y * s.y < 1.0)
}
