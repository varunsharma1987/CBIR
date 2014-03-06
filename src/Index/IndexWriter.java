
package Index;
import FeatureExtraction.ColorFeatureExtraction;
import java.awt.image.BufferedImage;
import utils.Constants;
/**
 *
 * @author test
 */
public class IndexWriter {
       public Index getIndex(BufferedImage bufferedImage) 
    {
        Index index = new Index();

        int w = Constants.IMAGE_WIDTH;
        int h = Constants.IMAGE_HEIGHT;

        ColorFeatureExtraction featureExtraction=new ColorFeatureExtraction();
        featureExtraction.extractFeatures(bufferedImage);
        
        index.setMeanRGB(featureExtraction.colFeatures);
//        System.out.println(index.avgMeanRGB);
        
        return index;

    }
}
