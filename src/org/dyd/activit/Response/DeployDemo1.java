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
 * 通过输入流对象实现对文件的加载存储
 * @author caowh 
 * @E-mail:458484881@qq.com 
 * @version 创建时间：2017年4月10日 下午3:45:41
 */
public class DeployDemo1 {

	public static void main(String[] args) throws FileNotFoundException {
		
		//创建流程引擎对象
		ProcessEngine engines = ProcessEngines.getDefaultProcessEngine();
		//得到流程存储实力对象
		RepositoryService repositoryService = engines.getRepositoryService();
		
		//第一个输入流对象资源
		InputStream input1=new FileInputStream(
				new File("resource/artifact/flow_inputstream1.png"));
		//第二个输入流对象资源
		InputStream input2=new FileInputStream(
				new File("resource/artifact/flow_inputstream2.png"));
		
		//创建流程DeploymentBuilder对象
		DeploymentBuilder builder = repositoryService.createDeployment();
		builder.addInputStream("inputA", input1);
		builder.addInputStream("inputB", input2);
		builder.deploy();
	}
}
