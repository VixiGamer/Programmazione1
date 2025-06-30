package forma_astratta;

public class Main {

	public static void main(String[] args) {
		
		Forma q1 = new Quadrato(7);
		Forma r1 = new Rettangolo(9, 4);
		
		q1.calcolaArea();
		q1.calcolaPerimetro();
		
		r1.calcolaArea();
		r1.calcolaPerimetro();

	}
}

