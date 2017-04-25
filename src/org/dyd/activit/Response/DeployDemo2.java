package org.dyd.activit.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;

/**
 * 实现对加载文件实现对资源文件的存储
 * @author caowh 
 * @E-mail:458484881@qq.com 
 * @version 创建时间：2017年4月10日 下午3:44:41
 */
public class DeployDemo2 {

	public static void main(String[] args) throws FileNotFoundException {
		
		//创建流程引擎对象
		ProcessEngine engines = ProcessEngines.getDefaultProcessEngine();
		//得到流程存储实例对象
		RepositoryService repositoryService = engines.getRepositoryService();
		
		//创建流程DeploymentBuilder对象
		DeploymentBuilder builder = repositoryService.createDeployment();
		builder.addClasspathResource("artifact/flow_inputstream1.png");
		builder.deploy();
	}
}
