package org.dyd.activit.task;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.dyd.activit.pojo.TestVO;

/**
 * 设置任务的基本参数获取基本参数
 * @author caowh 
 * @E-mail:458484881@qq.com 
 * @version 创建时间：2017年4月13日 下午5:15:03
 */
public class TaskDemo7 {

	public static void main(String[] args) {
		//创建流程对象实例
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		//获取任务实例对象
		TaskService taskService = engine.getTaskService();
		//保存第一个task对象
		Task task1 = taskService.newTask("task8");
		task1.setName("申请出差");
		taskService.saveTask(task1);
		
		taskService.setVariable(task1.getId(), "days", 10);
		taskService.setVariable(task1.getId(), "target",new TestVO("美丽的北京"));
		
		//获取出差的天数
		Integer days=(Integer) taskService.getVariable(task1.getId(), "days");
		//获取出差的地点
		TestVO target= (TestVO) taskService.getVariable(task1.getId(), "target");
		
		System.out.println("请假时间"+days);
		System.out.println("出差地点："+target.getName());
	}

}
