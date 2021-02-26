//Ahmet Turan Bulut 2315174
//customer class

package com.assignment.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.assignment.support.*;

import javax.swing.*;

public class Customer extends Person {
    private Date registrationDate;
    private int creditCardDetails;
    static java.util.ArrayList<Booking> bookings;
    private java.util.ArrayList<Order> orders;

    /**
     * @param SSN              ssn of customer
     * @param Name             name of customer
     * @param Gender           gender of customer
     * @param DateOfBirth      birth day of customer
     * @param RegistrationDate reg day of customer
     */
    public Customer(int SSN, String Name, char Gender, Date DateOfBirth, Date RegistrationDate, int creditCardDetails) {
        this.ssn = SSN;
        this.name = Name;
        this.gender = Gender;
        this.dateOfBirth = DateOfBirth;
        this.registrationDate = RegistrationDate;
        this.bookings = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.creditCardDetails = creditCardDetails;
    }

    /**
     * creating customer with no values
     */
    Customer() {
        ssn = 0;
        name = "Not Provided";
        gender = 'n';
        dateOfBirth = new Date();
        registrationDate = new Date();
        bookings = new ArrayList<>();
    }

    // Getters, get each variable in that class

    /**
     * @return ssn
     */
    public int GetSSN() {
        return ssn;
    }

    /**
     * @return name
     */
    public String GetName() {
        return name;
    }

    /**
     * @return gender
     */
    public char GetGender() {

        return gender;
    }

    /**
     * @return date of birth
     */
    public Date GetBirthDate() {
        return dateOfBirth;
    }

    /**
     * @return reg date
     */
    public Date GetRegDate() {
        return registrationDate;
    }

    /**
     * printing booking details
     */
    public void GetBookings(JFrame frame) {
        //print all the bookings of the customer
        int y=10;
        for (Booking booking : bookings) {
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/y");
            String bookingDate = formatter.format(booking.GetBookingDate());

            JLabel orderDates=new JLabel("Booking Date: "+bookingDate);
            orderDates.setBounds(10,y,300,30);
            frame.add(orderDates);

            JLabel bookingTable=new JLabel("Booking Table: " + booking.GetBookingID());
            bookingTable.setBounds(10,y+30,300,30);
            frame.add(bookingTable);

            System.out.printf("%s %tB %<te, %<tY", "Booking Date: ", booking.GetBookingDate());
            System.out.println("Booking ID: " + booking.GetBookingID());
            y+=60;
        }
        JLabel dummy=new JLabel();
        dummy.setBounds(10,y,100,30);
        frame.add(dummy);
    }

    /**
     * @param bookQueue specify which number of order in the array
     */
    public void GetBookingOrders(int bookQueue) {
        //bookings.get(bookQueue).GetOrders();
    }

    /**
     * @return credit card details
     */
    public int GetCreditCardDetails() {
        return creditCardDetails;
    }

    // Setters, setting variables if user want to change single data

    /**
     * @param SSN ssn
     */
    public void SetSSN(int SSN) {
        ssn = SSN;
    }

    /**
     * @param Name name
     */
    public void SetName(String Name) {
        name = Name;
    }

    /**
     * @param Gender gender
     */
    public void SetGender(char Gender) {
        gender = Gender;
    }

    /**
     * @param DateOfBirth birthday
     */
    public void SetDateOfBirth(Date DateOfBirth) {
        dateOfBirth = DateOfBirth;
    }

    /**
     * @param RegistrationDate reg day
     */
    public void SetRegistrationDate(Date RegistrationDate) {
        registrationDate = RegistrationDate;
    }

    /**
     * @param bookingDate  date of booking
     * @param bookingTable number of booking table
     */
    public void MakeBooking(Date bookingDate, int bookingTable) {

        this.bookings.add(new Booking(bookingDate, bookingTable));
    }

    /**
     * @param myBookingDate booking date
     * @return index of the booking that in the array
     */
    public int checkBookingDate(Date myBookingDate) {
        for (int x = 0; x < bookings.size(); x++)
            if (myBookingDate.compareTo(bookings.get(x).GetBookingDate()) == 0) {
                return x;
            }
        return -1;
    }

    /**
     * @param creditCardDetails setting credit card details
     */
    public void SetCreditCardDetails(int creditCardDetails) {
        this.creditCardDetails = creditCardDetails;
    }

    /**
     * @return getting orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * @param orders setting orders
     */
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    /**
     * showing last booking of customer
     */
    public void ShowLastBooking() {
        JFrame frame=new JFrame("Last Booking");

        Booking booking = bookings.get(bookings.size() - 1);
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/y");
        String bookingDate = formatter.format(booking.GetBookingDate());

        JLabel orderDates=new JLabel("Booking Date: "+bookingDate);
        orderDates.setBounds(10,10,300,30);
        frame.add(orderDates);

        JLabel bookingTable=new JLabel("Booking Table: " + booking.GetBookingID());
        bookingTable.setBounds(10,40,300,30);
        frame.add(bookingTable);

        JLabel dummy =new JLabel("");
        dummy.setBounds(10,70,100,30);
        frame.add(dummy);

        //Set the properties
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * listing all orders
     */
    public int listOrders(JFrame frame,int y) {
        for (Order order : orders) {
            if (order.getClass().arrayType().toString().equals("class [Lcom.assignment.core.OnlineOrder;"))
            {
                y=printOnlineOrder(frame,(OnlineOrder) order,y);
            }
            else
                y=printOnlineOrder(frame,(InRestrOrder) order,y);
        }
        return y+30;
    }

    /**
     * @param order printing online order which is inrestrorder
     */
    private int printOnlineOrder(JFrame frame,InRestrOrder order,int y) {

        JLabel id=new JLabel("Order ID: "+order.GetOrderID());
        id.setBounds(10,y,300,30);
        frame.add(id);

        JLabel details=new JLabel("Order Details: "+order.GetDetails());
        details.setBounds(10,y+30,300,30);
        frame.add(details);

        JLabel extra=new JLabel("Order Extra Notes: "+order.GetExtraNotes());
        extra.setBounds(10,y+60,300,30);
        frame.add(extra);

        SimpleDateFormat formatter = new SimpleDateFormat("d/M/y");
        String orderDate = formatter.format(order.GetOrderDate());

        JLabel orderDates=new JLabel("\nOrder Date: "+orderDate);
        orderDates.setBounds(10,y+90,300,30);
        frame.add(orderDates);

        JLabel tableNumber=new JLabel("Table Number: "+order.getTableNumber());
        tableNumber.setBounds(10,y+120,300,30);
        frame.add(tableNumber);

        JLabel bookingID=new JLabel("\nBooking ID: "+order.getBookingOrder().GetBookingID());
        bookingID.setBounds(10,y+150,300,30);
        frame.add(bookingID);

        return y+180;
    }

    /**
     * @param order printing online order
     */
    private int printOnlineOrder(JFrame frame,OnlineOrder order,int y) {

        JLabel id=new JLabel("Order ID: "+order.GetOrderID());
        id.setBounds(10,y,300,30);
        frame.add(id);

        JLabel details=new JLabel("Order Details: "+order.GetDetails());
        details.setBounds(10,y+30,300,30);
        frame.add(details);

        JLabel extra=new JLabel("Order Extra Notes: "+order.GetExtraNotes());
        extra.setBounds(10,y+60,300,30);
        frame.add(extra);

        SimpleDateFormat formatter = new SimpleDateFormat("d/M/y");
        String orderDate = formatter.format(order.GetOrderDate());

        JLabel orderDates=new JLabel("\nOrder Date: "+orderDate);
        orderDates.setBounds(10,y+90,300,30);
        frame.add(orderDates);

        JLabel paymentType=new JLabel("Payment Type: "+order.getPaymentType());
        paymentType.setBounds(10,y+120,300,30);
        frame.add(paymentType);

        JLabel deliveredBy=new JLabel("\nDelivered by: "+order.getDeliveredBy().GetName());
        deliveredBy.setBounds(10,y+150,300,30);
        frame.add(deliveredBy);

        return y+180;
    }

    /**
     * @param id id of customer
     * @param orderDate order date
     * @param details details of order
     * @param notes extra notes about order
     * @param tableNumber table number of order
     * @param theBooking booking related that order
     */
    public void addOrder(int id,Date orderDate,String details,String notes,int tableNumber,int theBooking) {
        Booking mybooking = bookings.get(theBooking);
        orders.add(new InRestrOrder(id, orderDate, details, notes, tableNumber, mybooking));
    }

    /**
     * @param id id of customer
     * @param orderDate order date
     * @param details details of order
     * @param notes extra notes about order
     * @param paymentType paymnet type for that order
     * @param deliveredBy junior staff for that order
     */
    public void addOrder(int id,Date orderDate,String details,String notes,int paymentType,Junior deliveredBy) {
        orders.add(new OnlineOrder(id, orderDate, details, notes, paymentType, deliveredBy));
    }

    /**
     * @param id id of customer
     * @return if that order exist return true otherwise return false
     */
    private boolean checkOrderIDs(int id) {
        for (Order order : orders) {
            if (id == order.GetOrderID()) {
                return true;
            }
        }
        return false;
    }
}
