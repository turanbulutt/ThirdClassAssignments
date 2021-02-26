//Ahmet Turan Bulut 2315174
//person abstract class

package com.assignment.support;

import java.io.Serializable;
import java.util.Date;

public abstract class Person implements Serializable {
    protected int ssn;
    protected String name;
    protected char gender;
    protected Date dateOfBirth;
    public abstract int GetSSN();
    public abstract String GetName();
    public abstract char GetGender();
    public abstract Date GetBirthDate();

    public abstract void SetSSN(int SSN);
    public abstract void SetName(String Name);
    public abstract void SetGender(char Gender);
    public abstract void SetDateOfBirth(Date DateOfBirth);
}
