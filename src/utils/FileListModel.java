


package utils;

import java.util.Vector;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class FileListModel implements ListModel {
    public class FileListModelEntry {
        public String fileName;
        public String fullPath;
        public String recognizedPlate;
        public FileListModelEntry(String fileName, String fullPath) {
            this.fileName = fileName;
            this.fullPath = fullPath;
            this.recognizedPlate = "?";
        }
        public String toString() {
            return this.fileName;
        }
    }
    
    public Vector<FileListModelEntry> fileList;
    
    /** Creates a new instance of FileListModel */
    public FileListModel() {
        fileList = new Vector<FileListModelEntry>();
    }

    public void addFileListModelEntry(String fileName, String fullPath) {
        fileList.add(new FileListModelEntry(fileName, fullPath));
    }
    
    public int getSize() {
        return fileList.size();
    }

    public Object getElementAt(int index) {
        return fileList.elementAt(index);
    }

    public void addListDataListener(ListDataListener l) {
    }

    public void removeListDataListener(ListDataListener l) {
    }
    
    
}
