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
import fr.hsyl20.sunburn.colors.RGBColor._
import fr.hsyl20.sunburn.core._
import fr.hsyl20.sunburn.math._
import fr.hsyl20.sunburn.geometry._
import fr.hsyl20.sunburn.samplers.Sampler


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
