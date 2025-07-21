// ModificaAlbumFrame.java
package MyMusic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.text.NumberFormatter;
import java.util.Objects;

public class ModificaAlbumFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private String selectedImagePath;
    private Artista selectedAlbum; // Riferimento all'album selezionato per la modifica

    public ModificaAlbumFrame(JFrame parent, GestoreCollezione gestore, Runnable onModificaCallback) {
        setTitle("Modifica Album");
        setSize(550, 700); // Altezza adeguata per i campi
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        setResizable(false);

        this.getContentPane().setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JComboBox<Artista> albumBox = new JComboBox<>();
        albumBox.setBackground(Color.WHITE);
        gestore.getTuttiGliAlbum().forEach(albumBox::addItem);

        JTextField nomeArtistaField = new JTextField(25);
        nomeArtistaField.setBackground(Color.WHITE);
        JTextField titoloAlbumField = new JTextField(25);
        titoloAlbumField.setBackground(Color.WHITE);

        // Modificato: Anno Pubblicazione torna a JTextField semplice

		NumberFormat intFormat = NumberFormat.getNumberInstance(java.util.Locale.US);
		intFormat.setGroupingUsed(false);
		NumberFormatter yearFormatter = new NumberFormatter(intFormat);
		yearFormatter.setValueClass(Integer.class);
		yearFormatter.setMinimum(1900);
		yearFormatter.setMaximum(2100);
		yearFormatter.setAllowsInvalid(false);
		
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

        
        JButton scegliFotoBtn = new JButton("Scegli Nuova Foto Copertina");
        JLabel fotoPathLabel = new JLabel("Nessuna foto selezionata");
        fotoPathLabel.setForeground(Color.GRAY);

        JComboBox<Artista.StatoCollezione> statoBox = new JComboBox<>(Artista.StatoCollezione.values());
        statoBox.setBackground(Color.WHITE);

        // Modificato: Prezzo torna a JTextField semplice per gestire meglio il valore vuoto
        JTextField prezzoField = new JTextField(25);
        prezzoField.setBackground(Color.WHITE);

        JTextField luogoAcquistoField = new JTextField(25);
        luogoAcquistoField.setBackground(Color.WHITE);

        int row = 0;
        addComponent(new JLabel("Seleziona Album:"), albumBox, gbc, row++);
        addComponent(new JLabel("Titolo Album:"), titoloAlbumField, gbc, row++);
        addComponent(new JLabel("Nome Artista:"), nomeArtistaField, gbc, row++);
        addComponent(new JLabel("Anno Pubblicazione:"), annoPubblicazioneField, gbc, row++);
        addComponent(new JLabel("Genere:"), genereField, gbc, row++);
        addComponent(new JLabel("Formato:"), formatoBox, gbc, row++);
        addComponent(new JLabel("Tipo Musica:"), tipoMusicaBox, gbc, row++);
        addComponent(editionPanel, gbc, row++);
        addComponent(new JLabel("Esclusive:"), esclusiveField, gbc, row++);
        addComponent(new JLabel("Stato Collezione:"), statoBox, gbc, row++);
        addComponent(new JLabel("Prezzo (€):"), prezzoField, gbc, row++);
        addComponent(new JLabel("Luogo Acquisto (Link/Nome Negozio):"), luogoAcquistoField, gbc, row++);

        JPanel fotoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        fotoPanel.setBackground(new Color(240, 240, 240));
        fotoPanel.add(scegliFotoBtn);
        fotoPanel.add(fotoPathLabel);

        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(fotoPanel, gbc);

        // Listener per la selezione dell'album
        albumBox.addActionListener(e -> {
            selectedAlbum = (Artista) albumBox.getSelectedItem();
            if (selectedAlbum != null) {
                nomeArtistaField.setText(selectedAlbum.getNomeArtista());
                titoloAlbumField.setText(selectedAlbum.getTitoloAlbum());
                annoPubblicazioneField.setText(String.valueOf(selectedAlbum.getAnnoPubblicazione())); // Popola Anno
                genereField.setText(selectedAlbum.getGenere());
                formatoBox.setSelectedItem(selectedAlbum.getFormato());
                tipoMusicaBox.setSelectedItem(selectedAlbum.getTipoMusica());
                deluxeEditionCheckBox.setSelected(selectedAlbum.isDeluxeEdition());
                edizioneLimitataCheckBox.setSelected(selectedAlbum.isEdizioneLimitata());
                esclusiveField.setText(selectedAlbum.getEsclusive());
                statoBox.setSelectedItem(selectedAlbum.getStato());
                selectedImagePath = selectedAlbum.getPercorsoFoto();
                fotoPathLabel.setText(selectedImagePath != null && !selectedImagePath.isEmpty() ? new File(selectedImagePath).getName() : "Nessuna foto selezionata");
                fotoPathLabel.setForeground(selectedImagePath != null && !selectedImagePath.isEmpty() ? Color.BLACK : Color.GRAY);
                
                // Popola i nuovi campi
                if (selectedAlbum.getPrezzo() == 0.0) { // Se il prezzo è 0.0, lo visualizzo come vuoto per la modifica
                    prezzoField.setText("");
                } else {
                    prezzoField.setText(String.format("%.2f", selectedAlbum.getPrezzo()).replace('.', ',')); // Formatta e usa la virgola
                }
                luogoAcquistoField.setText(selectedAlbum.getLuogoAcquisto());

            }
        });

        // Inizializza i campi con il primo album se presente
        if (albumBox.getItemCount() > 0) {
            albumBox.setSelectedIndex(0);
        }

        scegliFotoBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Scegli Nuova Foto Copertina");
            int userSelection = fileChooser.showOpenDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = fileChooser.getSelectedFile();
                selectedImagePath = fileToLoad.getAbsolutePath();
                fotoPathLabel.setText(fileToLoad.getName());
                fotoPathLabel.setForeground(Color.BLACK);
            }
        });

        JButton confermaBtn = new JButton("Salva Modifiche");
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(confermaBtn, gbc);

        confermaBtn.addActionListener(e -> {
            if (selectedAlbum == null) {
                JOptionPane.showMessageDialog(this, "Seleziona un album da modificare.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                String newNomeArtista = Objects.requireNonNull(nomeArtistaField.getText(), "Il nome dell'artista non può essere vuoto.").trim();
                String newTitoloAlbum = Objects.requireNonNull(titoloAlbumField.getText(), "Il titolo dell'album non può essere vuoto.").trim();
                
                if (newNomeArtista.isEmpty() || newTitoloAlbum.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nome artista e titolo album non possono essere vuoti.", "Errore di Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int newAnnoPubblicazione;
                try {
                    newAnnoPubblicazione = Integer.parseInt(annoPubblicazioneField.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Anno di pubblicazione non valido. Inserire un numero intero.", "Errore di Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String newGenere = genereField.getText().trim();
                Artista.FormatoMusica newFormato = (Artista.FormatoMusica) Objects.requireNonNull(formatoBox.getSelectedItem(), "Il formato non può essere nullo.");
                Artista.TipoMusica newTipoMusica = (Artista.TipoMusica) Objects.requireNonNull(tipoMusicaBox.getSelectedItem(), "Il tipo di musica non può essere nullo.");
                boolean newEdizioneLimitata = edizioneLimitataCheckBox.isSelected();
                String newEsclusive = esclusiveField.getText().trim();
                Artista.StatoCollezione newStato = (Artista.StatoCollezione) Objects.requireNonNull(statoBox.getSelectedItem(), "Lo stato della collezione non può essere nullo.");
                

double newPrezzo = 0.0;
String prezzoText = prezzoField.getText().trim();
if (!prezzoText.isEmpty()) {
    try {
        newPrezzo = Double.parseDouble(prezzoText.replace(',', '.'));
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Prezzo non valido. Inserire un numero.", "Errore di Input", JOptionPane.ERROR_MESSAGE);
        return;
    }
}

                
                String newLuogoAcquisto = luogoAcquistoField.getText().trim();

                selectedAlbum.setNomeArtista(newNomeArtista);
                selectedAlbum.setTitoloAlbum(newTitoloAlbum);
                selectedAlbum.setAnnoPubblicazione(newAnnoPubblicazione);
                selectedAlbum.setGenere(newGenere);
                selectedAlbum.setFormato(newFormato);
                selectedAlbum.setTipoMusica(newTipoMusica);
                selectedAlbum.setDeluxeEdition(deluxeEditionCheckBox.isSelected());
                selectedAlbum.setEdizioneLimitata(newEdizioneLimitata);
                selectedAlbum.setEsclusive(newEsclusive);
                selectedAlbum.setPercorsoFoto(selectedImagePath);
                selectedAlbum.setStato(newStato);
                selectedAlbum.setPrezzo(newPrezzo); // AGGIORNATO
                selectedAlbum.setLuogoAcquisto(newLuogoAcquisto); // AGGIORNATO


                gestore.salvaSuFile();
                JOptionPane.showMessageDialog(this, "Album modificato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                onModificaCallback.run();
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore durante la modifica dell'album: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        setVisible(true);
    }

    private void addComponent(JComponent component, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
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