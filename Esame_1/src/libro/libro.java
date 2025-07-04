package libro;

public class libro {
	String nome;
	String autore;
	int anno;



	public libro(String nome, String autore, int anno) {
		this.nome = nome;
		this.autore = autore;
		this.anno = anno;
	}


	public void stampaInformazioni() {
	     System.out.println("Nome del libro: " + nome + " - Autore: " + autore + " - Anno: " + anno);
	}

}
