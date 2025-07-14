package GiochiSteamV2;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private GestoreGiochi gestore = new GestoreGiochi();
    private JPanel listaPanel = new JPanel(new GridLayout(1, 0, 10, 10));
    private Map<Gioco.ListaGioco, Color> coloriListe = Map.of(
        Gioco.ListaGioco.Lo_voglio_subito, Color.decode("#d9ead3"),
        Gioco.ListaGioco.Lo_voglio_ma_non_subito, Color.decode("#fff2cc"),
        Gioco.ListaGioco.Lo_voglio_per_collezione, Color.decode("#ffd5a9"),
        Gioco.ListaGioco.Non_lo_so, Color.decode("#cfe2f3"),
        Gioco.ListaGioco.Non_ce_bisogno, Color.decode("#f4cccc")
    );

    // Mappa per tenere traccia della pagina corrente per ogni lista
    private Map<Gioco.ListaGioco, Integer> currentPageMap = new HashMap<>();
    private final int GAMES_PER_PAGE = 9; // Numero di giochi per pagina, cambiato a 9
    private final int GAME_BUTTON_HEIGHT = 55; // Altezza di un singolo bottone gioco, ridotta a 55
    private final int GAME_SPACING_HEIGHT = 5; // Spazio rigido tra i bottoni

    public MainFrame() {
        setTitle("MyGames | by VixiGamer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1450, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        gestore.caricaDaFile();

        // Inizializza le pagine correnti per tutte le liste a 0 (prima pagina)
        for (Gioco.ListaGioco lista : Gioco.ListaGioco.values()) {
            currentPageMap.put(lista, 0);
        }

        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton aggiungiBtn = new JButton("➕ Aggiungi gioco");
        JButton modificaBtn = new JButton("✏️ Modifica gioco");
        JButton eliminaPrincipaleBtn = new JButton("🗑️ Elimina gioco");

        aggiungiBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        modificaBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        eliminaPrincipaleBtn.setFont(new Font("SansSerif", Font.BOLD, 12));

        navbar.add(aggiungiBtn);
        navbar.add(modificaBtn);
        navbar.add(eliminaPrincipaleBtn);
        add(navbar, BorderLayout.NORTH);

        aggiungiBtn.addActionListener(e -> new AddFrame(this, gestore, this::aggiornaListe));
        modificaBtn.addActionListener(e -> new ModificaFrame(this, gestore, this::aggiornaListe));

        eliminaPrincipaleBtn.addActionListener(e -> {
            List<Gioco> tuttiIGiochi = gestore.getTuttiIGiochi();
            if (tuttiIGiochi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Non ci sono giochi da eliminare.", "Nessun Gioco", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String[] nomiGiochi = tuttiIGiochi.stream()
                                            .map(Gioco::getNome)
                                            .toArray(String[]::new);

            String giocoSelezionatoNome = (String) JOptionPane.showInputDialog(
                    this,
                    "Seleziona il gioco da eliminare:",
                    "Elimina Gioco",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    nomiGiochi,
                    nomiGiochi[0]);

            if (giocoSelezionatoNome != null) {
                Gioco giocoDaEliminare = tuttiIGiochi.stream()
                                                    .filter(g -> g.getNome().equals(giocoSelezionatoNome))
                                                    .findFirst()
                                                    .orElse(null);

                if (giocoDaEliminare != null) {
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "Sei sicuro di voler eliminare il gioco \"" + giocoDaEliminare.getNome() + "\"?",
                            "Conferma Eliminazione", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        gestore.rimuoviGioco(giocoDaEliminare);
                        aggiornaListe();
                        JOptionPane.showMessageDialog(this, "Gioco eliminato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        JScrollPane mainScrollPane = new JScrollPane(listaPanel);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(mainScrollPane, BorderLayout.CENTER);

        aggiornaListe();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gestore.salvaSuFile();
            }
        });

        setVisible(true);
    }

    private void aggiornaListe() {
        listaPanel.removeAll();
        listaPanel.setLayout(new GridLayout(1, Gioco.ListaGioco.values().length, 10, 10));

        for (Gioco.ListaGioco lista : Gioco.ListaGioco.values()) {
            JPanel card = new JPanel(new BorderLayout()); // Using BorderLayout for the card
            card.setBackground(coloriListe.get(lista));
            card.setBorder(new CompoundBorder(
                    new LineBorder(Color.GRAY, 1, true),
                    new EmptyBorder(10, 10, 10, 10)
            ));

            // Wrapper panel for title and its padding, using BoxLayout.Y_AXIS
            JPanel titlePanelWrapper = new JPanel();
            titlePanelWrapper.setLayout(new BoxLayout(titlePanelWrapper, BoxLayout.Y_AXIS));
            titlePanelWrapper.setBackground(coloriListe.get(lista));

            JLabel titolo = new JLabel(lista.toString().toUpperCase());
            titolo.setFont(new Font("SansSerif", Font.BOLD, 16));
            titolo.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
            titlePanelWrapper.add(titolo);
            titlePanelWrapper.add(Box.createRigidArea(new Dimension(0, 10))); // 10px space below title

            card.add(titlePanelWrapper, BorderLayout.NORTH); // Add the wrapper to the NORTH of the card

            // Changed from JPanel to Box.createVerticalBox()
            Box gamesDisplayPanel = Box.createVerticalBox();
            gamesDisplayPanel.setOpaque(false);
            
            // Calcola l'altezza totale desiderata per il gamesDisplayPanel
            int totalGamesPanelHeight = (GAMES_PER_PAGE * GAME_BUTTON_HEIGHT) + ((GAMES_PER_PAGE - 1) * GAME_SPACING_HEIGHT);
            gamesDisplayPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, totalGamesPanelHeight));
            gamesDisplayPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, totalGamesPanelHeight));
            gamesDisplayPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, totalGamesPanelHeight));


            List<Gioco> giochiNellaLista = gestore.getTuttiIGiochi().stream()
                    .filter(g -> g.getLista() == lista)
                    .sorted(Comparator.comparing(Gioco::getNome))
                    .collect(Collectors.toList());

            int currentPage = currentPageMap.getOrDefault(lista, 0);
            int totalGames = giochiNellaLista.size();
            int totalPages = (int) Math.ceil((double) totalGames / GAMES_PER_PAGE);

            if (totalPages == 0) {
                currentPage = 0;
            } else if (currentPage >= totalPages) {
                currentPage = totalPages - 1;
            }
            currentPageMap.put(lista, currentPage);


            int startIndex = currentPage * GAMES_PER_PAGE;
            int endIndex = Math.min(startIndex + GAMES_PER_PAGE, totalGames);

            // Aggiungi solo i giochi della pagina corrente e gli spazi rigidi
            for (int i = startIndex; i < endIndex; i++) {
                Gioco g = giochiNellaLista.get(i);
                JButton giocoBtn = new JButton("<html><div style='text-align: center;'>"
                        + "<b>" + g.getNome() + "</b><br/>"
                        + "€" + String.format("%.2f", g.getPrezzo()) + " | "
                        + g.getShop() + "</div></html>");

                giocoBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
                giocoBtn.setBackground(new Color(240, 240, 240));
                giocoBtn.setForeground(Color.BLACK);
                giocoBtn.setFocusPainted(false);
                giocoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                // Impostato preferita, massima e minima altezza per il singolo bottone
                giocoBtn.setPreferredSize(new Dimension(Integer.MAX_VALUE, GAME_BUTTON_HEIGHT));
                giocoBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, GAME_BUTTON_HEIGHT));
                giocoBtn.setMinimumSize(new Dimension(Integer.MAX_VALUE, GAME_BUTTON_HEIGHT));
                
                giocoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

                giocoBtn.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
                giocoBtn.addActionListener(ev -> apriLinkSteam(g.getSteamlink()));

                gamesDisplayPanel.add(giocoBtn);
                // Aggiungi spazio rigido dopo ogni bottone, tranne l'ultimo slot logico
                if (gamesDisplayPanel.getComponentCount() / 2 < GAMES_PER_PAGE) {
                    gamesDisplayPanel.add(Box.createRigidArea(new Dimension(0, GAME_SPACING_HEIGHT)));
                }
            }
            
            // Riempie lo spazio rimanente con pannelli vuoti per mantenere l'altezza fissa della pagina
            int actualGamesAdded = endIndex - startIndex;
            int emptySlotsToFill = GAMES_PER_PAGE - actualGamesAdded;
            
            for (int i = 0; i < emptySlotsToFill; i++) {
                JPanel emptyPanel = new JPanel();
                emptyPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, GAME_BUTTON_HEIGHT));
                emptyPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, GAME_BUTTON_HEIGHT));
                emptyPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, GAME_BUTTON_HEIGHT));
                emptyPanel.setOpaque(false); // Rende il pannello trasparente
                gamesDisplayPanel.add(emptyPanel);
                // Aggiungi spazio rigido dopo ogni pannello vuoto, tranne l'ultimo slot logico complessivo
                if ((actualGamesAdded + i + 1) < GAMES_PER_PAGE) {
                     gamesDisplayPanel.add(Box.createRigidArea(new Dimension(0, GAME_SPACING_HEIGHT)));
                }
            }
            
            // Aggiungi il pannello dei giochi alla card
            card.add(gamesDisplayPanel, BorderLayout.CENTER);

            // Aggiungi i pulsanti di paginazione
            if (totalPages > 1) {
                JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
                paginationPanel.setOpaque(false);

                JButton prevBtn = new JButton("<");
                prevBtn.setFont(new Font("SansSerif", Font.BOLD, 10));
                prevBtn.setFocusPainted(false);
                prevBtn.setEnabled(currentPage > 0);
                prevBtn.addActionListener(e -> {
                    int current = currentPageMap.getOrDefault(lista, 0); 
                    currentPageMap.put(lista, current - 1);
                    aggiornaListe(); 
                });

                JButton nextBtn = new JButton(">");
                nextBtn.setFont(new Font("SansSerif", Font.BOLD, 10));
                nextBtn.setFocusPainted(false);
                nextBtn.setEnabled(currentPage < totalPages - 1);
                nextBtn.addActionListener(e -> {
                    int current = currentPageMap.getOrDefault(lista, 0);
                    currentPageMap.put(lista, current + 1);
                    aggiornaListe(); 
                });

                paginationPanel.add(prevBtn);
                paginationPanel.add(new JLabel("Pagina " + (currentPage + 1) + " di " + totalPages));
                paginationPanel.add(nextBtn);

                card.add(paginationPanel, BorderLayout.SOUTH);
            }
            
            listaPanel.add(card);
        }

        listaPanel.revalidate();
        listaPanel.repaint();
    }

    private void apriLinkSteam(String link) {
        if (link == null || link.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nessun link Steam disponibile per questo gioco.", "Informazione", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(link));
            } else {
                JOptionPane.showMessageDialog(this, "La navigazione web non è supportata su questo sistema.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore durante l'apertura del link: " + e.getMessage(), "Errore Link", JOptionPane.ERROR_MESSAGE);
        }
    }
}