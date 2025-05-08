import javax.swing.*;

public class GlobalBrowserGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Global Browser");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 600);

            // Add the BrowserGUI panel
            BrowserGUI browserPanel = new BrowserGUI();
            frame.add(browserPanel);

            frame.setVisible(true);
        });
    }
}