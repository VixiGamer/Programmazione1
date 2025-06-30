package dispositivi_astratto;

public class Main {

	public static void main(String[] args) {

		Dispositivo t1 = new Televisore();
		Dispositivo l1 = new Lampada();
		
		t1.accendiDispositivo();
		t1.spegniDispositivo();
		
		l1.accendiDispositivo();
		l1.spegniDispositivo();
		
	}
}
