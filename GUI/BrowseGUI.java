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

public class BrowseGUI extends JFrame{
	
	private JPanel contentPane;
	private JList<String> noteList;
	private JScrollPane scrollPane;
	private JLabel title;
	private JButton viewButton;
	private JButton returnButton;
	
public BrowseGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //(xpos, ypos, width, height)
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		//title
		title = new JLabel("Browse!");
		title.setBounds(150, 15, 200, 29);
		contentPane.add(title);
		
		//list
		noteList = new JList<>();
        noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane();
		
		
		//button nav to MainMenuGUI
		returnButton = new JButton("Return");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuGUI mainFrame = new MainMenuGUI();
		        mainFrame.setVisible(true);
		        dispose();
			}
		});
		returnButton.setBounds(95, 86, 117, 29);
		contentPane.add(returnButton);
		
		
		//button nav to note's class
		viewButton = new JButton("Select");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		viewButton.setBounds(239, 86, 117, 29);
		contentPane.add(viewButton);
		
	}

}
