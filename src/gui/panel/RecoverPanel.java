package gui.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.listener.RecoverListener;
import util.ColorUtil;
import util.GUIUtil;

public class RecoverPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}
	public static RecoverPanel instance = new RecoverPanel();

	JButton bRecover = new JButton("Recover");

	public RecoverPanel() {

		GUIUtil.setColor(ColorUtil.blueColor, bRecover);
		this.add(bRecover);
		
		addListener();

	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener() {
		RecoverListener rl = new RecoverListener();
		bRecover.addActionListener(rl);
		
	}

}