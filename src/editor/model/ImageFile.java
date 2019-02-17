package editor.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFile {
	public static BufferedImage image;

	public ImageFile() throws IOException {
		try {
			BufferedImage img = new BufferedImage(1800, 1000, BufferedImage.TYPE_INT_RGB);
			File f = new File("G:\\MyFile.png");
			int r = 255;
			int g = 255;
			int b = 243;
			Color myWhite = new Color(r, g, b);
			image = ImageIO.read(f);
			for (int x=0; x<1800; x++) {
				for(int y=0; y<1000; y++) {
					image.setRGB(x, y, myWhite.getRGB());
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
