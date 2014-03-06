
package Image;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author test
 */
public class SnapShot {

    public BufferedImage snapshotImage;
    int wirth;
    int height;

    public SnapShot(String fileName) {
        try {
            this.snapshotImage = ImageIO.read(new File(fileName));

        } catch (Exception e) {
        }
    }

    public BufferedImage clone(BufferedImage image) {
        BufferedImage imageCopy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        imageCopy.setData(image.getData());
        return imageCopy;
    }

    static public BufferedImage createBlankBi(BufferedImage image) {
        BufferedImage imageCopy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        return imageCopy;
    }

    public void linearResize(int width, int height) {
        this.snapshotImage = linearResizeBi(this.snapshotImage, width, height);
    }

    static public BufferedImage linearResizeBi(BufferedImage origin, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        float xScale = (float) width / origin.getWidth();
        float yScale = (float) height / origin.getHeight();
        AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);
        g.drawRenderedImage(origin, at);
        g.dispose();
        return resizedImage;
    }

    public void loadImage(String filepath) throws IOException {
        try {
            File source = new File(filepath);
            BufferedImage image = ImageIO.read(source);
            BufferedImage outimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = outimage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            this.snapshotImage = outimage;
        } catch (IOException ex) {
            throw new IOException("{Error in image loader} Couldn't read input file " + filepath);
        }
    }

    public void saveImage(String filepath) throws IOException {
        String type = new String(filepath.substring(filepath.lastIndexOf('.') + 1, filepath.length()).toUpperCase());
        if (!type.equals("BMP")
                && !type.equals("JPG")
                && !type.equals("JPEG")
                && !type.equals("PNG")) {
            throw new IOException("Unsupported file format");
        }
        File destination = new File(filepath);
        ImageIO.write(this.snapshotImage, type, destination);
    }
}
