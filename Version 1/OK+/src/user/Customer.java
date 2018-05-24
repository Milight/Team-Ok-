package user;

import java.util.ArrayList;

import Obj.Catalog;
import Obj.Product;
import eception.TooExpensiveException;

public class Customer {

	private int id;
	private static int counterId = 0;
	private String firstN;
	private String lastN;
	private int balance = 0;
	private String email;
	private String password;
	//private Address address;
	private ArrayList<Product> cart;

	public Customer(String firstN, String lastN, int balance, String email, String pas) {
		this.firstN = firstN;
		this.lastN = lastN;
		this.balance = balance;
		this.email = email;
		//this.address = address;
		id = counterId;
		this.password = pas;
		cart = new ArrayList<Product>();
		counterId++;
	}
	
	
	//Constructor for BDD
	public Customer(int ID,String firstN, String lastN, int balance, String email, String pas) {
		this.id = ID;
		this.firstN = firstN;
		this.lastN = lastN;
		this.balance = balance;
		this.email = email;
		//this.address = address;
		id = counterId;
		this.password = pas;
		cart = new ArrayList<Product>();
		
	}

	public Customer() {
		super();
	}
	
	

	public void addMoney(int a) {
		balance += a;
	}
	
	
	
	//Seller
	
	public void changePrice(Product p, int price){
		if(p.getOwner().getId() != this.getId()){
			System.out.println("it's not your product");
			return;
		}
		p.setPrix(price);
	};
	
	
	public void sellP(int prix, String name, String description){
		Product p = new Product(prix, name, description, this);
		Catalog.addProduct(p);
	}
	
	
	
	//Buyer
	
	public void addCart(Product p){
		cart.add(p);
		Catalog.deleteP(p);
	}
	
	public void emptyCart(){
		for(int i = 0; i < cart.size();i++){
			Catalog.addProduct(cart.get(i));
		}
		cart.clear();
	}	
	
	
	public void buy() throws TooExpensiveException{
		int a = 0;
		for(int i = 0; i < cart.size();i++){
			a+= cart.get(i).getPrix();
		}
		if(a>this.balance) throw new  TooExpensiveException(this.balance, a);
		for(int i = 0; i < cart.size();i++){
			this.buyP(cart.get(i));
		}
		cart.clear();
	}
	
	public void buyP(Product b) throws TooExpensiveException{
		if(b.getPrix()>this.balance) throw new  TooExpensiveException(this.balance, b.getPrix());
		else {
			this.balance = updateBalance(b.getPrix());
		}		
	}
	
	public int updateBalance(int price) {
		int newBalance=this.balance;
		return (newBalance -= price);
	}

	@Override
	public String toString() {
		int a = 0;
		for(int i = 0; i < cart.size();i++){
			a=a+1;
		}
		return "Customer [id=" + id + ", firstN= " + firstN + ", lastN= " + lastN + ", balance= " + balance
				+ ", email= " + email + ", "+" Number of item :"+ a+"]";
	}
	
	
	//ToString for BDD
	public String toString2() {
		String s = "";
		s+= id + "," + firstN + "," + lastN + "," + balance
				+ "," + email + ","+password;
		return s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public static void setCounterID(int a){
		counterId = a;
	}


}
