package Organization;
import Notes.FleetingNote;

import java.util.ArrayList;

public class Inbox {
    // This class is responsible for handling the collection of fleeting notes to display
    // to the user in the inbox
    private static Inbox instance; // Singleton instance
    private static Index index = Index.getInstance(); // Singleton Index for managing notes
    private final ArrayList<FleetingNote> fleetingNotes = new ArrayList<>(); // List of fleeting notes

    // Private constructor to enforce singleton
    private Inbox() {}

    // Public method to get the singleton instance
    public static synchronized Inbox getInstance() {
        if (instance == null) {
            instance = new Inbox();
        }
        return instance;
    }

    public void addFleetingNote(FleetingNote fleetingNote) {
        fleetingNotes.add(fleetingNote);
    }

    // Method to display the inbox
    private static ArrayList<FleetingNote> retrieveFleetingNotes(String orderby,) {

        // This method will display the inbox to the user
        // It will use the settings from the Settings class to determine how to display the inbox.
        return null;
    }


    // use the inverted index in the index instance to search fleeting notes by content
    
    


    // It will use the settings from the Settings class to determine how to display the inbox.
    
}
