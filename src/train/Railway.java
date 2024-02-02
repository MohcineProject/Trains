package train;

/**
 * Représentation d'un circuit constitué d'éléments de voie ferrée : gare ou
 * section de voie
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Railway {
	private final Element[] elements;

	private int countRL;
	private int countLR;

	public Railway(Element[] elements) {
		if (elements == null)
			throw new NullPointerException();

		this.elements = elements;
		for (Element e : elements)
			e.setRailway(this);

	}

	public Element getElement(int index) {
		return elements[index];
	}

	public void addRL() {
		this.countRL++;
	}

	public void addLR() {
		this.countLR++;
	}

	public void decreaseRL() {
		this.countRL--;
	}

	public void decreaseLR() {
		this.countLR--;
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

	// Q1.3
	public int indexOfElement(Element target) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].equals(target)) {
				return i;
			}
		}
		return -1;
	}

	// Q1.3
	public int getSize() {
		return elements.length;
	}

//	public  boolean canAdvance(Element elet, Direction d) {
//		int index = indexOfElement(elet);
//		if (index == 0) {
//			if (d == Direction.LR) {
//				Section nextSection = (Section) getElement(index + 1); // Problem if we have 2 stations only 
//				if (countRL == 0 && !nextSection.isOccupied()) {
//					((Station) elet).trainOut();
//					nextSection.occupy();
//					return true;
//				} else {
//					return false;
//				}
//			} else {
//				return true;
//			}
//		} else if (index == getSize() - 1) {
//			if (d == Direction.RL) {
//				Section nextSection = (Section) getElement(index - 1);
//
//				if (countLR == 0 && !nextSection.isOccupied()) {
//					((Station) elet).trainOut();
//					nextSection.occupy();
//					return true;
//				} else {
//					return false;
//				}
//			} else {
//				return true;
//			}
//		} else {
//			Element nextElement;
//			if (d == Direction.LR) {
//				nextElement = getElement(index + 1);
//			} else {
//				nextElement = getElement(index - 1);
//			}
//			if (nextElement instanceof Section) {
//				Section nextSection = (Section) nextElement;
//				if (!nextSection.isOccupied()) {
//					((Section) elet).free();
//					nextSection.occupy();
//					return true;
//				} else {
//					return false;
//				}
//			} else {
//				Station nextStation = (Station) nextElement;
//				if (!nextStation.isOccupied()) {
//					((Section) elet).free();
//					nextStation.trainIn();
//					return true;
//				} else {
//					return false;
//				}
//			}
//		}
//		//			} else {
//		//				Element nextElement = getElement(index - 1);
//		//				if (nextElement instanceof Section) {
//		//					Section nextSection = (Section) nextElement;
//		//					if (!nextSection.isOccupied()) {
//		//						((Section) elet).free();
//		//						nextSection.occupy();
//		//						return true;
//		//					} else {
//		//						return false;
//		//					}
//		//				} else {
//		//					Station nextStation = (Station) nextElement;
//		//					if (!nextStation.isFull()) {
//		//						((Section) elet).free();
//		//						nextStation.trainIn();
//		//						return true;
//		//					} else {
//		//						return false;
//		//					}
//		//				}
//		//			}
//	}
//
//	// int index = indexOfElement(elet);
//	// boolean canGo = true ;
//	// if (d == Direction.RL) {
//	//
//	// if (index == getSize()-1) {
//	// for (int i =1 ; i<getSize()-2 ; i++) {
//	// if(getElement(i).getDirection() == Direction.LR)
//	// canGo = false ;
//	//
//	//
//	// }
//	// Section after = (Section) getElement(getSize()-2) ;
//	// if ()
//	//
	
public Element getNextElement(Element elet, Direction direction) {

	int index = indexOfElement(elet);
	if (direction == Direction.LR) {
	if (index == getSize() - 1 ) {
		return elet ; 
		
	} else {
		return getElement(index+1);
	}
	
	} else {
		if (index == 0 ) {
			return elet ; 
			
		} else {
			return getElement(index-1);
		}	
	}
	
	
}

	
public boolean oneDirection(Direction direction ) {
	if (direction == Direction.LR) {
		return (countRL == 0) ;
	} else {
		return (countLR == 0) ;
	}
	
	
}


	
public boolean canAdvance2(Element elet , Direction direction ) {
	int index = indexOfElement(elet);
	if (index == getSize() - 1 || index == 0 ) {
		Element section =  getNextElement(elet, direction);
		boolean SectionisOccupied = section.isOccupied() ;
		boolean OneDirection = oneDirection(direction) ;
		return ((!SectionisOccupied)&& OneDirection);
				
	} else {
		if (getNextElement(elet, direction) instanceof Section) {
			Section section = (Section) getNextElement(elet, direction);
			return !section.isOccupied() ;
		}
		else {
			return true  ;
		}
	}
	 
}
	public  static void main(String[] args) {

		// Q1.3
		Station A = new Station("GareA", 5);
		Station D = new Station("GareD", 5);
		Section AB = new Section("AB");
		Section BC = new Section("BC");
		Section CD = new Section("CD");
		Railway r = new Railway(new Element[] { A, AB, BC, CD, D });
		System.out.println("The railway is:");
		System.out.println("\t" + r);
		Position p = new Position(A, Direction.LR);

		Train t1;
		Train t2;
		Train t3;
		Train t4;
		Train t5;
		try {
			t1 = new Train("1", p);
			t2 = new Train("2", p);
			t3 = new Train("3", p);
			t4 = new Train("4", p);
			t5 = new Train("5", p);
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
		} catch (BadPositionForTrainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
