package editor.utiles;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SineWave extends JFrame {
	private JButton sines = new JButton("Button");
	//private JSlider adjustCycles = new JSlider(1, 30, 5);

	public SineWave() {
		add(sines, BorderLayout.SOUTH);
		sines.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				SineDraw sss = new SineDraw(10);
				sss.paintComponent(getGraphics());
				sss.setCycles();
			}
		});
		//add(BorderLayout.SOUTH, adjustCycles);
	}

	public static void main(String[] args) {
		Run.run(new SineWave(), 700, 400);
	}
}
