import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.*;

public class MainFrame extends JFrame {

    private GestoreGiochi gestore = new GestoreGiochi();
    private JPanel listaPanel = new JPanel(new GridLayout(1, 0, 10, 10)); // Un solo riga, colonne dinamiche
    private Map<Gioco.ListaGioco, Color> coloriListe = Map.of(
            Gioco.ListaGioco.Lo_voglio_subito, Color.decode("#d9ead3"), // Verde chiaro
            Gioco.ListaGioco.Lo_voglio_ma_non_subito, Color.decode("#fff2cc"), // Giallo chiaro
            Gioco.ListaGioco.Lo_voglio_per_collezione, Color.decode("#ffd5a9"), // Arancione chiaro
            Gioco.ListaGioco.Non_lo_so, Color.decode("#cfe2f3"), // Blu chiaro
            Gioco.ListaGioco.Non_ce_bisogno, Color.decode("#f4cccc") // Rosso chiaro
    );

    public MainFrame() {
        setTitle("Le Mie Liste Giochi Steam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 700); // Dimensioni aumentate per più spazio
        setLocationRelativeTo(null); // Centra la finestra
        setLayout(new BorderLayout(10, 10));

        // Carica i giochi all'avvio
        gestore.caricaDaFile();

        // Navbar con pulsanti
        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton aggiungiBtn = new JButton("➕ Aggiungi gioco");
        JButton modificaBtn = new JButton("✏️ Modifica/Elimina gioco");

        // Stile per i pulsanti della navbar
        aggiungiBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        modificaBtn.setFont(new Font("SansSerif", Font.BOLD, 12));

        navbar.add(aggiungiBtn);
        navbar.add(modificaBtn);
        add(navbar, BorderLayout.NORTH);

        // Listeners per i pulsanti
        aggiungiBtn.addActionListener(e -> new AddFrame(this, gestore, this::aggiornaListe));
        modificaBtn.addActionListener(e -> new ModificaFrame(this, gestore, this::aggiornaListe));

        // Pannello principale per le liste, avvolto in uno JScrollPane
        JScrollPane scrollPane = new JScrollPane(listaPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Liste in orizzontale
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Rimuovi il bordo predefinito dello scroll pane
        add(scrollPane, BorderLayout.CENTER);


        aggiornaListe(); // Popola le liste all'avvio

        // Salva i dati alla chiusura della finestra
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gestore.salvaSuFile();
            }
        });

        setVisible(true);
    }

    private void aggiornaListe() {
        listaPanel.removeAll(); // Rimuove tutti i pannelli delle liste esistenti
        listaPanel.setLayout(new GridLayout(1, Gioco.ListaGioco.values().length, 10, 10)); // Aggiorna il layout per nuove colonne


        for (Gioco.ListaGioco lista : Gioco.ListaGioco.values()) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS)); // Layout verticale per i giochi
            card.setBackground(coloriListe.get(lista)); // Colore di sfondo della card
            card.setBorder(new CompoundBorder(
                    new LineBorder(Color.GRAY, 1, true), // Bordo sottile e arrotondato
                    new EmptyBorder(10, 10, 10, 10) // Padding interno
            ));

            JLabel titolo = new JLabel(lista.name().replace("_", " ").toUpperCase()); // Titolo della lista
            titolo.setFont(new Font("SansSerif", Font.BOLD, 16));
            titolo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra il titolo
            card.add(titolo);
            card.add(Box.createRigidArea(new Dimension(0, 10))); // Spazio sotto il titolo

            // Pannello per i bottoni dei giochi, con uno scroll interno
            JPanel gamesContainer = new JPanel();
            gamesContainer.setLayout(new BoxLayout(gamesContainer, BoxLayout.Y_AXIS));
            gamesContainer.setOpaque(false); // Rende trasparente per mostrare il colore della card

            // Ordina i giochi per nome prima di visualizzarli
            gestore.getTuttiIGiochi().stream()
                    .filter(g -> g.getLista() == lista)
                    .sorted(Comparator.comparing(Gioco::getNome))
                    .forEach(g -> {
                        // Creazione del pulsante personalizzato per ogni gioco
                        JButton giocoBtn = new JButton("<html><div style='text-align: center;'>"
                                + "<b>" + g.getNome() + "</b><br/>"
                                + "€" + String.format("%.2f", g.getPrezzo()) + " | "
                                + g.getShop() + "</div></html>");
                        giocoBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
                        giocoBtn.setBackground(new Color(240, 240, 240)); // Un colore di sfondo leggermente diverso
                        giocoBtn.setForeground(Color.BLACK);
                        giocoBtn.setFocusPainted(false);
                        giocoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        giocoBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Altezza fissa, larghezza massima
                        giocoBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra il pulsante

                        // Listener per aprire il link Steam
                        giocoBtn.addActionListener(ev -> apriLinkSteam(g.getSteamlink()));

                        // Aggiunge un bordo e spazio intorno al pulsante
                        giocoBtn.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                                BorderFactory.createEmptyBorder(5, 10, 5, 10)
                        ));
                        gamesContainer.add(giocoBtn);
                        gamesContainer.add(Box.createRigidArea(new Dimension(0, 5))); // Spazio tra i pulsanti
                    });

            JScrollPane gamesScrollPane = new JScrollPane(gamesContainer);
            gamesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            gamesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            gamesScrollPane.setBorder(BorderFactory.createEmptyBorder()); // Rimuovi il bordo dello scroll pane
            gamesScrollPane.setOpaque(false);
            gamesScrollPane.getViewport().setOpaque(false); // Rende trasparente il viewport

            card.add(gamesScrollPane);
            listaPanel.add(card);
        }

        listaPanel.revalidate(); // Ricalcola il layout
        listaPanel.repaint();   // Ridipinge i componenti
    }

    private void apriLinkSteam(String link) {
        if (link == null || link.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nessun link Steam disponibile per questo gioco.", "Informazione", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            // Controlla se il Desktop è supportato
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