package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import util.MysqlUtil;

public class BackupListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		BackupPanel p = BackupPanel.instance;
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);

		if(mysqlPath.length()==0){
			JOptionPane.showMessageDialog(p, "Please backup mysql path");
			MainPanel.instance.workingPanel.show(ConfigPanel.instance);
			ConfigPanel.instance.tfMysqlPath.grabFocus();
			return;
			
		}

		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("bill.sql"));
		fc.setFileFilter(new FileFilter(){

			@Override
			public boolean accept(File f) {				
				return f.getName().toLowerCase().endsWith(".sql");
			}
			@Override
			public String getDescription() {				
				return ".sql";
			}
			
		});
		
		int returnval = fc.showSaveDialog(p);
		File file = fc.getSelectedFile();

		if(returnval == JFileChooser.APPROVE_OPTION){
			if(!file.getName().toLowerCase().endsWith(".sql")){
				file = new File(file.getParent(),file.getName()+".sql");
			}
			
			 try {
	                MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
	                JOptionPane.showMessageDialog(p, "Successed: file path:\r\n"+file.getAbsolutePath());
	            } catch (Exception e1) {
	                e1.printStackTrace();
	                JOptionPane.showMessageDialog(p, "Failed\r\n,Failure:\r\n"+e1.getMessage());   
	            }
		}
	}

}
