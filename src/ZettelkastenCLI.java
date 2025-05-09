/* import java.util.Scanner;

public class ZettelkastenCLI {
    private static Index index = Index.getInstance(); // Singleton Index for managing notes

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Zettelkasten CLI!");
        System.out.println("Type 'help' to see available commands.");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();
            String argument = parts.length > 1 ? parts[1] : "";

            switch (command) {
                case "help":
                    displayHelp();
                    break;
                case "create":
                    createNoteCLI(scanner);
                    break;
                case "convert":
                    convertNoteCLI(scanner);
                    break;
                case "search":
                    searchNotes(argument);
                    break;
                case "view":
                    viewNote(argument);
                    break;
                case "exit":
                    System.out.println("Exiting Zettelkasten CLI. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }
    }

    private static void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("  help               - Show this help message");
        System.out.println("  create             - Create a new note");
        System.out.println("  convert            - Convert a note to another type");
        System.out.println("  search <query>     - Search for notes by keyword");
        System.out.println("  view <title>       - View the content of a note");
        System.out.println("  exit               - Exit the program");
    }

    private static void createNoteCLI(Scanner scanner) {
        System.out.println("Creating a new note...");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter content: ");
        String content = scanner.nextLine();
        System.out.print("Enter tags (comma-separated): ");
        String tags = scanner.nextLine();

        // Create a new FleetingNote (default type for new notes)
        FleetingNote note = new FleetingNote(title, content, tags, getCurrentDate(), getCurrentDate());
        index.buildIndex(java.util.List.of(note)); // Add the note to the index
        System.out.println("Note created successfully!");
    }

    private static void convertNoteCLI(Scanner scanner) {
        System.out.println("Converting a note...");
        System.out.print("Enter the title of the note to convert: ");
        String title = scanner.nextLine();
        Note note = index.getNoteByTitle(title);

        if (note == null) {
            System.out.println("Note not found.");
            return;
        }

        System.out.println("Choose conversion type:");
        System.out.println("  1. Convert to Permanent Note");
        System.out.println("  2. Convert to Literature Note");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        switch (choice) {
            case 1:
                if (note instanceof FleetingNote) {
                    PermanentNote permanentNote = NoteConverter.convertFleetingToPermanent((FleetingNote) note);
                    index.buildIndex(java.util.List.of(permanentNote)); // Add the converted note to the index
                    System.out.println("Note converted to Permanent Note.");
                } else {
                    System.out.println("Only Fleeting Notes can be converted to Permanent Notes.");
                }
                break;
            case 2:
                if (note instanceof FleetingNote) {
                    LitNote litNote = NoteConverter.convertFleetingToLit((FleetingNote) note);
                    index.buildIndex(java.util.List.of(litNote)); // Add the converted note to the index
                    System.out.println("Note converted to Literature Note.");
                } else {
                    System.out.println("Only Fleeting Notes can be converted to Literature Notes.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void searchNotes(String query) {
        if (query.isEmpty()) {
            System.out.println("Please provide a search query.");
            return;
        }

        System.out.println("Searching for notes with query: " + query);
        var results = index.search(query);
        if (results.isEmpty()) {
            System.out.println("No notes found.");
        } else {
            System.out.println("Search results:");
            for (Note note : results) {
                System.out.println("  - " + note.getTitle());
            }
        }
    }

    private static void viewNote(String title) {
        if (title.isEmpty()) {
            System.out.println("Please provide the title of the note to view.");
            return;
        }

        Note note = index.getNoteByTitle(title);
        if (note == null) {
            System.out.println("Note not found.");
        } else {
            System.out.println("Title: " + note.getTitle());
            System.out.println("Content:\n" + note.getContent());
            System.out.println("Tags: " + note.getTags());

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
    }
} */