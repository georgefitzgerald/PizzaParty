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
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		LocalTime otime = LocalTime.of(21, 17, 0, 0);
		LocalTime dtime = LocalTime.of(21, 27, 0, 0);
	}
	
	//Initializes data structure
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
	
/*	//Throws exception when quantity zero
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
	}*/
	
	//Throws exception if ordertime < min order time
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
