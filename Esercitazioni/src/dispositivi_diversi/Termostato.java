package dispositivi_diversi;

public class Termostato extends Dispositivi {

	Termostato(String nome) {
		super(nome);
	}

	@Override
	public void eseguiAzione() {
		System.out.println("Temperatura impostata a 22°C");
	}

}
