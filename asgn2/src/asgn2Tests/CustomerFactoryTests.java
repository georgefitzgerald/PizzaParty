package asgn2Tests;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Person A
 *
 */
public class CustomerFactoryTests {
	// TO DO
	PickUpCustomer test1;
	
	PickUpCustomer cus1;
	
	DriverDeliveryCustomer test2;
	
	DroneDeliveryCustomer test3;
	
	
	@Before 
	public void initialize() throws CustomerException{
		cus1 = new PickUpCustomer("Leo", "0433563076",5,4);
		test2 = new DriverDeliveryCustomer("George", "0402123456", 5,3);
	}
	
	@Test
	public void cusFactoryTest(){
		
	}
}
