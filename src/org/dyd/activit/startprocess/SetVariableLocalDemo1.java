package org.dyd.activit.startprocess;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class SetVariableLocalDemo1 {

	public static void main(String[] args) {

		// 创建流程引擎
		ProcessEngine engins = ProcessEngines.getDefaultProcessEngine();
		// 得到流程存储服务实例
		RepositoryService repositoryService = engins.getRepositoryService();
		// 得到运行服务实例
		RuntimeService runtimeService = engins.getRuntimeService();
		// 得到运行实例
		TaskService taskService = engins.getTaskService();
		// 部署流程描述文件
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("bpmn/localVariable.bpmn20.xml")
				.deploy();

		// 启动流程
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("vacationRequest");
		// 查询全部的任务，得到相应的执行流，设置不同的参数
		List<Task> list = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

		for (Task task : list) {
			Execution exe = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
			if ("Manager Audit".equals(task.getName())) {
				// 经理审核节点，设置Local参数
				runtimeService.setVariableLocal(exe.getId(), "managerVar", "manager var");
			} else {
				// hr审核节点设置全局参数
				runtimeService.setVariable(exe.getId(), "hrVar", "hr var");
			}
		}
		// 两个执行流时输出的参数
		for (Task task : list) {
			Execution exe = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
			if ("Manager Audit".equals(task.getName())) {
				System.out.println(
						"使用getVariableLocal方法获取经理参数" + runtimeService.getVariableLocal(exe.getId(), "managerVar"));
				System.out.println("使用getVariable方法获取参数" + runtimeService.getVariable(exe.getId(), "managerVar"));
			} else {
				//本地不能获取全局的参数
				System.out.println("使用getVariableLocal方法获取hr参数" + runtimeService.getVariableLocal(exe.getId(), "hrVar"));
				
				System.out.println("使用getVariable方法获取hr参数" + runtimeService.getVariable(exe.getId(), "hrVar"));
			}
		}

		for (Task task : list) {

			//任务的完成
			taskService.complete(task.getId());
		}
		System.out.println("=======================完成任务流程之后=============================");
		list=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
		for (Task task : list) {
			System.out.println("当前流程节点："+task.getName());
			Execution exe=(Execution) runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
		System.out.println("经理参数："+runtimeService.getVariable(exe.getId(), "managerVar"));
		System.out.println("HR参数："+runtimeService.getVariable(exe.getId(), "hrVar"));
		 }
	}
}
