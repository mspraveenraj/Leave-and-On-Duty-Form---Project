package com.project.leaveod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.project.leaveod.beans.LeaveFormBean;
import com.project.leaveod.beans.SearchByBean;
import com.project.leaveod.beans.StudentBean;


public class LeaveAppliedFacultyDao {
	
	
	LinkedList<Integer> studentId = new LinkedList<Integer>();
	LinkedList<Integer> leaveFormId =new LinkedList<Integer>();
	LinkedList<LeaveFormBean> leaveFormBeanObjectList =new LinkedList<LeaveFormBean>();
	LinkedList<StudentBean> studentBeanObjectList = new LinkedList<StudentBean>();
	
	static String searchBy;
	int update1=0, update2=0;
	
	Connection con;
	LeaveFormBean leaveFormBeanObject;
	StudentBean studentBeanObject;
	SearchByBean sb =new SearchByBean();
	
	 public LeaveAppliedFacultyDao()
	 {
		CreateConnectionDao conObj=new CreateConnectionDao();
		con=conObj.dbConnection();
	 }
	
	public LinkedList<LeaveFormBean> leaveApplied(String searchBy)
	{		
		LeaveAppliedFacultyDao.searchBy=searchBy;
		PreparedStatement preparedStatement;
		PreparedStatement preparedStatement1;
		ResultSet resultSet1;
		//String searchColumn="pending";
		try {
			
			//if(searchBy.equals("pending") || searchBy.equals("accept") || searchBy.equals("reject"))
				//searchColumn="acceptreject";
			//else if(searchBy.endsWith("2017"))
			if(searchBy.equals("*"))
			{
				preparedStatement = con
						.prepareStatement("select studentid,leaveformid from leaveapplied ");
			}
			else
			{
				preparedStatement = con
					.prepareStatement("select studentid,leaveformid from leaveapplied where acceptreject=? ");
							//+ "and (select studentid from student where regno=?);");
			preparedStatement.setString(1,searchBy);
			}
			resultSet1 = preparedStatement.executeQuery();
			while(resultSet1.next())
			{
				studentId.add(resultSet1.getInt(1));
				leaveFormId.add(resultSet1.getInt(2));
			}
			//preparedStatement.close();
			
			Iterator<Integer> leaveFormIdIterator = leaveFormId.iterator();
			while(leaveFormIdIterator.hasNext())
			{
				preparedStatement1 =con.prepareStatement("select * from leaveform where leaveformid=?");
				preparedStatement1.setInt(1,(int)leaveFormIdIterator.next());
				ResultSet resultSet2 = preparedStatement1.executeQuery();
				leaveFormBeanObject = new LeaveFormBean();
				while(resultSet2.next())
				{
					leaveFormBeanObject.setDateOfApply(resultSet2.getString(3));
					leaveFormBeanObject.setDateOfLeaveFrom(resultSet2.getString(4));
					leaveFormBeanObject.setDateOfLeaveTo(resultSet2.getString(5));
					leaveFormBeanObject.setReason(resultSet2.getString(6));
					leaveFormBeanObjectList.add(leaveFormBeanObject);
				}
			}
			
			//preparedStatement1.close();
			
			
		} catch (SQLException e) {
			System.out.println("LeaveAppliedFacultyDao : Problem in getting Data");
		}
		sb.setLeaveFormId(leaveFormId);
		sb.setLeaveFormList(leaveFormBeanObjectList);
		return leaveFormBeanObjectList;
	}
	
	public LinkedList<StudentBean> leaveAppliedStudents()
	{
	
		try 
		{
			
			Iterator<Integer> studentIdIterator = studentId.iterator();
			while(studentIdIterator.hasNext())
			{
				PreparedStatement preparedStatement = con
						.prepareStatement("select studentname,regno,year,section from student where studentid=?");
			
				preparedStatement.setInt(1,(int)studentIdIterator.next());
		
				ResultSet resultSet1 = preparedStatement.executeQuery();
				studentBeanObject = new StudentBean();
			
				while(resultSet1.next())
				{
					studentBeanObject.setName(resultSet1.getString(1));
					studentBeanObject.setRegno(resultSet1.getString(2));
					studentBeanObject.setYear(resultSet1.getInt(3));
					studentBeanObject.setSection(resultSet1.getString(4));
					studentBeanObjectList.add(studentBeanObject);
				}
			}
			//preparedStatement.close();
			
		} catch (SQLException e) {
			System.out.println("LeaveAppliedFacultyDao : Problem in leave applied Students");
		}
		sb.setStudentList(studentBeanObjectList);
		return studentBeanObjectList;
	}
	
	public LinkedList<Integer> getLeaveFormId()
	{
		LinkedList<Integer> leaveFormId =new LinkedList<Integer>();
		try {
			
			PreparedStatement preparedStatement = con
					.prepareStatement("select leaveformid from leaveapplied where acceptreject=? ");
			preparedStatement.setString(1,LeaveAppliedFacultyDao.searchBy);
					
			ResultSet resultSet1 = preparedStatement.executeQuery();
			while(resultSet1.next())
			{
				leaveFormId.add(resultSet1.getInt(1));
			}
			preparedStatement.close();
		
		}
		catch(Exception e)
		{
			System.out.println("Problem in leave form id method "+e.getMessage());
		}
		
		//LeaveAppliedFacultyDao laf =new LeaveAppliedFacultyDao();
		//sb.setLeaveFormId(leaveFormId);
		return leaveFormId;
	}
	public void setLeaveFormId(LinkedList<Integer> fromSearchLeaveFormList)
	{
		this.leaveFormId= new LinkedList<Integer>(fromSearchLeaveFormList);
		
	}

}
