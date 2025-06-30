package dispositivi_astratto;

public class Lampada extends Dispositivo {

	@Override
	void accendiDispositivo() {
		System.out.println("Lampada: ACCESA");
	}

	@Override
	void spegniDispositivo() {
		System.out.println("Lampada: SPENTA");
	}

}
