package dispositivi_diversi;

public class Tapparella extends Dispositivi {

	Tapparella(String nome) {
		super(nome);
	}

	@Override
	public void eseguiAzione() {
		System.out.println("La tapparella si abassa");
	}

}
