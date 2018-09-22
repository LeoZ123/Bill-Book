package gui.panel;

import gui.listener.ToolBarListener;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import util.CenterPanel;
import util.GUIUtil;

public class MainPanel extends JPanel {
	static {
		GUIUtil.useLNF();
	}

	public static MainPanel instance = new MainPanel();
	public JToolBar tb = new JToolBar();
	public JButton bSpend = new JButton();
	public JButton bRecord = new JButton();
	public JButton bCategory = new JButton();
	public JButton bReport = new JButton();
	public JButton bConfig = new JButton();
	public JButton bBackup = new JButton();
	public JButton bRecover = new JButton();
	public CenterPanel workingPanel;

	private MainPanel() {
		GUIUtil.setImageIcon(this.bSpend, "home.png", "Dashboard");
		GUIUtil.setImageIcon(this.bRecord, "record.png", "Add Record");
		GUIUtil.setImageIcon(this.bCategory, "category2.png", "Catagray");
		GUIUtil.setImageIcon(this.bReport, "report.png", "Monthly View");
		GUIUtil.setImageIcon(this.bConfig, "config.png", "Settings");
		GUIUtil.setImageIcon(this.bBackup, "backup.png", "Backup");
		GUIUtil.setImageIcon(this.bRecover, "restore.png", "Recover");

		this.tb.add(this.bSpend);
		this.tb.add(this.bRecord);
		this.tb.add(this.bCategory);
		this.tb.add(this.bReport);
		this.tb.add(this.bConfig);
		this.tb.add(this.bBackup);
		this.tb.add(this.bRecover);
		this.tb.setFloatable(false);

		this.workingPanel = new CenterPanel(0.8D);

		setLayout(new BorderLayout());
		add(this.tb, BorderLayout.NORTH);
		add(this.workingPanel, BorderLayout.CENTER);

		addListener();
	}

	private void addListener() {
		ToolBarListener listener = new ToolBarListener();

		this.bSpend.addActionListener(listener);
		this.bRecord.addActionListener(listener);
		this.bCategory.addActionListener(listener);
		this.bReport.addActionListener(listener);
		this.bConfig.addActionListener(listener);
		this.bBackup.addActionListener(listener);
		this.bRecover.addActionListener(listener);
	}
}