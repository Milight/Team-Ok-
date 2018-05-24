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
import javax.swing.JPanel;
import javax.swing.JTextField;

import repository.FileRepository;
import user.Address;
import user.CustomerContainer;

public class RegisterWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pNord; // welcome
	private JPanel pSud; // tout ce qui ce trouve au centre
	private JPanel pSudOuest; // liste items
	private JPanel pSudEst; // log in / sign in
	
	/* Pour le formulaire */
	private JTextField txtName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtPassword;
	
	private JTextField txtStreetNb;
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtZip;
	
	private JButton btnOk;
	private JButton btnCancel;
	
	private static final Color blueDarkColor = new Color(127, 143, 166);
	private static final Color blueGrayColor = new Color(200,200,220);
	
	public RegisterWindow() {
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
		
	}

	
	void designPanel(){
		JPanel fp = new JPanel();
		
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
	
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(30, 70, 130, 30);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(30, 110, 130, 30);
		
		JLabel lblMdp = new JLabel("Password");
		lblMdp.setBounds(30, 150, 130, 30);
		
		txtName = new JTextField();
		txtName.setBounds(140, 30, 180, 30);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(140, 70, 180, 30);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(140, 110, 180, 30);

		txtPassword = new JTextField();
		txtPassword.setBounds(140, 150, 180, 30);
		
		JLabel lblStreetNb = new JLabel("Street number");
		lblStreetNb.setBounds(30, 30, 130, 30);
	
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(30, 70, 130, 30);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(30, 110, 130, 30);
		
		JLabel lblZip = new JLabel("Zip Code");
		lblZip.setBounds(30, 150, 130, 30);
		
		txtStreetNb = new JTextField();
		txtStreetNb.setBounds(140, 30, 180, 30);
		
		txtStreet = new JTextField();
		txtStreet.setBounds(140, 70, 180, 30);
		
		txtCity = new JTextField();
		txtCity.setBounds(140, 110, 180, 30);

		txtZip = new JTextField();
		txtZip.setBounds(140, 150, 180, 30);

		btnOk = new JButton("Ok");
		btnOk.setBounds(30, 300, 130, 30);
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Address a = new Address(Integer.parseInt(txtStreetNb.getText()), txtStreet.getText(), txtCity.getText(), txtZip.getText());
				CustomerContainer.getInstance().addCustomer(txtName.getText(), txtLastName.getText(), txtEmail.getText(), txtPassword.getText(),a);
				PrincipalWindow pw = new PrincipalWindow();
				pw.setVisible(true);
				
				
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
		pSudOuest.add(lblLastName);
		pSudOuest.add(txtLastName);
		pSudOuest.add(lblEmail);
		pSudOuest.add(txtEmail);
		pSudOuest.add(lblMdp);
		pSudOuest.add(txtPassword);
		pSudOuest.add(btnOk);
		
		/*** Panel au centre **/
		/*pCenter = new JPanel();
		pCenter.setBackground(Color.WHITE);*/
		
		/*** Panel en bas à droite ***/
		pSudEst = new JPanel();
		pSudEst.setBackground(blueGrayColor);
		pSudEst.setLayout(null);
	
		pSudEst.add(lblStreetNb);
		pSudEst.add(txtStreetNb);
		pSudEst.add(lblStreet);
		pSudEst.add(txtStreet);
		pSudEst.add(lblCity);
		pSudEst.add(txtCity);
		pSudEst.add(lblZip);
		pSudEst.add(txtZip);
		

		/*** Panel des éléments du sud ***/
		pSud.add(pSudOuest);
		//pSud.add(pCenter);
		pSud.add(pSudEst);

		
		
		fp.setLayout(null);
		fp.add(pNord);
		fp.add(pSud);
		
		this.add(fp, BorderLayout.CENTER);
	}
	
	
	
}
