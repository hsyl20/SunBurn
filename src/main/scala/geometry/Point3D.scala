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

class Point3D(val x:Double, val y:Double, val z:Double) extends Tuple3(x,y,z) {

   def + (v : Vector3D) : Point3D =   Point3D(x+v.x, y+v.y, z+v.z)

   def - (v: Point3D) : Vector3D = Vector3D(x-v.x, y-v.y, z-v.z)
   def - (v: Vector3D) : Point3D = Point3D(x-v.x, y-v.y, z-v.z)

   def * (a: Double) : Point3D = Point3D(a*x, a*y, a*z)
}


object Point3D {
   def apply(x:Double, y:Double, z:Double) = new Point3D(x,y,z)
   def apply(d: Double) = new Point3D(d,d,d)

   implicit def doubleWrapper(d : Double) = new PointDoubleWrapper(d)
}

class PointDoubleWrapper(d: Double) {
    def * (v: Point3D) : Point3D = v * d
}
