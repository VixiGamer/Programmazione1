package libro;

public class Libro {
	
	String titolo;
	String autore;
	int anno;
	
	Libro(String titolo, String autore, int anno) {
		this.titolo = titolo;
		this.autore = autore;
		this.anno = anno;
		System.out.println("Titolo: " + titolo + "\nAutore: " + autore + "\nAnno: " + anno + "\n");
	}
	

}
