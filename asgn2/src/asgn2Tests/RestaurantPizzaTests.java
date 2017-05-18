package asgn2Tests;

import asgn2Restaurant.PizzaRestaurant;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;

/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Person B
 *
 */
public class RestaurantPizzaTests {
	
	PizzaRestaurant rest;
	
	@Before //read in data structure
	public void RestaurantInstance() throws CustomerException, PizzaException, LogHandlerException{
		rest = new PizzaRestaurant();
		rest.processLog("C:\\Users\\You\\Documents\\PizzaParty\\asgn2\\logs\\20170101.txt");
	}
	
	//Constructor initialization
		//Creates an instance of object
		@Test
		public void CreateRestaurantInstance(){
			PizzaRestaurant rest1 = new PizzaRestaurant();
			assertNotEquals(rest1.toString(), null);
		}
		//separate instance created
		@Test 
		public void SeparateInstances(){
			PizzaRestaurant rest1 = new PizzaRestaurant();
			PizzaRestaurant rest2 = new PizzaRestaurant();
			assertNotEquals(rest1.toString(), rest2.toString());
		}
	
	//process log
		//Loghandler exception thrown when file doesn't exist
		@Test (expected=LogHandlerException.class)
		public void FileDoesntExistException() throws CustomerException, PizzaException, LogHandlerException{
			rest.processLog("");
		}
		//Pizza Exception thrown when pizza data is incorrect (10<quantity<1, time)
			//need my own log file for this. 
		//calls populatepizzadataset - check all pizzas at index aren't empty
		@Test
		public void populatesPizzaDataSet(){
			assertEquals(rest.getNumPizzaOrders(), 3);
		}
		//returns true when everythings correct
		@Test
		public void returnsTrueWhenSucces() throws CustomerException, PizzaException, LogHandlerException{
			rest = new PizzaRestaurant();
			String filename = "C:\\Users\\You\\Documents\\PizzaParty\\asgn2\\logs\\20170101.txt";
			assertEquals(rest.processLog(filename), true);
		}
		
	//Get pizza by index
		//gets correct pizza 
		@Test
		public void getsCorrectPizza() throws PizzaException{
			assertEquals(rest.getPizzaByIndex(0).getQuantity(), 2);
			assertEquals(rest.getPizzaByIndex(0).getPizzaType(), "Vegetarian");
		}
		//doesn't return the same pizzas 
		@Test
		public void createsSeparatePizzas() throws PizzaException{
			assertNotEquals(rest.getPizzaByIndex(0), rest.getPizzaByIndex(1));
			assertNotEquals(rest.getPizzaByIndex(1), rest.getPizzaByIndex(2));
			assertNotEquals(rest.getPizzaByIndex(0), rest.getPizzaByIndex(2));
		}
	//Get Number of Pizza orders
		//gets correct number 0 - 100
		@Test //3 orders
		public void GetsOrderCount3(){
			assertEquals(rest.getNumPizzaOrders(), 3, 0);
		}
		@Test //log 2
		public void GetOrderCountLog2() throws CustomerException, PizzaException, LogHandlerException{
			rest = new PizzaRestaurant();
			rest.processLog("C:\\Users\\You\\Documents\\PizzaParty\\asgn2\\logs\\20170102.txt");
			assertEquals(rest.getNumPizzaOrders(), 10, 0);
		}
		@Test //log3
		public void GetOrderCountLog3() throws CustomerException, PizzaException, LogHandlerException{
			rest = new PizzaRestaurant();
			rest.processLog("C:\\Users\\You\\Documents\\PizzaParty\\asgn2\\logs\\20170103.txt");
			assertEquals(rest.getNumPizzaOrders(), 100, 0);
		}
	//Gets total profit
		//correct with when profit 0 - 1000
		@Test 
		public void correctProfitLog1(){
			assertEquals(rest.getTotalProfit(), 30, 0);
		}
		@Test //
		public void correctProfitLog2() throws CustomerException, PizzaException, LogHandlerException{
			rest = new PizzaRestaurant();
			rest.processLog("C:\\Users\\You\\Documents\\ PizzaParty\\asgn2\\logs\\20170102.txt");
			assertEquals(rest.getTotalProfit(), 108, 0);
		}
		@Test //
		public void correctProfitLog3() throws CustomerException, PizzaException, LogHandlerException{
			rest = new PizzaRestaurant();
			rest.processLog("C:\\Users\\You\\Documents\\PizzaParty\\asgn2\\logs\\20170103.txt");
			assertEquals(rest.getTotalProfit(), 998, 0);
		}
		//correct profit with multiple different pizzas with various quantities. 
		//create own log file

}
