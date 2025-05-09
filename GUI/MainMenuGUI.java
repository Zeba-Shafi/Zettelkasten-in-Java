import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainMenuGUI extends JFrame{
	
	private JPanel contentPane;
	private JLabel title;
	private JButton noteButton;
	private JButton browseButton;
	private JButton inboxButton;
	private JButton exitButton;

	public MainMenuGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //(xpos, ypos, width, height)
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		//welcome title
		title = new JLabel("Welcome to Zettelkasten!");
		title.setBounds(150, 15, 200, 29);
		contentPane.add(title);
		
		//button nav to NoteGUI
		noteButton = new JButton("Create Note");
		noteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NoteGUI noteFrame = new NoteGUI();
				noteFrame.setVisible(true);
			}
		});
		noteButton.setBounds(95, 46, 117, 29);
		contentPane.add(noteButton);
		
		//button nav to BrowseGUI
		browseButton = new JButton("Browse Notes");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        BrowseGUI browseFrame = new BrowseGUI();
		        browseFrame.setVisible(true);
		        dispose();
		        
			}
		});
		browseButton.setBounds(239, 46, 117, 29);
		contentPane.add(browseButton);
		
		//button nav to InboxGUI
		inboxButton = new JButton("View Inbox");
		inboxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InboxGUI inboxFrame = new InboxGUI();
				inboxFrame.setVisible(true);
				dispose();
			}
		});
		inboxButton.setBounds(95, 86, 117, 29);
		contentPane.add(inboxButton);
		
		
		//close program
		exitButton = new JButton("Exit Program");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		exitButton.setBounds(239, 86, 117, 29);
		contentPane.add(exitButton);
		
	}
	
}
