package editor.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

import editor.utiles.*;
import editor.utiles.ScrollDemo2.DrawingPane;

public class MainWindow {

	private static final int WORK_AREA_WIDTH = 2000;
	private static final int WORK_AREA_HIGHT = 2000;
	private static final int AREA_WIDTH = 500;
	private static final int AREA_HIGHT = 500;
	private static final String PENCIL_PATH = "G:\\pencil.png";
	private static final String CIRCLE_PATH = "G:\\circle.png";
	private static final String RECTANGLE_PATH = "G:\\square.png";
	private static final String ERASER_PATH = "G:\\eraser.png";
	private static final String TEXT_PATH = "G:\\text.png";
	private static final String PICK_RECT_PATH = "G:\\pick1.png";
	private static final String PICK_FREE_PATH = "G:\\pick2.png";
	private static final String ZOOM_PATH = "G:\\find.png";
	public JFrame main;
	public JPanel paneFile;
	public JPanel paneFileImage;
	BufferedImage image;
	JTextPane textField;
	JColorChooser color;
	File f;
	Printable pr;
	Graphics2D graphMain;

	List<Point> pointsText;

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

///////////// That part of code is about mouse clicking

	interface Printable {
		public void print(Graphics g);
	}

	class Pencil implements Printable {
		@Override
		public void print(Graphics g) {
			g.setColor(color.getColor());
			g.drawLine(startX, startY, endX, endY);
			try {
				ImageIO.write(image, "png", f);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	class Circle implements Printable {
		@Override
		public void print(Graphics g) {
			g.setColor(color.getColor());
			g.fillOval(startX, startY, Math.abs((endX - startX) / 2), Math.abs((endX - startX) / 2));
			try {
				ImageIO.write(image, "png", f);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	class Eraser implements Printable {
		@Override
		public void print(Graphics g) {
			g.setColor(myWhite);
			g.fillRect(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
			try {
				ImageIO.write(image, "png", f);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	class Rectangle implements Printable {
		@Override
		public void print(Graphics g) {
			g.setColor(color.getColor());
			g.fillRect(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
			try {
				ImageIO.write(image, "png", f);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	class Clicker extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			switch (boo) {
			case 5:
				textField = new JTextPane();
				textField.setText("Add text here!");
				pointsText.add(e.getPoint());
				textField.setBackground(myWhite);
				paintComponentText(textField);
				paneFile.add(textField);
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
			pr.print(graphMain);
			pr.print(main.getGraphics());
		}
	}

///////////// That part of code is about painting	

	public void paintComponentText(JTextPane textField) {
		for (Point p : pointsText) {
			textField.setBounds(p.x, p.y, 100, 50);
		}
	}

/////////////
	/// HEEEELP
	public void pickRecShape() throws IOException {
		/*BufferedImage img1 = image.getSubimage(200, 100, 500, 300); // fill in the corners of the desired crop location														// // here
		BufferedImage copyOfImage = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = copyOfImage.createGraphics();
		g.drawImage(img1, 0, 0, null);*/
		ImageIO.read(f);
		BufferedImage subImage = image.getSubimage(0, 0, 400, 400);
		ImageIO.write(subImage, "png", new File("G:\\cutCut.png"));
	}


	public MainWindow() throws IOException {
		try {
			image = new BufferedImage(WORK_AREA_WIDTH, WORK_AREA_HIGHT, BufferedImage.TYPE_INT_RGB);
			f = new File("G:\\MyFile.png");
			myWhite = new Color(255, 255, 243);
			for (int x = 0; x < WORK_AREA_WIDTH; x++) {
				for (int y = 0; y < WORK_AREA_HIGHT; y++) {
					image.setRGB(x, y, myWhite.getRGB());
					graphMain = image.createGraphics();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		main = new JFrame();
		color = new JColorChooser();
		color.setBounds(100, 680, 1800, 270);
		main.add(color);
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
		//paneFile = new JPanel();
		//paneFile.setBounds(100, 100, 1720, 730);
		paneFile = new JPanel() {
			@Override
			protected void paintComponent(Graphics graph) {
				super.paintComponent(graph);
				graph.drawImage(image, 10, 0, null);
			}
		};
		//paneFileImage.setBounds(100, 100, 1720, 730);
		main.add(paneFile);
		pickRecShape();
		JScrollPane scroller = new JScrollPane(paneFile, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);;
		//scroller.setPreferredSize(new Dimension(100, 100));
        Dimension  area = new Dimension(0,0);
        paneFile.setPreferredSize(area);
        scroller.setPreferredSize(new Dimension(1500, 1080));
        paneFile.revalidate();
		main.add(scroller);
	}

	public void changeMouse(JButton button, String file) {
		ActionListener actionListener = new TestActionListener() {// àíîíèìíûé âíóòðåííèé êëàññ
			public void actionPerformed(ActionEvent e) {
				pointsText = new LinkedList<Point>();
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursor = toolkit.getImage(file);
				Point point = new Point(0, 0);
				Cursor cursor1 = toolkit.createCustomCursor(cursor, point, "Cursor");
				paneFile.setCursor(cursor1);
				if (RECTANGLE_PATH.equals(file)) {
					pr = new Rectangle();
				}
				if (CIRCLE_PATH.equals(file)) {
					pr = new Circle();
				}
				if (PENCIL_PATH.equals(file)) {
					pr = new Pencil();
				}
				if (ERASER_PATH.equals(file)) {
					pr = new Eraser();
				}
				if (TEXT_PATH.equals(file)) {
					boo = 5;
				}
				clicker = new Clicker();
				paneFile.setLayout(null);
				paneFile.addMouseListener(clicker);
			}
		};
		button.addActionListener(actionListener);
	}

	public static void main(String[] args) throws IOException {
		Run.run(new MainWindow(), AREA_WIDTH, AREA_HIGHT);
	}
}