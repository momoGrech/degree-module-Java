package GUI;
import java.awt.*;
import java.awt.event.*;
import itemSrc.*;
import javax.swing.*;
import exceptionHandling.*;
/**
 * GUI class is responisble for displaying list of orders, processed orders, failed orders and warehouse items.
 *
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   14/12/2021
 */
public class JavaGUI extends JFrame implements ActionListener
{
    //JavaGUI atributes
    private QueueOfCusts listOfOrder = new QueueOfCusts();
    private HeadsetMap listOfHeadset;
    private Manager manager;
    private JLabel lblCurrentOrder,lblListOfOrders,lblElementQty,lblElementPrice, lblProcessedOrders, lblUnProcessedOrders, lblWareHouse;
    private JTextField txtNewElementName;
    private JTextField txtNewElementQty;
    private JTextField txtNewElementPrice;
    private JScrollPane scrollElementsListProcessed;
    private JScrollPane scrollElementsListUnProcessed;
    private JTextArea displayListOfOrders;
    private JTextArea displayCurrentOrder;
    private JTextArea displayWareHouse;
    private JTextArea displayProcessedOrders;
    private JTextArea displayProcessedOrdersHeader;
    private JTextArea displayUnProcessedOrders;
    private JTextArea displayUnProcessedOrdersHeader;
    private JButton btnLoadOrderList, btnAddNextElement, btnClose;
    
    //colors
    public static final Color DARK_RED = new Color(204, 0, 0);
    public static final Color LIGHT_GREY = new Color(204, 204, 204);
    
    /**
     * Constructors for objects of class JavaWindow
     */
    /**
     * this method is an overloaded constructor
     * @param manager This parameter contains all the information related to orders and items
     */
    public JavaGUI(Manager manager)
    {
        // initialise instance variables
        this.manager = manager;
        this.listOfHeadset = manager.getHeadsetList();
        this.listOfOrder = manager.getOrderList();
        
        //initialise GUI Components
        initializeGUIComponents();
        
        //make the window visible
        setVisible(true);
    }
    
    /**
     * this method is responsible for initialising the graphical user interface components
     * such as text area, labels and buttons
     * @param manager This parameter contains all the information related to orders and items
     * @return Nothing
     */
    public void initializeGUIComponents(){
        //Set up Window Title, Size & Layout
        setTitle("Headset Warehose");
        setSize(1250,750);
        setLayout(null);
        
        //Set up the panel that will display the list of Orders
        displayListOfOrders = new JTextArea(160,250);
        displayListOfOrders.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayListOfOrders.setEditable(false);
        displayListOfOrders.setBounds(30, 30, 460, 400);
        displayListOfOrders.setBorder(BorderFactory.createLineBorder(Color.black));
        
        //Label for List of Orders
        lblListOfOrders = new JLabel("List of Orders");
        lblListOfOrders.setFont(new Font (Font.DIALOG, Font.BOLD,16));
        lblListOfOrders.setBounds(220,10,250,15);
        
        //Set up the panel that will display the WareHouse items
        displayWareHouse = new JTextArea(160,220); //can also specify rows & Columns
        displayWareHouse.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayWareHouse.setEditable(false);
        displayWareHouse.setBounds(30, 465, 460, 220);
        displayWareHouse.setBorder(BorderFactory.createLineBorder(Color.black));
        
        //Label for warehouse items
        lblWareHouse = new JLabel("List of items in the Warehouse");
        lblWareHouse.setFont(new Font (Font.DIALOG, Font.BOLD,16));
        lblWareHouse.setBounds(150,440,250,15);
        
        //Set up the panel that will display the current order
        displayCurrentOrder = new JTextArea(300,50); //can also specify rows & Columns
        displayCurrentOrder.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayCurrentOrder.setEditable(false);
        displayCurrentOrder.setBounds(540, 60, 600, 50);
        displayCurrentOrder.setBorder(BorderFactory.createLineBorder(Color.black));
        
        //Label for current order
        lblCurrentOrder = new JLabel("Current Order");
        lblCurrentOrder.setFont(new Font (Font.DIALOG, Font.BOLD,16));
        lblCurrentOrder.setBounds(790,30,250,15);
        
        //Set up the panel that will display the Processed Order header
        displayProcessedOrdersHeader = new JTextArea(300,30); //can also specify rows & Columns
        displayProcessedOrdersHeader.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayProcessedOrdersHeader.setEditable(false);
        displayProcessedOrdersHeader.setBounds(540, 160, 600, 30);
        displayProcessedOrdersHeader.setBackground(LIGHT_GREY);
        
        //Set up the panel that will display the Processed Orders
        displayProcessedOrders = new JTextArea(300,200); //can also specify rows & Columns
        displayProcessedOrders.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayProcessedOrders.setEditable(false);
        
        //Using scroll feature for processed orders
        scrollElementsListProcessed = new JScrollPane(displayProcessedOrders);
        scrollElementsListProcessed.setBounds(540, 190, 600, 160);
        
        //Label for processed orders
        lblProcessedOrders = new JLabel("Processed Orders");
        lblProcessedOrders.setFont(new Font (Font.DIALOG, Font.BOLD,16));
        lblProcessedOrders.setBounds(780,130,250,15);
        
        //Set up the panel that will display the Unprocessed Order header
        displayUnProcessedOrdersHeader = new JTextArea(300,30); //can also specify rows & Columns
        displayUnProcessedOrdersHeader.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayUnProcessedOrdersHeader.setEditable(false);
        displayUnProcessedOrdersHeader.setBounds(540, 400, 600, 30);
        displayUnProcessedOrdersHeader.setBackground(LIGHT_GREY);
        
        //Set up the panel that will display the Unprocessed Orders
        displayUnProcessedOrders = new JTextArea(300,200); //can also specify rows & Columns
        displayUnProcessedOrders.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayUnProcessedOrders.setEditable(false);
        
        //Using scroll feature for unprocessed orders
        scrollElementsListUnProcessed = new JScrollPane(displayUnProcessedOrders);
        scrollElementsListUnProcessed.setBounds(540, 430, 600, 160);
        
        //Label for Failed Orders
        lblUnProcessedOrders = new JLabel("Failed Orders");
        lblUnProcessedOrders.setFont(new Font (Font.DIALOG, Font.BOLD,16));
        lblUnProcessedOrders.setBounds(800,370,250,15);
        
        //Set up button for order list
        btnLoadOrderList = new JButton("Load");
        btnLoadOrderList.setBounds(920,620,100,50); //x-xoord, y-coord, width, height
        
        //Set Up Next Button to Save New Product to List and show the next order
        btnAddNextElement = new JButton("Next");
        btnAddNextElement.setBounds(800,620,100,50);
        
        //Set up Button to Close the application
        btnClose = new JButton("Exit");
        btnClose.setBounds(1040,620,100,50); //x-xoord, y-coord, width, height
        btnClose.setBackground(DARK_RED);
        btnClose.setForeground(Color.white);
        
        //Add ActionListeners to components that need to perform an action
        btnLoadOrderList.addActionListener(this);
        btnClose.addActionListener(this);
        btnAddNextElement.addActionListener(this);
        
        //Add components to the JFrame
        this.add(scrollElementsListProcessed);
        this.add(scrollElementsListUnProcessed);
        this.add(displayListOfOrders);
        this.add(displayWareHouse);
        this.add(lblWareHouse);
        this.add(btnLoadOrderList);
        this.add(displayCurrentOrder);
        this.add(lblCurrentOrder);
        this.add(lblListOfOrders);
        this.add(lblProcessedOrders);
        this.add(lblUnProcessedOrders);
        this.add(displayProcessedOrdersHeader);
        this.add(displayUnProcessedOrdersHeader);
        this.add(btnAddNextElement);
        this.add(btnClose);
    }
    
    /**
     * this method is responsible for Action perfomed based on the button clicked
     * such as text area, labels and buttons
     * @param ActionEvent This indicates that a component-defined action occurred
     * @return Nothing
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnLoadOrderList){
            //Display the text elements in the list box 
            populateHeadsetList();
        }
        else if(e.getSource() == btnAddNextElement){
            //Display next element
            displayNextElement();
        }
        else if(e.getSource() == btnClose){
            //Exit from the system
            System.exit(0);
        }
    }
    
    /**
     * this method populate and display headset list
     * @return Nothing
     */
    public void populateHeadsetList()
    {   
        
        displayListOfOrders.update(displayListOfOrders.getGraphics());
        String result = "Queue No.\tCustomerID\tHeadset ID\tQuantity\n";
        displayListOfOrders.setText(result);
        try{
            //Add the text for the new element to the textElements list
            int count = 1;
            for(int i =0; i < listOfOrder.getNumberOfEntries(); i++)
            {
                //get the order at the current position and pass it to a string
                String currentElement = listOfOrder.get(i)+"\n";
                //Use the above string as a parameter to update the text area of the list of orders
                //Basically populating the text area with order list. 
                displayListOfOrders.append(currentElement);
                
                //The below code is a condition to ensure that customer ID is accurate
                boolean validateOrderID = currentElement.contains("null");
                if (validateOrderID == true && i <= listOfOrder.getNumberOfEntries()){
                    throw new Exceptions("\nError: Please ensure that CustomerID on row "+count+" in Custs.csv file is meeting the requirments");
                }
                count++;
            }
        }
        catch (Exceptions error){
            JOptionPane.showMessageDialog (null, error.toString());
            btnAddNextElement.setEnabled(false);
            btnLoadOrderList.setEnabled(false);
            displayListOfOrders.setText("");
        }
        
        //Populate the items from the warehouse (They are being set to 0 since we would only need to display the list without updating it)
        int updatedQTY = 0;
        String HSTID = "";
        populateWarhouse(updatedQTY, HSTID);
        //Refresh the JFrame so that new elements in the list are visible
        this.repaint();
    }
    
    /**
     * this method is responsible for Updating the order list of text area when an order is processed
     * @return Nothing
     */
    /**
     * this method is responsible for updating text area
     * @return Nothing
     */
    public void updatingTextArea(){
        String listOfOrders = "Queue No.\tCustomerID\tHeadset ID\tQuantity\n";
        for(int i =0; i <= listOfOrder.getNumberOfEntries(); i++)
        {
            //get the order at the current position and pass it to a string
            String currentElement = listOfOrder.get(i)+"\n";
            listOfOrders += currentElement;
            //Use the above string as a parameter to update the text area of list of orders
            displayListOfOrders.setText(listOfOrders);
            
            /* Show message box if all orders processed
             * Disable the next and load buttons
             * clear the current order and list of orders
            */
            if(i == listOfOrder.getNumberOfEntries()){
                JOptionPane.showMessageDialog(this,"All orders processed");
                btnAddNextElement.setEnabled(false);
                btnLoadOrderList.setEnabled(false);
                displayCurrentOrder.setText("");
                displayListOfOrders.setText("");
            }
        }
    }
    
    /**
     * this method is responsible for Populating data into the warehouse
     * @param updatedQTY First parameter which carries the updated quantity
     * @param HSTID Second Parameter which carries the headset ID
     * @return Nothing
     */
    private void populateWarhouse(int updatedQTY, String HSTID){
        String wareHouseHeader = "Headset ID\tBrand\t\tModel\t\tQuantity\n";
        String HeadsetDetails = "";
        int count = 1;
        int listOfHedset = 10;
        try{
            for(int i =0; i < listOfHedset; i++){
                /*The below else if statment is ensuring that the warehouse is updated when an order is successsfully processed.
                 * thye confition is that if the parameter ID is equal to the current ID, then display the updated quantity
                */
                Headset newHST = listOfHeadset.getCurrentHeadset(i);
                if(HSTID == newHST.getID()){
                     HeadsetDetails = ""+newHST.getID()+"\t\t"+newHST.getBrand()+"\t\t"+newHST.getModel()+"\t\t"+updatedQTY+"\n";
                }
                else{
                     HeadsetDetails = ""+newHST.getID()+"\t\t"+newHST.getBrand()+"\t\t"+newHST.getModel()+"\t\t"+newHST.getQuantity()+"\n";
                }
                
                // Data validation for headset ID
                if (newHST.getID() == null && i <= listOfHeadset.getNumberOfEntries()){
                    throw new  Exceptions("\nError: Please ensure that Headset ID on row "+count+ " in Headsets.csv file is meeting the requirments");
                }
                wareHouseHeader += HeadsetDetails;
                displayWareHouse.setText(wareHouseHeader); 
                count++;
            }
        }
        catch (Exceptions error){
            JOptionPane.showMessageDialog (null, error.toString());
            btnAddNextElement.setEnabled(false);
            btnLoadOrderList.setEnabled(false);
            displayWareHouse.setText("");
        }
    }
    
    /**
     * this method is responsible for Displaying next order(process one order) 
     * @return Nothing
     */
    public void displayNextElement(){
        String subs = "";
        String orderToProcess = "";
        String currOrder = "";
        if(displayListOfOrders.getText().isEmpty()){
            //When next button is pressed and the order list has not been populated yet.
            JOptionPane.showMessageDialog(this,"Please first load the list of orders!");
        }
        else{
            String orderHeader = "\tQueue No.\tCustomerID\tHeadset ID\tQuantity\n";
            
            //Initialise a worker object and pass the list of orders, list of headsets as well as worker number and speed as a parameter
            Worker worker_1 = new Worker(listOfOrder, listOfHeadset, 3, 9);
            orderToProcess = worker_1.processOneCustomer();
            
            //diaplay number of employee on the GUI
            int workerNumber = worker_1.getNum();
            lblCurrentOrder.setText("Current Order - Employee No: "+workerNumber);
            int updatedQTY = worker_1.getupdateQTY();
            String HSTID = worker_1.getCurrentCust().getHeadsetID();
            
            /*The below condition is to differentiate orders with price and those without price(i.e. failed orders)
             * If orderToProcess contains "." this is a successfully processed order
             * and on the current order to display, the price must be removed as per the assignment requirment.
             * The the price will be displayed once we populate the above order in the processed order text area
            */
            if(orderToProcess.contains(".")){
                // get order to process and use substreing to remove the price at the end of the string
                subs = orderToProcess;
                subs = subs.substring(0, subs.length()-7);
                
                //add the order to the header so that every time we process an order, the header will remain on the top
                orderHeader += subs;
                
                //Dislay the result in the current order text area
                displayCurrentOrder.setText(orderHeader);
                
                //Display processed order
                orderHeader = " Queue No.\tCustomerID\tHeadset ID\tQuantity\tPrice\n";
                displayProcessedOrdersHeader.setText(orderHeader);
                
                // The below code is moving the orders to the left by removig from the same side
                orderToProcess = orderToProcess.substring(1, orderToProcess.length());
                displayProcessedOrders.append(orderToProcess+"\n");
                this.repaint();
                //Update the warehouse
                populateWarhouse(updatedQTY, HSTID);
            }
            else{
                //Display currentOrder(we do no need to accout for the substring since by default the order comes without value) 
                currOrder = orderToProcess;
                orderHeader += currOrder;
                displayCurrentOrder.setText(orderHeader);
                
                //Display unprocessed orders
                String UnprocessedOrderHeader = "\tQueue No.\tCustomerID\tHeadset ID\tQuantity\n";
                displayUnProcessedOrdersHeader.setText(UnprocessedOrderHeader);
                
                displayUnProcessedOrders.append(orderToProcess);
                this.repaint();
            }
            
            //Refrersh the text area
            this.repaint();
            
            //call a method to update the list of orders
            updatingTextArea();
        }
    }
}
