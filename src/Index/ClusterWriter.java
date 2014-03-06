
package Index;

import clustering.Cluster;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import utils.Constants;

/**
 * Clusters That Are Formed using K means Clustering Algorithm Is
 * Saved as xml file ( Refer ../CBIR/index/cluster.xml) 
 * 
 * ClusterWriter Are For Writing clusters Settings From cluster.xml  
 * 
 */
public class ClusterWriter 
{
     DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;
            
    public ClusterWriter()
    {
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();

             // root elements
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ClusterWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
      /**
     *  Following Method is used for Writing clusters stored in cluster.xml File
     * Files are Write according To DOMParser
     * For more Info refer following
     * http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
     * http://javarevisited.blogspot.in/2011/12/parse-xml-file-in-java-example-tutorial.html
     * 
     * here We read cluster.xml File and Forms Cluster Objects.
     * 
     * 
     * 
     */
    
    
    
    
    public void writeClusters(List<Cluster> clusters)
    {
     deleteAllFileFolders();
     
     
        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("ClusterInfo");
        doc.appendChild(rootElement);
        Element numClusters=doc.createElement("NumOfClusters");
        numClusters.appendChild(doc.createTextNode(clusters.size()+""));
        rootElement.appendChild(numClusters); 
//        Element clusterelem=doc.createElement("cluster");
        int attribCnt=1;
         
        String writeText = "";
        for (Cluster cluster : clusters) {
           
            Element clusterelem=doc.createElement("cluster");
             rootElement.appendChild(clusterelem);
//            Attr attr=doc.createAttribute("id");
//            attr.setValue(""+attribCnt++);
//            clusterelem.setAttributeNode(attr);
            clusterelem.setAttribute("id", ""+attribCnt++);
            
            Index Firstindex = cluster.getFirstIndex();
            List<Index> indexes = cluster.getIndexLists();
            double rSpec = ((Double) Firstindex.getMeanRGB().get(0)).doubleValue();
            double gSpec = ((Double) Firstindex.getMeanRGB().get(1)).doubleValue();
            double bSpec = ((Double) Firstindex.getMeanRGB().get(2)).doubleValue();
            
            Element rspec=doc.createElement("rspec");
            rspec.appendChild(doc.createTextNode(rSpec+""));
            clusterelem.appendChild(rspec);
            
            Element gspec=doc.createElement("gspec");
            gspec.appendChild(doc.createTextNode(gSpec+""));
            clusterelem.appendChild(gspec);
            
            Element bspec=doc.createElement("bspec");
            bspec.appendChild(doc.createTextNode(bSpec+""));
            clusterelem.appendChild(bspec);
            
            
            String fileNames = "\n\t\t";
            for (Index index : indexes) {
                fileNames += index.getFilePath() + ";\n\t\t";
            }
//            String record = "@" + rSpec + ":" + gspec + ":" + bspec + ":" + fileNames + "#";
//            writeText += record;
            
            Element imgSrc=doc.createElement("ImageSrc");
            imgSrc.appendChild(doc.createTextNode(fileNames+""));
            clusterelem.appendChild(imgSrc);
            
        }
     
      WriteXML(doc);
    }
    
    
    /*
     * 
     */
    public void WriteXML(Document doc)
    {
        try {
            // write the content into xml file
                       TransformerFactory transformerFactory = TransformerFactory.newInstance();
                       Transformer transformer = transformerFactory.newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                       DOMSource source = new DOMSource(doc);
                       StreamResult result = new StreamResult(new File(Constants.CLUSTER_IDX));
        
                       // Output to console for testing
                       // StreamResult result = new StreamResult(System.out);
        
                       transformer.transform(source, result);
        
                       System.out.println("File saved!");
        } catch (TransformerException ex) {
            Logger.getLogger(ClusterWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClusterWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * 
     */
    
    public void deleteAllFileFolders() 
    {
        try 
        {
           
            File file=new File(Constants.INDEX_DIR);
            FileUtils.deleteDirectory(file);
            file.mkdir();
//            FileUtils.
        } catch (Exception e) 
        {
        }

    }
    
    public static void main(String[] args) {
        //new ClusterWriter().deleteAllFolders();
    }
    
}
