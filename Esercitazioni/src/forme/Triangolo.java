package forme;

public class Triangolo extends FormaGeometrica {
	protected double b;
	protected double h;
	
	Triangolo(double b, double h){
		this.b = b;
		this.h = h;
	}
	
	public double calcolaArea() {
		return (b*h) / 2;
	}

}
