package gui.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;


public class SpendPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}
	public static SpendPanel instance = new SpendPanel();

	public JLabel lMonthSpend = new JLabel("Monthly View");
	public JLabel lTodaySpend = new JLabel("Today Spend");
	public JLabel lAvgSpendPerDay = new JLabel("Avg Spend Per Day");
	public JLabel lMonthLeft = new JLabel("Monthly Available");
	public JLabel lDayAvgAvailable = new JLabel("Day Avg Available");
	public JLabel lMonthLeftDay = new JLabel("Days to the end of this month");

	public JLabel vMonthSpend = new JLabel();
	public JLabel vTodaySpend = new JLabel();
	public JLabel vAvgSpendPerDay = new JLabel();
	public JLabel vMonthAvailable = new JLabel();
	public JLabel vDayAvgAvailable = new JLabel();
	public JLabel vMonthLeftDay = new JLabel();

	CircleProgressBar bar;

	public SpendPanel() {
		this.setLayout(new BorderLayout());
		bar = new CircleProgressBar();
		bar.setBackgroundColor(ColorUtil.blueColor);

		GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
				lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
		GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);

		vMonthSpend.setFont(new Font("TimesRoman", Font.BOLD, 23));
		vTodaySpend.setFont(new Font("TimesRoman", Font.BOLD, 23));

		this.add(center(), BorderLayout.CENTER);
		this.add(south(), BorderLayout.SOUTH);

	}

	private JPanel center() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(west(), BorderLayout.WEST);
		p.add(center2(), BorderLayout.CENTER);

		return p;
	}

	private Component center2() {
		return bar;
	}

	private Component west() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 1));
		p.add(lMonthSpend);
		p.add(vMonthSpend);
		p.add(lTodaySpend);
		p.add(vTodaySpend);
		return p;
	}

	private JPanel south() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 4));

		p.add(lAvgSpendPerDay);
		p.add(lMonthLeft);
		p.add(lDayAvgAvailable);
		p.add(lMonthLeftDay);
		p.add(vAvgSpendPerDay);
		p.add(vMonthAvailable);
		p.add(vDayAvgAvailable);
		p.add(vMonthLeftDay);

		return p;
	}


	@Override
	public void updateData() {
		SpendPage sp = new SpendService().getSpendPage();
		vMonthSpend.setText(sp.monthSpend);
		vTodaySpend.setText(sp.todaySpend);
	    vAvgSpendPerDay.setText(sp.avgSpendPerDay);
	    vMonthAvailable.setText(sp.monthAvailable);
	    vDayAvgAvailable.setText(sp.dayAvgAvailable);
	    vMonthLeftDay.setText(sp.monthLeftDay);
	    
	    bar.setProgress(sp.usagePercentage);

	    if(sp.isOverSpend){
	    	vMonthAvailable.setForeground(ColorUtil.warningColor);
	    	vMonthSpend.setForeground(ColorUtil.warningColor);
	    	vTodaySpend.setForeground(ColorUtil.warningColor);
	   
	    }else{
	    	 vMonthAvailable.setForeground(ColorUtil.grayColor);
	         vMonthSpend.setForeground(ColorUtil.blueColor);
	         vTodaySpend.setForeground(ColorUtil.blueColor);
	    }
	    
	    bar.setForegroundColor(ColorUtil.getByPercentage(sp.usagePercentage));
	    
        addListener();
	    
	}

	@Override
	public void addListener() {
	}

}