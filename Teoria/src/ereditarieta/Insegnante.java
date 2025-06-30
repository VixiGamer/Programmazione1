package ereditarieta;

public class Insegnante extends Persona {
	
	//Attributo della classe Insegnante
	String materia;

	//Eredita il metodo Persona dalla classe Persona
	Insegnante(String nome, String cognome, String materia) {
		super(nome, cognome);
		this.materia = materia;
	}
	
	//Metodo di questa classe (Insegnante)
	void Insegna () {
		System.out.println("Sto insegnando...");
	}
	
	//Ora facciamo l'override del metodo saluta della classe Persona
	@Override
	void Saluta() {
		System.out.println("Buongiorno ragazzi!");
	}
}

