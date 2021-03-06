package asgn2Tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Seok Yoon
 * 
 *
 */
public class CustomerTests {
	PickUpCustomer test1;
	
	DriverDeliveryCustomer test2;
	
	DroneDeliveryCustomer test3;
	
	
	@Before 
	public void initialize() throws CustomerException{
		test1 = new PickUpCustomer("Leo", "0433563076",5,4);
		test2 = new DriverDeliveryCustomer("George", "0402123456", 5,3);
		test3 = new DroneDeliveryCustomer("Droning","0409333555",2,4);
	}
	
	//no name test
	@Test (expected=CustomerException.class)
	public void noNameTestPickUp() throws CustomerException{
		test1 = new PickUpCustomer("", "0123456789",1,2);
	}
	
	@Test (expected=CustomerException.class)
	public void noNameTestDriver() throws CustomerException{
		test2 = new DriverDeliveryCustomer("", "0123456789",1,2);
	}
	
	@Test (expected=CustomerException.class)
	public void noNameTestDrone() throws CustomerException{
		test3 = new DroneDeliveryCustomer("", "0123456789",1,2);
	}
	
	//long name constraint test
	@Test (expected=CustomerException.class)
	public void tooMuchLettersNamePickUp() throws CustomerException{
		test1 = new PickUpCustomer("This has got to be the longest name in the world because it's this long", "1234567890",1,2);
	}
	
	@Test (expected=CustomerException.class)
	public void tooMuchLettersNameDriver() throws CustomerException{
		test2 = new DriverDeliveryCustomer("This has to be the longest name in the world because it's this long", "1234567890",1,2);
	}
	
	@Test (expected=CustomerException.class)
	public void tooMuchLettersNameDrone() throws CustomerException{
		test3 = new DroneDeliveryCustomer("This is the longest name in the world because it's this long", "1234567890",1,2);
	}
	
	//short number constraint test
	@Test (expected=CustomerException.class)
	public void numberNotEnoughPickUp() throws CustomerException{
		test1 = new PickUpCustomer("Leo", "0123",1,2);
	}
	
	@Test (expected=CustomerException.class)
	public void numberNotEnoughDriver() throws CustomerException{
		test2 = new DriverDeliveryCustomer("Leo", "0",1,2);
	}
	
	@Test (expected=CustomerException.class)
	public void numberNotEnoughDrone() throws CustomerException{
		test3 = new DroneDeliveryCustomer("Leo", "1",1,2);
	}
	
	@Test (expected=CustomerException.class)
	public void errorNumberDigit() throws CustomerException{
		test1 = new PickUpCustomer("Leo", "1234567890", 1,2);
	}
	
	@Test (expected=CustomerException.class)
	public void errorNumberString() throws CustomerException{
		test1 = new PickUpCustomer("Leo", "0bcdefghij", 1,2);
	}
	
	@Test (expected=CustomerException.class)
	public void carDeliveryWhenInStore() throws CustomerException{
		test2 = new DriverDeliveryCustomer("Leo", "0434948394",0,0);
	}
	
	@Test (expected=CustomerException.class)
	public void droneDeliveryWhenInStore() throws CustomerException{
		test3 = new DroneDeliveryCustomer("Leo", "0434948394",0,0);
	}
	
	@Test(expected = CustomerException.class)
	public void deliveryTooFarAwayX() throws CustomerException{
		test2 = new DriverDeliveryCustomer("Loe", "0491839271", 11, 9);
	}
	
	@Test(expected = CustomerException.class)
	public void deliveryTooFarAwayY() throws CustomerException{
		test2 = new DriverDeliveryCustomer("Loe", "0491839271", 1, 11);
	}
	
	
	
	
	
	//Get name test
	@Test
	public void getNameTestPickUp(){
		String test = test1.getName();
		assertEquals("Leo",test);
	}
	@Test
	public void getNameTestDriver(){
		String test = test2.getName();
		assertEquals("George",test);
	}
	@Test
	public void getNameTestDrone(){
		String test = test3.getName();
		assertEquals("Droning",test);
	}
	
	//Get mobile number test
	@Test
	public void getMobileTestPickUp(){
		String test = test1.getMobileNumber();
		assertEquals("0433563076", test);
	}
	
	@Test
	public void getMobileTestDriver(){
		String test = test2.getMobileNumber();
		assertEquals("0402123456", test);
	}
	
	@Test
	public void getMobileTestDrone(){
		String test = test3.getMobileNumber();
		assertEquals("0409333555", test);
	}
	
	//Get customer type test
	@Test
	public void getCusTypePickUp(){
		String test = test1.getCustomerType();
		assertEquals("PickUp", test);
	}
	
	@Test
	public void getCusTypeDriver(){
		String test = test2.getCustomerType();
		assertEquals("DriverDelivery", test);
	}
	
	@Test
	public void getCusTypeDrone(){
		String test = test3.getCustomerType();
		assertEquals("DroneDelivery", test);
	}
	
	//customer X and Y location
	@Test
	public void getLocationXTestPickUp(){
		int x = test1.getLocationX();
		assertEquals(5,x);
	}
	
	@Test
	public void getLocationYTestPickUp(){
		int y = test1.getLocationY();
		assertEquals(4,y);
	}
	
	@Test
	public void getLocationXTestDriver(){
		int x = test2.getLocationX();
		assertEquals(5,x);
	}
	
	@Test
	public void getLocationYTestDriver(){
		int y = test2.getLocationY();
		assertEquals(3,y);
	}
	
	@Test
	public void getLocationXTestDrone(){
		int x = test3.getLocationX();
		assertEquals(2,x);
	}
	
	@Test
	public void getLocationYTestDrone(){
		int y = test3.getLocationY();
		assertEquals(4,y);
	}
	
	//delivery distance test
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
