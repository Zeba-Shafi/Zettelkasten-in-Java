public static class NoteConverter {

    //This note is responsible for converting Fleeting notes from one format to another

    //Fleeting Note to Permanent Note

    public static PermanentNote convertFleetingToPermanent(FleetingNote fleetingNote) {
        // Create a new PermanentNote using the content of the FleetingNote
        PermanentNote permanentNote = new PermanentNote(
            fleetingNote.getTitle(), // Title
            fleetingNote.getContent(), // Content
            fleetingNote.getTags(), // Tags (optional)
            fleetingNote.getDateCreated(), // Date created (optional)
            fleetingNote.getDateModified() // Date modified (optional)
        );

        return permanentNote;
    }

    //Fleeting Note to Literature Note
    //this is will be automatically created when convert button is pressed 
    //and the LitNote editor will be opened with the content of the Fleeting Note
    public static LitNote convertFleetingToLit(FleetingNote fleetingNote) {
        // Create a new LitNote using the content of the FleetingNote
        LitNote litNote = new LitNote(
            fleetingNote.getTitle(), // Title
            fleetingNote.getContent(), // Content
            fleetingNote.getTags(), // Tags (optional)
            fleetingNote.getDateCreated(), // Date created (optional)
            fleetingNote.getDateModified() // Date modified (optional)
        );

        return litNote;


    }


    
    
}