package editor.model;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import editor.model.Button;
import editor.utiles.TestActionListener;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PencilButton {
	public JButton pencil;

	public PencilButton() {
		pencil = new JButton(new ImageIcon("G:\\pencil.png"));
		changeMousePencil();
	}

	public void pencilPainting(BufferedImage image, int x, int y, Color color) {
		int rgb = color.getRGB();
		for (int i = 0; i < 1800; i++) {
			for (int j = 0; j < 1000; j++) {
				image.setRGB(i, j, rgb);
			}
		}
	}

	public void changeMousePencil() {
		ActionListener actionListener = new TestActionListener() {// анонимный внутренний класс
			public void actionPerformed(ActionEvent e) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursor = toolkit.getImage("G:\\pencil.png");
				Point point = new Point(0, 0);
				Cursor cursor1 = toolkit.createCustomCursor(cursor, point, "Cursor");
				Button.paneFile.setCursor(cursor1);
			}
		};
		pencil.addActionListener(actionListener);
	}
}
