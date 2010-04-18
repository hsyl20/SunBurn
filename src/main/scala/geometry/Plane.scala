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
