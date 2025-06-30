package forme;

public class Main {

	public static void main(String[] args) {
		FormaGeometrica f1 = new FormaGeometrica();
		Quadrato q1 = new Quadrato(5);
		Cerchio c1 = new Cerchio(3.2);
		Triangolo t1 = new Triangolo(4, 7);
		
		f1.calcolaArea();
		double aq1 = q1.calcolaArea();
		double ac1 = c1.calcolaArea();
		double at1 = t1.calcolaArea();
		
		System.out.println("Area quadrato: " + aq1);
		System.out.println("Area cerchio: " + ac1);
		System.out.println("Area triangolo: " + at1);

	}

}
