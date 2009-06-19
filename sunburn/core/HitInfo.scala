/*
 * HitInfo.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.core

import sunburn.geometry._
import sunburn.colors._
import sunburn.materials._


/**
 * Clas used when a ray hits an object
 **/
case class HitInfo(
   ray: Ray,
   hitPoint: Point3D,      /* World coordinates of hit point */
   normal: Vector3D,     /* Normal at hit point */
   distance: Double,      /* Ray length from origin to hitPoint*/
   material: Material)
