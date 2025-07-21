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
                String nome = nomeField.getText().trim();
                String prezzoStr = prezzoField.getText().trim();
                String link = linkField.getText().trim();

                // Validazione input base
                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Il campo 'Nome' non può essere vuoto.", "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (prezzoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Il campo 'Prezzo' non può essere vuoto.", "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                double prezzo;
                try {
                    prezzo = Double.parseDouble(prezzoStr);
                    if (prezzo < 0) {
                        JOptionPane.showMessageDialog(this, "Il prezzo non può essere negativo.", "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Il prezzo deve essere un numero valido.", "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Controllo duplicati
                if (!link.isEmpty()) {
                    // Se il link è fornito, controlla per link Steam duplicati
                    for (Gioco existingGioco : gestore.getTuttiIGiochi()) {
                        if (!existingGioco.getSteamlink().trim().isEmpty() && existingGioco.getSteamlink().trim().equalsIgnoreCase(link)) {
                            JOptionPane.showMessageDialog(this, "Un gioco con lo stesso link Steam esiste già: " + existingGioco.getNome(), "Errore di Duplicazione", JOptionPane.ERROR_MESSAGE);
                            return; // Blocca l'aggiunta
                        }
                    }
                } else {
                    // Se il link non è fornito, controlla per nome duplicato
                    for (Gioco existingGioco : gestore.getTuttiIGiochi()) {
                        if (existingGioco.getNome().trim().equalsIgnoreCase(nome)) {
                            JOptionPane.showMessageDialog(this, "Un gioco con lo stesso nome esiste già e il link Steam non è stato fornito.", "Errore di Duplicazione", JOptionPane.ERROR_MESSAGE);
                            return; // Blocca l'aggiunta
                        }
                    }
                }

                Gioco.ListaShop shop = (Gioco.ListaShop) Objects.requireNonNull(shopBox.getSelectedItem());
                Gioco.ListaGioco lista = (Gioco.ListaGioco) Objects.requireNonNull(listaBox.getSelectedItem());

                Gioco nuovo = new Gioco(nome, prezzo, shop, lista, link);
                gestore.aggiungiGioco(nuovo);
                onAddCallback.run();
                JOptionPane.showMessageDialog(this, "Gioco \"" + nome + "\" aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
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
        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE; // Non espandere questo componente
        gbc.anchor = GridBagConstraints.CENTER; // Centra lo spazio
        add(Box.createVerticalStrut(10), gbc); // Spazio verticale

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2; // Il pulsante occupa entrambe le colonne
        gbc.fill = GridBagConstraints.HORIZONTAL; // Il pulsante si espande orizzontalmente
        add(confermaBtn, gbc);

        setVisible(true);
    }
}