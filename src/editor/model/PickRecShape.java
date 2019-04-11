package editor.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import java.io.IOException;

class PickRecShape extends MouseAdapter {

	MainWindow main;
	BufferedImage subImage;
	int startX;
	int startY;
	int endX;
	int endY;
	int x;
	int y;
	MouseMotionListener listen;

	PickRecShape(MainWindow main) {
		this.main = main;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		endX = e.getX();
		endY = e.getY();
		print(main.getGraphicsLabel());
		print(main.getgraphMain());
		MouseListener click = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				listen = new MouseMotionListener() {
					@Override
					public void mouseDragged(MouseEvent e) {
						x = e.getX();
						y = e.getY();
					}

					@Override
					public void mouseMoved(MouseEvent e) {
					}
				};
				main.addMouseMotionListener(listen);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				main.getGraphicsLabel().drawImage(subImage, x, y, null);
				main.getgraphMain().drawImage(subImage, x, y, null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		};
		main.removeMouseMotionListener(listen);
		main.addMouseListener(click);
	}

	public void print(Graphics graph) {
		graph.setColor(Color.BLUE);
		graph.drawRect(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
		try {
			pickRecShape(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pickRecShape(int x1, int y1, int x2, int y2) throws IOException {
		main.readImage();
		subImage = main.getImage().getSubimage(x1, y1, x2, y2);
		Graphics2D g = subImage.createGraphics();
		g.drawImage(subImage, 0, 0, null);
	}
}