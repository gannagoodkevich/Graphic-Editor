package editor.model;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.imageio.ImageIO;

class Circle extends MouseAdapter {
	MainWindow main;
	int startX;
	int startY;
	int endX;
	int endY;
	MouseMotionListener listen;

	Circle(MainWindow main) {
		this.main = main;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
		listen = new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				endX = e.getX();
				endY = e.getY();
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
	}

	public void print(Graphics graph) {
		graph.setColor(main.getColor());
		int radius = Math.abs((endX - startX) / 2);
		int x = startX - (radius / 2);
		int y = startY - (radius / 2);
		graph.fillOval(x, y, radius, radius);
		;
		main.updateImage();
	}
}