import javax.swing.*;
import java.awt.*;

public class BrowserGUI extends JPanel {
    private JTextField searchBar;
    private JList<String> noteList;
    private JButton filterButton;

    public BrowserGUI() {
        initializeGUI();
    }

    private void initializeGUI() {
        setLayout(new BorderLayout());

        // Search bar at the top
        searchBar = new JTextField();
        searchBar.addActionListener(e -> search(searchBar.getText()));
        add(searchBar, BorderLayout.NORTH);

        // List of notes in the center
        noteList = new JList<>(new String[]{"Note 1", "Note 2", "Note 3"}); // Example notes
        add(new JScrollPane(noteList), BorderLayout.CENTER);

        // Filter button at the bottom
        filterButton = new JButton("Filter by Type");
        filterButton.addActionListener(e -> filterByType("Fleeting")); // Example filter
        add(filterButton, BorderLayout.SOUTH);
    }

    // Method to handle search functionality
    public void search(String query) {
        System.out.println("Searching for notes with query: " + query);
        // Add logic to filter the note list based on the query
    }

    // Method to filter notes by type
    public void filterByType(String type) {
        System.out.println("Filtering notes by type: " + type);
        // Add logic to filter the note list by type
    }

    // Method for right-click context menu options
    public void showContextMenu() {
        // Implementation for showing context menu options (Edit, Delete, Convert)
        System.out.println("Showing context menu options");
    }
}
