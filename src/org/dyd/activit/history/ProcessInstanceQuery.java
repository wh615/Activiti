package org.dyd.activit.history;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;

public class ProcessInstanceQuery {

	public static void main(String[] args) {

		//创建流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		//获取仓储服务组件
		RepositoryService repositoryService = engine.getRepositoryService();
		//获取历史服务组件
		HistoryService historyService = engine.getHistoryService();
		//获取运行服务组件
		RuntimeService runtimeService = engine.getRuntimeService();
		//获取任务对象
		TaskService taskService = engine.getTaskService();
		//部署流程对象
		Deployment deploy = repositoryService.createDeployment().
				addClasspathResource("history/ProcessInstanceQuery.bpmn").deploy();
		//查询流程定义
		ProcessDefinition pi = repositoryService.createProcessDefinitionQuery().
				deploymentId(deploy.getId()).singleResult();
		//开始流程
		ProcessInstance pi1 = runtimeService.startProcessInstanceByKey("testProcess","business1");
		ProcessInstance pi2 = runtimeService.startProcessInstanceByKey("testProcess","business2");
		
		//查询执行流程
		Execution exe1 = runtimeService.createExecutionQuery().
				processInstanceId(pi1.getId()).singleResult();
		
		Execution exe2 = runtimeService.createExecutionQuery().
				processInstanceId(pi2.getId()).singleResult();
		//完成第一条流程
		runtimeService.signal(exe1.getId());
		exe1=runtimeService.createExecutionQuery().processInstanceId(pi1.getId()).singleResult();
		runtimeService.signal(exe1.getId());
		//查询已完成的流程
		List<HistoricProcessInstance> datas = historyService.createHistoricProcessInstanceQuery().finished().list();
		
	}

}

































