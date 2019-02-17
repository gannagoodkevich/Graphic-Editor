package editor.utiles;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Run {
	public static void run(final JFrame frame, final int wigth, final int hight) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				frame.setTitle(frame.getClass().getSimpleName()); 
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(wigth, hight);
				frame.setVisible(true);
			}
		});
	}
}
