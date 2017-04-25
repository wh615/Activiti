package org.dyd.activit.pojo;

import java.io.Serializable;

public class TestVO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	

	public TestVO(String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}
	
}
