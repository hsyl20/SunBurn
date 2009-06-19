/*
 * ColorMaterial.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.materials

import sunburn.core._
import sunburn.colors._

class ColorMaterial(color: RGBColor) extends Material {
    
    override def shade(world: World, h: HitInfo): RGBColor = color
}
