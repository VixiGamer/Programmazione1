package gestore_password;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class PasswordManager {

    private static final String FILE_NAME = "passwords.dat";
    private static ArrayList<Credenziali> lista = new ArrayList<>();
    private static String masterPassword = "";
    private static int indexSelezionato = -1;

    public static void main(String[] args) {
        JFrame loginFrame = new JFrame("Accesso Gestore Password");
        loginFrame.setSize(400, 200);
        loginFrame.setLayout(null);

        JLabel lbl = new JLabel("Inserisci la master password:");
        lbl.setBounds(30, 30, 300, 30);
        loginFrame.add(lbl);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(30, 70, 200, 30);
        loginFrame.add(txtPass);

        JButton btnLogin = new JButton("Accedi");
        btnLogin.setBounds(250, 70, 100, 30);
        loginFrame.add(btnLogin);

        btnLogin.addActionListener(e -> {
            masterPassword = new String(txtPass.getPassword());
            if (caricaDati()) {
                loginFrame.dispose();
                mostraInterfaccia();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Password errata o file danneggiato.");
            }
        });

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }

    private static void mostraInterfaccia() {
        JFrame frame = new JFrame("Gestore Password Sicuro");
        frame.setSize(650, 600);
        frame.setLayout(null);

        JTextField txtSito = new JTextField();
        txtSito.setBounds(30, 30, 200, 40);
        txtSito.setBorder(BorderFactory.createTitledBorder("Sito"));
        frame.add(txtSito);

        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(30, 70, 200, 40);
        txtUsername.setBorder(BorderFactory.createTitledBorder("Username"));
        frame.add(txtUsername);

        JTextField txtPassword = new JTextField();
        txtPassword.setBounds(30, 110, 200, 40);
        txtPassword.setBorder(BorderFactory.createTitledBorder("Password"));
        frame.add(txtPassword);

        JButton btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.setBounds(250, 30, 120, 30);
        frame.add(btnAggiungi);

        JButton btnModifica = new JButton("Modifica");
        btnModifica.setBounds(250, 70, 120, 30);
        frame.add(btnModifica);

        JButton btnRimuovi = new JButton("Rimuovi");
        btnRimuovi.setBounds(250, 110, 120, 30);
        frame.add(btnRimuovi);

        JTextField txtCerca = new JTextField();
        txtCerca.setBounds(30, 160, 200, 40);
        txtCerca.setBorder(BorderFactory.createTitledBorder("Cerca per sito"));
        frame.add(txtCerca);

        JButton btnCerca = new JButton("Cerca");
        btnCerca.setBounds(250, 160, 120, 30);
        frame.add(btnCerca);

        JTextArea area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(30, 210, 580, 300);
        area.setEditable(false);
        frame.add(scroll);

        // Eventi
        btnAggiungi.addActionListener(e -> {
            String sito = txtSito.getText().trim();
            String user = txtUsername.getText().trim();
            String pass = txtPassword.getText().trim();

            if (sito.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Completa tutti i campi.");
                return;
            }

            Credenziali c = new Credenziali(sito, user, pass);
            lista.add(c);
            salvaDati();
            aggiornaArea(area, "");
            txtSito.setText("");
            txtUsername.setText("");
            txtPassword.setText("");
        });

        btnModifica.addActionListener(e -> {
            if (indexSelezionato < 0 || indexSelezionato >= lista.size()) {
                JOptionPane.showMessageDialog(frame, "Seleziona una voce da modificare.");
                return;
            }

            String sito = txtSito.getText().trim();
            String user = txtUsername.getText().trim();
            String pass = txtPassword.getText().trim();

            if (sito.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Completa tutti i campi.");
                return;
            }

            lista.set(indexSelezionato, new Credenziali(sito, user, pass));
            salvaDati();
            aggiornaArea(area, "");
            txtSito.setText("");
            txtUsername.setText("");
            txtPassword.setText("");
            indexSelezionato = -1;
        });

        btnRimuovi.addActionListener(e -> {
            if (indexSelezionato < 0 || indexSelezionato >= lista.size()) {
                JOptionPane.showMessageDialog(frame, "Seleziona una voce da rimuovere.");
                return;
            }

            lista.remove(indexSelezionato);
            salvaDati();
            aggiornaArea(area, "");
            txtSito.setText("");
            txtUsername.setText("");
            txtPassword.setText("");
            indexSelezionato = -1;
        });

        btnCerca.addActionListener(e -> {
            String filtro = txtCerca.getText().trim();
            aggiornaArea(area, filtro);
        });

        area.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int line = area.getCaretPosition();
                int row = area.getDocument().getDefaultRootElement().getElementIndex(line);
                if (row < lista.size()) {
                    Credenziali c = lista.get(row);
                    txtSito.setText(c.toString().split("\\|")[0].split(":")[1].trim());
                    txtUsername.setText(c.toString().split("\\|")[1].split(":")[1].trim());
                    txtPassword.setText(c.toString().split("\\|")[2].split(":")[1].trim());
                    indexSelezionato = row;
                }
            }
        });

        aggiornaArea(area, "");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void aggiornaArea(JTextArea area, String filtro) {
        StringBuilder sb = new StringBuilder();
        for (Credenziali c : lista) {
            if (filtro.isEmpty() || c.toString().toLowerCase().contains(filtro.toLowerCase())) {
                sb.append(c.toString()).append("\n");
            }
        }
        area.setText(sb.toString());
    }

    private static boolean caricaDati() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return true;

            FileInputStream fis = new FileInputStream(file);
            byte[] encrypted = fis.readAllBytes();
            fis.close();

            byte[] decrypted = CryptoUtils.decrypt(encrypted, masterPassword);

            ByteArrayInputStream bais = new ByteArrayInputStream(decrypted);
            ObjectInputStream ois = new ObjectInputStream(bais);
            lista = (ArrayList<Credenziali>) ois.readObject();
            ois.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void salvaDati() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(lista);
            oos.close();

            byte[] encrypted = CryptoUtils.encrypt(baos.toByteArray(), masterPassword);

            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            fos.write(encrypted);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
