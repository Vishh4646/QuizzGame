package com.quizz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import com.connection.quizz.ConnectionProvider;

public class RetrieveStudentResults implements retrieveresultInterface
{
	
	ConnectionProvider connection= new ConnectionProvider();
	Scanner scanner = new Scanner(System.in);
	Connection con;
	
	public void dispStudentsSort()
	{
		try
		{
			con=connection.getCon();
			PreparedStatement preparedS = con.prepareStatement("select * from student  order by id asc");
			ResultSet resultset = preparedS.executeQuery();
			System.out.println("Sorting order of student by their id's: ");
			while(resultset.next())
			{			
				ArrayList al= new ArrayList();
				al.add(resultset.getInt(1));
				al.add(resultset.getString(2));
				al.add(resultset.getInt(3));
				System.out.println(al);
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		MainQuiz mainquiz = new MainQuiz();
		mainquiz.enterChoice();
	}
	
	
	public void getByID()
	{
		try
		{
			con=connection.getCon();
			PreparedStatement preparedS = con.prepareStatement("select * from student  where id=?");
			System.out.println("Enter id of student to view SCORE....");
			int input= scanner.nextInt();
			preparedS.setInt(1, input);
			ResultSet resultset = preparedS.executeQuery();
			if(resultset.next()==true)
			{
				System.out.println("id:  "+resultset.getInt(1)+ "|  name: "+resultset.getString(2)+"|    score: "+resultset.getInt(3));
				System.out.println("Your Score is: " +resultset.getInt(3));
				if(resultset.getInt(3) >8)
				{
					System.out.println("Your Grade is 'A'.");
				}else if(resultset.getInt(3) >=6 && resultset.getInt(3) <=8)
				{
					System.out.println("Your Grade is 'B'.");
				}else if(resultset.getInt(3) ==5)
				{
					System.out.println("Your Grade is 'C'.");
				}else
				{
					System.out.println("Fail");
				}
				MainQuiz mainquiz = new MainQuiz();
				mainquiz.enterChoice();		
			}
			else 
			{
				System.out.println("Id doesnt exist...Please enter valid id.....");
				System.out.println("===========================================");
				getByID();
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}		
}
	

