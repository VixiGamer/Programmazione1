import java.io.*;
import java.util.*;

public class GestoreGiochi implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String FILENAME = "giochi.dat"; // Costante per il nome del file
    private ArrayList<Gioco> giochi = new ArrayList<>();

    public void aggiungiGioco(Gioco g) {
        if (g != null) {
            giochi.add(g);
            salvaSuFile(); // Salva ogni volta che aggiungi
        }
    }

    public void rimuoviGioco(Gioco g) {
        if (g != null) {
            giochi.remove(g);
            salvaSuFile(); // Salva ogni volta che rimuovi
        }
    }

    public List<Gioco> getTuttiIGiochi() {
        // Restituisce una copia per prevenire modifiche esterne dirette
        return new ArrayList<>(giochi);
    }

    public void salvaSuFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(giochi);
            // System.out.println("Giochi salvati con successo in " + FILENAME);
        } catch (IOException e) {
            System.err.println("Errore nel salvataggio dei giochi: " + e.getMessage());
            // Potresti anche loggare l'eccezione o mostrarla all'utente
        }
    }

    @SuppressWarnings("unchecked") // Sopprime l'avviso di cast non controllato
    public void caricaDaFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList) {
                giochi = (ArrayList<Gioco>) obj;
                // System.out.println("Giochi caricati con successo da " + FILENAME);
            } else {
                System.err.println("Il file non contiene un ArrayList di Giochi valido.");
                giochi = new ArrayList<>(); // Inizializza una lista vuota per evitare NPE
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + FILENAME + " non trovato. Creazione di una nuova lista.");
            giochi = new ArrayList<>(); // Inizializza una nuova lista se il file non esiste
        } catch (IOException e) {
            System.err.println("Errore di I/O durante il caricamento dei giochi: " + e.getMessage());
            giochi = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.err.println("Errore: Classe Gioco non trovata durante il caricamento. " + e.getMessage());
            giochi = new ArrayList<>();
        }
    }
}