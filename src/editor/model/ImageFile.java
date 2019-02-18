package editor.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFile {
	public BufferedImage image;
	private Color myWhite;
	private Color currentColor;

	public ImageFile() throws IOException {
		try {
			BufferedImage img = new BufferedImage(1800, 1000, BufferedImage.TYPE_INT_RGB);
			File f = new File("G:\\MyFile.png");
			myWhite = new Color(255, 255, 243);
			image = ImageIO.read(f);
			currentColor = new Color(0, 0, 50);
			for (int x = 0; x < 1800; x++) {
				for (int y = 0; y < 1000; y++) {
					image.setRGB(x, y, myWhite.getRGB());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void drowRectangle(int x1, int y1, int x2, int y2) {
		for (int x = x1; x < x2; x++) {
			for(int y = y1; y<y2; y++) {
			image.setRGB(x, y, currentColor.getRGB());
			}
		}
	}
	
	public void changeColor(int r, int g, int b) {
		currentColor = new Color(r, g, b);
	}
}
