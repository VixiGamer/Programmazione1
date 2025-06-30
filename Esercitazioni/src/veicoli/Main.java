package veicoli;

public class Main {

	public static void main(String[] args) {
		
		Veicolo v1 = new Veicolo("Tesla");
		Auto a1 = new Auto("Tesla", "Model X");
		Moto m1 = new Moto("Pegassi", 1200);
		
		v1.mostraMarca();
		a1.mostraModello();
		m1.mostraCilindrata();
	}
	
	/* In questo esercizio, Auto e Moto dipendono da veicolo */

}
