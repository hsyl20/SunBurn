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

package fr.hsyl20.sunburn.geometry

import fr.hsyl20.sunburn.core._
import fr.hsyl20.sunburn.geometry.Vector3D._
import fr.hsyl20.sunburn.materials._

class Sphere extends GeometricObject {

    var center: Point3D = Point3D(0,0,0)
    var radius: Double = 1.0

    override def hit(ray: Ray): Option[HitInfo] = {
        val temp = ray.origin - center
        val a = ray.direction * ray.direction
        val b = 2.0 * temp * ray.direction
        val c = temp * temp - radius * radius
        val delta = b * b - 4.0 * a * c

        def returns(t: Double) : Option[HitInfo] = {
                val normal = (temp + t * ray.direction) / radius
                val hitPoint = ray.origin + t * ray.direction
                val h = HitInfo(ray, hitPoint, normal, t, material)
                Some(h)
        }

        if (delta < 0.0) { None }
        else {
            val e = scala.math.sqrt(delta)
            val denom = 2.0 * a
            val t1 = (- b - e) / denom   //smaller root

            if (t1 > kEpsilon) { returns(t1) }
            else {
                val t2 = (-b + e) / denom  //larger root
                if (t2 > kEpsilon) { returns(t2) }
                else None
            }
        }
    }

    
}
