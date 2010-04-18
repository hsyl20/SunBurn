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

package fr.hsyl20.sunburn.core

import fr.hsyl20.sunburn.geometry._
import fr.hsyl20.sunburn.colors._
import fr.hsyl20.sunburn.materials._


/**
 * Clas used when a ray hits an object
 **/
case class HitInfo(
   ray: Ray,
   hitPoint: Point3D,      /* World coordinates of hit point */
   normal: Vector3D,     /* Normal at hit point */
   distance: Double,      /* Ray length from origin to hitPoint*/
   material: Material)
