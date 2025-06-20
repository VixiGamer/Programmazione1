package forma;

class Triangolo extends Forma{
	private double base;
	private double altezza;
	
	private double lato1, lato2, lato3;
	
	public Triangolo(double base, double altezza, double lato1, double lato2, double lato3) {
		
		this.base = base;
		this.altezza = altezza;
		this.lato1 = lato1;
		this.lato2 = lato2;
		this.lato3 = lato3;
		
	}

	
	@Override
	double area() {
		return (base * altezza) / 2;
	}

	@Override
	double perimetro() {
		// TODO Auto-generated method stub
		return lato1 + lato2 + lato3;
	}

}
