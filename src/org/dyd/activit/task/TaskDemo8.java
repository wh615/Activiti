package org.dyd.activit.task;

import javax.xml.soap.Detail;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.dyd.activit.pojo.TestVO;

/**
 * 验证参数的作用域
 * @author caowh 
 * @E-mail:458484881@qq.com 
 * @version 创建时间：2017年4月13日 下午5:15:03
 */
public class TaskDemo8 {

	public static void main(String[] args) {
		//获取流程实例
		ProcessEngine engines = ProcessEngines.getDefaultProcessEngine();
		//获取任务组件
		TaskService taskService = engines.getTaskService();
		//获取运行服务组件
		RuntimeService runtimeService = engines.getRuntimeService();
		//获取流程存储服务组件
		RepositoryService repositoryService = engines.getRepositoryService();
		//部署流程描述文件
		Deployment deploy = repositoryService.createDeployment().
		addClasspathResource("taskdemo/vacation.bpmn").deploy();
		
		//查找流程定义
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().
				deploymentId(deploy.getId()).singleResult();
		//启动流程定义
		ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
	   
		//分别调用setVariable和setVariableLocal的方法
		Task task = taskService.createTaskQuery().
				processInstanceId(pi.getId()).singleResult();
		taskService.setVariable(task.getId(), "days",10);
		taskService.setVariableLocal(task.getId(), "target2", "浪漫的意大利旅游");
		//获取参数 请假的天数和旅游的目的地
        Object days = taskService.getVariable(task.getId(), "days");
        System.out.println("请假的天数是："+days);
        Object target = taskService.getVariable(task.getId(), "target2");
        System.out.println("旅游的地址："+target);
        //获取本地参数
        Object days1 = taskService.getVariableLocal(task.getId(), "days");
        System.out.println("获取本地请假的天数是"+days);
        Object target1 = taskService.getVariableLocal(task.getId(), "target2");
        System.out.println("获取本地旅游的目的地："+target1);
	}

}



























