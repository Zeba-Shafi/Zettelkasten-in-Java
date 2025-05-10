import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.Map;

import NoteHandler.NoteCreator;
import Organization.Index;

import Notes.*;

public class Test {
    static Index index = Index.getInstance();

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

        if (!note.getLinks().isEmpty()) {
            String linkString = "";
            for (String id: note.getLinks()) {
                Note linkedNote = index.getNoteByID(id);
                linkString += "- " + linkedNote.getTitle() + "\n"; 
            }
            System.out.println("Links: \n + " +  linkString);
        } else {
            System.out.println("No Linked Notes");
        }

        
    }

    private static void viewNotePerm(PermanentNote note) {
        System.out.println("Title: " + note.getTitle());
        System.out.println("Unique ID: " + note.getUniqueID());
        System.out.println("File Path: " + note.getFilePath());
        System.out.println("Content: " + note.getContent());
        System.out.println("Tags: " + note.getTags());
        System.out.println("Date Created: " + note.getDateCreated());
        System.out.println("Date Modified: " + note.getDateModified());
        
        if (!note.getLinks().isEmpty()) {
            String linkString = "";
            for (String id: note.getLinks()) {
                Note linkedNote = index.getNoteByID(id);
                linkString += "- " + linkedNote.getTitle() + "\n"; 
            }
            System.out.println("Links: \n + " +  linkString);
        }
            
    }
    

    public static void main(String[] args) {
        // Call the singleton instance of the index
       

        // Define the folder path for the Zettelkasten
        String folderPath = "c:\\Users\\zs132\\OneDrive - nyu.edu\\Documents\\OOPFinal\\Zettelkasten-in-Java\\Zettelkasten\\";

        // Debug: Print folder path
        System.out.println("Folder path: " + folderPath);

        // Call FullIndexing to index all notes in the folder
        System.out.println("Starting FullIndexing...");
        index.FullIndexing(folderPath);
        System.out.println("FullIndexing completed.");

        // Debug: Check the size of notesByID
        System.out.println("Number of notes indexed: " + index.getNotesByID().size());

        // Iterate through the notesByID map and display each note
        for (Map.Entry<String, Note> entry : index.getNotesByID().entrySet()) {
            Note note = entry.getValue();

            // Debug: Print the note type and ID
            System.out.println("Processing note with ID: " + note.getUniqueID());
            System.out.println("Note type: " + note.getClass().getSimpleName());

            // Use the appropriate view method based on the note type
            if (note instanceof FleetingNote) {
                System.out.println("Fleeting Note:");
                viewNoteFleet(note);
            } else if (note instanceof PermanentNote) {
                System.out.println("Permanent Note:");
                viewNotePerm((PermanentNote) note);
            } else if (note instanceof LitNote) {
                System.out.println("Literature Note:");
                viewNoteLit((LitNote) note);
            }

            System.out.println("-----------------------------------");
        }
    }
}
