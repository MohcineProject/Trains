package train4;

/**
 * Représentation d'une section de voie ferrée. C'est une sous-classe de la
 * classe {@link Element}.
 *
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Section extends Element {

	private int count ;
	 
	
	public Section(String name) {
		super(name);
		super.size = 1 ;
		this.count = 0 ; 
	
			
	}
	@Override
	public synchronized void leave() {
		this.count -- ; 
		notifyAll();
		
			
	}
	@Override
	public synchronized void enter() {
		
		while(isOccupied) { 
			try {
				wait() ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		isOccupied = true ;
				
	}
	@Override
	public synchronized boolean isOccupied() {
		return isOccupied ; 
	}

	
	@Override 
	public synchronized boolean isSection() {
		return true ;
	}
	 
	


}
