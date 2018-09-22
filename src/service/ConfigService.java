package service;

import dao.ConfigDAO;
import entity.Config;

public class ConfigService {

	public static final String budget = "budget";   

	public static final String mysqlPath = "C:/web qldata/mysql"; 

	public static final String default_budget = "500";  
	
	static ConfigDAO dao = new ConfigDAO();

	static{
		init();
	}
	
	private static void init(String key,String value){
		Config config = dao.getByKey(key);
		if(config==null){
			Config c = new Config();
			c.setMyKey(key);
			c.setMyValue(value);
			dao.add(c);
		}
	}
	
	public static void init(){
		init(budget,default_budget);
		init(mysqlPath,"");
	}
	
	public String get(String key){
		Config config = dao.getByKey(key);
		return config.getMyValue();
	}
	
	public boolean update(String key,String value){
		Config config = dao.getByKey(key);
		config.setMyValue(value);
		return dao.update(config);		
	}

	public int getIntBudget(){
		return Integer.parseInt(get(budget));
	}
}
