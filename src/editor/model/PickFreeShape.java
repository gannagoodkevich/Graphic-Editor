package editor.model;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList;

class PickFreeShape extends MouseAdapter {

	int WORK_AREA_WIDTH;
	int WORK_AREA_HIGHT;
	int NumOfClick;
	private static final int NUM_OF_VERT = 6;
	MainWindow main;
	ArrayList<Integer> polyX = new ArrayList<Integer>();
	ArrayList<Integer> polyY = new ArrayList<Integer>();
	Polygon poly;
	int startX;
	int startY;
	int endX;
	int endY;
	int x;
	int y;
	MouseMotionListener listen;

	PickFreeShape(MainWindow main) {
		this.main = main;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (NumOfClick < NUM_OF_VERT) {
			NumOfClick += e.getClickCount();
			polyX.add(e.getX());
			polyY.add(e.getY());
			print(main.getGraphicsLabel());
		} else {
			NumOfClick = 0;
			print(main.getGraphicsLabel());
			print(main.getgraphMain());
			main.getGraphicsLabel().drawImage(main.getImage(), 0, 0, null);
			main.updateImage();
		}
	}

	public void print(Graphics graph) {
		WORK_AREA_WIDTH = main.getImage().getWidth();
		WORK_AREA_HIGHT = main.getImage().getHeight();
		graph.setColor(Color.BLUE);
		int[] polyXarr = new int[polyX.size()];
		int[] polyYarr = new int[polyY.size()];
		for (int i = 0; i < polyX.size(); i++) {
			polyXarr[i] = polyX.get(i);
		}
		for (int i = 0; i < polyY.size(); i++) {
			polyYarr[i] = polyY.get(i);
		}
		poly = new Polygon(polyXarr, polyYarr, polyX.size());//
		graph.drawPolygon(poly);

		MouseListener click = new MouseListener() { //anonym inner class that make interface

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent m) {
				startX = m.getX();
				startY = m.getY();
			}

			@Override
			public void mouseReleased(MouseEvent m) {
				endX = m.getX();
				endY = m.getY();
				for (int i = 0; i < WORK_AREA_WIDTH; i++) {
					for (int j = 0; j < WORK_AREA_HIGHT; j++) {
						if (poly.contains(i, j)) {
							main.getImage().setRGB(i + Math.abs(endX - startX), j + Math.abs(endX - startX),
									main.getImage().getRGB(i, j));
						}
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			
			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		};
		main.addMouseListener(click);
	}

}