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
