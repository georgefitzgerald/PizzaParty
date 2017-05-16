package asgn2Tests;


/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Person A
 * 
 *
 */
public class CustomerTests {
	PickUpCustomer test1;
	PickUpCustomer cus1;
	PickUpCustomer cus2;
	
	
	@Before 
	public void initialize() throws CustomerException{
		cus1 = new PickUpCustomer("Leo", "0433563076",5,4);
	}
	
	
	@Test (expected=CustomerException.class)
	public void noNameTest() throws CustomerException{
		test1 = new PickUpCustomer("", "0123456789",1,2);
	}
	
	@Test (expected=CustomerException.class)
	public void tooMuchLettersName() throws CustomerException{
		test1 = new PickUpCustomer("This has got to be the longest name in the world because it's this long", "1234567890",1,2);
	}
	
	@Test (expected=CustomerException.class)
	public void numberNotEnough() throws CustomerException{
		test1 = new PickUpCustomer("Leo", "0123",1,2);
	}
	
	@Test
	public void getNameTest(){
		String test = cus1.getName();
		assertEquals("Leo",test);
	}
	
	@Test
	public void getMobileTest(){
		String test = cus1.getMobileNumber();
		assertEquals("0433563076", test);
	}
	
	@Test
	public void getCusType(){
		String test = cus1.getCustomerType();
		assertEquals("PickUp", test);
	}
	
	@Test
	public void getLocationXTest(){
		int x = cus1.getLocationX();
		assertEquals(5,x);
	}
	
	@Test
	public void getLocationYTest(){
		int y = cus1.getLocationY();
		assertEquals(4,y);
	}
	
	@Test
	public void getDeliveryDistanceTest(){
		
	}
}
