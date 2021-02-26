//Ahmet Turan Bulut 2315174
//online order class

package com.assignment.core;

import java.util.Date;
import java.util.Scanner;
import com.assignment.support.*;

public class OnlineOrder extends Order {
    private int paymentType;
    private Junior deliveredBy;

    /**
     * @param orderID order id
     * @param orderDate order date
     * @param details details
     * @param extraNotes extra notes
     * @param paymentType payment type
     * @param deliveredBy junior for order
     */
    public OnlineOrder(int orderID, Date orderDate,String details,String extraNotes, int paymentType, Junior deliveredBy) {
        this.orderID=orderID;
        this.orderDate=orderDate;
        this.details=details;
        this.extraNotes=extraNotes;
        this.paymentType = paymentType;
        this.deliveredBy = deliveredBy;
    }

    /**
     * @return junior
     */
    public Junior getDeliveredBy() {
        return deliveredBy;
    }

    /**
     * @param deliveredBy set junior
     */
    public void setDeliveredBy(Junior deliveredBy) {
        this.deliveredBy = deliveredBy;
    }

    /**
     * @return payment type
     */
    public int getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType set payment type
     */
    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
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
     * @param orderID order id
     */
    @Override
    public void SetOrderID(int orderID) {
        this.orderID=orderID;
    }

    /**
     * @param orderDate order date
     */
    @Override
    public void SetOrderDate(Date orderDate) {
        this.orderDate=orderDate;
    }

    /**
     * @param Details details
     */
    @Override
    public void SetDetails(String Details) {
        this.details=details;
    }

    /**
     * @param extraNotes extra notes
     */
    @Override
    public void SetExtraNotes(String extraNotes) {
        this.extraNotes=extraNotes;
    }

    /**
     * process payment
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
