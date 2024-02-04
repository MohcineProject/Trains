package exercice1;

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
	
	
	
	
	
	

	// Q1.3
	/**
	 * Returns the element at the specified index
	 * 
	 * @param index : index of the element
	 * @return element with the specified index
	 */
	private Element getElement(int index) {

		return elements[index];
	}

	/**
	 * get The index of the element in a railway
	 * 
	 * @param target : the element to query
	 * @return the index of the element in the list
	 */
	// Q1.3
	private int indexOfElement(Element target) {

		for (int i = 0; i < elements.length; i++) {
			if (elements[i].equals(target)) {
				return i;
			}
		}
		return -1;
	}

	// Q1.3

	/**
	 * Simplifies getting the size of the railway elements
	 * 
	 * @return the size of the railway
	 */
	private int getSize() {

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

}
