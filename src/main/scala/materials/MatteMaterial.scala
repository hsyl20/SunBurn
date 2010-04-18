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

package fr.hsyl20.sunburn.materials

import fr.hsyl20.sunburn.core._
import fr.hsyl20.sunburn.colors._
import fr.hsyl20.sunburn.materials.brdf._

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
