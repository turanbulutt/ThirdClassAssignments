//Ahmet Turan Bulut 2315174
//staff class

package com.assignment.support;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Staff extends Person implements Employee{
    protected Date startDate;
    /**
     *
     * @param SSN ssn
     * @param Name name
     * @param Gender gender
     * @param DateOfBirth birthday
     */
    protected Staff(int SSN, String Name, char Gender, Date DateOfBirth, Date startDate)
    {
        this.ssn=SSN;
        this.name=Name;
        this.gender=Gender;
        this.dateOfBirth=DateOfBirth;
        this.startDate=startDate;
    }

    /**
     * creating staff with no info
     */
    Staff()
    {
        ssn=0;
        name="Not Provided";
        gender='n';
        dateOfBirth=new Date();
    }

    // Getters, get each variable in that class

    /**
     *
     * @return ssn
     */
    public  int GetSSN()
    {
        return ssn;
    }

    /**
     *
     * @return name
     */
    public String GetName()
    {
        return name;
    }

    /**
     *
     * @return gender
     */
    public char GetGender()
    {
        return gender;
    }

    /**
     *
     * @return birthday
     */
    public Date GetBirthDate()
    {
        return dateOfBirth;
    }

    public Date GetStartDate(){return startDate;}

    // Setters, setting variables if user want to change single data

    /**
     *
     * @param SSN ssn
     */
    public void SetSSN(int SSN)
    {
        this.ssn=SSN;
    }

    /**
     *
     * @param Name name
     */
    public void SetName(String Name)
    {
        this.name=Name;
    }

    /**
     *
     * @param Gender gender
     */
    public void SetGender(char Gender)
    {
        this.gender=Gender;
    }

    /**
     *
     * @param DateOfBirth birthday
     */
    public void SetDateOfBirth(Date DateOfBirth)
    {
        this.dateOfBirth=DateOfBirth;
    }

    public void SetStartDate(Date startDate){this.startDate=startDate;}

    @Override
    public void getSalary(JFrame frame,int y) {
    }
}
