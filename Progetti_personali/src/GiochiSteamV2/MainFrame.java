package GiochiSteamV2;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.*;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private GestoreGiochi gestore = new GestoreGiochi();
    private JPanel listaPanel = new JPanel(new GridLayout(1, 0, 10, 10)); // Modificato a 1 riga, 0 colonne per scroll orizzontale
    private Map<Gioco.ListaGioco, Color> coloriListe = Map.of(
        Gioco.ListaGioco.Lo_voglio_subito, Color.decode("#d9ead3"),
        Gioco.ListaGioco.Lo_voglio_ma_non_subito, Color.decode("#fff2cc"),
        Gioco.ListaGioco.Lo_voglio_per_collezione, Color.decode("#ffd5a9"),
        Gioco.ListaGioco.Non_lo_so, Color.decode("#cfe2f3"),
        Gioco.ListaGioco.Non_ce_bisogno, Color.decode("#f4cccc")
    );

    public MainFrame() {
        setTitle("MyGames | by VixiGamer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 700); // Aumentate le dimensioni per supportare più liste orizzontalmente
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        gestore.caricaDaFile();

        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Aggiunta spaziatura
        JButton aggiungiBtn = new JButton("➕ Aggiungi gioco");
        JButton modificaBtn = new JButton("✏️ Modifica/Elimina gioco"); // Testo più chiaro

        aggiungiBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        modificaBtn.setFont(new Font("SansSerif", Font.BOLD, 12));

        navbar.add(aggiungiBtn);
        navbar.add(modificaBtn);
        add(navbar, BorderLayout.NORTH);

        aggiungiBtn.addActionListener(e -> new AddFrame(this, gestore, this::aggiornaListe));
        modificaBtn.addActionListener(e -> new ModificaFrame(this, gestore, this::aggiornaListe));

        JScrollPane scrollPane = new JScrollPane(listaPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Nessun bordo
        add(scrollPane, BorderLayout.CENTER);

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
        listaPanel.setLayout(new GridLayout(1, Gioco.ListaGioco.values().length, 10, 10)); // Layout dinamico per le card

        for (Gioco.ListaGioco lista : Gioco.ListaGioco.values()) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(coloriListe.get(lista));
            card.setBorder(new CompoundBorder(
                    new LineBorder(Color.GRAY, 1, true),
                    new EmptyBorder(10, 10, 10, 10)
            ));

            JLabel titolo = new JLabel(lista.name().replace("_", " ").toUpperCase()); // Titolo della lista, tutto maiuscolo
            titolo.setFont(new Font("SansSerif", Font.BOLD, 16));
            titolo.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.add(titolo);
            card.add(Box.createRigidArea(new Dimension(0, 10)));

            JPanel gamesContainer = new JPanel(); // Contenitore per i pulsanti dei giochi
            gamesContainer.setLayout(new BoxLayout(gamesContainer, BoxLayout.Y_AXIS));
            gamesContainer.setOpaque(false);

            gestore.getTuttiIGiochi().stream()
                    .filter(g -> g.getLista() == lista)
                    .sorted(Comparator.comparing(Gioco::getNome)) // Ordina i giochi per nome
                    .forEach(g -> {
                        JButton giocoBtn = new JButton("<html><div style='text-align: center;'>"
                                + "<b>" + g.getNome() + "</b><br/>"
                                + "€" + String.format("%.2f", g.getPrezzo()) + " | "
                                + g.getShop() + "</div></html>");

                        giocoBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
                        giocoBtn.setBackground(new Color(240, 240, 240));
                        giocoBtn.setForeground(Color.BLACK);
                        giocoBtn.setFocusPainted(false);
                        giocoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        giocoBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
                        giocoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

                        giocoBtn.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                                BorderFactory.createEmptyBorder(5, 10, 5, 10)
                        ));
                        giocoBtn.addActionListener(ev -> apriLinkSteam(g.getSteamlink()));

                        gamesContainer.add(giocoBtn);
                        gamesContainer.add(Box.createRigidArea(new Dimension(0, 5)));
                    });

            JScrollPane gamesScrollPane = new JScrollPane(gamesContainer);
            gamesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            gamesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            gamesScrollPane.setBorder(BorderFactory.createEmptyBorder());
            gamesScrollPane.setOpaque(false);
            gamesScrollPane.getViewport().setOpaque(false);

            card.add(gamesScrollPane);
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