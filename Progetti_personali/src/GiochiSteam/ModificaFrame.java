package GiochiSteam;

import javax.swing.*;
import java.awt.*;

public class ModificaFrame extends JFrame {

    public ModificaFrame(JFrame parent, GestoreGiochi gestore, Runnable onModificaCallback) {
        setTitle("Modifica Gioco");
        setSize(400, 400);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(8, 2));

        JComboBox<Gioco> giocoBox = new JComboBox<>();
        for (Gioco g : gestore.getTuttiIGiochi()) {
            giocoBox.addItem(g);
        }

        JTextField nomeField = new JTextField();
        JTextField prezzoField = new JTextField();
        JComboBox<Gioco.ListaShop> shopBox = new JComboBox<>(Gioco.ListaShop.values());
        JComboBox<Gioco.ListaGioco> listaBox = new JComboBox<>(Gioco.ListaGioco.values());
        JTextField linkField = new JTextField();
        JButton confermaBtn = new JButton("Salva");
        JButton eliminaBtn = new JButton("❌ Elimina");

        giocoBox.addActionListener(e -> {
            Gioco g = (Gioco) giocoBox.getSelectedItem();
            if (g != null) {
                nomeField.setText(g.getNome());
                prezzoField.setText(g.getPrezzo() + "");
                shopBox.setSelectedItem(g.getShop());
                listaBox.setSelectedItem(g.getLista());
                linkField.setText(g.getSteamlink());
            }
        });

        confermaBtn.addActionListener(e -> {
            Gioco g = (Gioco) giocoBox.getSelectedItem();
            if (g != null) {
                g.setNome(nomeField.getText());
                g.setPrezzo(Double.parseDouble(prezzoField.getText()));
                g.setShop((Gioco.ListaShop) shopBox.getSelectedItem());
                g.setLista((Gioco.ListaGioco) listaBox.getSelectedItem());
                g.setSteamlink(linkField.getText());
                gestore.salvaSuFile();
                onModificaCallback.run();
                dispose();
            }
        });

        eliminaBtn.addActionListener(e -> {
            Gioco g = (Gioco) giocoBox.getSelectedItem();
            if (g != null) {
                gestore.rimuoviGioco(g);
                onModificaCallback.run();
                dispose();
            }
        });

        add(new JLabel("Gioco:"));       add(giocoBox);
        add(new JLabel("Nome:"));        add(nomeField);
        add(new JLabel("Prezzo (€):"));  add(prezzoField);
        add(new JLabel("Shop:"));        add(shopBox);
        add(new JLabel("Lista:"));       add(listaBox);
        add(new JLabel("Link Steam:"));  add(linkField);
        add(confermaBtn);                add(eliminaBtn);

        setVisible(true);
    }
}
