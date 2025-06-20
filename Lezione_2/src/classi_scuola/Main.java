package classi_scuola;

public class Main {

	public static void main(String[] args) {
		
		Person p1 = new Person("Viggo", "Ponturo Nygaard", 21);
		Studenti s1 = new Studenti("Giuseppe", "Gravagno", 21, 9.7f, 123456789);
		Docenti d1 = new Docenti("Ettore", "Condemi", 24, 1500, 2345678);
		
		System.out.println(p1.nome + " | " + p1.cognome + " | " + p1.eta);
		System.out.println(s1.nome + " | " + s1.cognome + " | " + s1.eta + " | Media: " + s1.media + "/10 | Matricola: " + s1.matricola);
		System.out.println(d1.nome + " | " + d1.cognome + " | " + d1.eta + " | Stipendio: " + d1.stipendio + "€ | Matricola: " + d1.matricola);
		//System.out.println(s1.toString());
	}
}
