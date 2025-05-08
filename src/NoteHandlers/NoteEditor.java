public class NoteEditor {
    public class NoteEditor {
        // Edit the content of a note and save it
        public void editContent(Note note, String newContent) {
            note.setContent(newContent);
            note.setDateModified(getCurrentDate()); // Update the modification date
            note.saveNote(); // Save the changes to the file
        }
    
        // Edit the title of a note and save it
        public void editTitle(Note note, String newTitle) {
            note.setTitle(newTitle);
            note.setDateModified(getCurrentDate()); // Update the modification date
            note.saveNote(); // Save the changes to the file
        }
    
        // Utility method to get the current date
        private String getCurrentDate() {
            return java.time.LocalDate.now().toString();
        }
    }
}
