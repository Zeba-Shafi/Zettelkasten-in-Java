package MDHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MDEditor {
    
    // This class is responsible for editing the markdown files
    
    // Helper method to format tags for YAML
    private static String formatTagsForYaml(List<String> tags) {
        return "tags: [" + String.join(", ", tags) + "]";
    }

    // Replace Tags
    public static void replaceTag(String filePath, List<String> tags) {
        System.out.println("Original Tags: " + tags);
        List<String> flatTags = flattenTags(tags);
        String formattedTags = formatTagsForYaml(flatTags);
        System.out.println("Formatted Tags: " + formattedTags);

        // Logic to write the formattedTags to the file
    }

    // Helper method to flatten tags
    private static List<String> flattenTags(List<String> tags) {
        List<String> flatTags = new ArrayList<>();
        for (String tag : tags) {
            if (tag.contains(",")) {
                String[] splitTags = tag.split(",");
                for (String splitTag : splitTags) {
                    flatTags.add(splitTag.trim());
                }
            } else {
                flatTags.add(tag.trim());
            }
        }
        return flatTags;
    }

    // Replace Title
    public static void replaceTitle(String filePath, String newTitle) {
        String titleString = "";
        if (newTitle != null && newTitle.length() > 0) {
            titleString = newTitle;
        }

        // replace the line in the file that matches the pattern "tags: %s\r\n"
        String pattern = "title: .*\\r?\\n?";
        String replacement = "title: " + titleString;
        try {
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
            List<String> updatedLines = new ArrayList<>();
            for (String line : lines) {
                if (line.matches(pattern)) {
                    updatedLines.add(replacement);
                } else {
                    updatedLines.add(line);
                }
            }
            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), updatedLines);
            System.out.println("title replaced in file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
       
    }

    // Replace Content 

    // Replace Date Modified
    public static void replaceDateModified(String filePath, String newDateModified) {
        String dateModifiedString = "";
        if (newDateModified != null && newDateModified.length() > 0) {
            dateModifiedString = newDateModified;
        }

        // replace the line in the file that matches the pattern "tags: %s\r\n"
        String pattern = "dateModified: .*\\r?\\n?";
        String replacement = "dateModified: " + dateModifiedString;
        try {
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
            List<String> updatedLines = new ArrayList<>();
            for (String line : lines) {
                if (line.matches(pattern)) {
                    updatedLines.add(replacement);
                } else {
                    updatedLines.add(line);
                }
            }
            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), updatedLines);
            System.out.println("dateMod replaced: " + filePath);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
       
    }


    // Replace content
    public static void replaceContent(String filePath, String newContent) {
        String contentString = "";
        if (newContent != null && newContent.length() > 0) {
            contentString = newContent;
        }

        // replace the line in the file that matches the pattern "tags: %s\r\n"
        String pattern = "## Notes\\s*\\r?\\n(.*?)\\r?\\n\\s*## Footnotes";
        String replacement = "## Notes\r\n" + contentString + "\r\n## Footnotes";
        try {
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
            List<String> updatedLines = new ArrayList<>();
            for (String line : lines) {
                if (line.matches(pattern)) {
                    updatedLines.add(replacement);
                } else {
                    updatedLines.add(line);
                }
            }
            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), updatedLines);
            System.out.println("content replaced: " + filePath);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
       
    }

    // Replace Citation
    public static void replaceCitation(String filePath, String newCitation) {
        String citationString = "";
        if (newCitation != null && newCitation.length() > 0) {
            citationString = newCitation;
        }

        // replace the line in the file that matches the pattern "tags: %s\r\n"
        String pattern = "citation: .*\\r?\\n?";
        String replacement = "citation: " + citationString;
        try {
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
            List<String> updatedLines = new ArrayList<>();
            for (String line : lines) {
                if (line.matches(pattern)) {
                    updatedLines.add(replacement);
                } else {
                    updatedLines.add(line);
                }
            }
            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), updatedLines);
            System.out.println("citation replaced: " + filePath);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
       
    }

    // Replace Source URL
    public static void replaceSourceURL(String filePath, String newSourceURL) {
        String sourceURLString = "";
        if (newSourceURL != null && newSourceURL.length() > 0) {
            sourceURLString = newSourceURL;
        }

        // replace the line in the file that matches the pattern "tags: %s\r\n"
        String pattern = "source: .*\\r?\\n?";
        String replacement = "source: " + sourceURLString;
        try {
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
            List<String> updatedLines = new ArrayList<>();
            for (String line : lines) {
                if (line.matches(pattern)) {
                    updatedLines.add(replacement);
                } else {
                    updatedLines.add(line);
                }
            }
            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), updatedLines);
            System.out.println("sourceURL replaced: " + filePath);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
       
    }

    public static void replaceLinks(String filePath, ArrayList<String> newLinks) {
        //delete all the links in the file
        try {
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
            List<String> updatedLines = new ArrayList<>();
            // Removed unused variable 'foundPattern'

            for (String line : lines) {
                if (line.matches("## Footnotes\\s*\\r?\\n?")) {
                    updatedLines.add(line);
                    break; // Stop processing after finding the pattern
                }
                updatedLines.add(line);
            }

            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), updatedLines);
            System.out.println("Deleted lines after the pattern in file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
        // all the lines after the pattern

        if (!(newLinks.isEmpty())) {
            ArrayList<String> links = newLinks;
            // Convert the links to a string format
            StringBuilder linksString = new StringBuilder();
            for (String link : links) {
                linksString.append(link + "\n");
            }

            // Write the links to the file
            try (FileWriter writer = new FileWriter(filePath, true)) { // Append mode
                writer.write(linksString.toString());
                System.out.println("Links written to file: " + filePath);
            } catch (IOException e) {
                System.err.println("Error writing links to file: " + e.getMessage());
            }
        } else {
             try (FileWriter writer = new FileWriter(filePath, true)) { // Append mode
                writer.write("");
                System.out.println("Links written to file: " + filePath);
            } catch (IOException e) {
                System.err.println("Error writing links to file: " + e.getMessage());
            }

        }
    }



    // Save Fleeting Note
    // Save Permanent Note
    // Save Literature Note
    
    // Delete Fleeting Note
    // Delete Permanent Note
    // Delete Literature Note
    
    // Create Fleeting Note
    // Create Permanent Note
    // Create Literature Note
}
