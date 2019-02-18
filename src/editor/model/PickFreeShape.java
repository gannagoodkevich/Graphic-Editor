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

public class PickFreeShape {
	
		public JButton free;

		public PickFreeShape() {
			free = new JButton(new ImageIcon("G:\\pick2.png"));
			changeMousePick2();
		}
		
		public void changeMousePick2() {
			ActionListener actionListener = new TestActionListener() {// ��������� ���������� �����
				public void actionPerformed(ActionEvent e) {
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Image cursor = toolkit.getImage("G:\\pick2.png");
					Point point = new Point(0, 0);
					Cursor cursor1 = toolkit.createCustomCursor(cursor, point, "Cursor");
					Button.paneFile.setCursor(cursor1);
				}
			};
			free.addActionListener(actionListener);
		}
}
