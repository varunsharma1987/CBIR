
package FeatureExtraction;

import Index.Index;
import clustering.Cluster;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class ExtractImages {

    double factor = 10.0;

    public List<File> getBufferedImage(Vector colorFeatureVector, List<Cluster> clusters) {
        ColorFeatureExtraction featureExtraction = new ColorFeatureExtraction();
        List<File> imgList = new ArrayList<File>();
        try {
            for (Cluster cluster : clusters) {
                List<Index> indexList = cluster.getIndexLists();
                for (Index index : indexList) {
                   
                    File file = new File(index.getFilePath().trim());
                    if(file.exists()){
                    BufferedImage img = ImageIO.read(file);

                    Vector meanRgb = index.getMeanRGB(img);
                    double diff = featureExtraction.compareFeatureVector(colorFeatureVector, meanRgb);
                    if (diff <= factor) {
                        imgList.add(file);
                    }
                    }
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(ExtractImages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imgList;
    }
}
