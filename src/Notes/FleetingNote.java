package Notes;



import java.util.ArrayList;

import MDHandler.*;

public class FleetingNote extends Note {

    public FleetingNote(String title, String content, ArrayList<String> tags, String dateCreated) {
        super(title, content, tags, dateCreated);
        this.filePath =  "Zettelkasten\\FleetingNote_" + this.uniqueID + ".md"; // Example file path for saving the note
        MDWriter.writeFleetingNote(this);
    
    }

    public FleetingNote(String filePath) {
        super(filePath);
    }

}