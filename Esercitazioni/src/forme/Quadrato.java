package forme;

public class Quadrato extends FormaGeometrica {
	protected int l;
	
	Quadrato(int l) {
		this.l = l;
		
	}

	public double calcolaArea() {
		return l * l;
	}
	

}
