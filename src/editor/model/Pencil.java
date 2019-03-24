package editor.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;

class Pencil implements Printable {
	MainWindow mw= new MainWindow();
	
	@Override
	public void print(Graphics g) {
		g.setColor(mw.color.getColor());
		g.drawLine(mw.startX, mw.startY, mw.endX, mw.endY);
		try {
			ImageIO.write(mw.image, "png", mw.f);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}