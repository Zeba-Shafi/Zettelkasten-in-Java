import javax.swing.*;
import java.awt.*;

public class NoteGUI extends JFrame {
    private Note currentNote;

    public NoteGUI(Note note) {
        this.currentNote = note;
        this.currentNote.parseLinks(); // Parse links when the note is loaded
        initializeGUI();
    }

    private void initializeGUI() {
        // Set up the JFrame
        setTitle(currentNote.getTitle());
        setSize(800, 600); // Increased width to accommodate the sidebar
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display the note content
        JTextArea contentArea = new JTextArea(currentNote.getContent());
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        add(contentScrollPane, BorderLayout.CENTER);

        // Add the BrowserGUI as a sidebar
        BrowserGUI browserPanel = new BrowserGUI();
        add(browserPanel, BorderLayout.WEST); // Sidebar on the left

        // Make the JFrame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Example usage
        Note note = new LitNote("Note1", "This is a note with a link to [Note2](Note2.md).", "tags", "2025-05-01", "2025-05-06", "Author", "2025");
        SwingUtilities.invokeLater(() -> new NoteGUI(note));
    }
}