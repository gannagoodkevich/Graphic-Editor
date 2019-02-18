package editor.utiles;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SineWave extends JFrame {
	private SineDraw sines = new SineDraw();
	private JSlider adjustCycles = new JSlider(1, 30, 5);

	public SineWave() {
		add(sines);
		adjustCycles.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				sines.setCycles(((JSlider) e.getSource()).getValue());
			}
		});
		add(BorderLayout.SOUTH, adjustCycles);
	}

	public static void main(String[] args) {
		Run.run(new SineWave(), 700, 400);
	}
}
