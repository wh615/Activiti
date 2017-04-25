package org.dyd.activit.Proce;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.activiti.engine.ProcessEngineConfiguration;
import org.apache.commons.dbcp.BasicDataSource;

public class C3P0Demo1 {

	public static void main(String[] args) throws SQLException  {

		//读取配置文件
		  ProcessEngineConfiguration config = 
				  ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("c3p0-config.xml");
		  //能正常输出，即完成配置
		  DataSource ds = config.getDataSource();
		  //查询数据库元信息 ，如果能查询则表示链接成功
		  ds.getConnection().getMetaData();
		  System.out.println(ds.getClass().getName());
	}
}
