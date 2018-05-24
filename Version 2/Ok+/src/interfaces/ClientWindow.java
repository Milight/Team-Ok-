package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import user.Customer;
import Obj.Product;
import Obj.ProductContainer;
import Obj.ProductList;
import eception.TooExpensiveException;
import repository.FileRepository;

public class ClientWindow extends JFrame implements ActionListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pNord; // welcome
	private JPanel pSud; // tout ce qui ce trouve au centre
	private JPanel pSudOuest; // liste items
	private JPanel pSudEst; // log in / sign in
	private JScrollPane scrollPane;
	private JScrollPane scrollPaneCart;
	private JPanel itemsPanel;
	private JPanel cartPanel;
	private JPanel fp;
	private JButton btnLogout;
	private JButton btnAddCart;
	private JButton btnAddMoney;
	private JButton btnEmptyCart;
	private JButton btnSuppItemCart;
	private JButton btnAddProduct;
	private JButton btnBuy;
	private JButton btnSearch;
	private JTextField txtSearch;
	private JTextField txtMoney;
	private Product currentProd;
	private Product currentProdCart;
	private Customer customer;
	private JList<Product> listP;
	private JList<Product> listItem;
	private ArrayList<Product> tempList;
	private ListModel<Product> listModelCart;
	private JLabel lblBalance;

	private JRadioButton mobileButton;
	private JRadioButton laptopButton;
	private JRadioButton cameraButton;
	private JRadioButton consoleButton;
	private JRadioButton allButton;
	
	private JPanel pnlRadioBtn;
	private String type;
	
	private static final Color blueDarkColor = new Color(127, 143, 166);
	private static final Color blueGrayColor = new Color(200,200,220);

	public ClientWindow(Customer cus) {
		super();
		this.customer = cus;
		build();//On initialise notre fen�tre
		System.out.println("Welcom "+customer.getFirstN() + " " + customer.getLastN());
	}

	private void build(){
		/** Config principal panel **/
		setBackground(new Color(200,200,250));
		setLayout(new BorderLayout());

		setLayout(new BorderLayout());
		setTitle("OK+"); //window title
		setSize(800,500); //On donne une taille � notre fen�tre
		setLocationRelativeTo(null); //On centre la fen�tre sur l'�cran
		setResizable(false); //On interdit la redimensionnement de la fen�tre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l'application de se	fermer lors du clic sur la croix
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
			
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				FileRepository fl = new FileRepository();
				fl.save();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		designPanel();
		
	}



	void designPanel(){

		buildRadioButton();
		
		ProductList.getInstance().getList().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()){
					Object result =  ProductList.getInstance().getList().getSelectedValue();
					if (result instanceof Product) {
						currentProd =  (Product)result;
						System.out.println("[Prod] You are currently selecting : "+ currentProd.getName());
					}

				}
			}
		});



		fp = new JPanel();

		/* design buttons, label and JTextFields */
		btnLogout = new JButton("Log Out");
		btnLogout.setBounds(490, 50, 130, 30);


		btnAddProduct = new JButton("Sell product");
		btnAddProduct.setBounds(630, 50, 130, 30);
		btnAddProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddItemWindow ai = new AddItemWindow(customer);
				ai.setVisible(true);
				dispose();

			}
		});

		btnSearch = new JButton("Search");
		btnSearch.setBounds(170, 50, 130, 30);
		

		txtSearch = new JTextField();
		txtSearch.setBounds(30, 50, 130, 30);

		lblBalance = new JLabel(customer.getBalance()+" $");
		lblBalance.setBounds(650, 20, 100, 30);

		btnAddCart = new JButton("Add Item");
		btnAddCart.setBounds(30, 260, 130, 30);

		btnAddCart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				customer.addCart(currentProd);
				updateCartPanel();
				updateItemPanel();


				listP.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent event) {
						if (!event.getValueIsAdjusting()){
							Object result =  listP.getSelectedValue();
							if (result instanceof Product) {
								currentProdCart =  (Product)result;
								System.out.println("[CART] You are currently selecting : "+ currentProdCart.getName());
							}

						}
					}
				});
			}



		});

		btnAddMoney = new JButton("Add Money");
		btnAddMoney.setBounds(30, 300, 130, 30);

		btnEmptyCart = new JButton("Empty cart");
		btnEmptyCart.setBounds(30, 260, 130, 30);
		btnEmptyCart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				customer.emptyCart();
				updateCartPanel();
				updateItemPanel();

			}
		});


		btnSuppItemCart = new JButton("Remove item");
		btnSuppItemCart.setBounds(30, 300, 130, 30);
		btnSuppItemCart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				customer.removeProduct(currentProdCart);
				updateCartPanel();
				updateItemPanel();

			}
		});

		btnBuy = new JButton("Buy");
		btnBuy.setBounds(230, 260, 130, 30);
		btnBuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					customer.buy();
					updateCartPanel();
					updateItemPanel();
				} catch (TooExpensiveException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		txtMoney = new JTextField();
		txtMoney.setBounds(170, 300, 130, 30);

		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				PrincipalWindow pw = new PrincipalWindow();
				pw.setVisible(true);
				dispose();

			}
		});

		btnAddMoney.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(Integer.parseInt(txtMoney.getText())>0){
					customer.addMoney(Integer.parseInt(txtMoney.getText()));
					lblBalance.setText(customer.getBalance()+" $");
					System.out.println("You add " + txtMoney.getText());
					System.out.println("Now you have : " + customer.getBalance());
				}else
					JOptionPane.showMessageDialog(null, "Invalid amount");
			}

		});

		/* panel Nord - Welcome */
		pNord = new JPanel();
		pNord.setLayout(null);
		pNord.setBackground(blueGrayColor);
		pNord.setOpaque(true);
		pNord.setSize(800, 100);
		pNord.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, blueDarkColor));

		/* label welcom */
		JLabel lblWelcom = new JLabel("Welcom "+this.customer.getFirstN()+" "+this.customer.getLastN());
		lblWelcom.setBounds(40, 10, 500, 40);

		pNord.add(lblWelcom);
		pNord.add(btnLogout);
		pNord.add(btnAddProduct);
		pNord.add(lblBalance);

		/* Config pSud */

		pSud = new JPanel();
		pSud.setBackground(blueDarkColor);
		pSud.setBorder(BorderFactory.createMatteBorder(0, 10, 10, 10, blueDarkColor));
		pSud.setBackground(blueGrayColor);
		pSud.setLayout(new GridLayout(1,2));
		pSud.setBounds(0, 100, 800, 380);

		/* Panel � gauche */
		//  haut , gauche ,  bas, droit 
		pSudOuest = new JPanel();
		pSudOuest.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, blueDarkColor));
		pSudOuest.setBackground(blueGrayColor);
		pSudOuest.setLayout(null);

		pnlRadioBtn = new JPanel();
		pnlRadioBtn.setBackground(blueGrayColor);
		pnlRadioBtn.setBounds(265, 30, 100, 200);
		pnlRadioBtn.setLayout(new GridLayout(5, 1));
		
		pnlRadioBtn.add(allButton);
		pnlRadioBtn.add(mobileButton);
		pnlRadioBtn.add(laptopButton);
		pnlRadioBtn.add(cameraButton);
		pnlRadioBtn.add(consoleButton);

		/* Panel � droite */
		pSudEst = new JPanel();
		pSudEst.setBackground(blueGrayColor);
		pSudEst.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, blueDarkColor));
		pSudEst.setLayout(null);

		/* panel pour la liste des items */
		itemsPanel = new JPanel();
		itemsPanel.setLayout(new BorderLayout());
		itemsPanel.setBounds(10, 30, 250, 200); //taille du panel qui contient la liste

		/* panel pour la liste des items dans cart */
		cartPanel = new JPanel();
		cartPanel.setLayout(new BorderLayout());
		cartPanel.setBounds(35, 30, 300, 200); //taille du panel qui contient la liste

		pSudOuest.add(pnlRadioBtn);
		pSudOuest.add(itemsPanel);
		pSudOuest.add(btnAddCart);
		pSudOuest.add(btnAddMoney);
		pSudOuest.add(txtMoney);

		pSudEst.add(cartPanel);
		pSudEst.add(btnEmptyCart);
		pSudEst.add(btnSuppItemCart);
		pSudEst.add(btnBuy);
		/* Ajout des elements du panel sud */
		pSud.add(pSudOuest);
		pSud.add(pSudEst);

		displayListItem();
		displayListCart();

		/* Ajout des elements du panel principal */
		fp.setLayout(null); 
		fp.add(pNord);
		fp.add(pSud);

		setContentPane(fp); // ajout du panel principal � la window
	}

	public void updateItemPanel() {
		ProductList.getInstance().updatePanel();
		itemsPanel.validate();
		itemsPanel.repaint();
	}

	public void updateCartPanel() {
		((DefaultListModel<Product>) listModelCart).clear();
		listModelCart = new DefaultListModel<Product>();
		for(Product item : customer.getCart()) {
			Product p = new Product(item.getPrix(),item.getName(),item.getDescription(),item.getType(),null);
			((DefaultListModel<Product>) listModelCart).addElement(p);
		}
		listP.setModel(listModelCart);

		cartPanel.validate();
		cartPanel.repaint();
	}

	public void displayListItem(){
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(ProductList.getInstance().getList());
		itemsPanel.add(scrollPane);
		updateItemPanel();
	}



	public void displayListCart(){


		listModelCart = new DefaultListModel<Product>();
		for(Product item : customer.getCart()) {
			Product p = new Product(item.getPrix(),item.getName(),item.getDescription(),item.getType(),null);
			((DefaultListModel<Product>) listModelCart).addElement(p);
		}

		listP = new JList<Product>(listModelCart);
		listP.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listP.setLayoutOrientation(JList.VERTICAL);
		listP.setVisibleRowCount(11);


		scrollPaneCart = new JScrollPane();
		scrollPaneCart.setViewportView(listP);
		cartPanel.add(scrollPaneCart);

	}

	public void addListerOnCart() {
		if(!cartEmpty()) {
			listP.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent event) {
					if (!event.getValueIsAdjusting()){
						Object result =  listP;
						if (result instanceof Product) {
							currentProdCart =  (Product)result;
							System.out.println("[CART] You are currently selecting : "+ currentProdCart.getName());
						}

					}
				}
			});
		}
	}

	public boolean cartEmpty() {

		return(listP.equals(null)); //return true if null
	}
	
	public void searchByType(String nom) {
		tempList = new ArrayList<Product>();
		
		for(Product element : ProductContainer.getList()) {
			if(element.getType().equals(nom)) {
				tempList.add(element);
			}
		}
		
	}
	
	void buildRadioButton() {
	  	mobileButton = new JRadioButton("Mobile");
	  	mobileButton.setMnemonic(KeyEvent.VK_M);
	  	mobileButton.setActionCommand("Mobile");
	  	mobileButton.setSelected(true);

	  	laptopButton = new JRadioButton("Laptop");
	  	laptopButton.setMnemonic(KeyEvent.VK_L);
	  	laptopButton.setActionCommand("Laptop");
	  	
	  	cameraButton = new JRadioButton("Camera");
	  	cameraButton.setMnemonic(KeyEvent.VK_A);
	  	cameraButton.setActionCommand("Camera");

	  	consoleButton = new JRadioButton("Console");
	  	consoleButton.setMnemonic(KeyEvent.VK_O);
	  	consoleButton.setActionCommand("Console");
	  	
	  	allButton = new JRadioButton("All");
	  	allButton.setMnemonic(KeyEvent.VK_A);
	  	allButton.setActionCommand("All");
	  	
	  	ButtonGroup group = new ButtonGroup();
	    group.add(mobileButton);
	    group.add(laptopButton);
	    group.add(cameraButton);
	    group.add(consoleButton);
	    group.add(allButton);
	    
	    mobileButton.addActionListener(this);
	    laptopButton.addActionListener(this);
	    cameraButton.addActionListener(this);
	    consoleButton.addActionListener(this);
	    allButton.addActionListener(this);
	    
}

		public void actionPerformed(ActionEvent e) {
			type = e.getActionCommand();
			
			searchByType(type);
			if(type.equals("All"))
				ProductList.getInstance().updatePanel();
			else
				ProductList.getInstance().setList(tempList);
			
		}
}
