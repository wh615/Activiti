package org.dyd.activit.task;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;

/**
 * 设置任务的基本参数
 * @author caowh 
 * @E-mail:458484881@qq.com 
 * @version 创建时间：2017年4月13日 下午5:15:03
 */
public class TaskDemo6 {

	public static void main(String[] args) {
		//创建流程对象实例
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		//获取任务实例对象
		TaskService taskService = engine.getTaskService();
		Task task1 = taskService.newTask("task5");
		task1.setName("申请任务");
		taskService.saveTask(task1);
		Map<String, Object> map = taskService.getVariablesLocal("task1");
		
		for (String map1 : map.keySet()) {
			System.out.println(map1+"===="+map.get(map1));
		}
		
		Date date = new Date();
		short s=3;
		taskService.setVariable(task1.getId(), "args1", false);
		taskService.setVariable(task1.getId(), "args2", date);
		taskService.setVariable(task1.getId(), "args3", s);
		taskService.setVariable(task1.getId(), "args4", "疯狂的程序员");
		taskService.setVariable(task1.getId(), "args5", 1000);
		taskService.setVariable(task1.getId(), "args6", null);
		taskService.setVariable(task1.getId(), "args7", 10L);
		
	}

}
