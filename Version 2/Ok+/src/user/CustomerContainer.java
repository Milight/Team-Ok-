package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;



public class CustomerContainer {
	
	static ArrayList<Customer> list;
	
	CustomerContainer(){
		list = new ArrayList<Customer>();
	}
	
	CustomerContainer(ArrayList<Customer> list){
		this.list.addAll(list);		
	}
	
	private static CustomerContainer INSTANCE = new CustomerContainer();
	 
	public static CustomerContainer getInstance()
	{   return INSTANCE;
	}	

	public void addCustomer(String firstN, String lastN, String email, String pas, Address a)
	{
		Customer cus = new Customer(firstN,lastN,0,email,pas,a);
		list.add(cus);
	}
	
	
	
	public void add(Customer c)
	{		
		list.add(c);
	}
	
	public Customer searchCustomer(String email, String pas) {
		
		
		for(Customer cus : list) {
			if( (cus.getEmail().equals(email)) && (cus.getPassword().equals(pas)) ){
				
				return cus;
			}
		}
		return null;
		
	}
public Customer searchCustomer(String email) {
		
		
		for(Customer cus : list) {
			if( (cus.getEmail().equals(email)) ){
				
				return cus;
			}
		}
		return null;
		
	}

	public void setCounter() {
		// TODO Auto-generated method stub
		int i = 0;
		for(Customer cus : list) {
			if( cus.getId()>i ){				
				i = cus.getId();
			}
		}
		Customer.setCounter(i+1);		
	}
	
	public static void print(){
		for(Customer cus : list) {
			System.out.println(cus.toString());
			
		}
		
	}

	public ArrayList<Customer> getList() {
		// TODO Auto-generated method stub
		return list;
	}
		
}
