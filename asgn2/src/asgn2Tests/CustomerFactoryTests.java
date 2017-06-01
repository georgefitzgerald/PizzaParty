package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Seok Yoon
 *
 */
public class CustomerFactoryTests {
	// TO DO
	PickUpCustomer test1;
	
	DriverDeliveryCustomer test2;
	
	DroneDeliveryCustomer test3;
	
	
	@Before 
	public void initialize() throws CustomerException{
		test1 = new PickUpCustomer("Leo", "0433563076",5,4);
		test2 = new DriverDeliveryCustomer("George", "0402123456", 5,3);
		test3 = new DroneDeliveryCustomer("Tease", "0413513231", 2, 5);
	}
	
	//Check exception thrown for invalid customer code
	@Test (expected = CustomerException.class)
	public void invalidCodeTest() throws CustomerException{
		CustomerFactory.getCustomer("test", "Leo", "0433563076", 5, 4);
	}
	
	@Test (expected = CustomerException.class)
	public void invalidCodeNullTest() throws CustomerException{
		CustomerFactory.getCustomer("", "Leo", "0433563076", 5, 4);
	}
	
	@Test (expected = CustomerException.class)
	public void invalidCodeNumberTest() throws CustomerException{
		CustomerFactory.getCustomer("123", "Leo", "0433563076", 5, 4);
	}
	
	//Check correct customer is returned
	@Test 
	public void pickUpCustomer() throws CustomerException{
		assertEquals(test1.getCustomerType(), "PickUp");
	}
	
	@Test 
	public void driverCustomer() throws CustomerException{
		assertEquals(test2.getCustomerType(), "DriverDelivery");
	}
	
	@Test 
	public void droveCustomer() throws CustomerException{
		assertEquals(test3.getCustomerType(), "DroneDelivery");
	}
	
	//Different instances returned to check for not equal
	@Test 
	public void notEqualPickUpCustomer() throws CustomerException{
		Customer cus1;
		cus1 = CustomerFactory.getCustomer("PUC","Testing", "0412456789",5,6);
		
		Customer cus2;
		cus2 = CustomerFactory.getCustomer("PUC","Testing", "0412456789",5,6);
		
		assertNotEquals(cus1.hashCode(),cus2.hashCode());
		
	}
	
	@Test 
	public void notEqualDriveCustomer() throws CustomerException{
		Customer cus1;
		cus1 = CustomerFactory.getCustomer("DNC","Testing", "0412456789",5,6);
		
		Customer cus2;
		cus2 = CustomerFactory.getCustomer("DNC","Testing", "0412456789",5,6);
		
		assertNotEquals(cus1.hashCode(),cus2.hashCode());
		
	}
	
	@Test 
	public void notEqualDroneCustomer() throws CustomerException{
		Customer cus1;
		cus1 = CustomerFactory.getCustomer("DVC","Testing", "0412456789",5,6);
		
		Customer cus2;
		cus2 = CustomerFactory.getCustomer("DVC","Testing", "0412456789",5,6);
		
		assertNotEquals(cus1.hashCode(),cus2.hashCode());
		
	}
	
	//Check customer distance
	@Test
	public void pickUpDistance(){
		
		assertEquals(test1.getDeliveryDistance(),0,0);
	}
	
	@Test
	public void DriveDeliveryDistance(){
		double distance = test2.getLocationX()+test2.getLocationY();
		assertEquals(test2.getDeliveryDistance(),distance,0);
	}
	
	@Test
	public void DroneDeliveryDistance(){
		double distance = Math.sqrt((Math.pow(test3.getLocationX(), 2))+(Math.pow(test3.getLocationY(), 2)));
		assertEquals(test3.getDeliveryDistance(),distance,0);
	}
}
