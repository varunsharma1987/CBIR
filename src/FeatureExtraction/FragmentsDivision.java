
package FeatureExtraction;

import java.awt.image.BufferedImage;
import utils.Constants;
import utils.GraphicsUtilities;

/**
 *
 * @author test
 */
public class FragmentsDivision 
{
    public BufferedImage original;
    
    public BufferedImage fragment1;
    public BufferedImage fragment2;
    public BufferedImage fragment3; 
    public BufferedImage fragment4;
    
    public BufferedImage fragment_s1;
    public BufferedImage fragment_s2;
    public BufferedImage fragment_s3;
    public BufferedImage fragment_s4;
    
    public BufferedImage fragment_s5;
    public BufferedImage fragment_s6;
    public BufferedImage fragment_s7;
    public BufferedImage fragment_s8;
    
    public BufferedImage fragment_s9;
    public BufferedImage fragment_s10;
    public BufferedImage fragment_s11;
    public BufferedImage fragment_s12;
    
    public BufferedImage fragment_s13;
    public BufferedImage fragment_s14;
    public BufferedImage fragment_s15;
    public BufferedImage fragment_s16;
    
    int divfactor=2;
    
    public FragmentsDivision(BufferedImage img)
    {
      BufferedImage bufferedImage = GraphicsUtilities.resizeImage(img, Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT);
      this.original=clone(bufferedImage);
      
      fragment1 = bufferedImage.getSubimage(0, 0, Constants.IMAGE_WIDTH / divfactor, Constants.IMAGE_HEIGHT / divfactor);
      fragment2 = bufferedImage.getSubimage(Constants.IMAGE_WIDTH / divfactor, 0, Constants.IMAGE_WIDTH / divfactor, Constants.IMAGE_HEIGHT / divfactor);
      fragment3 = bufferedImage.getSubimage(0, Constants.IMAGE_HEIGHT / divfactor, Constants.IMAGE_WIDTH / divfactor, Constants.IMAGE_HEIGHT / divfactor);
      fragment4 = bufferedImage.getSubimage(Constants.IMAGE_WIDTH / divfactor, Constants.IMAGE_HEIGHT / divfactor, Constants.IMAGE_WIDTH / divfactor, Constants.IMAGE_HEIGHT / divfactor);
      
      applySubDivision(fragment1, 1);
      applySubDivision(fragment2, 2);
      applySubDivision(fragment3, 3);
      applySubDivision(fragment4, 4);
      
    }
    
    public void applySubDivision(BufferedImage fragment,int num)
    {
        int width=fragment.getWidth();
        int height=fragment.getHeight();
        
        
        if (num == 1) 
        {
           fragment_s1=fragment.getSubimage(0, 0, width / divfactor, height / divfactor);
           fragment_s2=fragment.getSubimage(width / divfactor, 0, width/ divfactor, height / divfactor);
           fragment_s3=fragment.getSubimage(0, height / divfactor, width / divfactor, height / divfactor);
           fragment_s4=fragment.getSubimage(width / divfactor, height / divfactor, width / divfactor, height / divfactor);
        }
        
          if (num == 2) 
        {
           fragment_s5=fragment.getSubimage(0, 0, width / divfactor, height / divfactor);
           fragment_s6=fragment.getSubimage(width / divfactor, 0, width/ divfactor, height / divfactor);
           fragment_s7=fragment.getSubimage(0, height / divfactor, width / divfactor, height / divfactor);
           fragment_s8=fragment.getSubimage(width / divfactor, height / divfactor, width / divfactor, height / divfactor);
        }
          if (num == 3) 
        {
           fragment_s9=fragment.getSubimage(0, 0, width / divfactor, height / divfactor);
           fragment_s10=fragment.getSubimage(width / divfactor, 0, width/ divfactor, height / divfactor);
           fragment_s11=fragment.getSubimage(0, height / divfactor, width / divfactor, height / divfactor);
           fragment_s12=fragment.getSubimage(width / divfactor, height / divfactor, width / divfactor, height / divfactor);
        }
          
          if (num == 4) 
        {
           fragment_s13=fragment.getSubimage(0, 0, width / divfactor, height / divfactor);
           fragment_s14=fragment.getSubimage(width / divfactor, 0, width/ divfactor, height / divfactor);
           fragment_s15=fragment.getSubimage(0, height / divfactor, width / divfactor, height / divfactor);
           fragment_s16=fragment.getSubimage(width / divfactor, height / divfactor, width / divfactor, height / divfactor);
        }  
          
          
          
    
    }
    
    
    
    
  public BufferedImage clone(BufferedImage image) {
        BufferedImage imageCopy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        imageCopy.setData(image.getData());
        return imageCopy;
    }

    
}
