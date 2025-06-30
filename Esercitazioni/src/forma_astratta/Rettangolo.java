package forma_astratta;

public class Rettangolo extends Forma {

	float b;
	float h;
	
	Rettangolo(float b, float h) {
		this.b = b;
		this.h = h;
	}
	
	
	@Override
	void calcolaArea() {
		System.out.println(b * h);
	}

	@Override
	void calcolaPerimetro() {
		System.out.println((b * 2) + (h * 2));
	}

}

