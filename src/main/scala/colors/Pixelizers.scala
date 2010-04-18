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

package fr.hsyl20.sunburn.colors

import sunburn.core._

object Pixelizers {
    def average(s : Seq[Tuple2[Sample, RGBColor]]) : RGBColor = s match {
        case Nil => RGBColor.Black
        case _    => (RGBColor.Black /: (s map (_._2)))(_ + _) / s.length
    }

    def averageClampTo(s : Seq[Tuple2[Sample, RGBColor]], c: RGBColor) : RGBColor = s match {
        case Nil => c
        case _    => average(s).clampTo(c)
    }
}
