/*
 * vectors.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.geometry

case class Vector3D(x: Double, y: Double, z: Double) extends Tuple3(x,y,z) {
    
    def + (v : Vector3D) : Vector3D =   Vector3D(x+v.x, y+v.y, z+v.z)

    def - (v: Vector3D) : Vector3D = Vector3D(x-v.x, y-v.y, z-v.z)
    def unary_- : Vector3D = Vector3D(-x, -y, -z)

    def * (a: Double) : Vector3D = Vector3D(a*x, a*y, a*z)

    def / (a: Double) : Vector3D = Vector3D(x/a, y/a, z/a)

    lazy val N  : Double = Math.sqrt(N2)
    lazy val N2  : Double = x*x + y*y + z*z

    def * (v: Vector3D) : Double = x*v.x + y*v.y + z*v.z
    def ^ (v: Vector3D) : Vector3D =Vector3D (y*v.z - z*v.y, z*v.x - v.z*x, x*v.y - y*v.x)

    //Called "hat()" in RTftGU
    def normalize() : Vector3D = Vector3D(x/N, y/N, z/N)
}


object Vector3D {

    def apply(d: Double) = new Vector3D(d,d,d)
    
    implicit def doubleWrapper(d : Double) =new VectorDoubleWrapper(d)
    implicit def tupleWrapper(t: Tuple3[Double, Double, Double]) = Vector3D(t._1, t._2, t._3)
}

class VectorDoubleWrapper(d: Double) {
    def * (v: Vector3D) : Vector3D= v * d
}