package org.dyd.activit.task;

import java.util.Date;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;

/**
 * 设置用户的受理人
 * @author caowh 
 * @E-mail:458484881@qq.com 
 * @version 创建时间：2017年4月13日 下午5:15:03
 */
public class TaskDemo4 {

	public static void main(String[] args) {
		//创建流程对象实例
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		//获取身份服务组件
		IdentityService identityService = engine.getIdentityService();
		//新建用户
		User user = create(identityService,"userfirst3","张三","last","abc@126.com","123");
		//获取任务实例对象
		TaskService taskService = engine.getTaskService();
		Task task1 = taskService.newTask("task3");
		task1.setName("申请任务");
		taskService.saveTask(task1);
		//设置任务的受理人
		taskService.setAssignee(task1.getId(), user.getId());
		
		//
		System.out.println("用户的受理人是："+
		taskService.createTaskQuery().taskAssignee(user.getId()));
		
	}

	private static User create(IdentityService identityService, String id, String firstName, String lastName,
			String email, String password) {
		
		User newUser = identityService.newUser(id);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setPassword(password);
		identityService.saveUser(newUser);
		return identityService.createUserQuery().userId(id).singleResult();
	}
}
