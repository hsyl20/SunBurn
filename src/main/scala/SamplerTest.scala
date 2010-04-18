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

package fr.hsyl20.sunburn

import javax.swing._
import java.awt.Color
import java.awt.event._
import fr.hsyl20.sunburn.samplers._
import java.awt.image.BufferedImage
import fr.hsyl20.sunburn.core._

object SamplerTest {

    /* Sample count */
    val count = 256
    /* Column width */
    val colwidth = 40
    /* Pixel width */
    val pixWidth = 2

    val width  = scala.math.sqrt(count).round.toInt * colwidth
    val realwidth = width+10
    var image : BufferedImage = new BufferedImage(realwidth,realwidth+10, BufferedImage.TYPE_INT_ARGB)

    def main(args: Array[String]) = {
        val label = new JLabel()
        val icon = new ImageIcon(drawSamples(new RegularSampler(count).generate))
        label.setText(null)
        label.setIcon(icon)
        val cb = new JComboBox()

        cb.addItem(new RegularSampler(count){override def toString = "RegularSampler"})
        cb.addItem(new RandomSampler(count){override def toString = "RandomSampler"})
        cb.addItem(new JitteredSampler(count){override def toString = "JitteredSampler"})
        cb.addItem(new NRooksSampler(count){override def toString = "NRooksSampler"})
        cb.addItem(new MultiJitteredSampler(count){override def toString = "MultiJitteredSampler"})
        cb.addItem(new HammersleySampler(count){override def toString = "HammersleySampler"})

        cb.addItem(new BufferedSampler(new RandomSampler(count), 2){override def toString = "BufferedSampler(RandomSampler, 2 sets) "})
        cb.addItem(new ShuffledSampler(new HammersleySampler(count)) {override def toString = "ShuffledSampler(Hammersley)"})

        cb.addItem(new RejectionDiscSampler(new RegularSampler(count)){override def toString = "RejectionDiscSampler(RegularSampler)"})
        cb.addItem(new RejectionDiscSampler(new RandomSampler(count)){override def toString = "RejectionDiscSampler(RandomSampler)"})
        cb.addItem(new RejectionDiscSampler(new MultiJitteredSampler(count)){override def toString = "RejectionDiscSampler(MultiJitteredSampler)"})

        cb.addItem(new RotationDiscSampler(new MultiJitteredSampler(count)){override def toString = "DiscSampler(MultiJitteredSampler)"})
        cb.addItem(new RotationDiscSampler(new RegularSampler(count)){override def toString = "DiscSampler(RegularSampler)"})
        cb.addItem(new RotationDiscSampler(new HammersleySampler(count)){override def toString = "DiscSampler(HammersleySampler)"})
        cb.addItem(new RotationDiscSampler(new ShuffledSampler(new HammersleySampler(count))){override def toString = "DiscSampler(ShuffledSampler(HammersleySampler))"})

        cb.addItem(new HemisphereSampler(new RegularSampler(count), 1.0){override def toString = "HemisphereSampler(RegularSampler, 1.0)"})
        cb.addItem(new HemisphereSampler(new HammersleySampler(count), 1.0){override def toString = "HemisphereSampler(HammersleySampler, 1.0)"})
        cb.addItem(new HemisphereSampler(new HammersleySampler(count), 5.0){override def toString = "HemisphereSampler(HammersleySampler, 5.0)"})
        cb.addItem(new HemisphereSampler(new HammersleySampler(count), 10.0){override def toString = "HemisphereSampler(HammersleySampler, 10.0)"})
        cb.addItem(new HemisphereSampler(new HammersleySampler(count), 100.0){override def toString = "HemisphereSampler(HammersleySampler, 100.0)"})
        cb.addItem(new HemisphereSampler(new HammersleySampler(count), 1000.0){override def toString = "HemisphereSampler(HammersleySampler, 1000.0)"})

        

        cb.addActionListener(new ActionListener {
                def actionPerformed(e : ActionEvent) {
                    cb.getSelectedItem match {
                        case s : DiscSampler => label.setIcon(new ImageIcon(drawCircle(drawSamples(s.generate.map(e => Sample(e.x / 2.0 +0.5, e.y /2.0 + 0.5))))))
                        case s : Sampler => label.setIcon(new ImageIcon(drawSamples(s.generate)))
                        case s : HemisphereSampler => label.setIcon(new ImageIcon(drawCircle(drawSamples(s.generate.map (e => Sample(e.x/2.0 + 0.5,e.y/2.0 + 0.5))))))
                    }
                }
            })

        val frame = new JFrame("SunBurn Test: Samplers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout( frame.getContentPane(), BoxLayout.Y_AXIS))
        frame.getContentPane().add(label);
        frame.getContentPane().add(cb);
        frame.pack();
        frame.setVisible(true);
    }

    def drawSamples(samples: Seq[Sample]) : BufferedImage = {
        //Background
        for (i<- 0 until realwidth; j<- 0 until realwidth)
                image.setRGB(i,j,Color.WHITE.getRGB)
        //Grid
        for (i<- 0 until width; j<- 0 until width if i % colwidth == 0 || j % colwidth == 0 || i == width-1 || j == width -1)
                image.setRGB(i,j,Color.GRAY.getRGB)
            
        //Samples and sample projections (left, bottom)
        var num = 0.0
        for (Sample(x,y) <- samples) {
            val px = (x * width).toInt
            val py = (y * width).toInt
            implicit def tofloat(d:Double) = d.toFloat
            val color = new Color(0.2 + 0.8 * num / samples.size.toDouble, 0.0, 0.0)
            for (i <- px-pixWidth to px+pixWidth if i >= 0 && i < width;
                   j <- py-pixWidth to py+pixWidth if j >= 0 && j < width) {
              image.setRGB(i,j, color.getRGB)
              image.setRGB(width+5, j, Color.BLUE.getRGB)
              image.setRGB(width+6, j, Color.BLUE.getRGB)
              image.setRGB(i, width+5, Color.BLUE.getRGB)
              image.setRGB(i, width+6, Color.BLUE.getRGB)
            }
            num += 1
        }
        image
    }

    def drawCircle(b: BufferedImage) : BufferedImage = {
      import scala.math._
        val d = width / 2.0
        for (a <- 0 until 360) {
            val x = cos((a/2.0).toRadians) * d + d
            val y = sin((a/2.0).toRadians) * d + d
            b.setRGB(x.toInt, y.toInt, Color.BLACK.getRGB)
            val x2 = cos(a.toDouble.toRadians) * d + d
            val y2 = sin(a.toDouble.toRadians) * d + d
            b.setRGB(x2.toInt, y2.toInt, Color.BLACK.getRGB)

        }
        b
    }
}
