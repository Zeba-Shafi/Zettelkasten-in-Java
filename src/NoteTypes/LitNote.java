public class LitNote extends Note {

    private String author;
    private String sourceURL;
    private String citation;
    private String publicationDate;

    public LitNote(String title, String content, String tags, String dateCreated, String dateModified, String author, String publicationDate) {
        super(title, content, tags, dateCreated, dateModified);
        this.author = author;
        this.publicationDate = publicationDate;
    }

    @Override
    public void saveNote() {
        // Implementation for saving a literature note
    }

    @Override
    public void loadNote(String title) {
        // Implementation for loading a literature note
    }

    @Override
    public void deleteNote(String title) {
        // Implementation for deleting a literature note
    }

    // Getters and Setters for author and publicationDate
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCitation() {
        return citation;
    }
    public void setCitation(String citation) {
        this.citation = citation;
    }
    
    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public String getSourceURL() {
        return sourceURL;
    }
    
}