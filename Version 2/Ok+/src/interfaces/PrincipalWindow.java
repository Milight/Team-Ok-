package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Obj.ProductList;
import repository.FileRepository;
import user.Customer;
import user.CustomerContainer;

public class PrincipalWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pNord; // welcome
	private JPanel pSud; // tout ce qui ce trouve au centre
	private JPanel pSudOuest; // liste items
	private JPanel pSudEst; // log in / sign in
	private JScrollPane scrollPane;
	private JPanel itemsPanel;
	private JPanel fp;
	private JButton btnLogin;
	private JButton btnSignUp;
	private JTextField txtLogin;
	private JTextField txtPassword;
	
	
	private static final Color blueDarkColor = new Color(127, 143, 166);
	private static final Color blueGrayColor = new Color(200,200,220);
	
	public PrincipalWindow() {
		super();
		
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
		
		System.out.println(CustomerContainer.getInstance().getList());
		
	}
	
	
	void designPanel(){
		fp = new JPanel();
		
		/* design buttons and JTextFields */
		btnLogin = new JButton("Sign In");
		btnLogin.setBounds(30, 230, 130, 30);
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(30, 270, 130, 30);
	
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(30, 40, 100, 30);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(140, 40, 180, 30);
		
		
		JLabel lblMdp = new JLabel("Password");
		lblMdp.setBounds(30, 90, 100, 30);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(140, 90, 180, 30);
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Customer cus = CustomerContainer.getInstance().searchCustomer(txtLogin.getText(), txtPassword.getText());
				//CustomerContainer.getInstance().
				if(cus.equals(null)){
					JOptionPane.showMessageDialog(null, "Wrong Email or Password. Try again ");
				}else{
					ClientWindow cw = new ClientWindow(cus);
					cw.setVisible(true);
					System.out.println(cus.getFirstN() + " " + cus.getLastN()+" is leaving Principal window");
				}	
				
				dispose();
				
			}
		});
		
		btnSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RegisterWindow rw = new RegisterWindow();
				rw.setVisible(true);
				dispose();
			}
		});
		
		/* panel Nord - Welcome */
		pNord = new JPanel();
		pNord.setLayout(new FlowLayout());
		pNord.setBackground(blueGrayColor);
		pNord.setOpaque(true);
		pNord.setSize(800, 100);
		pNord.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, blueDarkColor));
		
		/* label welcom */
		JLabel lblWelcom = new JLabel("Welcom in Ok+ Application");
		
		pNord.add(lblWelcom);
		
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
	
		
		/* Panel � droite */
		pSudEst = new JPanel();
		pSudEst.setBackground(blueGrayColor);
		pSudEst.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, blueDarkColor));
		pSudEst.setLayout(null);
		
		

		//pSudEst.setLayout(null);
		pSudEst.add(lblLogin);
		pSudEst.add(txtLogin);
		pSudEst.add(lblMdp);
		pSudEst.add(txtPassword);
		pSudEst.add(btnLogin);
		pSudEst.add(btnSignUp);
		
		/* panel pour la liste des items */
		itemsPanel = new JPanel();
		itemsPanel.setBackground(Color.BLACK);
		itemsPanel.setLayout(new BorderLayout());
		itemsPanel.setBounds(35, 30, 300, 300); //taille du panel qui contient la liste
		
		pSudOuest.add(itemsPanel);
		
		/* Ajout des elements du panel sud */
		pSud.add(pSudOuest);
		pSud.add(pSudEst);

		displayListItem();
		
		/* Ajout des elements du panel principal */
		fp.setLayout(null); 
		fp.add(pNord);
		fp.add(pSud);
		
		setContentPane(fp); // ajout du panel principal � la window
	}
	
	public void updateItemPanel() {
		itemsPanel.validate();
		itemsPanel.repaint();
	}
	
	public void displayListItem(){
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(ProductList.getInstance().getList());
			itemsPanel.add(scrollPane);
			updateItemPanel();
		
	}
	
}
