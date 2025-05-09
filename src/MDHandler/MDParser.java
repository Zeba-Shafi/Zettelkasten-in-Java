package MDHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class MDParser {
    
    // Method to parse a markdown file and extract its content
    public static String parseMarkdown(String filePath) {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
        return content.toString();
    }
  

    // Method to yaml metadata from the markdown content
    public static Map<String, String> extractYamlMetadata(String filePath) {
        String markdownContent = parseMarkdown(filePath); // Read the markdown content
        Map<String, String> metadata = new HashMap<>();
        String regex = "---\\s*\\n(.*?)\\n---"; // Match YAML block at the start of the file
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(markdownContent);

        if (matcher.find()) {
            String yamlContent = matcher.group(1); 
            
            String[] lines = yamlContent.split("\n");
            for (String line : lines) {
                
                if (line.contains(":")) { // Ensure the line contains a key-value pair
                    String[] keyValue = line.split(":", 2); // Split into key and value
                    metadata.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }
        }
        return metadata;
    }

    // Method to extract content between "Notes" and "Footnotes" headers
    public static String extractContent(String filePath) {
        String markdownContent = parseMarkdown(filePath); // Read the markdown content
        String regex =  "## Notes\\s*\\r?\\n(.*?)\\r?\\n\\s*## Footnotes";// Regex to match content between headers
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL); // DOTALL to match across multiple lines
        Matcher matcher = pattern.matcher(markdownContent);

        if (matcher.find()) {
            return matcher.group(1).trim(); // Return the captured content, trimmed of extra spaces
        }
        return ""; // Return an empty string if no match is found
    }

    // Method to extract links from the footnotes section of the markdown content
   

    // Method to extract UUIDs from the footnotes section of the markdown content
    public static ArrayList<String> extractLinks(String filePath) {
        String markdownContent = parseMarkdown(filePath); // Read the markdown content
        ArrayList<String> uuids = new ArrayList<>();
        
        // Regex to match the content after "## Footnotes"
        String regex = "## Footnotes\\s*\\r?\\n(.*)";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL); // DOTALL to match across multiple lines
        Matcher matcher = pattern.matcher(markdownContent);

        if (matcher.find()) {
            String footnotesContent = matcher.group(1); // Extract content after "## Footnotes"

            // Regex to match UUIDs
            String uuidRegex = "([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})";
            Pattern uuidPattern = Pattern.compile(uuidRegex);
            Matcher uuidMatcher = uuidPattern.matcher(footnotesContent);

            while (uuidMatcher.find()) {
                String uuid = uuidMatcher.group(1).trim(); // Extract and trim the UUID
                uuids.add(uuid); // Add the clean UUID to the list
            }
        }

        return uuids;
    }



}
