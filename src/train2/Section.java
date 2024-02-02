package train2;

/**
 * Représentation d'une section de voie ferrée. C'est une sous-classe de la
 * classe {@link Element}.
 *
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Section extends Element {

	
	private boolean isOccupied ; 
	
	public Section(String name) {
		super(name);
		super.size = 1 ; 
	
			
	}
	@Override
	public synchronized void trainOut() {
		isOccupied = false ;
		notifyAll();
		
			
	}
	@Override
	public synchronized void trainIn() {
		
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
