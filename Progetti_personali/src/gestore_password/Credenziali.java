package gestore_password;

import java.io.Serializable;

public class Credenziali implements Serializable {
    private String sito;
    private String username;
    private String password;

    public Credenziali(String sito, String username, String password) {
        this.sito = sito;
        this.username = username;
        this.password = password;
    }

    public String toString() {
        return "Sito: " + sito + " | Utente: " + username + " | Password: " + password;
    }
}
