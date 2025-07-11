import java.io.Serializable; // Import aggiunto per Serializable

public class Gioco implements Serializable {

    private static final long serialVersionUID = 1L; // Aggiunto serialVersionUID

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

    public enum ListaGioco {
        Lo_voglio_subito,
        Lo_voglio_ma_non_subito,
        Lo_voglio_per_collezione,
        Non_lo_so,
        Non_ce_bisogno
    }

    public enum ListaShop {
        Steam,
        InstantGaming,
        G2A,
        Kinguin
    }

    // Getter e Setter
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

    @Override
    public String toString() { // Override per una migliore visualizzazione
        return nome + " (€" + String.format("%.2f", prezzo) + ") - " + shop.name();
    }
}