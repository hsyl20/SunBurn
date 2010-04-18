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

/**
 * This sampler takes another sampler as an input.
 * It buffers a few sets of samples (the number of sets can be chosen with
 * setCount). 
 **/
class BufferedSampler(sampler: Sampler, setCount: Int) extends Sampler(sampler.sampleCount) {

    def this(sampler: Sampler) = this(sampler, 83)

    var sets : List[Seq[Sample]] = Nil
    for (i <- 0 until setCount) sets ::= sampler.generate()

    var current = sets

    override def generate = current match {
         case x :: xs => {current = xs ; x }
         case Nil      => {current = sets.drop(1); sets.first}
    }

}
