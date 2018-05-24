package user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Obj.Product;
import Obj.ProductContainer;
import eception.TooExpensiveException;

public class Customer {

	private int id;
	private static int counterId = 0;
	private String firstN;
	private String lastN;
	private double balance = 0;
	private String email;
	private String password;
	private Address address;
	private ArrayList<Product> cart;

	public Customer(String firstN, String lastN, double balance, String email, String pas, Address address) {
		this.firstN = firstN;
		this.lastN = lastN;
		this.balance = balance;
		this.email = email;
		this.address = address;
		id = counterId;
		this.password = pas;
		cart = new ArrayList<Product>();
		counterId++;
		Product p = new Product(15,"test","super","Mobile", null);
		addCart(p);
	}
	
	
	
	public String getFirstN() {
		return firstN;
	}


	public void setFirstN(String firstN) {
		this.firstN = firstN;
	}


	public String getLastN() {
		return lastN;
	}


	public void setLastN(String lastN) {
		this.lastN = lastN;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public ArrayList<Product> getCart() {
		return cart;
	}


	public void setCart(ArrayList<Product> cart) {
		this.cart = cart;
		
	}
	
	

	
	//Constructor for BDD
	public Customer(int ID,String firstN, String lastN, double balance, String email, String pas, Address address) {
		this.id = ID;
		this.firstN = firstN;
		this.lastN = lastN;
		this.balance = balance;
		this.email = email;
		this.address = address;
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
	
	public void changePrice(Product p, double price){
		if(p.getOwner().getId() != this.getId()){
			System.out.println("it's not your product");
			return;
		}
		p.setPrix(price);
	};
	
	
	public void sellP(double prix, String name, String description,String type){
		Product p = new Product(prix, name, description,type, this);
		ProductContainer.getInstance();
		ProductContainer.addProduct(p);
	}
	
	
	
	//Buyer
	
	public void addCart(Product p){
		cart.add(p);
		ProductContainer.getInstance().supprimerProduct(p);
		ProductContainer.getInstance();
		System.out.println("[CART] Add cart. New list : "+getCart());
	}
	
	public void emptyCart(){
		for(int i = 0; i < cart.size();i++){
			ProductContainer.addProduct(cart.get(i));
		}
		cart.clear();
		System.out.println("[CART] Empty cart. New list : "+getCart());
	}	
	
	public void removeProduct(Product p) {
		List<Product> temp = new ArrayList<Product>();
		
		for(Product prod : cart) {

			if( prod.getName() != p.getName()){
				temp.add(prod);
			}
		}
		
		cart.clear();
		cart.addAll(temp);
		
		ProductContainer.addProduct(p);
		
	}
	
	
	
	public void buy() throws TooExpensiveException{
		int a = 0;
		
		for(int i = 0; i < cart.size();i++){
			double prix = cart.get(i).getPrix();
			a+= prix;
		}
		if(a>this.balance) throw new  TooExpensiveException(this.balance, a);
		for(int i = 0; i < cart.size();i++){
			this.buyP(cart.get(i));
		}
		cart.clear();
		System.out.println("[CUSTOMER] Congratulation ! You bought items. Current Balance :" + getBalance());
	}
	
	public void buyP(Product b) throws TooExpensiveException{
		double prix = b.getPrix();

		if(prix>this.balance) throw new  TooExpensiveException(this.balance, prix);
		else {
			this.balance -= prix;
		}		
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstN == null) ? 0 : firstN.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastN == null) ? 0 : lastN.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstN == null) {
			if (other.firstN != null)
				return false;
		} else if (!firstN.equals(other.firstN))
			return false;
		if (id != other.id)
			return false;
		if (lastN == null) {
			if (other.lastN != null)
				return false;
		} else if (!lastN.equals(other.lastN))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}


	public static void setCounter(int i) {
		// TODO Auto-generated method stub
		counterId = i;
		
	}


	public Object getAddress() {
		// TODO Auto-generated method stub
		return address;
	}


	
}
