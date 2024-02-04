package exercice4;

/**
 * Représentation d'une section de voie ferrée. C'est une sous-classe de la
 * classe {@link Element}.
 *
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Section extends Element {

	

	
	public Section(String name) {
		super(name);
		super.size = 1 ; 
	
			
	}
	// Added code begins : 
	
	
	
	//We add a condition to assure that the section contains one train max
	private boolean isOccupied ; 
	
	
	
	/**
	 * The train leaves the section and set the isOccupied attribut to false
	 */
	@Override
	public synchronized void leave() {
		isOccupied = false ;
		notifyAll();
		
			
	}
	
	
	
	
	/**
	 * The train enter the section, we check if the section is already occupied 
	 */
	@Override
	public synchronized void enter() {
		
		while(isOccupied) { 
			try {
				wait() ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		isOccupied = true ;
				
	}
	
	
	/**
	 * The condition that controls the state of a train 
	 * @return a boolean value of the condition
	 */
	@Override
	public synchronized boolean isOccupied() {
		return isOccupied ; 
	}

	/**
	 * Verifies if the element is a section or a station 	
	 * @return a boolean value for the result 
	 * 
	 */
	@Override 
	public synchronized boolean isSection() {
		return true ;
	}
	 
	


}