package com.zversal.dao;

import spark.Spark;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zversal.connection.GetConnection;
import com.zversal.connection.HikariConnection;
import com.zversal.pojo.Student;

public class StudentDao
{
	public int add(Student s)
	{
		
		try(Connection con =HikariConnection.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement("insert into student1 value(?,?,?)"))
		{
			ps.setInt(1, s.getId());
			ps.setString(2, s.getName());
			ps.setInt(3, s.getAge());	 
			System.out.println("Data added successfully"); 
			return ps.executeUpdate();
            
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public int delete(int id)
	{
		try(Connection con = HikariConnection.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement("delete from student1 where id = ?"))
		{
			ps.setInt(1,id);
			
			System.out.println("Data Deleted Successfully !");
			return ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} 
      return 0;
	}

	public int update(Student s)
	{
		try(Connection con = HikariConnection.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement("update student1 set name = ?, age = ? where id = ?;"))
		{
			ps.setString(1,s.getName());
			ps.setInt(2,s.getAge());
			ps.setInt(3,s.getId());


			System.out.println("Data updated Successfully ! ");
			ps.executeUpdate();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
      return 0;
	}

	public int printAll()
	{
		try(Connection con = HikariConnection.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement("Select * from student1;");
				ResultSet rs = ps.executeQuery()) 
		{
			while(rs.next())
			{
				System.out.println("Id :- " + rs.getInt(1) + " Name :- "+rs.getString(2) +" Age:- "+ rs.getInt(3));
			}
			System.out.println("All Data Displayed");

		} catch (Exception e) {
		System.out.println("In Print "+e);
		}
      return 1;
	}
	
	public int printAllUsingCreate()
	{
		try(Connection con = HikariConnection.getInstance().getConnection();
				Statement ps = con.createStatement();
				ResultSet rs = ps.executeQuery("Select * from student1;")) 
		{
			while(rs.next())
			{
				System.out.println("Id :- " + rs.getInt(1) + " Name :- "+rs.getString(2) +" Age:- "+ rs.getInt(3));
			}
			System.out.println("All Data Displayed");

		} catch (Exception e) {
		System.out.println("In Print "+e);
		}
      return 1;
	}



}
