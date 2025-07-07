package swing_scuola;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Liste per contenere oggetti Studente e Professore
        ArrayList<Studente> studenti = new ArrayList<>();
        ArrayList<Professore> professori = new ArrayList<>();

        // Stato attuale della modalità
        final boolean[] modalitàStudente = {true};

        // Finestra principale
        JFrame frame = new JFrame("Gestione Scuola");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Etichetta modalità
        JLabel lblModalità = new JLabel("Modalità: Studente");
        lblModalità.setBounds(30, 20, 300, 30);
        frame.add(lblModalità);

        // Pulsanti per selezionare la modalità
        JButton btnStudente = new JButton("Studente");
        btnStudente.setBounds(30, 60, 120, 30);
        frame.add(btnStudente);

        JButton btnProfessore = new JButton("Professore");
        btnProfessore.setBounds(170, 60, 120, 30);
        frame.add(btnProfessore);

        // Campi comuni
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

        // Campo specifico: Matricola o Materia
        JLabel lblExtra = new JLabel("Matricola:");
        lblExtra.setBounds(30, 190, 100, 30);
        frame.add(lblExtra);

        JTextField txtExtra = new JTextField();
        txtExtra.setBounds(130, 190, 200, 30);
        frame.add(txtExtra);

        // Pulsante Aggiungi
        JButton btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.setBounds(130, 230, 100, 30);
        frame.add(btnAggiungi);

        // Area elenco
        JLabel lblElenco = new JLabel("<html><b>Elenco:</b><br></html>");
        lblElenco.setBounds(30, 280, 480, 200);
        frame.add(lblElenco);

        // Cambio modalità → Studente
        btnStudente.addActionListener(e -> {
            modalitàStudente[0] = true;
            lblModalità.setText("Modalità: Studente");
            lblExtra.setText("Matricola:");
            txtExtra.setText("");
        });

        // Cambio modalità → Professore
        btnProfessore.addActionListener(e -> {
            modalitàStudente[0] = false;
            lblModalità.setText("Modalità: Professore");
            lblExtra.setText("Materia:");
            txtExtra.setText("");
        });

        // Aggiunta
        btnAggiungi.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String cognome = txtCognome.getText().trim();
            String extra = txtExtra.getText().trim();

            if (nome.isEmpty() || cognome.isEmpty() || extra.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Compila tutti i campi!");
                return;
            }

            if (modalitàStudente[0]) {
                Studente s = new Studente(nome, cognome, extra);
                studenti.add(s);
            } else {
                Professore p = new Professore(nome, cognome, extra);
                professori.add(p);
            }

            // Costruisci l'elenco da visualizzare
            StringBuilder sb = new StringBuilder("<html><b>Studenti:</b><br>");
            for (Studente s : studenti) {
                sb.append("- ").append(s.nome).append(" ").append(s.cognome)
                  .append(" | Matricola: ").append(s.matricola).append("<br>");
            }

            sb.append("<br><b>Professori:</b><br>");
            for (Professore p : professori) {
                sb.append("- ").append(p.nome).append(" ").append(p.cognome)
                  .append(" | Materia: ").append(p.materia).append("<br>");
            }

            sb.append("</html>");
            lblElenco.setText(sb.toString());

            // Pulisci i campi
            txtNome.setText("");
            txtCognome.setText("");
            txtExtra.setText("");
        });

        // Mostra GUI
        frame.setVisible(true);
    }
}
