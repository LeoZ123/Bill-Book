package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import entity.Category;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.RecordService;
import util.CenterPanel;
import util.GUIUtil;

public class RecordListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		RecordPanel rp = RecordPanel.instance;
		CenterPanel cp = MainPanel.instance.workingPanel;
		
		if(rp.cbModel.cs.size()==0){
			JOptionPane.showMessageDialog(rp, "Please add a catagray");
			cp.show(CategoryPanel.instance);
			return;
		}

        if(!GUIUtil.checkZero(rp.tfSpend,"Spend money")){
        	return;
        }

        int spend = Integer.parseInt(rp.tfSpend.getText());
        Category c = rp.getSelectedCategory();
        String comment = rp.tfComment.getText();
        Date date = rp.datepick.getDate();

        new RecordService().add(spend,c,comment,date);
        JOptionPane.showMessageDialog(rp, "Success");

        cp.show(SpendPanel.instance);
	}

}
