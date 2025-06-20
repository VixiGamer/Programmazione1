package arraylist_prodotti;
import java.time.LocalDate;

public class Prodotto {
	
	private String nome;
	private String marca;
	private double prezzo;
	private LocalDate scadenza;
	
	public Prodotto(String nome, String marca, double prezzo, LocalDate scadenza) {
		this.nome = nome;
		this.marca = marca;
		this.prezzo = prezzo;
		this.scadenza = scadenza;
	}
	
	
	//
	public boolean dateNow() {
		return scadenza.isBefore(LocalDate.now());
	}
	//
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public LocalDate getScadenza() {
		return scadenza;
	}

	public void setScadenza(LocalDate scadenza) {
		this.scadenza = scadenza;
	}


	@Override
	public String toString() {
		return "Nome: " + nome + "\n"
		     + "Marca: " + marca + "\n"
		     + "Prezzo: " + String.format("%.2f", prezzo) + "€\n"
		     + "Scadenza: " + scadenza;
	}
	
	
	
}
