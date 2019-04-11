package editor.model;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class Eraser extends MouseAdapter {

	MainWindow main;
	int startX;
	int startY;
	int endX;
	int endY;
	MouseMotionListener listen;

	Eraser(MainWindow main) {
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
		graph.setColor(main.getWhite());
		graph.fillRect(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
		main.updateImage();
	}
}