import javax.swing.SwingUtilities;
// Rimuovi: import com.formdev.flatlaf.FlatLightLaf;

public class Avvio {
    public static void main(String[] args) {
        // Rimuovi: FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}