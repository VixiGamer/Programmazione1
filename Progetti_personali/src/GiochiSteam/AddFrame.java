package GiochiSteam;

import javax.swing.*;
import java.awt.*;

public class AddFrame extends JFrame {

    public AddFrame(JFrame parent, GestoreGiochi gestore, Runnable onAddCallback) {
        setTitle("Aggiungi Nuovo Gioco");
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2));

        JTextField nomeField = new JTextField();
        JTextField prezzoField = new JTextField();
        JComboBox<Gioco.ListaShop> shopBox = new JComboBox<>(Gioco.ListaShop.values());
        JComboBox<Gioco.ListaGioco> listaBox = new JComboBox<>(Gioco.ListaGioco.values());
        JTextField linkField = new JTextField();
        JButton confermaBtn = new JButton("Aggiungi");

        confermaBtn.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                double prezzo = Double.parseDouble(prezzoField.getText());
                Gioco.ListaShop shop = (Gioco.ListaShop) shopBox.getSelectedItem();
                Gioco.ListaGioco lista = (Gioco.ListaGioco) listaBox.getSelectedItem();
                String link = linkField.getText();

                Gioco nuovo = new Gioco(nome, prezzo, shop, lista, link);
                gestore.aggiungiGioco(nuovo);
                onAddCallback.run();
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore nei dati.");
            }
        });

        add(new JLabel("Nome:"));        add(nomeField);
        add(new JLabel("Prezzo (€):"));  add(prezzoField);
        add(new JLabel("Shop:"));        add(shopBox);
        add(new JLabel("Lista:"));       add(listaBox);
        add(new JLabel("Link Steam:"));  add(linkField);
        add(new JLabel(""));             add(confermaBtn);

        setVisible(true);
    }
}
