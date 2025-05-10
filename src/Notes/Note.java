package Notes;


import java.io.File;
import java.util.*;

import MDHandler.MDEditor;
import MDHandler.MDParser;

public abstract class Note {

    protected String uniqueID; // Unique identifier for the note
    protected String title; // Title of the note
    protected String content; // Content of the note
    protected ArrayList<String> tags = new ArrayList<String>(); // List of Tags associated with the note
    protected String dateCreated; // Date when the note was created
    protected String dateModified; // Date when the note was last modified
    protected String filePath; // Path to the note file 

    //private Set<String> outgoingLinks = new HashSet<>(); // Titles of linked notes
    //private Set<String> incomingLinks = new HashSet<>(); // Titles of notes linking to this note

    public Note(String title, String content, ArrayList<String> tags, String dateCreated) {
        this.uniqueID = java.util.UUID.randomUUID().toString(); // Generate a unique identifier
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.dateCreated = dateCreated; // Get the current date and time
        this.dateModified = dateCreated; // Initially set to the creation date
       
    } 

    public Note(String filepath){
        this.filePath = filepath; // Set the file path for the note
        Map<String,String> metadata = MDParser.extractYamlMetadata(filepath);
        this.content = MDParser.extractContent(filepath);
        this.tags = new ArrayList<String>(Arrays.asList(metadata.get("tags").split(","))); // Split tags by comma and convert to ArrayList
        this.uniqueID = metadata.get("NoteID");
        this.title = metadata.get("title");
        this.dateCreated = metadata.get("dateCreated");
        this.dateModified = metadata.get("dateModified");

    }

    
    /* // Method to parse links from the note content
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
 */
    // Getters and setters for links
   /*  public Set<String> getOutgoingLinks() {
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
    } */

    protected String getCurrentDate() {
        java.util.Date date = new java.util.Date();
        return date.toString();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    
    // Getters and Setters

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
        MDEditor.replaceTitle(this.filePath,title);
        setDateModified(getCurrentDate());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        MDEditor.replaceContent(this.filePath,content);
        setDateModified(getCurrentDate());
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
        MDEditor.replaceTag(this.filePath,tags);
        setDateModified(getCurrentDate());
    }

    public void addTag(String tag) {
        if (tag != null) {
            tag = tag.replaceAll("[\\[\\]]", "").trim(); // Remove square brackets and trim whitespace
            if (!tags.contains(tag)) {
                tags.add(tag);
                tags = cleanAndFlattenTags(tags); // Clean and flatten the tags
                MDEditor.replaceTag(this.filePath, tags);
                setDateModified(getCurrentDate());
            }
        }
    }

    public void removeTag(String tag) {
        tags.remove(tag);
        MDEditor.replaceTag(this.filePath,tags);
        setDateModified(getCurrentDate());
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
        MDEditor.replaceDateModified(this.filePath,dateModified);
    }


    //delete Note

    public void delete() {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("File path is not set for this note. Cannot delete.");
            return;
        }
    
        File file = new File(filePath);
        if (file.exists()) {
            // Remove incoming links before deleting the file
            // Ensure you have access to the Indexer instance
            //Index index = Index.getInstance(); // Replace with a method or logic to obtain the Indexer instance
            //index.removeIncomingLinks(this);
    
            if (file.delete()) {
                System.out.println("Deleted file: " + filePath);
            } else {
                System.out.println("Failed to delete file: " + filePath);
            }
        } else {
            System.out.println("File does not exist: " + filePath);
        }
    }

    private ArrayList<String> cleanAndFlattenTags(List<String> tags) {
        ArrayList<String> flatTags = new ArrayList<>();
        for (String tag : tags) {
            if (tag != null) {
                tag = tag.replaceAll("[\\[\\]]", "").trim(); // Remove square brackets and trim whitespace
                if (!flatTags.contains(tag)) { // Avoid duplicates
                    flatTags.add(tag);
                }
            }
        }
        return flatTags;
    }

    // Abstract methods for saving, loading, and deleting notes
    // public abstract void saveNote();
    // public abstract void loadNote(String title);
    
}
