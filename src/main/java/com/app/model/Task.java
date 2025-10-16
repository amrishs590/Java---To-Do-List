package com.app.model;

public class Task {
	private int id;
	private String name;
	private boolean status;
	
	public Task(int id,String name,boolean status)
	{
		this.id = id;
		this.name = name;
		this.status = status;
	}
	
	public int getId()
	{
		return id;
	}
	public String getTaskName()
	{
		return name;
	}
	public boolean isCompleted()
	{
		return status;
	}
	public void setStatus()
	{
		status = !status;
	}
}
