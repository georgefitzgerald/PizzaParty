package asgn2Restaurant;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;
import asgn2Customers.CustomerFactory;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Person A and Person B
 *
 */
public class LogHandler {
	
	final static String COMMA = ",";

	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws LogHandlerException, CustomerException{
		
		ArrayList<Customer> customerArr = new ArrayList<Customer>();
		
		//read in the file to buffer
        BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new LogHandlerException();
		}
		//read each line getting customer information and store in array list
    	boolean end = false;
		do {
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
	            System.out.println("LogHandler: Error reading line");

			}
			if (line == null){ end = true;}
			else {
	       	    //add a "customer" to the customer array with field populated from read in line
	       	    
				customerArr.add(createCustomer(line));
			}
			
    	} while (!end);
		
		return customerArr;
	}		

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException{
		// TO DO
		ArrayList<Pizza> pizzaArr = new ArrayList<Pizza>();
		
		BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException e) {
				throw new LogHandlerException();
			}
		boolean end = false;
			do {
				String line = null;
				try {
					line = br.readLine();
				} catch (IOException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();

				}
				if (line == null){ end = true;}
				else {
					pizzaArr.add(createPizza(line));
				}

		} while (!end);
		
		return pizzaArr;
	}		

	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException{
		// TO DO
		//create string array for each field of line
   	    //otime, dtime, name, number, code, x, y
		String[] lineArr;
		try{
			lineArr = line.split(COMMA);
		} catch (PatternSyntaxException e) {throw new LogHandlerException();}
		//System.out.println("LogHandler: Mobile Number " + lineArr[2]);
		try{
			return CustomerFactory.getCustomer(lineArr[4].trim(), lineArr[2], lineArr[3], Integer.parseInt(lineArr[5]), Integer.parseInt(lineArr[6]));
		}	catch (ArrayIndexOutOfBoundsException e){throw new LogHandlerException();}
		catch (NumberFormatException e){throw new LogHandlerException();}
	}


	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException{
		// TO DO
		String[] lineArr;
		try{
			lineArr = line.split(COMMA);
		} catch (PatternSyntaxException e) {throw new LogHandlerException();}
		try{
			return PizzaFactory.getPizza(lineArr[7], Integer.parseInt(lineArr[8]), LocalTime.parse(lineArr[0]), LocalTime.parse(lineArr[1]));
		} catch (ArrayIndexOutOfBoundsException e){throw new LogHandlerException();}
	}

}
