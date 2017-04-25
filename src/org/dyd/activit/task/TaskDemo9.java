package org.dyd.activit.task;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * 设置多个参数
 * 
 * @author caowh
 * @E-mail:458484881@qq.com
 * @version 创建时间：2017年4月13日 下午5:15:03
 */
public class TaskDemo9 {

	public static void main(String[] args) {
		// 获取流程引擎实例
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		// 获取任务实例
		TaskService taskService = engine.getTaskService();
		// 保存第一个Task实例

		Task task1 = taskService.newTask("任务1");

		task1.setName("请假流程");
		taskService.saveTask(task1);
		// 初始化参数
		Map<String, Object> vars = new HashMap<>();
		vars.put("days", 10);
		vars.put("target", "欧洲");
		taskService.setVariables(task1.getId(), vars);
	}

}
