package forma_astratta;

public class Quadrato extends Forma{

	float l;
	
	Quadrato(float l) {
		this.l = l;
	}
	
	
	@Override
	void calcolaArea() {
		System.out.println(l * l);
	}

	@Override
	void calcolaPerimetro() {
		System.out.println(l * 4);
	}

}

