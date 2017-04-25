package org.dyd.activit.startprocess;

import java.util.HashMap;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;

public class SignalDemo1 {

	public static void main(String[] args) {
		//获取流程引擎实例
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
	    //得到流程存储服务实例
		RepositoryService repositoryService = engine.getRepositoryService();
		//得到运行是服务组件
		RuntimeService runtimeService = engine.getRuntimeService();
		//部署流程描述文件
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("bpmn1/signal.bpmn20.xml").deploy();
	   
		//开始流程
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("vacationRequest");
		
		//查找当前的执行流
		Execution exe = runtimeService.createExecutionQuery().activityId("writeVacation").singleResult();
		if(exe!=null){
			System.out.println("当前的流程节点：writeVacation");
		}
		//发送信息让执行流往前执行
		runtimeService.signal(exe.getId());
		//向下执行后悔出现两个分支，根据流程节点Id查询两个执行流
		Execution managerAuditExe = runtimeService.createExecutionQuery().activityId("managerAudit").singleResult();
		Execution hrAuditExe = runtimeService.createExecutionQuery().activityId("hrAudit").singleResult();
		if(managerAuditExe!=null&&hrAuditExe!=null){
			System.out.println("当前流程节点为：managerAuditExe======》hrAuditExe");
		}
		//初始化参数
		HashMap<String, Object> vars = new HashMap<>();
		vars.put("days", 5);
		//向前进行两个节点
		runtimeService.signal(managerAuditExe.getId());
		runtimeService.signal(hrAuditExe.getId(), vars);
		//此时到了Boss Audit 节点 查询节点对应的执行流
		Execution bossExe = runtimeService.createExecutionQuery().activityId("bossAudit").singleResult();
		if(bossExe!=null){
			System.out.println("当前流程执行点为："+bossExe);
		}
		//输出参数
		System.out.println("当前参数是:"+runtimeService.getVariable(bossExe.getId(), "days"));
		//完成流程
		runtimeService.signal(bossExe.getId());
		//查询执行流
		List<Execution> exes = runtimeService.createExecutionQuery().processDefinitionId(pi.getId()).list();
		System.out.println("流程完成，执行流数量："+exes.size());
	}
}
