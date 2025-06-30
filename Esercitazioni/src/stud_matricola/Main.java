package stud_matricola;

public class Main {

	public static void main(String[] args) {
		Studente s1 = new Studente(12345, "Viggo");
		
		//Siccome  eprivate dobbiamo fare cosi
		System.out.println(s1.getMatri() + " " + s1.getNome());
		//Risultato '12345 Viggo'
		
		//E NON COSI
		System.out.println(s1);
		//Risultato 'stud_matricola.Studente@c4437c4'

	}

}
