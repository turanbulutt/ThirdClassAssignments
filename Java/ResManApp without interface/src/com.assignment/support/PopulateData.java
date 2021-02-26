//Ahmet Turan Bulut 2315174
//populate data class

package com.assignment.support;
import com.assignment.core.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PopulateData {
    public Senior staff1;
    public Junior staff2;
    public Senior staff3;

    public Customer customer1;
    public Customer customer2;
    public Customer customer3;

    /**
     *
     * @throws ParseException error handling for date parsing
     */
    public PopulateData() throws ParseException {
        staff1= new Senior(1,"Turan",'m',new SimpleDateFormat("d/M/y").parse("27/10/1998"),new SimpleDateFormat("d/M/y").parse("20/10/2020"),50000);
        staff2= new Junior(2,"Furkan",'m',new SimpleDateFormat("d/M/y").parse("06/07/2003"),new SimpleDateFormat("d/M/y").parse("27/10/2019"),654,new SimpleDateFormat("d/M/y").parse("27/10/2029"));
        staff3= new Senior(3,"Fatos",'f',new SimpleDateFormat("d/M/y").parse("10/11/1971"),new SimpleDateFormat("d/M/y").parse("16/05/2020"),564);

        customer1=new Customer(4,"Deniz",'m',new SimpleDateFormat("d/M/y").parse("10/11/2020"),new SimpleDateFormat("d/M/y").parse("06/09/1998"),456498);
        customer2=new Customer(5,"Javid",'m',new SimpleDateFormat("d/M/y").parse("11/11/2020"),new SimpleDateFormat("d/M/y").parse("15/08/2000"),3248);
        customer3=new Customer(6,"Rumessa",'f',new SimpleDateFormat("d/M/y").parse("12/11/2020"),new SimpleDateFormat("d/M/y").parse("29/03/1999"),46832);

        customer1.MakeBooking(new SimpleDateFormat("d/M/y").parse("11/11/2020"),1);
        customer2.MakeBooking(new SimpleDateFormat("d/M/y").parse("12/11/2020"),1);
        customer3.MakeBooking(new SimpleDateFormat("d/M/y").parse("13/11/2020"),1);
        customer1.addOrder(01, new SimpleDateFormat("d/M/y").parse("11/11/2020"), "adana kebab", "extra hot", 3, staff2);
        customer2.addOrder(02, new SimpleDateFormat("d/M/y").parse("12/11/2020"), "manti", "with yogurt", 1, 0);
        customer3.addOrder(03,new SimpleDateFormat("d/M/y").parse("13/11/2020"),"icli kofte","4 pieces",3,staff2);


    }
}
