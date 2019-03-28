package editor.model;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList;

import javax.imageio.ImageIO;

//import javax.swing.ImageIcon;
class PickFreeShape extends MouseAdapter {

	int NumOfClick;
	private static final int NUM_OF_VERT = 4;
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
			System.out.println(NumOfClick);
			polyX.add(e.getX());
			polyY.add(e.getY());
			print(main.label.getGraphics());
		} else {
			NumOfClick = 0;
			print(main.label.getGraphics());
			print(main.graphMain);
			main.label.getGraphics().drawImage(main.image, 0, 0, null);
			try {
				ImageIO.write(main.image, "png", main.f);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void print(Graphics graph) {
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

		MouseListener click = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent m) {
				System.out.println("Pressed!");
				startX = m.getX();
				startY = m.getY();
			}

			@Override
			public void mouseReleased(MouseEvent m) {
				// TODO Auto-generated method stub
				System.out.println("Released!");
				endX = m.getX();
				endY = m.getY();
				for (int i = 0; i < 1800; i++) {
					for (int j = 0; j < 900; j++) {
						if (poly.contains(i, j)) {
							main.image.setRGB(i + Math.abs(endX - startX), j + Math.abs(endX - startX),
									main.image.getRGB(i, j));
						}
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		};
		main.label.addMouseListener(click);

	}

}