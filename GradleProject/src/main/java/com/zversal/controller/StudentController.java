package com.zversal.controller;

import java.util.concurrent.ConcurrentHashMap;
import com.zversal.dao.StudentDao;
import com.zversal.pojo.Student;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class StudentController{

	private static StudentDao daobj = new StudentDao();

	public static Route test = (Request req,Response res)->{
		System.out.println("TESTTTTTTTTT");
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		map.put("msg:", "Hello World");
		return map;
	};
	
	public static Route getallstudentinfo = (Request req,Response res)->{
		return daobj.printAll();
	};

	public static Route getByid = (Request req,Response res)->{
		try
		{
			return daobj.getById(Integer.parseInt(req.params(":id")));
		}
		catch(Exception e)
		{
			Spark.halt("Invalid Input !!");
		}
      return "Something is Wrong";
	};

	public static Route deletestudent = (Request req,Response res)->{
		try
		{
			return daobj.delete(Integer.parseInt(req.params(":rollno")));
		}
		catch(Exception e)
		{
			Spark.halt("Invalid Input !!");
		}
		return "Something is Wrong";
	};

	public static Route updatestudent = (Request req,Response res)->{
		Student stemp = new Student();
		stemp.setId(Integer.parseInt(req.queryParams("id")));
		stemp.setName(req.queryParams("name"));
		stemp.setAge(Integer.parseInt(req.queryParams("age")));
		return daobj.update(stemp);
	};

	public static Route addstudent = (Request req,Response res)->{
		Student stemp = new Student();
		stemp.setId(Integer.parseInt(req.queryParams("id")));
		stemp.setName(req.queryParams("name"));
		stemp.setAge(Integer.parseInt(req.queryParams("age")));
		return daobj.add(stemp);
	};
	
	public static Route getallnames = (Request req,Response res)->{
		return daobj.getnames();
	};

	public static Route getallids = (Request req,Response res)->{
		return daobj.getids();
	};


};
