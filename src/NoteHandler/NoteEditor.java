package NoteHandler;

import Notes.Note;
import Notes.PermanentNote;
import Notes.LitNote;

import java.util.ArrayList;
import java.util.Scanner;

public class NoteEditor {


    // Save a message when a note is saved
    private void saveNoteMessage(Note note) {
        System.out.println("Saving current note: " + note.getTitle());
    }


    // Edit the title of a note
    public void editTitle(Note note) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Current title: " + note.getTitle());
            System.out.println("Enter new title:");
            String newTitle = scanner.nextLine();
            note.setTitle(newTitle);
            saveNoteMessage(note);
        } finally {
            scanner.close();
        }
    }

    // Edit the content of a note
    public void editContent(Note note) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Current content: " + note.getContent());
            System.out.println("Enter new content:");
            String newContent = scanner.nextLine();
            note.setContent(newContent);
            saveNoteMessage(note);
        } finally {
            scanner.close();
        }
    }

    // Edit the tags of a note
    public void editTags(Note note) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Current tags: " + note.getTags());
            System.out.println("Enter new tags (comma-separated):");
            String newTagsInput = scanner.nextLine();

            ArrayList<String> newTags = new ArrayList<>();
            for (String tag : newTagsInput.split(",")) {
                newTags.add(tag.trim());
            }
            note.setTags(newTags);
            saveNoteMessage(note);
        } finally {
            scanner.close();
        }
    }

    // Remove a link from a note
    public void removeLink(Note note) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Check the type of the note
            if (note instanceof PermanentNote) {
                PermanentNote permanentNote = (PermanentNote) note; // Cast to PermanentNote
                System.out.println("Current links: " + permanentNote.getLinks());
                System.out.println("Enter the ID of the link to remove:");
                String linkToRemove = scanner.nextLine();
                permanentNote.deleteLink(linkToRemove);
                System.out.println("Link removed from PermanentNote.");
            } else if (note instanceof LitNote) {
                LitNote litNote = (LitNote) note; // Cast to LitNote
                System.out.println("Current links: " + litNote.getLinks());
                System.out.println("Enter the ID of the link to remove:");
                String linkToRemove = scanner.nextLine();
                litNote.deleteLink(linkToRemove);
                System.out.println("Link removed from LitNote.");
            } else {
                System.out.println("This note type does not support links.");
            }
        } finally {
            scanner.close();
        }
    }

    public void editFleetingNote(Note note) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                // Display menu of options
                System.out.println("Editing Fleeting Note: " + note.getTitle());
                System.out.println("Choose a property to edit:");
                System.out.println("1. Title");
                System.out.println("2. Tags");
                System.out.println("3. Content");
                System.out.println("4. Exit");

                // Get user input
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Handle user choice
                switch (choice) {
                    case 1:
                        editTitle(note);
                        break;
                    case 2:
                        editTags(note);
                        break;
                    case 3:
                        editContent(note);
                        break;
                    case 4:
                        System.out.println("Exiting edit mode for Fleeting Note.");
                        return; // Exit the method
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    public void editPermanentNote(Note note) {
        if (!(note instanceof PermanentNote)) {
            System.out.println("The provided note is not a PermanentNote.");
            return;
        }

        PermanentNote permanentNote = (PermanentNote) note; // Cast to PermanentNote
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                // Display menu of options
                System.out.println("Editing Permanent Note: " + permanentNote.getTitle());
                System.out.println("Choose a property to edit:");
                System.out.println("1. Title");
                System.out.println("2. Tags");
                System.out.println("3. Content");
                System.out.println("4. Exit");

                // Get user input
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Handle user choice
                switch (choice) {
                    case 1:
                        editTitle(permanentNote);
                        break;
                    case 2:
                        editTags(permanentNote);
                        break;
                    case 3:
                        editContent(permanentNote);
                        break;
                    case 4:
                        System.out.println("Exiting edit mode for Permanent Note.");
                        return; // Exit the method
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    public void editLitNote(Note note) {
        if (!(note instanceof LitNote)) {
            System.out.println("The provided note is not a Literature Note.");
            return;
        }

        LitNote litNote = (LitNote) note; // Cast to LitNote
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                // Display menu of options
                System.out.println("Editing Literature Note: " + litNote.getTitle());
                System.out.println("Choose a property to edit:");
                System.out.println("1. Title");
                System.out.println("2. Tags");
                System.out.println("3. Content");
                System.out.println("4. Citation");
                System.out.println("5. Source");
                System.out.println("6. Exit");

                // Get user input
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Handle user choice
                switch (choice) {
                    case 1:
                        editTitle(litNote);
                        break;
                    case 2:
                        editTags(litNote);
                        break;
                    case 3:
                        editContent(litNote);
                        break;
                    case 4:
                        System.out.println("Current citation: " + litNote.getCitation());
                        System.out.println("Enter new citation:");
                        String newCitation = scanner.nextLine();
                        litNote.setCitation(newCitation);
                        saveNoteMessage(litNote);
                        break;
                    case 5:
                        System.out.println("Current source: " + litNote.getSourceURL());
                        System.out.println("Enter new source URL:");
                        String newSource = scanner.nextLine();
                        litNote.setSourceURL(newSource);
                        saveNoteMessage(litNote);
                        break;
                    case 6:
                        System.out.println("Exiting edit mode for Literature Note.");
                        return; // Exit the method
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } finally {
            scanner.close();
        }
    }
}
