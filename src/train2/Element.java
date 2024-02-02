package train2;



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

	public abstract  void trainOut(); 

	public abstract void trainIn() ;

	public abstract boolean isOccupied() ;


	
	public void setRailway(Railway r) {
		if (r == null)
			throw new NullPointerException();

		this.railway = r;
	}

	@Override
	public String toString() {
		return this.name;
	}

	// Q1.3
	public int index() {
		return railway.indexOfElement(this);
	}

	// Q1.3
	public int sizeOfRailway() {
		return railway.getSize();
	}

	
	
	
	// Q1.3
//	public synchronized Element advance(Direction d) {
//		
//		if (d == Direction.LR) {
//			if (index() == sizeOfRailway() - 1) {
//				railway.decreaseLR();
//				notifyAll();
//				return this;
//			} else {
//				while (!railway.canAdvance2(this, d)) {
//					try {
//						wait();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				int previous = index() -1 ;
//				
//				if (index() == 0) {
//					railway.addLR();
//					previous = 0 ;
//					}
//				trainOut();
//				Element elet = railway.getElement(index() + 1);
//				Element previousElet = railway.getElement(previous);
//				elet.trainIn();
//				previousElet.Notify();
//				return elet ; 
//		
//				}
//		} else {
//			if (index() == 0) {
//				railway.decreaseRL();
//				notifyAll();
//				return this;
//			} else {
//				while(!railway.canAdvance2(this, d)) {
//					try {
//						wait();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				int previous = index() +1 ;
//				if (index() == sizeOfRailway()-1) {
//					railway.addRL();
//					previous = sizeOfRailway() -1 ; 
//				}
//				trainOut();
//				Element elet = railway.getElement(index() - 1);
//				Element previousElet = railway.getElement(previous);
//				elet.trainIn();
//				previousElet.Notify();
//				return elet ; 
//
//			}
//
//		}
//
//	}
	
	
	public synchronized void Notify() {
		notifyAll();
	}
	 
	public Element getNext(Direction direction ) {
		Element element = railway.getNextElement(this, direction) ;
		return element ;
	}
	public synchronized void  checkOneDirection(Direction direction) {
		
		 while(!railway.oneDirection(direction)) {
			 try {
				wait() ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 
	}

	public synchronized void announceDirection(Direction direction, boolean entering) {
		if (direction == Direction.LR) {
			if (entering) 
				railway.decreaseLR();
			else 
				railway.addLR();
		} else {
			if (entering) 
				railway.decreaseRL();
			else 
				railway.addRL();
		 }
		notifyAll();
	}
	
	 public abstract boolean isSection() ;
}
