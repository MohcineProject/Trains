package exercice1;


/**
 * Cette classe abstraite est la représentation générique d'un élément de base
 * d'un circuit, elle factorise les fonctionnalitÃ©s communes des deux
 * sous-classes : l'entrée d'un train, sa sortie et l'appartenance au
 * circuit.<br/>
 * Les deux sous-classes sont :
 * <ol>
 * <li>La représentation d'une gare : classe {@link Station}</li>
 * <li>La représentation d'une section de voie ferrée : classe
 * {@link Section}</li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public abstract class Element {
	
	
	private final String name;
	protected Railway railway;
	protected  int size ;

	protected Element(String name) {

		if (name == null)
			throw new NullPointerException();

		this.name = name;
	
	
	}
	public void setRailway(Railway r) {
		if (r == null)
			throw new NullPointerException();

		this.railway = r;
	}

	@Override
	public String toString() {
		return this.name;
	}

	
	
	// Added code begins :
	
	
	
	
	
	

	// Abstract methods used in Section and Station
	public abstract void leave();

	public abstract void enter();


	public abstract boolean isOccupied();

	/**
	 * The method that returns the next element depending on the direction
	 * 
	 * @param direction : the direction of the current element
	 * @return the next element
	 */
	public Element getNext(Direction direction) {
		Element element = railway.getNextElement(this, direction);
		return element;
	}



}
