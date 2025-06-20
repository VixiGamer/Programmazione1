package forma;

public class Cerchio extends Forma{
	
	private double raggio;

	
	
	public Cerchio(double raggio) {
		this.raggio = raggio;
	}

	
	@Override
	double area() {
		return raggio * raggio * Math.PI;
	}

	@Override
	double perimetro() {
		return (raggio * 2) * Math.PI;
	}
}
