package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.ZoneId;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.VegetarianPizza;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author George Fitzgerald
 * 
 */
public class PizzaTests {
	
	LocalTime otime;
	LocalTime dtime;

	@Before 
	public void createTimes(){
		otime = LocalTime.of(21, 17, 0, 0);
		dtime = LocalTime.of(21, 27, 0, 0);
	}
	
	//Initializes data structure on Pizza Abstract class

	
	//Create margherita
		@Test
		public void CreateMargherita() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(2, otime, dtime);
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
		
		//Throws exception if ordertime less than min order time of 19
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime17() throws PizzaException{
			otime = LocalTime.of(17, 00, 0, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
	
		//Throws exception if ordertime just less than min order time of 10
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime175959() throws PizzaException{
			otime = LocalTime.of(18, 59, 59, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
		
		//Don't throw exception if ordertime is 19
		@Test 
		public void MargheritaExceptionOrdertime18() throws PizzaException{
			otime = LocalTime.of(19, 00, 0, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
			
		//Throws exception if ordertime is 23
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime23() throws PizzaException{
			otime = LocalTime.of(23, 00, 0, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
		
		//Throws exception if ordertime greater than order time of 23 by minutes
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime2301() throws PizzaException{
			otime = LocalTime.of(23, 01, 0, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
	
		//Throws exception if ordertime greater than order time of 23 by second
		@Test (expected=PizzaException.class)
		public void MargheritaExceptionOrdertime230001() throws PizzaException{
			otime = LocalTime.of(23, 00, 01, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
	
		//Doesn't throw exception when order time is 10:59:59pm 
		@Test 
		public void MargheritaExceptionOrdertime225959() throws PizzaException{
			otime = LocalTime.of(22, 59, 59, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
		
		//Throws exception if delivery time is an hour and ten minutes greater than ordertime 
		@Test (expected=PizzaException.class)
		public void PizzaExceptionCookedToolong() throws PizzaException{
			otime = LocalTime.of(19, 00, 00, 0);
			dtime = LocalTime.of(20, 10, 00, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
	
		@Test (expected=PizzaException.class)
		public void PizzaExceptionCookedOneSecToolong() throws PizzaException{
			otime = LocalTime.of(19, 00, 00, 0);
			dtime = LocalTime.of(20, 10, 01, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
		
		@Test (expected=PizzaException.class)
		public void PizzaExceptionCookedWayToolong() throws PizzaException{
			otime = LocalTime.of(19, 00, 00, 0);
			dtime = LocalTime.of(20, 50, 01, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
		
		
		@Test 
		public void PizzaExceptionCookedAlmostToolong() throws PizzaException{
			otime = LocalTime.of(19, 00, 00, 0);
			dtime = LocalTime.of(20, 9, 59, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}
		
		//don't throw exception if ordertime is before 11pm but delivery is after
		@Test 
		public void DeliveryAfterClosing() throws PizzaException{
			otime = LocalTime.of(22, 40, 00, 0);
			dtime = LocalTime.of(23, 40, 00, 0);
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
		}

		
		
	//Calculate cost per pizza and getcostperpizza
		
		@Test //check cost for margherita is correct
		public void MargheritaCost() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
			marg.calculateCostPerPizza();
			assertEquals(marg.getCostPerPizza(), 1.5, 0);
		}
		
		@Test //check cost for meatlovers is correct
		public void MeatLoversCost() throws PizzaException{
			MeatLoversPizza marg = new MeatLoversPizza(1, otime, dtime);
			marg.calculateCostPerPizza();
			assertEquals(marg.getCostPerPizza(), 5, 0);
		}
		
		@Test //check cost for vege is correct
		public void VegetarianCost() throws PizzaException{
			VegetarianPizza marg = new VegetarianPizza(1, otime, dtime);
			marg.calculateCostPerPizza();
			assertEquals(marg.getCostPerPizza(), 5.5, 0);
		}
	
	//Gets correct price charged to customer for each pizza
		
		@Test //check price for margherita is correct
		public void MargheritaPrice() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
			assertEquals(marg.getPricePerPizza(), 8, 0);
		}
		//check price of other pizzas
		@Test //check price for meatlover is correct
		public void MeatLoverPrice() throws PizzaException{
			MeatLoversPizza marg = new MeatLoversPizza(1, otime, dtime);
			assertEquals(marg.getPricePerPizza(), 12, 0);
		}
		@Test //check price for vege is correct
		public void VegetarianPrice() throws PizzaException{
			VegetarianPizza marg = new VegetarianPizza(1, otime, dtime);
			assertEquals(marg.getPricePerPizza(), 10, 0);
		}
		
	//Gets correct quantity
		
		@Test //Check quantity 1 margherita is correct
		public void MargheritaQuantity1() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
			assertEquals(marg.getQuantity(), 1, 0);
		}
		
		@Test //Check quantity 2 margherita is correct
		public void MargheritaQuantity2() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(2, otime, dtime);
			assertEquals(marg.getQuantity(), 2, 0);
		}
		
		@Test //Check quantity 10 margherita is correct
		public void MargheritaQuantity10() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(10, otime, dtime);
			assertEquals(marg.getQuantity(), 10, 0);
		}
		
		@Test //check quantity meatlover is correct
		public void MeatLoverQuantity1() throws PizzaException{
			MeatLoversPizza marg = new MeatLoversPizza(1, otime, dtime);
			assertEquals(marg.getQuantity(), 1, 0);
		}
		
		@Test //check quantity 2 meatlover is correct
		public void MeatLoverQuantity2() throws PizzaException{
			MeatLoversPizza marg = new MeatLoversPizza(2, otime, dtime);
			assertEquals(marg.getQuantity(), 2, 0);
		}
		
		@Test //check quantity 10 meatlover is correct
		public void MeatLoverQuantity10() throws PizzaException{
			MeatLoversPizza marg = new MeatLoversPizza(10, otime, dtime);
			assertEquals(marg.getQuantity(), 10, 0);
		}
		
		@Test //check quantity vege is correct
		public void VegetarianQuantity1() throws PizzaException{
			VegetarianPizza marg = new VegetarianPizza(1, otime, dtime);
			assertEquals(marg.getQuantity(), 1, 0);
		}
		
		@Test //check quantity 2 vege is correct
		public void VegetarianQuantity2() throws PizzaException{
			VegetarianPizza marg = new VegetarianPizza(2, otime, dtime);
			assertEquals(marg.getQuantity(), 2, 0);
		}
		
		@Test //check quantity 10 vege is correct
		public void VegetarianQuantity10() throws PizzaException{
			VegetarianPizza marg = new VegetarianPizza(10, otime, dtime);
			assertEquals(marg.getQuantity(), 10, 0);
		}
		
		
		
	//Get order price commented out until price is corrected
		
		@Test //check order price for 1 margherita is correct
		public void MargheritaOrderPrice1() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
			assertEquals(marg.getOrderPrice(), 8, 0);
		}
		
		@Test //check order price for 2 margherita is correct
		public void MargheritaOrderPrice2() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(2, otime, dtime);
			assertEquals(marg.getOrderPrice(), 16, 0);
		}
		
		@Test //check order price for 10 margherita is correct
		public void MargheritaOrderPrice10() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(10, otime, dtime);
			assertEquals(marg.getOrderPrice(), 80, 0);
		}
		
		@Test //check order for meatlover is correct
		public void MeatLoverOrderPrice1() throws PizzaException{
			MeatLoversPizza marg = new MeatLoversPizza(1, otime, dtime);
			assertEquals(marg.getOrderPrice(), 12, 0);
		}
		
		@Test //check order for 2 meatlover is correct
		public void MeatLoverOrderPrice2() throws PizzaException{
			MeatLoversPizza marg = new MeatLoversPizza(2, otime, dtime);
			assertEquals(marg.getOrderPrice(), 24, 0);
		}
		
		@Test //check order for 10 meatlover is correct
		public void MeatLoverOrderPrice10() throws PizzaException{
			MeatLoversPizza marg = new MeatLoversPizza(10, otime, dtime);
			assertEquals(marg.getOrderPrice(), 120, 0);
		}
		
		@Test //check order for vege is correct
		public void VegetarianOrderPrice1() throws PizzaException{
			VegetarianPizza marg = new VegetarianPizza(1, otime, dtime);
			assertEquals(marg.getOrderPrice(), 10, 0);
		}
		
		@Test //check order for 2 vege is correct
		public void VegetarianOrderPrice2() throws PizzaException{
			VegetarianPizza marg = new VegetarianPizza(2, otime, dtime);
			assertEquals(marg.getOrderPrice(), 20, 0);
		}
		
		@Test //check order for 10 vege is correct
		public void VegetarianOrderPrice10() throws PizzaException{
			VegetarianPizza marg = new VegetarianPizza(10, otime, dtime);
			assertEquals(marg.getOrderPrice(), 100, 0);
		}
		
	// Get Order Profit insert correct pricing when price works
		
		@Test //check order Profit for 1 margherita is correct
		public void MargheritaOrderProfit1() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
			assertEquals(marg.getOrderProfit(), 6.5, 0);
		}
		
		@Test //check order Profit for 2 margherita is correct
		public void MargheritaOrderProfit2() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(2, otime, dtime);
			assertEquals(marg.getOrderProfit(), 13, 0);
		}
		
		@Test //check order Profit for 10 margherita is correct
		public void MargheritaOrderProfit10() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(10, otime, dtime);
			assertEquals(marg.getOrderProfit(), 65, 0);
		}
		
	//Contains Topping - check if it does or doesn't contain
		
		@Test //check margherita contains correct toppings
		public void CheckMargheritaToppings() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
			assertEquals(marg.containsTopping(PizzaTopping.CHEESE), true);
			assertEquals(marg.containsTopping(PizzaTopping.TOMATO), true);
		}
		
		@Test //check margherita doesn't contain any other toppings
		public void CheckMargheritaDoesntHaveToppings() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
			assertEquals(marg.containsTopping(PizzaTopping.BACON), false);
			assertEquals(marg.containsTopping(PizzaTopping.CAPSICUM), false);
			assertEquals(marg.containsTopping(PizzaTopping.EGGPLANT), false);
			assertEquals(marg.containsTopping(PizzaTopping.MUSHROOM), false);
			assertEquals(marg.containsTopping(PizzaTopping.PEPPERONI), false);
			assertEquals(marg.containsTopping(PizzaTopping.SALAMI), false);
		}
		
		@Test //check vege contains correct toppings
		public void CheckVegeContainsToppings() throws PizzaException{
			VegetarianPizza marg = new VegetarianPizza(1, otime, dtime);
			assertEquals(marg.containsTopping(PizzaTopping.CHEESE), true);
			assertEquals(marg.containsTopping(PizzaTopping.TOMATO), true);
			assertEquals(marg.containsTopping(PizzaTopping.MUSHROOM), true);
			assertEquals(marg.containsTopping(PizzaTopping.CAPSICUM), true);
			assertEquals(marg.containsTopping(PizzaTopping.EGGPLANT), true);
		}
		
		@Test //check vege doesnt contains toppings
		public void CheckVegeDoesntHaveToppings() throws PizzaException{
			VegetarianPizza marg = new VegetarianPizza(1, otime, dtime);
			assertEquals(marg.containsTopping(PizzaTopping.BACON), false);
			assertEquals(marg.containsTopping(PizzaTopping.PEPPERONI), false);
			assertEquals(marg.containsTopping(PizzaTopping.SALAMI), false);
		}
		
		@Test //check meatlovers contains correct toppings
		public void CheckMeatLovContainsToppings() throws PizzaException{
			MeatLoversPizza marg = new MeatLoversPizza(1, otime, dtime);
			assertEquals(marg.containsTopping(PizzaTopping.CHEESE), true);
			assertEquals(marg.containsTopping(PizzaTopping.TOMATO), true);
			assertEquals(marg.containsTopping(PizzaTopping.BACON), true);
			assertEquals(marg.containsTopping(PizzaTopping.PEPPERONI), true);
			assertEquals(marg.containsTopping(PizzaTopping.SALAMI), true);
		}
		
		@Test //check meatlovers doesnt contains correct toppings
		public void CheckMeatLovDoesntHaveToppings() throws PizzaException{
			MeatLoversPizza marg = new MeatLoversPizza(1, otime, dtime);
			assertEquals(marg.containsTopping(PizzaTopping.MUSHROOM), false);
			assertEquals(marg.containsTopping(PizzaTopping.CAPSICUM), false);
			assertEquals(marg.containsTopping(PizzaTopping.EGGPLANT), false);
		}
		
		
	//Get pizza type
		
		@Test //Margerita has correct type
		public void CheckMargheritaType() throws PizzaException{
			MargheritaPizza marg = new MargheritaPizza(1, otime, dtime);
			assertEquals("Margherita", marg.getPizzaType());
		}
		
		@Test //MeatLover has correct type
		public void CheckMeatLoverType() throws PizzaException{
			MeatLoversPizza marg = new MeatLoversPizza(1, otime, dtime);
			assertEquals("Meat Lovers", marg.getPizzaType());
		}
		
		@Test //Vegetarian has correct type
		public void CheckVegetarianType() throws PizzaException{
			VegetarianPizza marg = new VegetarianPizza(1, otime, dtime);
			assertEquals("Vegetarian", marg.getPizzaType());
		}
		
	
}
