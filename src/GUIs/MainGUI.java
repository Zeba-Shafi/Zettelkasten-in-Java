import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private BrowserGUI browserPanel; // Sidebar for browsing notes
    private JTextArea contentArea;  // Central area for displaying content

    public MainGUI() {
        initializeGUI();
    }

    private void initializeGUI() {
        // Set up the JFrame
        setTitle("Zettelkasten - Main GUI");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the menu bar
        setJMenuBar(createMenuBar());

        // Add the BrowserGUI as a sidebar
        browserPanel = new BrowserGUI();
        add(browserPanel, BorderLayout.WEST);

        // Add a central content area
        contentArea = new JTextArea();
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        add(contentScrollPane, BorderLayout.CENTER);

        // Add a status bar at the bottom
        JLabel statusBar = new JLabel("Ready");
        add(statusBar, BorderLayout.SOUTH);

        // Make the JFrame visible
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newNoteItem = new JMenuItem("New Note");
        newNoteItem.addActionListener(e -> createNewNote());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(newNoteItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem settingsItem = new JMenuItem("Settings");
        settingsItem.addActionListener(e -> openSettings());
        editMenu.add(settingsItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private void createNewNote() {
        // Logic to create a new note
        JOptionPane.showMessageDialog(this, "New Note functionality not implemented yet.");
    }

    private void openSettings() {
        // Logic to open the settings window
        JOptionPane.showMessageDialog(this, "Settings functionality not implemented yet.");
    }

    private void showAboutDialog() {
        // Show an About dialog
        JOptionPane.showMessageDialog(this, "Zettelkasten Application\nVersion 1.0\nDeveloped by You.");
    }

    //public static void main(String[] args) {
        //SwingUtilities.invokeLater(MainGUI::new);
    //}
}
