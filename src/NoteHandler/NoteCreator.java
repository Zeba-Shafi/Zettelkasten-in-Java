package NoteHandler;


import java.util.ArrayList;
import Notes.*;


public class NoteCreator {
    
    // Method to create a new note
    public static FleetingNote createFleetingNote(String title, String content, ArrayList<String> tags) {
        FleetingNote fleetingNote = new FleetingNote(title, content, tags, getCurrentDate());
        return fleetingNote;
    }

    public static PermanentNote createPermanentNote(String title, String content, ArrayList<String> tags, ArrayList<String> links) {
        PermanentNote permanentNote = new PermanentNote(title, content, tags, getCurrentDate(), links);
        return permanentNote;
    }

    public static LitNote createLitNote(String title, String content, ArrayList<String> tags, String citation, String sourceURL, ArrayList<String> links) {
        LitNote litNote = new LitNote(title, content, tags, getCurrentDate(), citation, sourceURL, links);
        return litNote;
    }

    public static FleetingNote createFleetingNote(String filepath) {
        FleetingNote fleetingNote = new FleetingNote(filepath);
        return fleetingNote;
    }

    public static PermanentNote createPermanentNote(String filepath) {
        PermanentNote permanentNote = new PermanentNote(filepath);
        return permanentNote;
    }

    public static LitNote createLitNote(String filepath) {
        LitNote litNote = new LitNote(filepath);
        return litNote;
    }

    //method to get curent date
    private static String getCurrentDate() {
        java.util.Date date = new java.util.Date();
        return date.toString();
    }



}
