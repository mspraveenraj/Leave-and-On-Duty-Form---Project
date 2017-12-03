package com.project.leaveod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;

public class UpdateFromFacultyOdDao {
	PreparedStatement preparedStatement;
	int update1=0;
	
	Connection con;
	
	public UpdateFromFacultyOdDao() 
	{
		CreateConnectionDao conObj=new CreateConnectionDao();
		con=conObj.dbConnection();
	}
	//SearchByBean leaveFormIdObj=new SearchByBean();
	
				
	//leaveformId = leaveFormIdObj.getLeaveFormId();
	//Object leaveFormIdArray[]=leaveFormId.toArray();
	
	public boolean updateOdApplied(String receivedFacultySelection[])
	{		
		OdAppliedFacultyDao odFormIdObj =new OdAppliedFacultyDao();
		LinkedList<Integer> odFormId = odFormIdObj.getOdFormId();
		int i=0;
		try {
			preparedStatement = con
					.prepareStatement("update odapplied set acceptreject=? where odformid=?");
			
			while(i<receivedFacultySelection.length)
			{
			
			/*Iterator<Integer> leaveFormIdIterator = leaveFormId.iterator();
			while(leaveFormIdIterator.hasNext())
			{
				Integer appliedId = leaveFormIdIterator.next();
				if(appliedId)
			*/
				preparedStatement.setString(1,receivedFacultySelection[i]);
				preparedStatement.setInt(2,odFormId.get(i));
			
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