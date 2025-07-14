package GiochiSteamV2;

import javax.swing.*;
import java.awt.*;
import java.util.Objects; // Necessario per Objects.requireNonNull

public class AddFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public AddFrame(JFrame parent, GestoreGiochi gestore, Runnable onAddCallback) {
        setTitle("Aggiungi Nuovo Gioco");
        setSize(400, 280); // Aumentata l'altezza per migliore visualizzazione con le validazioni
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout()); // Passato a GridBagLayout per un controllo più fine
        setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Spaziatura tra i componenti
        gbc.fill = GridBagConstraints.HORIZONTAL; // I componenti si espandono orizzontalmente

        JTextField nomeField = new JTextField(20);
        JTextField prezzoField = new JTextField(20);
        JComboBox<Gioco.ListaShop> shopBox = new JComboBox<>(Gioco.ListaShop.values());
        JComboBox<Gioco.ListaGioco> listaBox = new JComboBox<>(Gioco.ListaGioco.values());
        JTextField linkField = new JTextField(20);
        JButton confermaBtn = new JButton("Aggiungi Gioco"); // Testo più esplicito

        confermaBtn.addActionListener(e -> {
            try {
                String nome = nomeField.getText().trim(); // Rimuovi spazi extra
                String link = linkField.getText().trim(); // Rimuovi spazi extra

                // Validazioni input
                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Il nome non può essere vuoto.", "Errore di Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double prezzo;
                try {
                    prezzo = Double.parseDouble(prezzoField.getText());
                    if (prezzo < 0) {
                        JOptionPane.showMessageDialog(this, "Il prezzo non può essere negativo.", "Errore di Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Prezzo non valido. Inserire un numero.", "Errore di Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validazione link Steam se lo shop è Steam
                if (link.isEmpty() && ((Gioco.ListaShop) Objects.requireNonNull(shopBox.getSelectedItem())) == Gioco.ListaShop.Steam) {
                    JOptionPane.showMessageDialog(this, "Il link Steam non può essere vuoto se lo shop è Steam.", "Errore di Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Gioco.ListaShop shop = (Gioco.ListaShop) Objects.requireNonNull(shopBox.getSelectedItem());
                Gioco.ListaGioco lista = (Gioco.ListaGioco) Objects.requireNonNull(listaBox.getSelectedItem());

                Gioco nuovo = new Gioco(nome, prezzo, shop, lista, link);
                gestore.aggiungiGioco(nuovo);
                onAddCallback.run();
                JOptionPane.showMessageDialog(this, "Gioco \"" + nome + "\" aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                // Questo catch generico è un fallback, le validazioni specifiche sopra sono preferibili
                JOptionPane.showMessageDialog(this, "Si è verificato un errore durante l'aggiunta del gioco: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Aggiunta dei componenti con GridBagLayout
        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.1; add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 0.9; add(nomeField, gbc);

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.1; add(new JLabel("Prezzo (€):"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 0.9; add(prezzoField, gbc);

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.1; add(new JLabel("Shop:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 0.9; add(shopBox, gbc);

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.1; add(new JLabel("Lista:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 0.9; add(listaBox, gbc);

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.1; add(new JLabel("Link Steam:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 0.9; add(linkField, gbc);

        // Aggiungi un po' di spazio prima del pulsante per mantenere la spaziatura desiderata
        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2; gbc.weighty = 0.1; add(Box.createVerticalGlue(), gbc);

        // Aggiunta del pulsante "Aggiungi Gioco"
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2; // Il pulsante occupa entrambe le colonne
        gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.CENTER; // Centra il pulsante
        add(confermaBtn, gbc);
        row++;

        // Aggiungi spazio verticale flessibile sotto il pulsante per spingere tutto in alto
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
        add(Box.createVerticalGlue(), gbc);

        setVisible(true);
    }
}