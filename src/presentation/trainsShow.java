package presentation;

import javax.swing.*;

public class trainsShow extends JFrame{
	
	public trainsShow (String name) {
		super(name) ; 
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel principal = new JPanel() ; 
		setContentPane(principal);
		
		
	}

	
	public void main(String[] args) {
		trainsShow s = new trainsShow("Hello") ; 
		s.setVisible(true);
	}
	
	
}
