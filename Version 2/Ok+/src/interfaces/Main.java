package interfaces;

import repository.FileRepository;

public class Main {
	

	public static void main(String[] args) {
		
		//get items list from file
		FileRepository fl = new FileRepository();
		fl.load();
	
		PrincipalWindow w = new PrincipalWindow();
		w.setVisible(true);
	}
	

}
