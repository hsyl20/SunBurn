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

package fr.hsyl20.sunburn.materials.brdf

import fr.hsyl20.sunburn.colors.RGBColor
import fr.hsyl20.sunburn.core._
import fr.hsyl20.sunburn.geometry._
import fr.hsyl20.sunburn.samplers._

/**
 * This class caracterizes the reflection of light on a surface
 * BRDF means Bidirectional Reflectance Distribution Function
 */
abstract class BRDF {

    /**
     * This is the factor such that: reflected radiance = f * irradiance
     */
    def f (hp: HitInfo, wi: Vector3D, wo: Vector3D) : RGBColor

    /**
     * @return a sampled ray wi reflected from wo on the surface
     */
    def samplef (hp: HitInfo, wo: Vector3D) : (RGBColor, Vector3D)

    /**
     * Reflectance:
     *   rho = dPhi_o / dPhi_i where Phi is the radiant flux density
     */
    def rho (hp: HitInfo, wo: Vector3D) : RGBColor

    protected var sampler : Sampler = NoSampler
}
