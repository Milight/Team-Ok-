package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Obj.Product;
import Obj.ProductContainer;
import repository.FileRepository;
import user.Address;
import user.Customer;
import user.CustomerContainer;

public class AddItemWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pNord; // welcome
	private JPanel pSud; // tout ce qui ce trouve au centre
	private JPanel pSudOuest; // liste items
	private JPanel pSudEst; // log in / sign in
	private JPanel pCenter;
	private Customer customer;
	
	/* Pour le formulaire */
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtComment;
	
	private JRadioButton mobileButton;
	private JRadioButton laptopButton;
	private JRadioButton cameraButton;
	private JRadioButton consoleButton;
	
	private String type;

	
	private JButton btnOk;
	private JButton btnCancel;
	
	private static final Color blueDarkColor = new Color(127, 143, 166);
	private static final Color blueGrayColor = new Color(200,200,220);
	
	public AddItemWindow(Customer cus) {
		super();
		customer = cus;
		build();//On initialise notre fen�tre
	}
	
	private void build(){
		/** Config principal panel **/
		setBackground(new Color(200,200,250));
		setLayout(new BorderLayout());
		
		setLayout(new BorderLayout());
		setTitle("OK+"); //On donne un titre � l'application
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
		JPanel fp = new JPanel();
		buildRadioButton();
		type = "Mobile";
		
		/**** panel Nord - Welcome ***/
		pNord = new JPanel();
		pNord.setLayout(new FlowLayout());
		pNord.setBackground(blueGrayColor);
		pNord.setOpaque(true);
		pNord.setSize(800, 100);
		pNord.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, blueDarkColor));
		JLabel lblTitle = new JLabel("Please complete all mandatory fields");
		pNord.add(lblTitle);
		
		/********************* Config pSud **********************/
		
		pSud = new JPanel();
		pSud.setBackground(blueDarkColor);
		pSud.setBorder(BorderFactory.createMatteBorder(0, 10, 10, 10, blueDarkColor));
		pSud.setBackground(blueGrayColor);
		pSud.setLayout(new GridLayout(1,2));
		pSud.setBounds(0, 100, 795, 370);

		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(30, 30, 130, 30);
	
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(30, 70, 130, 30);
		
		JLabel lblComment = new JLabel("Comment");
		lblComment.setBounds(30, 110, 130, 30);
		

		
		txtName = new JTextField();
		txtName.setBounds(140, 30, 180, 30);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(140, 70, 180, 30);
		
		txtComment= new JTextField();
		txtComment.setBounds(140, 110, 180, 30);



		btnOk = new JButton("Ok");
		btnOk.setBounds(30, 300, 130, 30);
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Product p = new Product(Double.parseDouble(txtPrice.getText()), txtName.getText(), txtComment.getText(), type, customer);
				ProductContainer.addProduct(p);
				
				ClientWindow cl = new ClientWindow(customer);
				cl.setVisible(true);
				dispose();
				
				
			}
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(30, 340, 130, 30);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				PrincipalWindow pw = new PrincipalWindow();
				pw.setVisible(true);
				
				
				dispose();
				
				
			}
		});
		
		
		
		
		
		/* Panel en bas à gauche */
		pSudOuest = new JPanel();
		pSudOuest.setBackground(blueGrayColor);
		pSudOuest.setLayout(null);
		

		pSudOuest.add(lblName);
		pSudOuest.add(txtName);
		pSudOuest.add(lblPrice);
		pSudOuest.add(txtPrice);
		pSudOuest.add(lblComment);
		pSudOuest.add(txtComment);
		pSudOuest.add(btnOk);
		
		/*** Panel au centre **/
		/*pCenter = new JPanel();
		pCenter.setBackground(Color.WHITE);*/
		
		/*** Panel en bas à droite ***/
		pSudEst = new JPanel();
		pSudEst.setBackground(blueGrayColor);
		pSudEst.setLayout(new GridLayout(0, 1));
	
		pSudEst.add(mobileButton);
		pSudEst.add(laptopButton);
		pSudEst.add(cameraButton);
		pSudEst.add(consoleButton);

		/*** Panel des éléments du sud ***/
		pSud.add(pSudOuest);
		//pSud.add(pCenter);
		pSud.add(pSudEst);

		
		
		fp.setLayout(null);
		fp.add(pNord);
		fp.add(pSud);
		
		this.add(fp, BorderLayout.CENTER);
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
		  	
		  	ButtonGroup group = new ButtonGroup();
		    group.add(mobileButton);
		    group.add(laptopButton);
		    group.add(cameraButton);
		    group.add(consoleButton);
		    
		    mobileButton.addActionListener(this);
		    laptopButton.addActionListener(this);
		    cameraButton.addActionListener(this);
		    consoleButton.addActionListener(this);
		    
		   
	}
	
	 public void actionPerformed(ActionEvent e) {
	       type = e.getActionCommand();
	 }
}
