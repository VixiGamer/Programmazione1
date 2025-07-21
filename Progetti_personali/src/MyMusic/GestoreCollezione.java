// GestoreCollezione.java
package MyMusic;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class GestoreCollezione implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Artista> collezione = new ArrayList<>();
    private static final String FILE_NAME = "collezione_mymusic.dat";

    public GestoreCollezione() {
        caricaDaFile();
    }

    public void aggiungiAlbum(Artista a) {
        collezione.add(a);
        salvaSuFile();
    }

    public void rimuoviAlbum(Artista a) {
        collezione.remove(a);
        salvaSuFile();
    }

    public List<Artista> getTuttiGliAlbum() {
        return new ArrayList<>(collezione);
    }

    public void salvaSuFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(collezione);
            System.out.println("Dati salvati con successo in " + FILE_NAME);
        } catch (Exception e) {
            System.err.println("Errore nel salvataggio dei dati: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore nel salvataggio della collezione: " + e.getMessage(), "Errore di Salvataggio", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    public void caricaDaFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            collezione = (ArrayList<Artista>) ois.readObject();
            System.out.println("Dati caricati con successo da " + FILE_NAME);
        } catch (FileNotFoundException e) {
            System.out.println("File " + FILE_NAME + " non trovato, creazione di una nuova lista.");
            collezione = new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Errore nel caricamento dei dati: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore nel caricamento della collezione: " + e.getMessage() + "\nSarà creata una nuova lista.", "Errore di Caricamento", JOptionPane.ERROR_MESSAGE);
            collezione = new ArrayList<>();
        }
    }

	public void salvaCollezione() {
		// TODO Auto-generated method stub
		
	}
}