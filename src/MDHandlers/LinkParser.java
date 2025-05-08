import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;

import java.util.ArrayList;
import java.util.List;

public class LinkParser {
    /**
     * Extracts internal links (links to .md files) from the given Markdown content.
     * 
     * @param content The Markdown content to parse.
     * @return A list of internal links in the format "Link Text -> Note Title".
     */
    public static List<String> extractInternalLinks(String content) {
        List<String> links = new ArrayList<>();

        // Parse the Markdown content into an AST
        Parser parser = Parser.builder().build();
        Node document = parser.parse(content);

        // Traverse the AST to find all Link nodes
        document.accept(new com.vladsch.flexmark.util.ast.VisitHandler<>(Link.class, link -> {
            String url = link.getUrl().toString(); // Get the URL of the link
            String text = link.getText().toString(); // Get the text of the link

            // Only consider internal links (e.g., links to .md files)
            if (!url.startsWith("http") && url.endsWith(".md")) {
                // Remove the .md extension and format as "Link Text -> Note Title"
                links.add(text + " -> " + url.replace(".md", ""));
            }
        }));

        return links;
    }
}

