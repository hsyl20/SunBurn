/*
 * Point3D.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.geometry

case class Point3D(x: Double, y: Double, z: Double) extends Tuple3(x,y,z) {

    def + (v : Vector3D) : Point3D =   Point3D(x+v.x, y+v.y, z+v.z)

    def - (v: Point3D) : Vector3D = Vector3D(x-v.x, y-v.y, z-v.z)
    def - (v: Vector3D) : Point3D = Point3D(x-v.x, y-v.y, z-v.z)

    def * (a: Double) : Point3D = Point3D(a*x, a*y, a*z)
}


object Point3D {

    def apply(d: Double) = new Point3D(d,d,d)

    implicit def doubleWrapper(d : Double) =new PointDoubleWrapper(d)
    implicit def tupleWrapper(t: Tuple3[Double, Double, Double]) = Point3D(t._1, t._2, t._3)
}

class PointDoubleWrapper(d: Double) {
    def * (v: Point3D) : Point3D = v * d
}
