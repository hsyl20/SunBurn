/*
 * Sample.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.core

import sunburn.colors._
import sunburn.geometry.Point2D

case class Sample(override val x : Double, override val y: Double) extends Point2D(x,y)

case class ColoredSample(x: Double, y: Double, color: RGBColor)