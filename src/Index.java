import java.util.*;
import java.util.regex.*;

public class Index {
    private static Index instance; // Singleton instance
    private final Map<String, Set<Note>> invertedIndex = new HashMap<>(); // Word -> Set of Note objects
    private final Map<String, Note> notesByTitle = new HashMap<>(); // Map of note titles to Note objects

    // Private constructor to enforce singleton
    private Index() {}

    // Public method to get the singleton instance
    public static synchronized Index getInstance() {
        if (instance == null) {
            instance = new Index();
        }
        return instance;
    }

    // Build the index from a collection of notes
    public void buildIndex(Collection<Note> notes) {
        for (Note note : notes) {
            indexNote(note);
            indexNoteLinks(note);
        }
    }

    // Index a single note
    private void indexNote(Note note) {
        // Tokenize the content of the note
        String[] tokens = note.getContent().toLowerCase().split("\\W+"); // Split by non-word characters

        for (String token : tokens) {
            if (!token.isEmpty()) {
                invertedIndex.computeIfAbsent(token, k -> new HashSet<>()).add(note);
            }
        }

        // Optionally, index metadata (e.g., title, tags, author)
        String[] metadataTokens = note.getTitle().toLowerCase().split("\\W+");
        for (String token : metadataTokens) {
            if (!token.isEmpty()) {
                invertedIndex.computeIfAbsent(token, k -> new HashSet<>()).add(note);
            }
        }

        if (note instanceof LitNote) {
            LitNote litNote = (LitNote) note;
            String[] authorTokens = litNote.getAuthor().toLowerCase().split("\\W+");
            for (String token : authorTokens) {
                if (!token.isEmpty()) {
                    invertedIndex.computeIfAbsent(token, k -> new HashSet<>()).add(note);
                }
            }
        }
    }

    // Index links for a single note
    private void indexNoteLinks(Note note) {
        String content = note.getContent();
        // Regex to match Markdown links: [Link Text](NoteTitle.md)
        String regex = "\\[.*?\\]\\((.*?)\\.md\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String linkedNoteTitle = matcher.group(1); // Extract the note title from the link
            note.addOutgoingLink(linkedNoteTitle);

            // Add the current note as an incoming link to the linked note
            Note linkedNote = notesByTitle.computeIfAbsent(linkedNoteTitle, t -> new NoteStub(t));
            linkedNote.addIncomingLink(note.getTitle());
        }

        // Add the note to the map
        notesByTitle.put(note.getTitle(), note);
    }

    // Search the index for a specific term
    public Set<Note> search(String term) {
        return invertedIndex.getOrDefault(term.toLowerCase(), Collections.emptySet());
    }

    // Search for multiple terms (AND logic)
    public Set<Note> searchAll(String... terms) {
        Set<Note> result = null;

        for (String term : terms) {
            Set<Note> matches = search(term);
            if (result == null) {
                result = new HashSet<>(matches); // Initialize with the first term's results
            } else {
                result.retainAll(matches); // Perform intersection for AND logic
            }
        }

        return result != null ? result : Collections.emptySet();
    }

    // Get a note by title
    public Note getNoteByTitle(String title) {
        return notesByTitle.get(title);
    }

    // Get all notes linking to a specific note (backlinks)
    public Set<Note> getBacklinks(Note note) {
        Set<Note> backlinks = new HashSet<>();
        for (String incomingLink : note.getIncomingLinks()) {
            Note linkedNote = notesByTitle.get(incomingLink);
            if (linkedNote != null) {
                backlinks.add(linkedNote);
            }
        }
        return backlinks;
    }

    // Get all notes linked from a specific note (forward links)
    public Set<Note> getForwardLinks(Note note) {
        Set<Note> forwardLinks = new HashSet<>();
        for (String outgoingLink : note.getOutgoingLinks()) {
            Note linkedNote = notesByTitle.get(outgoingLink);
            if (linkedNote != null) {
                forwardLinks.add(linkedNote);
            }
        }
        return forwardLinks;
    }

    // Remove incoming links to a note being deleted
    public void removeIncomingLinks(Note noteToDelete) {
        // Get all notes linking to the note being deleted
        Set<Note> backlinks = getBacklinks(noteToDelete);

        // Remove the outgoing link to the note being deleted from each backlink
        for (Note backlink : backlinks) {
            backlink.getOutgoingLinks().remove(noteToDelete.getTitle());
        }

        // Remove the note from the index
        notesByTitle.remove(noteToDelete.getTitle());
    }

    // Stub class for notes that are linked but not yet indexed
    private static class NoteStub extends Note {
        public NoteStub(String title) {
            super(title, "", "", "", "");
        }

        @Override
        public void saveNote() {}
        @Override
        public void loadNote(String title) {}
        @Override
        public void deleteNote(String title) {}
    }
}