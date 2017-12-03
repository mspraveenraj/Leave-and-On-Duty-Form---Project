package com.project.leaveod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.project.leaveod.beans.LeaveAppliedDetailsBean;

public class LeaveAppliedDetailsDao {
	
	LinkedList<LeaveAppliedDetailsBean> leaveFormBeanObjectList =new LinkedList<LeaveAppliedDetailsBean>();
	
	Connection con;
	LeaveAppliedDetailsBean leaveFormBeanObject;
		
	 public LeaveAppliedDetailsDao()
	 {
		CreateConnectionDao conObj=new CreateConnectionDao();
		con=conObj.dbConnection();
	 }
	public LinkedList<LeaveAppliedDetailsBean> leaveAppliedDetails(String searchBy)
	{		
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		//String searchColumn="pending";
		try {
			
			preparedStatement = con
					.prepareStatement("select leaveform.dateofapply,leaveform.dateofleavefrom,leaveform.dateofleaveto,leaveform.reason,leaveapplied.acceptreject "
							+ "from leaveform inner join leaveapplied on leaveform.studentid=leaveapplied.studentid "
							+ "where leaveform.studentid = (select studentid from student where regno=?); ");
							
			preparedStatement.setString(1,searchBy);
				
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				leaveFormBeanObject = new LeaveAppliedDetailsBean();
				leaveFormBeanObject.setDateOfApply(resultSet.getString(1));
				leaveFormBeanObject.setDateOfLeaveFrom(resultSet.getString(2));
				leaveFormBeanObject.setDateOfLeaveTo(resultSet.getString(3));
				leaveFormBeanObject.setReason(resultSet.getString(4));
				leaveFormBeanObject.setStatus(resultSet.getString(5));
				leaveFormBeanObjectList.add(leaveFormBeanObject);
			}
			
			
		} catch (SQLException e) {
			System.out.println("LeaveAppliedDetailsDao : Problem in getting Data");
		}
		
		return leaveFormBeanObjectList;
	}
}
