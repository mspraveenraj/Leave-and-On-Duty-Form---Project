package com.project.leaveod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;

public class UpdateFromFacultyDao {
	PreparedStatement preparedStatement;
	int update1=0;
	
	Connection con;
	
	public UpdateFromFacultyDao() 
	{
		CreateConnectionDao conObj=new CreateConnectionDao();
		con=conObj.dbConnection();
	}
	//SearchByBean leaveFormIdObj=new SearchByBean();
	
				
	//leaveformId = leaveFormIdObj.getLeaveFormId();
	//Object leaveFormIdArray[]=leaveFormId.toArray();
	
	public boolean updateLeaveApplied(String receivedFacultySelection[])
	{		
		LeaveAppliedFacultyDao leaveFormIdObj =new LeaveAppliedFacultyDao();
		LinkedList<Integer> leaveFormId = leaveFormIdObj.getLeaveFormId();
		int i=0;
		try {
			preparedStatement = con
					.prepareStatement("update leaveapplied set acceptreject=? where leaveformid=?");
			
			while(i<receivedFacultySelection.length)
			{
			
			/*Iterator<Integer> leaveFormIdIterator = leaveFormId.iterator();
			while(leaveFormIdIterator.hasNext())
			{
				Integer appliedId = leaveFormIdIterator.next();
				if(appliedId)
			*/
				preparedStatement.setString(1,receivedFacultySelection[i]);
				preparedStatement.setInt(2,leaveFormId.get(i));
			
				update1 = preparedStatement.executeUpdate();
				i++;
			}
			preparedStatement.close();
		}
		catch (Exception e) {
			System.out.println("Problem in Update Leave Applied  "+e.getMessage());
			e.printStackTrace();
			
			//System.out.println((Integer)leaveFormId.get(i));
		}
		return update1>0;
	}
}