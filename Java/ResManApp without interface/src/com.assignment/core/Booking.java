//Ahmet Turan Bulut 2315174
//booking class

package com.assignment.core;

import java.awt.print.Book;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Booking {
    private Date bookingDate;
    private int bookingID;

    Booking(Date bookingDate,int bookingID)
    {
        this.bookingDate=bookingDate;
        this.bookingID=bookingID;
    }

    /**
     * creating booking with no values
     */
    Booking()
    {
        bookingID=0;
        bookingDate=new Date();
    }

    // Getters, get each variable in that class

    /**
     *
     * @return booking date
     */
    public Date GetBookingDate()
    {
        return bookingDate;
    }

    /**
     *
     * @return booking table
     */
    public int GetBookingID()
    {
        return bookingID;
    }

    // Setters, setting variables if user want to change single data

    /**
     *
     * @param BookingDate setting the date of booking
     */
    public void SetBookingDate(Date BookingDate)
    {
        this.bookingDate=BookingDate;
    }

    /**
     * @param BookingID setting the booking id
     */
    public void SetBookingID(int BookingID)
    {
        this.bookingID=bookingID;
    }

}
