package main;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.jaunt.JauntException;

public class GUI {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new Panel());
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setTitle("Soccer Data Extractor");
		frame.setIconImage(loadImage("/res/img/icon.png"));
		frame.getIconImage().toString();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static BufferedImage loadImage(String fileName){
	    BufferedImage buff = null;
	    try {
	        buff = ImageIO.read(GUI.class.getResourceAsStream(fileName));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return buff;
	}

}

class Panel extends JPanel {
	
	private final static String ALL_CLUBS = "All Clubs";
	private final static String CLUB_DETAILS = "Club Details";
	private final static String CLUB_STAFF = "Club Staff";
	private final static String TEAM_DETAILS = "Team Details";
	
	private final static String LABEL = "Run Time: ";
	
	private static JTextPane pane;
	private static JLabel timeLabel;
	
	public Panel() {
		setPreferredSize(new Dimension(800, 600));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initGui();
	}
	
	private void initGui() {
		add(Box.createVerticalGlue());
		
		Box buttonBox = new Box(BoxLayout.X_AXIS);
		JButton allClubs = new JButton(ALL_CLUBS);
		JButton clubDetails = new JButton(CLUB_DETAILS);
		JButton clubStaff = new JButton(CLUB_STAFF);
		JButton teamDetails = new JButton(TEAM_DETAILS);
		List<JButton> buttons = new ArrayList<JButton>();
		buttons.add(allClubs);
		buttons.add(clubDetails);
		buttons.add(clubStaff);
		buttons.add(teamDetails);
		for (JButton button : buttons) {
			button.addActionListener(e -> {
				try {
					buttonClick(button.getText());
				} catch (JauntException e1) {
					e1.printStackTrace();
				}
			});
			buttonBox.add(button);
			buttonBox.add(Box.createRigidArea(new Dimension(5,0)));
		}
		add(buttonBox);
		
		pane = new JTextPane();
		pane.setEditable(false);
		//pane.setBackground(null);
		add(Box.createRigidArea(new Dimension(0, 25)));
		pane.setMaximumSize(new Dimension(300, 500));
		JScrollPane jsp = new JScrollPane(pane);
		add(jsp);
		
		timeLabel = new JLabel(LABEL);
		add(timeLabel);
		
		add(Box.createVerticalGlue());
	}
	
	private static void buttonClick(String text) throws JauntException {
		long time = System.currentTimeMillis();
		switch(text) {
		case ALL_CLUBS:
			pane.setText(Directory.directory());
			break;
		case CLUB_DETAILS:
			pane.setText(Website.website());
			break;
		case CLUB_STAFF:
			pane.setText(Staff.staff());
			break;
		case TEAM_DETAILS:
			pane.setText(Team.team());
			break;
		}
		long runtime = System.currentTimeMillis() - time;
		timeLabel.setText(LABEL + runtime + "ms");
	}
	
}
