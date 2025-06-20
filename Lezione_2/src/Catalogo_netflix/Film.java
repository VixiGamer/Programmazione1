package Catalogo_netflix;

public class Film extends Contenuto {
	
	int durata;

	public Film(String titolo, String genere, int anno, int durata) {
		super(titolo, genere, anno);
		this.durata = durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	@Override
	public String toString() {
		return "Film [durata=" + durata + ", titolo=" + titolo + ", genere=" + genere + ", anno=" + anno + "]";
	}

	
	
}
