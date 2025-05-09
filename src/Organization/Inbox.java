package Organization;

public class Inbox {
    // This class is responsible for handling the collection of fleeting notes to display
    // to the user in the inbox
    private static Inbox instance; // Singleton instance

    // Private constructor to enforce singleton
    private Inbox() {}

    // Public method to get the singleton instance
    public static synchronized Inbox getInstance() {
        if (instance == null) {
            instance = new Inbox();
        }
        return instance;
    }


    


    // It will use the settings from the Settings class to determine how to display the inbox.
    
}
