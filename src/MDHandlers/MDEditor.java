import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterVisitor;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.util.List;
import java.util.Map;

public class MDEditor {

    public static String editYamlMetadata(String markdownContent, String key, String newValue) {
        // Configure Flexmark with the YamlFrontMatterExtension
        MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, List.of(YamlFrontMatterExtension.create()));
        Parser parser = Parser.builder(options).build();

        // Parse the Markdown content
        Node document = parser.parse(markdownContent);

        // Extract YAML metadata
        YamlFrontMatterVisitor visitor = new YamlFrontMatterVisitor();
        visitor.visit(document);
        Map<String, List<String>> metadata = visitor.getData();

        // Modify the YAML metadata
        if (metadata.containsKey(key)) {
            metadata.put(key, List.of(newValue)); // Replace the value for the key
        } else {
            metadata.put(key, List.of(newValue)); // Add the key if it doesn't exist
        }

        // Rebuild the Markdown content with updated YAML metadata
        StringBuilder updatedMarkdown = new StringBuilder();
        updatedMarkdown.append("---\n");
        for (Map.Entry<String, List<String>> entry : metadata.entrySet()) {
            updatedMarkdown.append(entry.getKey()).append(": ").append(String.join(", ", entry.getValue())).append("\n");
        }
        updatedMarkdown.append("---\n\n");

        // Append the original Markdown content (excluding the original YAML front matter)
        String originalContent = markdownContent.substring(markdownContent.indexOf("---", 3) + 3).trim();
        updatedMarkdown.append(originalContent);

        return updatedMarkdown.toString();
    }

    public static String editContent(String markdownContent, String newContent) {
        // Extract the YAML front matter
        int yamlEndIndex = markdownContent.indexOf("---", 3) + 3;
        String yamlFrontMatter = markdownContent.substring(0, yamlEndIndex).trim();

        // Rebuild the Markdown content with updated content
        StringBuilder updatedMarkdown = new StringBuilder();
        updatedMarkdown.append(yamlFrontMatter).append("\n\n");
        updatedMarkdown.append(newContent); // Replace the content with the new content

        return updatedMarkdown.toString();
    }

    

}
