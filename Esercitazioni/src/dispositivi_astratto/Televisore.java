package dispositivi_astratto;

public class Televisore extends Dispositivo {

	@Override
	void accendiDispositivo() {
		System.out.println("Televisore: ACCESO");
	}

	@Override
	void spegniDispositivo() {
		System.out.println("Televisore: SPENTO");
	}

}
