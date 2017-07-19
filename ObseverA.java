package com.pattern.observer;

public class ObseverA extends Obsever{

	private Subject subject;
	
	
	//测试。。。
	public ObseverA() {
		super();
		// TODO Auto-generated constructor stub
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
