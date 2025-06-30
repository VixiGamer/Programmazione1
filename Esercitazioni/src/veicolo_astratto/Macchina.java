package veicolo_astratto;

public class Macchina extends Veicolo {
	
	int v;

	@Override
	void calcolaVelocita(int n) {
		
		System.out.println("Velocita: " + n + "km/h");
		
	}


}
