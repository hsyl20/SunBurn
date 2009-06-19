/*
 * Point2D.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.geometry

case class Point2D(x: Double, y:Double) {
    def * (a: Double) : Point2D = Point2D(a*x, a*y)
}
