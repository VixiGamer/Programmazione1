package cerchio;

public class Cerchio {
	float raggio;
	
	static float Perimetro(float raggio) {
		float p = (float) (2*Math.PI*raggio);
		return p;
	}
	
	static float Area(float raggio) {
		float a = (float) (Math.PI*raggio*raggio);
		return a;
	}
}
