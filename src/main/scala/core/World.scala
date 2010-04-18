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

import fr.hsyl20.sunburn.colors._
import fr.hsyl20.sunburn.geometry._
import fr.hsyl20.sunburn.lights._

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
