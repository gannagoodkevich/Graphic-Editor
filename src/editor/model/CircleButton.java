package editor.model;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import editor.utiles.TestActionListener;

public class CircleButton {
	public static JButton circle;

	public CircleButton() {
		circle = new JButton(new ImageIcon("G:\\circle.png"));
		changeMouseCircle();
	}
	
	public void changeMouseCircle() {
		ActionListener actionListener = new TestActionListener() {// анонимный внутренний класс
			public void actionPerformed(ActionEvent e) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursor = toolkit.getImage("G:\\circle.png");
				Point point = new Point(0, 0);
				Cursor cursor1 = toolkit.createCustomCursor(cursor, point, "Cursor");
				Button.paneFile.setCursor(cursor1);
			}
		};
		circle.addActionListener(actionListener);
	}

}
