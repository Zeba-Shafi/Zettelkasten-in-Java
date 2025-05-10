package Organization;

import java.util.*;
import java.io.File;

import NoteHandler.*;
import Notes.*;

public class Index {
    private static Index instance; // Singleton instance
    private final Map<String, Set<Note>> invertedIndex = new HashMap<>(); // Word -> Set of Note objects
    private final Map<String, Note> notesByID = new HashMap<>(); // Map of note titles to Note objects
    private final Map<String, Set<Note>> backlinksIndex = new HashMap<>(); // Map of noteID -> Set of notes linking to it

    // Private constructor to enforce singleton
    private Index() {}

    // Public method to get the singleton instance
    public static synchronized Index getInstance() {
        if (instance == null) {
            instance = new Index();
        }
        return instance;
    }

    public void FullIndexing(String directory) {
        File folder = new File(directory);
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Invalid directory: " + directory);
            return;
        }

        System.out.println("Starting indexing in directory: " + directory);

        // Iterate through all files in the directory
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".md")) {
                String filename = file.getName();
                String filePath = file.getAbsolutePath();

                // Debug: Print the file being processed
                System.out.println("Processing file: " + filename);

                // Determine the type of note based on the filename
                if (filename.startsWith("FleetingNote_")) {
                    FleetingNote fleetingNote = NoteCreator.createFleetingNote(filePath);
                    if (fleetingNote != null) {
                        indexNote(fleetingNote);
                        System.out.println("Indexed FleetingNote: " + fleetingNote.getUniqueID());
                    } else {
                        System.err.println("Failed to create FleetingNote from file: " + filename);
                    }
                } else if (filename.startsWith("PermanentNote_")) {
                    PermanentNote permNote = NoteCreator.createPermanentNote(filePath);
                    if (permNote != null) {
                        indexNote(permNote);
                        System.out.println("Indexed PermanentNote: " + permNote.getUniqueID());
                    } else {
                        System.err.println("Failed to create PermanentNote from file: " + filename);
                    }
                } else if (filename.startsWith("LiteratureNote_")) {
                    LitNote litNote = NoteCreator.createLitNote(filePath);
                    if (litNote != null) {
                        indexNote(litNote);
                        System.out.println("Indexed LiteratureNote: " + litNote.getUniqueID());
                    } else {
                        System.err.println("Failed to create LiteratureNote from file: " + filename);
                    }
                } else {
                    System.out.println("Skipping unknown file type: " + filename);
                }
            } else {
                System.out.println("Skipping non-md file: " + file.getName());
            }
        }

        System.out.println("Indexing completed.");
    }

    // Build the index from a collection of notes
    public void buildIndex(Collection<Note> notes) {
        for (Note note : notes) {
            indexNote(note);
            if (note instanceof LitNote) {
                indexLitNoteLinks((LitNote) note);
            } else if (note instanceof PermanentNote) {
                indexPermanentNoteLinks((PermanentNote) note);
            } 

        }
    }

    // Index a single note
    public void indexNote(Note note) {
        String[] words = note.getContent().split("\\W+"); // Split content into words
        for (String word : words) {
            word = word.toLowerCase(); // Normalize to lowercase
            invertedIndex.computeIfAbsent(word, k -> new HashSet<>()).add(note);
        }
        notesByID.put(note.getUniqueID(), note); // Add note to the map by ID
    }

    // Index links in a LitNote
    public void indexLitNoteLinks(LitNote note) {
        if (note.getLinks() == null || note.getLinks().isEmpty()) {
            return; // No links to process
        }

        for (String link : note.getLinks()) {
            Note linkedNote = notesByID.get(link);
            if (linkedNote != null) {
                // Index the linked note
                indexNote(linkedNote);

                // Update backlinks
                backlinksIndex.computeIfAbsent(link, k -> new HashSet<>()).add(note);
            }
        }
    }

    // Index links in a PermanentNote
    public void indexPermanentNoteLinks(PermanentNote note) {
        if (note.getLinks() == null || note.getLinks().isEmpty()) {
            return; // No links to process
        }

        for (String link : note.getLinks()) {
            Note linkedNote = notesByID.get(link);
            if (linkedNote != null) {
                // Index the linked note
                indexNote(linkedNote);

                // Update backlinks
                backlinksIndex.computeIfAbsent(link, k -> new HashSet<>()).add(note);
            }
        }
    }

    // Search for notes containing a specific word
    public Set<Note> searchByWord(String query) {
        Set<Note> results = new HashSet<>();
        String[] words = query.split("\\W+"); // Split query into words
        for (String word : words) {
            word = word.toLowerCase(); // Normalize to lowercase
            Set<Note> notes = invertedIndex.get(word);
            if (notes != null) {
                results.addAll(notes); // Add matching notes to results
            }
        }
        return results;
    }

    public Set<Note> searchByTag(String tag) {
        Set<Note> results = new HashSet<>();
        for (Note note : notesByID.values()) {
            if (Arrays.asList(note.getTags()).contains(tag)) {
                results.add(note); // Add matching notes to results
            }
        }
        return results;
    }

    public Set<Note> searchByDate(String date) {
        Set<Note> results = new HashSet<>();
        for (Note note : notesByID.values()) {
            if (note.getDateCreated().equals(date)) {
                results.add(note); // Add matching notes to results
            }
        }
        return results;
    }

    public Set<Note> searchByTitle(String title) {
        Set<Note> results = new HashSet<>();
        for (Note note : notesByID.values()) {
            if (note.getTitle().equalsIgnoreCase(title)) {
                results.add(note); // Add matching notes to results
            }
        }
        return results;
    }


    // Get backlinks for a specific note
    public Set<Note> getBacklinks(String noteID) {
        return backlinksIndex.getOrDefault(noteID, Collections.emptySet());
    }

    //delete a note from the index
    public void deleteNote(String noteID) {
        Note note = notesByID.remove(noteID);
        if (note != null) {
            // Remove the note from the inverted index
            String[] words = note.getContent().split("\\W+");
            for (String word : words) {
                word = word.toLowerCase();
                Set<Note> notes = invertedIndex.get(word);
                if (notes != null) {
                    notes.remove(note);
                    if (notes.isEmpty()) {
                        invertedIndex.remove(word);
                    }
                }
            }

            // Remove backlinks
            backlinksIndex.remove(noteID);

            // Remove links from other notes
            for (Set<Note> linkedNotes : backlinksIndex.values()) {
                linkedNotes.remove(note);
                for (Note linkedNote : linkedNotes) {
                    if (linkedNote instanceof LitNote) {
                        ((LitNote) linkedNote).deleteLink(noteID);
                    } else if (linkedNote instanceof PermanentNote) {
                        ((PermanentNote) linkedNote).deleteLink(noteID);
                    }
                }
            }

            // Call delete method on the note itself
            note.delete();
        }
    }

    public Note getNoteByID(String idString) {
        // Search the notesByID map for a matching Note with the given ID
        if (notesByID.containsKey(idString)) {
            return notesByID.get(idString);
        } else {
            System.err.println("Note with ID " + idString + " not found.");
            return null; // Return null if the note is not found
        }
    }

    public void addNote(Note note) {
        notesByID.put(note.getUniqueID(), note);
        indexNote(note);
    }

    public Collection<Note> getAllNotes() {
        return notesByID.values();
    }

    public void deleteLink(String sourcednoteID, String linkedNoteID) {
        Note note = notesByID.get(sourcednoteID);
        if (note != null) {
            if (note instanceof LitNote) {
                ((LitNote) note).deleteLink(linkedNoteID);
            } else if (note instanceof PermanentNote) {
                ((PermanentNote) note).deleteLink(linkedNoteID);
            }
        }

        // Remove the sourcelink from the backlinks index
        Set<Note> linkedNotes = backlinksIndex.get(linkedNoteID);
        if (linkedNotes != null) {
            linkedNotes.remove(note);
            if (linkedNotes.isEmpty()) {
                backlinksIndex.remove(linkedNoteID);
            }
        }
    }

    public void addLink(String sourcednoteID, String linkedNoteID) {
        Note note = notesByID.get(sourcednoteID);
        if (note != null) {
            if (note instanceof LitNote) {
                ((LitNote) note).addLink(linkedNoteID);
            } else if (note instanceof PermanentNote) {
                ((PermanentNote) note).addLink(linkedNoteID);
            }
        }

        // Add the sourcelink to the backlinks index
        backlinksIndex.computeIfAbsent(linkedNoteID, k -> new HashSet<>()).add(note);

    }

    //return inverted index
    public Map<String, Set<Note>> getInvertedIndex() {
        return invertedIndex;
    }

    // return notes by ID
    public Map<String, Note> getNotesByID() {
        return notesByID;
    }

    // return backlinks index
    public Map<String, Set<Note>> getBacklinksIndex() {
        return backlinksIndex;
    }


}