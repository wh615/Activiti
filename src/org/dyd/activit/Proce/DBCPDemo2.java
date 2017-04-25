package org.dyd.activit.Proce;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.activiti.engine.ProcessEngineConfiguration;
import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDemo2 {

	public static void main(String[] args) throws SQLException  {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/act");
		ds.setUsername("root");
		ds.setPassword("mysql");
		ds.getConnection().getMetaData();
		ProcessEngineConfiguration config = 
				ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("dbcp-conding.xml");
		config.setDataSource(ds);
		
		System.out.println(config.getDataSource());
	}
}
