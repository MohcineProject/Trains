package train3;

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
	
	
	
	
	

	// Each Variable count the number of trains going in a Direction (RL or LR ),
	// either before an intermediate station (1) or after it (2).
	// In case there is no intermediate section we only use (2)
	private int countRL1;
	private int countRL2;
	private int countLR1;
	private int countLR2;

	/**
	 * This method is used to determine the index of the middle station
	 * 
	 * @param elements : the elements of the railway
	 * @return return the index of the middle station or -1 if not found
	 */
	private int determineMedian(Element[] elements) {
		int n = -1;
		for (int i = 1; i < elements.length - 1; i++) {
			if (!elements[i].isSection())
				n = i;
		}

		return n;
	}

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
	
	private int indexOfElement(Element target) {

		for (int i = 0; i < elements.length; i++) {
			if (elements[i].equals(target)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method is used to increase the count of right to left trains elements on
	 * the railway. This changes either for the (1) or (2) part using the index of
	 * the element.
	 * 
	 * @param element
	 */

	public synchronized void addRL(Element element) {

		int n = determineMedian(elements);
		if (indexOfElement(element) > n)
			this.countRL2++;
		else
			this.countRL1++;
	}

	/**
	 * This method is used to increase the count of Left to right trains elements on
	 * the railway. This changes either for the (1) or (2) part using the index of
	 * the element.
	 * 
	 * @param element
	 */
	public synchronized void addLR(Element element) {

		int n = determineMedian(elements);
		if (indexOfElement(element) < n)
			this.countLR1++;
		else
			this.countLR2++;
	}

	/**
	 * This method is used to decrease the count of right to left trains elements on
	 * the railway. This changes either for the (1) or (2) part using the index of
	 * the element.
	 * 
	 * @param element
	 */
	public synchronized void decreaseRL(Element element) {
		int n = determineMedian(elements);
		if (indexOfElement(element) > n)
			this.countRL2--;
		else
			this.countRL1--;
	}

	/**
	 * This method is used to decrease the count of Left to right trains elements on
	 * the railway. This changes either for the (1) or (2) part using the index of
	 * the element.
	 * 
	 * @param element
	 */
	public synchronized void decreaseLR(Element element) {

		int n = determineMedian(elements);
		if (indexOfElement(element) < n)
			this.countLR1--;
		else
			this.countLR2--;
	}

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

	/**
	 * Verifies if trains are going only in one direction either in (1) or (2)
	 * 
	 * @param direction : the direction used to verify
	 * @param element   : the element used to determine either the train is in (1)
	 *                  or (2)
	 * @return a boolean that verifies the condition
	 */
	public boolean oneDirection(Direction direction, Element element) {
		int n = determineMedian(elements);
		int index = indexOfElement(element);
		if (direction == Direction.LR) {
			if (index < n)
				return (countRL1 == 0);
			else
				return countRL2 == 0;
		} else {
			if (index > n)
				return (countLR2 == 0);
			else
				return (countLR1 == 0);
		}
	}
}
