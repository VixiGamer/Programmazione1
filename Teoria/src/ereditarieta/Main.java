package ereditarieta;

public class Main {

	public static void main(String[] args) {
		
		//Creo un oggetto Studente
		Studente s1 = new Studente("Silas", "PN", "Motoria");
		//Creo un oggetto Insegante
		Insegnante i1 = new Insegnante("Viggo", "PN", "Matematica");

		s1.Studia();
		s1.Saluta();
		
		i1.Insegna();
		i1.Saluta();
		
	}

}

