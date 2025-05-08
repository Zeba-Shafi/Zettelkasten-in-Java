import java.io.IOException;


public class Zettelkasten {
//change from CLI to GUI
    public static void main(String[] args) throws Exception {
        

        // Check if this is the first time the program is run

        if (Settings.getMarkdownDir() == null || Settings.getMarkdownDir().isEmpty()) {
            //message to user ask to set the markdown directory
            System.out.println("Welcome to Zettelkasten! Please set your markdown directory.");
        }

        // Initialize the markdown directory
        Settings.setMarkdownDir("path/to/your/markdown/directory"); // Set your desired path here
        System.out.println("Markdown directory set to: " + Settings.getMarkdownDir());

        // Load existing notes or create a new note 
        

        // Welcome message
    }
}
