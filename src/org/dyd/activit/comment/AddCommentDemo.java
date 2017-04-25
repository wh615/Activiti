package org.dyd.activit.comment;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

public class AddCommentDemo {

	public static void main(String[] args) {

		// 获取流程实例
		ProcessEngine engins = ProcessEngines.getDefaultProcessEngine();
		// 获取任务服务组件
		TaskService taskService = engins.getTaskService();
		// 获取资源实例
		RepositoryService repositoryService = engins.getRepositoryService();
		// 获取运行实例
		RuntimeService runtimeService = engins.getRuntimeService();
		// 部署流程描述文件
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("taskdemo/vacation.bpmn")
				.deploy();
		// 查找流程定义
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId())
				.singleResult();
		// 启动流程
		ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

		taskService.addComment(task.getId(), pi.getId(), "this is comment message");
		
		//查询评论
		List<Comment> taskComments = taskService.getTaskComments(task.getId());
		for (Comment comment : taskComments) {
			System.out.println(comment.getFullMessage());//获取评论的内容
		}
		System.out.println("评论的数量是："+taskComments.size());
		System.out.println("评论的内容是："+taskComments.get(0).toString());
	}
}
