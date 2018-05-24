package Obj;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




public class ProductContainer implements Iterable<Product>{
	static private  List<Product> listProducts;

	public ProductContainer(){
		ProductContainer.listProducts = new ArrayList<Product>();
	}
	
	private static ProductContainer INSTANCE = new ProductContainer();
	 
	public static ProductContainer getInstance()
	{   return INSTANCE;
	}

	public static void addProduct(Product p) {
		listProducts.add(p);
		System.out.println("[CONTAINER] Add item. New list : "+getList());
	}
	
	
	public void supprimerProduct(Product p) {
			List<Product> temp = new ArrayList<Product>();
			for(Product prod : listProducts) {
				if( prod.getName() != p.getName()){
					temp.add(prod);
				}
			}
			listProducts.clear();
			listProducts.addAll(temp);
			
		
		print();
	}
	
	public void clearList() {
		listProducts.clear();
	}
	public static List<Product> getList(){
		return listProducts;
		
	}
	
	@Override
	public Iterator<Product> iterator() {
		return new Iterator<Product> () {
			private final Iterator<Product> iter = listProducts.iterator();

			@Override
			public boolean hasNext() {
				return iter.hasNext();
			}

			@Override
			public Product next() {
				return iter.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("no changes allowed");
			}
		};
	}
	
	public Object[]  toArray() {
		return listProducts.toArray();
	}
	
	public Product getProductById(int id){
		return listProducts.get(id);
	}
	
	public Product searchProduct(String nom) {
		
		
		for(Product prod : listProducts) {
			if( prod.getName().equals(nom)){
				return prod;
			}
		}
		return null;
		
	}
	
	
	public static void print(){
		for(Product prod : listProducts) {
			System.out.println(prod.toString());
		}
	}
	
	
	
}
