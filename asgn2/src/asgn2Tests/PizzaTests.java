package asgn2Tests;

import static org.junit.Assert.assertNotEquals;

import java.time.LocalTime;
import java.time.ZoneId;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.MargheritaPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Person B
 *
 */
public class PizzaTests {
	
	LocalTime otime;
	LocalTime dtime;

	@Before 
	public void createTimes(){
		LocalTime otime = LocalTime.of(21, 17, 0, 0);
		LocalTime dtime = LocalTime.of(21, 27, 0, 0);
	}
	
	//Initializes data structure on Pizza Abstract class
	@Test
	public void initDataStructure() {
/*
		String type = "PZM";
		Pizza pizzat = new Pizza(1, ltime, ltime, type,34);
		assertNotEquals(pizzat, null);*/
	}
	
	//Create margherita
	@Test
	public void CreateMargherita() throws PizzaException{
		MargheritaPizza marg = new MargheritaPizza(2, otime, dtime);
		//assertNotEquals(marg, null);
	}
	
	//Quantity Exceptions

		//Throws exception when quantity zero
		@Test (expected=PizzaException.class)//throws exception when too small quantity
		public void MargheritaExceptionQuantityZero() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(0, otime, dtime);
		}
		
		//Throws exception when quantity > 10
		@Test (expected=PizzaException.class)//throws exception when too small quantity
		public void MargheritaExceptionQuantityEleven() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(11, otime, dtime);
		}
		
		//Throws exception when quantity negative
		@Test (expected=PizzaException.class)//throws exception when too small quantity
		public void MargheritaExceptionQuantityNeg() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(-1, otime, dtime);
		}
		
		//Doesn't throw exception when quantity is one
		public void MargheritaQuantityOne() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
			assertNotEquals(marg, null);
		}
		
		//Doesn't throw exception when quantity is ten
		public void MargheritaQuantityTen() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(10, otime, dtime);
			assertNotEquals(marg, null);
		}
	
	//Ordertime Exceptions
		
		//Throws exception if ordertime less than min order time of 18
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime17() throws PizzaException{
			otime = LocalTime.of(17, 00, 0, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
	
		//Throws exception if ordertime just less than min order time of 18
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime175959() throws PizzaException{
			otime = LocalTime.of(17, 59, 59, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
		//Don't throw exception if ordertime is 18
		@Test 
		public void MargheritaExceptionOrdertime18() throws PizzaException{
			otime = LocalTime.of(18, 00, 0, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
	
		//Throws exception if ordertime greater than order time of 22
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime23() throws PizzaException{
			otime = LocalTime.of(23, 00, 0, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
		
		//Throws exception if ordertime is 22
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime22() throws PizzaException{
			otime = LocalTime.of(22, 00, 0, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
		
		//Throws exception if ordertime greater than order time of 22 by minutes
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime2201() throws PizzaException{
			otime = LocalTime.of(22, 01, 0, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
	
		//Throws exception if ordertime greater than order time of 22 by second
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime220001() throws PizzaException{
			otime = LocalTime.of(22, 00, 01, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
	
		//Doesn't throw exception when order time is 11pm 
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime215959() throws PizzaException{
			otime = LocalTime.of(21, 59, 59, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
	
	
	
	
	
	
	
}
