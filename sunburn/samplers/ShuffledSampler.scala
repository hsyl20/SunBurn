/*
 * ShuffledSampler.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.samplers

import sunburn.core._

/**
 * This sampler shuffles samples from another sampler
 * (set as an argument of the constructor).
 * Shuffling is used because samples created with the same
 * method may have the same spatial arrangement.
 **/
class ShuffledSampler(sampler: Sampler) extends Sampler(sampler.sampleCount) {

    private def randomInt(max: Int) : Int = (Math.random * max).toInt

    override def generate : Seq[Sample]= {    
        val s = sampler.generate()

        //shuffled indices
        val r = Array.range(0, s.size)
        for (i <- 0 until s.size) {
            val temp = r(i)
            val j = randomInt(s.size)
            r(i) = r(j)
            r(j) = temp
        }

        //shuffled samples
        val sh = new Array[Sample](s.size)
        var i = 0
        for (sa <- s) {
            sh(r(i)) = sa
            i += 1
        }

        sh
    }
}
