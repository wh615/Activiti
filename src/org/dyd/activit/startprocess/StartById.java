package org.dyd.activit.startprocess;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

public class StartById {

	public static void main(String[] args) {
		
		//获取流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		
		//获取仓储服务实例
		RepositoryService repositoryService = engine.getRepositoryService();
		//获取运行实例
		RuntimeService runtimeService = engine.getRuntimeService();
		//获取资源描述文件
		Deployment deploy = repositoryService.createDeployment().
				addClasspathResource("bpmn/startById.bpmn20.xml").deploy();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
	    //设置流程参数
		Map<String, Object> vars = new HashMap<>();
		
		vars.put("days", 5);
		runtimeService.startProcessInstanceById(pd.getId());
		runtimeService.startProcessInstanceById(pd.getId(),vars);
		runtimeService.startProcessInstanceById(pd.getId(), "vacationRequest1");
		runtimeService.startProcessInstanceById(pd.getId(),"vacationRequest2",vars);
		
		//查询流程实例 输出结果为4
		long count = runtimeService.createProcessInstanceQuery().count();
		System.out.println("流程实例数量："+count);
	}
}
