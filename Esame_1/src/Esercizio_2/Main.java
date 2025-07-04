package Esercizio_2;

public class Main {

	public static void main(String[] args) {
		
		Persona s1 = new Studente("Marco", "Rossi", "123456789");
		Persona pf1 = new Professore("Giuseppe", "Bianchi", "Matematica");
		Persona p1 = new Persona("Andrea", "Blui");
		
		Persona[] persone = {s1, pf1, p1};
		
		for(Persona persone1: persone) {
			persone1.presentati();
		}
	}
}
