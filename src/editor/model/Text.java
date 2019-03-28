package editor.model;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextPane;

class Text extends MouseAdapter {

	List<Point> pointsText = new LinkedList<Point>();
	MainWindow main;

	Text(MainWindow main) {
		this.main = main;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTextPane textField = new JTextPane();
		textField.setText("Add text here!");
		pointsText.add(e.getPoint());
		textField.setBackground(main.myWhite);
		for (Point p : pointsText) {
			textField.setBounds(p.x, p.y, 100, 50);
		}
		main.label.add(textField);
	}
}