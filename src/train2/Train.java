package train2;

/**
 * Représentation d'un train. Un train est caractérisé par deux valeurs :
 * <ol>
 *   <li>
 *     Son nom pour l'affichage.
 *   </li>
 *   <li>
 *     La position qu'il occupe dans le circuit (un élément avec une direction) : classe {@link Position}.
 *   </li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Mayte segarra <mt.segarra@imt-atlantique.fr>
 * Test if the first element of a train is a station
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 * @version 0.3
 */


public class Train extends Thread{
	private final String name;
	private Position pos;



	public void setPos(Position pos) {
		this.pos = pos;
	}

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



	// Q1.3 
	public void move() {
		// ajout d'une condition sur le bout de la ligne
		// vérifier qu'aucun train n'est présent dans la ligne
		this.setPos(pos.nextPosition()) ; 
		
		
		
		
		
		
//
//		Direction d = pos.getDirection();
//		Element elet = pos.getPos();
//		
//		
//		
//		Railway r = elet.getRailway();
//		int index = r.indexOfElement(elet);
//		int next;
//		int size = r.getSize();
//		boolean gare = false;
//		if (d == Direction.LR) {
//			if (index == size - 1) {
//				if (gare == false) {
//					this.setPos(new Position(r.getElement(index), Direction.RL));
//					gare = true;
//				} else {
//					next = index - 1;
//					this.setPos(new Position(r.getElement(next), Direction.RL));
//					gare = false;
//
//				}
//
//			} else {
//
//				next = (index + 1);
//				this.setPos(new Position(r.getElement(next), Direction.LR));
//			}
//
//		} else {
//			if (index == 0) {
//				if (gare == false) {
//
//					this.setPos(new Position(r.getElement(index), Direction.LR));
//					gare = true;
//				} else {
//					next = index + 1;
//					this.setPos(new Position(r.getElement(next), Direction.LR));
//					gare = false;
//				}
//
//			} else {
//				next = index - 1;
//				this.setPos(new Position(r.getElement(next), Direction.RL));
//			}
//
//		}

	}






	@Override 
	public void run() {
		while(true) {
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			move();
			System.out.println(toString());

		}
	}


}
