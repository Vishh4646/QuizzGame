package com.student;
import com.connection.quizz.ConnectionProvider;
import com.quizz.Quiz;
import java.sql.*;
import java.util.Scanner;

public class Student implements studentInterface
{
	int id;
	String name;
	int score=0;
	Connection con;
	ConnectionProvider connection= new ConnectionProvider();
	Scanner sc = new Scanner(System.in);

	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public int getScore() 
	{
		return score;
	}
	public void setScore(int score) 
	{
		this.score = score;
	}
	
	
	public void addStudent()
	{
		System.out.println("Enter your unique id: ");
		int studentid =sc.nextInt();
		setId(studentid);
		
		System.out.println("Enter your name: ");
		String tempnm =sc.next();
		setName(tempnm);
		try
		{			
			con=connection.getCon();
			PreparedStatement preparedS = con.prepareStatement("insert into student(id,name)values(?,?)");
			preparedS.setInt(1, getId());
			preparedS.setString(2, getName());
			preparedS.executeUpdate();
			System.out.println("Student Registration Successfully....Complete the Quiz ");
			System.out.println("======================================================");
			Quiz quiz = new Quiz();
			quiz.getRandQue(studentid);
		}
		catch(Exception e)
		{
			try
			{
				con=connection.getCon();
				PreparedStatement preparedS = con.prepareStatement("Select id from student");
				ResultSet resultset=preparedS.executeQuery();
				while(resultset.next())
				{
					if(studentid==resultset.getInt(1))
					{
						System.out.println("Please enter different id for Student ");
						addStudent();
					}
				}
			}catch(Exception duplicate_id)
			{
				System.out.println(duplicate_id);
			}
			System.out.println(e);
		}	
	}
}
