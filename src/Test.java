import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import NoteHandler.NoteCreator;

import Notes.*;


public class Test {
    
    private static void viewNoteFleet(Note note) {
        System.out.println("Title: " + note.getTitle());
        System.out.println("Content: " + note.getContent());
        System.out.println("Tags: " + note.getTags());
        System.out.println("Date Created: " + note.getDateCreated());
    }

    private static void viewNoteLit(LitNote note) {
        System.out.println("Title: " + note.getTitle());
        System.out.println("Content: " + note.getContent());
        System.out.println("Tags: [" + String.join(",", note.getTags()) + "]");
        System.out.println("Date Created: " + note.getDateCreated());
        System.out.println("Citation: " + note.getCitation());
        System.out.println("Source URL: " + note.getSourceURL());
    }

    private static void viewNotePerm(PermanentNote note) {
        System.out.println("Title: " + note.getTitle());
        System.out.println("Unique ID: " + note.getUniqueID());
        System.out.println("File Path: " + note.getFilePath());
        System.out.println("Content: " + note.getContent());
        System.out.println("Tags: " +  note.getTags());
        System.out.println("Date Created: " + note.getDateCreated());
        System.out.println("Date Modified: " + note.getDateModified());
    }
    

    public static void main(String[] args) {
        // Create a list to hold all notes
        List<Note> notes = new ArrayList<>();

        // Add 5 fleeting notes
        for (int i = 1; i <= 5; i++) {
            FleetingNote fleetingNote = NoteCreator.createFleetingNote(
                "C:\\Users\\zs132\\OneDrive - nyu.edu\\Documents\\OOPFinal\\Zettelkasten-in-Java\\Zettelkasten\\FleetingNote_" + UUID.randomUUID() + ".md"
            );
            notes.add(fleetingNote);
        }

        // Add 5 permanent notes
        for (int i = 1; i <= 5; i++) {
            PermanentNote permanentNote = NoteCreator.createPermanentNote(
                "C:\\Users\\zs132\\OneDrive - nyu.edu\\Documents\\OOPFinal\\Zettelkasten-in-Java\\Zettelkasten\\PermanentNote_" + UUID.randomUUID() + ".md"
            );
            notes.add(permanentNote);
        }

        // Add 5 literature notes
        for (int i = 1; i <= 5; i++) {
            LitNote litNote = NoteCreator.createLitNote(
                "C:\\Users\\zs132\\OneDrive - nyu.edu\\Documents\\OOPFinal\\Zettelkasten-in-Java\\Zettelkasten\\LiteratureNote_" + UUID.randomUUID() + ".md"
            );
            notes.add(litNote);
        }

        // Print all notes to verify
        for (Note note : notes) {
            if (note instanceof FleetingNote) {
                System.out.println("Fleeting Note: " + note.getTitle());
            } else if (note instanceof PermanentNote) {
                System.out.println("Permanent Note: " + note.getTitle());
            } else if (note instanceof LitNote) {
                System.out.println("Literature Note: " + note.getTitle());
            }
        }
    }
}
