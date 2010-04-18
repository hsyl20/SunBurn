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

class RGBColor(val r:Double, val g:Double, val b:Double) extends Tuple3(r,g,b) {
   import scala.math._

    def + (c : RGBColor) : RGBColor = RGBColor(r+c.r, g+c.g, b+c.b)

    def * (a : Double) : RGBColor = RGBColor(a*r, a*g, a*b)
    def * (c : RGBColor) : RGBColor = RGBColor(c.r*r, c.g*g, c.b*b)

    def / (a: Double) : RGBColor = RGBColor(r/a, g/a, b/a)

    def ^ (a: Int) = RGBColor(pow(r,a), pow(g,a), pow(b,a))

    def clampTo(c: RGBColor) : RGBColor = if (r >1.0 || g >1.0 || b > 1.0) c else this

    def fitScale : RGBColor = {
        val m = max(max(r, g), b)
        if (m> 1.0) RGBColor(r / m, g / m,b / m)
        else this
    }

    def fullRangeScale : RGBColor = {
        val m = max(max(r,g),b)
        RGBColor(r/m, g/m, b/m)
    }

    def toInt: Int = new java.awt.Color((r*255.0).toInt, (g*255.0).toInt, (b*255.0).toInt).getRGB
}

class DoubleWrapper(d: Double) {
    def * (c : RGBColor) = RGBColor(d * c.r, d*c.g, d*c.b)
}

object RGBColor {
   def apply(r:Double, g:Double, b:Double) = new RGBColor(r,g,b)

    implicit def doubleWrapper(d: Double) : DoubleWrapper = {
        new DoubleWrapper(d)
    }

    lazy val Red = RGBColor(1.0, 0.0, 0.0)
    lazy val Green = RGBColor(0.0, 1.0, 0.0)
    lazy val Blue = RGBColor(0.0, 0.0, 1.0)
    lazy val Yellow = RGBColor(1.0, 1.0, 0.0)
    lazy val Orange = RGBColor(0.0, 1.0, 1.0)
    lazy val Magenta = RGBColor(1.0, 0.0, 1.0)
    lazy val Black = RGBColor(0.0, 0.0, 0.0)
    lazy val White = RGBColor(1.0, 1.0, 1.0)
}
