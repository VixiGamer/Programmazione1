// SearchAlbumFrame.java
package MyMusic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class SearchAlbumFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private GestoreCollezione gestore;
    private JTextField searchField;
    private JComboBox<Object> statoFilterBox;
    private JComboBox<Object> formatoFilterBox;
    private JComboBox<String> genereFilterBox;
    private JCheckBox edizioneLimitataFilterCheckBox;
    private JCheckBox deluxeEditionFilterCheckBox;
    private JTextField esclusiveFilterField;
    private JPanel albumsDisplayPanel;

    public SearchAlbumFrame(JFrame parent, GestoreCollezione gestore) {
        this.gestore = gestore;
        setTitle("Cerca/Filtra Album");
        setSize(750, 700);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        filterPanel.setBackground(new Color(240, 248, 255));

        JPanel searchBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        searchBarPanel.setBackground(filterPanel.getBackground());
        searchField = new JTextField(30);
        searchField.putClientProperty("JTextField.placeholderText", "Cerca per artista o titolo album...");
        JButton searchButton = new JButton("Cerca");
        searchButton.addActionListener(e -> updateAlbumsDisplay());
        searchBarPanel.add(new JLabel("Testo Ricerca:"));
        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);
        filterPanel.add(searchBarPanel);

        JPanel filtersRow1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        filtersRow1Panel.setBackground(filterPanel.getBackground());
        JPanel filtersRow2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        filtersRow2Panel.setBackground(filterPanel.getBackground());
        
        filterPanel.add(Box.createVerticalStrut(0));
        

        statoFilterBox = new JComboBox<>();
        statoFilterBox.addItem("Tutti gli Stati");
        Arrays.stream(Artista.StatoCollezione.values()).forEach(statoFilterBox::addItem);
        statoFilterBox.addActionListener(e -> updateAlbumsDisplay());
        filtersRow1Panel.add(new JLabel("Stato:"));
        filtersRow1Panel.add(statoFilterBox);

        formatoFilterBox = new JComboBox<>();
        formatoFilterBox.addItem("Tutti i Formati");
        Arrays.stream(Artista.FormatoMusica.values()).forEach(formatoFilterBox::addItem);
        formatoFilterBox.addActionListener(e -> updateAlbumsDisplay());
        filtersRow1Panel.add(new JLabel("Formato:"));
        filtersRow1Panel.add(formatoFilterBox);

        genereFilterBox = new JComboBox<>();
        genereFilterBox.addItem("Tutti i Generi");
        gestore.getTuttiGliAlbum().stream()
                .map(Artista::getGenere)
                .filter(g -> g != null && !g.trim().isEmpty())
                .distinct()
                .sorted()
                .forEach(genereFilterBox::addItem);
        genereFilterBox.addActionListener(e -> updateAlbumsDisplay());
        filtersRow1Panel.add(new JLabel("Genere:"));
        filtersRow1Panel.add(genereFilterBox);

        filterPanel.add(filtersRow1Panel);

        edizioneLimitataFilterCheckBox = new JCheckBox("Solo Edizione Limitata");
        edizioneLimitataFilterCheckBox.addActionListener(e -> updateAlbumsDisplay());
        filtersRow2Panel.add(edizioneLimitataFilterCheckBox);
        

		deluxeEditionFilterCheckBox = new JCheckBox("Solo Deluxe Edition");
		deluxeEditionFilterCheckBox.addActionListener(e -> updateAlbumsDisplay());
		filtersRow2Panel.add(deluxeEditionFilterCheckBox);


        esclusiveFilterField = new JTextField(15);
        esclusiveFilterField.putClientProperty("JTextField.placeholderText", "Esclusive (es. Giappone)");
        JButton esclusiveFilterBtn = new JButton("Filtra Esclusive");
        esclusiveFilterBtn.addActionListener(e -> updateAlbumsDisplay());
        filtersRow2Panel.add(new JLabel("Contiene Esclusive:"));
        filtersRow2Panel.add(esclusiveFilterField);
        filtersRow2Panel.add(esclusiveFilterBtn);
        
        filterPanel.add(filtersRow2Panel);
        filterPanel.add(Box.createVerticalStrut(5));


        add(filterPanel, BorderLayout.NORTH);

        albumsDisplayPanel = new JPanel();
        albumsDisplayPanel.setLayout(new BoxLayout(albumsDisplayPanel, BoxLayout.Y_AXIS));
        albumsDisplayPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        albumsDisplayPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(albumsDisplayPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        updateAlbumsDisplay();
        setVisible(true);
    }

    private void updateAlbumsDisplay() {
        albumsDisplayPanel.removeAll();

        String searchText = searchField.getText().trim().toLowerCase();
        Artista.StatoCollezione selectedStato = (statoFilterBox.getSelectedItem() instanceof Artista.StatoCollezione) ?
                (Artista.StatoCollezione) statoFilterBox.getSelectedItem() : null;
        Artista.FormatoMusica selectedFormato = (formatoFilterBox.getSelectedItem() instanceof Artista.FormatoMusica) ?
                (Artista.FormatoMusica) formatoFilterBox.getSelectedItem() : null;
        String selectedGenere = (genereFilterBox.getSelectedItem() instanceof String && !genereFilterBox.getSelectedItem().equals("Tutti i Generi")) ?
                (String) genereFilterBox.getSelectedItem() : null;
        boolean onlyLimitedEdition = edizioneLimitataFilterCheckBox.isSelected();
        boolean onlyDeluxeEdition = deluxeEditionFilterCheckBox.isSelected();
        String esclusiveSearchText = esclusiveFilterField.getText().trim().toLowerCase();


        List<Artista> filteredAlbums = gestore.getTuttiGliAlbum().stream()
                .filter(album -> {
                    boolean matchesSearch = true;
                    if (!searchText.isEmpty()) {
                        matchesSearch = album.getNomeArtista().toLowerCase().contains(searchText) ||
                                album.getTitoloAlbum().toLowerCase().contains(searchText);
                    }

                    boolean matchesStato = (selectedStato == null) || album.getStato() == selectedStato;

                    boolean matchesFormato = (selectedFormato == null) || album.getFormato() == selectedFormato;

                    boolean matchesGenere = (selectedGenere == null) ||
                                            (album.getGenere() != null && album.getGenere().equalsIgnoreCase(selectedGenere));

                    boolean matchesLimitedEdition = (!onlyLimitedEdition) || album.isEdizioneLimitata();                   

                    boolean matchesDeluxeEdition = (!onlyDeluxeEdition) || album.isDeluxeEdition();


                    boolean matchesEsclusive = true;
                    if (!esclusiveSearchText.isEmpty()) {
                        matchesEsclusive = (album.getEsclusive() != null && album.getEsclusive().toLowerCase().contains(esclusiveSearchText));
                    }

					return matchesSearch && matchesStato && matchesFormato && matchesGenere &&
					       matchesLimitedEdition && matchesDeluxeEdition && matchesEsclusive;

                })
                .sorted(Comparator.comparing(Artista::getNomeArtista)
                        .thenComparing(Artista::getAnnoPubblicazione)
                        .thenComparing(Artista::getTitoloAlbum))
                .collect(Collectors.toList());

        if (filteredAlbums.isEmpty()) {
            albumsDisplayPanel.add(new JLabel("Nessun album trovato con i criteri specificati."));
        } else {
            for (Artista album : filteredAlbums) {
                JPanel albumPanel = createSearchAlbumPanel(album);
                albumsDisplayPanel.add(albumPanel);
                albumsDisplayPanel.add(Box.createVerticalStrut(10));
            }
        }

        albumsDisplayPanel.revalidate();
        albumsDisplayPanel.repaint();
    }

    private JPanel createSearchAlbumPanel(Artista album) {
        JPanel albumPanel = new JPanel(new BorderLayout(10, 5));
        albumPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));
        albumPanel.setBackground(Color.WHITE);

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(80, 80));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        if (album.getPercorsoFoto() != null && !album.getPercorsoFoto().isEmpty()) {
            try {
                File imageFile = new File(album.getPercorsoFoto());
                if (imageFile.exists() && imageFile.canRead()) {
                    ImageIcon originalIcon = new ImageIcon(ImageIO.read(imageFile));
                    Image image = originalIcon.getImage();
                    Image scaledImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaledImage));
                } else {
                    imageLabel.setText("Foto non trovata");
                    imageLabel.setFont(new Font("Arial", Font.PLAIN, 9));
                    imageLabel.setForeground(Color.RED);
                }
            } catch (IOException ex) {
                imageLabel.setText("Errore caricamento foto");
                imageLabel.setFont(new Font("Arial", Font.PLAIN, 9));
                imageLabel.setForeground(Color.RED);
            }
        } else {
            imageLabel.setText("No Image");
            imageLabel.setFont(new Font("Arial", Font.PLAIN, 9));
            imageLabel.setForeground(Color.LIGHT_GRAY);
        }
        albumPanel.add(imageLabel, BorderLayout.WEST);

        JPanel detailsPanel = new JPanel(new GridLayout(0, 1));
        detailsPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("<html><b>" + album.getTitoloAlbum() + "</b></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel artistLabel = new JLabel("di: " + album.getNomeArtista());
        artistLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel yearFormatLabel = new JLabel("Anno: " + album.getAnnoPubblicazione() + " | Formato: " + album.getFormato().toString());
        yearFormatLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel genreStatusLabel = new JLabel("Genere: " + (album.getGenere().isEmpty() ? "N/A" : album.getGenere()) + " | Stato: " + album.getStato().toString());
        genreStatusLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel limitedExclusiveLabel = new JLabel(
    		(album.isDeluxeEdition() ? "Deluxe Edition | " : "") +
            (album.isEdizioneLimitata() ? "Edizione Limitata" : "") +
            (!album.getEsclusive().isEmpty() ? (" | Esclusive: " + album.getEsclusive()) : "")
        );
        limitedExclusiveLabel.setFont(new Font("Arial", Font.ITALIC, 11));

        detailsPanel.add(titleLabel);
        detailsPanel.add(artistLabel);
        detailsPanel.add(yearFormatLabel);
        detailsPanel.add(genreStatusLabel);
        if (!limitedExclusiveLabel.getText().trim().isEmpty()) {
            detailsPanel.add(limitedExclusiveLabel);
        }

        albumPanel.add(detailsPanel, BorderLayout.CENTER);


        return albumPanel;
    }
}