/*
 * Main.scala
 *
 */

package sunburn

import sunburn.geometry._
import sunburn.geometry.Vector3D._
import sunburn.core._

import sunburn.samplers._
import sunburn.colors._
import sunburn.colors.RGBColor._
import sunburn.materials._
import sunburn.cameras._
import sunburn.lights._

import scala.actors.Futures._

object Main {

  def main(args: Array[String]) = {

     val myworld = new World() {
        objects (
            new Sphere {
                center = (0.0, 12.0, 0.0)
                radius = 10.0
                material = new MatteMaterial {
                    color = Yellow
                    diffuse = 0.5
                    ambient = 0.5
                }
            },

            new Sphere {
                center = (20.0, 10.0, 0.0)
                radius = 5.0
                material = new MatteMaterial {
                    color = Red
                    diffuse = 1
                    ambient = 1
                }
            },

            new Plane {
                point = (0,0,0)
                normal = (0.0, 1.0, 0)
                material = new MatteMaterial {
                    color = RGBColor(0.2,0.2,0.2)
                    diffuse = 2.2
                    ambient = 2.2
                }
            }
        )
    
        backgroundColor = Green
        
        ambientLight = new SimpleAmbientLight {
            intensity = 0.2
            color = Blue
        }

        lights (
            new PointLight {
                intensity = 8.0
                color = White
                location =  (10,30,10)
            }
        )
     }
    

    val vp = new ViewPlane {
        size = (800,600)
        resolution = 0.2
        //sampler = new BufferedSampler(new ShuffledSampler(new MultiJitteredSampler(16)))
    }

    val c = new PinholeCamera(myworld, vp) {

        eye =  Point3D(0,25,20)
        lookat = Point3D(0,15,0)
        viewDistance = 50.0
    }

    val c2 = new ThinsLensCamera(myworld, vp) {
        eye =  Point3D(0,40,40)
        lookat = Point3D(0,0,0)
        viewDistance = 50.0
        focalDistance = 40.0
        lensRadius = 2.0
        sampler = new RotationDiscSampler(new ShuffledSampler(new MultiJitteredSampler(16)))
    }

    /*BUGGY (see class file)
     val c = new OrthographicCamera(myworld, vp) {
        eye =  Point3D(0,40,40)
        lookat = Point3D(0,0,0)
    }*/

    //val r2 = new Displayer(c2)
    //future(c2.render)
    
    val r = new Displayer(c)
    c.render


    ()
  }

}
