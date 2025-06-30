package veicoli;

public class Auto extends Veicolo{
	protected String modello;

	Auto(String marca, String modello) {
		super(marca);
		this.modello = modello;
	}
	
	void mostraModello() {
		System.out.println("Modello: " + modello);
	}

}
