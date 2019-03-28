package editor.model;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

class Zoom extends MouseAdapter {

	MainWindow main;
	int imageWidth = 1800;
	int imageHeight = 900;
	int zoomLevel = 2;

	Zoom(MainWindow main) {
		this.main = main;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			int newImageWidth = imageWidth * zoomLevel;
			int newImageHeight = imageHeight * zoomLevel;
			BufferedImage resizedImage = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(main.image, 0, 0, newImageWidth, newImageHeight, null);
			main.label.setIcon(new ImageIcon(resizedImage));
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			main.label.setIcon(new ImageIcon(main.image));
		}
	}
}