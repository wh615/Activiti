package org.dyd.activit.attachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class CreateAttachment {
	public static void main(String[] args) throws FileNotFoundException {

		// 创建流程实例
		ProcessEngine engines = ProcessEngines.getDefaultProcessEngine();
		// 获取任务服务组件
		TaskService taskService = engines.getTaskService();
		// 获取运行服务组件
		RuntimeService runtimeService = engines.getRuntimeService();
		// 获取流程服务组件
		RepositoryService repositoryService = engines.getRepositoryService();
		// 部署流程描述文件
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("taskdemo/vacation1.bpmn")
				.deploy();
		// 查找流程定义
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId())
				.singleResult();
		// 启动流程文件
		ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
		// 查找任务
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		// 设置任务附件
		taskService.createAttachment("web url", task.getId(), pi.getId(), "163.com", "163 web Page",
				"http://www.163.com");

		// 创建图片的输入流文件
		File file = new File("resource/artifact/result.png");
		InputStream is = new FileInputStream(file);
		// 设置输入流为任务附件
		taskService.createAttachment("web url", task.getId(), pi.getId(), "163.com", "163 web Page", is);
	}
}
