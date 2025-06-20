package forma;

public class Rettangolo extends Forma {
	
	private double base;
	private double altezza;
	

	public Rettangolo(double base, double altezza) {
		this.base = base;
		this.altezza = altezza;
	}
	
	

	@Override
	double area() {
		return base * altezza;
	}

	@Override
	double perimetro() {
		// TODO Auto-generated method stub
		return (base * 2) + (altezza * 2);
	}
	
}
