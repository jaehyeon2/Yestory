package com.example.project.beans.param;

import com.example.project.beans.param.request.Action;
import com.example.project.beans.param.request.Bot;
import com.example.project.beans.param.request.Intent;
import com.example.project.beans.param.request.UserRequest;


public class RequestParam {
	
	private Intent intent;
	private UserRequest userRequest;
	private Bot bot;
	private Action action;
	
	public Intent getIntent() {
		return intent;
	}
	public void setIntent(Intent intent) {
		this.intent = intent;
	}
	public UserRequest getUserRequest() {
		return userRequest;
	}
	public void setUserRequest(UserRequest userRequest) {
		this.userRequest = userRequest;
	}
	public Bot getBot() {
		return bot;
	}
	public void setBot(Bot bot) {
		this.bot = bot;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
	
}
