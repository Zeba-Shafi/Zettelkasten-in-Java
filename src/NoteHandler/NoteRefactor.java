package NoteHandler;

import java.util.ArrayList;

import Notes.*;
import Organization.Index;

public class NoteRefactor {

    Index index = Index.getInstance();


    
    // Convert a FleetingNote to a LitNote
    public void litFromFleet(FleetingNote fleetingSource, String contentSelection) {
        // Display editable text output for user to edit and pass back as input
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Current content: " + contentSelection);
        System.out.println("Edit the content and press Enter:");
        String editedContent = scanner.nextLine();

        System.out.println("Enter a title for the literature note:");
        String newTitle = scanner.nextLine();

        System.out.println("Enter a citation for the literature note:");
        String citation = scanner.nextLine();

        System.out.println("Enter a source URL for the literature note:");
        String sourceURL = scanner.nextLine();

        scanner.close();

        // Create a new LitNote
        LitNote litNote = NoteCreator.createLitNote(newTitle, editedContent, fleetingSource.getTags(), citation, sourceURL, new ArrayList<>());

        // Add the LitNote to the index
        index.addNoteToIndex(litNote);

        System.out.println("Literature note created and added to the index.");
    }

    // Convert a FleetingNote to a PermanentNote
    public void permFromFleet(FleetingNote fleetingSource, String contentSelection) {
        // Display editable text output for user to edit and pass back as input
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Current content: " + contentSelection);
        System.out.println("Edit the content and press Enter:");
        String editedContent = scanner.nextLine();

        System.out.println("Enter a title for the permanent note:");
        String newTitle = scanner.nextLine();

        scanner.close();

        // Create a new PermanentNote
        PermanentNote permNote = NoteCreator.createPermanentNote(newTitle, editedContent, fleetingSource.getTags(), new ArrayList<>());

        // Add the PermanentNote to the index
        index.addNoteToIndex(permNote);

        System.out.println("Permanent note created and added to the index.");
    }

    //create permanant note from literature note 

    public void permFromLit(LitNote litSource , String content_Selection) {
        //content selection is the highlighted section that is kept

        // Display editable text output for user to edit and pass back as input
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Current content: " + content_Selection);
        System.out.println("Edit the content and press Enter:");
        String editedContent = scanner.nextLine();

        System.out.println("Enter a new title for the permanent note:");
        String newTitle = scanner.nextLine();
        litSource.setTitle(newTitle);

        scanner.close();
        //add 
        
        // Pass the edited content back as input
        PermanentNote permNote = NoteCreator.createPermanentNote(newTitle, editedContent, litSource.getTags(),new ArrayList<String>());

        //add permNote as link to litSource
        litSource.addLink(permNote.getUniqueID());

        //add permNote to index
        index.addNoteToIndex(permNote);
        

    }

}
