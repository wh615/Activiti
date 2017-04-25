package org.dyd.activit.task;

import java.util.Date;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
/**
 * 创建任务实例
 * @author caowh 
 * @E-mail:458484881@qq.com 
 * @version 创建时间：2017年4月13日 下午5:14:49
 */
public class TaskDemo1 {

	public static void main(String[] args) {
		//创建流程对象实例
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		//获取任务实例对象
		TaskService taskService = engine.getTaskService();
		//创建一个task1对象
		Task task1 = taskService.newTask();
		taskService.saveTask(task1);
		
		//创建一个task2对象
		Task task2 = taskService.newTask("审核任务1");
		task2.setDueDate(new Date());
		taskService.saveTask(task2);
		
	}
}
