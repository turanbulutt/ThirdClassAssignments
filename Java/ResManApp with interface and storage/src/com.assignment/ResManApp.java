//Ahmet Turan Bulut 2315174
//main class of the code,

package com.assignment;

import com.assignment.support.*;
import com.assignment.core.*;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class ResManApp {
    // Some variables to keep track of numbers, staffs, and input shortcut
    static java.util.ArrayList<Staff> myStaffs = new java.util.ArrayList<>();
    static java.util.ArrayList<Customer> myCustomers = new java.util.ArrayList<>();
    /*static PopulateData populate;

    //populating data from populate data method
    static {
        try {
            populate = new PopulateData(myCustomers,myStaffs);
        } catch (ParseException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    static Scanner input = new Scanner(System.in);

    /**
     *
     */
    public ResManApp() {
    }

    /**
     * @param argv commandline arguments
     */
    public static void main(String[] argv) throws IOException, ClassNotFoundException {
        //exception handling
        readData(myCustomers,myStaffs);
        try {
            Menu();
        } catch (InputMismatchException e) {
            System.out.println("\nYou entered wrong type please be careful next time!");
        } catch (ParseException e) {
            System.out.println("\nYou entered wrong date type please be careful!\nUse d/m/y format");
        }


    }

    /**
     * @param myCustomers all customers
     * @param myStaffs all staffs
     * @throws IOException exception handling
     * @throws ClassNotFoundException exception handling
     */
    private static void readData(ArrayList<Customer> myCustomers, ArrayList<Staff> myStaffs) throws IOException, ClassNotFoundException {

        //reading seniors in bin file
        ArrayList<Senior> seniors;
        ObjectInputStream inputStreamSeniors = new ObjectInputStream(new FileInputStream("seniors.bin"));
        seniors = (ArrayList<Senior>) inputStreamSeniors.readObject();
        inputStreamSeniors.close();
        myStaffs.addAll(seniors);

        //reading juniors in bin file
        ArrayList<Junior> juniors;
        ObjectInputStream inputStreamJuniors = new ObjectInputStream(new FileInputStream("juniors.bin"));
        juniors = (ArrayList<Junior>) inputStreamJuniors.readObject();
        inputStreamJuniors.close();
        myStaffs.addAll(juniors);

        //reading customers in bin file
        ArrayList<Customer> customers;
        ObjectInputStream inputStreamCustomers = new ObjectInputStream(new FileInputStream("customers.bin"));
        customers = (ArrayList<Customer>) inputStreamCustomers.readObject();
        inputStreamCustomers.close();
        myCustomers.addAll(customers);
    }

    /**
     *
     */
    public static void Menu() throws InputMismatchException, ParseException {
        int choose = 1, ssn;
        //main menu of the program
        JFrame frame = new JFrame("Res Man App");

        //creating label
        JLabel first = new JLabel("1-Add Staff");
        //creating bounds
        first.setBounds(10, 10, 100, 30);
        //adding label to frame
        frame.add(first);

        JLabel second = new JLabel("2-Delete Staff");
        second.setBounds(10, 40, 100, 30);
        frame.add(second);

        JLabel third = new JLabel("3-List Staff Details");
        third.setBounds(10, 70, 300, 30);
        frame.add(third);

        JLabel fourth = new JLabel("4-Add Customer");
        fourth.setBounds(10, 100, 100, 30);
        frame.add(fourth);

        JLabel fifth = new JLabel("5-Delete Customer");
        fifth.setBounds(10, 130, 300, 30);
        frame.add(fifth);

        JLabel six = new JLabel("6-Add Booking");
        six.setBounds(10, 160, 100, 30);
        frame.add(six);

        JLabel seven = new JLabel("7-Add Order");
        seven.setBounds(10, 190, 100, 30);
        frame.add(seven);

        JLabel eight = new JLabel("8-Get Customer Details");
        eight.setBounds(10, 210, 400, 30);
        frame.add(eight);

        JLabel nine = new JLabel("9-Get Customer Bookings");
        nine.setBounds(10, 240, 400, 30);
        frame.add(nine);

        JLabel ten = new JLabel("10-Get Customer Orders");
        ten.setBounds(10, 270, 400, 30);
        frame.add(ten);

        JLabel eleven = new JLabel("11-List Staffs");
        eleven.setBounds(10, 300, 100, 30);
        frame.add(eleven);

        JLabel twelve = new JLabel("12-List Customers");
        twelve.setBounds(10, 330, 400, 30);
        frame.add(twelve);

        JLabel thirteen = new JLabel("13-Get Customer Last Booking");
        thirteen.setBounds(10, 360, 400, 30);
        frame.add(thirteen);

        JLabel fourteen = new JLabel("14-List All Orders");
        fourteen.setBounds(10, 390, 100, 30);
        frame.add(fourteen);

        JLabel fifteen = new JLabel("15-List All Staff Salary");
        fifteen.setBounds(10, 420, 400, 30);
        frame.add(fifteen);

        JLabel sixteen = new JLabel("16-Add Order Of Booking");
        sixteen.setBounds(10, 450, 400, 30);
        frame.add(sixteen);

        JLabel seventeen = new JLabel("17-Exit");
        seventeen.setBounds(10, 480, 100, 30);
        frame.add(seventeen);

        JLabel chooseOpt = new JLabel("Option: ");
        chooseOpt.setBounds(10, 510, 100, 30);
        JFormattedTextField chooseField = new JFormattedTextField();
        chooseField.setBounds(60, 510, 100, 30);
        frame.add(chooseField);
        frame.add(chooseOpt);

        JLabel ssnOpt = new JLabel("SSN(only options 2,3,5,6,7,8,9,10,13,16): ");
        ssnOpt.setBounds(10, 540, 300, 30);
        JFormattedTextField ssnText = new JFormattedTextField();
        ssnText.setBounds(240, 540, 100, 30);
        frame.add(ssnText);
        frame.add(ssnOpt);


        JButton submit = new JButton("Submit");
        submit.setBounds(70, 600, 100, 30);
        frame.add(submit);

        //i have that dummy node at every new frame because when i don't have that last component of the frame takes up the frame
        JLabel dummy = new JLabel("");
        dummy.setBounds(10, 630, 100, 30);
        frame.add(dummy);


        //i call that method when user press submit button
        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choose = Integer.parseInt(chooseField.getText());
                switch (choose) {
                    case 1 -> {
                        try {
                            addStaff();
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                    }
                    case 2 -> {
                        int ssn = Integer.parseInt(ssnText.getText());
                        deleteStaff(frame, ssn);
                    }
                    case 3 -> {
                        int ssn = Integer.parseInt(ssnText.getText());
                        listStaffDetails(frame, ssn);
                    }
                    case 4 -> {
                        try {
                            addCustomer();
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                    }
                    case 5 -> {
                        int ssn = Integer.parseInt(ssnText.getText());
                        deleteCustomer(frame, ssn);
                    }
                    case 6 -> {
                        try {
                            int ssn = Integer.parseInt(ssnText.getText());
                            addBooking(frame, ssn);
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                    }
                    case 7 -> {
                        try {
                            int ssn = Integer.parseInt(ssnText.getText());
                            addOrder(frame, ssn);
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                    }
                    case 8 -> {
                        int ssn = Integer.parseInt(ssnText.getText());
                        getCustomerDetails(frame, ssn);
                    }
                    case 9 -> {
                        int ssn = Integer.parseInt(ssnText.getText());
                        getCustomerBooking(frame, ssn);
                    }
                    case 10 -> {
                        int ssn = Integer.parseInt(ssnText.getText());
                        listCustomerOrders(frame, ssn);
                    }
                    case 11 -> listStaff();
                    case 12 -> listCustomer();
                    case 13 -> {
                        int ssn = Integer.parseInt(ssnText.getText());
                        getCustomerLastBooking(frame, ssn);
                    }
                    case 14 -> listAllOrders();
                    case 15 -> listAllStaffSalary();
                    case 16 -> {
                        try {
                            int ssn = Integer.parseInt(ssnText.getText());
                            addOrderOfBooking(ssn);
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                    }
                    case 17 -> {
                        try {
                            writeData(frame);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    default -> System.out.println("Please enter a number in the range 1-17");
                }
            }
        });


        //Set the properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    /**
     * @param frame main frame for close the app
     * @throws IOException exception handling
     */
    private static void writeData(JFrame frame) throws IOException {

        //first seperating staffs according to junior or senior
        java.util.ArrayList<Junior> juniors=new ArrayList<>();
        for (Staff staff:myStaffs)
            if (staff instanceof Junior)
                juniors.add((Junior) staff);

        java.util.ArrayList<Senior> seniors=new ArrayList<>();
        for (Staff staff:myStaffs)
            if (staff instanceof Senior)
                seniors.add((Senior) staff);

            //temp array for customer
        java.util.ArrayList<Customer> customers=new ArrayList<>();
        for (Customer customer:myCustomers)
            customers.add((Customer) customer);

        //writing to file seperatly in order to read easily
        ObjectOutputStream outputStreamSeniors=new ObjectOutputStream(new FileOutputStream("seniors.bin"));
        outputStreamSeniors.writeObject(seniors);
        outputStreamSeniors.close();

        ObjectOutputStream outputStreamJuniors=new ObjectOutputStream(new FileOutputStream("juniors.bin"));
        outputStreamJuniors.writeObject(juniors);
        outputStreamJuniors.close();

        ObjectOutputStream outputStreamCustomers=new ObjectOutputStream(new FileOutputStream("customers.bin"));
        outputStreamCustomers.writeObject(customers);
        outputStreamCustomers.close();

        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * @param ssn of customer to add booking
     * @throws ParseException         exception handling
     * @throws InputMismatchException exception handling
     *                                adding order and taking datas from user for order
     */
    private static void addOrderOfBooking(int ssn) throws ParseException, InputMismatchException {
        int check = 0;
        for (Customer customer : myCustomers)
            if (customer.GetSSN() == ssn) {
                check = 1;
                //new frame
                JFrame frame = new JFrame("Add Order Of Booking");

                //showing user to what you need to write in text field
                JLabel bookingDate = new JLabel("Booking Date:");
                //setting location of component
                bookingDate.setBounds(10, 10, 100, 30);
                JFormattedTextField bookingDateText = new JFormattedTextField();
                bookingDateText.setBounds(110, 10, 100, 30);
                //adding components to frame
                frame.add(bookingDate);
                frame.add(bookingDateText);

                JLabel orderID = new JLabel("Order ID:");
                orderID.setBounds(10, 40, 100, 30);
                JFormattedTextField orderIDText = new JFormattedTextField();
                orderIDText.setBounds(110, 40, 100, 30);
                frame.add(orderID);
                frame.add(orderIDText);

                JLabel orderDate = new JLabel("Order Date:");
                orderDate.setBounds(10, 70, 100, 30);
                JFormattedTextField orderDateText = new JFormattedTextField();
                orderDateText.setBounds(110, 70, 100, 30);
                frame.add(orderDateText);
                frame.add(orderDate);

                JLabel details = new JLabel("Details:");
                details.setBounds(10, 100, 100, 30);
                JFormattedTextField detailsText = new JFormattedTextField();
                detailsText.setBounds(60, 100, 100, 30);
                frame.add(detailsText);
                frame.add(details);

                JLabel extra = new JLabel("Extra Notes:");
                extra.setBounds(10, 130, 100, 30);
                JFormattedTextField extraText = new JFormattedTextField();
                extraText.setBounds(110, 130, 100, 30);
                frame.add(extra);
                frame.add(extraText);

                JLabel tableNumber = new JLabel("Table Number:");
                tableNumber.setBounds(10, 160, 100, 30);
                JFormattedTextField tableNumberText = new JFormattedTextField();
                tableNumberText.setBounds(110, 160, 100, 30);
                frame.add(tableNumberText);
                frame.add(tableNumber);

                JButton submit = new JButton("Submit");
                submit.setBounds(70, 200, 100, 30);
                frame.add(submit);

                JLabel dummy = new JLabel("");
                dummy.setBounds(10, 220, 100, 30);
                frame.add(dummy);

                submit.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //changing format of the components according to what i need to create customer
                        //because all of them are string at the beginning and i need int,date...
                        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");

                        Date bookingDate = null;
                        try {
                            bookingDate = dateFormat.parse(bookingDateText.getText());
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }

                        Date orderDate = null;
                        try {
                            orderDate = dateFormat.parse(orderDateText.getText());
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }

                        int theBooking = customer.checkBookingDate(bookingDate);

                        int id = Integer.parseInt(orderIDText.getText());
                        int tableNumber = Integer.parseInt(tableNumberText.getText());

                        customer.addOrder(id, orderDate, detailsText.getText(), extraText.getText(), tableNumber, theBooking);

                        int index=getIndex(customer.GetSSN());

                        /*
                        //writing data to file
                        try {
                            FileWriter file=new FileWriter("data.txt",true);
                            file.write("InRestr;"+index+";"+orderDateText.getText()+";"+detailsText.getText()+";"+extraText.getText()+";"+tableNumber+";"+theBooking+"\n");
                            file.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }*/


                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

                    }
                });

                frame.setSize(700, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        if (check == 0)
            System.out.print("We couldn't find that customer!");
    }

    /**
     * @param ssn of customer or staff
     * @return index of customer or staff
     */
    private static int getIndex(int ssn) {
        for(int x=0;x<myCustomers.size();x++)
        {
            if (myCustomers.get(x).GetSSN()==ssn)
                return x;
        }

        for(int x=0;x<myStaffs.size();x++)
        {
            if (myStaffs.get(x).GetSSN()==ssn)
                return x;
        }
        return -1;
    }

    /**
     * getting salary according the staff type
     */
    private static void listAllStaffSalary() {
        JFrame frame = new JFrame("Staff Salaries");
        int y = 10;
        for (Staff staff : myStaffs) {
            //acording to staff class i decide which get salary will run
            if (staff.getClass().arrayType().toString().equals("class [LJunior;"))
                staff.getSalary(frame, y);
            else
                staff.getSalary(frame, y);
            y += 30;
        }
        JLabel dummy = new JLabel("");
        dummy.setBounds(10, 220, 100, 30);
        frame.add(dummy);

        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * listing all orders
     */
    private static void listAllOrders() {
        JFrame frame = new JFrame("All Orders");
        int y = 10;
        for (Customer myCustomer : myCustomers)
            //checking every customer if it is ssn true. It also checks every booking that customer make to be sure
            if (myCustomer.getOrders().size() > 0)
                y = myCustomer.listOrders(frame, y);

        JLabel dummy = new JLabel("");
        dummy.setBounds(10, y, 100, 30);
        frame.add(dummy);

        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * @param myFrame main frame for print error
     * @param ssn of customer
     * @throws InputMismatchException error handling
     * @throws ParseException error handling
     */
    private static void addOrder(JFrame myFrame, int ssn) throws InputMismatchException, ParseException {
        int check = 0;
        for (Customer myCustomer : myCustomers) {
            //check every customer if it is ssn true and if it is true get all bookings
            if (ssn == myCustomer.GetSSN()) {
                check = 1;


                JFrame frame = new JFrame("Add Order");

                JRadioButton online = new JRadioButton("Online");
                online.setBounds(10, 10, 100, 30);
                JRadioButton inRestr = new JRadioButton("InRestr");
                inRestr.setBounds(110, 10, 100, 30);
                ButtonGroup bg = new ButtonGroup();
                bg.add(online);
                bg.add(inRestr);
                frame.add(online);
                frame.add(inRestr);

                JLabel orderID = new JLabel("Order ID:");
                orderID.setBounds(10, 40, 100, 30);
                JFormattedTextField orderIDText = new JFormattedTextField();
                orderIDText.setBounds(110, 40, 100, 30);
                frame.add(orderID);
                frame.add(orderIDText);

                JLabel orderDate = new JLabel("Order Date:");
                orderDate.setBounds(10, 70, 100, 30);
                JFormattedTextField orderDateText = new JFormattedTextField();
                orderDateText.setBounds(110, 70, 100, 30);
                frame.add(orderDateText);
                frame.add(orderDate);

                JLabel details = new JLabel("Details:");
                details.setBounds(10, 100, 100, 30);
                JFormattedTextField detailsText = new JFormattedTextField();
                detailsText.setBounds(60, 100, 100, 30);
                frame.add(detailsText);
                frame.add(details);

                JLabel extra = new JLabel("Extra Notes:");
                extra.setBounds(10, 130, 100, 30);
                JFormattedTextField extraText = new JFormattedTextField();
                extraText.setBounds(110, 130, 100, 30);
                frame.add(extra);
                frame.add(extraText);

                JLabel onlineWarning = new JLabel("Please fill below if order is ONLINE");
                onlineWarning.setBounds(70, 170, 300, 30);
                frame.add(onlineWarning);

                JRadioButton cash = new JRadioButton("cash");
                cash.setBounds(10, 200, 100, 30);
                JRadioButton creditCard = new JRadioButton("Credit Card");
                creditCard.setBounds(110, 200, 100, 30);
                JRadioButton bitcoin = new JRadioButton("Bitcoin");
                bitcoin.setBounds(210, 200, 100, 30);
                ButtonGroup bg2 = new ButtonGroup();
                bg2.add(cash);
                bg2.add(creditCard);
                bg2.add(bitcoin);
                frame.add(cash);
                frame.add(creditCard);
                frame.add(bitcoin);

                JLabel inRestrWarning = new JLabel("Please fill below if order is InRestr");
                inRestrWarning.setBounds(70, 230, 300, 30);
                frame.add(inRestrWarning);

                JLabel tableNumber = new JLabel("Table Number:");
                tableNumber.setBounds(10, 260, 100, 30);
                JFormattedTextField tableNumberText = new JFormattedTextField();
                tableNumberText.setBounds(110, 260, 100, 30);
                frame.add(tableNumberText);
                frame.add(tableNumber);

                JLabel bookingDate = new JLabel("Booking Date:");
                bookingDate.setBounds(10, 290, 100, 30);
                JFormattedTextField bookingDateText = new JFormattedTextField();
                bookingDateText.setBounds(110, 290, 100, 30);
                frame.add(bookingDate);
                frame.add(bookingDateText);

                JButton submit = new JButton("Submit");
                submit.setBounds(70, 320, 100, 30);
                frame.add(submit);

                JLabel dummy = new JLabel("");
                dummy.setBounds(10, 220, 100, 30);
                frame.add(dummy);

                submit.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
                        Date orderDate = null;
                        try {
                            orderDate = dateFormat.parse(orderDateText.getText());
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                        int id = Integer.parseInt(orderIDText.getText());

                        if (online.isSelected() && cash.isSelected()) {
                            Junior myJunior= getRandomJunior();
                            myCustomer.addOrder(id, orderDate, detailsText.getText(), extraText.getText(), 1, myJunior);
                            int index=getIndex(myCustomer.GetSSN());
                            int juniorIndex=getIndex(myJunior.GetSSN());/*
                            try {
                                FileWriter file=new FileWriter("data.txt",true);
                                file.write("Online;"+index+";"+orderDateText.getText()+";"+detailsText.getText()+";"+extraText.getText()+";"+1+";"+juniorIndex+"\n");
                                file.close();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }*/

                        } else if (online.isSelected() && creditCard.isSelected()) {
                            Junior myJunior= getRandomJunior();
                            myCustomer.addOrder(id, orderDate, details.getText(), extraText.getText(), 2, myJunior);
                            int index=getIndex(myCustomer.GetSSN());
                            int juniorIndex=getIndex(myJunior.GetSSN());/*
                            try {
                                FileWriter file=new FileWriter("data.txt",true);
                                file.write("Online;"+index+";"+orderDateText.getText()+";"+detailsText.getText()+";"+extraText.getText()+";"+2+";"+juniorIndex+"\n");
                                file.close();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }*/

                        } else if (online.isSelected() && bitcoin.isSelected()) {
                            Junior myJunior= getRandomJunior();
                            myCustomer.addOrder(id, orderDate, details.getText(), extraText.getText(), 3, myJunior);
                            int index=getIndex(myCustomer.GetSSN());
                            int juniorIndex=getIndex(myJunior.GetSSN());/*
                            try {
                                FileWriter file=new FileWriter("data.txt",true);
                                file.write("Online;"+index+";"+orderDateText.getText()+";"+detailsText.getText()+";"+extraText.getText()+";"+3+";"+juniorIndex+"\n");
                                file.close();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }*/

                        } else if (inRestr.isSelected()) {
                            Date bookingDate = null;
                            try {
                                bookingDate = dateFormat.parse(bookingDateText.getText());
                            } catch (ParseException parseException) {
                                parseException.printStackTrace();
                            }
                            int tableNumber = Integer.parseInt(tableNumberText.getText());
                            int theBooking = myCustomer.checkBookingDate(bookingDate);
                            myCustomer.addOrder(id, orderDate, detailsText.getText(), extraText.getText(), tableNumber, theBooking);
                            int index=getIndex(myCustomer.GetSSN());/*

                            try {
                                FileWriter file=new FileWriter("data.txt",true);
                                file.write("InRestr;"+index+";"+orderDateText.getText()+";"+detailsText.getText()+";"+extraText.getText()+";"+tableNumber+";"+theBooking+"\n");
                                file.close();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }*/
                        }
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                });

                //Set the properties
                frame.setSize(500, 700);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        }
        if (check == 0) {
            printErrorMessage(myFrame, "SSN", "customer");
        }
    }


    /**
     * @return getting random junior for online order
     */
    private static Junior getRandomJunior() {
        java.util.ArrayList<Junior> juniors = new ArrayList<>();
        for (Staff myStaff : myStaffs) {
            //print for every staff
            if (myStaff.getClass().arrayType().toString().equals("class [LJunior;"))
                juniors.add((Junior) myStaff);
        }
        int random = new Random().nextInt(juniors.size());
        return juniors.get(random);

    }


    /**
     * @param myFrame main frame for print error
     * @param ssn of customer
     */
    private static void getCustomerLastBooking(JFrame myFrame, int ssn) {
        int check = 0;
        for (Customer myCustomer : myCustomers) {
            //check every customer if it is ssn true and if it is true get all bookings
            if (ssn == myCustomer.GetSSN()) {
                check = 1;
                myCustomer.ShowLastBooking();
            }
        }
        if (check == 0) {
            printErrorMessage(myFrame, "SSN", "customer");
        }
    }


    /**
     * @param myDate date for converting to string
     * @return string version of wanted date
     */
    private static String DateString(Date myDate) {
        //printing date string month day year respectively
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/y");
        return formatter.format(myDate);
    }


    /**
     * @param myFrame main frame
     * @param str error type
     * @param kind decide staff or customer
     */
    private static void printErrorMessage(JFrame myFrame, String str, String kind) {
        //printing error in short way
        JOptionPane.showMessageDialog(myFrame, str + " of the " + kind + " that you enter couldn't find please try again");
    }


    /**
     * @param frame for print staff
     * @param myJunior junior that will print
     * @param y coordinate of components
     * @return last components y coordinate plus 30
     */
    private static int printStaff(JFrame frame, Junior myJunior, int y) {
        JLabel ssn = new JLabel("SSN: " + myJunior.GetSSN());
        ssn.setBounds(10, y, 100, 30);
        frame.add(ssn);

        JLabel name = new JLabel("Name: " + myJunior.GetName());
        name.setBounds(10, y + 30, 100, 30);
        frame.add(name);

        JLabel myClass = new JLabel(myJunior.getClass().toString());
        name.setBounds(10, y + 60, 100, 30);
        frame.add(myClass);

        if (myJunior.GetGender() == 'M' || myJunior.GetGender() == 'm') {
            JLabel gender = new JLabel("Gender: Male");
            gender.setBounds(10, y + 90, 100, 30);
            frame.add(gender);
        } else {
            JLabel gender = new JLabel("Gender: Female");
            gender.setBounds(10, y + 90, 100, 30);
            frame.add(gender);
        }

        JLabel birthDate = new JLabel("Date of birth: " + DateString(myJunior.GetBirthDate()));
        birthDate.setBounds(10, y + 120, 400, 30);
        frame.add(birthDate);

        JLabel startDate = new JLabel("Start Date: " + DateString(myJunior.GetStartDate()));
        startDate.setBounds(10, y + 150, 400, 30);
        frame.add(startDate);

        JLabel endDate = new JLabel("End Date: " + DateString(myJunior.getExpectedEndDate()));
        endDate.setBounds(10, y + 180, 400, 30);
        frame.add(endDate);

        JLabel monthlySalary = new JLabel("Monthly salary: " + myJunior.getMonthlySalary());
        monthlySalary.setBounds(10, y + 210, 400, 30);
        frame.add(monthlySalary);

        return y + 240;
    }

    /**
     * @param frame for print staff
     * @param mySenior senior that will print
     * @param y coordinate of components
     * @return last components y coordinate plus 30
     */
    private static int printStaff(JFrame frame, Senior mySenior, int y) {

        JLabel ssn = new JLabel("SSN: " + mySenior.GetSSN());
        ssn.setBounds(10, y, 100, 30);
        frame.add(ssn);

        JLabel name = new JLabel("Name: " + mySenior.GetName());
        name.setBounds(10, y + 30, 100, 30);
        frame.add(name);

        JLabel myClass = new JLabel(mySenior.getClass().toString());
        name.setBounds(10, y + 60, 100, 30);
        frame.add(myClass);

        if (mySenior.GetGender() == 'M' || mySenior.GetGender() == 'm') {
            JLabel gender = new JLabel("Gender: Male");
            gender.setBounds(10, y + 90, 100, 30);
            frame.add(gender);
        } else {
            JLabel gender = new JLabel("Gender: Female");
            gender.setBounds(10, y + 90, 100, 30);
            frame.add(gender);
        }

        JLabel birthDate = new JLabel("Date of birth: " + DateString(mySenior.GetBirthDate()));
        birthDate.setBounds(10, y + 120, 400, 30);
        frame.add(birthDate);

        JLabel grossSalary = new JLabel("Gross Salary: " + mySenior.getGrossSalaryYearly());
        grossSalary.setBounds(10, y + 150, 400, 30);
        frame.add(grossSalary);

        int newY = y + 180;
        for (int x = 0; x < mySenior.getResponsibleFrom().size(); x++) {
            JLabel responsible = new JLabel((x + 1) + "-" + mySenior.getResponsibleFrom().get(x).GetName());
            responsible.setBounds(10, newY, 100, 30);
            frame.add(responsible);
            newY += 30;
        }

        return newY;
    }


    /**
     * @param frame for print customer
     * @param x index of customer
     * @param y coordinate of components
     * @return last coomponents y coordinate plus 30
     */
    private static int printCustomer(JFrame frame, int x, int y) {
        JLabel ssn = new JLabel("SSN: " + myCustomers.get(x).GetSSN());
        ssn.setBounds(10, y, 100, 30);
        frame.add(ssn);

        JLabel name = new JLabel("Name: " + myCustomers.get(x).GetName());
        name.setBounds(10, y + 30, 100, 30);
        frame.add(name);

        JLabel myClass = new JLabel(myCustomers.get(x).getClass().toString());
        name.setBounds(10, y + 60, 100, 30);
        frame.add(myClass);

        if (myCustomers.get(x).GetGender() == 'M' || myCustomers.get(x).GetGender() == 'm') {
            JLabel gender = new JLabel("Gender: Male");
            gender.setBounds(10, y + 90, 100, 30);
            frame.add(gender);
        } else {
            JLabel gender = new JLabel("Gender: Female");
            gender.setBounds(10, y + 90, 100, 30);
            frame.add(gender);
        }

        JLabel birthDate = new JLabel("Date of birth: " + DateString(myCustomers.get(x).GetBirthDate()));
        birthDate.setBounds(10, y + 120, 400, 30);
        frame.add(birthDate);

        JLabel regDate = new JLabel("Registration date: " + DateString(myCustomers.get(x).GetRegDate()));
        birthDate.setBounds(10, y + 150, 400, 30);
        frame.add(birthDate);

        JLabel creditCard = new JLabel("Credit Card Details: " + myCustomers.get(x).GetCreditCardDetails());
        creditCard.setBounds(10, y + 180, 400, 30);
        frame.add(creditCard);


        return y + 210;
    }

    /**
     * print all customers
     */
    private static void listCustomer() {
        JFrame frame = new JFrame("Customers");
        int y = 10;
        for (int x = 0; x < myCustomers.size(); x++) {
            //print for every customer
            y = printCustomer(frame, x, y);
        }
        JLabel dummy = new JLabel();
        dummy.setBounds(10, y, 100, 30);
        frame.add(dummy);

        //Set the properties
        frame.setSize(300, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * @throws ClassCastException exception handling
     */
    private static void listStaff() throws ClassCastException {
        JFrame frame = new JFrame("List Staff");
        int y = 10;
        for (Staff myStaff : myStaffs) {
            //print for every staff
            if (myStaff.getClass().arrayType().toString().equals("class [Lcom.assignment.core.Junior;"))
                y = printStaff(frame, (Junior) myStaff, y);
            else
                y = printStaff(frame, (Senior) myStaff, y);
        }

        JLabel dummy = new JLabel();
        dummy.setBounds(10, y, 100, 30);
        frame.add(dummy);

        //Set the properties
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * @param myFrame for print error message
     * @param ssn of customer
     */
    private static void listCustomerOrders(JFrame myFrame, int ssn) {
        int check = 0;
        JFrame frame = new JFrame("Customer Orders");
        int y = 10;
        for (Customer myCustomer : myCustomers) {
            //checking every customer if it is ssn true. It also checks every booking that customer make to be sure
            if (myCustomer.getOrders().size() > 0 && myCustomer.GetSSN() == ssn) {
                check = 1;
                myCustomer.listOrders(frame, y);
            }
        }
        if (check == 0) {
            printErrorMessage(myFrame, "SSN or the booking date", "customer");
        }
        JLabel dummy = new JLabel("");
        dummy.setBounds(10, 220, 100, 30);
        frame.add(dummy);

        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * @param myFrame for print error message
     * @param ssn of customer
     */
    private static void getCustomerBooking(JFrame myFrame, int ssn) {
        int check = 0;
        JFrame frame = new JFrame("Add booking");
        for (Customer myCustomer : myCustomers) {
            //check every customer if it is ssn true and if it is true get all bookings
            if (ssn == myCustomer.GetSSN()) {
                check = 1;
                myCustomer.GetBookings(frame);
            }
        }
        if (check == 0) {
            printErrorMessage(myFrame, "SSN", "customer");
        }


        //Set the properties
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * @param myFrame for print error message
     * @param ssn of customer
     */
    private static void getCustomerDetails(JFrame myFrame, int ssn) {
        int check = 0;
        JFrame frame = new JFrame("Customer Details");
        int y = 10;
        for (int x = 0; x < myCustomers.size(); x++) {
            if (ssn == myCustomers.get(x).GetSSN()) {
                check = 1;
                y = printCustomer(frame, x, y);
            }
        }
        if (check == 0) {
            printErrorMessage(myFrame, "SSN", "customer");
        }

        JLabel dummy = new JLabel("");
        dummy.setBounds(10, y, 100, 30);
        frame.add(dummy);
        //Set the properties
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * @param myFrame for print error message
     * @param ssn of customer
     * @throws ParseException exception handling
     * @throws InputMismatchException exception handling
     */
    private static void addBooking(JFrame myFrame, int ssn) throws ParseException, InputMismatchException {
        Customer theCustomer = null;
        for (Customer myCustomer : myCustomers) {
            if (ssn == myCustomer.GetSSN()) {
                theCustomer = myCustomer;
            }
        }
        if (theCustomer != null) {

            JFrame frame = new JFrame("Add booking");

            JLabel bookingDate = new JLabel("Booking Date: ");
            bookingDate.setBounds(10, 10, 100, 30);
            JFormattedTextField bookingDateText = new JFormattedTextField();
            bookingDateText.setBounds(110, 10, 100, 30);
            frame.add(bookingDateText);
            frame.add(bookingDate);

            JLabel bookingID = new JLabel("Booking ID: ");
            bookingID.setBounds(10, 40, 100, 30);
            JFormattedTextField bookingIDText = new JFormattedTextField();
            bookingIDText.setBounds(110, 40, 100, 30);
            frame.add(bookingIDText);
            frame.add(bookingID);

            JButton submit = new JButton("Submit");
            submit.setBounds(70, 70, 100, 30);
            frame.add(submit);

            JLabel dummy = new JLabel("");
            dummy.setBounds(10, 120, 100, 30);
            frame.add(dummy);

            Customer finalTheCustomer = theCustomer;
            submit.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
                    Date bookingDate = null;
                    try {
                        bookingDate = dateFormat.parse(bookingDateText.getText());
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    int bookingID = Integer.parseInt(bookingIDText.getText());

                    finalTheCustomer.MakeBooking(bookingDate, bookingID);
                    int index=getIndex(finalTheCustomer.GetSSN());/*

                    try {
                        FileWriter file=new FileWriter("data.txt",true);
                        file.write("Booking;"+index+";"+bookingDateText.getText()+";"+bookingIDText.getText()+"\n");
                        file.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }*/


                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            });

            //Set the properties
            frame.setSize(300, 150);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);


        } else {
            printErrorMessage(myFrame, "SSN", "customer");
        }
    }

    /**
     * @param myFrame for print error message
     * @param ssn of customer
     */
    private static void deleteCustomer(JFrame myFrame, int ssn) {
        int check = 0;
        for (int x = 0; x < myCustomers.size(); x++) {
            //find true customer and delete after fixing gabs in the array
            if (ssn == myCustomers.get(x).GetSSN()) {
                int y;
                for (y = x; y < myCustomers.size() - 1; y++)
                    myCustomers.set(y, myCustomers.get(y + 1));
                myCustomers.remove(y);
                check = 1;
                JOptionPane.showMessageDialog(myFrame, "Customer deleted!");
            }
        }
        if (check == 0) {
            printErrorMessage(myFrame, "SSN", "customer");
        }
    }


    /**
     * @throws InputMismatchException exception handling
     * @throws ParseException exception handling
     */
    private static void addCustomer() throws InputMismatchException, ParseException {

        JFrame frame = new JFrame("Add Customer");

        JLabel ssn = new JLabel("SSN: ");
        ssn.setBounds(10, 10, 100, 30);
        JFormattedTextField ssnText = new JFormattedTextField();
        ssnText.setBounds(60, 10, 100, 30);
        frame.add(ssn);
        frame.add(ssnText);

        JLabel name = new JLabel("Name: ");
        name.setBounds(10, 40, 100, 30);
        JFormattedTextField nameText = new JFormattedTextField();
        nameText.setBounds(60, 40, 100, 30);
        frame.add(name);
        frame.add(nameText);

        JRadioButton male = new JRadioButton("Male");
        male.setBounds(10, 70, 100, 30);
        JRadioButton female = new JRadioButton("Female");
        female.setBounds(110, 70, 100, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(female);
        bg.add(male);
        frame.add(female);
        frame.add(male);

        JLabel birthday = new JLabel("Date of Birth: ");
        birthday.setBounds(10, 100, 100, 30);
        JFormattedTextField birthdayText = new JFormattedTextField();
        birthdayText.setBounds(110, 100, 100, 30);
        frame.add(birthday);
        frame.add(birthdayText);

        JLabel regDate = new JLabel("Registration Date: ");
        regDate.setBounds(10, 130, 150, 30);
        JFormattedTextField regDateText = new JFormattedTextField();
        regDateText.setBounds(130, 130, 100, 30);
        frame.add(regDate);
        frame.add(regDateText);

        JLabel creditCard = new JLabel("Card Details: ");
        creditCard.setBounds(10, 160, 100, 30);
        JFormattedTextField creditCardText = new JFormattedTextField();
        creditCardText.setBounds(110, 160, 100, 30);
        frame.add(creditCardText);
        frame.add(creditCard);

        JButton submit = new JButton("Submit");
        submit.setBounds(70, 190, 100, 30);
        frame.add(submit);

        JLabel dummy = new JLabel("");
        dummy.setBounds(10, 240, 100, 30);
        frame.add(dummy);


        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int ssn = Integer.parseInt(ssnText.getText());

                SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");

                Date dateOfBirth = null;
                try {
                    dateOfBirth = dateFormat.parse(birthdayText.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

                Date customerRegDate = null;
                try {
                    customerRegDate = dateFormat.parse(regDateText.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

                int creditCard = Integer.parseInt(creditCardText.getText());
                if (male.isSelected()) {
                    myCustomers.add(new Customer(ssn, nameText.getText(), 'm', dateOfBirth, customerRegDate, creditCard));/*

                    try {
                        FileWriter file=new FileWriter("data.txt",true);
                        file.write("Customer;"+ssn+";"+nameText.getText()+";m;"+birthdayText.getText()+";"+regDateText.getText()+";"+creditCardText.getText()+"\n");
                        file.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }*/
                }
                else if (female.isSelected()) {
                    myCustomers.add(new Customer(ssn, nameText.getText(), 'f', dateOfBirth, customerRegDate, creditCard));/*
                    try {
                        FileWriter file=new FileWriter("data.txt",true);
                        file.write("Customer;"+ssn+";"+nameText.getText()+";f;"+birthdayText.getText()+";"+regDateText.getText()+";"+creditCardText.getText()+"\n");
                        file.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }*/
                }
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        //Set the properties
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * @param myFrame for print error message
     * @param ssn of staff
     */
    private static void listStaffDetails(JFrame myFrame, int ssn) {
        int check = 0;
        JFrame frame = new JFrame("Staff Details");
        for (Staff myStaff : myStaffs) {
            if (ssn == myStaff.GetSSN()) {
                if (myStaff.getClass().arrayType().toString().equals("class [Lcom.assignment.core.Junior;"))
                    printStaff(frame, (Junior) myStaff, 10);
                else
                    printStaff(frame, (Senior) myStaff, 10);
                check = 1;
                break;
            }
        }
        if (check == 0) {
            printErrorMessage(myFrame, "SSN", "staff");
        }
        JLabel dummy = new JLabel();
        dummy.setBounds(10, 400, 100, 30);
        frame.add(dummy);
        //Set the properties
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * @param myFrame for print error message
     * @param ssn of staff
     */
    private static void deleteStaff(JFrame myFrame, int ssn) {
        int check = 0;
        for (int x = 0; x < myStaffs.size(); x++) {
            if (ssn == myStaffs.get(x).GetSSN()) {
                int y;
                for (y = x; y < myStaffs.size() - 1; y++)
                    myStaffs.set(y, myStaffs.get(y + 1));
                myStaffs.remove(y);
                check = 1;
                JOptionPane.showMessageDialog(myFrame, "Staff deleted!");
            }
        }
        if (check == 0) {
            printErrorMessage(myFrame, "SSN", "staff");
        }

    }


    /**
     * @throws ParseException exception handling
     * @throws InputMismatchException exception handling
     */
    private static void addStaff() throws ParseException, InputMismatchException {

        JFrame frame = new JFrame("Add Staff");

        JRadioButton senior = new JRadioButton("Senior");
        senior.setBounds(10, 10, 100, 30);
        JRadioButton junior = new JRadioButton("Junior");
        junior.setBounds(110, 10, 100, 30);
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(senior);
        bg2.add(junior);
        frame.add(senior);
        frame.add(junior);

        JLabel ssn = new JLabel("SSN: ");
        ssn.setBounds(10, 40, 100, 30);
        JFormattedTextField ssnText = new JFormattedTextField();
        ssnText.setBounds(60, 40, 100, 30);
        frame.add(ssn);
        frame.add(ssnText);

        JLabel name = new JLabel("Name: ");
        name.setBounds(10, 70, 100, 30);
        JFormattedTextField nameText = new JFormattedTextField();
        nameText.setBounds(60, 70, 100, 30);
        frame.add(name);
        frame.add(nameText);

        JRadioButton male = new JRadioButton("Male");
        male.setBounds(10, 100, 100, 30);
        JRadioButton female = new JRadioButton("Female");
        female.setBounds(110, 100, 100, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(female);
        bg.add(male);
        frame.add(female);
        frame.add(male);

        JLabel birthday = new JLabel("Date of Birth: ");
        birthday.setBounds(10, 130, 100, 30);
        JFormattedTextField birthdayText = new JFormattedTextField();
        birthdayText.setBounds(100, 130, 100, 30);
        frame.add(birthday);
        frame.add(birthdayText);

        JLabel startDate = new JLabel("Start Date: ");
        startDate.setBounds(10, 170, 100, 30);
        JFormattedTextField startDateText = new JFormattedTextField();
        startDateText.setBounds(80, 170, 100, 30);
        frame.add(startDate);
        frame.add(startDateText);

        JLabel seniorWarning = new JLabel("If your staff is SENIOR  please fill below");
        seniorWarning.setBounds(50, 200, 300, 30);
        frame.add(seniorWarning);

        JLabel grossSalary = new JLabel("Gross Salary: ");
        grossSalary.setBounds(10, 230, 100, 30);
        JFormattedTextField grossSalaryText = new JFormattedTextField();
        grossSalaryText.setBounds(110, 230, 100, 30);
        frame.add(grossSalaryText);
        frame.add(grossSalary);

        JLabel juniorWarning = new JLabel("If your staff is JUNIOR  please fill below");
        juniorWarning.setBounds(50, 260, 300, 30);
        frame.add(juniorWarning);

        JLabel monthlySalary = new JLabel("Monthly Salary: ");
        monthlySalary.setBounds(10, 290, 100, 30);
        JFormattedTextField monthlySalaryText = new JFormattedTextField();
        monthlySalaryText.setBounds(110, 290, 100, 30);
        frame.add(monthlySalaryText);
        frame.add(monthlySalary);

        JLabel endDate = new JLabel("End Date: ");
        endDate.setBounds(10, 320, 100, 30);
        JFormattedTextField endDateText = new JFormattedTextField();
        endDateText.setBounds(80, 320, 100, 30);
        frame.add(endDateText);
        frame.add(endDate);

        JButton submit = new JButton("Submit");
        submit.setBounds(70, 370, 100, 30);
        frame.add(submit);

        JLabel dummy = new JLabel("");
        dummy.setBounds(10, 400, 100, 30);
        frame.add(dummy);

        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ssn = Integer.parseInt(ssnText.getText());

                SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");

                Date dateOfBirth = null;
                try {
                    dateOfBirth = dateFormat.parse(birthdayText.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

                Date staffStartDate = null;
                try {
                    staffStartDate = dateFormat.parse(startDateText.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

                if (senior.isSelected() && male.isSelected()) {
                    int grossSalary = Integer.parseInt(grossSalaryText.getText());
                    myStaffs.add(new Senior(ssn, nameText.getText(), 'm', dateOfBirth, staffStartDate, grossSalary));/*

                    try {
                        FileWriter file=new FileWriter("data.txt",true);
                        file.write("Senior;"+ssn+";"+nameText.getText()+";m;"+birthdayText.getText()+";"+startDateText.getText()+";"+grossSalaryText.getText()+"\n");
                        file.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }*/

                } else if (senior.isSelected() && female.isSelected()) {
                    int grossSalary = Integer.parseInt(grossSalaryText.getText());
                    myStaffs.add(new Senior(ssn, nameText.getText(), 'f', dateOfBirth, staffStartDate, grossSalary));/*

                    try {
                        FileWriter file=new FileWriter("data.txt",true);
                        file.write("Senior;"+ssn+";"+nameText.getText()+";f;"+birthdayText.getText()+";"+startDateText.getText()+";"+grossSalaryText.getText()+"\n");
                        file.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }*/
                } else if (junior.isSelected() && male.isSelected()) {
                    int monthSalary = Integer.parseInt(monthlySalaryText.getText());
                    Date juniorEndDate = null;
                    try {
                        juniorEndDate = dateFormat.parse(endDateText.getText());
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    myStaffs.add(new Junior(ssn, nameText.getText(), 'm', dateOfBirth, staffStartDate, monthSalary, juniorEndDate));/*
                    try {
                        FileWriter file=new FileWriter("data.txt",true);
                        file.write("Junior;"+ssn+";"+nameText.getText()+";m;"+birthdayText.getText()+";"+startDateText.getText()+";"+monthlySalaryText.getText()+";"+endDateText.getText()+"\n");
                        file.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }*/
                } else if (junior.isSelected() && female.isSelected()) {
                    int monthSalary = Integer.parseInt(monthlySalaryText.getText());
                    Date juniorEndDate = null;
                    try {
                        juniorEndDate = dateFormat.parse(endDateText.getText());
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }/*
                    myStaffs.add(new Junior(ssn, nameText.getText(), 'f', dateOfBirth, staffStartDate, monthSalary, juniorEndDate));
                    try {
                        FileWriter file=new FileWriter("data.txt",true);
                        file.write("Junior;"+ssn+";"+nameText.getText()+";f;"+birthdayText.getText()+";"+startDateText.getText()+";"+monthlySalaryText.getText()+";"+endDateText.getText()+"\n");
                        file.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }*/
                }
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        //Set the properties
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
