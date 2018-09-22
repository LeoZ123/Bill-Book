package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

public class ReportService {

	public List<Record> listThisMonthRecords() {

		RecordDAO dao = new RecordDAO();
		List<Record> monthRawData = dao.listThisMonth();
		List<Record> result = new ArrayList<>();

		Date monthBegin = DateUtil.getMonthBegin();
		int monthTotalDay = DateUtil.thisMonthTotalDay();

		Calendar c = Calendar.getInstance();

		for (int i = 0; i < monthTotalDay; i++) {
			Record r = new Record();
			c.setTime(monthBegin);
			c.add(Calendar.DATE, i);
			Date eachDayOfThisMonth = c.getTime();

			int daySpend = getDaySpend(eachDayOfThisMonth, monthRawData);
			r.setSpend(daySpend);
			result.add(r);
		}
		
		return result;
	}

	public int getDaySpend(Date d, List<Record> monthRawData) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(d);
		int daySpend = 0;
        for (Record record : monthRawData) {
        	String date1 = sdf.format(record.getMyDate());
            if(nowDate.equals(date1)){
                daySpend+=record.getSpend();
            }
        }
        return daySpend;
	}
}
