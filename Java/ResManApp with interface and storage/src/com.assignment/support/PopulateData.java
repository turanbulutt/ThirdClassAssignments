//Ahmet Turan Bulut 2315174
//populate data class

package com.assignment.support;
import com.assignment.core.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PopulateData {
    public Senior staff1;
    public Junior staff2;
    public Senior staff3;

    public Customer customer1;
    public Customer customer2;
    public Customer customer3;


    /**
     * @param myCustomers all customers
     * @param myStaffs all staffs
     * @throws ParseException exception handling
     * @throws FileNotFoundException exception handling
     */
    public PopulateData(java.util.ArrayList<Customer> myCustomers,java.util.ArrayList<Staff> myStaffs) throws ParseException, FileNotFoundException {

        //opening file and taking string line by line
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
        FileInputStream file=new FileInputStream("data.txt");
        Scanner sc=new Scanner(file);
        String line;

        while(sc.hasNextLine())
        {
            //splitting line according to ;
            line=sc.nextLine();
            System.out.println(line);

            String[] parts=line.split(";");

            //check that line belongs which class after that doing necessary formatting and add class to program
            if (parts[0].equals("Senior"))
            {
                int ssn=Integer.parseInt(parts[1]);

                Date dateOfBirth = null;
                dateOfBirth=dateFormat.parse(parts[4]);

                Date startDate=null;
                startDate=dateFormat.parse(parts[5]);

                int salary=Integer.parseInt(parts[6]);
                if (parts[3].equals("m"))
                    myStaffs.add(new Senior(ssn,parts[2],'m',dateOfBirth,startDate,salary ));
                else
                    myStaffs.add(new Senior(ssn,parts[2],'f',dateOfBirth,startDate,salary ));
            }
            else if(parts[0].equals("Junior"))
            {
                int ssn=Integer.parseInt(parts[1]);

                Date dateOfBirth = null;
                dateOfBirth=dateFormat.parse(parts[4]);

                Date startDate=null;
                startDate=dateFormat.parse(parts[5]);

                int salary=Integer.parseInt(parts[6]);

                Date endDate=null;
                endDate=dateFormat.parse(parts[7]);
                if (parts[3].equals("m"))
                    myStaffs.add(new Junior(ssn,parts[2],'m',dateOfBirth,startDate,salary,endDate ));
                else
                    myStaffs.add(new Junior(ssn,parts[2],'f',dateOfBirth,startDate,salary,endDate ));
            }
            else if(parts[0].equals("Customer"))
            {
                int ssn=Integer.parseInt(parts[1]);

                Date dateOfBirth = null;
                dateOfBirth=dateFormat.parse(parts[4]);

                Date regDate=null;
                regDate=dateFormat.parse(parts[5]);

                int salary=Integer.parseInt(parts[6]);

                if (parts[3].equals("m"))
                    myCustomers.add(new Customer(ssn,parts[2],'m',dateOfBirth,regDate,salary ));
                else
                    myCustomers.add(new Customer(ssn,parts[2],'f',dateOfBirth,regDate,salary ));

            }
            else if(parts[0].equals("Booking"))
            {
                int index=Integer.parseInt(parts[1]);

                Date bookingDate=null;
                bookingDate=dateFormat.parse(parts[2]);

                int table=Integer.parseInt(parts[3]);

                myCustomers.get(index).MakeBooking(bookingDate,table);
            }
            else if (parts[0].equals("Online"))
            {
                int index=Integer.parseInt(parts[1]);

                int orderID=Integer.parseInt(parts[2]);

                Date orderDate=null;
                orderDate=dateFormat.parse(parts[3]);

                int paymentType=Integer.parseInt(parts[6]);

                int staffIndex=Integer.parseInt(parts[7]);

                myCustomers.get(index).addOrder(orderID,orderDate,parts[4],parts[5],paymentType, (Junior) myStaffs.get(staffIndex));
            }
            else if (parts[0].equals("InRestr"))
            {
                int index=Integer.parseInt(parts[1]);

                int orderID=Integer.parseInt(parts[2]);

                Date orderDate=null;
                orderDate=dateFormat.parse(parts[3]);

                int paymentType=Integer.parseInt(parts[6]);
                int table=Integer.parseInt(parts[7]);
                myCustomers.get(index).addOrder(orderID,orderDate,parts[4],parts[5],paymentType, table);
            }
        }
        sc.close();



    }
}
