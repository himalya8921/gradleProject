package com.java.gradleproject;

public class MainClass {

	public static void main(String[] args)
	{
			
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
      
      
      
      StudentDao daobj = new StudentDao();
     
      daobj.add(s1);
      
      daobj.printAll();
      daobj.add(s2);
      
      daobj.printAll();
      daobj.delete(1);
      
      daobj.printAll();
      daobj.update(s3);
      
      daobj.printAll();
      

    
	}	
}
