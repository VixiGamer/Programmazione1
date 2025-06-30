package prodotto_astratto;

public class Libro extends Prodotto {
	
	double p;
	int q;
	
	Libro(double p, int q) {
		this.p = p;
		this.q = q;
	}

	@Override
	void calcolaPrezzo() {
		System.out.println("Prezzo: " + p *q);
	}

}
