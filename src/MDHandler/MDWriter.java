package MDHandler;

import Notes.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MDWriter {
 
    public static void writeFleetingNote(FleetingNote note) {
        // Get the file template as a string for formatting by opening the file using the file path
        String fileTemplate = "---\r\n" + //
                        "NoteID: %s\r\n" + //
                        "title: %s\r\n" + //
                        "tags: %s\r\n" + //
                        "dateCreated: %s\r\n" + //
                        "dateModified: %s\r\n" + //
                        "---\r\n" + //
                        "\r\n" + //
                        "## Notes\r\n" + //
                        "%s" + //
                        "\r\n" + // 
                        "## Footnotes\r\n";

        // Get the note details
        String filePath = note.getFilePath();
        String directoryPath = new File(filePath).getParent(); // Extract the directory path

        // Ensure the directory exists
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }
        String UniqueID = note.getUniqueID();

        String content = note.getContent();
        String title = note.getTitle();
        String tags = "[" + String.join(", ", note.getTags()) + "]";
        String dateCreated = note.getDateCreated();
        String dateModified = note.getDateModified();

        // Create the markdown string using the template
        String markdownString = String.format(fileTemplate, UniqueID, title, tags, dateCreated, dateModified, content);

        // Write the markdown string to the file
        try (FileWriter writer = new FileWriter(filePath)) { 
            writer.write(markdownString);
            System.out.println("Fleeting note written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing fleeting note to file: " + e.getMessage());
        }
    }

    public static void writePermanentNote(PermanentNote note) {
        String fileTemplate = "---\r\n" + //
                        "NoteID: %s\r\n" + //
                        "title: %s\r\n" + //
                        "tags: %s\r\n" + //
                        "dateCreated: %s\r\n" + //
                        "dateModified: %s\r\n" + //
                        "---\r\n" + //
                        "\r\n" + //
                        "## Notes\r\n" + //
                        "%s\r\n" + //
                        "\r\n" + // 
                        "## Footnotes\r\n";
       
        
        // Get the note details
        String filePath = note.getFilePath();
        String directoryPath = new File(filePath).getParent(); // Extract the directory path

        // Ensure the directory exists
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }
        String UniqueID = note.getUniqueID();

        String content = note.getContent();
        String title = note.getTitle();
        String tags = "[" + String.join(", ", note.getTags()) + "]";
        String dateCreated = note.getDateCreated();
        String dateModified = note.getDateModified();


        // Create the markdown string using the template
        String markdownString = String.format(fileTemplate, UniqueID, title, tags, dateCreated, dateModified, content);

        // Write the markdown string to the file
        try (FileWriter writer = new FileWriter(filePath)) { 
            writer.write(markdownString);
            System.out.println("Permanent note written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing permanent note to file: " + e.getMessage());
        }


        if (!(note.getLinks().isEmpty())) {
            ArrayList<String> links = note.getLinks();
            // Convert the links to a string format
            StringBuilder linksString = new StringBuilder();
            for (String link : links) {
                linksString.append(link).append("\r\n");
            }

            // Write the links to the file
            try (FileWriter writer = new FileWriter(filePath, true)) { // Append mode
                writer.write(linksString.toString());
                System.out.println("Links written to file: " + filePath);
            } catch (IOException e) {
                System.err.println("Error writing links to file: " + e.getMessage());
            }
        }

    }

    public static void writeLiteratureNote(LitNote note) {
        // Get the file template as a string for formatting by opening the file using the file path
        
        String fileTemplate = "---\r\n" + //
                        "NoteID: %s\r\n" + //
                        "title: %s\r\n" + //
                        "tags: %s\r\n" + //
                        "dateCreated: %s\r\n" + //
                        "dateModified: %s\r\n" + //
                        "citation: %s\r\n" + //
                        "source: %s\r\n" + //             
                        "---\r\n" + //
                        "\r\n" + //
                        "## Notes\r\n" + //
                        "%s\r\n" + //
                        "\r\n" + //
                        "## Footnotes\r\n";
        
        // Get the note details
        String filePath = note.getFilePath();
        String directoryPath = new File(filePath).getParent(); // Extract the directory path

        // Ensure the directory exists
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }
        String UniqueID = note.getUniqueID();

        String content = note.getContent();
        String title = note.getTitle();
        String tags = "[" + String.join(", ", note.getTags()) + "]";
        String dateCreated = note.getDateCreated();
        String dateModified = note.getDateModified();
        String citation = note.getCitation();
        String sourceURL = note.getSourceURL();


        // Create the markdown string using the template
        String markdownString = String.format(fileTemplate, UniqueID, title, tags, dateCreated, dateModified, citation, sourceURL, content);

        // Write the markdown string to the file
        try (FileWriter writer = new FileWriter(filePath)) { 
            writer.write(markdownString);
            System.out.println("Literature note written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing permanent note to file: " + e.getMessage());
        }

        // Write the links to the file
        if (!(note.getLinks().isEmpty())) {
            ArrayList<String> links = note.getLinks();
            // Convert the links to a string format
            StringBuilder linksString = new StringBuilder();
            for (String link : links) {
                linksString.append("-  ").append(link).append("\r\n");
            }

            // Write the links to the file
            try (FileWriter writer = new FileWriter(filePath, true)) { // Append mode
                writer.write(linksString.toString());
                System.out.println("Links written to file: " + filePath);
            } catch (IOException e) {
                System.err.println("Error writing links to file: " + e.getMessage());
            }
        }


    }

}