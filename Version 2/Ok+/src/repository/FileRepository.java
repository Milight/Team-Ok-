package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Obj.Product;
import Obj.ProductContainer;
import user.Address;
import user.Customer;
import user.CustomerContainer;

public class FileRepository implements Repository {

	@Override
	public void load() {
		
		// TODO Auto-generated method stub
		Scanner sc;
		Customer s = null;
		Address a = null;

		int id;
		String firstN;
		String lastN;
		double balance;
		String email = null;
		String password;

		int number;
		String street;
		String city;
		String zipC;
		String type;
		// Customer
		
		try {
			sc = new Scanner(new File("Customer.txt"));
			
			while (sc.hasNextLine()) {
				System.out.println("Je suis ici");
				id = Integer.parseInt(sc.next());
				firstN = sc.next();
				lastN = sc.next();
				balance = Double.parseDouble(sc.next());
				email = sc.next();
				password = sc.next();
				number = Integer.parseInt(sc.next());
				street = sc.next();
				city = sc.next();
				zipC = sc.next();
				
				a = new Address(number, street, city, zipC);
				s = new Customer(id, firstN, lastN, balance, email, password, a);
				System.out.println(s.toString());
				System.out.println("emp created.");
				CustomerContainer.getInstance().add(s);
				System.out.println("emp added.");
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		CustomerContainer.getInstance().setCounter();

		double prix;
		String name;
		String description;

		// Product
		try {
			
			sc = new Scanner(new File("Product.txt"));
			while (sc.hasNextLine()) {
				prix = Double.parseDouble(sc.next());
				name = sc.next();
				description = sc.next();
				type = sc.next();
				email = sc.next();
				
				s = CustomerContainer.getInstance().searchCustomer(email);
				Product p = new Product(prix, name, description,type, s);
				System.out.println("emp created.");
				ProductContainer.getInstance();
				ProductContainer.addProduct(p);
				System.out.println("emp added.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		 FileWriter fileWriter;
		//Customer
		try {
			fileWriter = new FileWriter("Customer.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
		    ArrayList<Customer> list = CustomerContainer.getInstance().getList();
		    String str = "";
		    for(Customer cus : list){
		    	str = "\n" + cus.getId()+" "+cus.getFirstN()+" "+cus.getLastN()+" "+cus.getBalance()+" "+cus.getEmail()+" "+cus.getPassword()+" "+cus.getAddress().toString();
		    	printWriter.printf(str);
		    } 
		    
		    printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Product 
		try {
			fileWriter = new FileWriter("Product.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			List<Product> list = ProductContainer.getList();
		    String str = "";
		    for(Product prod : list){
		    	
		    	str = "\n"+ prod.getPrix()+" "+prod.getName()+" "+prod.getDescription()+" "+prod.getType()+ " "+ prod.getOwner().getEmail() ;
		    	printWriter.printf(str);
		    } 
		    
		    printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    

	}

}
