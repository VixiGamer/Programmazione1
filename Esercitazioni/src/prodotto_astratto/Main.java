package prodotto_astratto;

public class Main {

	public static void main(String[] args) {
		
		Prodotto p1 = new Libro(19.99, 2);
		Prodotto p2 = new Libro(4.99, 19);
		
		p1.calcolaPrezzo();
		p2.calcolaPrezzo();

	}

}
