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

public class UserDemo2 {

	public static void main(String[] args) {
		//创建默认流程
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		
		//获取权限服务对象
		IdentityService identityService = engine.getIdentityService();
	   
		String id=UUID.randomUUID().toString().replaceAll("-","");
		createUser(identityService,id,"angus","young","wanhe615@126.com","abc");
		
	}

	private static void createUser(IdentityService identityService, String id, String firstName, String lastName,
			String email, String picid) {
		User user = identityService.newUser(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(picid);
		identityService.saveUser(user);
	}
}
