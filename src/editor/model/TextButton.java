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

public class TextButton {
	public static JButton text;

	public TextButton() {
		text = new JButton(new ImageIcon("G:\\text.png"));
		changeMouseText();
	}
	
	public void changeMouseText() {
		ActionListener actionListener = new TestActionListener() {// анонимный внутренний класс
			public void actionPerformed(ActionEvent e) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursor = toolkit.getImage("G:\\text.png");
				Point point = new Point(0, 0);
				Cursor cursor1 = toolkit.createCustomCursor(cursor, point, "Cursor");
				Button.paneFile.setCursor(cursor1);
			}
		};
		text.addActionListener(actionListener);
	}
	
	

}
