package com.devalla.grocerieskart.dto;

import java.io.Serializable;

public interface BaseDto extends Serializable {
	
	public Integer getNextPage();
	public void setNextPage(Integer nextPage);
	public Integer getPreviousPage();
	public void setPreviousPage(Integer previousPage);
	public Integer getTotalNumberOfPages();
	public void setTotalNumberOfPages(Integer totalNumberOfPages);
	

}
