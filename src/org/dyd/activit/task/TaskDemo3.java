package org.dyd.activit.task;

import java.util.Date;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * 删除任务实例
 * @author caowh 
 * @E-mail:458484881@qq.com 
 * @version 创建时间：2017年4月13日 下午5:15:03
 */
public class TaskDemo3 {

	public static void main(String[] args) {
		//创建流程对象实例
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		//获取任务实例对象
		TaskService taskService = engine.getTaskService();
		
		//删除任务实例
		taskService.deleteTask("审核任务1");
		
		
	}
}
