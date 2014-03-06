
package Index;

import clustering.Cluster;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.Constants;

/**
 * Clusters That Are Formed using K means Clustering Algorithm Is
 * Saved as xml file ( Refer ../CBIR/index/cluster.xml) 
 * 
 * ClusterReader Are For Reading Xlusters Settings From cluster.xml  
 * 
 */
public class ClusterReader 
{
    public List<Cluster> clusters;
     int numOfCluster=0;    
     public ClusterReader() 
     {
         
     }
    
     
    public void setSize(int numOfClusters) 
    {
        this.numOfCluster = numOfClusters;
    }

    
    public int getSize() 
    {
        return this.numOfCluster;
    }
    
    
     public void setCluster(List<Cluster> clusters)
     {
       this.clusters=clusters;
       setSize(clusters.size());
     }
     
    public List<Cluster> getCluster() 
    {
         return this.clusters;
         
    }
     
    public Cluster getCluster(int index)
    {
     return this.clusters.get(index-1);
    }
    
    
    
    
    /**
     *  Following Method is used for reading clusters stored in cluster.xml File
     * Files are read according To DOMParser
     * For more Info refer following
     * http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
     * http://javarevisited.blogspot.in/2011/12/parse-xml-file-in-java-example-tutorial.html
     * 
     * here We read cluster.xml File and Forms Cluster Objects.
     * 
     * 
     * 
     */
    
    public void readCluster() 
    {
        List<Cluster> clusters = new ArrayList<Cluster>();
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(Constants.CLUSTER_IDX));

            doc.getDocumentElement().normalize();
            System.out.println("Root element of the doc is "
                    + doc.getDocumentElement().getNodeName());


            NodeList listOfClusters = doc.getElementsByTagName("cluster");
            int totalCluster = listOfClusters.getLength();
            System.out.println("" + totalCluster);



            for (int i = 0; i < listOfClusters.getLength(); i++) {
                List<Index> indexes = new ArrayList<Index>();
                Index index = new Index();
                Node FirstNode = listOfClusters.item(i);
                if (FirstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element clusterElement = (Element) FirstNode;

                    //-------------------------------------------------
                    NodeList rspecList = clusterElement.getElementsByTagName("rspec");
                    Element rspecElement = (Element) rspecList.item(0);

                    NodeList textrList = rspecElement.getChildNodes();
                    String rspec = "" + ((Node) textrList.item(0)).getNodeValue().trim();
                    double rspecVal = Double.parseDouble(rspec);
                    //System.out.println("rspec : "
                    //        + ((Node) textrList.item(0)).getNodeValue().trim());
                    //-------------------------------------------------

                    NodeList gspecList = clusterElement.getElementsByTagName("gspec");
                    Element gspecElement = (Element) gspecList.item(0);

                    NodeList textgList = gspecElement.getChildNodes();
                    String gspec = "" + ((Node) textgList.item(0)).getNodeValue().trim();
                    double gspecVal = Double.parseDouble(gspec);

//                     System.out.println("gspec : "
//                             + ((Node) textgList.item(0)).getNodeValue().trim());

                    //--------------------------------------------------- - -

                    NodeList bspecList = clusterElement.getElementsByTagName("bspec");
                    Element bspecElement = (Element) bspecList.item(0);

                    NodeList textbList = bspecElement.getChildNodes();
                    String bspec = "" + ((Node) textbList.item(0)).getNodeValue().trim();
                    double bspecVal = Double.parseDouble(bspec);

//                     System.out.println("bspec : "
//                             + ((Node) textbList.item(0)).getNodeValue().trim());

                    //-----------------------------------------------------------------
                    Vector v = new Vector();
                    v.add(rspecVal);
                    v.add(gspecVal);
                    v.add(bspecVal);
                    index.setMeanRGB(v);


                    NodeList imgList = clusterElement.getElementsByTagName("ImageSrc");
                    Element imgElement = (Element) imgList.item(0);

                    NodeList textimgList = imgElement.getChildNodes();
                    String images = "" + ((Node) textimgList.item(0)).getNodeValue().trim();
                    String imgArr[] = images.split(";");
                    for (String img : imgArr) {

                        Index imgIndex = new Index();
//                         System.out.println(""+img);            
                        imgIndex.setFilePath(img.trim());
                        indexes.add(imgIndex);
                    }

                    Cluster c = new Cluster(indexes, index);
                    clusters.add(c);
//                     System.out.println("img : "
//                             + ((Node) textimgList.item(0)).getNodeValue().trim());




                }

            }

            setCluster(clusters);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


     
    
    
     
     
     
     
//     public static void main(String[] args) 
//     {
//      
//         try {
//             
//             DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
//             DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
//             Document doc = docBuilder.parse(new File(Constants.CLUSTER_IDX));
//
//             doc.getDocumentElement().normalize();
//             System.out.println("Root element of the doc is "
//                     + doc.getDocumentElement().getNodeName());
//            
//             NodeList listOfClusters = doc.getElementsByTagName("cluster");
//             int totalPersons = listOfClusters.getLength();
//             System.out.println(""+totalPersons);
//             
//             for(int i=0;i<listOfClusters.getLength();i++)
//             {
//                 
//                 Node FirstNode=listOfClusters.item(i);
//                 if(FirstNode.getNodeType() == Node.ELEMENT_NODE)
//                 {
//                   Element clusterElement=(Element)FirstNode;
//                  
//                   //-------------------------------------------------
//                     NodeList rspecList = clusterElement.getElementsByTagName("rspec");
//                     Element rspecElement = (Element) rspecList.item(0);
//
//                     NodeList textrList = rspecElement.getChildNodes();
//                     System.out.println("rspec : "
//                             + ((Node) textrList.item(0)).getNodeValue().trim());
//                   //-------------------------------------------------
//                     
//                     NodeList gspecList = clusterElement.getElementsByTagName("gspec");
//                     Element gspecElement = (Element) gspecList.item(0);
//
//                     NodeList textgList = gspecElement.getChildNodes();
//                     System.out.println("gspec : "
//                             + ((Node) textgList.item(0)).getNodeValue().trim());
//                     
//                   //--------------------------------------------------- - -
//                     
//                     NodeList bspecList = clusterElement.getElementsByTagName("bspec");
//                     Element bspecElement = (Element) bspecList.item(0);
//
//                     NodeList textbList = bspecElement.getChildNodes();
//                     System.out.println("bspec : "
//                             + ((Node) textbList.item(0)).getNodeValue().trim());
//                     
//                   //-----------------------------------------------------------------
//                     
//                     NodeList imgList = clusterElement.getElementsByTagName("ImageSrc");
//                     Element imgElement = (Element) imgList.item(0);
//
//                     NodeList textimgList = imgElement.getChildNodes();
//                     System.out.println("img : "
//                             + ((Node) textimgList.item(0)).getNodeValue().trim());
//                     
//                     
//                     
//                     
//                 }
//             }
//             
//             
//         } catch (Exception e) 
//         {
//             e.printStackTrace();
//         }
//         
//     }
//   
    
    
    public static void main(String[] args) 
    {
        
        ClusterReader reader=new ClusterReader();
        reader.readCluster();
        List<Cluster> clusters=reader.getCluster(); 
        for(Cluster c:clusters)
        {
         Index index=c.getFirstIndex();
         System.out.println(index.avgMeanRGB);
        }
        
    }
    
    
             
}
