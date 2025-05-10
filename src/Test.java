import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.Map;
import java.util.Set;

import NoteHandler.NoteCreator;
import Organization.Index;

import Notes.*;

public class Test {
    static Index index = Index.getInstance();

    
            
    }
    

    public static void main(String[] args) {
        // Create an instance of Index
        Index index = Index.getInstance();

        // Define the folder path for the Zettelkasten
        String folderPath = "c:\\Users\\zs132\\OneDrive - nyu.edu\\Documents\\OOPFinal\\Zettelkasten-in-Java\\Zettelkasten\\";

        // Call FullIndexing to index all notes in the folder
        System.out.println("Starting FullIndexing...");
        index.FullIndexing(folderPath);
        System.out.println("FullIndexing completed.");

        // Iterate through all notes in notesByID and test backlinks
        System.out.println("Testing backlinks for all notes...");
        for (Map.Entry<String, Note> entry : index.getNotesByID().entrySet()) {
            String noteID = entry.getKey();
            Note note = entry.getValue();

            // Print the note being tested
            System.out.println("Testing backlinks for note ID: " + noteID + " (" + note.getTitle() + ")");

            // Get backlinks for the current note
            Set<Note> backlinks = index.getBacklinks(noteID);
            if (backlinks.isEmpty()) {
                System.out.println("No backlinks found for note ID: " + noteID);
            } else {
                System.out.println("Backlinks for note ID: " + noteID);
                for (Note backlink : backlinks) {
                    System.out.println("- " + backlink.getUniqueID() + ": " + backlink.getTitle());
                }
            }

            System.out.println("-----------------------------------");
        }
    }
}
