package com.quizz;
import java.sql.*;
import java.util.Scanner;

import com.connection.quizz.ConnectionProvider;

public class Quiz implements quizzInterface
{
	ConnectionProvider connection= new ConnectionProvider();
	Connection con;
	Scanner scanner = new Scanner(System.in);
    int score=0;
    //method to retrieve random questions and to store into the Questions object
	public void getRandQue(int studentid) 
	{	
		
		try
		{
			con=connection.getCon();
			PreparedStatement preparedS = con.prepareStatement("select question,opt1,opt2,opt3,opt4,correctans from question  order by RAND() limit 10");
			ResultSet resultset = preparedS.executeQuery();
			while(resultset.next())
			{
				System.out.println(resultset.getString(1));//to get the selected data from our query
				System.out.println(resultset.getString(2));
				System.out.println(resultset.getString(3));
				System.out.println(resultset.getString(4));
				System.out.println(resultset.getString(5));
				System.out.println("==========================");
				System.out.println("Enter your Option no: ");
				int uans=scanner.nextInt();
				if(uans==resultset.getInt(6))
				{
					System.out.println("Correct");
					score++;
				}
				else
				{
					System.err.println("Wrong Answer");
				}
			}
			System.out.println("Your Score is: "+score);
			getGrade();
			storeScore(studentid);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	
	}
	
	public void getGrade()
	{
		if(score >8)
		{
			System.out.println("Your Grade is 'A'.");
		}else if(score >=6 && score <=8)
		{
			System.out.println("Your Grade is 'B'.");
		}else if(score ==5)
		{
			System.out.println("Your Grade is 'C'.");
		}else
		{
			System.out.println("Sorry, Your Fail Please try Again with New Registration");
		}		
	}

	public void storeScore(int studentid) 
	{
		int sid= studentid;
		try
		{
			con = connection.getCon();
			PreparedStatement preparedS = con.prepareStatement("update student set score=? where id=?");
			preparedS.setInt(1, score);
			preparedS.setInt(2, sid );
			preparedS.executeUpdate();
			MainQuiz mainobj = new MainQuiz();
			mainobj.enterChoice();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
