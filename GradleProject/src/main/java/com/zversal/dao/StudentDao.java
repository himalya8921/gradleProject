package com.zversal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.zversal.main.MainClass;
import com.zversal.pojo.Student;

public class StudentDao {

	private static ConcurrentHashMap<Integer, String> productCache = new ConcurrentHashMap<Integer,String>();

	public String add(Student s) {
		try (Connection con = MainClass.connection.getConnection();
				PreparedStatement ps = con.prepareStatement("insert into student1 value(?,?,?)")) {
			ps.setInt(1, s.getId());
			ps.setString(2, s.getName());
			ps.setInt(3, s.getAge());

			if(ps.executeUpdate()>0)
			{
				addtocache(s.getId(),s.getName());
				return "Data Added successfully";
			}
			return "Data Not Added";
		} catch (Exception e) {
			System.out.println(e);
		}
		return "Data Not Added";
	}

	private void addtocache(int id, String name)
	{
		try
		{
			productCache.putIfAbsent(id, name);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void deletefromcache(int id)
	{
		try{
			if(productCache.containsKey(id)){
				String val = productCache.get(id);
				productCache.remove(id,val);
				System.out.println("Data deleted from Cache");	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	private void updateincache(int id,String name)
	{
		try
		{
			productCache.replace(id,name);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public String delete(int id) {
		try (Connection con = MainClass.connection.getConnection();
				PreparedStatement ps = con.prepareStatement("delete from student1 where id = ?")) {
			ps.setInt(1, id);
			System.out.println("Data Deleted Successfully !");
			if(ps.executeUpdate()>0)
			{
				deletefromcache(id);
				return "Successfully deleted !";
			}
			return "Not deleted !";	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Not deleted !";	
	}

	public ConcurrentHashMap<Integer, Student> getById(int id) {
		try (Connection con =MainClass.connection.getConnection();
				PreparedStatement ps = con.prepareStatement("select id,name,age  from student1 where id = ?")) {

			ConcurrentHashMap<Integer, Student> maps = new ConcurrentHashMap<Integer,Student>();
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Student temp = new Student();
				temp.setId(rs.getInt(1));
				temp.setName(rs.getString(2));
				temp.setAge(rs.getInt(3));
				maps.put(rs.getInt(1),temp);
			}
			return maps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String update(Student s) {
		try (Connection con =MainClass.connection.getConnection();
				PreparedStatement ps = con.prepareStatement("update student1 set name = ?, age = ? where id = ?;")) {
			ps.setString(1, s.getName());
			ps.setInt(2, s.getAge());
			ps.setInt(3, s.getId());
			System.out.println("Data updated Successfully ! ");
			if(ps.executeUpdate()>0)
			{
				updateincache(s.getId(), s.getName());
				return "Updated successfully";
			}
			return "Data Not Updated";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Data Not Updated";
	}

	public ConcurrentHashMap<Integer, Student> printAll() {
		try (Connection con = MainClass.connection.getConnection();
				PreparedStatement ps = con.prepareStatement("Select id,name,age from student1;");)
		{
			ConcurrentHashMap<Integer, Student> maps = new ConcurrentHashMap<Integer,Student>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Student temp = new Student();
				temp.setId(rs.getInt(1));
				temp.setName(rs.getString(2));
				temp.setAge(rs.getInt(3));
				maps.put(rs.getInt(1),temp);
			}
			return maps;
		}
		catch (Exception e) {
			System.out.println("In Print " + e);
		}
		return null;
	}

	public ArrayList<String> getnames() {
		ArrayList<String> lists = new ArrayList<String>();

		ConcurrentHashMap.KeySetView<Integer, String> keySetView = productCache.keySet();
		Iterator<Integer> iterator = keySetView.iterator();

		while(iterator.hasNext())
		{
			Integer key = iterator.next();
			lists.add(productCache.get(key));
		}
		//		for (Integer key : productCache.keySet()) 
		//		{ 
		//			lists.add(productCache.get(key));
		//		}
		return lists;
	}

	public ArrayList<Integer> getids() {

		ArrayList<Integer> lists = new ArrayList<Integer>();
		ConcurrentHashMap.KeySetView<Integer, String> keySetView = productCache.keySet();
		Iterator<Integer> iterator = keySetView.iterator();

		while(iterator.hasNext())
		{
			lists.add(iterator.next());
		}
		//		Set<Integer> keys = productCache.keySet();
		return lists;
	}
};
