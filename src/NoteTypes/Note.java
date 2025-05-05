public abstract class Note {
    

    protected String title;
    protected String content;
    protected String tags;
    protected String dateCreated;
    protected String dateModified;

    public Note(String title, String content, String tags, String dateCreated, String dateModified) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public abstract void saveNote();
    
    public abstract void loadNote(String title);
    
    public abstract void deleteNote(String title);
    
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
}
