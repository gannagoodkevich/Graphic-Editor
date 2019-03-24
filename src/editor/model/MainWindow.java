package editor.model;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainWindow {

	private static final int WORK_AREA_WIDTH = 2000;
	private static final int WORK_AREA_HIGHT = 2000;
	private static final int AREA_WIDTH = 500;
	private static final int AREA_HIGHT = 500;
	private static final int NUM_OF_VERT = 6;
	private static final String PENCIL_PATH = "G:\\pencil.png";
	private static final String CIRCLE_PATH = "G:\\circle.png";
	private static final String RECTANGLE_PATH = "G:\\square.png";
	private static final String ERASER_PATH = "G:\\eraser.png";
	private static final String TEXT_PATH = "G:\\text.png";
	private static final String PICK_RECT_PATH = "G:\\pick1.png";
	private static final String PICK_FREE_PATH = "G:\\pick2.png";
	private static final String ZOOM_PATH = "G:\\find.png";
	public JFrame main;
	BufferedImage image;
	BufferedImage subImage;
	JTextPane textField;
	JColorChooser color;
	File f;
	File f2;
	Printable pr;
	Graphics2D graphMain;
	JLabel label;
	int m = 0;

	List<Point> pointsText;
	ArrayList<Integer> polyX = new ArrayList<Integer>();
	ArrayList<Integer> polyY = new ArrayList<Integer>();
	PickFreeShape pick2 = new PickFreeShape();
	int NumOfClick = 0;

	int boo;
	int startX;
	int startY;
	int endX;
	int endY;

	JMenu[] menus = { new JMenu("File"), new JMenu("Edit"), new JMenu("Paint") };
	JMenuItem[] itemsFile = { new JMenuItem("Save"), new JMenuItem("Open") };
	JMenuItem[] itemsEdit = { new JMenuItem("Resize"), new JMenuItem("Pick"), new JMenuItem("Text") };
	JMenuItem[] itemsPaint = { new JMenuItem("Pencil"), new JMenuItem("Shape"), new JMenuItem("Eraser") };
	JMenuItem[] additionalPick = { new JMenuItem("Rectangle"), new JMenuItem("Free") };
	JMenuItem[] additionalShape = { new JMenuItem("Rectangle"), new JMenuItem("Circle") };

	Clicker clicker;
	Color myWhite = new Color(255, 255, 243);
	int curRed = 120;
	int curGreen = 50;
	int curBlue = 120;

	class Pencil extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			startX = e.getX();
			startY = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			endX = e.getX();
			endY = e.getY();
			print(label.getGraphics());
			print(graphMain);
		}

		public void print(Graphics graph) {
			graph.setColor(color.getColor());
			graph.drawLine(startX, startY, endX, endY);
			try {
				ImageIO.write(image, "png", f);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

///////////// That part of code is about mouse clicking	

	class Circle extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			startX = e.getX();
			startY = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			endX = e.getX();
			endY = e.getY();
			print(label.getGraphics());
			print(graphMain);
		}

		public void print(Graphics graph) {
			graph.setColor(color.getColor());
			graph.fillOval(startX, startY, Math.abs((endX - startX) / 2), Math.abs((endX - startX) / 2));
			;
			try {
				ImageIO.write(image, "png", f);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	class Eraser extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			startX = e.getX();
			startY = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			endX = e.getX();
			endY = e.getY();
			print(label.getGraphics());
			print(graphMain);
		}

		public void print(Graphics graph) {
			graph.setColor(myWhite);
			graph.fillRect(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
			try {
				ImageIO.write(image, "png", f);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	class Rectangle extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			startX = e.getX();
			startY = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			endX = e.getX();
			endY = e.getY();
			print(label.getGraphics());
			print(graphMain);
		}

		public void print(Graphics graph) {
			graph.setColor(color.getColor());
			graph.fillRect(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
			try {
				ImageIO.write(image, "png", f);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	class PickRecShape extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			startX = e.getX();
			startY = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			endX = e.getX();
			endY = e.getY();
			print(label.getGraphics());
			print(graphMain);
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
			try {
				ImageIO.write(subImage, "png", f2);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	class PickFreeShape extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// PolyX = new Int();
			// PolyX[m++] = e.getX();
			if (NumOfClick < NUM_OF_VERT) {
				NumOfClick += e.getClickCount();
				System.out.println(NumOfClick);
				polyX.add(e.getX());
				polyY.add(e.getY());
				print(label.getGraphics());
				print(graphMain);
				// startY = e.getY();
			} else {
				print(label.getGraphics());
				print(graphMain);
				try {
					ImageIO.write(image, "png", f);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		public void print(Graphics graph) {
			// graph.drawRect(startX, startY, Math.abs(endX - startX), Math.abs(endY -
			// startY));
			graph.setColor(Color.BLUE);
			int[] polyXarr = new int[polyX.size()];
			int[] polyYarr = new int[polyY.size()];
			// g.drawRect(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
			for (int i = 0; i < polyX.size(); i++) {
				polyXarr[i] = polyX.get(i);
			}
			for (int i = 0; i < polyY.size(); i++) {
				polyYarr[i] = polyY.get(i);
			}
			Polygon poly = new Polygon(polyXarr, polyYarr, polyX.size());//
			graph.drawPolygon(poly);
			/*
			 * try { ImageIO.write(subImage, "png", f2);
			 * 
			 * } catch (Exception ex) { ex.printStackTrace(); }
			 */
		}

	}

	class Zoom extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			/*ImageIcon imageIcon = new ImageIcon("MyFile"); // load the image to a imageIcon
			Image image1 = imageIcon.getImage(); // transform it 
			Image newimg = image1.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			//BufferedImage buff = new BufferedImage(newimg.getWidth(null),newimg.getHeight(null),BufferedImage.TYPE_BYTE_ARGB);
			graphMain.drawImage(newimg, 0, 0, null);
			graphMain.dispose();
	        try {
	        	File nf = new File("G:\\new.png");
				ImageIO.write(image, "png", nf);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			final int SCALE = 2;

	        Image img = new ImageIcon("G:\\MyFile.png").getImage();

	        BufferedImage bi = new BufferedImage(SCALE * img.getWidth(null),
	                                             SCALE * img.getHeight(null),
	                                             BufferedImage.TYPE_INT_ARGB);

	        Graphics2D grph = (Graphics2D) bi.getGraphics();
	        grph.scale(SCALE, SCALE);

	        // everything drawn with grph from now on will get scaled.
	        try {
				ImageIO.read(f);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	        grph.drawImage(image, 0, 0, null);
	        grph.dispose();
	        graphMain.drawImage(image, 0, 0, null);
	        graphMain.dispose();
	        label.getGraphics().drawImage(image, 0, 0, null);
	        label.getGraphics().dispose();
	        try {
				ImageIO.write(bi, "png", new File("G:\\NewFile.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/*
	 * class PickUpRect implements Printable {
	 * 
	 * @Override public void print(Graphics g) { g.setColor(Color.BLUE);
	 * g.drawRect(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
	 * try { pickRecShape(startX, startY, Math.abs(endX - startX), Math.abs(endY -
	 * startY)); } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } try { ImageIO.write(subImage, "png", f2);
	 * 
	 * } catch (Exception ex) { ex.printStackTrace(); } } }
	 */
	/*
	 * public void print(Graphics g, int polyX[], int polyY[]) {
	 * g.setColor(Color.BLUE); // g.drawRect(startX, startY, Math.abs(endX -
	 * startX), Math.abs(endY - startY)); Polygon poly = new Polygon(polyX, polyY,
	 * polyX.length);// g.drawPolygon(poly); }
	 */

	class Clicker extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println(e.getClickCount());
			/*
			 * if (e.getClickCount() < 6) { polyX[m++] = e.getX(); polyY[m++] = e.getY(); }
			 * if (e.getClickCount() == 5) { pr.print(graphMain); m=0; }
			 */
			// cl.print(label.getGraphics());
			switch (boo) {
			case 5:
				textField = new JTextPane();
				textField.setText("Add text here!");
				pointsText.add(e.getPoint());
				textField.setBackground(myWhite);
				paintComponentText(textField);
				// image.a;
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			}
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
			pr.print(graphMain);//
			pr.print(label.getGraphics());
		}
	}

///////////// That part of code is about painting	

	public void paintComponentText(JTextPane textField) {
		for (Point p : pointsText) {
			textField.setBounds(p.x, p.y, 100, 50);
		}
	}

	public void clearScreen() {
		myWhite = new Color(255, 255, 243);
		for (int x = 0; x < WORK_AREA_WIDTH; x++) {
			for (int y = 0; y < WORK_AREA_HIGHT; y++) {
				image.setRGB(x, y, myWhite.getRGB());
			}
		}
	}

/////////////
	/// HEEEELP
	public void pickRecShape(int x1, int y1, int x2, int y2) throws IOException {
		ImageIO.read(f);
		subImage = image.getSubimage(x1, y1, x2, y2);
		//Graphics2D g = subImage.createGraphics();
//		g.drawImage(subImage, 0, 0, null);
		f2 = new File("G:\\cutCut.png");
		ImageIO.write(subImage, "png", f2);
	}

	public MainWindow() {
		main = new JFrame();
		try {
			image = new BufferedImage(WORK_AREA_WIDTH, WORK_AREA_HIGHT, BufferedImage.TYPE_INT_RGB);
			f = new File("G:\\MyFile.png");
			clearScreen();
			graphMain = image.createGraphics();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel paneFile = new JPanel();
		main.add(paneFile);
		paneFile.setLayout(new BoxLayout(paneFile, BoxLayout.Y_AXIS));
		label = new JLabel();
		paneFile.add(label);
		label.setIcon(new ImageIcon("G:\\MyFile.png"));
		color = new JColorChooser();
		paneFile.add(color);
		JButton pencilButton = new JButton(new ImageIcon(PENCIL_PATH));
		changeMouse(pencilButton, PENCIL_PATH);
		JButton circleButton = new JButton(new ImageIcon(CIRCLE_PATH));
		changeMouse(circleButton, CIRCLE_PATH);
		JButton rectangleButton = new JButton(new ImageIcon(RECTANGLE_PATH));
		changeMouse(rectangleButton, RECTANGLE_PATH);
		JButton eraserButton = new JButton(new ImageIcon(ERASER_PATH));
		changeMouse(eraserButton, ERASER_PATH);
		JButton textButton = new JButton(new ImageIcon(TEXT_PATH));
		changeMouse(textButton, TEXT_PATH);
		JButton pickRectangleShape = new JButton(new ImageIcon(PICK_RECT_PATH));
		changeMouse(pickRectangleShape, PICK_RECT_PATH);
		JButton pickFreeShape = new JButton(new ImageIcon(PICK_FREE_PATH));
		changeMouse(pickFreeShape, PICK_FREE_PATH);
		JButton zoomButton = new JButton(new ImageIcon(ZOOM_PATH));
		changeMouse(zoomButton, ZOOM_PATH);
		JPanel panelVert = new JPanel();
		panelVert.setLayout(new BoxLayout(panelVert, BoxLayout.Y_AXIS));
		panelVert.add(pencilButton);
		panelVert.add(circleButton);
		panelVert.add(rectangleButton);
		panelVert.add(eraserButton);
		panelVert.add(textButton);
		panelVert.add(pickRectangleShape);
		panelVert.add(pickFreeShape);
		panelVert.add(zoomButton);
		/// !!!!! Refactore menu!
		// java.util
		// ArrayList
		for (int i = 0; i < itemsFile.length; i++) {
			menus[0].add(itemsFile[i]);
		}
		for (int i = 0; i < itemsEdit.length; i++) {
			menus[1].add(itemsEdit[i]);
		}
		for (int i = 0; i < itemsPaint.length; i++) {
			menus[2].add(itemsPaint[i]);
		}
		for (int i = 0; i < additionalPick.length; i++) {
			itemsEdit[1].add(additionalPick[i]);
		}
		for (int i = 0; i < additionalShape.length; i++) {
			itemsPaint[1].add(additionalShape[i]);
		}
		/// !!!!! End of future refactoring
		JMenuBar menuBar = new JMenuBar();
		for (JMenu jm : menus) {
			menuBar.add(jm);
		}
		main.setJMenuBar(menuBar);
		main.add(panelVert, BorderLayout.WEST);

		JScrollPane scroller = new JScrollPane(label, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		;
		scroller.setViewportView(label);
		paneFile.add(scroller);
		scroller.setVisible(true);
	}

	public void changeMouse(JButton button, String file) {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Action");
				pointsText = new LinkedList<Point>();
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursor = toolkit.getImage(file);
				Point point = new Point(0, 0);
				Cursor cursor1 = toolkit.createCustomCursor(cursor, point, "Cursor");
				label.setCursor(cursor1);
				Rectangle rectangle = new Rectangle();
				Pencil pencil = new Pencil();
				Eraser eraser = new Eraser();
				Circle circle = new Circle();
				PickRecShape pick1 = new PickRecShape();
				Zoom zoom = new Zoom();
				switch (file) {
				case RECTANGLE_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					label.addMouseListener(rectangle);
					System.out.println("remove");
					break;
				case CIRCLE_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					label.addMouseListener(circle);
					System.out.println("add");
					break;
				case PENCIL_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					label.addMouseListener(pencil);
					System.out.println("remove");
					break;
				case ERASER_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					label.addMouseListener(eraser);
					System.out.println("remove");
					break;
				case TEXT_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					System.out.println("remove");
					break;
				case PICK_RECT_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					label.addMouseListener(pick1);
					System.out.println("remove");
					break;
				case PICK_FREE_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					label.addMouseListener(pick2);
					System.out.println("remove");
					break;
				case ZOOM_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					label.addMouseListener(zoom);
					System.out.println("remove");
					break;
				}

				/*
				 * if (RECTANGLE_PATH.equals(file)) { //label.addMouseListener(rectangle);
				 * //label.removeMouseListener(rectangle); } if (CIRCLE_PATH.equals(file)) {
				 * Circle circle = new Circle(); label.addMouseListener(circle); } if
				 * (PENCIL_PATH.equals(file)) { //label.addMouseListener(pencil); } if
				 * (ERASER_PATH.equals(file)) { //label.addMouseListener(eraser); } if
				 * (TEXT_PATH.equals(file)) { boo = 5; clicker = new Clicker();
				 * label.addMouseListener(clicker); } if (PICK_RECT_PATH.equals(file)) { pr =
				 * new PickUpRect(); clicker = new Clicker(); label.addMouseListener(clicker); }
				 * if (PICK_FREE_PATH.equals(file)) { pr = new PickUpFree(); clicker = new
				 * Clicker(); label.addMouseListener(clicker); }
				 */
				label.setLayout(null);
			}
		};
		button.addActionListener(actionListener);
	}

	public static void main(String[] args) throws IOException {
		Run.run(new MainWindow(), AREA_WIDTH, AREA_HIGHT);
	}
}