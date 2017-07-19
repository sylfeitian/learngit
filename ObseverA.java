package com.pattern.observer;

public class ObseverA extends Obsever{


/**
*/
	private Subject subject;
	
	/**
	这是我的测试文件
	*/
	//测试。。。
	//sadfsaf
	public ObseverA() {
		super();
		// TODO Auto-generated constructor stub2017/7/19


	}


	public ObseverA(Subject subject) {
		this.subject = subject;
		this.subject.addObsever(this);
	}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
		this.subject.addObsever(this);
	}


	@Override
	public void change() {
		System.out.println("a:"+subject.getState());
		
	}

}
