package com.example.views.touch;

import android.view.MotionEvent;

public class MotionEvenAgent {
	
	private String name;
	private MotionEvent ev;
	private float startX, startY;
	private float currentX, currentY;
	private int action;
	private long startTimeMillis;
	private long currentTimeMillis;

	public MotionEvenAgent(String name){
		this.name = name;
	}
	
	public void setEvent(MotionEvent ev){
		this.ev = ev;
		this.action = ev.getAction() & MotionEvent.ACTION_MASK;
		if(isDown()){
			startX = ev.getX();
			startY = ev.getY();
			startTimeMillis = System.currentTimeMillis();
		}else if(isMove()){
			currentX = ev.getX();
			currentY = ev.getY();
			currentTimeMillis = System.currentTimeMillis();
		}
	}
	
	public boolean isDown(){
		return action == MotionEvent.ACTION_DOWN;
	}
	
	public boolean isMove(){
		return action == MotionEvent.ACTION_MOVE;
	}
	
	public boolean isUp(){
		return action == MotionEvent.ACTION_UP;
	}
	
	public boolean isFinish(){
		return action == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL;
	}
	
	public void show(){
		StringBuffer sb = new StringBuffer();
		if(isDown()){
			sb.append(name + "--DOWN--(" + startX + ", " + startY + ")");
		}else if(isMove()){
			sb.append(name + "--MOVE--(" + currentX + ", " + currentY + ")");
		}
		System.out.println(sb.toString());
	}
	
	public boolean expiredFor(long millis){
		return currentTimeMillis - startTimeMillis >= millis;
	}
}
