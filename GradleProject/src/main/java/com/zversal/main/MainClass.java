package com.zversal.main;

import spark.Spark;

import java.util.Map;
import java.util.Set;

import javax.xml.ws.Response;

import com.google.gson.Gson;
import com.zversal.dao.StudentDao;
import com.zversal.pojo.Student;

class thread1 extends Thread
{
	public void run()
	{
		while(true)
		{
			
		}
	}
}


class thread2 extends Thread
{
	public void run()
	{
		while(true)
		{
			
		}
	}
}

class thread3 extends Thread
{
	public void run()
	{
		while(true)
		{
			
		}
	}
}





public class MainClass {

	public static void main(String[] args)
	{
/*
		Thread t1 = new Thread();
		Thread t2 = new Thread();
		Thread t3 = new Thread();
		
		
		t1.start();
		t2.start();
		t3.start();
		
		
		try
		{
		t1.join();
		t2.join();
		t3.join();
		}
		catch(Exception e)
		{
		   System.out.println(e);
		}
		
		
	*/	
		StudentDao daobj = new StudentDao();

		Spark.post("/login",(req,res)->{

			res.cookie("user_name", "ankush");
			res.cookie("pass_word", "pin123");


			/*
			 *---- FOR SESSION-----
		     req.session().attribute("user_name", "ankush");
		     req.session().attribute("pass_word", "pin123"); 
			 */

			return 1;
		});

		Spark.path("/api",()->{

			Spark.before("/*",(request, response) -> {

				Map<String,String> m = request.cookies();

				/*
				Set<String> sets = request.session().attributes();
				 */
				
				if(m.size()<=0)
				{

					System.out.println("Emptyyyyyy");
					//					response.status(401);
					Spark.halt(401, "Go away!");
					// no cookies , we need to login then 
				}


				String username =  request.cookie("user_name");
				String password =  request.cookie("pass_word");

				/*
				String username =  request.session().attribute("user_name"); 
				String password =   request.session().attribute("pass_word");
				 */
				if(!autheticate(username,password))
				{
					System.out.println("not");
					Spark.halt(401, "Authentication Failed!"); 
				}

			});


			Spark.get("/print", (req,res)->{
				return daobj.printAll();
			});

			Spark.get("/printcreate", (req,res)->{
				return daobj.printAllUsingCreate();
			});

			Spark.delete("/delete/:rollno",(req,res)->{
				daobj.delete(Integer.parseInt(req.params(":rollno")));
				return 1;
			});

			Spark.put("/update/*/*/*",(req,res)->{
				Student stemp = new Student();
				stemp.setId(Integer.parseInt(req.splat()[0]));
				stemp.setName(req.splat()[1]);
				stemp.setAge(Integer.parseInt(req.splat()[2]));

				return daobj.update(stemp);
			});

			Spark.post("/add",(req,res)->{
				Student stemp = new Student();
				stemp.setId(Integer.parseInt(req.queryParams("id")));
				stemp.setName(req.queryParams("name"));
				stemp.setAge(Integer.parseInt(req.queryParams("age")));

				System.out.println("Data in request.body is " + req.body() + " Ip is:- " + req.ip() + " Host:- " + req.host()+ " method:- " + req.requestMethod() + " schemea :-" );//+ req.scheme() + " Session is "+ req.session());
				System.out.println("Uri "+ req.uri()+ "Url " + req.url() + "Srvlet path " + req.servletPath() + " User agent " + req.userAgent());
				System.out.println("port is:- " + req.port() + " Protocol is:- " + req.protocol());
				System.out.println("Content type is :- " + req.contentType());
		//      System.out.println("Session is :- "+ req.session());
		//      System.out.println(res.bod);
				return daobj.add(stemp);
			});


			Spark.post("/dummy", (request,res)->{

				Student stemp = new Student();
				stemp.setAge(86);
				stemp.setId(78);
				stemp.setName("Atom");

				Gson g = new Gson();

				Map<String,String> maps = request.cookies();

				System.out.println("ip is " + request.ip());

				System.out.println("ip is " + request.contextPath());


				for(Map.Entry<String,String>m:maps.entrySet())
				{
					System.out.println(m.getKey().toString() + " " + m.getValue().toString());
				}

				Spark.halt(g.toJson(stemp));


				return 1;

				//return g.toJson(stemp);

			});
		});


		Spark.post("/logout",(req,res)->{
			//if(req.cook)
			res.removeCookie("user_name");
			res.removeCookie("pass_word");
			return 1;
		});
		

		/*
		Student s1 = new Student();
		s1.setId(1);
		s1.setName("Ankush");
		s1.setAge(28);

		Student s2 = new Student();
		s2.setId(2);
		s2.setName("Abhishek");
		s2.setAge(22); 

		Student s3 = new Student();
		s3.setId(2);
		s3.setName("Himalya");
		s3.setAge(23);
		 */

	}

	private static boolean autheticate(String username, String password) {

		if(username.equals("ankush") &&  password.equals("pin123"))
			return true;
		return true;



	}	
}
