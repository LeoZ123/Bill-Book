package gui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.*;

import org.jdesktop.swingx.JXDatePicker;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

public class RecordPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}

	public static RecordPanel instance = new RecordPanel();

	JLabel lSpend = new JLabel("Total($)");
	JLabel lCategory = new JLabel("Category");
	JLabel lComment = new JLabel("Comment");
	JLabel lDate = new JLabel("Date");

	public JTextField tfSpend = new JTextField("0");

	public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
	public JComboBox<Category> cbCategory = new JComboBox<Category>(cbModel);

	public JTextField tfComment = new JTextField();

	public JXDatePicker datepick = new JXDatePicker(new Date());

	public JButton bSubmit = new JButton("Add Record");

	public RecordPanel() {
		GUIUtil.setColor(ColorUtil.grayColor, lSpend, lCategory, lComment, lDate);
		GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

		lSpend.setFont(new Font("Microsoft Yahei", Font.ROMAN_BASELINE, 16));
		lCategory.setFont(new Font("Microsoft Yahei", Font.ROMAN_BASELINE, 16));
		lComment.setFont(new Font("Microsoft Yahei", Font.ROMAN_BASELINE, 16));
		lDate.setFont(new Font("Microsoft Yahei", Font.ROMAN_BASELINE, 16));

		int gap = 40;
		JPanel pInput = new JPanel(new GridLayout(4, 2, gap, gap));
		JPanel pSubmit = new JPanel(new FlowLayout());

		pInput.add(lSpend);
		pInput.add(tfSpend);

		pInput.add(lCategory);
		pInput.add(cbCategory);

		pInput.add(lComment);
		pInput.add(tfComment);

		pInput.add(lDate);
		pInput.add(datepick);

		pSubmit.add(bSubmit);

		this.setLayout(new BorderLayout());
		this.add(pInput, BorderLayout.NORTH);
		this.add(pSubmit, BorderLayout.SOUTH);
		
		addListener();

	}
	
	public Category getSelectedCategory(){
		return (Category)cbCategory.getSelectedItem();		
	}

	
	@Override
	public void updateData() {
		cbModel.cs = new CategoryService().list();
		cbCategory.updateUI();  
		resetInput();  
		tfSpend.grabFocus();
		
	}
	
	private void resetInput(){
        tfSpend.setText("0");
        tfComment.setText("");
        if(cbModel.cs.size()!=0){
        	 cbCategory.setSelectedIndex(0);
        }         
        datepick.setDate(new Date());
	}

	@Override
	public void addListener() {
		RecordListener rl = new RecordListener();
		bSubmit.addActionListener(rl);
		
	}

}
