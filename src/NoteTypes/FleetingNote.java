public class FleetingNote extends Note {

    public FleetingNote(String title, String content, String tags, String dateCreated, String dateModified) {
        super(title, content, tags, dateCreated, dateModified);
    }

    @Override
    public void saveNote() {
        // Implementation for saving a fleeting note
        System.out.println("Saving fleeting note: " + title);
    }

    @Override
    public void loadNote(String title) {
        // Implementation for loading a fleeting note
        System.out.println("Loading fleeting note: " + title);
    }

    @Override
    public void deleteNote(String title) {
        // Implementation for deleting a fleeting note
        System.out.println("Deleting fleeting note: " + title);
    }
}