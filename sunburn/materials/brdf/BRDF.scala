/*
 * BRDF.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.materials.brdf

import sunburn.colors.RGBColor
import sunburn.core._
import sunburn.geometry._
import sunburn.samplers._

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
