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

public class MainWindow /*extends JFrame*/ {

	public JFrame main;
	public static JPanel paneFile;
	public BufferedImage image;
	
	public List<Point> pointsRectangle;
	public List<Point> pointsCircle;
	public List<Point> pointsPencil;
	public List<Point> pointsEraser;
	int boo;
	
	private JMenu[] menus = { new JMenu("File"), new JMenu("Edit"), new JMenu("Paint") };
	private JMenuItem[] itemsFile = { new JMenuItem("Save"), new JMenuItem("Open") };
	private JMenuItem[] itemsEdit = { new JMenuItem("Resize"), new JMenuItem("Pick"), new JMenuItem("Text") };
	private JMenuItem[] itemsPaint = { new JMenuItem("Pencil"), new JMenuItem("Shape"), new JMenuItem("Eraser") };
	private JMenuItem[] additionalPick = { new JMenuItem("Rectangle"), new JMenuItem("Free") };
	private JMenuItem[] additionalShape = { new JMenuItem("Rectangle"), new JMenuItem("Circle") };
	
	Clicker clicker;
	Color myWhite = new Color(255, 255, 243);

///////////// That part of code is about mouse clicking
	
	class Clicker extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			switch (boo) {
			case 1:
				pointsRectangle.add(e.getPoint());
				paintComponentRec(main.getGraphics());
				break;
			case 2:
				pointsCircle.add(e.getPoint());
				paintComponentCir(main.getGraphics());
				break;
			case 3:
				pointsPencil.add(e.getPoint());
				paintComponentPen(main.getGraphics());
				break;
			case 4:
				pointsEraser.add(e.getPoint());
				erase(main.getGraphics());
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			}
		}
	}

///////////// That part of code is about painting	
	
	public void paintComponentCir(Graphics g) {
		g.setColor(Color.BLACK);
		for (Point p : pointsCircle) {
			g.drawOval(p.x, p.y, 300, 300);
		} 
	}

	public void paintComponentRec(Graphics g) {
		g.setColor(Color.BLACK);
		for (Point p : pointsRectangle) {
			g.drawRect(p.x, p.y, 400, 200);
		}
	}

	public void paintComponentPen(Graphics g) {
		g.setColor(Color.BLACK);
		for (Point p : pointsPencil) {
			g.drawRect(p.x, p.y, 1, 1);
		}
	}

	public void erase(Graphics g) {
		g.setColor(myWhite);
		for (Point p : pointsEraser) {
			g.fillRect(p.x, p.y, 300, 300);
		}
	}
	
/////////////

	public MainWindow() throws IOException {
		LinkedList<String> paths = new LinkedList<String>();
		paths.add("G:\\pencil.png");
		paths.add("G:\\circle.png");
		paths.add("G:\\square.png");
		paths.add("G:\\eraser.png");
		paths.add("G:\\text.png");
		paths.add("G:\\pick1.png");
		paths.add("G:\\pick2.png");
		paths.add("G:\\find.png");
		try {
			BufferedImage img = new BufferedImage(1800, 1000, BufferedImage.TYPE_INT_RGB);
			File f = new File("G:\\MyFile.png");
			myWhite = new Color(255, 255, 243);
			image = ImageIO.read(f);
			for (int x = 0; x < 1800; x++) {
				for (int y = 0; y < 1000; y++) {
					image.setRGB(x, y, myWhite.getRGB());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		JButton pencilButton = new JButton(new ImageIcon("G:\\pencil.png"));
		changeMouse(pencilButton, new String("G:\\pencil.png"));
		JButton circleButton = new JButton(new ImageIcon("G:\\circle.png"));
		changeMouse(circleButton, new String("G:\\circle.png"));
		JButton rectangleButton = new JButton(new ImageIcon("G:\\square.png"));
		changeMouse(rectangleButton, new String("G:\\square.png"));
		JButton eraserButton = new JButton(new ImageIcon("G:\\eraser.png"));
		changeMouse(eraserButton, new String("G:\\eraser.png"));
		JButton textButton = new JButton(new ImageIcon("G:\\text.png"));
		changeMouse(textButton, new String("G:\\text.png"));
		JButton pickRectangleShape = new JButton(new ImageIcon("G:\\pick1.png"));
		changeMouse(pickRectangleShape, new String("G:\\pick1.png"));
		JButton pickFreeShape = new JButton(new ImageIcon("G:\\pick2.png"));
		changeMouse(pickFreeShape, new String("G:\\pick2.png"));
		JButton zoomButton = new JButton(new ImageIcon("G:\\find.png"));
		changeMouse(zoomButton, new String("G:\\find.png"));
		main = new JFrame();
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
		paneFile = new JPanel() {
			@Override
			protected void paintComponent(Graphics graph) {
				super.paintComponent(graph);
				graph.drawImage(image, 10, 0, null);
			}
		};
		main.add(paneFile);

		// Clicker clicker = new Clicker();
		// paneFile.addMouseListener(clicker);
		// paneFile.removeMouseListener(clicker);

	}

	public void changeMouse(JButton button, String file) {
		ActionListener actionListener = new TestActionListener() {// анонимный внутренний класс
			public void actionPerformed(ActionEvent e) {
				pointsRectangle = new LinkedList<Point>();
				pointsCircle = new LinkedList<Point>();
				pointsPencil = new LinkedList<Point>();
				pointsEraser = new LinkedList<Point>();
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursor = toolkit.getImage(file);
				System.out.println(file);
				Point point = new Point(0, 0);
				Cursor cursor1 = toolkit.createCustomCursor(cursor, point, "Cursor");
				paneFile.setCursor(cursor1);
				if (file.equals(new String("G:\\square.png"))) {
					boo = 1;
				}
				if (file.equals(new String("G:\\circle.png"))) {
					boo = 2;
				}
				if (file.equals(new String("G:\\pencil.png"))) {
					boo = 3;
				}
				if (file.equals(new String("G:\\eraser.png"))) {
					boo = 4;
				}
				clicker = new Clicker();
				paneFile.addMouseListener(clicker);
			}
		};
		button.addActionListener(actionListener);
	}

	public static void main(String[] args) throws IOException {
		Run.run(new MainWindow(), 1920, 1080);
	}
}
