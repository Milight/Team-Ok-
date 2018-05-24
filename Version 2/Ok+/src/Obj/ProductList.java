package Obj;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;


import repository.FileRepository;


public class ProductList extends JList<Product> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JList<Product> list;
	private String name;
	ListModel<Product> listModel;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductList() {	
		
		listModel = new DefaultListModel<Product>();
		for(Product item : ProductContainer.getInstance()) {
			Product p = new Product(item.getPrix(),item.getName(),item.getDescription(),item.getType(),null);
			((DefaultListModel<Product>) listModel).addElement(p);
		}
		
		list = new JList<Product>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(11);
		
	}
	
	public void updatePanel() {
		((DefaultListModel<Product>) listModel).clear();
		listModel = new DefaultListModel<Product>();
		for(Product item : ProductContainer.getList()) {
			Product p = new Product(item.getPrix(),item.getName(),item.getDescription(),item.getType(),null);
			((DefaultListModel<Product>) listModel).addElement(p);
		}
		list.setModel(listModel);
	}
	
	private static ProductList INSTANCE = new ProductList();
	 
	public static ProductList getInstance()
	{   
		return INSTANCE;
	}

	public JList<Product> getList(){
		list.validate();
		list.repaint();
		return list;
	}
	
	public void setList(ArrayList<Product> arrList) {
		((DefaultListModel<Product>) listModel).clear();
		listModel = new DefaultListModel<Product>();
		for(Product item : arrList) {
			Product p = new Product(item.getPrix(),item.getName(),item.getDescription(),item.getType(),null);
			((DefaultListModel<Product>) listModel).addElement(p);
		}
		list.setModel(listModel);
	}
}
