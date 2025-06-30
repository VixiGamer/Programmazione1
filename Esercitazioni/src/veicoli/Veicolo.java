package veicoli;

public class Veicolo {
	protected String marca;
	
	Veicolo(String marca) {
		this.marca = marca;
	}
	
	void mostraMarca() {
		System.out.println(marca);
	}

}
