package com.project.leaveod.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class FacultyLoginDao {
		
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		Connection con;
		//String studentName;
		
		public FacultyLoginDao()
		{
			CreateConnectionDao conObj=new CreateConnectionDao();
			con=conObj.dbConnection();
		}
		
		public boolean verifyUser(String receivedUserName, String receivedPassword)
		{
			int c=0;
			
			try {
				preparedStatement = con
						.prepareStatement("select * from faculty where username=? and password=?");
				preparedStatement.setString(1,receivedUserName);
				preparedStatement.setString(2,receivedPassword);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next())
				{
					//studentName = resultSet.getString(2);
					c++;
				}
				
				if(c==1)
					return true;
				
			} catch (SQLException e) {
				System.out.println("Problem in getting Data");
			}
			
			return false;
		}
		
		//public String getStudentName()
		//{
		//	return studentName;
		//}
	}
