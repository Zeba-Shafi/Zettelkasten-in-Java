package Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Notes.FleetingNote;
import Notes.LitNote;
import Notes.Note;
import Notes.PermanentNote;

public class Browser {

    Index index = Index.getInstance();
    // This class is responsible for browsing through notes for a particular query

    public Note globalSearch() {
        Scanner scanner = new Scanner(System.in);
        try {
            // Step 1: Ask for the type of note to search for
            System.out.println("Select the type of note to search for:");
            System.out.println("1. Fleeting Note");
            System.out.println("2. Permanent Note");
            System.out.println("3. Literature Note");
            System.out.print("Enter your choice: ");
            int noteTypeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Step 2: Ask for the parameter to search by
            System.out.println("Select the parameter to search by:");
            System.out.println("1. Title");
            System.out.println("2. Tags");
            System.out.println("3. Content");
            System.out.print("Enter your choice: ");
            int parameterChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Step 3: Get the search query
            System.out.print("Enter your search query: ");
            String query = scanner.nextLine();

            // Step 4: Filter notes based on type and parameter
            ArrayList<Note> matchingNotes = new ArrayList<>();
            for (Note note : index.getNotesByID().values()) {
                // Filter by note type
                if ((noteTypeChoice == 1 && note instanceof FleetingNote) ||
                    (noteTypeChoice == 2 && note instanceof PermanentNote) ||
                    (noteTypeChoice == 3 && note instanceof LitNote)) {

                    // Filter by parameter
                    switch (parameterChoice) {
                        case 1: // Title
                            if (note.getTitle() != null && note.getTitle().toLowerCase().contains(query.toLowerCase())) {
                                matchingNotes.add(note);
                            }
                            break;
                        case 2: // Tags
                            if (note.getTags() != null && note.getTags().contains(query)) {
                                matchingNotes.add(note);
                            }
                            break;
                        case 3: // Content
                            Set<Note> contentMatches = index.getInvertedIndex().get(query.toLowerCase());
                            if (contentMatches != null && contentMatches.contains(note)) {
                                matchingNotes.add(note);
                            }
                            break;
                        default:
                            System.out.println("Invalid parameter choice.");
                            return null;
                    }
                }
            }

            // Step 5: Display matching notes
            if (matchingNotes.isEmpty()) {
                System.out.println("No matching notes found.");
                return null;
            }

            System.out.println("Matching notes:");
            for (int i = 0; i < matchingNotes.size(); i++) {
                System.out.println((i + 1) + ". " + matchingNotes.get(i).getTitle());
            }

            // Step 6: Ask the user to select a note
            System.out.print("Enter the number of the note to select: ");
            int selectedNoteIndex = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (selectedNoteIndex < 1 || selectedNoteIndex > matchingNotes.size()) {
                System.out.println("Invalid selection.");
                return null;
            }

            // Return the selected note
            return matchingNotes.get(selectedNoteIndex - 1);
        } finally {
            scanner.close();
        }
    }

    // Search notes by tag
    public ArrayList<Note> searchByTag(String tag) {
        ArrayList<Note> matchingNotes = new ArrayList<>();

        // Iterate through all notes in the index
        for (Note note : index.getNotesByID().values()) {
            if (note.getTags() != null && note.getTags().contains(tag)) {
                matchingNotes.add(note);
            }
        }

        return matchingNotes;
    }

    // Search notes by title
    public ArrayList<Note> searchByTitle(String title) {
        ArrayList<Note> matchingNotes = new ArrayList<>();

        // Iterate through all notes in the index
        for (Note note : index.getNotesByID().values()) {
            if (note.getTitle() != null && note.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingNotes.add(note);
            }
        }

        return matchingNotes;
    }

    // Search notes by content using the inverted index
    public ArrayList<Note> searchByContent(String keyword) {
        ArrayList<Note> matchingNotes = new ArrayList<>();

        // Get the set of notes that contain the keyword from the inverted index
        Set<Note> notesWithKeyword = index.getInvertedIndex().get(keyword.toLowerCase());
        if (notesWithKeyword != null) {
            matchingNotes.addAll(notesWithKeyword);
        }

        return matchingNotes;
    }
}
