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

class RotationDiscSampler(sampler: Sampler) extends Sampler(sampler.sampleCount) with DiscSampler {

    override def generate = {
        import Math._

        val v = sampler.generate map (s => Sample(s.x*2.0 -1.0, s.y*2.0-1.0))
        val w : Seq[Tuple2[Double, Double]] = v map (_ match {
                case Sample(x,y) if (x == 0 && y == 0) => (0,0)
                case Sample(x,y) if (x >= y && x >= -y) => (x,  Pi * y / (4.0 * x))
                case Sample(x,y) if (x <= y && x >= -y) => (y,  Pi / 4.0 * (2.0 - x / y))
                case Sample(x,y) if (x <= y && x <= -y) => (-x, Pi / 4.0 * (4.0 + y/x))
                case Sample(x,y) if (x >= y && x <= -y) => (-y, Pi / 4.0 * (6.0  -  x/y))
                case _ => (0,0)
        })
    
        w map (p => Sample(p._1 * cos(p._2), p._1 * sin(p._2)))  //p._1= r and p._2=phi
    }
}
