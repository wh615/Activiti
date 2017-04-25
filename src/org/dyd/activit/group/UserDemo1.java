package org.dyd.activit.group;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.cmd.CreateGroupCmd;

/**
 * 通过activiti实现用户名和密码的校验
 * @author caowh 
 * @E-mail:458484881@qq.com 
 * @version 创建时间：2017年4月9日 下午10:10:13
 */
public class UserDemo1 {

	public static void main(String[] args) {
		//创建默认流程
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		
		//获取权限服务对象
		IdentityService identityService = engine.getIdentityService();
	   
		String id=UUID.randomUUID().toString().replaceAll("-","");
		createUser(identityService,id,"angus","young","wanhe615@126.com","abc");
		System.out.println("验证密码结果是："+identityService.checkPassword(id, "abc"));
		System.out.println("验证密码结果是："+identityService.checkPassword(id, "c"));
		
	}

	private static void createUser(IdentityService identityService, String id, String firstName, String lastName,
			String email, String psw) {
		User user = identityService.newUser(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(psw);
		identityService.saveUser(user);
		
	}

	
}
