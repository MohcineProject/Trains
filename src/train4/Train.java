package train4;

/**
 * Représentation d'un train. Un train est caractérisé par deux valeurs :
 * <ol>
 * <li>Son nom pour l'affichage.</li>
 * <li>La position qu'il occupe dans le circuit (un élément avec une direction)
 * : classe {@link Position}.</li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Mayte segarra <mt.segarra@imt-atlantique.fr> Test if the first
 *         element of a train is a station
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 * @version 0.3
 */

public class Train extends Thread {
	private final String name;
	private Position pos; //Removed the final keyword from Position to enable modification.

	public Train(String name, Position p) throws BadPositionForTrainException {
		if (name == null || p == null)
			throw new NullPointerException();

		// A train should be first be in a station
		if (!(p.getPos() instanceof Station))
			throw new BadPositionForTrainException(name);

		this.name = name;
		this.pos = p.clone();
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Train[");
		result.append(this.name);
		result.append("]");
		result.append(" is on ");
		result.append(this.pos);
		return result.toString();
	}

	
// Added code begins :	
	
	
	/**
	 * set the position of the train to a new one
	 * 
	 * @param pos
	 */
	public void setPos(Position pos) {
		this.pos = pos;
	}

	/**
	 * Change the train position to the next.
	 */
	// Q1.3
	public void move() {
		this.setPos(pos.nextPosition());
	}

	/**
	 * The train run function to execute the Thread
	 */
	@Override
	public void run() {
		while (true) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			move();
			System.out.println(toString());

		}
	}

}
