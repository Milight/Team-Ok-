package main;

import repository.Repository;
import repository.RepositoryFactory;
import user.Address;
import user.Customer;

public class Main {

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
		
		
		
		RepositoryFactory repFactory = new RepositoryFactory();
		Repository R = repFactory.getRepository("FILE");
		
		Address a = new Address(10, "Marceau", "Paris", "94210");
//		
		Customer c = new Customer("Fabien","Caron", 0, "fabcaron7@gmail.com","admin",a);
		c.sellP(498, "Xperia-XZ", "Rip","Mobile");
		R.save();
		
		
		
		
	}

}
