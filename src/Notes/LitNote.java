package Notes;

import java.util.ArrayList;
import java.util.Map;

import MDHandler.MDEditor;
import MDHandler.MDParser;
import MDHandler.MDWriter;

public class LitNote extends Note {

    private String sourceURL;
    private String citation;
    private ArrayList<String> links = new ArrayList<>();

    public LitNote(String title, String content, ArrayList<String> tags, String dateCreated, String citation, String sourceURL, ArrayList<String> links) {
        super(title, content, tags, dateCreated);
        this.citation = citation;
        this.sourceURL = sourceURL;
        if (links != null && !links.isEmpty()) {this.links = links;}
        this.filePath =  "Zettelkasten\\LiteratureNote_" + this.uniqueID + ".md"; // Example file path for saving the note
       
        MDWriter.writeLiteratureNote(this);

    }

    public LitNote(String filePath) {
        super(filePath);
        this.links = MDParser.extractLinks(filePath);
        Map<String, String> metadata = MDParser.extractYamlMetadata(filePath);
        this.citation = metadata.get("citation");
        this.sourceURL = metadata.get("source");
        
    }

    //Getter and Setter methods for the new attributes
    // Usecase: Editing Notes

    public String getCitation() {
        return citation;
    }
    public void setCitation(String citation) {
        this.citation = citation;
        MDEditor.replaceCitation(this.filePath, citation);
        setDateModified(getCurrentDate());
    }
    
    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
        MDEditor.replaceSourceURL(this.filePath, sourceURL);
        setDateModified(getCurrentDate());
    }

    public String getSourceURL() {
        return sourceURL;
    }

    // Get and Set Links

    public ArrayList<String> getLinks() {
        return links;
    }

     public void setLinks(ArrayList<String> links) {
        this.links = links;
        MDEditor.replaceLinks(this.filePath, links);
        setDateModified(getCurrentDate());
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