package Esercizio_3;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws AutenticazioneException {
		
		ArrayList<Utente> utente = new ArrayList<Utente>();
		
		Utente s1 = new Studente("Marco", "marco@gmail.com", "123456");
		Utente p1 = new Professore("Giuseppe", "giuseppe@istituto.com", "Matematica");
		Utente sg1 = new Segreterio("Luca", "luca@istituto.com", "23");
		
		utente.add(s1);
		utente.add(p1);
		utente.add(sg1);
		
		for (Utente utente1: utente) {
			utente1.presentati();
		}
		
		s1.autentica("studente123");
		p1.autentica("prof1990");
		
		

	}

}
