package util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.panel.WorkingPanel;

public class CenterPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double rate; 
	private JComponent c; 
	private boolean stretch;

	public CenterPanel(double rate, boolean stretch) {
		this.setLayout(null);
		this.rate = rate;
		this.stretch = stretch;
	}

	public CenterPanel(double rate) {
		this(rate, true);
	}

	public void repaint() {
		if (this.c != null) {
			Dimension containerSize = this.getSize();
			Dimension componentSize = this.c.getPreferredSize();

			if (this.stretch) {

				this.c.setSize((int) (containerSize.width * this.rate), (int) (containerSize.height * this.rate));
			} else {
				this.c.setSize(componentSize);
			}

			this.c.setLocation(containerSize.width / 2 - this.c.getWidth() / 2,
					containerSize.height / 2 - this.c.getHeight() / 2);

		}
		super.repaint();
	}

	public void show(JComponent p) {
		this.c = p;
		Component[] cs = this.getComponents();
		Component[] arrayOfComponent1;
		int j = (arrayOfComponent1 = cs).length;
		for (int i = 0; i < j; i++) { 
			Component c = arrayOfComponent1[i];
			remove(c);
		}
		add(p);

		if ((p instanceof WorkingPanel)) {
			((WorkingPanel) p).updateData();
		}
		updateUI();
	}
}
