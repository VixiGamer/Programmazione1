package coda_stampa;

import java.util.LinkedList;
import java.util.Queue;

public class CodaStampa {
	
	private static Queue<Documento> coda = new LinkedList<Documento>();
	
	public void aggiungiDocumento(Documento d) {
		coda.add(d);
	}
	
	
	public void stampaProssimo() {
		
		if (coda.isEmpty() == true) {
			System.out.println("Nessun documento in coda");
		} else {
			System.out.println(coda.poll().titolo + " Stampato");
		}
		
	}

}
