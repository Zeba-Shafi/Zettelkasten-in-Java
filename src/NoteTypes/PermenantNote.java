public class PermenantNote extends Note {

    public PermenantNote(String title, String content, String tags, String dateCreated, String dateModified) {
        super(title, content, tags, dateCreated, dateModified);
    }

    @Override
    public void saveNote() {
        // Implementation for saving a permanent note
        System.out.println("Saving permanent note: " + title);
    }

    @Override
    public void loadNote(String title) {
        // Implementation for loading a permanent note
        System.out.println("Loading permanent note: " + title);
    }

    
    @Override
    public void deleteNote(String title) {
        // Implementation for deleting a permanent note
        System.out.println("Deleting permanent note: " + title);
    }
}