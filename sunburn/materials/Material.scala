/*
 * Material.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.materials

import sunburn.core._
import sunburn.colors._

abstract class Material {
    def shade(world: World, h: HitInfo): RGBColor
}
