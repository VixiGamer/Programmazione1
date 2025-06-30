package veicoli;

public class Moto extends Veicolo{
	protected int cilindrata;

	Moto(String marca, int cilindrata) {
		super(marca);
		this.cilindrata = cilindrata;
	}
	
	void mostraCilindrata() {
		System.out.println("Cilindrata: " + cilindrata);
	}

}
