package exercice2_3;

/**
 * Représentation de la position d'un train dans le circuit. Une position est
 * caractérisée par deux valeurs :
 * <ol>
 * <li>L'élément où se positionne le train : une gare (classe {@link Station})
 * ou une section de voie ferrée (classe {@link Section}).</li>
 * <li>La direction qu'il prend (enumération {@link Direction}) : de gauche à
 * droite ou de droite à gauche.</li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr> Modifié par Mayte
 *         Segarra
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 * 
 * @version 0.3
 */
public class Position implements Cloneable {

	private final Direction direction;
	private final Element pos;

	public Position(Element elt, Direction d) {
		if (elt == null || d == null)
			throw new NullPointerException();

		this.pos = elt;

		this.direction = d;
	}

	public Element getPos() {

		return pos;
	}

	@Override
	public Position clone() {
		try {
			return (Position) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(this.pos.toString());
		result.append(" going ");
		result.append(this.direction);
		return result.toString();
	}

	
	
	
	
	
	// Added code begins :

	
	
	
	/**
	 * Returns the next position to be taken by the train
	 * 
	 * @return the next position
	 */

	public Position nextPosition() {
		Element element = pos.getNext(direction);
		Direction direction = this.direction;
		if (!pos.isSection()) {
			if (pos == element) {
				pos.announceDirection(direction, true);
				direction = direction.change();

			} else {
				pos.checkOneDirection(direction);
				pos.announceDirection(direction, false);
			}
		}
		element.enter();
		pos.leave();
		return new Position(element, direction);
	}
}
