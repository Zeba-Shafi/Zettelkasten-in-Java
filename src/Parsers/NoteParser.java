import Settings;
import java.io.File;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class NoteParser {
    protected String filePath; // Path to the note file
    private static Map<String, Note> notes = new HashMap<>(); // Stores all notes by title

    public NoteParser(String filePath) {
        this.filePath = filePath;
    }

    // Abstract methods for parsing notes
    public abstract void parseNote() throws Exception;

    // Method to get the file path
    public String getFilePath() {
        return filePath;
    }

    // Method to set the file path
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // Method to check if the file exists
    public boolean fileExists() {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    // Parse a note and update outgoing and incoming links
    public static Note parseNote(File markdownFile) throws IOException {
        String content = Files.readString(markdownFile.toPath());
        String title = markdownFile.getName().replace(".md", "");

        // Create or retrieve the note
        Note note = notes.computeIfAbsent(title, t -> new Note(t, content));

        // Parse outgoing links
        Parser parser = Parser.builder().build();
        Node document = parser.parse(content);
        document.getChildren().forEach(node -> {
            if (node instanceof Link) {
                String linkDestination = ((Link) node).getUrl().toString().replace(".md", "");
                note.addOutgoingLink(linkDestination);

                // Update the incoming links for the linked note
                Note linkedNote = notes.computeIfAbsent(linkDestination, t -> new Note(t, ""));
                linkedNote.addIncomingLink(title);
            }
        });

        return note;
    }

    // Get all notes
    public static Map<String, Note> getNotes() {
        return notes;
    }
}
