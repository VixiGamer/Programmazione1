package forme;

public class Cerchio extends FormaGeometrica {

	protected double r;
	
	Cerchio(double r){
		this.r = r;
	}
	
	public double calcolaArea() {
		return Math.PI*r*r;
	}
	
}
