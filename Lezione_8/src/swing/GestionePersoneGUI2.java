package swing;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GestionePersoneGUI2 {
    public static void main(String[] args) {
        // Liste per studenti e professori
        ArrayList<String> studenti = new ArrayList<>();
        ArrayList<String> professori = new ArrayList<>();

        // Stato modalità
        final boolean[] modalitàStudente = {true};

        // Finestra
        JFrame frame = new JFrame("Gestione Studenti e Professori");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Etichetta modalità
        JLabel lblModalità = new JLabel("Modalità: Studente");
        lblModalità.setBounds(30, 20, 300, 30);
        frame.add(lblModalità);

        // Pulsanti modalità
        JButton btnStudente = new JButton("Studente");
        btnStudente.setBounds(30, 60, 120, 30);
        frame.add(btnStudente);

        JButton btnProfessore = new JButton("Professore");
        btnProfessore.setBounds(170, 60, 120, 30);
        frame.add(btnProfessore);

        // Etichette e campi comuni
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

        // Etichetta elenco finale
        JLabel lblLista = new JLabel("<html><b>Elenco:</b><br></html>");
        lblLista.setBounds(30, 280, 480, 200);
        frame.add(lblLista);

        // Cambia modalità → Studente
        btnStudente.addActionListener(e -> {
            modalitàStudente[0] = true;
            lblModalità.setText("Modalità: Studente");
            lblExtra.setText("Matricola:");
            txtExtra.setText("");
        });

        // Cambia modalità → Professore
        btnProfessore.addActionListener(e -> {
            modalitàStudente[0] = false;
            lblModalità.setText("Modalità: Professore");
            lblExtra.setText("Materia:");
            txtExtra.setText("");
        });

        // Azione Aggiungi
        btnAggiungi.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String cognome = txtCognome.getText().trim();
            String extra = txtExtra.getText().trim();

            if (nome.isEmpty() || cognome.isEmpty() || extra.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Compila tutti i campi!");
                return;
            }

            if (modalitàStudente[0]) {
                studenti.add(nome + " " + cognome + " - Matricola: " + extra);
            } else {
                professori.add(nome + " " + cognome + " - Materia: " + extra);
            }

            // Aggiorna la lista
            StringBuilder sb = new StringBuilder("<html><b>Studenti:</b><br>");
            for (String s : studenti) sb.append("- ").append(s).append("<br>");

            sb.append("<br><b>Professori:</b><br>");
            for (String p : professori) sb.append("- ").append(p).append("<br>");

            sb.append("</html>");
            lblLista.setText(sb.toString());

            // Pulisci i campi
            txtNome.setText("");
            txtCognome.setText("");
            txtExtra.setText("");
        });

        // Mostra la finestra
        frame.setVisible(true);
    }
}

