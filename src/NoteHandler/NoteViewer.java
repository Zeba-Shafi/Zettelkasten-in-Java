package NoteHandler;

import Notes.FleetingNote;
import Notes.LitNote;
import Notes.Note;
import Notes.PermanentNote;
import Organization.Index;

import java.util.Scanner;

public class NoteViewer {

    private static Index index = Index.getInstance();
    private static NoteEditor noteEditor = new NoteEditor();
    private static NoteRefactor noteRefactor = new NoteRefactor();

    // This class is responsible for displaying the note to the user

    public void displayNote(Object note) {
        if (note instanceof FleetingNote) {
            FleetingNote fleetingNote = (FleetingNote) note;
            viewNoteFleet(fleetingNote);
        } else if (note instanceof LitNote) {
            LitNote litNote = (LitNote) note;
            viewNoteLit(litNote);
        } else if (note instanceof PermanentNote) {
            PermanentNote permanentNote = (PermanentNote) note;
            viewNotePerm(permanentNote);
        }
    }

    private static void viewNoteFleet(Note note) {
        System.out.println("Title: " + note.getTitle());
        System.out.println("Tags: " + note.getTags());
        System.out.println("Date Created: " + note.getDateCreated());
        System.out.println("------------------------------");
        System.out.println("Content: " + note.getContent());
        System.out.println("------------------------------");

        // Prompt user for action
        promptUserAction(note);
    }

    private static void viewNoteLit(LitNote note) {
        System.out.println("Title: " + note.getTitle());
        System.out.println("Tags: [" + String.join(",", note.getTags()) + "]");
        System.out.println("Date Created: " + note.getDateCreated());
        System.out.println("Citation: " + note.getCitation());
        System.out.println("Source URL: " + note.getSourceURL());
        System.out.println("------------------------------");
        System.out.println("Content: " + note.getContent());
        System.out.println("------------------------------");

        System.out.println("->->");
        if (!note.getLinks().isEmpty()) {
            String linkString = "";
            for (String id : note.getLinks()) {
                Note linkedNote = index.getNoteByID(id);
                linkString += "- " + linkedNote.getTitle() + "\n";
            }
            System.out.println("Links: \n + " + linkString);
        } else {
            System.out.println("No Linked Notes");
        }

        System.out.println("<-<-");
        if (!index.getBacklinks(note.getUniqueID()).isEmpty()) {
            String linkString = "";
            for (Note backlinkedNote : index.getBacklinks(note.getUniqueID())) {
                linkString += "- " + backlinkedNote.getTitle() + "\n";
            }
            System.out.println("Links: \n + " + linkString);
        } else {
            System.out.println("No incoming linked notes");
        }

        // Prompt user for action
        promptUserAction(note);
    }

    private static void viewNotePerm(PermanentNote note) {
        System.out.println("Title: " + note.getTitle());
        System.out.println("Unique ID: " + note.getUniqueID());
        System.out.println("File Path: " + note.getFilePath());
        System.out.println("Tags: " + note.getTags());
        System.out.println("Date Created: " + note.getDateCreated());
        System.out.println("Date Modified: " + note.getDateModified());
        System.out.println("------------------------------");
        System.out.println("Content: " + note.getContent());
        System.out.println("------------------------------");

        System.out.println("->->");
        if (!note.getLinks().isEmpty()) {
            String linkString = "";
            for (String id : note.getLinks()) {
                Note linkedNote = index.getNoteByID(id);
                linkString += "- " + linkedNote.getTitle() + "\n";
            }
            System.out.println("Links: \n + " + linkString);
        } else {
            System.out.println("No outgoing linked notes");
        }

        System.out.println("<-<-");
        if (!index.getBacklinks(note.getUniqueID()).isEmpty()) {
            String linkString = "";
            for (Note backlinkedNote : index.getBacklinks(note.getUniqueID())) {
                linkString += "- " + backlinkedNote.getTitle() + "\n";
            }
            System.out.println("Links: \n + " + linkString);
        } else {
            System.out.println("No incoming linked notes");
        }

        // Prompt user for action
        promptUserAction(note);
    }

    private static void promptUserAction(Note note) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("What would you like to do?");
            System.out.println("1. Edit the note");
            System.out.println("2. Delete the note");
            System.out.println("3. Refactor (convert) the note");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    noteEditor.editContent(note); // Call the appropriate edit method
                    break;
                case 2:
                    index.getNotesByID().remove(note.getUniqueID()); // Remove the note from the index
                    System.out.println("Note deleted successfully.");
                    break;
                case 3:
                    noteRefactor.refactorNote(note); // Call the refactor method
                    break;
                default:
                    System.out.println("Invalid choice. No action taken.");
            }
        } finally {
            scanner.close();
        }
    }
}
