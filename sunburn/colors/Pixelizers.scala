/*
 * Pixelizers.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.colors

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
