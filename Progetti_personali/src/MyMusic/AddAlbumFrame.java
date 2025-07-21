// AddAlbumFrame.java
package MyMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.text.NumberFormatter;
import java.util.Objects;

public class AddAlbumFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private String selectedImagePath;

    public AddAlbumFrame(JFrame parent, GestoreCollezione gestore, Runnable onAddCallback) {
        setTitle("Aggiungi Nuovo Album");
        setSize(550, 680); // Aumentata altezza per i nuovi campi
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        setResizable(false);
        
        this.getContentPane().setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nomeArtistaField = new JTextField(25);
        nomeArtistaField.setBackground(Color.WHITE);
        JTextField titoloAlbumField = new JTextField(25);
        titoloAlbumField.setBackground(Color.WHITE);
        
		JTextField annoPubblicazioneField = new JTextField(25);
		annoPubblicazioneField.setBackground(Color.WHITE);

		JTextField genereField = new JTextField(25);
		genereField.setBackground(Color.WHITE);
		JComboBox<Artista.FormatoMusica> formatoBox = new JComboBox<>(Artista.FormatoMusica.values());
		formatoBox.setBackground(Color.WHITE);
		JComboBox<Artista.TipoMusica> tipoMusicaBox = new JComboBox<>(Artista.TipoMusica.values());
		tipoMusicaBox.setBackground(Color.WHITE);
		
		// Nuova checkbox Deluxe Edition
		JCheckBox deluxeEditionCheckBox = new JCheckBox("Deluxe Edition");
		deluxeEditionCheckBox.setBackground(new Color(240, 240, 240));
		JCheckBox edizioneLimitataCheckBox = new JCheckBox("Edizione Limitata");
		edizioneLimitataCheckBox.setBackground(new Color(240, 240, 240));
		
		// Pannello per le due checkbox affiancate
		JPanel editionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		editionPanel.setBackground(new Color(240, 240, 240));
		editionPanel.add(deluxeEditionCheckBox);
		editionPanel.add(Box.createHorizontalStrut(16));
		editionPanel.add(edizioneLimitataCheckBox);
		
		JTextField esclusiveField = new JTextField(25);
		esclusiveField.setBackground(Color.WHITE);

        
        JButton scegliFotoBtn = new JButton("Scegli Foto Copertina");
        JLabel fotoPathLabel = new JLabel("Nessuna foto selezionata");
        fotoPathLabel.setForeground(Color.GRAY);

        JComboBox<Artista.StatoCollezione> statoBox = new JComboBox<>(Artista.StatoCollezione.values());
        statoBox.setBackground(Color.WHITE);

        // NUOVI CAMPI
        NumberFormat doubleFormat = NumberFormat.getNumberInstance();
        doubleFormat.setMinimumFractionDigits(2);
        doubleFormat.setMaximumFractionDigits(2);
        NumberFormatter priceFormatter = new NumberFormatter(doubleFormat);
        priceFormatter.setValueClass(Double.class);
        priceFormatter.setMinimum(0.0);
        priceFormatter.setAllowsInvalid(false);
        JFormattedTextField prezzoField = new JFormattedTextField(priceFormatter);
        prezzoField.setColumns(25);
        prezzoField.setBackground(Color.WHITE);
        prezzoField.setText("0.00"); // Valore predefinito

        JTextField luogoAcquistoField = new JTextField(25);
        luogoAcquistoField.setBackground(Color.WHITE);


        int row = 0;
        addComponent(new JLabel("Titolo Album:"), titoloAlbumField, gbc, row++);
        addComponent(new JLabel("Nome Artista:"), nomeArtistaField, gbc, row++);
        addComponent(new JLabel("Anno Pubblicazione:"), annoPubblicazioneField, gbc, row++);
        addComponent(new JLabel("Genere:"), genereField, gbc, row++);
        addComponent(new JLabel("Formato:"), formatoBox, gbc, row++);
        addComponent(new JLabel("Tipo Musica:"), tipoMusicaBox, gbc, row++);
        addComponent(editionPanel, gbc, row++);
        addComponent(new JLabel("Esclusive:"), esclusiveField, gbc, row++);
        addComponent(new JLabel("Stato Collezione:"), statoBox, gbc, row++);
        // Aggiungi i nuovi campi
        addComponent(new JLabel("Prezzo (€):"), prezzoField, gbc, row++);
        addComponent(new JLabel("Luogo Acquisto (Link/Nome Negozio):"), luogoAcquistoField, gbc, row++);
        
        // Pannello per la selezione della foto
        JPanel fotoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        fotoPanel.setBackground(new Color(240, 240, 240));
        fotoPanel.add(scegliFotoBtn);
        fotoPanel.add(fotoPathLabel);

        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2; // Span due colonne
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(fotoPanel, gbc);

        scegliFotoBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Scegli Foto Copertina");
            int userSelection = fileChooser.showOpenDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = fileChooser.getSelectedFile();
                selectedImagePath = fileToLoad.getAbsolutePath();
                fotoPathLabel.setText(fileToLoad.getName());
                fotoPathLabel.setForeground(Color.BLACK);
            }
        });

        JButton confermaBtn = new JButton("Aggiungi Album");
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(confermaBtn, gbc);

        confermaBtn.addActionListener(e -> {
            try {
                String titoloAlbum = Objects.requireNonNull(titoloAlbumField.getText(), "Il titolo dell'album non può essere vuoto.");
                String nomeArtista = Objects.requireNonNull(nomeArtistaField.getText(), "Il nome dell'artista non può essere vuoto.");
                
				String annoText = annoPubblicazioneField.getText().trim();
				int annoPubblicazione;
				try {
				    annoPubblicazione = Integer.parseInt(annoText);
				    if (annoPubblicazione < 1900 || annoPubblicazione > 2100) {
				        throw new NumberFormatException();
				    }
				} catch (NumberFormatException ex) {
				    JOptionPane.showMessageDialog(this, "Inserisci un anno di pubblicazione valido (1900-2100).", "Errore di Input", JOptionPane.ERROR_MESSAGE);
				    return;
				}

                String genere = genereField.getText().trim();
                Artista.FormatoMusica formato = (Artista.FormatoMusica) Objects.requireNonNull(formatoBox.getSelectedItem(), "Il formato non può essere nullo.");
                Artista.TipoMusica tipoMusica = (Artista.TipoMusica) Objects.requireNonNull(tipoMusicaBox.getSelectedItem(), "Il tipo di musica non può essere nullo.");
                boolean deluxeEdition = deluxeEditionCheckBox.isSelected();
                boolean edizioneLimitata = edizioneLimitataCheckBox.isSelected();
                String esclusive = esclusiveField.getText().trim();
                Artista.StatoCollezione stato = (Artista.StatoCollezione) Objects.requireNonNull(statoBox.getSelectedItem(), "Lo stato della collezione non può essere nullo.");
                

				double prezzo = 0.0;
				try {
				    Object prezzoObj = prezzoField.getValue();
				    if (prezzoObj != null) {
				        prezzo = ((Number) prezzoObj).doubleValue();
				    }
				} catch (Exception ex) {
				    prezzo = 0.0;
				}

                String luogoAcquisto = luogoAcquistoField.getText().trim(); // Recupera il luogo di acquisto


                if (nomeArtista.isEmpty() || titoloAlbum.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nome artista e titolo album non possono essere vuoti.", "Errore di Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }


				Artista nuovoAlbum = new Artista(
				    nomeArtista, titoloAlbum, annoPubblicazione, genere,
				    formato, tipoMusica, deluxeEditionCheckBox.isSelected(), edizioneLimitataCheckBox.isSelected(),
				    esclusive, selectedImagePath, stato, prezzo, luogoAcquisto
				); // Aggiornato costruttore

                gestore.aggiungiAlbum(nuovoAlbum);
                JOptionPane.showMessageDialog(this, "Album aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);

                // Reset dei campi
                nomeArtistaField.setText("");
                titoloAlbumField.setText("");
                genereField.setText("");
                formatoBox.setSelectedIndex(0);
                tipoMusicaBox.setSelectedIndex(0);
                edizioneLimitataCheckBox.setSelected(false);
                esclusiveField.setText("");
                selectedImagePath = null;
                fotoPathLabel.setText("Nessuna foto selezionata");
                fotoPathLabel.setForeground(Color.GRAY);
                statoBox.setSelectedIndex(0);
                prezzoField.setText("0.00"); // Resetta il prezzo
                luogoAcquistoField.setText(""); // Resetta il luogo di acquisto


                onAddCallback.run(); // Aggiorna la MainFrame
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore durante l'aggiunta dell'album: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }

    private void addComponent(JComponent component, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2; // Component takes full width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(component, gbc);
    }

    private void addComponent(JLabel label, JComponent component, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.1;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(component, gbc);
    }
}