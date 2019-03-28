package editor.model;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.imageio.ImageIO;

class Rectangle extends MouseAdapter {

	MainWindow main;
	int startX;
	int startY;
	int endX;
	int endY;
	MouseMotionListener listen;

	Rectangle(MainWindow main) {
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
				System.out.println("Mouse Dragged");
				System.out.println(endX);
				System.out.println(endY);
				print(main.label.getGraphics());
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}
		};
		main.label.addMouseMotionListener(listen);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		main.label.removeMouseMotionListener(listen);
		print(main.graphMain);
	}

	public void print(Graphics graph) {
		graph.setColor(main.color.getColor());
		graph.fillRect(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
		try {
			ImageIO.write(main.image, "png", main.f);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}