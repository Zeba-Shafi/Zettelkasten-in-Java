public abstract class NoteCreator {
    public static Note createNote(String type, String title, String content, String tags, String dateCreated, String dateModified) {
        switch (type.toLowerCase()) {
            case "litnote":
                return new LitNote(title, content, tags, dateCreated, dateModified);
            case "fleetingnote":
                return new FleetingNote(title, content, tags, dateCreated, dateModified);
            case "permanentnote":
                return new PermanentNote(title, content, tags, dateCreated, dateModified,author);
            default:
                throw new IllegalArgumentException("Unknown note type: " + type);
        }
    }

    public static Note createNote(String filepath) {
        //use MDparser to parse the file and create a note object
        // This is a placeholder implementation. You would need to implement the actual parsing logic.
    }
}
