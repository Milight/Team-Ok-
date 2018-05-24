package Obj;

import java.util.ArrayList;



public class Catalog {

	static ArrayList<Product> cata;

	public Catalog() {
		cata = new ArrayList<Product>();
	}

	public static void addProduct(Product p) {
		cata.add(p);
	}

	public Product searchP(int id) {

		for (int i = 0; i < cata.size(); i++) {
			if (cata.get(i).getId() == id)
				return cata.get(i);
		}
		return null;
	}	

	public static void deleteP(Product p) {
		cata.remove(p);
	}
	
	public void print(){
		if (cata.size()==0) System.out.println("No item sells for now");
		for (int i = 0; i < cata.size(); i++) {			
			System.out.println(cata.get(i).toString());
		}
	}
	

}
