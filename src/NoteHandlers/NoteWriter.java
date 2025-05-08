import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class NoteWriter {
    private static final String TEMPLATE_FOLDER = "noteTemplates"; // Folder containing templates
    private final Map<String, String> templates = new HashMap<>(); // Cache for loaded templates

    public NoteWriter() {
        try {
            loadTemplates();
        } catch (IOException e) {
            System.err.println("Error loading templates: " + e.getMessage());
        }
    }

    // Load all templates from the "noteTemplates" folder
    private void loadTemplates() throws IOException {
        File folder = new File(TEMPLATE_FOLDER);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IOException("Template folder not found: " + TEMPLATE_FOLDER);
        }

        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".template")) {
                String templateName = file.getName().replace(".template", ""); // Template name without extension
                String templateContent = Files.readString(file.toPath());
                templates.put(templateName, templateContent);
            }
        }
    }

    // Write a note to a file using the appropriate template
    public void writeNoteToFile(Note note, String filePath) {
        try {
            // Determine the template to use based on the note type
            String template = getTemplateForNoteType(note);

            // Format the note using the template
            String formattedNote = formatNoteWithTemplate(note, template);

            // Write the formatted note to the file
            Files.writeString(Path.of(filePath), formattedNote);
            System.out.println("Note written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing note to file: " + e.getMessage());
        }
    }

    // Get the appropriate template for the note type
    private String getTemplateForNoteType(Note note) {
        if (note instanceof LitNote) {
            return templates.getOrDefault("LitNote", getDefaultTemplate());
        } else if (note instanceof FleetingNote) {
            return templates.getOrDefault("FleetingNote", getDefaultTemplate());
        } else if (note instanceof PermanentNote) {
            return templates.getOrDefault("PermanentNote", getDefaultTemplate());
        }
        return getDefaultTemplate(); // Fallback to default template
    }

    // Format the note using the template
    private String formatNoteWithTemplate(Note note, String template) {
        return template
                .replace("{title}", note.getTitle())
                .replace("{tags}", note.getTags() != null ? note.getTags() : "")
                .replace("{dateCreated}", note.getDateCreated())
                .replace("{dateModified}", note.getDateModified())
                .replace("{content}", note.getContent());
    }

    
}
