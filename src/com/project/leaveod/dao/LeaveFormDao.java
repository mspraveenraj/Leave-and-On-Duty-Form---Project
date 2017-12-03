package com.project.leaveod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaveFormDao {

	PreparedStatement preparedStatement;
	PreparedStatement preparedStatement1;
	PreparedStatement preparedStatement2;
	int update1=0, update2=0;
	int leaveFormId=1;
	Connection con;
	
	public LeaveFormDao() 
	{
		CreateConnectionDao conObj=new CreateConnectionDao();
		con=conObj.dbConnection();
	}
	
	public boolean applyLeave(String receivedFromDate, String receivedToDate,String receivedReason,String receivedRegno)
	{		
		try {
			preparedStatement = con
					.prepareStatement("insert into leaveform (studentid,dateofapply,dateofleavefrom,dateofleaveto,reason) values "
							+ "((select studentid from student where regno=?), now(),?,?,?)");
			preparedStatement.setString(1,receivedRegno);
			preparedStatement.setString(2,receivedFromDate);
			preparedStatement.setString(3,receivedToDate);
			preparedStatement.setString(4, receivedReason);
			update1 = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement1 =con.prepareStatement("select auto_increment from information_schema.tables where table_name='leaveform';");
			ResultSet resultSet = preparedStatement1.executeQuery();
			
			while(resultSet.next())
			 leaveFormId=resultSet.getInt("auto_increment")-1;
			preparedStatement1.close();
			
			preparedStatement2 = con.prepareStatement("insert into leaveapplied (studentid, leaveformid, acceptreject) values"
					+ "((select studentid from student where regno=?),?, ?)");
			
			preparedStatement2.setString(1,receivedRegno);
			preparedStatement2.setInt(2,leaveFormId);
			preparedStatement2.setString(3,"pending");
			update2 = preparedStatement2.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			System.out.println("LeaveFormDao : Problem in getting Data"+leaveFormId);
		}
		return (update1>0 && update2>0);
	}
}