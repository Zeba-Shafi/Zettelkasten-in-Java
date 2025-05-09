import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
       

        
       
        FleetingNote fleetingNote = NoteCreator.createFleetingNote("C:\\Users\\zs132\\OneDrive - nyu.edu\\Documents\\OOP Final\\Zettelkasten-in-Java\\Zettelkasten\\FleetingNote_37dd4298-c6a4-4965-a5b2-7dc26e0da70d.md");
       
        PermanentNote permanentNote = NoteCreator.createPermanentNote("C:\\Users\\zs132\\OneDrive - nyu.edu\\Documents\\OOP Final\\Zettelkasten-in-Java\\Zettelkasten\\PermanentNote_aa305a49-81a0-4ab1-9aaa-d67a270d88e4.md");

        LitNote litNote = NoteCreator.createLitNote("C:\\Users\\zs132\\OneDrive - nyu.edu\\Documents\\OOP Final\\Zettelkasten-in-Java\\Zettelkasten\\LiteratureNote_8682bf69-cf58-4dbb-b46d-4c4afe17d7e3.md");
        
        
    
        viewNotePerm(permanentNote);
        System.out.println();

        litNote.setTitle("Updated Title");
        litNote.setContent("Updated Content");
        
        //permanentNote.addTag("tag5");
        
        //generate a new UUID

        UUID uuid = UUID.randomUUID();

        // Convert UUID to string
        String uuidString = uuid.toString();
        UUID uuid1 = UUID.randomUUID();


        // Convert UUID to string
        String uuidString1 = uuid1.toString();
    
        permanentNote.addTag("tagfoo");
        
        viewNotePerm(permanentNote);


        // Create a new file in the markdown directory
        /* File file = new File(markdownDir + "test.md");
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Write to the file
        try {
            Files.write(Paths.get(file.getPath()), "Hello, World!".getBytes());
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } */

    }
}
