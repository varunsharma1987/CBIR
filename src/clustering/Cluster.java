
package clustering;

import Index.Index;
import java.util.List;

/**
 * Cluster class Holds All Clusters Formed By Kmeans Clustering Algorithm.
 * Here List<index> Denotes all indexes in on clusters.
 * i.e Set of all Images In one Cluster.
 */

public class Cluster 
{

    Index index;
    List<Index> indexes;

    
    /** 
     * @param indexes
     * @param index
     * Cluster constructor Here Give 2 parameters
     * 1)List Of Indexes That forms cluster
     * 2)index Representing average feature Vector of this cluster
     */
    public Cluster(List<Index> indexes, Index index) 
    {
        this.index = index;
        this.indexes = indexes;
    }
    
//    public Cluster(List<Index> indexes, Index index) 
//    {
//        this.index = index;
//        this.indexes = indexes;
//    }
    
 /*
  *   Here Checking given index in already In formed Clustered.
  *   If it exists it will return false Else It will return True 
  */  
    public boolean isExists(Index index) {
        boolean flag = false;
        for (Index index1 : indexes) {
            if (index1 == index) {
                flag = true;
                break;
            }

        }
        return flag;
    }
    /*
     * Getting First index Recall Cluster Constructor
     * public Cluster(List<Index> indexes, Index index) 
     * parameter (Index index) is represents FirstIndex
     * 
     */
    public Index getFirstIndex()
    {
     return this.index ;
    }
    
    /*
     * Getting Lists Of All Indexes in Given Cluster 
     * 
     */
    public List<Index> getIndexLists()
    {
      return this.indexes;
     
    }
    
}
