//Ahmet Turan Bulut 2315174
//order abstract class

package com.assignment.support;

import java.util.Date;

public abstract class Order implements Payment {
    protected int orderID;
    protected Date orderDate;
    protected String details;
    protected String extraNotes;

    // Getters, get each variable in that class


    public abstract int GetOrderID();
    public abstract Date GetOrderDate();
    public abstract String GetDetails();
    public abstract String GetExtraNotes();

    // Setters, setting variables if user want to change single data

    public abstract void SetOrderID(int orderID);
    public abstract void SetOrderDate(Date orderDate);
    public abstract void SetDetails(String Details);
    public abstract void SetExtraNotes(String extraNotes);

}
