package com.devalla.grocerieskart.web.form;

public abstract class BaseForm {	
	
	private String actionString;

	public String getActionString() {
		return actionString;
	}

	public void setActionString(String actionString) {
		this.actionString = actionString;
	}
	
	private Integer nextPage;
	private Integer previousPage;
	private Integer totalNumberOfPages;
	private Integer startNumber;
	private Integer endNumber;
	
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

	public Integer getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(Integer startNumber) {
		this.startNumber = startNumber;
	}

	public Integer getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(Integer endNumber) {
		this.endNumber = endNumber;
	}

}
