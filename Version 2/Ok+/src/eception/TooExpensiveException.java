package eception;

public class TooExpensiveException extends Exception {

	public TooExpensiveException(double balance, double a) {
		System.out.println("The item is too expensive for you (Actual balance : "+ balance + ", Price: "+ a + ") ");
		System.out.println("Add some money if you want this items.");
	}
	
}
