

package FeatureExtraction;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class ColorFeatureExtraction 
{
    
//    public 
     public Vector colFeatures;
//     final int IMAGE_HEIGHT=256;
//     final int IMAGE_WIDTH=256;
     final int F=4;
     final int S=4;
     
     Map fragment1=new HashMap();
     
     
    public ColorFeatureExtraction()
    {
        Vector colFeatures=new Vector();  
    }

    public void extractFeatures(BufferedImage bufferedImage) {
        extractFeatures(new FragmentsDivision(bufferedImage));        
    }
   
    public void extractFeatures(FragmentsDivision division) 
    {
        try {
            Vector v = new Vector();
            v.add(avgRGB(division.fragment_s1));
            v.add(avgRGB(division.fragment_s2));
            v.add(avgRGB(division.fragment_s3));
            v.add(avgRGB(division.fragment_s4));
            v.add(avgRGB(division.fragment_s5));
            v.add(avgRGB(division.fragment_s6));
            v.add(avgRGB(division.fragment_s7));
            v.add(avgRGB(division.fragment_s8));
            v.add(avgRGB(division.fragment_s9));
            v.add(avgRGB(division.fragment_s10));
            v.add(avgRGB(division.fragment_s11));
            v.add(avgRGB(division.fragment_s12));
            v.add(avgRGB(division.fragment_s13));
            v.add(avgRGB(division.fragment_s14));
            v.add(avgRGB(division.fragment_s15));
            v.add(avgRGB(division.fragment_s16));

//            System.out.println(avgRGB(division.original));
            colFeatures=meanRGB(v);
//            System.out.println(colFeatures);
          
        } catch (Exception e) {
        }

    }
    
    public Vector avgRGB(BufferedImage img) 
    {
        int r = 0;
        int g = 0;
        int b = 0;
        int w = img.getWidth();
        int h = img.getHeight();
        Vector vector = new Vector(3);
        for (int i = 0; i < w; i++)
        {
            for (int j = 0; j < h; j++)
            {
                Color color = new Color(img.getRGB(i, j));

                r = r + color.getRed();
                g = g + color.getGreen();
                b = b + color.getBlue();

            }
        }

        double dr = (double) r / (double) (h * w);
        double dg = (double) g / (double) (h * w);
        double db = (double) b / (double) (h * w);

        vector.add(dr);
        vector.add(dg);
        vector.add(db);
        return vector;
    }
    
    public Vector meanRGB(Vector v) 
    {

        Vector mean = new Vector(3);
        double meanr = 0;
        double meang = 0;
        double meanb = 0;

        for (Object obj : v) {
            Vector feature = (Vector) v.get(0);
            meanr += ((Double) (feature.get(0))).doubleValue();
            meang += ((Double) (feature.get(1))).doubleValue();
            meanb += ((Double) (feature.get(2))).doubleValue();
        }
        meanr = meanr / v.size();
        meang = meang / v.size();
        meanb = meanb / v.size();
        mean.add(meanr);
        mean.add(meang);
        mean.add(meanb);
        return mean;

    }
    
    
    
    public void deFragment(BufferedImage fragment)
    {
        int width=fragment.getWidth();
        int height=fragment.getHeight();

    }
    
     /*
     * In Following Method Traditional Euclidean Distance Between 
     * 2 Feature Vectors Are Calculated
     * For More About Euclidean Distance refer following sites
     * http://answers.yahoo.com/question/index?qid=20101212102358AA4SXP0
     * http://stackoverflow.com/questions/9650368/euclidean-distance-returning-strange-results
     * http://en.wikipedia.org/wiki/Euclidean_distance
 */
    
    
    public double compareFeatureVector(Vector vector, Vector vector1) 
    {
        Double double1 = (Double) vector.elementAt(0);
        Double double2 = (Double) vector.elementAt(1);
        Double double3 = (Double) vector.elementAt(2);
        Double double4 = (Double) vector1.elementAt(0);
        Double double5 = (Double) vector1.elementAt(1);
        Double double6 = (Double) vector1.elementAt(2);
        double d = (double1.doubleValue() - double4.doubleValue())
                * (double1.doubleValue() - double4.doubleValue());
        double d1 = (double2.doubleValue() - double5.doubleValue())
                * (double2.doubleValue() - double5.doubleValue());
        double d2 = (double3.doubleValue() - double6.doubleValue())
                * (double3.doubleValue() - double6.doubleValue());
        return Math.sqrt((d + d1 + d2) / 3D);
    }
    
    
    
    public static void main(String[] args) {
        try {

            BufferedImage bimage = ImageIO.read(new File("dataset/612.jpg"));
            FragmentsDivision div = new FragmentsDivision(bimage);
            ColorFeatureExtraction cfe = new ColorFeatureExtraction();

            cfe.extractFeatures(div);
            Vector v1 = (Vector) cfe.colFeatures.clone();
            bimage = ImageIO.read(new File("dataset/301.jpg"));

            cfe.extractFeatures(bimage);
            Vector v2 = (Vector) cfe.colFeatures.clone();
            System.out.println("" + cfe.compareFeatureVector(v1, v2));
        } catch (IOException ex) {
            Logger.getLogger(ColorFeatureExtraction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }  
  
}
