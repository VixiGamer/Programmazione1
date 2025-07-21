// Avvio.java
package MyMusic;

import javax.swing.SwingUtilities;

public class Avvio {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}