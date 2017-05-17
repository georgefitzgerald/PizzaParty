package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Person B 
 * 
 */
public class PizzaFactoryTests {
	
	LocalTime otime;
	LocalTime dtime;

	@Before 
	public void createTimes(){
		otime = LocalTime.of(21, 17, 0, 0);
		dtime = LocalTime.of(21, 27, 0, 0);
	}
	
	//check exception thrown when invalid pizza code
		@Test (expected=PizzaException.class)
		public void InvalidPizzaCodeException() throws PizzaException{
			PizzaFactory.getPizza("tits", 1, otime, dtime);
		}
		
		@Test (expected=PizzaException.class)
		public void InvalidPizzaCodeNullException() throws PizzaException{
			PizzaFactory.getPizza("", 1, otime, dtime);
		}
		
		@Test (expected=PizzaException.class)
		public void InvalidPizzaCodeNmbersException() throws PizzaException{
			PizzaFactory.getPizza("1234", 1, otime, dtime);
		}
	
	//check that correct subclasses are created for each pizza code based on type?!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		@Test
		public void CreateMargheritaClass() throws PizzaException{
			Pizza marg;
			marg = PizzaFactory.getPizza("PZM", 1, otime, dtime);
			assertEquals(marg.getPizzaType(), "Margherita");
		}
		
		@Test
		public void CreateMeatLoverClass() throws PizzaException{
			Pizza marg;
			marg = PizzaFactory.getPizza("PZL", 1, otime, dtime);
			assertEquals(marg.getPizzaType(), "MeatLovers");
		}
		
		@Test
		public void CreateVegetarianClass() throws PizzaException{
			Pizza marg;
			marg = PizzaFactory.getPizza("PZV", 1, otime, dtime);
			assertEquals(marg.getPizzaType(), "Vegetarian");
		}
		
	//different instances are returned for same pizza code and different pizza codes
		@Test
		public void VegeInstancesNotEqual() throws PizzaException{
			Pizza vege1;
			vege1 = PizzaFactory.getPizza("PZV", 1, otime, dtime);
			Pizza vege;
			vege = PizzaFactory.getPizza("PZV", 1, otime, dtime);
			assertNotEquals(vege1, vege);
		}
		
		@Test
		public void MeatLoversInstancesNotEqual() throws PizzaException{
			Pizza meat1;
			meat1 = PizzaFactory.getPizza("PZL", 1, otime, dtime);
			Pizza meat2;
			meat2 = PizzaFactory.getPizza("PZL", 1, otime, dtime);
			assertNotEquals(meat1, meat2);
		}
		
		@Test
		public void MargInstancesNotEqual() throws PizzaException{
			Pizza marg1;
			marg1 = PizzaFactory.getPizza("PZM", 1, otime, dtime);
			Pizza marg2;
			marg2 = PizzaFactory.getPizza("PZM", 1, otime, dtime);
			assertNotEquals(marg1, marg2);
		}
		
		@Test
		public void MargMeatVegeInstancesNotEqual() throws PizzaException{
			Pizza marg1;
			marg1 = PizzaFactory.getPizza("PZM", 1, otime, dtime);
			Pizza meat1;
			meat1 = PizzaFactory.getPizza("PZL", 1, otime, dtime);
			Pizza vege1;
			vege1 = PizzaFactory.getPizza("PZV", 1, otime, dtime);
			assertNotEquals(marg1, meat1);
			assertNotEquals(marg1, vege1);
			assertNotEquals(vege1, meat1);
		}
		
	//check that correct quantity, ordertime and deliverytime are parsed to instances - all three
		//how to check order time!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		@Test //Correct parameters for margherita class
		public void CheckParametersMargherita() throws PizzaException {
			Pizza marg1;
			otime = LocalTime.of(19, 22, 01, 00);
			dtime = LocalTime.of(19, 44);
			marg1 = PizzaFactory.getPizza("PZM", 6, otime, dtime);
			//assertEquals(marg1.orderTime, otime);
			assertEquals(marg1.getQuantity(), 6);
			marg1 = PizzaFactory.getPizza("PZM", 10, otime, dtime);
			assertEquals(marg1.getQuantity(), 10);
		}
		
		@Test //Correct parameters for meatlovers class
		public void CheckParametersMeatLovers() throws PizzaException {
			Pizza marg1;
			otime = LocalTime.of(19, 22, 01, 00);
			dtime = LocalTime.of(19, 44);
			marg1 = PizzaFactory.getPizza("PZL", 6, otime, dtime);
			//assertEquals(marg1.orderTime, otime);
			assertEquals(marg1.getQuantity(), 6);
			marg1 = PizzaFactory.getPizza("PZL", 10, otime, dtime);
			assertEquals(marg1.getQuantity(), 10);
		}
		
		@Test //Correct parameters for vegetarian class
		public void CheckParametersVegetarian() throws PizzaException {
			Pizza marg1;
			otime = LocalTime.of(19, 22, 01, 00);
			dtime = LocalTime.of(19, 44);
			marg1 = PizzaFactory.getPizza("PZV", 6, otime, dtime);
			//assertEquals(marg1.orderTime, otime);
			assertEquals(marg1.getQuantity(), 6);
			marg1 = PizzaFactory.getPizza("PZV", 10, otime, dtime);
			assertEquals(marg1.getQuantity(), 10);
		}
	
}
