package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;

import Backend.*;

public class Home {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void gui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1055, 634);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPerfectBartender = new JLabel("PERFECT BARTENDER");
		lblPerfectBartender.setBounds(0, 16, 1033, 61);
		lblPerfectBartender.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblPerfectBartender.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblPerfectBartender);
		
		textField = new JTextField();
		textField.setBounds(241, 129, 564, 42);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		String[] listOfDrinks = {"drink1","drink2","drink3"};
		
		
		JButton btnNewButton = new JButton("Drink1");
		btnNewButton.setBounds(240, 251, 142, 73);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Drink2");
		button.setBounds(451, 251, 142, 73);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Drink3");
		button_1.setBounds(663, 251, 142, 73);
		frame.getContentPane().add(button_1);
		
		
		JButton [] buttons = {btnNewButton,button,button_1};
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(832, 133, 127, 35);
		btnSearch.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				String searchQuery = textField.getText();
				generateDrinkOptions(searchQuery);
				
					
					//Backmain.BackMain(args);(searchQuery);
					List<Sets> list;
					try {
						list = Backmain.search(searchQuery);
						for (int i = 0; i < 3; i++) {
							Sets l = list.get(i);
							buttons[i].setText(l.getData().getDrinkName());
							buttons[i].addActionListener(new ActionListener (){
								public void actionPerformed(ActionEvent e){
								
									DrinkRecipe dr = new DrinkRecipe(l.getData().getBody());
									dr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
									dr.setVisible(true);
									
								}
							});
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
						
						
						
						
						//System.out.println(l.getData().getDrinkName() + "  " + l.getValue());
						// System.out.println(l.getData().getBody());
						
					
				
					
					
				
				
			}
		});
		frame.getContentPane().add(btnSearch);
	
	}
	
	private void generateDrinkOptions(String searchText){
		String[] terms = searchText.split("\\s+");
		///// call IR with search
		///// return top 3 hits
		String[] listOfDrinks = {"drink1","drink2","drink3"};
		
	}
}
