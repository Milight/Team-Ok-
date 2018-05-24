package Obj;

import user.Customer;

public class Product {
	
	private static int idCounter = 0;
	private int prix;
	private int id; 
	private String name;
	private String Description;
	private Customer owner;
	
	
	
	public Product(int prix, String name, String description, Customer s) {
		this.prix = prix;
		this.name = name;
		Description = description;
		owner = s;
		id = idCounter;
		idCounter++;
	}
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Product [prix=" + prix + ", id=" + id + ", name=" + name + ", Description=" + Description + ", "
				+ owner.getId() + "]";
	}


	public Customer getOwner() {
		return owner;
	}

	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
	

}
