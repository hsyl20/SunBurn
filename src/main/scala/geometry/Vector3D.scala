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

class Vector3D(val x: Double, val y: Double, val z: Double) extends Tuple3(x,y,z) {
    
    def + (v : Vector3D) : Vector3D = Vector3D(x+v.x, y+v.y, z+v.z)

    def - (v: Vector3D) : Vector3D = Vector3D(x-v.x, y-v.y, z-v.z)
    def unary_- : Vector3D = Vector3D(-x, -y, -z)

    def * (a: Double) : Vector3D = Vector3D(a*x, a*y, a*z)

    def / (a: Double) : Vector3D = Vector3D(x/a, y/a, z/a)

    lazy val N  : Double = scala.math.sqrt(N2)
    lazy val N2  : Double = x*x + y*y + z*z

    def * (v: Vector3D) : Double = x*v.x + y*v.y + z*v.z
    def ^ (v: Vector3D) : Vector3D = Vector3D (y*v.z - z*v.y, z*v.x - v.z*x, x*v.y - y*v.x)

    //Called "hat()" in RTftGU
    def normalize() : Vector3D = Vector3D(x/N, y/N, z/N)
}


object Vector3D {
   def apply(x:Double, y:Double, z:Double) = new Vector3D(x,y,z)
   def apply(d: Double) = new Vector3D(d,d,d)
    
   implicit def doubleWrapper(d : Double) = new VectorDoubleWrapper(d)
   implicit def tupleWrapper(t: Tuple3[Double, Double, Double]) = Vector3D(t._1, t._2, t._3)
}

class VectorDoubleWrapper(d: Double) {
    def * (v: Vector3D) : Vector3D = v * d
}
