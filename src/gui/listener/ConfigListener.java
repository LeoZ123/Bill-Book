package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

public class ConfigListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ConfigPanel p = ConfigPanel.instance;
		if(!GUIUtil.checkNumber(p.tfBudget, "Budget for this month")){
			return;
		}
		String mysqlPath = p.tfMysqlPath.getText();
		if(mysqlPath.length()!=0){
			File commandFile = new File(mysqlPath,"bin/mysql.exe");
			if(!commandFile.exists()){//如果不是
				JOptionPane.showMessageDialog(p, "MYSQL path is incorrect");
				p.tfMysqlPath.grabFocus();
				return;
			}
		}
		
		ConfigService cs = new ConfigService();
		cs.update(ConfigService.budget, p.tfBudget.getText());
		cs.update(ConfigService.mysqlPath, mysqlPath);
		
		JOptionPane.showMessageDialog(p, "edit config successed");
	}

}
