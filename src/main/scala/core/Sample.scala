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

package fr.hsyl20.sunburn.core

import fr.hsyl20.sunburn.colors._
import fr.hsyl20.sunburn.geometry.Point2D

class Sample(x:Double, y:Double) extends Point2D(x,y)

object Sample {
   def apply(x:Double, y:Double): Sample = new Sample(x,y)
   def unapply(s:Sample) = Some(s.x, s.y)
}

class ColoredSample(x:Double, y:Double, color:RGBColor)

object ColoredSample {
   def apply(x:Double, y:Double, color:RGBColor) = new ColoredSample(x,y,color)
}
