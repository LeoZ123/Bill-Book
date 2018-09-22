package util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class GUIUtil {

	private static String imageFolder = getAbsPath() + "/img";

	public static String getAbsPath() {
		File dir = new File(".");
		try {
			String path = dir.getCanonicalPath();
			return path;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public static void setImageIcon(JButton b, String fileName, String tip) {
		File f = new File(imageFolder, fileName);
		ImageIcon i = null;
		if (f.exists()) {
			i = new ImageIcon(f.getAbsolutePath());
		} else {
			URL url = ClassLoader.getSystemResource("img/" + fileName);
			i = new ImageIcon(url);
		}
		b.setIcon(i);
		b.setPreferredSize(new Dimension(61, 81));
		b.setToolTipText(tip); 
		b.setVerticalTextPosition(3); 
		b.setHorizontalTextPosition(0); 
		b.setText(tip); 
	}

	public static void setColor(Color color, JComponent... cs) { 
		JComponent[] arrayOfJComponent;
		int j = (arrayOfJComponent = cs).length;
		for (int i = 0; i < j; i++) {
			JComponent c = arrayOfJComponent[i];
			c.setForeground(color);
		}
	}

	public static void useLNF() {
		try {
			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showPanel(JPanel p, double stretchRate) {
		GUIUtil.useLNF();
		JFrame f = new JFrame();
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		CenterPanel cp = new CenterPanel(stretchRate);
		f.setContentPane(cp);
		f.setDefaultCloseOperation(3);
		f.setVisible(true);
		cp.show(p);
	}

	public static void showPanel(JPanel p) {
		showPanel(p, 0.85d);
	}

	public static boolean checkNumber(JTextField tf, String input) {
		if (!checkEmpty(tf, input)) {
			return false;
		}
		String text = tf.getText().trim();
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, input + "need integer");
			tf.grabFocus();
		}
		return false;
	}

	public static boolean checkEmpty(JTextField tf, String input) {
		String text = tf.getText().trim();
		if (text.length() == 0) {
			JOptionPane.showMessageDialog(null, input + "cannot be empty");
			tf.grabFocus();
			return false;
		}
		return true;
	}

	public static boolean checkZero(JTextField tf, String input) {
		if (!checkNumber(tf, input)) {
			return false;
		}
		String text = tf.getText().trim();
		if (Integer.parseInt(text) == 0) {
			JOptionPane.showMessageDialog(null, input + "cannot be zero");
			tf.grabFocus();
			return false;
		}
		return true;
	}

	public static int getInt(JTextField tf) {
		return Integer.parseInt(tf.getText().trim());
	}

}
