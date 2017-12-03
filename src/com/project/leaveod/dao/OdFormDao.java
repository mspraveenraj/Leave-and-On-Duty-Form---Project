package com.project.leaveod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OdFormDao {

	PreparedStatement preparedStatement;
	PreparedStatement preparedStatement1;
	PreparedStatement preparedStatement2;
	int update1=0, update2=0;
	int odFormId=1;
	Connection con;
	
	public OdFormDao() 
	{
		CreateConnectionDao conObj=new CreateConnectionDao();
		con=conObj.dbConnection();
	}
	
	public boolean applyOnduty(String receivedPermissionFrom, String receivedPermissionTo,String receivedReason,
										String receivedWorkAssignedBy,String receivedRegno)
	{		
		try {
			preparedStatement = con
					.prepareStatement("insert into odform (studentid,dateofapply,permissionfrom,permissionto,reason,workassignedby) values "
							+ "((select studentid from student where regno=?), now(),?,?,?,?)");
			preparedStatement.setString(1,receivedRegno);
			preparedStatement.setString(2,receivedPermissionFrom);
			preparedStatement.setString(3,receivedPermissionTo);
			preparedStatement.setString(4, receivedReason);
			preparedStatement.setString(5, receivedWorkAssignedBy);
			update1 = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement1 =con.prepareStatement("select auto_increment from information_schema.tables where table_name='odform';");
			ResultSet resultSet = preparedStatement1.executeQuery();
			
			while(resultSet.next())
			 odFormId=resultSet.getInt("auto_increment")-1;
			preparedStatement1.close();
			
			preparedStatement2 = con.prepareStatement("insert into odapplied (studentid, odformid, acceptreject) values"
					+ "((select studentid from student where regno=?),?, ?)");
			
			preparedStatement2.setString(1,receivedRegno);
			preparedStatement2.setInt(2,odFormId);
			preparedStatement2.setString(3,"pending");
			update2 = preparedStatement2.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			System.out.println("OndutyFormDao : Problem in getting Data");
		}
		return (update1>0 && update2>0);
	}
}