package org.dyd.activit.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;

/**
 * 实现流程和用户信息的绑定(act.act_ru_identitylink数据表展现数据的关系)
 * 
 * @author caowh
 * @E-mail:458484881@qq.com
 * @version 创建时间：2017年4月10日 下午3:45:41
 */
public class DeployDemo5 {

	public static void main(String[] args) throws IOException {

		// 创建流程引擎对象
		ProcessEngine engines = ProcessEngines.getDefaultProcessEngine();
		// 得到流程存储实力对象
		RepositoryService repositoryService = engines.getRepositoryService();

		// 创建流程DeploymentBuilder对象
		Deployment dep = repositoryService.createDeployment().addClasspathResource("artifact/first.txt").deploy();
 
	
		InputStream is = repositoryService.getResourceAsStream(dep.getId(), "artifact/first.txt");
		//读取输入流
		/*int count = is.available();
		byte[] content=new byte[count];
		is.read(content);
		String result=new String(content);
		System.out.println(result);
		*/
		
		int count;
		byte[] content=new byte[1024];
		while(is.read(content)!=-1){
			count = is.read();
		}
		System.out.println(new String(content));
	}
}
