public class MDParser {
    // This class is responsible for parsing markdown files and extracting note information to create note objects
    // It will use the settings from the Settings class to determine where to look for markdown files.


    public static void parseMarkdownFile(String filePath) {
        // Implementation for parsing a markdown file
        System.out.println("Parsing markdown file: " + filePath);
        // Here you would add the logic to read the file and extract notes
    }

    public static void extractNoteInfo(String markdownContent) {
        // Implementation for extracting note information from markdown content
        System.out.println("Extracting note information from markdown content.");
        // Here you would add the logic to parse the markdown content and create note objects
    }

    public static void convertToNoteObject(String noteInfo) {
        // Implementation for converting extracted note information to a note object
        System.out.println("Converting extracted note information to a note object.");
        // Here you would add the logic to create a note object from the extracted information
    }

    public static List<String> extractLinks(String markdown) {
        // Parse the Markdown content
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);

        // List to store extracted links
        List<String> links = new ArrayList<>();

        // Traverse the AST to find Link nodes
        document.accept(new com.vladsch.flexmark.util.ast.VisitHandler<>(Link.class, link -> {
            links.add(link.getUrl().toString()); // Extract the URL or file path
        }));

        return links;
    }

    // Method to extract metadata from the markdown content
    public static Map<String, String> extractMetadata(String markdown) {
        // This method would parse the YAML front matter and return it as a map
        // For simplicity, let's assume we have a method that does this
        Map<String, String> metadata = new HashMap<>();
        // Parse the YAML front matter and populate the metadata map
        return metadata;
    }

    // Method to extract content from the markdown file
    public static String extractContent(String markdown) {
        // This method would extract the main content from the markdown file
        // For simplicity, let's assume we have a method that does this
        // Using flexmark extract content after t
        
        String content = markdown; // Placeholder for actual content extraction logic
        return content;
    }

    

    
}