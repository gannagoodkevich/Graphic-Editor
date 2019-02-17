package editor.model;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.image.BufferedImage;
import java.awt.*;

public class PencilButton {
	public JButton pencil;

	public PencilButton() {
		pencil = new JButton(new ImageIcon("G:\\pencil.png"));
	}

	public void pencilPainting(BufferedImage image, int x, int y, Color color) {
		int rgb = color.getRGB();
		for (int i = 0; i < 1800; i++) {
			for (int j = 0; j < 1000; j++) {
				image.setRGB(i, j, rgb);
			}
		}
	}
}

