package org.dyd.activit.Proce;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.concurrent.ThreadPoolExecutor;

import org.activiti.engine.ProcessEngineConfiguration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Demo2 {

	private ThreadPoolExecutor executor;
	
	public static void main(String[] args) throws SQLException, PropertyVetoException {

		
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setUser("root");
		ds.setPassword("mysql");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/act");
		ds.setDriverClass("com.mysql.jdbc.Driver");
		
		ds.getConnection().getMetaData();
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("c3p0-config2.xml");
		config.setDataSource(ds);
		System.out.println(config.getDataSource());

	}
}
