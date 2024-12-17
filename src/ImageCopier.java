// ImageCopier.java -- Alan Cuevas
/*
 * this is all the necessary stuff to copy an image to a device's clipboard
 */
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import javax.swing.ImageIcon;
import java.io.IOException;

public class ImageCopier {

    public void toClipboard(String img) {
        
        // Get the system clipboard
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Create a new Image object for the image you want to copy
        Image image = new ImageIcon(img).getImage();

        // Create a Transferable object with the image
        TransferableImage transImage = new TransferableImage(image);

        // Set the clipboard's contents to the image
        clipboard.setContents(transImage, null);
    }

    private static class TransferableImage implements Transferable {

        private Image image;

        public TransferableImage(Image image) {
            this.image = image;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { DataFlavor.imageFlavor };
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return DataFlavor.imageFlavor.equals(flavor);
        }

        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if (!DataFlavor.imageFlavor.equals(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return image;
        }
    }
}