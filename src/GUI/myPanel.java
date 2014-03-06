
package GUI;

import Test.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import sun.awt.image.ImageAccessException;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class myPanel extends JPanel {
    
         Image bi;
         ImageIcon ii;
         String FileName="";
         String setImage="";
         
    
    public myPanel()
     {
         
         try {
            String path=new File(".").getCanonicalPath();
                      bi = Toolkit.getDefaultToolkit().getImage(path+"/3_Loading-30302.gif");
                      ii = new ImageIcon(bi);
                      setSize(ii.getIconWidth(), ii.getIconHeight());
                      //panelImage.repaint();
        } catch (IOException ex) {
            //Logger.getLogger(panelView.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     }
     
    //======================================================================================
    public myPanel(String filename)
    {
     try {
//            String path=new File(".").getCanonicalPath();
                      bi = Toolkit.getDefaultToolkit().getImage(filename);
                      ii = new ImageIcon(bi);
                      setSize(ii.getIconWidth(), ii.getIconHeight());
                      //panelImage.repaint();
        } catch (Exception ex) {
            //Logger.getLogger(panelView.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
        public myPanel(File file,int width,int height)
    {
     try {
//            String path=new File(".").getCanonicalPath();
        
                      BufferedImage bi=ImageIO.read(file);
                      setSize(width,height);
                      this.bi=linearResizeBi(bi, width, height);
//                      ii = new ImageIcon(bi);
//                      setSize(ii.getIconWidth(), ii.getIconHeight());
                      //panelImage.repaint();
        } catch (Exception ex) {
            //Logger.getLogger(panelView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //==========================================================================================
    public myPanel(BufferedImage bi,int width, int height)
    {
     try {
            setSize(width,height);         
            this.bi=linearResizeBi(bi, width, height);
            
            
                      //panelImage.repaint();
        } catch (Exception ex) {
            //Logger.getLogger(panelView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BufferedImage linearResizeBi(BufferedImage origin, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        float xScale = (float) width / origin.getWidth();
        float yScale = (float) height / origin.getHeight();
        AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);
        g.drawRenderedImage(origin, at);
        g.dispose();
        return resizedImage;
    }
    
    //===========================
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       // g.drawString(Integer.toString(avg), 75, 75);
        g.drawImage(bi, 0, 0, this);
    }
}
