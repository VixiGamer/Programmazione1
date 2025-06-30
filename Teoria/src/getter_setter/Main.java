package getter_setter;

public class Main {

	public static void main(String[] args) {
		
		Persona p1 = new Persona("Viggo", "PN");
		//Se ora voglio stampare il nome e il cognome usiamo:
		System.out.println(p1.getNome() + " " + p1.getCognome());

		//Se ora voglio cambiare il cognome facciamo:
		p1.setCognome("Ponturo Nygaard");
		System.out.println(p1.getNome() + " " + p1.getCognome());
		
	}
}

