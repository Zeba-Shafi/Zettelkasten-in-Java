package Organization;ner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;stenCLI {
import java.util.Scanner;ex index = Index.getInstance(); // Singleton Index for managing notes
import java.util.Set;box inbox = Inbox.getInstance(); // Singleton Inbox for managing fleeting notes

import Notes.FleetingNote;the CLI
import Notes.LitNote;d main(String[] args) {
import Notes.Note;anner = new Scanner(System.in);
import Notes.PermanentNote;"Welcome to Zettelkasten CLI");
        System.out.println("Type 'help' to see available commands.");
public class Browser {
        while (true) {
    Index index = Index.getInstance();
    // This class is responsible for browsing through notes for a particular query
            String[] parts = input.split(" ", 2);
    public Note globalSearch() {ts[0].toLowerCase();
        Scanner scanner = new Scanner(System.in);parts[1] : "";
        try {
            // Step 1: Ask for the type of note to search for
            System.out.println("Select the type of note to search for:");
            System.out.println("1. Fleeting Note");
            System.out.println("2. Permanent Note");
            System.out.println("3. Literature Note");
            System.out.print("Enter your choice: ");
            int noteTypeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
                    convertNoteCLI(scanner);
            // Step 2: Ask for the parameter to search by
            System.out.println("Select the parameter to search by:");
            System.out.println("1. Title");
            System.out.println("2. Tags");
            System.out.println("3. Content");
            System.out.print("Enter your choice: ");
            int parameterChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
                    System.out.println("Exiting Zettelkasten CLI. Goodbye!");
            // Step 3: Get the search query
            System.out.print("Enter your search query: ");
            String query = scanner.nextLine();
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
            // Step 4: Filter notes based on type and parameter
            ArrayList<Note> matchingNotes = new ArrayList<>();
            for (Note note : index.getNotesByID().values()) {
                // Filter by note type
                if ((noteTypeChoice == 1 && note instanceof FleetingNote) ||
                    (noteTypeChoice == 2 && note instanceof PermanentNote) ||
                    (noteTypeChoice == 3 && note instanceof LitNote)) {ge");
        System.out.println("  create             - Create a new note");
                    // Filter by parameter       - Convert a note to another type");
                    switch (parameterChoice) {   - Search for notes by keyword");
                        case 1: // Titlee>       - View the content of a note");
                            if (note.getTitle() != null && note.getTitle().toLowerCase().contains(query.toLowerCase())) {
                                matchingNotes.add(note);
                            }
                            break;CLI(Scanner scanner) {
                        case 2: // Tagsnew note...");
                            if (note.getTags() != null && note.getTags().contains(query)) {
                                matchingNotes.add(note);
                            }er content: ");
                            break;extLine();
                        case 3: // Contenta-separated): ");
                            Set<Note> contentMatches = index.getInvertedIndex().get(query.toLowerCase());
                            if (contentMatches != null && contentMatches.contains(note)) {
                                matchingNotes.add(note);ew notes)
                            }ew FleetingNote(title, content, tags, getCurrentDate(), getCurrentDate());
                            break;.List.of(note)); // Add the note to the index
                        default: created successfully!");
                            System.out.println("Invalid parameter choice.");
                            return null;
                    }id convertNoteCLI(Scanner scanner) {
                }t.println("Converting a note...");
            }m.out.print("Enter the title of the note to convert: ");
        String title = scanner.nextLine();
            // Step 5: Display matching notese);
            if (matchingNotes.isEmpty()) {
                System.out.println("No matching notes found.");
                return null;ln("Note not found.");
            }eturn;
        }
            System.out.println("Matching notes:");
            for (int i = 0; i < matchingNotes.size(); i++) {
                System.out.println((i + 1) + ". " + matchingNotes.get(i).getTitle());
            }m.out.println("  2. Convert to Literature Note");
        System.out.print("Enter your choice: ");
            // Step 6: Ask the user to select a note
            System.out.print("Enter the number of the note to select: ");
            int selectedNoteIndex = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            case 1:
            if (selectedNoteIndex < 1 || selectedNoteIndex > matchingNotes.size()) {
                System.out.println("Invalid selection.");verter.convertFleetingToPermanent((FleetingNote) note);
                return null;ildIndex(java.util.List.of(permanentNote)); // Add the converted note to the index
            }       System.out.println("Note converted to Permanent Note.");
                } else {
            // Return the selected note"Only Fleeting Notes can be converted to Permanent Notes.");
            return matchingNotes.get(selectedNoteIndex - 1);
        } finally {ak;
            scanner.close();
        }       if (note instanceof FleetingNote) {
    }               LitNote litNote = NoteConverter.convertFleetingToLit((FleetingNote) note);
                    index.buildIndex(java.util.List.of(litNote)); // Add the converted note to the index
    // Search notes by tag.out.println("Note converted to Literature Note.");
    public ArrayList<Note> searchByTag(String tag) {
        ArrayList<Note> matchingNotes = new ArrayList<>();s can be converted to Literature Notes.");
                }
        // Iterate through all notes in the index
        for (Note note : index.getNotesByID().values()) {
            if (note.getTags() != null && note.getTags().contains(tag)) {
                matchingNotes.add(note);
            }
        }
    private static void searchNotes(String query) {
        return matchingNotes;{
    }       System.out.println("Please provide a search query.");
            return;
    // Search notes by title
    public ArrayList<Note> searchByTitle(String title) {
        ArrayList<Note> matchingNotes = new ArrayList<>();: " + query);
        var results = index.search(query);
        // Iterate through all notes in the index
        for (Note note : index.getNotesByID().values()) {
            if (note.getTitle() != null && note.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingNotes.add(note);esults:");
            }or (Note note : results) {
        }       System.out.println("  - " + note.getTitle());
            }
        return matchingNotes;
    }

    // Search notes by content using the inverted index
    public ArrayList<Note> searchByContent(String keyword) {
        ArrayList<Note> matchingNotes = new ArrayList<>();f the note to view.");
            return;
        // Get the set of notes that contain the keyword from the inverted index
        Set<Note> notesWithKeyword = index.getInvertedIndex().get(keyword.toLowerCase());
        if (notesWithKeyword != null) {e(title);
            matchingNotes.addAll(notesWithKeyword);
        }   System.out.println("Note not found.");
        } else {
        return matchingNotes;n("Title: " + note.getTitle());
    }       System.out.println("Content:\n" + note.getContent());
}           System.out.println("Tags: " + note.getTags());

            // Parse and display internal links
            note.parseLinks();
            Set<String> links = note.getOutgoingLinks();
            if (!links.isEmpty()) {
                System.out.println("\nInternal Links:");
                int i = 1;
                for (String link : links) {
                    System.out.println("  " + i + ". " + link);
                    i++;
                }

                // Allow navigation to a linked note
                System.out.print("\nEnter the number of a link to view it, or press Enter to go back: ");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().trim();
                if (!input.isEmpty()) {
                    try {
                        int choice = Integer.parseInt(input);
                        if (choice > 0 && choice <= links.size()) {
                            String selectedLink = links.toArray(new String[0])[choice - 1];
                            viewNote(selectedLink); // Recursively view the linked note
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                }
            } else {
                System.out.println("\nNo internal links in this note.");
            }
        }
    }

    private static String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    } */
}