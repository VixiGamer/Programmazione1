package coda_stampa;

public class Main {

	public static void main(String[] args) {
		
		CodaStampa codastampa = new CodaStampa();
		
		Documento d1 = new Documento("Mappe Java", 10);
		Documento d2 = new Documento("Mappe C", 2);
		Documento d3 = new Documento("Mappe Data", 13);
		
		codastampa.aggiungiDocumento(d1);
		codastampa.aggiungiDocumento(d2);
		codastampa.aggiungiDocumento(d3);
		
		codastampa.stampaProssimo();
		codastampa.stampaProssimo();
		codastampa.stampaProssimo();

	}

}
