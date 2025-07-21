package GiochiSteamV2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays; 

public class SearchFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private GestoreGiochi gestore;
    private JTextField searchField;
    // Modificato il tipo del JComboBox per lavorare direttamente con Gioco.ListaGioco
    private JComboBox<Object> listaFilterBox; 
    private JComboBox<String> priceFilterBox;
    private JComboBox<String> shopFilterBox;
    private JPanel gamesDisplayPanel; // Pannello dove verranno mostrati i giochi

    public SearchFrame(JFrame parent, GestoreGiochi gestore) {
        this.gestore = gestore;
        setTitle("Cerca Gioco");
        setSize(625, 600); // Dimensioni adeguate per la ricerca
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10)); // Layout principale

        // Pannello principale per la barra di ricerca e i filtri
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding generale

        // Sub-pannello per il campo di ricerca
        JPanel searchFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, FlowLayout.CENTER));
        searchField = new JTextField(20);
        searchField.putClientProperty("JTextField.placeholderText", "Cerca per nome..."); // Placeholder text
        searchField.addActionListener(e -> updateGamesDisplay()); // Aggiorna al premere Invio
        // Aggiungi un DocumentListener per aggiornare dinamicamente durante la digitazione
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateGamesDisplay(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateGamesDisplay(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateGamesDisplay(); }
        });
        searchFieldPanel.add(new JLabel("Nome:"));
        searchFieldPanel.add(searchField);
        searchFieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Allinea a sinistra nel BoxLayout superiore

        // Sub-pannello per i filtri Lista, Prezzo, Shop
        JPanel filterBoxesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        // Filtro per Lista - Modificato per usare direttamente gli oggetti enum
        DefaultComboBoxModel<Object> listaModel = new DefaultComboBoxModel<>();
        listaModel.addElement("Tutte le Liste"); // Opzione per non filtrare
        for (Gioco.ListaGioco lg : Gioco.ListaGioco.values()) {
            listaModel.addElement(lg); // Aggiungi gli oggetti enum reali
        }
        listaFilterBox = new JComboBox<>(listaModel);
        // Aggiungi un renderer personalizzato per mostrare il testo corretto dell'enum
        listaFilterBox.setRenderer(new DefaultListCellRenderer() {

			private static final long serialVersionUID = 1L;

			@Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Gioco.ListaGioco) {
                    setText(((Gioco.ListaGioco) value).toString()); // Usa il toString dell'enum
                } else if (value instanceof String) {
                    setText((String) value); // Per l'opzione "Tutte le Liste"
                }
                return this;
            }
        });
        listaFilterBox.addActionListener(e -> updateGamesDisplay());
        filterBoxesPanel.add(new JLabel("Lista:"));
        filterBoxesPanel.add(listaFilterBox);

        // Filtro per Prezzo
        String[] priceOptions = {"Nessuno", "Prezzo Crescente", "Prezzo Decrescente", "Meno di €1", "Meno di €3", "Meno di €5", "Meno di €10"};
        priceFilterBox = new JComboBox<>(priceOptions);
        priceFilterBox.addActionListener(e -> updateGamesDisplay());
        filterBoxesPanel.add(new JLabel("Prezzo:"));
        filterBoxesPanel.add(priceFilterBox);

        // Filtro per Shop
        String[] shopOptions = Arrays.stream(Gioco.ListaShop.values())
                                     .map(Enum::toString)
                                     .collect(Collectors.toList())
                                     .toArray(new String[0]);
        String[] allShopOptions = new String[shopOptions.length + 1];
        allShopOptions[0] = "Tutti gli Shop";
        System.arraycopy(shopOptions, 0, allShopOptions, 1, shopOptions.length);
        shopFilterBox = new JComboBox<>(allShopOptions);
        shopFilterBox.addActionListener(e -> updateGamesDisplay());
        filterBoxesPanel.add(new JLabel("Shop:"));
        filterBoxesPanel.add(shopFilterBox);
        filterBoxesPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Allinea a sinistra nel BoxLayout superiore


        // Aggiungi i sub-pannelli al pannello principale dei filtri
        filterPanel.add(searchFieldPanel);
        filterPanel.add(filterBoxesPanel);

        add(filterPanel, BorderLayout.NORTH);

        // Pannello per la visualizzazione dei giochi
        gamesDisplayPanel = new JPanel();
        gamesDisplayPanel.setLayout(new BoxLayout(gamesDisplayPanel, BoxLayout.Y_AXIS)); // I giochi si impilano verticalmente
        JScrollPane scrollPane = new JScrollPane(gamesDisplayPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Rimuovi il bordo predefinito
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        updateGamesDisplay(); // Chiamata iniziale per popolare i giochi

        setVisible(true);
    }

    private void updateGamesDisplay() {
        gamesDisplayPanel.removeAll(); // Pulisce i giochi esistenti

        List<Gioco> filteredGames = gestore.getTuttiIGiochi();

        // 1. Filtro per nome (barra di ricerca)
        String searchText = searchField.getText().trim();
        if (!searchText.isEmpty()) {
            filteredGames = filteredGames.stream()
                .filter(g -> g.getNome().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
        }

        // 2. Filtro per Lista - Modificato per usare direttamente gli oggetti enum
        Object selectedListaObj = listaFilterBox.getSelectedItem();
        // Filtra solo se l'elemento selezionato è un'istanza di Gioco.ListaGioco (non "Tutte le Liste")
        if (selectedListaObj instanceof Gioco.ListaGioco) {
            Gioco.ListaGioco listaEnum = (Gioco.ListaGioco) selectedListaObj;
            filteredGames = filteredGames.stream()
                .filter(g -> g.getLista() == listaEnum)
                .collect(Collectors.toList());
        }


        // 3. Filtro per Shop
        String selectedShop = (String) shopFilterBox.getSelectedItem();
        if (selectedShop != null && !selectedShop.equals("Tutti gli Shop")) {
            try {
                Gioco.ListaShop shopEnum = Gioco.ListaShop.valueOf(selectedShop);
                filteredGames = filteredGames.stream()
                    .filter(g -> g.getShop() == shopEnum)
                    .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                System.err.println("Errore: Impossibile trovare lo shop enum per: " + selectedShop);
            }
        }

        // 4. Ordinamento e Filtro per Prezzo
        String priceOrder = (String) priceFilterBox.getSelectedItem();
        if (priceOrder != null) {
            switch (priceOrder) {
                case "Prezzo Crescente":
                    filteredGames.sort(Comparator.comparing(Gioco::getPrezzo));
                    break;
                case "Prezzo Decrescente":
                    filteredGames.sort(Comparator.comparing(Gioco::getPrezzo).reversed());
                    break;
                case "Meno di €1":
                    filteredGames = filteredGames.stream()
                        .filter(g -> g.getPrezzo() <= 1.0) // Modificato da < a <=
                        .collect(Collectors.toList());
                    break;
                case "Meno di €3":
                    filteredGames = filteredGames.stream()
                        .filter(g -> g.getPrezzo() <= 3.0) // Modificato da < a <=
                        .collect(Collectors.toList());
                    break;
                case "Meno di €5":
                    filteredGames = filteredGames.stream()
                        .filter(g -> g.getPrezzo() <= 5.0) // Modificato da < a <=
                        .collect(Collectors.toList());
                    break;
                case "Meno di €10":
                    filteredGames = filteredGames.stream()
                        .filter(g -> g.getPrezzo() <= 10.0) // Modificato da < a <=
                        .collect(Collectors.toList());
                    break;
            }
        }

        if (filteredGames.isEmpty()) {
            JLabel noResultsLabel = new JLabel("Nessun gioco trovato con i criteri di ricerca/filtro.");
            noResultsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            gamesDisplayPanel.add(Box.createVerticalGlue());
            gamesDisplayPanel.add(noResultsLabel);
            gamesDisplayPanel.add(Box.createVerticalGlue());
        } else {
            for (Gioco g : filteredGames) {
                // Creazione del pannello per ogni gioco, con un layout più flessibile (es. BorderLayout o GridBagLayout)
                JPanel giocoPanel = new JPanel(new BorderLayout(5, 0)); // Piccola spaziatura
                giocoPanel.setBorder(BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                                        BorderFactory.createEmptyBorder(5, 10, 5, 10)));
                
                // Imposta la dimensione fissa per il box del gioco
                int fixedWidth = 580; // Larghezza fissa desiderata (puoi aggiustarla)
                int fixedHeight = 70; // Altezza fissa desiderata
                giocoPanel.setPreferredSize(new Dimension(fixedWidth, fixedHeight));
                giocoPanel.setMinimumSize(new Dimension(fixedWidth, fixedHeight));
                giocoPanel.setMaximumSize(new Dimension(fixedWidth, fixedHeight)); 
                
                giocoPanel.setBackground(Color.WHITE);

                // Dettagli del gioco a sinistra
                JPanel detailsPanel = new JPanel(new GridLayout(2, 1)); // Ho ripristinato GridLayout(2,1) per nome sopra info
                detailsPanel.setOpaque(false);
                JLabel nomeLabel = new JLabel("<html><b>" + g.getNome() + "</b></html>");
                // Modifica qui: Rimuovi le etichette "Prezzo: ", "Shop: ", "Lista: "
                JLabel infoLabel = new JLabel(String.format("€%.2f | %s | %s", g.getPrezzo(), g.getShop().toString(), g.getLista().toString()));
                detailsPanel.add(nomeLabel);
                detailsPanel.add(infoLabel);
                giocoPanel.add(detailsPanel, BorderLayout.CENTER);

                // Pulsante Link Steam a destra
                if (g.getSteamlink() != null && !g.getSteamlink().trim().isEmpty()) {
                    // Rinomina il pulsante
                    JButton linkBtn = new JButton("Steam Store"); 
                    linkBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    linkBtn.addActionListener(e -> apriLinkSteam(g.getSteamlink()));
                    
                    // Pannello per centrare verticalmente il pulsante
                    JPanel linkButtonContainer = new JPanel(new GridBagLayout()); // Usa GridBagLayout per centratura precisa
                    linkButtonContainer.setOpaque(false);
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.CENTER; // Ancoraggio al centro
                    linkButtonContainer.add(linkBtn, gbc);

                    giocoPanel.add(linkButtonContainer, BorderLayout.EAST); // Aggiungi il container del pulsante a destra
                }

                gamesDisplayPanel.add(giocoPanel);
                gamesDisplayPanel.add(Box.createVerticalStrut(5)); // Spazio tra i giochi
            }
        }

        gamesDisplayPanel.revalidate();
        gamesDisplayPanel.repaint();
    }

    // Metodo helper per aprire il link Steam, copiato da MainFrame
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