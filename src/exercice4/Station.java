package exercice4;


/**
 * Représentation d'une gare. C'est une sous-classe de la classe
 * {@link Element}. Une gare est caractérisée par un nom et un nombre de quais
 * (donc de trains qu'elle est susceptible d'accueillir à un instant donné).
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Station extends Element {

	public Station(String name, int size) {
		super(name);
		if (name == null || size <= 0)
			throw new NullPointerException();
		super.size = size;

	}

	// Added code begins :
	
	
	
	
	

	private int count = 0; // The count used to represent the number of trains in the Station

	/**
	 * The train enter the station, we check if the station is not already full
	 */
	@Override
	public synchronized void enter() {
		while (isOccupied())
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		this.count++;
	}

	/**
	 * The train leave the station, we check if the section is empty before
	 */
	
	@Override
	public synchronized void leave() {
		if (!isEmpty())
			this.count--;
		notifyAll();
	}

	/**
	 * represents the condition to access a station based on the number of trains
	 * already in
	 * 
	 * @return a boolean value for the result
	 * 
	 */
	@Override
	public synchronized boolean isOccupied() {
		return (count == size);
	}

	/**
	 * Verifies if the station is not empty to avoid having a negative count
	 * 
	 * @return a boolean value for the result
	 * 
	 */
	private synchronized boolean isEmpty() {
		return (count == 0);
	}

	/**
	 * Verifies if the element is a section or a station
	 * 
	 * @return a boolean value for the result
	 * 
	 */
	@Override
	public boolean isSection() {
		return false;
	}
	
	/**
	 * Return if the station can take one more train 
	 * @return a boolean to verify the csondition
	 */
	public synchronized boolean almostFull() {
	return count == size - 1 || isOccupied() ; 
}


}