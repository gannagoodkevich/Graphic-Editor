package editor.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

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
		print(main.label.getGraphics());
		print(main.graphMain);
		MouseListener click = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				listen = new MouseMotionListener() {
					@Override
					public void mouseDragged(MouseEvent e) {
						x = e.getX();
						y = e.getY();
						System.out.println("Mouse Dragged");
						System.out.println(x);
						System.out.println(x);
					}

					@Override
					public void mouseMoved(MouseEvent e) {
					}
				};
				main.label.addMouseMotionListener(listen);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				main.label.getGraphics().drawImage(subImage, x, y, null);
				main.graphMain.drawImage(subImage, x, y, null);
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
		main.label.removeMouseMotionListener(listen);
		for (int i = 0; i < main.label.getMouseListeners().length; i++) {
			main.label.removeMouseListener(main.label.getMouseListeners()[i]);
		}
		main.label.addMouseListener(click);
	}

	public void print(Graphics graph) {
		graph.setColor(Color.BLUE);
		graph.drawRect(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
		try {
			pickRecShape(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pickRecShape(int x1, int y1, int x2, int y2) throws IOException {
		ImageIO.read(main.f);
		subImage = main.image.getSubimage(x1, y1, x2, y2);
		Graphics2D g = subImage.createGraphics();
		g.drawImage(subImage, 0, 0, null);
		main.f2 = new File("G:\\cutCut.png");
		ImageIO.write(subImage, "png", main.f2);
	}
}