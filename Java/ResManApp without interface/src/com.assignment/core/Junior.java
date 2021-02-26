//Ahmet Turan Bulut 2315174
//junior class

package com.assignment.core;

import java.util.Date;
import com.assignment.support.*;

public class Junior extends Staff {
    private int monthlySalary;
    private Date expectedEndDate;

    /**
     * @param SSN ssn of junior
     * @param Name name of junior
     * @param Gender gender of junior
     * @param DateOfBirth date of birth of junior
     * @param startDate start date of junior
     * @param monthlySalary salary of junior
     * @param expectedEndDate end date of junior
     */
    public Junior(int SSN,String Name,char Gender,Date DateOfBirth,Date startDate,int monthlySalary, Date expectedEndDate) {
        super(SSN, Name, Gender, DateOfBirth, startDate);
        this.monthlySalary = monthlySalary;
        this.expectedEndDate = expectedEndDate;
    }

    /**
     * @return end date
     */
    public Date getExpectedEndDate() {
        return expectedEndDate;
    }

    /**
     * @param expectedEndDate set end date
     */
    public void setExpectedEndDate(Date expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    /**
     * @return get salary
     */
    public int getMonthlySalary() {
        return monthlySalary;
    }

    /**
     * @param monthlySalary set salary
     */
    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    /**
     * get salary
     */
    public void getSalary()
    {
        System.out.println(monthlySalary+" is monthly salary of junior "+name);
    }
}
