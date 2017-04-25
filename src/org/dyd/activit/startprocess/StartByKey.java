package org.dyd.activit.startprocess;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

public class StartByKey {

	public static void main(String[] args) {
		
		//获取流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		
		//获取仓储服务实例
		RepositoryService repositoryService = engine.getRepositoryService();
		//获取运行实例
		RuntimeService runtimeService = engine.getRuntimeService();
		repositoryService.createDeployment().addClasspathResource("bpmn/startByKey.bpmn20.xml").deploy();
		//初始化流程参数
		HashMap<String, Object> vars = new HashMap<>();
		vars.put("days", 4);
		//启动流程
		runtimeService.startProcessInstanceByKey("vacationRequest");
		runtimeService.startProcessInstanceByKey("vacationRequest", vars);
		runtimeService.startProcessInstanceByKey("vacationRequest", "testKey");
		runtimeService.startProcessInstanceByKey("vacationRequest", "testKey2", vars);
		//查询流程实例 结果为4
		
		long count = runtimeService.createProcessInstanceQuery().count();
		System.out.println("流程实例数量是："+count);
	}
}
