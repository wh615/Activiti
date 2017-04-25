package org.dyd.activit.group;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.cmd.CreateGroupCmd;

public class GroupDemo1 {

	public static void main(String[] args) {
		//创建默认流程
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		
		//获取权限服务对象
		IdentityService identityService = engine.getIdentityService();
	    
		//创建用户组对象实现对用户组主键的赋值操作
		//Group group = identityService.newGroup("5");
		//group.setName("超级管理员");
		//group.setType("manager");
		//identityService.saveGroup(group);
		
	
		//实现对用户组的修改操作
		//Group singleResult = identityService.createGroupQuery().groupId("1").singleResult();
		//singleResult.setName("扯淡的项目组长");
		//identityService.saveGroup(singleResult);
		//查询床架用户组的总数
		//System.out.println(identityService.createGroupQuery().count());
		//System.out.println(identityService.createGroupQuery().asc());
		
		String id=UUID.randomUUID().toString().replaceAll("-","");
		//createGroup(identityService,"6","userA","userA");
		//createGroup(identityService,"7","userb","userb");
		//createGroup(identityService,"8","userc","userb");
		createGroup(identityService,id,"userd","userb");
		// List<Group> datas = identityService.createGroupQuery().list();
		 List<Group> datas = identityService.createGroupQuery().listPage(2, 3);
		 for (Group data : datas) {
			System.out.println(data.getId()+"==="+data.getName()+"");
			if(data.getName().equals("超级管理员")){
				data.setType("最高级管理员");
				identityService.saveGroup(data);
				System.out.println(data.getId()+"==="+data.getName()+"=="+data.getType());
			}
		}
	}

	private static void createGroup(IdentityService identityService, String groupId, String name, String type) {
		Group newGroup = identityService.newGroup(groupId);
		newGroup.setName(name);
		newGroup.setType(type);
		identityService.saveGroup(newGroup);
	}
}
