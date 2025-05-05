import java.io.File;
public static class Settings {
    
    public static String markdownDir;
    
    //Getters

    public static String getMarkdownDir() {
        return markdownDir;
    }

    // Setter for the directory path (allows user to modify it)
    public static void setMarkdownDir(String newPath) {
        markdownDir = newPath;
        File dir = new File(markdownDir);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Markdown directory created at: " + dir.getAbsolutePath());
            } else {
                System.out.println("Failed to create markdown directory.");
            }
        }
    }

   
}
