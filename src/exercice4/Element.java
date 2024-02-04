package exercice4;

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
	protected int size;

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

	/**
	 * Used to enable or wait() a train from going a certain direction if there is
	 * already trains on the sections going on the opposite direction.
	 * 
	 * @param direction : the direction of the train to go
	 */
	public synchronized void checkOneDirection(Direction direction) {

		while (!railway.oneDirection(direction, this)) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Used to change the counts on railway that indicates the number of the trains

	 * going in a certain direction in a part of sections.
	 * 
	 * @param direction : the direction of the train
	 * @param next      : used to determine if the following element is a station or
	 *                  not. This information determines if a train is leaving a
	 *                  station or entering it, so that the counts increase or
	 *                  decrease accordingly
	 */

	public synchronized void announceDirection(Direction direction, Element next) {
		if (direction == Direction.LR) {
			if (!next.isSection())
				railway.decreaseLR(this);
			else
				railway.addLR(this);
		} else {
			if (!next.isSection())
				railway.decreaseRL(this);
			else
				railway.addRL(this);
		}

		notifyAll();
	}

	// A method used to distinguish if an element is a section or a station 	
	public abstract boolean isSection();

/**
 * Verify if the next station can accept another train 	
 * @param station 
 * @param direction
 */
	public synchronized void verifyNextStation(Element station, Direction direction) {
	while (!railway.verifyNextStation(station, direction)) {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
}
