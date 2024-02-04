package exercice1;

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

	}
	
	
	
	
	// Added code begins :

	// The state of a section if it is occupied or not
	private boolean isOccupied;

	/**
	 * The train leaves the section and set the isOccupied attribute to false
	 */
	@Override
	public synchronized void leave() {
		isOccupied = false;

	}

	/**
	 * The train enter the section, we check if the section is already occupied
	 */
	@Override
	public synchronized void enter() {
		isOccupied = true;
	}
	
	/**
	 * The state of the section
	 * @return a boolean value of the condition
	 */
	@Override
	public synchronized boolean isOccupied() {
		return isOccupied ; 
	}


}
