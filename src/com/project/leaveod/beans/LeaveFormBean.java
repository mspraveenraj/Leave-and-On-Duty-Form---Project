package com.project.leaveod.beans;

public class LeaveFormBean {
	int id;
	String dateOfApply;
	String dateOfLeaveFrom;
	String dateOfLeaveTo;
	String reason;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getDateOfApply() {
		return dateOfApply;
	}
	public void setDateOfApply(String dateOfApply) {
		this.dateOfApply = dateOfApply;
	}
	public String getDateOfLeaveFrom() {
		return dateOfLeaveFrom;
	}
	public void setDateOfLeaveFrom(String dateOfLeaveFrom) {
		this.dateOfLeaveFrom = dateOfLeaveFrom;
	}
	public String getDateOfLeaveTo() {
		return dateOfLeaveTo;
	}
	public void setDateOfLeaveTo(String dateOfLeaveTo) {
		this.dateOfLeaveTo = dateOfLeaveTo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
