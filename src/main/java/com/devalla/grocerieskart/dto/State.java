package com.devalla.grocerieskart.dto;

import java.io.Serializable;

public interface State extends Serializable {

	public Long getStateId();
	public void setStateId(Long stateId);
	public String getStateName();
	public void setStateName(String stateName);
}
