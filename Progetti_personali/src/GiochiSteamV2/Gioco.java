package GiochiSteamV2;

import java.io.Serializable;

public class Gioco implements Serializable {

	private static final long serialVersionUID = 1L; // serialVersionUID per Gioco

	private String nome;
	private Double prezzo;
	private ListaShop shop;
	private ListaGioco lista;
	private String steamlink;

	public Gioco(String nome, Double prezzo, ListaShop shop, ListaGioco lista, String steamlink) {
		this.nome = nome;
		this.prezzo = prezzo;
		this.shop = shop;
		this.lista = lista;
		this.steamlink = steamlink;
	}

	// Metodo toString per visualizzare il gioco correttamente nelle JComboBox e altrove
    @Override
    public String toString() {
        return nome + " (€" + String.format("%.2f", prezzo) + ") - " + shop;
    }

	public enum ListaGioco {
	    Lo_voglio_subito,
	    Lo_voglio_ma_non_subito,
	    Lo_voglio_per_collezione,
	    Non_lo_so,
	    Non_ce_bisogno;

        @Override
        public String toString() {
            // Sostituisci i trattini bassi con spazi
            String formattedName = this.name().replace("_", " ");

            // Converti l'intera stringa in minuscolo e poi capitalizza solo la prima lettera
            if (formattedName.isEmpty()) {
                return "";
            }
            return Character.toUpperCase(formattedName.charAt(0)) + formattedName.substring(1).toLowerCase();
        }
	}

	public enum ListaShop {
		Steam,
		InstantGaming,
		Kinguin,
		G2A
	}


	//Getter e Setter
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public ListaGioco getLista() {
		return lista;
	}

	public void setLista(ListaGioco lista) {
		this.lista = lista;
	}

	public ListaShop getShop() {
		return shop;
	}

	public void setShop(ListaShop shop) {
		this.shop = shop;
	}

	public String getSteamlink() {
		return steamlink;
	}

	public void setSteamlink(String steamlink) {
		this.steamlink = steamlink;
	}
}