
package GUI;

import Index.Index;
import clustering.Cluster;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author test
 */
public class ThumbPanelRenderer {

    public ThumbPanelRenderer() 
    {
        
    }
    
    public List<ThumbPanel> getPanles(Cluster cluster) {
        List<Index> indexes = cluster.getIndexLists();
        List<File> images = new ArrayList<File>();
        for (Index index : indexes) {
            File file = getFile(index.getFilePath());
            if (file != null) {
                images.add(file);
            }
        }

//        int size=images.size();
//        List<ThumbPanel> thumb = new ArrayList<ThumbPanel>();
//        int cnter = 0;
//        for (int i = 0; i < images.size(); i = i + 4) {            
//            
//            ThumbPanel thumbpanel = new ThumbPanel();            
//            if (i < images.size()) {
//                thumbpanel.setImage1(images.get(i));
//                
//            }
//            if (i + 1 < images.size()) {
//                thumbpanel.setImage2(images.get(i + 1));
//                
//            }
//            if (i + 2 < images.size()) {
//                thumbpanel.setImage3(images.get(i + 2));
//                
//            }
//            if (i + 3 < images.size()) {
//                thumbpanel.setImage4(images.get(i + 3));
//                
//            }
//            
//            thumb.add(thumbpanel);
//        }
        
        return getPanles(images);        
    }
    
    public List<ThumbPanel> getPanles(List<File> images)
    {
    
              List<ThumbPanel> thumb = new ArrayList<ThumbPanel>();
        int cnter = 0;
        for (int i = 0; i < images.size(); i = i + 4) {            
            
            ThumbPanel thumbpanel = new ThumbPanel();            
            if (i < images.size()) {
                thumbpanel.setImage1(images.get(i));
                
            }
            if (i + 1 < images.size()) {
                thumbpanel.setImage2(images.get(i + 1));
                
            }
            if (i + 2 < images.size()) {
                thumbpanel.setImage3(images.get(i + 2));
                
            }
            if (i + 3 < images.size()) {
                thumbpanel.setImage4(images.get(i + 3));
                
            }
            
            thumb.add(thumbpanel);
        }
       return thumb;       
    }
    
    
    
 public File getFile(String file)
 {
   File f=new File(file.trim());
   if(f.exists())
   {
    return f;
   }
   return null;  
 }
    
   
 
 
 
}
