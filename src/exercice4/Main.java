package exercice4;

/**
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 */
public class Main {
	public static void main(String[] args) {

		// Q1.3
		Station A = new Station("GareA", 5);
		Station H = new Station("GareD", 5);
		Section AB = new Section("AB");
		Section BC = new Section("BC");
		Section CD = new Section("CD");
		Station DE = new Station("StationInter", 10);
		Section EF = new Section("EF");
		Section FG = new Section("FG");
		Section GH = new Section("GH");
		Railway r = new Railway(new Element[] { A, AB, BC, CD, DE , EF , FG , GH , H });
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
			e.printStackTrace();
		}
	}
}
