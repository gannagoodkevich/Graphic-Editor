package editor.model;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

class Zoom extends MouseAdapter {

	MainWindow main;
	int zoomLevel = 2;
	int newImageWidth;
	int newImageHeight;
	int imageWidth;
	int imageHeight;

	Zoom(MainWindow main) {
		this.main = main;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			imageWidth = main.getImage().getWidth();
			imageHeight = main.getImage().getHeight();
			newImageWidth = imageWidth * zoomLevel;
			newImageHeight = imageHeight * zoomLevel;
			BufferedImage resizedImage = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(main.getImage(), 0, 0, newImageWidth, newImageHeight, null);
			main.getLabel().setIcon(new ImageIcon(resizedImage));
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			main.getLabel().setIcon(new ImageIcon(main.getImage()));
		}
	}
}