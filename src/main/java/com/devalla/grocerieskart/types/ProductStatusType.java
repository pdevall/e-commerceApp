package com.devalla.grocerieskart.types;

import java.io.Serializable;

public interface ProductStatusType extends Serializable {
	
	public Long getStatusTypeId();
	public void setStatusTypeId(Long statusTypeId);
	public String getStatus();
	public void setStatus(String status);

}
