package exercice1;

/**
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 */
public class Main {
	public static void main(String[] args) {
		
			
			// Q1.3 
			// the main method to test the movement of one train
		
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
			try {
				t1 = new Train("1", p);
				t1.start();
			} catch (BadPositionForTrainException e) {
				e.printStackTrace();
			}

	}
}
