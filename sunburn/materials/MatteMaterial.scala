/*
 * MatteMaterial.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.materials

import sunburn.core._
import sunburn.colors._
import sunburn.materials.brdf._

class MatteMaterial extends Material {

    var color: RGBColor = RGBColor.Green
    var ambient: Double = 1.0
    var diffuse: Double = 1.0

    lazy private val ambientBRDF = new Lambertian(ambient, color)
    lazy private val diffuseBRDF = new Lambertian(diffuse, color)

    def shade(world: World, h: HitInfo): RGBColor = {
        val wo = h.ray.direction
        
        //Ambient light
        var L = ambientBRDF.rho(h, wo) * world.ambientLight.L(h)

        //Diffuse light
        val Ls = for (li <- world.lights) yield {
            val wi = li.getDirection(h)
            val ndotwi = h.normal * wi
            if (ndotwi > 0.0) diffuseBRDF.f(h, wo, wi)* li.L(h) * ndotwi else RGBColor.Black
        }

        (L /: Ls)(_+_)
    }
}
