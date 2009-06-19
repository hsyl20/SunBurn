/*
 * Sampler.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.samplers

import sunburn.core.Sample

abstract class Sampler(val sampleCount: Int) {
    def generate() : Seq[Sample]
}

object NoSampler extends Sampler(1) {
    def generate() = Array(Sample(0.5, 0.5))
}