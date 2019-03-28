package editor.model;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class MainWindow {

	private static final int WORK_AREA_WIDTH = 1800;
	private static final int WORK_AREA_HIGHT = 900;
	private static final int AREA_WIDTH = 1920;
	private static final int AREA_HIGHT = 1080;
	private static final String PENCIL_PATH = "G:\\pencil.png";
	private static final String CIRCLE_PATH = "G:\\circle.png";
	private static final String RECTANGLE_PATH = "G:\\square.png";
	private static final String ERASER_PATH = "G:\\eraser.png";
	private static final String TEXT_PATH = "G:\\text.png";
	private static final String PICK_RECT_PATH = "G:\\pick1.png";
	private static final String PICK_FREE_PATH = "G:\\pick2.png";
	private static final String ZOOM_PATH = "G:\\find.png";
	//private static final String MAIN_FILE = "G:\\MyFile.png";
	public JFrame main;
	BufferedImage image;
	JTextPane textField;
	JColorChooser color;
	File f;
	File f2;
	Graphics2D graphMain;
	JLabel label;

	Color myWhite = new Color(255, 255, 243);
	int curRed = 120;
	int curGreen = 50;
	int curBlue = 120;

	public void clearScreen() {
		myWhite = new Color(255, 255, 243);
		for (int x = 0; x < WORK_AREA_WIDTH; x++) {
			for (int y = 0; y < WORK_AREA_HIGHT; y++) {
				image.setRGB(x, y, myWhite.getRGB());
			}
		}
		try {
			ImageIO.write(image, "png", f);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public MainWindow() {
		main = new JFrame();
		JMenu[] menus = { new JMenu("Highlight"), new JMenu("Edit"), new JMenu("Paint") };
		JMenuItem[] itemsHighlight = { new JMenuItem("Rectangle shape"), new JMenuItem("Free Shape") };
		JMenuItem[] itemsEdit = { new JMenuItem("Resize"), new JMenuItem("Text") };
		JMenuItem[] itemsPaint = { new JMenuItem("Pencil"), new JMenuItem("Rectangle"), new JMenuItem("Circle"), new JMenuItem("Eraser") };
		for (int i = 0; i < itemsHighlight.length; i++) {
			menus[0].add(itemsHighlight[i]);
		}
		for (int i = 0; i < itemsEdit.length; i++) {
			menus[1].add(itemsEdit[i]);
		}
		for (int i = 0; i < itemsPaint.length; i++) {
			menus[2].add(itemsPaint[i]);
		}
		
		/// !!!!! End of future refactoring
		JMenuBar menuBar = new JMenuBar();
		for (JMenu jm : menus) {
			menuBar.add(jm);
		}
		main.setJMenuBar(menuBar);
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
		changeMouse(pencilButton, itemsPaint[0], PENCIL_PATH, this);
		JButton circleButton = new JButton(new ImageIcon(CIRCLE_PATH));
		changeMouse(circleButton, itemsPaint[2], CIRCLE_PATH, this);
		JButton rectangleButton = new JButton(new ImageIcon(RECTANGLE_PATH));
		changeMouse(rectangleButton, itemsPaint[1], RECTANGLE_PATH, this);
		JButton eraserButton = new JButton(new ImageIcon(ERASER_PATH));
		changeMouse(eraserButton, itemsPaint[3], ERASER_PATH, this);
		JButton textButton = new JButton(new ImageIcon(TEXT_PATH));
		changeMouse(textButton, itemsEdit[1], TEXT_PATH, this);
		JButton pickRectangleShape = new JButton(new ImageIcon(PICK_RECT_PATH));
		changeMouse(pickRectangleShape, itemsHighlight[0], PICK_RECT_PATH, this);
		JButton pickFreeShape = new JButton(new ImageIcon(PICK_FREE_PATH));
		changeMouse(pickFreeShape, itemsHighlight[1], PICK_FREE_PATH, this);
		JButton zoomButton = new JButton(new ImageIcon(ZOOM_PATH));
		changeMouse(zoomButton, itemsEdit[0], ZOOM_PATH, this);
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
		main.add(panelVert, BorderLayout.WEST);

		itemsHighlight[0].addActionListener(new ActionListener()
		  {
		   public void actionPerformed(ActionEvent e)
		   {
			   		   }
		  });
		itemsPaint[1].addActionListener(new java.awt.event.ActionListener()
		  {
			   public void actionPerformed(ActionEvent e)
			   {
			    System.out.println("Paint 1");
			   }
		  });
		
		
		
		JScrollPane scroller = new JScrollPane(label, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroller.setViewportView(label);
		paneFile.add(scroller);
		scroller.setVisible(true);
	}

	public void changeMouse(JButton button, JMenuItem menu, String file, MainWindow main) {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Action");
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursor = toolkit.getImage(file);
				Point point = new Point(0, 0);
				Cursor cursor1 = toolkit.createCustomCursor(cursor, point, "Cursor");
				label.setCursor(cursor1);
				Rectangle rectangle = new Rectangle(main);
				Pencil pencil = new Pencil(main);
				Eraser eraser = new Eraser(main);
				Circle circle = new Circle(main);
				PickRecShape pick1 = new PickRecShape(main);
				PickFreeShape pick2 = new PickFreeShape(main);
				Text text = new Text(main);
				Zoom zoom = new Zoom(main);
				switch (file) {
				case RECTANGLE_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					for (int i = 0; i < label.getMouseMotionListeners().length; i++) {
						label.removeMouseMotionListener(label.getMouseMotionListeners()[i]);
					}
					label.addMouseListener(rectangle);
					System.out.println("remove");
					break;
				case CIRCLE_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					for (int i = 0; i < label.getMouseMotionListeners().length; i++) {
						label.removeMouseMotionListener(label.getMouseMotionListeners()[i]);
					}
					label.addMouseListener(circle);
					System.out.println("add");
					break;
				case PENCIL_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					for (int i = 0; i < label.getMouseMotionListeners().length; i++) {
						label.removeMouseMotionListener(label.getMouseMotionListeners()[i]);
					}
					label.addMouseListener(pencil);
					System.out.println("remove");
					break;
				case ERASER_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					for (int i = 0; i < label.getMouseMotionListeners().length; i++) {
						label.removeMouseMotionListener(label.getMouseMotionListeners()[i]);
					}
					label.addMouseListener(eraser);
					System.out.println("remove");
					break;
				case TEXT_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					for (int i = 0; i < label.getMouseMotionListeners().length; i++) {
						label.removeMouseMotionListener(label.getMouseMotionListeners()[i]);
					}
					label.addMouseListener(text);
					System.out.println("remove");
					break;
				case PICK_RECT_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					for (int i = 0; i < label.getMouseMotionListeners().length; i++) {
						label.removeMouseMotionListener(label.getMouseMotionListeners()[i]);
					}
					label.addMouseListener(pick1);
					System.out.println("remove");
					break;
				case PICK_FREE_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					for (int i = 0; i < label.getMouseMotionListeners().length; i++) {
						label.removeMouseMotionListener(label.getMouseMotionListeners()[i]);
					}
					label.addMouseListener(pick2);
					System.out.println("remove");
					break;
				case ZOOM_PATH:
					for (int i = 0; i < label.getMouseListeners().length; i++) {
						label.removeMouseListener(label.getMouseListeners()[i]);
					}
					for (int i = 0; i < label.getMouseMotionListeners().length; i++) {
						label.removeMouseMotionListener(label.getMouseMotionListeners()[i]);
					}
					label.addMouseListener(zoom);
					System.out.println("remove");
					break;
				}
				label.setLayout(null);
			}
		};
		button.addActionListener(actionListener);
		menu.addActionListener(actionListener);
	}

	public static void main(String[] args) throws IOException {
		Run.run(new MainWindow(), AREA_WIDTH, AREA_HIGHT);
	}
}