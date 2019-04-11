package editor.model;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.util.ArrayList;

class Pencil extends MouseAdapter {
	MainWindow main;
	ArrayList<Point> points;
	MouseMotionListener listen;
	Path2D.Double path = new Path2D.Double();

	Pencil(MainWindow main) {
		this.main = main;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		points = new ArrayList<Point>();
		points.add(e.getPoint());
		listen = new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				points.add(e.getPoint());
				System.out.println("[" + e.getX() + "," + e.getY() + "]");
				print(main.getGraphicsLabel());
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}
		};
		main.addMouseMotionListener(listen);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		main.removeMouseMotionListener(listen);
		print(main.getgraphMain());
		main.updateImage();
	}

	public void print(Graphics graph) {
		if (main.getPencilSize() == 4) {
			((Graphics2D) graph).setStroke(new BasicStroke(4));
		}
		graph.setColor(main.getColor());
		/*
		 * if(points.size()>3) { for (Point point : points) { path.moveTo(point.getX(),
		 * point.getY()); } for(int i=1; i<points.size()-1; i++) {
		 * path.curveTo(points.get(i-1).getX(), points.get(i-1).getY(),
		 * points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(),
		 * points.get(i+1).getY()); } //graph.drawLine(startX, startY, endX, endY); }
		 * ((Graphics2D) graph).draw(path);
		 */
		for (int i = 1; i < points.size(); i++) {
			graph.drawLine((int) points.get(i - 1).getX(), (int) points.get(i - 1).getY(), (int) points.get(i).getX(),
					(int) points.get(i).getY());
		}
	}

}
