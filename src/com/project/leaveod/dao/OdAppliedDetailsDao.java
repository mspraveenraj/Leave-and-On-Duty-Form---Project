package com.project.leaveod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.project.leaveod.beans.OdAppliedDetailsBean;

public class OdAppliedDetailsDao {
	
	LinkedList<OdAppliedDetailsBean> odFormBeanObjectList =new LinkedList<OdAppliedDetailsBean>();
	
	Connection con;
	OdAppliedDetailsBean odFormBeanObject;
		
	 public OdAppliedDetailsDao()
	 {
		CreateConnectionDao conObj=new CreateConnectionDao();
		con=conObj.dbConnection();
	 }
	public LinkedList<OdAppliedDetailsBean> odAppliedDetails(String searchBy)
	{		
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		//String searchColumn="pending";
		try {
			
			preparedStatement = con
					.prepareStatement("select odform.dateofapply,odform.permissionfrom,odform.permissionto,odform.reason,odform.workassignedby,odapplied.acceptreject "
							+ "from odform inner join odapplied on odform.studentid=odapplied.studentid "
							+ "where odform.studentid = (select studentid from student where regno=?); ");
							
			preparedStatement.setString(1,searchBy);
				
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				odFormBeanObject = new OdAppliedDetailsBean();
				odFormBeanObject.setDateOfApply(resultSet.getString(1));
				odFormBeanObject.setDateOfLeaveFrom(resultSet.getString(2));
				odFormBeanObject.setDateOfLeaveTo(resultSet.getString(3));
				odFormBeanObject.setReason(resultSet.getString(4));
				odFormBeanObject.setWorkAssignedBy(resultSet.getString(5));
				odFormBeanObject.setStatus(resultSet.getString(6));
				odFormBeanObjectList.add(odFormBeanObject);
			}
			
			
		} catch (SQLException e) {
			System.out.println("LeaveAppliedDetailsDao : Problem in getting Data");
		}
		
		return odFormBeanObjectList;
	}
}
