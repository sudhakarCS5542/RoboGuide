import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import net.sourceforge.tess4j.*;
import javax.imageio.*;

/**
 * Created by sudhakar on 3/13/16.
 */
public class MainOCR {
    public static void main(String[] args) throws IOException{
        //File imageFile = new File("/Users/sudhakar/Downloads/luckybrand_07.jpg");
        File imageFile = new File("/Users/sudhakar/Downloads/Library.png");
        Tesseract instance = new Tesseract(); //
        BufferedImage bufferedImage = null;
        bufferedImage = ImageIO.read(imageFile);

        instance.setDatapath("/Users/sudhakar/Documents/Tess4J-2/");

        try {

            String result = instance.doOCR(instance.convertImageToBinary(bufferedImage));
            //String result = instance.doOCR(imageFile);
            System.out.println(result);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
