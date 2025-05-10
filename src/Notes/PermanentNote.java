package Notes;


import java.util.ArrayList;

import MDHandler.*;


public class PermanentNote extends Note {

    private ArrayList<String> links = new ArrayList<>(); // Initialize to an empty list
    private ArrayList<String> backlinks = new ArrayList<>();

    public PermanentNote(String title, String content, ArrayList<String> tags, String dateCreated,ArrayList<String> links) {
        super(title, content, tags, dateCreated);
        if (links != null && !links.isEmpty()) {this.links = links;}
        this.filePath =  "Zettelkasten\\PermanentNote_" + this.uniqueID + ".md"; // Example file path for saving the note
        //create the md file
        MDWriter.writePermanentNote(this);
    }

    public PermanentNote(String filePath) {
        super(filePath);
        this.links = MDParser.extractLinks(filePath);
        
    }

    //Getter and Setter methods for links

    // Add and Delete Note Links

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
        MDEditor.replaceLinks(this.filePath, links);
        setDateModified(getCurrentDate());
    }

    public ArrayList<String> getBacklinks() {
        return backlinks;
    }


     // Add and Delete Note Links 
    public void addLink(String link) {       
        if (!links.contains(link)){
            links.add(link);
            MDEditor.replaceLinks(this.filePath, links);
            setDateModified(getCurrentDate());
        }

    }

    // Delete Note Links
    public void deleteLink(String link) {
        if (links.contains(link)) {
            links.remove(link); 
            MDEditor.replaceLinks(this.filePath, links);
            setDateModified(getCurrentDate());
        }

    }

    

   


   



}