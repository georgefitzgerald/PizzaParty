package asgn2Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Restaurant.LogHandler;
/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Seok Yoon
 */
public class LogHandlerCustomerTests {
	Customer cus;
	String path1;
	String path2;
	
	Path currentRelativePath = Paths.get("");
	String s = currentRelativePath.toAbsolutePath().toString();
	//Create Customer correctly parses string to getCustomer	
	//throws exceptions when invalid parameters (functionality of customerfactory.getCustomer)
		
	@Test (expected = LogHandlerException.class)//no commas
	public void invalidLineNoCommasCreateCustomer() throws CustomerException, LogHandlerException{
		String line = "21:17:0021:27:00Emma Brown0602547760DVC-10PZV5";
		cus = LogHandler.createCustomer(line);
	}
			
	@Test (expected = LogHandlerException.class)//one comma
	public void invalidLine1CommaCreateCustomer() throws CustomerException, LogHandlerException{
		String line = "21:17:00,21:27:00Emma Brown0602547760DVC-10PZV5";
		cus = LogHandler.createCustomer(line);
	}
			
	@Test (expected = LogHandlerException.class)//missing data
	public void LineMissingDataCreateCustomer() throws CustomerException, LogHandlerException{
		String line = "21:17:00,21:27:00,0602547760,-1,0,PZM,5";
		cus = LogHandler.createCustomer(line);
	}
	
	@Test (expected = CustomerException.class)// wrong quantity
	public void tooMuchLetterName() throws CustomerException, LogHandlerException{
		String line = "21:17:00,21:27:00,This is a very long Name cause it can be this long Brown,0602547760,DVC,-1,0,PZV,100";
		cus = LogHandler.createCustomer(line);
	}
	
	@Test (expected = CustomerException.class)// wrong quantity
	public void inCorrectPhoneNumber() throws CustomerException, LogHandlerException{
		String line = "21:17:00,21:27:00,Emma Brown,6602547760,DVC,-1,0,PZV,-1";
		cus = LogHandler.createCustomer(line);
	}
	//Correct Customer classes created 
	//Name
	@Test
	public void correctName() throws CustomerException, LogHandlerException{
		String line = "21:17:00,21:27:00,Emma Brown,0602547760,PUC,-1,0,PZM,5";
		cus = LogHandler.createCustomer(line);
		assertEquals(cus.getName(), "Emma Brown");
	}
	//Phone number
	@Test
	public void correctPhoneNumber() throws CustomerException, LogHandlerException{
		String line = "21:17:00,21:27:00,Emma Brown,0602547760,PUC,-1,0,PZM,5";
		cus = LogHandler.createCustomer(line);
		assertEquals(cus.getMobileNumber(), "0602547760");
	}
	
	//Type of customer
	@Test
	public void correctCreatePickUp() throws CustomerException, LogHandlerException{
		String line = "21:17:00,21:27:00,Emma Brown,0602547760,PUC,-1,0,PZM,5";
		cus = LogHandler.createCustomer(line);
		assertEquals(cus.getCustomerType(), "PickUp");
	}
	
	@Test
	public void correctCreateDrive() throws CustomerException, LogHandlerException{
		String line = "21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,PZM,5";
		cus = LogHandler.createCustomer(line);
		assertEquals(cus.getCustomerType(), "DriverDelivery");
	}
	
	@Test
	public void correctCreateDrone() throws CustomerException, LogHandlerException{
		String line = "21:17:00,21:27:00,Emma Brown,0602547760,DNC,-1,0,PZM,5";
		cus = LogHandler.createCustomer(line);
		assertEquals(cus.getCustomerType(), "DroneDelivery");
	}
	
	//Correct parameters parsed for location
	//Pick Up
	@Test
	public void correctLocationPickUpX() throws CustomerException, LogHandlerException{
		String line = "21:00:00,21:35:00,Oroku Saki,0111222333,PUC,0,0,PZL,3";
		cus = LogHandler.createCustomer(line);
		assertEquals(cus.getLocationX(), 0);
	}
	@Test
	public void correctLocationPickUpY() throws CustomerException, LogHandlerException{
		String line = "21:00:00,21:35:00,Oroku Saki,0111222333,PUC,0,0,PZL,3";
		cus = LogHandler.createCustomer(line);
		assertEquals(cus.getLocationX(), 0);
	}
	//Driver
	@Test
	public void correctLocationDriveX() throws CustomerException, LogHandlerException{
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		cus = LogHandler.createCustomer(line);
		assertEquals(cus.getLocationX(), 5);
	}
	@Test
	public void correctLocationDriveY() throws CustomerException, LogHandlerException{
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		cus = LogHandler.createCustomer(line);
		assertEquals(cus.getLocationX(), 5);
	}
	//Drone
	@Test
	public void correctLocationDroneX() throws CustomerException, LogHandlerException{
		String line = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1";
		cus = LogHandler.createCustomer(line);
		assertEquals(cus.getLocationX(), 3);
	}
	@Test
	public void correctLocationDroneY() throws CustomerException, LogHandlerException{
		String line = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1";
		cus = LogHandler.createCustomer(line);
		assertEquals(cus.getLocationY(), 4);
	}
				
	//PopulateDataset - correctly reads a file into a customer array 
	//throws customer exception when invalid parameters - quantity, times... 
	//throws loghandler exception when file doesn't exist. 
	@Test (expected=LogHandlerException.class)
	public void filedoesntExistException() throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset("invalidFilePath");
	}

	//returns different arrays - for same file and different files
	@Test //same file
	public void SameFileDifferentInstances() throws CustomerException, LogHandlerException{
		ArrayList<Customer> cusArr1;
		ArrayList<Customer> cusArr2;
		path2 = s + "\\logs\\20170102.txt";
		cusArr1 = LogHandler.populateCustomerDataset(path2);
		cusArr2 = LogHandler.populateCustomerDataset(path2);
		assertNotEquals(cusArr1.toString(), cusArr2.toString());
	}
	@Test //different file
	public void DifferentFileDifferentInstances() throws CustomerException, LogHandlerException{
		ArrayList<Customer> cusArr1;
		ArrayList<Customer> cusArr2;
		path1 = s + "\\logs\\20170101.txt";
		path2 = s + "\\logs\\20170102.txt";
		cusArr1 = LogHandler.populateCustomerDataset(path1);
		cusArr2 = LogHandler.populateCustomerDataset(path2);
		assertNotEquals(cusArr1, cusArr2);
	}
		
	//calling creatCustomer method 
	@Test
	public void PopulateDataCallsCreateCustomer() throws CustomerException, LogHandlerException{
		ArrayList<Customer> cusArr1;
		path2 = s + "\\logs\\20170102.txt";
		cusArr1 = LogHandler.populateCustomerDataset(path2);
		assertEquals("Emma Brown", cusArr1.get(0).getName());
		assertEquals("Lucas Anderson", cusArr1.get(1).getName());
	}	
}
