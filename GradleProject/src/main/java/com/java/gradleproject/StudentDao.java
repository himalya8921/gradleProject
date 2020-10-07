package com.java.gradleproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao
{
	
	public void add(Student s)
	{
		System.out.println("IN ADDDDD");
		
		try(Connection con = HikariConnection.getConnection();
				PreparedStatement ps = con.prepareStatement("insert into student1 value(?,?,?)");)
		{
			ps.setInt(1, s.getId());
			ps.setString(2, s.getName());
			ps.setInt(3, s.getAge());	 

			ps.executeUpdate();

			System.out.println("Data added successfully");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public void delete(int id)
	{
		try(Connection con = HikariConnection.getConnection();
				PreparedStatement ps = con.prepareStatement("delete from student1 where id = ?"))
		{
			ps.setInt(1,id);
			ps.executeUpdate();

			System.out.println("Data Deleted Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

	public void update(Student s)
	{
		try(Connection con = HikariConnection.getConnection();
				PreparedStatement ps = con.prepareStatement("update student1 set name = ?, age = ? where id = ?;"))
		{
			ps.setString(1,s.getName());
			ps.setInt(2,s.getAge());
			ps.setInt(3,s.getId());

			ps.executeUpdate();

			System.out.println("Data updated Successfully ! ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void printAll()
	{
		//		try
		try(Connection con = HikariConnection.getConnection();
				PreparedStatement ps = con.prepareStatement("Select * from student1;");
				ResultSet rs = ps.executeQuery()) 
		{
			while(rs.next())
			{
				System.out.println("Id :- " + rs.getInt(1) + " Name :- "+rs.getString(2) +" Age:- "+ rs.getInt(3));
			}
			System.out.println("All Data Displayed");

		} catch (Exception e) {
			// TODO Auto-generated catch block
		System.out.println("In Print "+e);
		}


	}



}
