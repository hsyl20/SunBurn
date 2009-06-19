/*
 * Renderer.scala
 *
 * @author Sylvain HENRY
 * @license GPL v3
 */

package sunburn.core

import java.awt.image.BufferedImage
import javax.swing.{JLabel, JFrame, ImageIcon, SwingUtilities}
import java.util.{Timer, TimerTask}

import sunburn.cameras._

class Displayer(camera: Camera) {

    SwingUtilities.invokeLater(new Runnable() {
            def run() {
                val label = new JLabel()
                val icon = new ImageIcon(camera.getImage)
                label.setText(null)
                label.setIcon(icon)

                val frame = new JFrame("SunBurn Display");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(label);
                frame.pack();
                frame.setVisible(true);

                val timer = new Timer()
                val task = new TimerTask() {
                    def run() {
                        label.repaint()
                        if (camera.isRendered) cancel()
                    }
                }
                timer.scheduleAtFixedRate(task, 1000, 1000)
            }
        })
}
