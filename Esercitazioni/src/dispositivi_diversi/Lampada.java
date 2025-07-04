package dispositivi_diversi;

public class Lampada extends Dispositivi {

	Lampada(String nome) {
		super(nome);
	}

	@Override
	public void eseguiAzione() {
		System.out.println("La lampada si accende");
	}

}
