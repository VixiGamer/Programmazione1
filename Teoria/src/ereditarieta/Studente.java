package ereditarieta;

public class Studente extends Persona {
	
	//Attributo della classe Studente
	String materiaPreferita;

	//Eredita il metodo Persona dalla classe Persona
	Studente(String nome, String cognome, String materiaPreferita) {
		super(nome, cognome);
		this.materiaPreferita = materiaPreferita;
	}
	
	//Metodo di questa classe (Studente)
	void Studia () {
		System.out.println("Sto studiando...");
	}
	
	//Ora facciamo l'override del metodo saluta della classe Persona
	@Override
	void Saluta() {
		System.out.println("Buongiorno prof.!");
	}
}

