package jUnit;

import basicClasses.Project;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class ProjectTest {
    
    @Test
    public void testProject(){
        Project project = new Project();
        String name = "Twitter Integration";    
        project.setName(name);    
        String status = "In Progress";  
        project.setStatus(status);              
        int budget = 300000;
        project.setBudget(budget);    
        String  managerName = "Amber Monarrez";
        project.setManagerName(managerName);
        String clientContact = "Graciela Ruta";
        project.setClientContact(clientContact);
        
        
        
        Assert.assertEquals(name, project.getName());
        Assert.assertEquals(managerName, project.getManagerName());
        Assert.assertEquals(budget, project.getBudget());
        Assert.assertEquals(clientContact, project.getClientContact());
        Assert.assertEquals(status, project.getStatus());
    }
}


class DriverClassProjectTest{
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(ProjectTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
   }
    
}
