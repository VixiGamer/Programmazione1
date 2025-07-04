package coda_stampa;

public class Documento {
	
	String titolo;
	int paggine;
	
	public Documento(String titolo, int paggine) {
		this.titolo = titolo;
		this.paggine = paggine;
	}
	
	public void stampa(String titolo, int paggine) {
		System.out.println("Stampando " + titolo + " - " + paggine + "paggine");
	}

}
