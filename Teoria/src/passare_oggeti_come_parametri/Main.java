package passare_oggeti_come_parametri;

public class Main {

	public static void main(String[] args) {
		
		Persona p1 = new Persona("Viggo", "Ponturo Nygaard", 21);
		Persona p2 = new Persona("Silas", "Ponturo Nygaard", 21);
		
		p1.Saluta(p2);
		p2.Saluta(p1);
		
	}
}

