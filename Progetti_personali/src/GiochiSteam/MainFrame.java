package GiochiSteam;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.*;

public class MainFrame extends JFrame {

    private GestoreGiochi gestore = new GestoreGiochi();
    private JPanel listaPanel = new JPanel(new GridLayout(2, 3, 10, 10));
    private Map<Gioco.ListaGioco, Color> coloriListe = Map.of(
        Gioco.ListaGioco.Lo_voglio_subito, Color.decode("#d9ead3"),
        Gioco.ListaGioco.Lo_voglio_ma_non_subito, Color.decode("#fff2cc"),
        Gioco.ListaGioco.Lo_voglio_per_collezione, Color.decode("#ffd5a9"),
        Gioco.ListaGioco.Non_lo_so, Color.decode("#cfe2f3"),
        Gioco.ListaGioco.Non_ce_bisogno, Color.decode("#f4cccc")
    );

    public MainFrame() {
        setTitle("Le Mie Liste Giochi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        gestore.caricaDaFile();

        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton aggiungiBtn = new JButton("➕ Aggiungi gioco");
        JButton modificaBtn = new JButton("✏️ Modifica gioco");
        navbar.add(aggiungiBtn);
        navbar.add(modificaBtn);
        add(navbar, BorderLayout.NORTH);

        aggiungiBtn.addActionListener(e -> new AddFrame(this, gestore, this::aggiornaListe));
        modificaBtn.addActionListener(e -> new ModificaFrame(this, gestore, this::aggiornaListe));

        add(listaPanel, BorderLayout.CENTER);
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

        for (Gioco.ListaGioco lista : Gioco.ListaGioco.values()) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(coloriListe.get(lista));
            card.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 2, true), new EmptyBorder(10, 10, 10, 10)));

            JLabel titolo = new JLabel(lista.name().replace("_", " "));
            titolo.setFont(new Font("SansSerif", Font.BOLD, 14));
            card.add(titolo);

            for (Gioco g : gestore.getTuttiIGiochi()) {
                if (g.getLista() == lista) {
                    JButton giocoBtn = new JButton("<html>" + g.getNome() + " (€" + g.getPrezzo() + ")<br/>Shop: " + g.getShop() + "</html>");
                    giocoBtn.setFocusPainted(false);
                    giocoBtn.setBackground(new Color(255, 255, 255));
                    giocoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    giocoBtn.addActionListener(ev -> apriLinkSteam(g.getSteamlink()));
                    card.add(giocoBtn);
                }
            }

            listaPanel.add(card);
        }

        listaPanel.revalidate();
        listaPanel.repaint();
    }

    private void apriLinkSteam(String link) {
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore nel link.");
        }
    }
}
