package GiochiSteam;

import java.io.*;
import java.util.*;

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
        } catch (Exception e) {
            System.out.println("Errore nel salvataggio: " + e.getMessage());
        }
    }

    public void caricaDaFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("giochi.dat"))) {
            giochi = (ArrayList<Gioco>) ois.readObject();
        } catch (Exception e) {
            giochi = new ArrayList<>();
        }
    }
}
