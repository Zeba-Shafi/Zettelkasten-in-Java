package Organization;

import java.util.ArrayList;
import java.util.Map;

import Notes.Note;

public class Browser {
    
    Index index = Index.getInstance();
    // This class is responsible for browsing through notes for a particular query

    public ArrayList<Note> globalSearch() {

    }
    

    public static ArrayList<Note> returnMatchingNoteByNoteID(Map<String,Note> notes, ) {
        ArrayList<Note> matchesByID = new ArrayList<Note>();

        //iterate through noteByIndex and add
        for (note : index.getAllNotes())
    }
    
    // It will use the settings from the Settings class to determine how to browse through notes.

    // Global search
    
    // Search only Fleeting Notes
    
    // Search only Permanent Notes

    // Search only Literature Notes

    // Search by Tag 
    private ArrayList<Note> searchByTag(ArrayList<Note>)
    // Order by Date Created

    // Order by Date Modified

    // Order by Title

    

    
}
