package com.devalla.grocerieskart.dto.impl;

import com.devalla.grocerieskart.dto.State;

public class StateImpl implements State {
	
	private Long stateId;
	private String stateName;
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}	

}
