/*
 * World.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.core

import sunburn.colors._
import sunburn.geometry._
import sunburn.lights._

class World {

    private var objects : List[GeometricObject] = Nil
    var lights : List[Light] = Nil

    var backgroundColor : RGBColor = RGBColor.Black
    
    var ambientLight : AmbientLight = NoAmbientLight

    
    def objects(o : GeometricObject *) : Unit = {
        objects :::= o.toList
    }

    def lights(l : Light*) : Unit = {
        lights :::= l.toList
    }

    def hitObjects(ray: Ray) : Option[HitInfo] = {
        def reduceHits(h1: Option[HitInfo], h2: Option[HitInfo]): Option[HitInfo] = (h1,h2) match {
            case (None, _) => h2
            case (_, None) => h1
            case (Some(hh1), Some(hh2)) => if (hh1.distance < hh2.distance)  h1 else h2
        }

        //Get hitInfo (or None) for each object
        //Extract the closest one
        objects map (_.hit(ray)) reduceLeft (reduceHits)
    }
}
