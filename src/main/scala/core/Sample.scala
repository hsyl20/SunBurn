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

import sunburn.colors._
import sunburn.geometry.Point2D

package object core {
   implicit def sample2point(s:Sample): Point2D = Point2D(s.x, s.y)
}

case class Sample(x : Double, y: Double)

case class ColoredSample(x: Double, y: Double, color: RGBColor)
