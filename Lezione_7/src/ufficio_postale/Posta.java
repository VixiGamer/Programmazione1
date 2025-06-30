package ufficio_postale;

import java.util.LinkedList;
import java.util.Queue;

public class Posta {
	
	private static Queue<Persona> coda = new LinkedList<>();
	
	static void entraInCoda(Persona p) {
		coda.add(p);
	}
	
	static void chiEIlProssimo() {
		System.out.println("Il prossimo ad essere servito è: " + coda.peek().getNome());
	}
	
	static void servireProssimo() {
		System.out.println("E' satto servito: " + coda.poll().getNome());
	}
	
	static void mostraCoda() {
		for (Persona person: coda) {
			System.out.println(person.getNome());
		}
	}

}
