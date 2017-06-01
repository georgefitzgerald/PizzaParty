package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;
import java.awt.GridBagLayout;

import java.awt.*;
import javax.swing.*;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a summary class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Seok Yoon and George Fitzgerald
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private PizzaRestaurant restaurant;
	//Jframe elements
	private JPanel pnlBtn;
	private JPanel pnlTotals;
	private JLabel labDist;
	private JLabel labProf;
	private JButton btnLoad;
	private JButton btnReset;
	private JButton btnTotals;
	private JTextArea CDisplay;
	private JTextArea PDisplay;
	private JPanel pnlTable;
	private JScrollPane scroll1;
	private JScrollPane scroll2;

	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		super(title);
		createGUI();
		restaurant = new PizzaRestaurant();
	}

	
	@Override
	public void run() {
		// TO DO
	}
	
	/**
	 * Create all the gui components on the correct panels. 
	 */
	private void createGUI() { 
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		PDisplay = displayTextArea();
		CDisplay = displayTextArea();

		pnlBtn = createPanel(Color.LIGHT_GRAY); 
		pnlTotals = createPanel(Color.LIGHT_GRAY);
		pnlTable = createPanel(Color.DARK_GRAY);
		
		this.getContentPane().add(pnlBtn,BorderLayout.NORTH);
		this.getContentPane().add(pnlTotals,BorderLayout.SOUTH);
		this.getContentPane().add(pnlTable, BorderLayout.CENTER);
		

		btnLoad = createButton("Load"); 
		btnReset = createButton("Reset");
		btnTotals = createButton("Totals");
		
		labDist = createLabel("Total Distance: ");
		labProf = createLabel("Total Profit: ");
		
		layoutButtonPanel();
		createTextBoxLayout();

		totalsLabelPanel();
		repaint();
		this.setVisible(true);
	}
	
	/**
	 * Create panel with specified colour.
	 */
	private JPanel createPanel(Color c) {
		//Create a JPanel object and store it in a local var
		JPanel temp = new JPanel();
		//set the background colour to that passed in c
		temp.setBackground(c);
		//Return the JPanel object
		return temp;
	}
	
	/**
	 * Create Label object. 
	 */
	private JLabel createLabel(String str){
		JLabel notifyline1 = new JLabel();
        notifyline1.setText(str);
        return notifyline1;
	}
	
	/**
	 * Create Button object.
	 */
	private JButton createButton(String str) {
		//Create a JButton object and store it in a local var
		JButton tempButton = new JButton();
		//Set the button text to that passed in str
		tempButton.setText(str);
		//Add the frame as an actionListener
		tempButton.addActionListener(this);
		//Return the JButton object
		return tempButton;
	}
	
	/**
	 * Add buttons to button panel
	 */
	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlBtn.setLayout(layout);
		//Lots of layout code here
		//add components to grid
		GridBagConstraints constraints = new GridBagConstraints();
		//Defaults
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;
		addToPanel(pnlBtn, btnLoad,constraints,0,0,1,1);
		addToPanel(pnlBtn, btnReset,constraints,1,0,1,1);
		addToPanel(pnlBtn, btnTotals,constraints,2,0,1,1);
	} 
	
	/**
	 * Add buttons to the bottom panel for totals. 
	 */
	private void totalsLabelPanel(){
		GridBagLayout layout = new GridBagLayout();
		pnlTotals.setLayout(layout);
		//Lots of layout code here
		//add components to grid
		GridBagConstraints constraints = new GridBagConstraints();
		//Defaults
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;
		addToPanel(pnlTotals, labDist,constraints,0,0,1,1);
		addToPanel(pnlTotals, labProf,constraints,1,0,1,1);
	}
	
	/**
	 * Add the text boxes to the middle panel. 
	 */
	private void createTextBoxLayout(){
		GridBagLayout layout = new GridBagLayout();
		pnlTable.setLayout(layout);
		//Lots of layout code here
		//add scroll bar to both Jtext areas

		scroll1 = new JScrollPane(CDisplay); //place the JTextArea in a scroll pane
		scroll2 = new JScrollPane(PDisplay, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints constraints = new GridBagConstraints();
		//Defaults
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;
		constraints.fill = constraints.BOTH;
		addToPanel(pnlTable, scroll1,constraints,0,0,1,3);
		addToPanel(pnlTable, scroll2,constraints,1,0,1,2);
	}

	
	/**
	*
	* A convenience method to add a component to given grid bag
	* layout locations. Code due to Cay Horstmann
	*
	* @param c the component to add
	* @param constraints the grid bag constraints to use
	* @param x the x grid position
	* @param y the y grid position
	* @param w the grid width of the component
	* @param h the grid height of the component
	*/
	private void addToPanel(JPanel jp,Component c, GridBagConstraints
		constraints,int x, int y, int w, int h) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		jp.add(c, constraints);
	}
	
	/**
	 * Creates a text area to be added to panel. 
	 * @return Jtext Area
	 */
	private JTextArea displayTextArea(){

		JTextArea tempDisplay = new JTextArea();
		tempDisplay.setEditable(false);
		tempDisplay.setLineWrap(true);
		//tempDisplay.
		tempDisplay.setFont(new Font("Arial",Font.BOLD,12));
		tempDisplay.setSelectedTextColor(Color.YELLOW);
		tempDisplay.setBorder(BorderFactory.createEtchedBorder());
		tempDisplay.setBackground(Color.BLACK);
		Dimension textDim = new Dimension();
		textDim.setSize(30, 50);
		tempDisplay.setMinimumSize(textDim);
		return tempDisplay;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e){
		//Get event source
				Object src = e.getSource();
				//Consider the alternatives - not all active at once.
				if (src==btnLoad) {
					resetEverything();
					JFileChooser fc = new JFileChooser();
					
					int returnVal = fc.showOpenDialog(this);
					
					if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();

			            try {
							restaurant.processLog(file.getPath());
						} catch ( LogHandlerException e2) {
							System.out.println("Exception thrown by process log");
							JOptionPane.showMessageDialog(null,"ALERT MESSAGE","Error loading file",JOptionPane.WARNING_MESSAGE);
						} catch (CustomerException e2){
							JOptionPane.showMessageDialog(null,"ALERT MESSAGE","Error in Customer Data",JOptionPane.WARNING_MESSAGE);
						} catch (PizzaException e2){
							JOptionPane.showMessageDialog(null,"ALERT MESSAGE","Error in pizza Data",JOptionPane.WARNING_MESSAGE);
						}

			            //Display Each Customer as line of text
			            try {
							DisplayText();
						} catch (CustomerException | PizzaException e1) {
							System.out.println("Exception thrown by display");
							JOptionPane.showMessageDialog(null,"ALERT MESSAGE","Error displaying file",JOptionPane.WARNING_MESSAGE);

						}  
						JOptionPane.showMessageDialog(null, "Loaded Database");

					}

				}
				//Total Distance (need to implement restaurant correctly)
				if (src==btnTotals) {
					double totalDist = restaurant.getTotalDeliveryDistance();
					totalDist = Math.round(totalDist*100)/100;
					labDist.setText("Total Distance: " + totalDist)	;
					//if profit is integer print no decimals else print two
					labProf.setText("Total Profit: " +restaurant.getTotalProfit())	;
					JOptionPane.showMessageDialog(null, "Displayed Totals");

				}
				if (src==btnReset){
					resetEverything();
					JOptionPane.showMessageDialog(null, "Reset");

				}
	}
	
	/**
	 * Display Each Customer as line in text box.
	 */
	private void DisplayText() throws CustomerException, PizzaException{
		//areDisplay.setText(customerArr.get(0).toString());
		
		for (int i = 0; i < restaurant.getNumCustomerOrders(); i++){
			CDisplay.append("\n"+restaurant.getCustomerByIndex(i).getName());
			CDisplay.append(", Type:" + restaurant.getCustomerByIndex(i).getCustomerType());
			CDisplay.append(", Num:" + restaurant.getCustomerByIndex(i).getMobileNumber());
			float totalDist = (float) restaurant.getCustomerByIndex(i).getDeliveryDistance();
			totalDist = totalDist*100;
			totalDist = Math.round(totalDist);
			totalDist = totalDist/100;
			CDisplay.append(", Dist:" + totalDist);
			CDisplay.append(", X:" + restaurant.getCustomerByIndex(i).getLocationX());
			CDisplay.append(", Y:" + restaurant.getCustomerByIndex(i).getLocationY());
		}
		for (int i = 0; i < restaurant.getNumPizzaOrders(); i++){
			PDisplay.append("\n"+restaurant.getPizzaByIndex(i).getPizzaType());
			PDisplay.append(", Quantity:" + restaurant.getPizzaByIndex(i).getQuantity());
			PDisplay.append(", Price:" + restaurant.getPizzaByIndex(i).getOrderPrice());
			PDisplay.append(", Cost:" + restaurant.getPizzaByIndex(i).getOrderCost());
			PDisplay.append(", Profit:" + restaurant.getPizzaByIndex(i).getOrderProfit());
		}
	}
	
	/**
	 * Reset the text fields such that no customer or pizza data is displayed. 
	 * 
	 */
	private void resetEverything(){
		//clear the pizza restuarant display and data
		CDisplay.setText("");
		PDisplay.setText("");
		restaurant.resetDetails();
		//remove totals and profit
		labDist.setText("Total Distance: ");
		labProf.setText("Total Profit: ");
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(false);
		SwingUtilities.invokeLater(new PizzaGUI("Pizza GUI"));

	}
}
