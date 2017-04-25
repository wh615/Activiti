package org.dyd.activit.task;

import java.util.HashSet;
import java.util.Set;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * 批量的创建和删除任务
 * @author caowh 
 * @E-mail:458484881@qq.com 
 * @version 创建时间：2017年4月13日 下午5:15:03
 */
public class TaskDemo2 {

	public static void main(String[] args) {
		//创建流程对象实例
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		//获取任务实例对象
		TaskService taskService = engine.getTaskService();
		//通过循环实现多个任务的存储
		for(int i=0;i<10;i++){
			saveTask(taskService,String.valueOf(i));
		}
		//删除task（不包括历史数据和子任务）
		taskService.deleteTask("1");
		//删除task（包括历史数据和子任务）
		taskService.deleteTask("2",true);
		
		//删除多个task（不包括历史数据和子任务）
	    Set<String> set=new HashSet<String>();
	    set.add("3");
	    set.add("4");
	    taskService.deleteTasks(set);
	    //删除多个task（包括历史数据和子任务）
	    Set<String> setm=new HashSet<String>();
	    setm.add("5");
	    setm.add("6");
	    taskService.deleteTasks(setm, true);
	    
	    taskService.deleteTask("3", true);
	}

	private static void saveTask(TaskService taskService, String id) {

		Task newTask = taskService.newTask(id);
		newTask.setName("张三");
		taskService.saveTask(newTask);
	}
}
