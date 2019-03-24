package editor.model;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import editor.model.MainWindow;

public class Run {
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
}