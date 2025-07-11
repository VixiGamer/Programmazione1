package GiochiSteamV2;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class GestoreGiochi implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Gioco> giochi = new ArrayList<>();

    public void aggiungiGioco(Gioco g) {
        giochi.add(g);
        salvaSuFile();
    }

    public void rimuoviGioco(Gioco g) {
        giochi.remove(g);
        salvaSuFile();
    }

    public List<Gioco> getTuttiIGiochi() {
        return new ArrayList<>(giochi);
    }

    public void salvaSuFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("giochi.dat"))) {
            oos.writeObject(giochi);
            System.out.println("Dati salvati con successo in giochi.dat");
        } catch (Exception e) {
            System.err.println("Errore nel salvataggio dei dati: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore nel salvataggio dei giochi: " + e.getMessage(), "Errore di Salvataggio", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void caricaDaFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("giochi.dat"))) {
            giochi = (ArrayList<Gioco>) ois.readObject();
            System.out.println("Dati caricati con successo da giochi.dat");
        } catch (FileNotFoundException e) {
            System.out.println("File giochi.dat non trovato, creazione di una nuova lista.");
            giochi = new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Errore nel caricamento dei dati: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore nel caricamento dei giochi: " + e.getMessage() + "\nSarà creata una nuova lista.", "Errore di Caricamento", JOptionPane.ERROR_MESSAGE);
            giochi = new ArrayList<>();
        }
    }
}