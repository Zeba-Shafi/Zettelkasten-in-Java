import java.util.*;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;

public abstract class Note {

    protected String UUID; // Unique identifier for the note
    protected String title; // Title of the note
    protected String content; // Content of the note
    protected String tags; // Tags associated with the note
    protected String dateCreated; // Date when the note was created
    protected String dateModified; // Date when the note was last modified
    protected String filePath; // Path to the note file 

    private Set<String> outgoingLinks = new HashSet<>(); // Titles of linked notes
    private Set<String> incomingLinks = new HashSet<>(); // Titles of notes linking to this note

    public Note(String title, String content, String tags, String dateCreated, String dateModified) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    // Method to parse links from the note content
    public void parseLinks() {
        outgoingLinks.clear(); // Clear existing links

        // Parse the content for links
        Parser parser = Parser.builder().build();
        Node document = parser.parse(this.content);

        document.accept(new com.vladsch.flexmark.util.ast.VisitHandler<>(Link.class, link -> {
            String url = link.getUrl().toString();
            if (!url.startsWith("http") && url.endsWith(".md")) {
                outgoingLinks.add(url.replace(".md", "")); // Remove .md extension
            }
        }));
    }

    // Getters and setters for links
    public Set<String> getOutgoingLinks() {
        return outgoingLinks;
    }

    public Set<String> getIncomingLinks() {
        return incomingLinks;
    }

    public void addOutgoingLink(String link) {
        outgoingLinks.add(link);
    }

    public void addIncomingLink(String link) {
        incomingLinks.add(link);
    }

    // Abstract methods
    public abstract void deleteNote();

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    // Method to edit the content of the note and save it
    public void editContent(String newContent) {
        this.content = newContent;
        this.dateModified = getCurrentDate(); // Update the modification date
        saveNote(); // Save the changes to the file
    }

    // Method to edit the title of the note and save it
    public void editTitle(String newTitle) {
        this.title = newTitle;
        this.dateModified = getCurrentDate(); // Update the modification date
        saveNote(); // Save the changes to the file
    }

    // Utility method to get the current date
    private String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }

    public void deleteNote(String title) {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("File path is not set for this note. Cannot delete.");
            return;
        }
    
        File file = new File(filePath);
        if (file.exists()) {
            // Remove incoming links before deleting the file
            // Ensure you have access to the Indexer instance
            Index index = getIndexInstance(); // Replace with a method or logic to obtain the Indexer instance
            index.removeIncomingLinks(this);
    
            if (file.delete()) {
                System.out.println("Deleted file: " + filePath);
            } else {
                System.out.println("Failed to delete file: " + filePath);
            }
        } else {
            System.out.println("File does not exist: " + filePath);
        }
    }

    // Abstract methods for saving, loading, and deleting notes
    public abstract void saveNote();
    public abstract void loadNote(String title);
    
}
