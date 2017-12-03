package com.project.leaveod.beans;

import java.util.LinkedList;

public class SearchByOdBean {
	public LinkedList<OdAppliedDetailsBean >odFormList;
	public LinkedList<StudentBean> studentList;
	public LinkedList<Integer> odFormId;
	
	public LinkedList<OdAppliedDetailsBean> getOdFormList() {
		return odFormList;
	}
	public void setOdFormList(LinkedList<OdAppliedDetailsBean> odFormList) {
		this.odFormList = odFormList;
	}
	public LinkedList<StudentBean> getStudentList() {
		return studentList;
	}
	public void setStudentList(LinkedList<StudentBean> studentList) {
		this.studentList = studentList;
	}
	public LinkedList<Integer> getOdFormId() {
		return odFormId;
	}
	public void setOdFormId(LinkedList<Integer> odFormId) {
		this.odFormId = odFormId;
	}
	
	
}
