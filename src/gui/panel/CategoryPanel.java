package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import entity.Category;
import gui.listener.CategoryListsner;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

public class CategoryPanel extends WorkingPanel {

	static {
		GUIUtil.useLNF();
	}
	public static CategoryPanel instance = new CategoryPanel();

	public JButton bAdd = new JButton("Add");
	public JButton bEdit = new JButton("Edit");
	public JButton bDelete = new JButton("Delete");

	String[] columnNames = new String[] { "Catagray Name", "Spending Times" };

	public CategoryTableModel ctm = new CategoryTableModel();
	public JTable jt = new JTable(ctm);

	public CategoryPanel() {
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jt.getColumn("Catagray Name").setCellRenderer(render);
		jt.getColumn("Spending Times").setCellRenderer(render);

		GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete);

		JScrollPane jsp = new JScrollPane(jt);

		JPanel pSubmit = new JPanel();
		pSubmit.add(bAdd);
		pSubmit.add(bEdit);
		pSubmit.add(bDelete);

		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.add(pSubmit, BorderLayout.SOUTH);

		bEdit.setEnabled(false);
		bDelete.setEnabled(false);

		addListener();

		jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				bEdit.setEnabled(true);
				bDelete.setEnabled(true);
			}
		});
	}

	public Category getSelectedCategory() {
		int index = jt.getSelectedRow();
		return ctm.cs.get(index);
	}

	public void updateData() {
		ctm.cs = new CategoryService().list(); 
		jt.updateUI();

		if (ctm.cs.size() == 0) {
			bEdit.setEnabled(false);
			bDelete.setEnabled(false);
		} else {
			bEdit.setEnabled(true);
			bDelete.setEnabled(true);
		}

	}

	public void addListener() {
		CategoryListsner cl = new CategoryListsner();
		bAdd.addActionListener(cl);
		bEdit.addActionListener(cl);
		bDelete.addActionListener(cl);
	}
}
