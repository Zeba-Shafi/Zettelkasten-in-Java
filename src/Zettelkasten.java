
public class Zettelkasten {
    public static void main(String[] args) throws Exception {
        
    	//open GUI
        MainMenuGUI mainFrame = new MainMenuGUI();
		mainFrame.setVisible(true);

        // Check if this is the first time the program is run
        
        if (args.length == 0) {
            System.out.println("Welcome to Zettelkasten! Please provide a command.");
            return;
        }
        // Welcome message
        
    }
}
