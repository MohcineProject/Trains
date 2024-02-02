package train;



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
	private int count =0;
	
	private Direction d ;  

	
	protected Element(String name) {
		if (name == null)
			throw new NullPointerException();

		this.name = name;
		this.size = 1 ; 
	
	}

	public synchronized void trainOut() {
		if (!isEmpty())
			this.count = count - 1;
	}

	public synchronized void trainIn() {
		if (!isOccupied())
			this.count = count + 1;
	}

	public synchronized boolean isOccupied() {
		return (count == size);
	}

	public synchronized boolean isEmpty() {
		return (count == 0);
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

	// Q1.3
	public int index() {
		return railway.indexOfElement(this);
	}

	// Q1.3
	public int sizeOfRailway() {
		return railway.getSize();
	}

	
	
	
	// Q1.3
	public synchronized Element advance(Direction d) {
		
		if (d == Direction.LR) {
			if (index() == sizeOfRailway() - 1) {
				railway.decreaseLR();
				notifyAll();
				return this;
			} else {
				while (!railway.canAdvance2(this, d)) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				int previous = index() -1 ;
				
				if (index() == 0) {
					railway.addLR();
					previous = 0 ;
					}
				trainOut();
				Element elet = railway.getElement(index() + 1);
				Element previousElet = railway.getElement(previous);
				elet.trainIn();
				previousElet.Notify();
				return elet ; 
		
				}
		} else {
			if (index() == 0) {
				railway.decreaseRL();
				notifyAll();
				return this;
			} else {
				while(!railway.canAdvance2(this, d)) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				int previous = index() +1 ;
				if (index() == sizeOfRailway()-1) {
					railway.addRL();
					previous = sizeOfRailway() -1 ; 
				}
				trainOut();
				Element elet = railway.getElement(index() - 1);
				Element previousElet = railway.getElement(previous);
				elet.trainIn();
				previousElet.Notify();
				return elet ; 

			}

		}

	}
	
	
	public synchronized void Notify() {
		notifyAll();
	}
	 
	public Direction getDirection() {
		return d;
	}


	public void setDirection(Direction d) {
		this.d = d;
	}


}
