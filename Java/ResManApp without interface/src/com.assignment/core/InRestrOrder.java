//Ahmet Turan Bulut 2315174
//inresrtrorder class

package com.assignment.core;

import java.util.Date;
import java.util.Scanner;
import com.assignment.support.*;

public class InRestrOrder extends Order {
    private int tableNumber;
    private Booking bookingOrder;

    /**
     * @param orderID order id
     * @param orderDate order date
     * @param details details of order
     * @param extraNotes extra notes about order
     * @param tableNumber table number of order
     * @param bookingOrder booking related that order
     */
    public InRestrOrder(int orderID, Date orderDate, String details, String extraNotes, int tableNumber, Booking bookingOrder) {
        this.orderID=orderID;
        this.orderDate=orderDate;
        this.details=details;
        this.extraNotes=extraNotes;
        this.tableNumber = tableNumber;
        this.bookingOrder = bookingOrder;
    }

    /**
     * @return booking order
     */
    public Booking getBookingOrder() {
        return bookingOrder;
    }

    public void setBookingOrder(Booking bookingOrder) {
        this.bookingOrder = bookingOrder;
    }

    /**
     * @return table number
     */
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     * @return order id
     */
    @Override
    public int GetOrderID() {
        return orderID;
    }

    /**
     * @return order date
     */
    @Override
    public Date GetOrderDate() {
        return orderDate;
    }

    /**
     * @return details
     */
    @Override
    public String GetDetails() {
        return details;
    }

    /**
     * @return extra notes
     */
    @Override
    public String GetExtraNotes() {
        return extraNotes;
    }

    /**
     * @param orderID set order id
     */
    @Override
    public void SetOrderID(int orderID) {
        this.orderID=orderID;
    }

    /**
     * @param orderDate set order date
     */
    @Override
    public void SetOrderDate(Date orderDate) {
        this.orderDate=orderDate;
    }

    /**
     * @param Details set details
     */
    @Override
    public void SetDetails(String Details) {
        this.details=details;
    }

    /**
     * @param extraNotes set extra notes
     */
    @Override
    public void SetExtraNotes(String extraNotes) {
        this.extraNotes=extraNotes;
    }

    /**
     * processing payment
     */
    @Override
    public void processPayment() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to process in the payment ?\n1-Yes\n2-No");
        int op=input.nextInt();
        if(op==1)
            System.out.println("Payment successful!!!");
        else
            System.out.println("Payment UNSUCCESSFUL!!!");
    }
}
