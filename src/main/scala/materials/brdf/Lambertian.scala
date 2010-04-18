/*
 * Lambertian.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.materials.brdf

import sunburn.colors.RGBColor
import sunburn.colors.RGBColor._
import sunburn.core._
import sunburn.geometry._
import sunburn.samplers.Sampler
import sunburn.core.CustomMath._


/**
 * This is perfect diffuse reflectance.
 * This means that an incident light is scattered equally
 * in the whole hemisphere around the hit point and directed
 * by the normal at the hit point
 *
 * @param kd    Diffuse reflection coefficient
 * @param cd    Diffuse color
 */
class Lambertian(kd: Double, cd: RGBColor) extends BRDF {

    /**
     * @return kd * cd / Pi
     */
    def f (h: HitInfo, wi: Vector3D, wo: Vector3D) : RGBColor = {
        kd * cd * invPi
    }

    /**
     * @return wi is determined by a cosine-weighted sample point on the hemisphere above the hitpoint
     */
    def samplef (h: HitInfo, wo: Vector3D) : (RGBColor, Vector3D) = {
        //TODO
        val wi = wo
        (RGBColor.Black, wi)
    }

    /**
    * Reflectance
    * @return rho = kd * cd
    */
    def rho (h: HitInfo, wo: Vector3D) : RGBColor = {
        kd * cd
    }
}
