package editor.model;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import editor.utiles.TestActionListener;

public class RectangleButton {
	public JButton rect;

	public RectangleButton() {
		rect = new JButton(new ImageIcon("G:\\square.png"));
		changeMouseRect();
	}
	
	public void changeMouseRect() {
		ActionListener actionListener = new TestActionListener() {// анонимный внутренний класс(реализует интерфейс)
			public void actionPerformed(ActionEvent e) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursor = toolkit.getImage("G:\\square.png");
				Point point = new Point(0, 0);
				Cursor cursor1 = toolkit.createCustomCursor(cursor, point, "Cursor");
				Button.paneFile.setCursor(cursor1);
			}
		};
		rect.addActionListener(actionListener);
	}

	
}
