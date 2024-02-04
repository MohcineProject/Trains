package train4;

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
	protected int size;

	protected Element(String name) {
		if (name == null)
			throw new NullPointerException();

		this.name = name;

	}

	public abstract void leave();

	public abstract void enter();

	public abstract boolean isOccupied();

	public void setRailway(Railway r) {
		if (r == null)
			throw new NullPointerException();

		this.railway = r;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public synchronized void Notify() {
		notifyAll();
	}

	public Element getNext(Direction direction) {
		Element element = railway.getNextElement(this, direction);
		return element;
	}

	public synchronized void checkOneDirection(Direction direction) {

		while (!railway.oneDirection(direction, this)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public synchronized void announceDirection(Direction direction, Element next) {
		if (direction == Direction.LR) {
			if (!next.isSection())
				railway.decreaseLR(this);
			else
				railway.addLR(this);
		} else {
			if (!next.isSection())
				railway.decreaseRL(this);
			else
				railway.addRL(this);
		}

		notifyAll();
	}

	public abstract boolean isSection();
}
