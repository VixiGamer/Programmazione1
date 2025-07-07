package swing;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GestionePersoneGUI {
    public static void main(String[] args) {
        // ArrayList per studenti e professori
        ArrayList<String> studenti = new ArrayList<>();
        ArrayList<String> professori = new ArrayList<>();

        // Finestra
        JFrame frame = new JFrame("Gestione Studenti e Professori");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Stato attuale: studente o professore
        final boolean[] modalitàStudente = {true};

        // Etichetta titolo modalità
        JLabel titoloModalità = new JLabel("Modalità: Studente");
        titoloModalità.setBounds(30, 20, 200, 30);
        frame.add(titoloModalità);

        // Pulsanti per switch modalità
        JButton btnStudente = new JButton("Aggiungi Studente");
        btnStudente.setBounds(30, 60, 170, 30);
        frame.add(btnStudente);

        JButton btnProfessore = new JButton("Aggiungi Professore");
        btnProfessore.setBounds(220, 60, 170, 30);
        frame.add(btnProfessore);

        // Campo nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 110, 100, 30);
        frame.add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(100, 110, 200, 30);
        frame.add(txtNome);

        // Campo sezione
        JLabel lblSezione = new JLabel("Sezione:");
        lblSezione.setBounds(30, 150, 100, 30);
        frame.add(lblSezione);

        JTextField txtSezione = new JTextField();
        txtSezione.setBounds(100, 150, 200, 30);
        frame.add(txtSezione);

        // Pulsante Aggiungi
        JButton btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.setBounds(100, 200, 100, 30);
        frame.add(btnAggiungi);

        // Etichetta per lista
        JLabel lblLista = new JLabel("<html><b>Elenco:</b><br></html>");
        lblLista.setBounds(30, 250, 400, 200);
        frame.add(lblLista);

        // Logica per cambiare modalità
        btnStudente.addActionListener(e -> {
            modalitàStudente[0] = true;
            titoloModalità.setText("Modalità: Studente");
        });

        btnProfessore.addActionListener(e -> {
            modalitàStudente[0] = false;
            titoloModalità.setText("Modalità: Professore");
        });

        // Azione per aggiungere
        btnAggiungi.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String sezione = txtSezione.getText().trim();

            if (!nome.isEmpty() && !sezione.isEmpty()) {
                String persona = nome + " (" + sezione + ")";
                if (modalitàStudente[0]) {
                    studenti.add(persona);
                } else {
                    professori.add(persona);
                }

                // Ricostruisci lista
                StringBuilder sb = new StringBuilder("<html><b>Studenti:</b><br>");
                for (String s : studenti) sb.append("- ").append(s).append("<br>");

                sb.append("<br><b>Professori:</b><br>");
                for (String p : professori) sb.append("- ").append(p).append("<br>");

                sb.append("</html>");
                lblLista.setText(sb.toString());

                txtNome.setText("");
                txtSezione.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Inserisci sia nome che sezione!");
            }
        });

        // Mostra la finestra
        frame.setVisible(true);
    }
}
