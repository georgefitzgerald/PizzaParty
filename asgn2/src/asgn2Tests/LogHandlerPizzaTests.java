package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Person B
* 
*/
public class LogHandlerPizzaTests {
	
			Pizza pizza;
	//Create Pizza correctly parses string to getpizza	
		//throws exceptions when invalid parameters (functionality of pizzafactory.getPizza)
		
			@Test (expected = LogHandlerException.class)//no commas
			public void invalidLineNoCommasCreatePizza() throws PizzaException, LogHandlerException{
				String line = "21:17:0021:27:00Emma Brown0602547760DVC-10PZV5";
				pizza = LogHandler.createPizza(line);
			}
			
			@Test (expected = LogHandlerException.class)//one comma
			public void invalidLine1CommaCreatePizza() throws PizzaException, LogHandlerException{
				String line = "21:17:00,21:27:00Emma Brown0602547760DVC-10PZV5";
				pizza = LogHandler.createPizza(line);
			}
			
			@Test (expected = LogHandlerException.class)//missing data
			public void LineMissingDataCreatePizza() throws PizzaException, LogHandlerException{
				String line = "21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,5";
				pizza = LogHandler.createPizza(line);
			}
			
			@Test (expected = PizzaException.class)// wrong code
			public void InocrrectCodeCreatePizza() throws PizzaException, LogHandlerException{
				String line = "21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,KKK,5";
				pizza = LogHandler.createPizza(line);
			}
			
			@Test (expected = PizzaException.class)// wrong quantity
			public void InocrrectQuantity100CreatePizza() throws PizzaException, LogHandlerException{
				String line = "21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,KKK,100";
				pizza = LogHandler.createPizza(line);
			}
			
			@Test (expected = PizzaException.class)// wrong quantity
			public void InocrrectQuantity1CreatePizza() throws PizzaException, LogHandlerException{
				String line = "21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,KKK,-1";
				pizza = LogHandler.createPizza(line);
			}
			
			@Test (expected = PizzaException.class)// wrong time
			public void InocrrectTime1CreatePizza() throws PizzaException, LogHandlerException{
				String line = "03:17:00,05:27:00,Emma Brown,0602547760,DVC,-1,0,KKK,-1";
				pizza = LogHandler.createPizza(line);
			}
			
		//Correct Pizza classes created 
			@Test //marg
			public void CorrectCreatePizzaMarg() throws PizzaException, LogHandlerException{
				String line = "21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,PZM,5";
				pizza = LogHandler.createPizza(line);
				assertEquals(pizza.getPizzaType(), "Margherita");
			}
			
			@Test //meat
			public void CorrectCreatePizzaMeat() throws PizzaException, LogHandlerException{
				String line = "21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,PZL,5";
				pizza = LogHandler.createPizza(line);
				assertEquals(pizza.getPizzaType(), "Meat Lovers");
			}
			
			@Test //vege
			public void CorrectCreatePizzaVege() throws PizzaException, LogHandlerException{
				String line = "21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,PZV,5";
				pizza = LogHandler.createPizza(line);
				assertEquals(pizza.getPizzaType(), "Vegetarian");
			}
		//correct parameters parsed to it (quantity) how to check order time!!!!!!!!
			@Test //vege
			public void CorrectQuantityPizzaVege() throws PizzaException, LogHandlerException{
				String line = "21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,PZV,5";
				pizza = LogHandler.createPizza(line);
				assertEquals(pizza.getQuantity(), 5, 0);
			}
			@Test 
			public void CorrectQuantityPizzaMeat() throws PizzaException, LogHandlerException{
				String line = "21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,PZL,1";
				pizza = LogHandler.createPizza(line);
				assertEquals(pizza.getQuantity(), 1, 0);
			}
			@Test 
			public void CorrectQuantityPizzaMarg() throws PizzaException, LogHandlerException{
				String line = "21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,PZM,9";
				pizza = LogHandler.createPizza(line);
				assertEquals(pizza.getQuantity(), 9, 0);
			}
	//PopulateDataset - correctly reads a file into a pizza array
		//throws loghandler exception when file doesn't exist. 
		//throws pizza exception when invalid parameters - quantity, times... 
		//each line of file is parsed
		//returns different arrays - for same file and different files
		//
}
