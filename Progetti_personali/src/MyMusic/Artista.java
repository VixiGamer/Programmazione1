// Artista.java
package MyMusic;

import java.io.Serializable;

public class Artista implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nomeArtista;
    private String titoloAlbum;
    private int annoPubblicazione;
    private String genere;
    private FormatoMusica formato;
    private TipoMusica tipoMusica;
    private boolean deluxeEdition;
    private boolean edizioneLimitata;
    private String esclusive;
    private String percorsoFoto;
    private StatoCollezione stato;
    private double prezzo; // NUOVO CAMPO
    private String luogoAcquisto; // NUOVO CAMPO (può essere un URL o nome negozio)

    public Artista(String nomeArtista, String titoloAlbum, int annoPubblicazione, String genere,
                   FormatoMusica formato, TipoMusica tipoMusica, boolean deluxeEdition, boolean edizioneLimitata, String esclusive,
                   String percorsoFoto, StatoCollezione stato, double prezzo, String luogoAcquisto) { // COSTRUTTORE AGGIORNATO
        this.nomeArtista = nomeArtista;
        this.titoloAlbum = titoloAlbum;
        this.annoPubblicazione = annoPubblicazione;
        this.genere = genere;
        this.formato = formato;
        this.tipoMusica = tipoMusica;
        this.deluxeEdition = deluxeEdition;
        this.edizioneLimitata = edizioneLimitata;
        this.esclusive = esclusive;
        this.percorsoFoto = percorsoFoto;
        this.stato = stato;
        this.prezzo = prezzo; // Inizializza il nuovo campo
        this.luogoAcquisto = luogoAcquisto; // Inizializza il nuovo campo
    }

    public enum FormatoMusica {
        Vinile,
        CD,
        Digitale,
        Cassetta
    }

    public enum TipoMusica {
        Album,
        EP,
        Singolo,
        Compilation,
        Live
    }

    public enum StatoCollezione {
        Posseduto,
        Desiderato
    }

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(titoloAlbum).append(" - ").append(nomeArtista).append("\n");
	    sb.append(annoPubblicazione).append(" | ").append(genere).append(" | ").append(tipoMusica).append("\n");
	    sb.append(formato);
	    if (deluxeEdition) sb.append(" | Deluxe Edition");
	    if (edizioneLimitata) sb.append(" | Edizione Limitata");
	    return sb.toString();
	}

    // Getter e Setter esistenti

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public String getTitoloAlbum() {
        return titoloAlbum;
    }

    public void setTitoloAlbum(String titoloAlbum) {
        this.titoloAlbum = titoloAlbum;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public FormatoMusica getFormato() {
        return formato;
    }

    public void setFormato(FormatoMusica formato) {
        this.formato = formato;
    }
    
    public TipoMusica getTipoMusica() {
        return tipoMusica;
    }

    public void setTipoMusica(TipoMusica tipoMusica) {
        this.tipoMusica = tipoMusica;
    }
    
	public boolean isDeluxeEdition() {
		return deluxeEdition;
	}
	
	public void setDeluxeEdition(boolean deluxeEdition) {
		this.deluxeEdition = deluxeEdition;
	}

    public boolean isEdizioneLimitata() {
        return edizioneLimitata;
    }

    public void setEdizioneLimitata(boolean edizioneLimitata) {
        this.edizioneLimitata = edizioneLimitata;
    }

    public String getEsclusive() {
        return esclusive;
    }

    public void setEsclusive(String esclusive) {
        this.esclusive = esclusive;
    }

    public String getPercorsoFoto() {
        return percorsoFoto;
    }

    public void setPercorsoFoto(String percorsoFoto) {
        this.percorsoFoto = percorsoFoto;
    }

    public StatoCollezione getStato() {
        return stato;
    }

    public void setStato(StatoCollezione stato) {
        this.stato = stato;
    }

    // NUOVI GETTER E SETTER
    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getLuogoAcquisto() {
        return luogoAcquisto;
    }

    public void setLuogoAcquisto(String luogoAcquisto) {
        this.luogoAcquisto = luogoAcquisto;
    }
}