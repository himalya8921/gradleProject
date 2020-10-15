package com.zversal.main;
import com.google.gson.Gson;
import com.zversal.connection.HikariConnection;
import com.zversal.controller.StudentController;
import spark.Spark;

public class MainClass {
	
	public static HikariConnection connection = new HikariConnection();
			
	public static void main(String[] args)
	{ 
		Gson gson = new Gson();

		Spark.get("/testing",StudentController.test,gson::toJson);

		Spark.get("/getall",StudentController.getallstudentinfo,gson::toJson);

		Spark.get("/getbyid/:id",StudentController.getByid,gson::toJson);

		Spark.delete("/delete/:rollno",StudentController.deletestudent,gson::toJson);

		Spark.put("/update",StudentController.updatestudent,gson::toJson);

		Spark.post("/add",StudentController.addstudent,gson::toJson);
		
		Spark.get("/getallnames",StudentController.getallnames,gson::toJson);
		
		Spark.get("/getallids",StudentController.getallids,gson::toJson);
		
	}

}