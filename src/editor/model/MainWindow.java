package editor.model;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class MainWindow {

	private static final int WORK_AREA_WIDTH = 1800;
	private static final int WORK_AREA_HIGHT = 900;
	private static final int AREA_WIDTH = 1920;
	private static final int AREA_HIGHT = 1080;
	private static final String PENCIL_PATH = "src/pencil.png";
	private static final String CIRCLE_PATH = "src/circle.png";
	private static final String RECTANGLE_PATH = "src/square.png";
	private static final String ERASER_PATH = "src/eraser.png";
	private static final String TEXT_PATH = "src/text.png";
	private static final String PICK_RECT_PATH = "src/pick1.png";
	private static final String PICK_FREE_PATH = "src/pick2.png";
	private static final String ZOOM_PATH = "src/find.png";

	private JFrame main;
	private BufferedImage image;
	private JColorChooser color;
	private File f;
	private int pencilSize;
	private Graphics2D graphMain;
	private JLabel label;
	private Color myWhite;

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

	public Graphics getGraphicsLabel() {
		Graphics graphics = label.getGraphics();
		return graphics;
	}

	public Graphics getgraphMain() {
		return graphMain;
	}

	public JFrame getFrame() {
		return main;
	}
	
	public void drawImage(BufferedImage image, int x, int y) {
		getGraphicsLabel().drawImage(image, x, y, null);
		getgraphMain().drawImage(image, x, y, null);
	}

	public void addMouseMotionListener(MouseMotionListener listener) {
		label.addMouseMotionListener(listener);
	}
	
	public void addMouseListener(MouseListener listener) {
		label.addMouseListener(listener);
	}
	
	public void removeMouseMotionListener(MouseMotionListener listener) {
		label.removeMouseMotionListener(listener);
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public int getPencilSize() {
		return pencilSize;
	}
	
	public Color getColor() {
		return color.getColor();
	
	}
	public void updateImage(){
		try {
			ImageIO.write(image, "png", f);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Color getWhite() {
		return myWhite;
	}
	
	public void readImage() throws IOException {
		ImageIO.read(f);
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void removeMouseListeners() {
		for (MouseListener listener : label.getMouseListeners()) {
			label.removeMouseListener(listener);
		}
	}
	
	public void removeMouseMotionListeners() {
		for (MouseMotionListener listener : label.getMouseMotionListeners()) {
			label.removeMouseMotionListener(listener);
		}
	}
	
	public static void run(final MainWindow frame, final int wigth, final int hight) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				frame.main.setTitle(frame.getClass().getSimpleName()); 
				frame.main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.main.setSize(wigth, hight);
				frame.main.setVisible(true);
			}
		});
	}
	
	public MainWindow() {
		main = new JFrame();
		ArrayList<JMenu> menus = new ArrayList<JMenu>();
		menus.add(new JMenu("Highlight"));
		menus.add(new JMenu("Edit"));
		menus.add(new JMenu("Paint"));
		ArrayList<JMenuItem> itemsHighlight = new ArrayList<JMenuItem>();
		itemsHighlight.add(new JMenuItem("Rectangle shape"));
		itemsHighlight.add(new JMenuItem("Free shape"));
		ArrayList<JMenuItem> itemsEdit = new ArrayList<JMenuItem>();
		itemsEdit.add(new JMenuItem("Resize"));
		itemsEdit.add(new JMenuItem("Text"));
		ArrayList<JMenuItem> itemsPaint = new ArrayList<JMenuItem>();
		itemsPaint.add(new JMenuItem("Pencil"));
		itemsPaint.add(new JMenuItem("Rectangle"));
		itemsPaint.add(new JMenuItem("Circle"));
		itemsPaint.add(new JMenuItem("Eraser"));
		for (JMenuItem item : itemsHighlight) {
			menus.get(0).add(item);
		}
		for (JMenuItem item : itemsEdit) {
			menus.get(1).add(item);
		}
		for (JMenuItem item : itemsPaint) {
			menus.get(2).add(item);
		}
		JMenuBar menuBar = new JMenuBar();
		for (JMenu jm : menus) {
			menuBar.add(jm);
		}
		main.setJMenuBar(menuBar);
		
		
		JFileChooser fileopen = new JFileChooser();             
        int ret = fileopen.showDialog(null, "Choose file");                
        if (ret == JFileChooser.APPROVE_OPTION) {
            f = fileopen.getSelectedFile();
            //label.setText(f.getName());
        }
        
        
		try {
			image = ImageIO.read(f);
			graphMain = image.createGraphics();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel paneFile = new JPanel();
		main.add(paneFile);
		paneFile.setLayout(new BoxLayout(paneFile, BoxLayout.Y_AXIS));
		label = new JLabel();
		paneFile.add(label);
		label.setIcon(new ImageIcon(f.getAbsolutePath()));
		color = new JColorChooser();
		paneFile.add(color);
		JButton pencilButton = new JButton(new ImageIcon(PENCIL_PATH));
		changeMouse(pencilButton, itemsPaint.get(0), PENCIL_PATH, this);
		JButton circleButton = new JButton(new ImageIcon(CIRCLE_PATH));
		changeMouse(circleButton, itemsPaint.get(2), CIRCLE_PATH, this);
		JButton rectangleButton = new JButton(new ImageIcon(RECTANGLE_PATH));
		changeMouse(rectangleButton, itemsPaint.get(1), RECTANGLE_PATH, this);
		JButton eraserButton = new JButton(new ImageIcon(ERASER_PATH));
		changeMouse(eraserButton, itemsPaint.get(3), ERASER_PATH, this);
		JButton textButton = new JButton(new ImageIcon(TEXT_PATH));
		changeMouse(textButton, itemsEdit.get(1), TEXT_PATH, this);
		JButton pickRectangleShape = new JButton(new ImageIcon(PICK_RECT_PATH));
		changeMouse(pickRectangleShape, itemsHighlight.get(0), PICK_RECT_PATH, this);
		JButton pickFreeShape = new JButton(new ImageIcon(PICK_FREE_PATH));
		changeMouse(pickFreeShape, itemsHighlight.get(1), PICK_FREE_PATH, this);
		JButton zoomButton = new JButton(new ImageIcon(ZOOM_PATH));
		changeMouse(zoomButton, itemsEdit.get(0), ZOOM_PATH, this);
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
		main.add(panelVert, BorderLayout.WEST);
		JScrollPane scroller = new JScrollPane(label, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroller.setViewportView(label);
		paneFile.add(scroller);
		scroller.setVisible(true);
	}

	public void changeMouse(JButton button, JMenuItem menu, String file, MainWindow main) {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursor = toolkit.getImage(file);
				Cursor cursor1 = toolkit.createCustomCursor(cursor, new Point(0, 0), "Cursor");
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
					removeMouseListeners();
					removeMouseMotionListeners();
					label.addMouseListener(rectangle);
					break;
				case CIRCLE_PATH:
					removeMouseListeners();
					removeMouseMotionListeners();
					label.addMouseListener(circle);
					break;
				case PENCIL_PATH:
					removeMouseListeners();
					removeMouseMotionListeners();
					Object[] options = { "1X", "2X" };
					int a=	JOptionPane.showOptionDialog(null,  "Choose size",  "Choose size",
					             JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					             null, options, options[0]);
					// System.out.println(a);
					if(a ==0) {//1x
						pencilSize = 1;
					}
					else {
						pencilSize = 4;
					}
					label.addMouseListener(pencil);
					break;
				case ERASER_PATH:
					removeMouseListeners();
					removeMouseMotionListeners();
					label.addMouseListener(eraser);
					break;
				case TEXT_PATH:
					removeMouseListeners();
					removeMouseMotionListeners();
					label.addMouseListener(text);
					break;
				case PICK_RECT_PATH:
					removeMouseListeners();
					removeMouseMotionListeners();
					label.addMouseListener(pick1);
					break;
				case PICK_FREE_PATH:
					removeMouseListeners();
					removeMouseMotionListeners();
					label.addMouseListener(pick2);
					break;
				case ZOOM_PATH:
					removeMouseListeners();
					removeMouseMotionListeners();
					label.addMouseListener(zoom);
					break;
				}
				label.setLayout(null);
			}
		};
		button.addActionListener(actionListener);
		menu.addActionListener(actionListener);
	}

	public static void main(String[] args) throws IOException {
		run(new MainWindow(), AREA_WIDTH, AREA_HIGHT);
	}

	public void removeMouseListener(MouseListener listen1) {
			label.removeMouseListener(listen1);		
	}
}