import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NoteGUI extends JFrame{
	
	private JPanel contentPane;
	private Note note;
	private JLabel title;
	private JButton saveButton;
	private JButton returnButton;
	private JTextField textField;
	
	public NoteGUI() {
		defaultMethod();
		note = null;
		
	}
	
	public NoteGUI(Note note) {
		defaultMethod();
		this.note = note;

	}
	
	public void defaultMethod() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //(xpos, ypos, width, height)
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		returnButton = new JButton("Return");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuGUI mainFrame = new MainMenuGUI();
		        mainFrame.setVisible(true);
		        dispose();
			}
		});
		returnButton.setBounds(95, 146, 117, 29);
		contentPane.add(returnButton);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		saveButton.setBounds(239, 146, 117, 29);
		contentPane.add(saveButton);
		
	}
}
