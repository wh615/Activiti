package org.dyd.activit.Response;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;

/**
 * 实现对压缩包的资源的上传服务
 * 
 * @author caowh
 * @E-mail:458484881@qq.com
 * @version 创建时间：2017年4月10日 下午3:45:41
 */
public class DeployDemo7 {

	public static void main(String[] args) {

		// 创建流程引擎对象
		ProcessEngine engines = ProcessEngines.getDefaultProcessEngine();
		// 得到流程存储实力对象
		RepositoryService repositoryService = engines.getRepositoryService();

		// 创建身份服务对象
		IdentityService identityService = engines.getIdentityService();
		// 创建流程DeploymentBuilder对象
		Deployment dep = repositoryService.createDeployment()
				.addClasspathResource("bpmn/candidateUser.bpmn").deploy();
		
		//查询流程定义实体
		ProcessDefinition def = repositoryService.createProcessDefinitionQuery().
				deploymentId(dep.getId()).singleResult();
		createUser(identityService,"user1","angus","young","abc@163.com","123");
		createUser(identityService,"user2","user1","young1","abc@163.com","123");
		createUser(identityService,"user3","user2","young2","abc@163.com","123");
		
		//设置用户和流程的关系
		repositoryService.addCandidateStarterUser(def.getId(),"user1");
		repositoryService.addCandidateStarterUser(def.getId(),"user2");
		
		
	}

	private static void createUser(IdentityService identityService, String id, String firstName, String lastName,
			String email, String password) {
		User newUser = identityService.newUser(id);
		//设置用户的各个属性
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setPassword(password);
		identityService.saveUser(newUser);
	}
}
