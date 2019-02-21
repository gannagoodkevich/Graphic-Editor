package editor.utiles;

import java.util.List;
import java.util.LinkedList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestPaint extends JFrame {

	List<Point> points;

	class Clicker extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			onClick(e.getPoint());
		}// mouseClicked

	}// Clicker

	TestPaint() {
		points = new LinkedList<Point>();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setResizable(false);
		setVisible(true);
		addMouseListener(new Clicker());
	}// TestPaint

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		for (Point p : points) {
			g.drawRect(p.x, p.y, 100, 400);
		} // for
	}// paint

	void onClick(Point p) {
		points.add(p);
		repaint();
	}// onClick

	public static void main(String[] args) {
		new TestPaint();
	}// main

}// class TestPaint