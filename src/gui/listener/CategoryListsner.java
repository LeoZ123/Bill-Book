package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

public class CategoryListsner implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		CategoryPanel p = CategoryPanel.instance;

		JButton b = (JButton) e.getSource();

		if (b == p.bAdd) {
			String name = JOptionPane.showInputDialog(null);
			if (name == null) { 
				return;
			}
			if (name.length() == 0) { 
				JOptionPane.showMessageDialog(p, "Catagraty name cannot empty");
				return;
			}
			new CategoryService().add(name);
		}

		if (b == p.bEdit) {
			Category c = p.getSelectedCategory();
			int id = c.getId();
			String name = JOptionPane.showInputDialog("edit catagray name", c.getName());
			if (name == null) { 
				return;
			}
			if (name.length() == 0) { 
				JOptionPane.showMessageDialog(p, "Catagraty name cannot empty ");
				return;
			}
			new CategoryService().update(id, name);
		}

		if (b == p.bDelete) {
			Category c = p.getSelectedCategory();
			if (c.getRecordNumber() > 0) {
				JOptionPane.showMessageDialog(p, "Record exist under this catagray");
				return;
			}
			if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "Continue to delete?")) {
				return;
			}

			int id = c.getId();
			new CategoryService().delete(id);
		}

		p.updateData();
	}

}
