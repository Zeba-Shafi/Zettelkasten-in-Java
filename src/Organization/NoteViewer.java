package Organization;

import Notes.FleetingNote;

public class NoteViewer {

    private static final String FLEETING_FORMAT = """
Title: %s
Tags : %s
Date Created : %s
## Note
%s
## Footnotes
""";
    private static final String LIT_FORMAT = """
Title: %s; 
Citation: %s
Source URL: %s
Tags : %s
Date Created : %s
## Note
%s
## Footnotes
Incoming Links :
%s
Outgoing Links :
%s
""";

    private static final String PERM_FORMAT = """
Title: %s
Tags : %s
Date Created : %s
Date Modified : %s


## Note
%s

## Footnotes
Incoming Links :
%s

Outgoing Links :
%s
""";
    // This class is responsible for displaying the note to the user

    public void displayNote(Object note) {
        
        if (note instanceof FleetingNote) {
            FleetingNote fleetingNote = (FleetingNote) note;
            System.out.printf(FLEETING_FORMAT, fleetingNote.getTitle(), fleetingNote.getTags(), fleetingNote.getDateCreated(), fleetingNote.getContent());
        } else if (note instanceof LitNote == true) {
            LitNote litNote = (LitNote) note;
            System.out.printf(LIT_FORMAT, litNote.getTitle(), litNote.getCitation(), litNote.getSourceURL(), litNote.getTags(), litNote.getDateCreated(), litNote.getContent(), litNote.getIncomingLinks(), litNote.getOutgoingLinks());
        } else if (note instanceof PermanentNote == true) {
            PermanentNote permanentNote = (PermanentNote) note;
            System.out.printf(PERM_FORMAT, permanentNote.getTitle(), permanentNote.getTags(), permanentNote.getDateCreated(), permanentNote.getDateModified(), permanentNote.getContent(), permanentNote.getIncomingLinks(), permanentNote.getOutgoingLinks());
        }
    }
}
