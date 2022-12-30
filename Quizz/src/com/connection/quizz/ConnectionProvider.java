package com.connection.quizz;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider 
{
	Connection con;
	
	//Connection Method which return Connection Object 
	public Connection getCon()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb","root", "Vishal#4646");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}
}

