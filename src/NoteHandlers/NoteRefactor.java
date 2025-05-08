public class NoteRefactor {
    
    // This class is responsible for coverting a section of literature notes
    // to a permenant note
    // This will be placed into an editor of a permenant note for editing 
    // It will use the settings from the Settings class to determine how to convert notes.
    // Method to create a PermanentNote from a highlighted section of a LitNote
    public PermanentNote createPermanentNoteFromHighlight(LitNote litNote, int startIndex, int endIndex) {
        // Validate indices
        if (startIndex < 0 || endIndex > litNote.getContent().length() || startIndex >= endIndex) {
            throw new IllegalArgumentException("Invalid start or end index for highlighting.");
        }

        // Extract the highlighted section
        String highlightedContent = litNote.getContent().substring(startIndex, endIndex);

        // Create a new PermanentNote
        PermanentNote permanentNote = new PermanentNote(
            "Permanent Note from: " + litNote.getTitle(), // Title
            highlightedContent,                           // Content
            litNote.getTags(),                            // Tags (optional)
            litNote.getDateCreated(),                     // Date created (optional)
            litNote.getDateModified()                     // Date modified (optional)
        );

        // Optionally, link the PermanentNote back to the LitNote
        permanentNote.addSource("Derived from Literature Note: " + litNote.getTitle());

        return permanentNote;
    }
}
