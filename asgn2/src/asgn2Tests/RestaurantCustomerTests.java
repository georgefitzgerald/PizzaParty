package asgn2Tests;

import asgn2Restaurant.PizzaRestaurant;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Person A
 */
public class RestaurantCustomerTests {
	// TO DO
	PizzaRestaurant cusRes;
	
	@Before //read in data structure
	public void RestaurantInstance() throws CustomerException, PizzaException, LogHandlerException{
		cusRes = new PizzaRestaurant();
		cusRes.processLog("C:\\Users\\You\\Documents\\PizzaParty\\asgn2\\logs\\20170101.txt");
	}
	
	//Constructor initialization
	//Creates an instance of object
	@Test
	public void CreateRestaurantInstance(){
		PizzaRestaurant cusRes1 = new PizzaRestaurant();
		assertNotEquals(cusRes1.toString(), null);
	}
	
	//separate instance created
	@Test 
	public void SeparateInstances(){
		PizzaRestaurant cusRes1 = new PizzaRestaurant();
		PizzaRestaurant cusRes2 = new PizzaRestaurant();
		assertNotEquals(cusRes1.toString(), cusRes2.toString());
	}
	
	//process log
	//Loghandler exception thrown when file doesn't exist
	@Test (expected=LogHandlerException.class)
	public void FileDoesntExistException() throws CustomerException, PizzaException, LogHandlerException{
		cusRes.processLog("");
	}
	
	//Customer Exception thrown when customer data is incorrect (10<quantity<1, time)
		//need my own log file for this. 
	//calls populatecustomerdataset - check all customers at index aren't empty
	@Test
	public void populatesCustomerDataSet(){
		assertEquals(cusRes.getNumCustomerOrders(), 3);
	}
	
	//returns true when everythings correct
	@Test
	public void returnsTrueWhenSucces() throws CustomerException, PizzaException, LogHandlerException{
		//cusRes = new PizzaRestaurant();
		String filename = "C:\\Users\\You\\Documents\\PizzaParty\\asgn2\\logs\\20170101.txt";
		assertEquals(cusRes.processLog(filename), true);
	}
	
	//Get customer by index
	//gets correct customer
	@Test
	public void getsCorrectCustomer() throws CustomerException{
		assertEquals(cusRes.getCustomerByIndex(0).getCustomerType(), "DriverDelivery");
		assertEquals(cusRes.getCustomerByIndex(0).getName(), "Casey Jones");
	}
	//doesn't return the same customer 
	@Test
	public void createsSeparateCustomer() throws CustomerException{
		assertNotEquals(cusRes.getCustomerByIndex(0), cusRes.getCustomerByIndex(1));
		assertNotEquals(cusRes.getCustomerByIndex(1), cusRes.getCustomerByIndex(2));
		assertNotEquals(cusRes.getCustomerByIndex(0), cusRes.getCustomerByIndex(2));
	}
	
	//Get customer location
	//Log1
	@Test
	public void customerLocationLog1() throws CustomerException{
		double loc = Math.abs(cusRes.getCustomerByIndex(0).getLocationX() + Math.abs(cusRes.getCustomerByIndex(0).getLocationY()));
		assertEquals(cusRes.getCustomerByIndex(0).getDeliveryDistance(), loc, 0);
	}

	//Log2
	@Test
	public void customerLocationLog2() throws CustomerException, PizzaException, LogHandlerException{
		cusRes.processLog("C:\\Users\\You\\Documents\\PizzaParty\\asgn2\\logs\\20170102.txt");
		double loc = 0;
		assertEquals(cusRes.getCustomerByIndex(3).getDeliveryDistance(), loc, 0);
	}
	
	//Log3
	@Test
	public void customerLocationLog3() throws CustomerException, PizzaException, LogHandlerException{
		cusRes.processLog("C:\\Users\\You\\Documents\\PizzaParty\\asgn2\\logs\\20170103.txt");
		double loc = Math.sqrt((Math.pow(cusRes.getCustomerByIndex(2).getLocationX(), 2))+(Math.pow(cusRes.getCustomerByIndex(2).getLocationY(), 2)));
		assertEquals(cusRes.getCustomerByIndex(2).getDeliveryDistance(), loc, 0);
	}
	
	//get Total delivery distance
	@Test
	public void totalDeliveryDistanceLog1(){
		
	}
	//testing reset Details
	@Test
	public void resetData() throws CustomerException, PizzaException, LogHandlerException{
		cusRes.processLog("C:\\Users\\Leo\\Documents\\PizzaParty\\asgn2\\logs\\20170101.txt");
		cusRes.resetDetails();
		assertEquals(cusRes.getNumCustomerOrders(),0);
		
	}
}
