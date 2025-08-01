import javax.swing.*;
import java.awt.*;
import java.util.Objects; // Import aggiunto per Objects

public class ModificaFrame extends JFrame {

    public ModificaFrame(JFrame parent, GestoreGiochi gestore, Runnable onModificaCallback) {
        setTitle("Modifica Gioco");
        setSize(450, 450); // Dimensioni leggermente aumentate
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout()); // Usato GridBagLayout per un layout più flessibile
        setResizable(false); // Rende la finestra non ridimensionabile

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding tra i componenti
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ComboBox per selezionare il gioco da modificare/eliminare
        JComboBox<Gioco> giocoBox = new JComboBox<>();
        gestore.getTuttiIGiochi().forEach(giocoBox::addItem); // Popola la ComboBox
        // Aggiunge un elemento vuoto se non ci sono giochi
        if (giocoBox.getItemCount() == 0) {
            giocoBox.addItem(null); // Aggiunge un placeholder o disabilita il form
        }

        JTextField nomeField = new JTextField(20);
        JTextField prezzoField = new JTextField(20);
        JComboBox<Gioco.ListaShop> shopBox = new JComboBox<>(Gioco.ListaShop.values());
        JComboBox<Gioco.ListaGioco> listaBox = new JComboBox<>(Gioco.ListaGioco.values());
        JTextField linkField = new JTextField(20);

        JButton confermaBtn = new JButton("Salva Modifiche");
        JButton eliminaBtn = new JButton("❌ Elimina Gioco");

        // Listener per popolare i campi quando si seleziona un gioco
        giocoBox.addActionListener(e -> {
            Gioco g = (Gioco) giocoBox.getSelectedItem();
            if (g != null) {
                nomeField.setText(g.getNome());
                prezzoField.setText(String.valueOf(g.getPrezzo())); // Utilizza String.valueOf
                shopBox.setSelectedItem(g.getShop());
                listaBox.setSelectedItem(g.getLista());
                linkField.setText(g.getSteamlink());
            } else {
                // Pulisci i campi se non c'è nessun gioco selezionato (e.g., lista vuota)
                nomeField.setText("");
                prezzoField.setText("");
                shopBox.setSelectedIndex(0);
                listaBox.setSelectedIndex(0);
                linkField.setText("");
            }
        });

        // Inizializza i campi se c'è già un gioco selezionato (primo elemento)
        if (giocoBox.getSelectedItem() != null) {
            Gioco initialGame = (Gioco) giocoBox.getSelectedItem();
            nomeField.setText(initialGame.getNome());
            prezzoField.setText(String.valueOf(initialGame.getPrezzo()));
            shopBox.setSelectedItem(initialGame.getShop());
            listaBox.setSelectedItem(initialGame.getLista());
            linkField.setText(initialGame.getSteamlink());
        }


        confermaBtn.addActionListener(e -> {
            Gioco g = (Gioco) giocoBox.getSelectedItem();
            if (g == null) {
                JOptionPane.showMessageDialog(this, "Seleziona un gioco da modificare.", "Errore", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validazione input
            if (nomeField.getText().trim().isEmpty()) {
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

            if (linkField.getText().trim().isEmpty() && ((Gioco.ListaShop) Objects.requireNonNull(shopBox.getSelectedItem())) == Gioco.ListaShop.Steam) {
                JOptionPane.showMessageDialog(this, "Il link Steam non può essere vuoto se lo shop è Steam.", "Errore di Input", JOptionPane.ERROR_MESSAGE);
                return;
            }


            g.setNome(nomeField.getText().trim());
            g.setPrezzo(prezzo);
            g.setShop((Gioco.ListaShop) Objects.requireNonNull(shopBox.getSelectedItem()));
            g.setLista((Gioco.ListaGioco) Objects.requireNonNull(listaBox.getSelectedItem()));
            g.setSteamlink(linkField.getText().trim());

            gestore.salvaSuFile(); // Salva dopo la modifica
            onModificaCallback.run(); // Aggiorna la MainFrame
            JOptionPane.showMessageDialog(this, "Gioco modificato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        eliminaBtn.addActionListener(e -> {
            Gioco g = (Gioco) giocoBox.getSelectedItem();
            if (g == null) {
                JOptionPane.showMessageDialog(this, "Seleziona un gioco da eliminare.", "Errore", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Sei sicuro di voler eliminare il gioco \"" + g.getNome() + "\"?",
                    "Conferma Eliminazione", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                gestore.rimuoviGioco(g);
                onModificaCallback.run(); // Aggiorna la MainFrame
                JOptionPane.showMessageDialog(this, "Gioco eliminato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        // Aggiunta dei componenti al GridBagLayout
        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.1; add(new JLabel("Seleziona Gioco:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 0.9; add(giocoBox, gbc);

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

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1; gbc.weightx = 0.5; add(confermaBtn, gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.gridwidth = 1; gbc.weightx = 0.5; add(eliminaBtn, gbc);


        setVisible(true);
    }
}