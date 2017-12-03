package com.project.leaveod.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnectionDao {
	Connection connection;
	public Connection dbConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/leave_od_form","root","");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Class Driver not loaded");
		} 
		catch (SQLException e) {
			System.out.println("Connection Problem");
		}
		return connection;
	}
}