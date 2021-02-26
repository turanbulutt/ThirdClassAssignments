//Ahmet Turan Bulut 2315174
//senior class

package com.assignment.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.assignment.support.*;

import javax.swing.*;

public class Senior extends Staff {
    private int grossSalaryYearly;
    private java.util.ArrayList<Junior> responsibleFrom;

    /**
     * @param SSN ssn of senior
     * @param Name name of senior
     * @param Gender gender of senior
     * @param DateOfBirth birthday of senior
     * @param startDate start date of senior
     * @param grossSalaryYearly gross salary of senior
     */
    public Senior(int SSN, String Name, char Gender, Date DateOfBirth, Date startDate,int grossSalaryYearly) {
        super( SSN, Name, Gender, DateOfBirth, startDate);
        this.grossSalaryYearly = grossSalaryYearly;
        this.responsibleFrom = new java.util.ArrayList<Junior>();
    }

    /**
     * @return responsible from
     */
    public ArrayList<Junior> getResponsibleFrom() {
        return responsibleFrom;
    }

    /**
     * @param responsibleFrom responsible from
     */
    public void setResponsibleFrom(ArrayList<Junior> responsibleFrom) {
        this.responsibleFrom = responsibleFrom;
    }

    /**
     * @return gross salary
     */
    public int getGrossSalaryYearly() {
        return grossSalaryYearly;
    }

    /**
     * @param grossSalaryYearly gross salary
     */
    public void setGrossSalaryYearly(int grossSalaryYearly) {
        this.grossSalaryYearly = grossSalaryYearly;
    }

    /**
     * getting salary of senior
     */
    public void getSalary(JFrame frame,int y)
    {
        String startyearstring=startDate.toString().substring(startDate.toString().length()-4);
        int startYear=Integer.parseInt(startyearstring);
        int currentYear=Calendar.getInstance().get(Calendar.YEAR);
        int difference=currentYear-startYear;
        int gross=grossSalaryYearly/10*difference;

        JLabel salary=new JLabel((grossSalaryYearly+gross)/12 + "is current monthly salary of senior "+name);
        salary.setBounds(10,y,300,30);
        frame.add(salary);
    }
}
