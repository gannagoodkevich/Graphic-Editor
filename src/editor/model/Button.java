package editor.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Cursor;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import editor.model.Button;
import editor.utiles.*;

public class Button extends JFrame {

	public static JPanel paneFile;
	public static ImageFile image;

	private JMenu[] menus = { new JMenu("File"), new JMenu("Edit"), new JMenu("Paint") };
	private JMenuItem[] itemsFile = { new JMenuItem("Save"), new JMenuItem("Open") };
	private JMenuItem[] itemsEdit = { new JMenuItem("Resize"), new JMenuItem("Pick"), new JMenuItem("Text") };
	private JMenuItem[] itemsPaint = { new JMenuItem("Pencil"), new JMenuItem("Shape"), new JMenuItem("Eraser") };
	private JMenuItem[] additionalPick = { new JMenuItem("Rectangle"), new JMenuItem("Free") };
	private JMenuItem[] additionalShape = { new JMenuItem("Rectangle"), new JMenuItem("Circle") };

	public Button() throws IOException {
		PencilButton pencilButton = new PencilButton();
		CircleButton circleButton = new CircleButton();
		RectangleButton rectangleButton = new RectangleButton();
		EraserButton eraserButton = new EraserButton();
		TextButton textButton = new TextButton();
		PickRectangleShape pickRectangleShape = new PickRectangleShape();
		PickFreeShape pickFreeShape = new PickFreeShape();
		ZoomButton zoomButton = new ZoomButton();
		JPanel panelVert = new JPanel();
		panelVert.setLayout(new BoxLayout(panelVert, BoxLayout.Y_AXIS));
		panelVert.add(pencilButton.pencil);
		panelVert.add(circleButton.circle);
		panelVert.add(rectangleButton.rect);
		panelVert.add(eraserButton.eraser);
		panelVert.add(textButton.text);
		panelVert.add(pickRectangleShape.rectangle);
		panelVert.add(pickFreeShape.free);
		panelVert.add(zoomButton.zoom);
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
		setJMenuBar(menuBar);
		add(panelVert, BorderLayout.WEST);
		image = new ImageFile();
		paneFile = new JPanel() {
			@Override
			protected void paintComponent(Graphics graph) {
				image.drowRectangle(200, 200, 500, 500);
				rectangleButton.drowRectangle(100, 700, 300, 900);
				super.paintComponent(graph);
				graph.drawImage(image.image, 10, 0, null);
				
			}
		};
		add(paneFile);
	}

	public static void main(String[] args) throws IOException {
		Run.run(new Button(), 1920, 1080);
	}
}
