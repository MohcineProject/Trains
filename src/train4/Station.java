package train4;

/**
 * Représentation d'une gare. C'est une sous-classe de la classe
 * {@link Element}. Une gare est caractérisée par un nom et un nombre de quais
 * (donc de trains qu'elle est susceptible d'accueillir à un instant donné).
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Station extends Element {

	private int count = 0;

	public Station(String name, int size) {
		super(name);
		if (name == null || size <= 0)
			throw new NullPointerException();
		super.size = size;

	}

	// Added code begins :

	@Override
	public synchronized void enter() {
		while (isOccupied())
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		this.count++;
	}

	@Override
	public synchronized void leave() {
		if (!isEmpty())
			this.count--;
		notifyAll();

	}

	@Override
	public synchronized boolean isOccupied() {
		return (count == size);
	}

	public synchronized boolean isEmpty() {
		return (count == 0);
	}

	@Override
	public boolean isSection() {
		return false;
	}

}
