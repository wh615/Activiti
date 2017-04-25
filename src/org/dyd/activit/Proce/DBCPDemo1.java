package org.dyd.activit.Proce;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.activiti.engine.ProcessEngineConfiguration;

public class DBCPDemo1 {

	public static void main(String[] args) throws SQLException {
		//读取配置文件
	  ProcessEngineConfiguration config = 
			  ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("dbcp-config.xml");
	  //能正常输出，即完成配置
	  DataSource ds = config.getDataSource();
	  //查询数据库元信息 ，如果能查询则表示链接成功
	  ds.getConnection().getMetaData();
	  System.out.println(ds.getClass().getName());
	}
}
