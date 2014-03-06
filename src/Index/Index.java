
package Index;

import FeatureExtraction.ColorFeatureExtraction;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
//import javax.vecmath.Vector2d;

/**
 *
 * @author test
 */
public class Index {

    Vector avgMeanRGB;
    String filePath;

    public void setMeanRGB(Vector v) 
    {
        this.avgMeanRGB=v;
    }

    public Vector getMeanRGB() {
        return this.avgMeanRGB;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }
    
    public void setBufferedImage(BufferedImage img)
    {
     
    }
    
      public Vector getMeanRGB(BufferedImage img) 
      {
       ColorFeatureExtraction featureExtraction=new ColorFeatureExtraction();
       featureExtraction.extractFeatures(img);
       this.avgMeanRGB=featureExtraction.colFeatures;
        return this.avgMeanRGB;
      }
}
