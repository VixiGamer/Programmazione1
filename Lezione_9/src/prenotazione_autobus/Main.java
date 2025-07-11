package prenotazione_autobus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {
    private static final int MAX_PASSEGGERI = 20;
    private static final int MAX_AUTISTI = 2;

    private static ArrayList<UtenteAutobus> utenti = new ArrayList<>();

    private static int countPasseggeri = 0;
    private static int countAutisti = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> creaGUI());
    }

    private static void creaGUI() {
        JFrame frame = new JFrame("Prenotazione Autobus");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblModalita = new JLabel("Modalità: Passeggero");
        lblModalita.setBounds(30, 20, 300, 30);
        frame.add(lblModalita);

        JButton btnPasseggero = new JButton("Passeggero");
        btnPasseggero.setBounds(30, 60, 120, 30);
        frame.add(btnPasseggero);

        JButton btnAutista = new JButton("Autista");
        btnAutista.setBounds(170, 60, 120, 30);
        frame.add(btnAutista);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 110, 100, 30);
        frame.add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(130, 110, 200, 30);
        frame.add(txtNome);

        JLabel lblCognome = new JLabel("Cognome:");
        lblCognome.setBounds(30, 150, 100, 30);
        frame.add(lblCognome);

        JTextField txtCognome = new JTextField();
        txtCognome.setBounds(130, 150, 200, 30);
        frame.add(txtCognome);

        JLabel lblCF = new JLabel("Codice Fiscale:");
        lblCF.setBounds(30, 190, 100, 30);
        frame.add(lblCF);

        JTextField txtCF = new JTextField();
        txtCF.setBounds(130, 190, 200, 30);
        frame.add(txtCF);

        JLabel lblDestinazione = new JLabel("Destinazione:");
        lblDestinazione.setBounds(30, 230, 100, 30);
        frame.add(lblDestinazione);

        JTextField txtDestinazione = new JTextField();
        txtDestinazione.setBounds(130, 230, 200, 30);
        frame.add(txtDestinazione);

        JCheckBox chkBagaglio = new JCheckBox("Bagaglio");
        chkBagaglio.setBounds(130, 270, 100, 30);
        frame.add(chkBagaglio);

        JLabel lblPatente = new JLabel("Patente:");
        lblPatente.setBounds(30, 190, 100, 30);
        frame.add(lblPatente);

        JTextField txtPatente = new JTextField();
        txtPatente.setBounds(130, 190, 200, 30);
        frame.add(txtPatente);

        JLabel lblEsperienza = new JLabel("Anni Esperienza:");
        lblEsperienza.setBounds(30, 230, 120, 30);
        frame.add(lblEsperienza);

        JTextField txtEsperienza = new JTextField();
        txtEsperienza.setBounds(160, 230, 170, 30);
        frame.add(txtEsperienza);

        JButton btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.setBounds(130, 310, 100, 30);
        frame.add(btnAggiungi);

        JLabel lblPostiPasseggeri = new JLabel("Posti Passeggeri: 20");
        lblPostiPasseggeri.setBounds(400, 20, 200, 30);
        frame.add(lblPostiPasseggeri);

        JLabel lblPostiAutisti = new JLabel("Posti Autisti: 2");
        lblPostiAutisti.setBounds(400, 60, 200, 30);
        frame.add(lblPostiAutisti);

        JLabel lblElencoPasseggeri = new JLabel("<html><b>Passeggeri:</b><br></html>");
        lblElencoPasseggeri.setVerticalAlignment(SwingConstants.TOP);
        JScrollPane scrollPasseggeri = new JScrollPane(lblElencoPasseggeri);
        scrollPasseggeri.setBounds(400, 110, 350, 180);
        frame.add(scrollPasseggeri);

        JLabel lblElencoAutisti = new JLabel("<html><b>Autisti:</b><br></html>");
        lblElencoAutisti.setVerticalAlignment(SwingConstants.TOP);
        JScrollPane scrollAutisti = new JScrollPane(lblElencoAutisti);
        scrollAutisti.setBounds(400, 310, 350, 180);
        frame.add(scrollAutisti);

        final boolean[] modalitaPasseggero = {true};

        btnPasseggero.addActionListener(e -> {
            modalitaPasseggero[0] = true;
            lblModalita.setText("Modalità: Passeggero");
            lblCF.setVisible(true);
            txtCF.setVisible(true);
            lblDestinazione.setVisible(true);
            txtDestinazione.setVisible(true);
            chkBagaglio.setVisible(true);
            lblPatente.setVisible(false);
            txtPatente.setVisible(false);
            lblEsperienza.setVisible(false);
            txtEsperienza.setVisible(false);
        });

        btnAutista.addActionListener(e -> {
            modalitaPasseggero[0] = false;
            lblModalita.setText("Modalità: Autista");
            lblCF.setVisible(false);
            txtCF.setVisible(false);
            lblDestinazione.setVisible(false);
            txtDestinazione.setVisible(false);
            chkBagaglio.setVisible(false);
            lblPatente.setVisible(true);
            txtPatente.setVisible(true);
            lblEsperienza.setVisible(true);
            txtEsperienza.setVisible(true);
        });

        btnAggiungi.addActionListener(e -> {
            String nome = txtNome.getText();
            String cognome = txtCognome.getText();

            if (modalitaPasseggero[0]) {
                try {
                    if (countPasseggeri >= MAX_PASSEGGERI)
                        throw new PostiEsauritiPasseggeroException();

                    String cf = txtCF.getText();
                    String destinazione = txtDestinazione.getText();
                    boolean bagaglio = chkBagaglio.isSelected();

                    Passeggero p = new Passeggero(nome, cognome, cf, destinazione, bagaglio);
                    utenti.add(p);
                    countPasseggeri++;

                    aggiornaElenco(lblElencoPasseggeri);
                    lblPostiPasseggeri.setText("Posti Passeggeri: " + (MAX_PASSEGGERI - countPasseggeri));

                } catch (PostiEsauritiPasseggeroException ex) {
                    JOptionPane.showMessageDialog(frame, "Posti passeggeri esauriti! | MAX 20", "Attenzione", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                try {
                    if (countAutisti >= MAX_AUTISTI)
                        throw new PostiEsauritiAutistaException();

                    String patente = txtPatente.getText();
                    int esperienza = Integer.parseInt(txtEsperienza.getText());

                    Autista a = new Autista(nome, cognome, patente, esperienza);
                    utenti.add(a);
                    countAutisti++;

                    aggiornaElenco(lblElencoAutisti);
                    lblPostiAutisti.setText("Posti Autisti: " + (MAX_AUTISTI - countAutisti));

                } catch (PostiEsauritiAutistaException ex) {
                    JOptionPane.showMessageDialog(frame, "Posti autisti esauriti! | MAX 2", "Attenzione", JOptionPane.WARNING_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Esperienza non valida", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnPasseggero.doClick();

        frame.setVisible(true);
    }

    private static void aggiornaElenco(JLabel label) {
        StringBuilder sb = new StringBuilder("<html>");

        if (label.getText().contains("Passeggeri")) {
            sb.append("<b>Passeggeri:</b><br>");
            for (UtenteAutobus u : utenti) {
                if (u instanceof Passeggero p) {
                    sb.append(p.nome).append(" ").append(p.cognome)
                            .append(" | CF: ").append(p.getCf())
                            .append(" | Destinazione: ").append(p.getDestinazione())
                            .append(" | Bagaglio: ").append(p.isLuggage() ? "Sì" : "No")
                            .append("<br>");
                }
            }
        } else {
            sb.append("<b>Autisti:</b><br>");
            for (UtenteAutobus u : utenti) {
                if (u instanceof Autista a) {
                    sb.append(a.nome).append(" ").append(a.cognome)
                            .append(" | Patente: ").append(a.getPatente())
                            .append(" | Esperienza: ").append(a.getAs())
                            .append(" anni<br>");
                }
            }
        }
        sb.append("</html>");
        label.setText(sb.toString());
    }
}
