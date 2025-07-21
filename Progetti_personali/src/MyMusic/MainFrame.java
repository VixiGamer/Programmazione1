// MainFrame.java
package MyMusic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter; // Import per MouseAdapter
import java.awt.event.MouseEvent;   // Import per MouseEvent
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI; // Import per URI
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private GestoreCollezione gestore = new GestoreCollezione();
    private Map<Artista.StatoCollezione, AlbumListComponents> listComponentsMap = new HashMap<>();

    private Map<Artista.StatoCollezione, Integer> currentPageMap = new HashMap<>();
    private final int ALBUMS_PER_PAGE = 6; // 6 album per pagina (2 righe x 3 colonne)
    private final int ALBUM_CARD_WIDTH = 200;
    private final int ALBUM_CARD_HEIGHT = 220; // L'altezza potrebbe aver bisogno di essere leggermente aumentata

    private Runnable onUpdateCallback = this::aggiornaListeAlbum;

    private static class AlbumListComponents {
        JPanel containerPanel;
        JPanel albumsDisplayPanel;
        JPanel paginationPanel;

        public AlbumListComponents(JPanel containerPanel, JPanel albumsDisplayPanel, JPanel paginationPanel) {
            this.containerPanel = containerPanel;
            this.albumsDisplayPanel = albumsDisplayPanel;
            this.paginationPanel = paginationPanel;
        }
    }

    public MainFrame() {
        setTitle("MyMusic - La Tua Collezione");
        setSize(1400, 750);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gestore.salvaSuFile();
                dispose();
                System.exit(0);
            }
        });

        setupUI();
        aggiornaListeAlbum();
        setVisible(true);
    }

    private void setupUI() {
        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        navbar.setBackground(new Color(200, 220, 240));
        navbar.setBorder(new EmptyBorder(5, 0, 5, 0));

        JButton addBtn = new JButton("Aggiungi Album");
        addBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addBtn.addActionListener(e -> new AddAlbumFrame(this, gestore, onUpdateCallback));

        JButton modifyBtn = new JButton("Modifica Album");
        modifyBtn.setFont(new Font("Arial", Font.BOLD, 14));
        modifyBtn.addActionListener(e -> new ModificaAlbumFrame(this, gestore, onUpdateCallback));

        JButton searchBtn = new JButton("Cerca/Filtra Album");
        searchBtn.setFont(new Font("Arial", Font.BOLD, 14));
        searchBtn.addActionListener(e -> new SearchAlbumFrame(this, gestore));

        JButton deleteBtn = new JButton("Elimina Album");
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 14));
        deleteBtn.addActionListener(e -> showDeleteAlbumDialog());

        navbar.add(addBtn);
        navbar.add(modifyBtn);
        navbar.add(deleteBtn);
        navbar.add(searchBtn);
        
        add(navbar, BorderLayout.NORTH);

        JPanel mainContentPanel = new JPanel(new GridLayout(1, Artista.StatoCollezione.values().length, 15, 0));
        mainContentPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        mainContentPanel.setBackground(new Color(245, 245, 245));

        for (Artista.StatoCollezione stato : Artista.StatoCollezione.values()) {
            AlbumListComponents components = createAlbumListPanel(stato);
            listComponentsMap.put(stato, components);
            mainContentPanel.add(components.containerPanel);
        }

        add(mainContentPanel, BorderLayout.CENTER);
    }
    
    private void showDeleteAlbumDialog() {
        List<Artista> tuttiGliAlbum = gestore.getTuttiGliAlbum();
        if (tuttiGliAlbum.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Non ci sono album da eliminare.", "Nessun Album", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        tuttiGliAlbum.sort(Comparator.comparing(Artista::getNomeArtista)
                                    .thenComparing(Artista::getTitoloAlbum));
        
        String[] nomiAlbum = tuttiGliAlbum.stream()
                                            .map(Artista::toString)
                                            .toArray(String[]::new);

        String albumSelezionatoString = (String) JOptionPane.showInputDialog(
                this,
                "Seleziona l'album da eliminare definitivamente:",
                "Elimina Album",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nomiAlbum,
                nomiAlbum[0]);

        if (albumSelezionatoString != null) {
            Artista albumDaEliminare = tuttiGliAlbum.stream()
                                                    .filter(a -> a.toString().equals(albumSelezionatoString))
                                                    .findFirst()
                                                    .orElse(null);

            if (albumDaEliminare != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Sei sicuro di voler eliminare definitivamente l'album \"" + albumDaEliminare.getTitoloAlbum() + "\" di " + albumDaEliminare.getNomeArtista() + "?\nQuesta azione non può essere annullata.",
                        "Conferma Eliminazione Definitiva", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    gestore.rimuoviAlbum(albumDaEliminare);
                    aggiornaListeAlbum();
                    JOptionPane.showMessageDialog(this, "Album eliminato definitivamente con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void aggiornaListeAlbum() {
        for (Artista.StatoCollezione stato : Artista.StatoCollezione.values()) {
            updateSingleAlbumList(listComponentsMap.get(stato), stato);
        }
    }

    private void updateSingleAlbumList(AlbumListComponents components, Artista.StatoCollezione stato) {
        JPanel albumsDisplayPanel = components.albumsDisplayPanel;
        JPanel paginationPanel = components.paginationPanel;
        JPanel containerPanel = components.containerPanel;

        albumsDisplayPanel.removeAll();
        paginationPanel.removeAll();

        List<Artista> albumsInStato = gestore.getTuttiGliAlbum().stream()
                .filter(a -> a.getStato() == stato)
                .sorted(Comparator.comparing(Artista::getNomeArtista)
                        .thenComparing(Artista::getAnnoPubblicazione)
                        .thenComparing(Artista::getTitoloAlbum))
                .collect(Collectors.toList());

        int initialCurrentPage = currentPageMap.getOrDefault(stato, 0);
        int adjustedCurrentPage = initialCurrentPage;

        int totalAlbums = albumsInStato.size();
        int totalPages = (int) Math.ceil((double) totalAlbums / ALBUMS_PER_PAGE);

        if (totalAlbums == 0) {
            adjustedCurrentPage = 0;
        } else if (adjustedCurrentPage >= totalPages) {
            adjustedCurrentPage = totalPages - 1;
        }
        if (adjustedCurrentPage < 0) {
            adjustedCurrentPage = 0;
        }

        currentPageMap.put(stato, adjustedCurrentPage);
        final int displayCurrentPage = adjustedCurrentPage;

        int startIndex = displayCurrentPage * ALBUMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ALBUMS_PER_PAGE, totalAlbums);

        for (int i = startIndex; i < endIndex; i++) {
            Artista album = albumsInStato.get(i);
            JPanel albumCard = createAlbumCard(album);
            albumsDisplayPanel.add(albumCard);
        }

        int albumsOnCurrentPage = endIndex - startIndex;
        for (int i = albumsOnCurrentPage; i < ALBUMS_PER_PAGE; i++) {
            JPanel emptyPanel = new JPanel();
            Color bgColor = null;
            switch (stato) {
                case Posseduto:
                    bgColor = new Color(200, 230, 200);
                    break;
                case Desiderato:
                    bgColor = new Color(255, 240, 190);
                    break;
                default:
                    bgColor = new Color(230, 230, 230);
            }
            emptyPanel.setBackground(bgColor); 
            albumsDisplayPanel.add(emptyPanel);
        }
        
        JButton prevBtn = new JButton("Precedente");
        prevBtn.setEnabled(displayCurrentPage > 0);
        prevBtn.addActionListener(e -> {
            int currentPageFromMap = currentPageMap.getOrDefault(stato, 0);
            currentPageMap.put(stato, currentPageFromMap - 1);
            aggiornaListeAlbum();
        });

        JButton nextBtn = new JButton("Successivo");
        nextBtn.setEnabled(displayCurrentPage < totalPages - 1);
        nextBtn.addActionListener(e -> {
            int currentPageFromMap = currentPageMap.getOrDefault(stato, 0);
            currentPageMap.put(stato, currentPageFromMap + 1);
            aggiornaListeAlbum();
        });

        paginationPanel.add(prevBtn);
        paginationPanel.add(new JLabel("Pagina " + (displayCurrentPage + 1) + " di " + totalPages + " (Album: " + totalAlbums + ")"));
        paginationPanel.add(nextBtn);

        albumsDisplayPanel.revalidate();
        albumsDisplayPanel.repaint();
        paginationPanel.revalidate();
        paginationPanel.repaint();
        containerPanel.revalidate();
        containerPanel.repaint();
    }

    private AlbumListComponents createAlbumListPanel(Artista.StatoCollezione stato) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); 

        JLabel titleLabel = new JLabel("Album " + stato.toString(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(new EmptyBorder(5, 0, 5, 0));
        
        Color bgColor;
        switch (stato) {
            case Posseduto:
                bgColor = new Color(200, 230, 200); // Verde chiaro
                break;
            case Desiderato:
                bgColor = new Color(255, 240, 190); // Giallo chiaro
                break;
            default:
                bgColor = new Color(230, 230, 230); // Grigio predefinito
        }
        titleLabel.setBackground(bgColor);
        titleLabel.setOpaque(true);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel listDisplayPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        listDisplayPanel.setBackground(bgColor);
        JScrollPane scrollPane = new JScrollPane(listDisplayPanel);
        scrollPane.getViewport().setBackground(bgColor); 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollPane.setBorder(null); 

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        paginationPanel.setBackground(bgColor);
        panel.add(paginationPanel, BorderLayout.SOUTH);

        panel.setBackground(bgColor); 

        return new AlbumListComponents(panel, listDisplayPanel, paginationPanel);
    }

    private JPanel createAlbumCard(Artista album) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(ALBUM_CARD_WIDTH, ALBUM_CARD_HEIGHT));

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(ALBUM_CARD_WIDTH, 100));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        if (album.getPercorsoFoto() != null && !album.getPercorsoFoto().isEmpty()) {
            try {
                File imageFile = new File(album.getPercorsoFoto());
                if (imageFile.exists() && imageFile.canRead()) {
                    ImageIcon originalIcon = new ImageIcon(ImageIO.read(imageFile));
                    Image image = originalIcon.getImage();
                    Image scaledImage = image.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
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
            imageLabel.setFont(new Font("Arial", Font.PLAIN, 10));
            imageLabel.setForeground(Color.LIGHT_GRAY);
        }
        card.add(imageLabel, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel(new GridLayout(0, 1));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel artistLabel = new JLabel("<html><b>" + album.getNomeArtista() + "</b></html>");
        JLabel titleLabel = new JLabel(album.getTitoloAlbum());

        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        artistLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel yearGenreLabel = new JLabel(album.getAnnoPubblicazione() + " | " + album.getGenere() + " | " + album.getTipoMusica() + " | " + album.getFormato().toString());
        yearGenreLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        JLabel formatLimitedLabel = new JLabel((album.isDeluxeEdition() ? "Deluxe Edition | " : "") + (album.isEdizioneLimitata() ? "Limited Edition" : ""));
        formatLimitedLabel.setFont(new Font("Arial", Font.ITALIC, 10));

        JLabel exclusiveLabel = new JLabel(album.getEsclusive().isEmpty() ? "" : "Escl: " + album.getEsclusive());
        exclusiveLabel.setFont(new Font("Arial", Font.ITALIC, 10));

        // NUOVO: Etichetta per il prezzo
        JLabel priceLabel = new JLabel("Prezzo: " + String.format("%.2f", album.getPrezzo()) + " €");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        priceLabel.setForeground(new Color(0, 100, 0)); // Un colore per il prezzo

        // NUOVO: Etichetta per il luogo di acquisto / link
        JLabel purchaseLocationLabel = new JLabel();
        String luogoAcquisto = album.getLuogoAcquisto();
        if (luogoAcquisto != null && !luogoAcquisto.isEmpty()) {
            if (isValidURL(luogoAcquisto)) {
                purchaseLocationLabel.setText("<html><u><font color='blue'>" + luogoAcquisto + "</font><u></html>"); // Sottolinea e colora di blu per indicare un link
                purchaseLocationLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore a mano
                purchaseLocationLabel.setToolTipText("Clicca per aprire il sito web");

                // Aggiungi un MouseListener per aprire il link
                purchaseLocationLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            // Controlla se il Desktop è supportato e l'azione BROWSE è supportata
                            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                                Desktop.getDesktop().browse(new URI(luogoAcquisto));
                            } else {
                                JOptionPane.showMessageDialog(card, "La navigazione web non è supportata su questo sistema.", "Errore", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (IOException | java.net.URISyntaxException ex) {
                            JOptionPane.showMessageDialog(card, "Impossibile aprire il link: " + luogoAcquisto + "\n" + ex.getMessage(), "Errore Link", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            } else {
                purchaseLocationLabel.setText("Acquistato da: " + luogoAcquisto);
            }
        } else {
            purchaseLocationLabel.setText("Luogo acquisto: N/D");
        }
        purchaseLocationLabel.setFont(new Font("Arial", Font.PLAIN, 10));


        detailsPanel.add(titleLabel);
        detailsPanel.add(artistLabel);
        detailsPanel.add(yearGenreLabel);
        detailsPanel.add(formatLimitedLabel);
        if (!album.getEsclusive().isEmpty()) {
            detailsPanel.add(exclusiveLabel);
        }
        detailsPanel.add(priceLabel); // Aggiungi il prezzo
        detailsPanel.add(purchaseLocationLabel); // Aggiungi il luogo/link di acquisto

        card.add(detailsPanel, BorderLayout.CENTER);

        return card;
    }

    // NUOVO: Metodo per controllare se una stringa è un URL valido
    private boolean isValidURL(String urlString) {
        try {
            new URI(urlString).toURL();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}