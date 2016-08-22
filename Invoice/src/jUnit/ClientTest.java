package jUnit;

import org.junit.Test;
import basicClasses.Client;
import org.junit.Assert;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ClientTest {
    
    Client client;
    
    @Test
    public void testClient(){
        client = new  Client();
        String name = "Feltz Printing Service";
        String addressLine1 = "639 Main St";
        String city = "Anchorage";
        String State = "AK";
        int zip = 99501;
        String email = "lpaprocki@hotmail.com";
        String contact = "Lenna Paprocki";
        String invoiceFrequency = "Weekly";
        String billingTerm = "Due on Recipt";
        String invoiceGrouping = "Project";
        client.setName(name);
        client.setAddressLine1(addressLine1);
        client.setCity(city);
        client.setState(State);
        client.setZip(zip);
        client.setEmail(email);
        client.setContact(contact);
        client.setInvoiceFrequency(invoiceFrequency);
        client.setBillingTerm(billingTerm);
        client.setInvoiceGrouping(invoiceGrouping);
        
        Assert.assertEquals(name, client.getName());
        Assert.assertEquals(addressLine1, client.getAddressLine1());
        Assert.assertEquals(city, client.getCity());
        Assert.assertEquals(State, client.getState());
        Assert.assertEquals(zip, client.getZip());
        Assert.assertEquals(email, client.getEmail());
        Assert.assertEquals(contact, client.getContact());
        Assert.assertEquals(invoiceFrequency, client.getInvoiceFrequency());
        Assert.assertEquals(billingTerm, client.getBillingTerm());
        Assert.assertEquals(invoiceGrouping, client.getInvoiceGrouping());
    }
}

class DriverClassClientTest{
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(ProjectTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
   }
    
}
