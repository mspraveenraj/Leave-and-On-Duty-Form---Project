package com.project.leaveod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.project.leaveod.beans.OdAppliedDetailsBean;
import com.project.leaveod.beans.SearchByOdBean;
import com.project.leaveod.beans.StudentBean;


public class OdAppliedFacultyDao {
	
	
	LinkedList<Integer> studentId = new LinkedList<Integer>();
	LinkedList<Integer> odFormId =new LinkedList<Integer>();
	LinkedList<OdAppliedDetailsBean> odFormBeanObjectList =new LinkedList<OdAppliedDetailsBean>();
	LinkedList<StudentBean> studentBeanObjectList = new LinkedList<StudentBean>();
	
	static String searchBy;
	int update1=0, update2=0;
	
	Connection con;
	OdAppliedDetailsBean odFormBeanObject;
	StudentBean studentBeanObject;
	SearchByOdBean sb =new SearchByOdBean();
	
	 public OdAppliedFacultyDao()
	 {
		CreateConnectionDao conObj=new CreateConnectionDao();
		con=conObj.dbConnection();
	 }
	
	public LinkedList<OdAppliedDetailsBean> odApplied(String searchBy)
	{		
		OdAppliedFacultyDao.searchBy=searchBy;
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
						.prepareStatement("select studentid,odformid from odapplied ");
			}
			else
			{
				preparedStatement = con
					.prepareStatement("select studentid,odformid from odapplied where acceptreject=? ");
							//+ "and (select studentid from student where regno=?);");
			preparedStatement.setString(1,searchBy);
			}
			resultSet1 = preparedStatement.executeQuery();
			while(resultSet1.next())
			{
				studentId.add(resultSet1.getInt(1));
				odFormId.add(resultSet1.getInt(2));
			}
			//preparedStatement.close();
			
			Iterator<Integer> odFormIdIterator = odFormId.iterator();
			while(odFormIdIterator.hasNext())
			{
				preparedStatement1 =con.prepareStatement("select * from odform where odformid=?");
				preparedStatement1.setInt(1,(int)odFormIdIterator.next());
				ResultSet resultSet2 = preparedStatement1.executeQuery();
				odFormBeanObject = new OdAppliedDetailsBean();
				while(resultSet2.next())
				{
					odFormBeanObject.setDateOfApply(resultSet2.getString(3));
					odFormBeanObject.setDateOfLeaveFrom(resultSet2.getString(4));
					odFormBeanObject.setDateOfLeaveTo(resultSet2.getString(5));
					odFormBeanObject.setReason(resultSet2.getString(6));
					odFormBeanObjectList.add(odFormBeanObject);
				}
			}
			
			//preparedStatement1.close();
			
			
		} catch (SQLException e) {
			System.out.println("LeaveAppliedFacultyDao : Problem in getting Data");
		}
		sb.setOdFormId(odFormId);
		sb.setOdFormList(odFormBeanObjectList);
		return odFormBeanObjectList;
	}
	
	public LinkedList<StudentBean> odAppliedStudents()
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
	
	public LinkedList<Integer> getOdFormId()
	{
		LinkedList<Integer> odFormId =new LinkedList<Integer>();
		try {
			
			PreparedStatement preparedStatement = con
					.prepareStatement("select odformid from odapplied where acceptreject=? ");
			preparedStatement.setString(1,OdAppliedFacultyDao.searchBy);
					
			ResultSet resultSet1 = preparedStatement.executeQuery();
			while(resultSet1.next())
			{
				odFormId.add(resultSet1.getInt(1));
			}
			preparedStatement.close();
		
		}
		catch(Exception e)
		{
			System.out.println("Problem in leave form id method "+e.getMessage());
		}
		
		//LeaveAppliedFacultyDao laf =new LeaveAppliedFacultyDao();
		//sb.setLeaveFormId(leaveFormId);
		return odFormId;
	}
	public void setOdFormId(LinkedList<Integer> fromSearchOdFormList)
	{
		this.odFormId= new LinkedList<Integer>(fromSearchOdFormList);
		
	}

}
