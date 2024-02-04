package exercice2_3;

/**
 * Représentation d'un circuit constitué d'éléments de voie ferrée : gare ou
 * section de voie
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Railway {
	private final Element[] elements;

	public Railway(Element[] elements) {
		if (elements == null)
			throw new NullPointerException();

		this.elements = elements;
		for (Element e : elements)
			e.setRailway(this);

	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Element e : this.elements) {
			if (first)
				first = false;
			else
				result.append("--");
			result.append(e);
		}
		return result.toString();
	}

	
	
	
	// Added code begins :
	
	
	
	
	
	// The counts are used to count the number of trains going in the two directions
	// Right to Left and Left to Right
	private int countRL;
	private int countLR;

	/**
	 * Returns the element at the specified index
	 * 
	 * @param index : index of the element
	 * @return element with the specified index
	 */

	public Element getElement(int index) {
		return elements[index];
	}

	/**
	 * This method is used to increase the count of right to left trains elements on
	 * the railway.
	 * 
	 */

	public void addRL() {
		this.countRL++;
	}

	/**
	 * This method is used to increase the count of Left to right trains elements on
	 * the railway.
	 * 
	 */

	public void addLR() {
		this.countLR++;
	}

	/**
	 * This method is used to decrease the count of right to left trains elements on
	 * the railway.
	 * 
	 */

	public void decreaseRL() {
		this.countRL--;
	}

	/**
	 * This method is used to decrease the count of Left to right trains elements on
	 * the railway.
	 * 
	 */

	public void decreaseLR() {
		this.countLR--;
	}

	/**
	 * get The index of the element in a railway
	 * 
	 * @param target : the element to query
	 * @return the index of the element in the list
	 */

	public int indexOfElement(Element target) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].equals(target)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Simplifies getting the size of the railway elements
	 * 
	 * @return the size of the railway
	 */
	public int getSize() {
		return elements.length;
	}

	/**
	 * Return the next possible element for a train depending on the direction
	 * 
	 * @param elet      : the current element a train is in
	 * @param direction : the direction of the train
	 * @return the next possible element for the train
	 */
	public Element getNextElement(Element elet, Direction direction) {

		int index = indexOfElement(elet);
		if (direction == Direction.LR) {
			if (index == getSize() - 1) {
				return elet;

			} else {
				return getElement(index + 1);
			}

		} else {
			if (index == 0) {
				return elet;

			} else {
				return getElement(index - 1);
			}
		}

	}

	/**
	 * Verifies if trains are going only in one direction either in (1) or (2)
	 * 
	 * @param direction : the direction used to verify
	 * @param element   : the element used to determine either the train is in (1)
	 *                  or (2)
	 * @return a boolean that verifies the condition
	 */

	public boolean oneDirection(Direction direction) {
		if (direction == Direction.LR) {
			return (countRL == 0);
		} else {
			return (countLR == 0);
		}

	}

}
