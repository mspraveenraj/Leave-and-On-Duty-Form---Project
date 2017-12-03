package com.project.leaveod.beans;

import java.util.LinkedList;

public class SearchByBean {
	//LeaveAppliedFacultyDao leaveAppliedFacultyObj = new LeaveAppliedFacultyDao();
	public LinkedList<LeaveFormBean> leaveFormList;
	public LinkedList<StudentBean> studentList;
	public LinkedList<Integer> leaveFormId;
	
	public LinkedList<LeaveFormBean> getLeaveFormList() {
		return leaveFormList;
	}
	public void setLeaveFormList(LinkedList<LeaveFormBean> leaveFormList) {
		this.leaveFormList = leaveFormList;
	}
	public LinkedList<StudentBean> getStudentList() {
		return studentList;
	}
	public void setStudentList(LinkedList<StudentBean> studentList) {
		this.studentList = studentList;
	}
	public LinkedList<Integer> getLeaveFormId() {
		return leaveFormId;
	}
	public void setLeaveFormId(LinkedList<Integer> leaveFormId) {
		this.leaveFormId = leaveFormId;
	}
	
	
}