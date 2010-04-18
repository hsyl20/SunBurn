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

package fr.hsyl20.sunburn.lights

import fr.hsyl20.sunburn.core._
import fr.hsyl20.sunburn.geometry._
import fr.hsyl20.sunburn.colors._
import fr.hsyl20.sunburn.colors.RGBColor._

class SimpleAmbientLight extends AmbientLight {

    var intensity : Double = 1.0
    var color : RGBColor = RGBColor.White

    /**
     * @return (0,0,0) as it is an ambient light
     */
    def getDirection(hi: HitInfo): Vector3D = {
        Vector3D(0.0)
    }

    /**
     * @param   h Hit point information
     * @return  Color at hit point h
     */
    def L(h: HitInfo) : RGBColor = {
        intensity * color
    }
}
