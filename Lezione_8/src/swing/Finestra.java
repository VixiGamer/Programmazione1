package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Finestra {
    public static void main(String[] args) {
    	
    	 // Creazione della finestra
        JFrame finestra = new JFrame("GUI con Pulsante");
        finestra.setSize(400, 300);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setLayout(null); // Usiamo posizionamento assoluto

        // Etichetta
        JLabel nome = new JLabel("Nome");
        nome.setBounds(10, 20, 200, 30);
        finestra.add(nome);
        
        JTextField campoNome = new JTextField();
        campoNome.setBounds(70, 20, 180, 30);
        finestra.add(campoNome);
        
        
        JLabel cognome = new JLabel("Cognome");
        cognome.setBounds(10, 60, 200, 30);
        finestra.add(cognome);
        
        JTextField campoCognome = new JTextField();
        campoCognome.setBounds(70, 60, 180, 30);
        finestra.add(campoCognome);

        // Pulsante
        JButton bottone = new JButton("Invia");
        bottone.setBounds(10, 200, 80, 30);
        finestra.add(bottone);

        // Mostra la finestra
        finestra.setVisible(true);
    }
}