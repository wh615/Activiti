package org.dyd.activit.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;

/**
 * 实现对压缩包的资源的上传服务
 * @author caowh 
 * @E-mail:458484881@qq.com 
 * @version 创建时间：2017年4月10日 下午3:45:41
 */
public class DeployDemo4 {

	public static void main(String[] args) throws FileNotFoundException {
		
		//创建流程引擎对象
		ProcessEngine engines = ProcessEngines.getDefaultProcessEngine();
		//得到流程存储实力对象
		RepositoryService repositoryService = engines.getRepositoryService();
		
		//第一个输入流对象资源
		InputStream input1=new FileInputStream(
				new File("resource/artifact/ZipInputStream.zip"));
		ZipInputStream zipInputStream = new ZipInputStream(input1);
		//创建流程DeploymentBuilder对象
		DeploymentBuilder builder = repositoryService.createDeployment();
		builder.addZipInputStream(zipInputStream);
		builder.name("ZIP文件");
		builder.deploy();
	}
}
