package swing;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ListaPersoneGUI {
    public static void main(String[] args) {
        // Lista per salvare i nomi
        ArrayList<String> persone = new ArrayList<>();

        // Finestra
        JFrame finestra = new JFrame("Lista persone");
        finestra.setSize(400, 400);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setLayout(null);

        // Etichetta istruzione
        JLabel etichetta = new JLabel("Inserisci un nome:");
        etichetta.setBounds(30, 30, 200, 30);
        finestra.add(etichetta);

        // Campo di input
        JTextField campoTesto = new JTextField();
        campoTesto.setBounds(30, 70, 200, 30);
        finestra.add(campoTesto);

        // Pulsante
        JButton bottone = new JButton("Invia");
        bottone.setBounds(30, 110, 100, 30);
        finestra.add(bottone);

        // Etichetta per mostrare i nomi
        JLabel listaNomi = new JLabel("<html></html>");
        listaNomi.setBounds(30, 160, 300, 150);
        finestra.add(listaNomi);

        // Azione sul pulsante
        bottone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = campoTesto.getText().trim();

                if (!nome.isEmpty()) {
                    persone.add(nome); // Aggiungi alla lista

                    // Costruisci la stringa da mostrare
                    StringBuilder sb = new StringBuilder("<html>");
                    for (String p : persone) {
                        sb.append(p).append("<br>");
                    }
                    sb.append("</html>");

                    listaNomi.setText(sb.toString());
                    campoTesto.setText(""); // Pulisci il campo
                }
            }
        });

        // Mostra la finestra
        finestra.setVisible(true);
    }
}
