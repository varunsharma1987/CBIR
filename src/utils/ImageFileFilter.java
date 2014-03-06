
package utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImageFileFilter extends FileFilter implements java.io.FileFilter {

    /** Creates a new instance of ImageFileFilter */
    private String exts[] = {".jpg", ".png", ".jpeg", ".gif"};
    private boolean acceptFolder = true;

    public ImageFileFilter(boolean folder) {
        this.acceptFolder = folder;
    }

    public ImageFileFilter() {
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory() && acceptFolder) {
            return true;
        }
        if (file.isFile()) {
            String name = file.getName().toLowerCase();
            for (String ext : exts) {
                if (name.endsWith(ext)) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public String getDescription() {
        return "All Image Types";
    }
}
