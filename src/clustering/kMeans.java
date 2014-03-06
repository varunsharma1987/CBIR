/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clustering;

import Index.Index;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * KMeans Class Is Used To Apply K-Means Algorithm
 * In this class clusters of Indexes(Images) Are made
 * according to Euclidean Distance Measure between feature of
 * One Image With feature Of another image
 * 
 */
public class kMeans 
{
    private int numCluster = 2;
    private Cluster[] clusters;
    
    private double mindistance=20.00;
    int clusterindex=0;
    public kMeans()
    {
     
    }
    
    /**
     * 
     * @param indexes
     * @return List<Cluster>
     * 
     * Following Method Applying Clustering Algorithm .
     * It Will Returns all clusters formed from given indexes.
     * Steps:-
     *       1)create Empty List of Clusters 
     *       2)Iterate For Loop Through Number Of Indexes
     *       3)For Each Index find another index who's feature
     *         Vector is closely matched
     *       ( Euclidean Distance Is used For Comparision ).
     *                
     */

    public List<Cluster> formCluster(List<Index> indexes) {

        List<Cluster> clusters = new ArrayList<Cluster>();
        for (int i = 0; i < indexes.size(); i++) {

            try {
                Index firstIndex = indexes.get(i);
//                 System.out.println(" here "+firstIndex.getMeanRGB());
                if (chkIndexExists(clusters, firstIndex)) {
                    continue;
                }

//                System.out.println(" here "+firstIndex.getMeanRGB());

                List<Index> clustersIndexes = new ArrayList<Index>();
                for (int j = i + 1; j < indexes.size(); j++) {
                    //if(compareFeatureVector(indexes., indexes))
                    double distance = compareFeatureVector(firstIndex, indexes.get(j));
                    if (distance <= mindistance) {
                        clustersIndexes.add(indexes.get(j));
                        indexes.remove(j);
                    }

                }
                Cluster cluster = new Cluster(clustersIndexes, firstIndex);
                clusters.add(cluster);

            } catch (Exception e) {
                System.err.println("===== here");
                e.printStackTrace();
            }
        }

        System.err.println("=====" + clusters);


        return clusters;
    }

    /**
     * 
     * @param clusters
     * @param index
     * @return
     * This method checks weather given Index is already exists
     * In one OF the Formed Clusters. If it Exists It Will return False.
     * This Index Is Skipped By Above Method
     * for more info refer comment on isExists method of Cluster class  
     */
    public boolean chkIndexExists(List<Cluster> clusters, Index index) {
       
        boolean flag = false;
        try{
        for (Cluster c : clusters) {
            if (c.isExists(index)) {
                flag = true;
                return flag;
            }
        }
        }catch(Exception e){}
        return flag;
    }
    
 /**
     * 
     * @param index1
     * @param index2
     * @return double value
     * 
     * refer following comment.
     */
    
    public double compareFeatureVector(Index index1,Index index2)
    {
     return compareFeatureVector(index1.getMeanRGB(), index2.getMeanRGB());
    }
  
    
 /*
     * In Following Method Traditional Euclidean Distance Between 
     * 2 Feature Vectors Are Calculated
     * For More About Euclidean Distance refer following sites
     * http://answers.yahoo.com/question/index?qid=20101212102358AA4SXP0
     * http://stackoverflow.com/questions/9650368/euclidean-distance-returning-strange-results
     * http://en.wikipedia.org/wiki/Euclidean_distance
 */
    
    public double compareFeatureVector(Vector vector, Vector vector1) {
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
        
    }
    
}
