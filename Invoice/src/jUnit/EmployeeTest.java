package jUnit;

import basicClasses.Employee;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class EmployeeTest {
    
    @Test
    public void testEmployee(){
        Employee emp = new Employee();     
        String name = "Carmelina Lindall";
        String title = "Developer";
        int billRate =  100;
        String role = "Software Engineer";
        emp.setName(name);
        emp.setTitle(title);
        emp.setBillRate(billRate);
        emp.setEmpRole(role);
                
        
        Assert.assertEquals(name, emp.getName());
        Assert.assertEquals(title, emp.getTitle());
        Assert.assertEquals(billRate, emp.getBillRate());
        Assert.assertEquals(role, emp.getEmpRole());
    }
}


class DriverClassEmployeeTest{
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(EmployeeTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
   }
    
}