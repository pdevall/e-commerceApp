package com.devalla.grocerieskart.dto.impl;

import com.devalla.grocerieskart.dto.BaseDto;

public class BaseDtoImpl implements BaseDto {
	
	private Integer nextPage;
	private Integer previousPage;
	private Integer totalNumberOfPages;
	
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(Integer previousPage) {
		this.previousPage = previousPage;
	}
	public Integer getTotalNumberOfPages() {
		return totalNumberOfPages;
	}
	public void setTotalNumberOfPages(Integer totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}
	
	

}
