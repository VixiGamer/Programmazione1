package Catalogo_netflix;

public class Contenuto {
	
	String titolo;
	String genere;
	int anno;
	
	public Contenuto(String titolo, String genere, int anno) {
		this.titolo = titolo;
		this.genere = genere;
		this.anno = anno;
	}

	//-------------------------------------------------------------------------
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	@Override
	public String toString() {
		return "Contenuto [titolo=" + titolo + ", genere=" + genere + ", anno=" + anno + "]";
	}
	
	//-------------------------------------------------------------------------
	
	
	
	
}
