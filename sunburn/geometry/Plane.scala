/*
 * Plane.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.geometry

import sunburn.core._
import sunburn.geometry.Vector3D._
import sunburn.materials._

class Plane extends GeometricObject{

    var point: Point3D = Point3D(0,0,0)
    var normal: Vector3D = Vector3D(0,1,0)

    override def hit(ray: Ray) : Option[HitInfo] = {
        val t : Double = (point - ray.origin) * normal / (ray.direction * normal)
        
        if (t > kEpsilon) {
            val hitPoint = ray.origin + t * ray.direction
            val h = HitInfo(ray, hitPoint, normal, t, material)
            Some(h)
        }
        else {
            None
        }
    }
}
