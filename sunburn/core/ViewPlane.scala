/*
 * ViewPlane.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.core

import sunburn.geometry.Point3D
import sunburn.samplers._


/**
 * This is a window on the world
 * The view plane has "width" cells per row with "resolution" real width
 * and "height" cells per column with "resolution" real height
 */
class ViewPlane {
    var width : Int = 640
    var height : Int = 480
    var resolution: Double = 0.2
    var sampler: Sampler = NoSampler


    def size = (width, height)
    
    def size_= (a : Tuple2[Int,Int]) {
        width = a._1
        height = a._2
    }

    def resolution(a: Double) : Unit = {
        resolution = a
    }
}