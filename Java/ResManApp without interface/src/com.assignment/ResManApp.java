//Ahmet Turan Bulut 2315174
//main class of the code,

package com.assignment;

import com.assignment.support.*;
import com.assignment.core.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
public class ResManApp {
    // Some variables to keep track of numbers, staffs, and input shortcut
    static java.util.ArrayList<Staff> myStaffs = new java.util.ArrayList<>();
    static java.util.ArrayList<Customer> myCustomers = new java.util.ArrayList<>();
    static PopulateData populate;

    //populating data from populatedata method
    static {
        try {
            populate = new PopulateData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    static Scanner input = new Scanner(System.in);

    /**
     *
     */
    public ResManApp() {
    }

    /**
     * @param argv commandline arguments
     */
    public static void main(String[] argv) {
        myCustomers.add(populate.customer1);
        myCustomers.add(populate.customer2);
        myCustomers.add(populate.customer3);
        myStaffs.add(populate.staff1);
        myStaffs.add(populate.staff2);
        myStaffs.add(populate.staff3);
        //exception handling
        try {
            Menu();
        } catch (InputMismatchException e) {
            System.out.println("\nYou entered wrong type please be careful next time!");
        } catch (ParseException e) {
            System.out.println("\nYou entered wrong date type please be careful!\nUse d/m/y format");
        }


    }

    /**
     *
     */
    public static void Menu() throws InputMismatchException, ParseException {
        int choose = 1, ssn;
        //main menu of the program
        while (choose != 17) {
            Scanner input = new Scanner(System.in);
            System.out.print("\n1-Add Staff\n2-Delete Staff\n3-List Staff Details\n4-Add Customer\n5-Delete Customer\n6-Add Booking\n7-Add Order");
            System.out.print("\n8-Get Customer Details\n9-Get Customer Bookings\n10-Get Customer Orders\n11-List Staffs\n12-List Customers\n13-Get Customer Last Booking");
            System.out.print("\n14-List All Orders\n15-List All Staff Salary\n16-Add Order Of Booking\n17-Exit\nChoose: ");
            choose = input.nextInt();

            switch (choose) {
                case 1 -> addStaff();
                case 2 -> {
                    System.out.print("please enter the ssn number of staff that you want to delete:");
                    ssn = input.nextInt();
                    deleteStaff(ssn);
                }
                case 3 -> {
                    System.out.print("please enter the ssn number of staff that you want to see details:");
                    ssn = input.nextInt();
                    listStaffDetails(ssn);
                }
                case 4 -> addCustomer();
                case 5 -> {
                    System.out.print("please enter the ssn number of customer that you want to delete:");
                    ssn = input.nextInt();
                    deleteCustomer(ssn);
                }
                case 6 -> {
                    System.out.print("please enter the ssn number of customer that you want to book:");
                    ssn = input.nextInt();
                    addBooking(ssn);
                }
                case 7 -> {
                    System.out.print("please enter the ssn number of customer that you want to make order:");
                    ssn = input.nextInt();
                    addOrder(ssn);
                }
                case 8 -> {
                    System.out.print("please enter the ssn number of customer that you want to get details:");
                    ssn = input.nextInt();
                    getCustomerDetails(ssn);
                }
                case 9 -> {
                    System.out.print("please enter the ssn number of customer that you want to book:");
                    ssn = input.nextInt();
                    getCustomerBooking(ssn);
                }
                case 10 -> {
                    System.out.print("please enter the ssn number of customer that you want to book:");
                    ssn = input.nextInt();
                    listCustomerOrders(ssn);
                }
                case 11 -> listStaff();
                case 12 -> listCustomer();
                case 13 -> {
                    System.out.print("please enter the ssn number of customer that you want to see last booking:");
                    ssn = input.nextInt();
                    getCustomerLastBooking(ssn);
                }
                case 14 -> listAllOrders();
                case 15 -> listAllStaffSalary();
                case 16 -> {
                    System.out.print("please enter the ssn number of customer that you want to see last booking:");
                    ssn = input.nextInt();
                    addOrderOfBooking(ssn);
                }
                default -> System.out.println("Please enter a number in the range 1-13");
            }
        }
    }

    /**
     * @param ssn of customer to add booking
     * @throws ParseException exception handling
     * @throws InputMismatchException exception handling
     * adding order and taking datas from user for order
     */
    private static void addOrderOfBooking(int ssn) throws ParseException,InputMismatchException {
        int check=0;
        for(Customer customer:myCustomers)
            if (customer.GetSSN()==ssn)
            {
                check=1;
                int theBooking;
                do {
                    System.out.print("Please enter booking date for that you want to add order: ");
                    String bookingDateString = input.next();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
                    //create a format for date
                    Date bookingDate = null;
                    //Parsing the String
                    bookingDate = dateFormat.parse(bookingDateString);
                    //booking index
                    theBooking = customer.checkBookingDate(bookingDate);
                }
                while (theBooking == -1);
                int id;
                do {
                    System.out.print("Please enter a order ID: ");
                    id = input.nextInt();
                }
                while (checkOrderIDs(customer.getOrders(), id));
                System.out.print("Please enter order date: ");
                String orderDateString = input.next();
                //create a format for date
                SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
                Date orderDate = null;
                //Parsing the String
                orderDate = dateFormat.parse(orderDateString);
                input.nextLine();
                System.out.print("Please enter the details of the order: ");
                String details = input.nextLine();
                System.out.print("Please enter the extra notes of the order: ");
                String notes = input.nextLine();
                System.out.print("Please enter the table number of order: ");
                int tableNumber = input.nextInt();
                customer.addOrder(id, orderDate, details, notes, tableNumber, theBooking);
                break;
            }
        if (check==0)
            System.out.print("We couldn't find that customer!");
    }

    /**
     * getting salary according the staff type
     */
    private static void listAllStaffSalary() {
        for(Staff staff: myStaffs )
        {
            //acording to staff class i decide which get salary will run
            if (staff.getClass().arrayType().toString().equals("class [LJunior;"))
                staff.getSalary();
            else
                staff.getSalary();
        }
    }

    /**
     * listing all orders
     */
    private static void listAllOrders() {
        for (Customer myCustomer : myCustomers)
            //checking every customer if it is ssn true. It also checks every booking that customer make to be sure
            if (myCustomer.getOrders().size() > 0)
                myCustomer.listOrders();
    }


    /**
     * @param ssn of customer
     * @throws InputMismatchException exception handling
     * @throws ParseException exception handling
     */
    private static void addOrder(int ssn) throws InputMismatchException, ParseException {
        int check = 0;
        for (Customer myCustomer : myCustomers) {
            //check every customer if it is ssn true and if it is true get all bookings
            if (ssn == myCustomer.GetSSN()) {
                check = 1;
                int op;
                do {
                    System.out.print("Which type of order do you want to make ?\n1-Online\n2-InRestr\nSelect: ");
                    op = input.nextInt();
                }
                while (op != 1 && op != 2);
                int id;
                do {
                    System.out.print("Please enter a order ID: ");
                    id = input.nextInt();
                }
                while (checkOrderIDs(myCustomer.getOrders(), id));
                System.out.print("Please enter order date: ");
                String orderDateString = input.next();
                //create a format for date
                SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
                Date orderDate = null;
                //Parsing the String
                orderDate = dateFormat.parse(orderDateString);
                input.nextLine();
                System.out.print("Please enter the details of the order: ");
                String details = input.nextLine();
                System.out.print("Please enter the extra notes of the order: ");
                String notes = input.nextLine();

                if (op == 1)//online
                {
                    int paymentType;
                    do {
                        System.out.print("Please select payment type\n1-Credit Card\n2-Cash\n3-Bitcoin\nSelect: ");
                        paymentType = input.nextInt();
                    }
                    while (paymentType != 1 && paymentType != 2 && paymentType != 3);
                    myCustomer.addOrder(id, orderDate, details, notes, paymentType, getRandomJunior());
                } else//inrestr
                {
                    System.out.print("Please enter the table number of order: ");
                    int tableNumber = input.nextInt();
                    Date bookingDate;
                    int theBooking;
                    do {
                        System.out.print("Please enter booking date for that order: ");
                        String bookingDateString = input.next();
                        //create a format for date
                        bookingDate = null;
                        //it is for catch the error while parsing the string according the format
                        //Parsing the String
                        bookingDate = dateFormat.parse(bookingDateString);
                        //booking index
                        theBooking = myCustomer.checkBookingDate(bookingDate);
                    }
                    while (theBooking == -1);
                    myCustomer.addOrder(id, orderDate, details, notes, tableNumber, theBooking);
                }

            }
        }
        if (check == 0) {
            printErrorMessage("SSN", "customer");
        }
    }


    /**
     * @param myCustomersorders all orders of customer
     * @param id for checking is order id unique or not
     * @return if it is not unique return true if it is false
     */
    private static boolean checkOrderIDs(java.util.ArrayList<Order> myCustomersorders, int id) {
        for (Order order : myCustomersorders) {
            if (id == order.GetOrderID()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return getting random junior for online order
     */
    private static Junior getRandomJunior() {
        java.util.ArrayList<Junior> juniors = new ArrayList<>();
        for (Staff myStaff : myStaffs) {
            //print for every staff
            if (myStaff.getClass().arrayType().toString().equals("class [LJunior;"))
                juniors.add((Junior) myStaff);
        }
        int random = new Random().nextInt(juniors.size());
        return juniors.get(random);

    }

    /**
     * @param ssn of the customer in order to print last booking
     */
    private static void getCustomerLastBooking(int ssn) {
        int check = 0;
        for (Customer myCustomer : myCustomers) {
            //check every customer if it is ssn true and if it is true get all bookings
            if (ssn == myCustomer.GetSSN()) {
                check = 1;
                myCustomer.ShowLastBooking();
            }
        }
        if (check == 0) {
            printErrorMessage("SSN", "customer");
        }
    }

    /**
     * @param myDate printing date
     */
    private static void printDate(Date myDate) {
        //printing date string month day year respectively
        System.out.printf("%s %tB %<te, %<tY\n", "Due date:", myDate);
    }

    /**
     * @param str  error details
     * @param kind customer or staff
     */
    private static void printErrorMessage(String str, String kind) {
        //printing error in short way
        System.out.println(str + " of the " + kind + " that you enter couldn't find please try again");
    }

    /**
     * @param myJunior print junior staff
     */
    private static void printStaff(Junior myJunior) {
        //print junior and decide if junior is male or female
        System.out.print("SSN: " + myJunior.GetSSN() + "\nName: " + myJunior.GetName() + "\n" + myJunior.getClass() + "\nGender: ");
        if (myJunior.GetGender() == 'M' || myJunior.GetGender() == 'm') {
            System.out.print("Male\nDate of Birth: ");
        } else {
            System.out.print("Female\nDate of Birth: ");
        }
        printDate(myJunior.GetBirthDate());
        System.out.print("Start date: ");
        printDate(myJunior.GetStartDate());
        System.out.print("End date: ");
        printDate(myJunior.getExpectedEndDate());
        System.out.println("Monthly salary: " + myJunior.getMonthlySalary());
    }

    /**
     * @param mySenior print senior staff
     */
    private static void printStaff(Senior mySenior) {
        //print junior and decide if junior is male or female
        System.out.print("SSN: " + mySenior.GetSSN() + "\nName: " + mySenior.GetName() + "\n" + mySenior.getClass() + "\nGender: ");
        if (mySenior.GetGender() == 'M' || mySenior.GetGender() == 'm') {
            System.out.print("Male\nDate of Birth: ");
        } else {
            System.out.print("Female\nDate of Birth: ");
        }
        printDate(mySenior.GetBirthDate());
        System.out.print("Gross Salary: " + mySenior.getGrossSalaryYearly() + "\nResponsible From:\n");

        for (int x = 0; x < mySenior.getResponsibleFrom().size(); x++) {
            System.out.println((x + 1) + "-" + mySenior.getResponsibleFrom().get(x).GetName());
        }
    }

    /**
     * @param x index of customer
     */
    private static void printCustomer(int x) {
        //print customer and decide if staff is male or female
        System.out.println("SSN: " + myCustomers.get(x).GetSSN() + "\nName: " + myCustomers.get(x).GetName() + "\nGender: ");
        if (myCustomers.get(x).GetGender() == 'M' || myCustomers.get(x).GetGender() == 'm') {
            System.out.print("Male\nDate of Birth: ");
        } else {
            System.out.print("Female\nDate of Birth: ");
        }
        printDate(myCustomers.get(x).GetBirthDate());
        System.out.print("\nRegistration Date: ");
        printDate(myCustomers.get(x).GetRegDate());
        System.out.println("Credit Card Details: " + myCustomers.get(x).GetCreditCardDetails());
    }

    /**
     * print all customers
     */
    private static void listCustomer() {
        for (int x = 0; x < myCustomers.size(); x++) {
            //print for every customer
            printCustomer(x);
        }
    }

    /**
     * print all staffs
     */
    private static void listStaff() throws ClassCastException {
        for (Staff myStaff : myStaffs) {
            //print for every staff
            if (myStaff.getClass().arrayType().toString().equals("class [Lcom.assignment.core.Junior;"))
                printStaff((Junior) myStaff);
            else
                printStaff((Senior) myStaff);
        }
    }

    /**
     * @param ssn of customer that we want to print orders
     */
    private static void listCustomerOrders(int ssn) {
        int check = 0;
        for (Customer myCustomer : myCustomers) {
            //checking every customer if it is ssn true. It also checks every booking that customer make to be sure
            if (myCustomer.getOrders().size() > 0 && myCustomer.GetSSN()==ssn) {
                check=1;
                myCustomer.listOrders();
            }
        }
        if (check == 0) {
            printErrorMessage("SSN or the booking date", "customer");
        }
    }

    /**
     * @param ssn ssn
     */
    private static void getCustomerBooking(int ssn) {
        int check = 0;
        for (Customer myCustomer : myCustomers) {
            //check every customer if it is ssn true and if it is true get all bookings
            if (ssn == myCustomer.GetSSN()) {
                check = 1;
                myCustomer.GetBookings();
            }
        }
        if (check == 0) {
            printErrorMessage("SSN", "customer");
        }
    }

    /**
     * @param ssn ssn
     */
    private static void getCustomerDetails(int ssn) {
        int check = 0;
        for (int x = 0; x < myCustomers.size(); x++) {
            if (ssn == myCustomers.get(x).GetSSN()) {
                check = 1;
                printCustomer(x);
                System.out.println("Bookings of the customer:");
                getCustomerBooking(ssn);
            }
        }
        if (check == 0) {
            printErrorMessage("SSN", "customer");
        }
    }

    /**
     * @param ssn ssn
     */
    private static void addBooking(int ssn) throws ParseException, InputMismatchException {
        Customer theCustomer = null;
        for (Customer myCustomer : myCustomers) {
            if (ssn == myCustomer.GetSSN()) {
                theCustomer = myCustomer;
            }
        }
        if (theCustomer != null) {
            System.out.println("Customer is found.\nDear " + theCustomer.GetName() + "\nPlease enter the booking date:");
            String bookingDateString = input.next();
            SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
            Date bookingDate = null;
            //Parsing the String
            bookingDate = dateFormat.parse(bookingDateString);
            System.out.println("Please enter the booking id:");
            int bookingID = input.nextInt();
            theCustomer.MakeBooking(bookingDate, bookingID);
        } else {
            printErrorMessage("SSN", "customer");
        }
    }

    /**
     * @param ssn ssn
     */
    private static void deleteCustomer(int ssn) {
        int check = 0;
        for (int x = 0; x < myCustomers.size(); x++) {
            //find true customer and delete after fixing gabs in the array
            if (ssn == myCustomers.get(x).GetSSN()) {
                int y;
                for (y = x; y < myCustomers.size() - 1; y++)
                    myCustomers.set(y, myCustomers.get(y + 1));
                myCustomers.remove(y);
                check = 1;
                System.out.println("Customer deleted...");
            }
        }
        if (check == 0) {
            printErrorMessage("SSN", "customer");
        }
    }

    /**
     * adding new customer
     */
    private static void addCustomer() throws InputMismatchException, ParseException {
        int ssn;
        do {
            System.out.print("Please enter the SSN number of your customer: ");
            ssn = input.nextInt();
        }
        while (checkSSNStaff(ssn) || checkSSNCustomer(ssn));
        System.out.print("Please enter the name of your customer: ");
        String name = input.next();
        char gender = 'a';
        do {
            if (gender != 'a')
                System.out.println("You entered wrong letter, please try again");
            System.out.print("Please enter the gender(f/m) of your customer: ");
            gender = input.next().toCharArray()[0];
        }
        while (gender != 'f' && gender != 'F' && gender != 'm' && gender != 'M');

        System.out.print("Please enter the date of birth of your customer: ");
        String customerBirthDateString = input.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
        Date customerDate = null;
        //Parsing the String
        customerDate = dateFormat.parse(customerBirthDateString);
        System.out.print("Please enter the registration date of your customer: ");
        String customerRegDateString = input.next();
        Date customerRegDate = null;
        //Parsing the String
        customerRegDate = dateFormat.parse(customerRegDateString);
        System.out.print("Please enter the credit card details of the customer: ");
        int creditCard = input.nextInt();
        myCustomers.add(new Customer(ssn, name, gender, customerDate, customerRegDate, creditCard));
    }

    /**
     * @param ssn ssn
     */
    private static void listStaffDetails(int ssn) {
        int check = 0;
        for (Staff myStaff : myStaffs) {
            if (ssn == myStaff.GetSSN()) {
                if (myStaff.getClass().arrayType().toString().equals("class [Lcom.assignment.core.Junior;"))
                    printStaff((Junior) myStaff);
                else
                    printStaff((Senior) myStaff);
                check = 1;
                break;
            }
        }
        if (check == 0) {
            printErrorMessage("SSN", "staff");
        }
    }

    /**
     * @param ssn ssn
     */
    private static void deleteStaff(int ssn) {
        int check = 0;
        for (int x = 0; x < myStaffs.size(); x++) {
            if (ssn == myStaffs.get(x).GetSSN()) {
                int y;
                for (y = x; y < myStaffs.size() - 1; y++)
                    myStaffs.set(y, myStaffs.get(y + 1));
                myStaffs.remove(y);
                check = 1;
                System.out.println("Staff deleted...");
            }
        }
        if (check == 0) {
            printErrorMessage("SSN", "staff");
        }

    }

    /**
     * adding new staff
     */
    private static void addStaff() throws ParseException, InputMismatchException {
        int op;
        do {
            System.out.print("Which type of staff do you want to add?\n1-Senior\n2-Junior\nChoose: ");
            op = input.nextInt();
        }
        while (op != 1 && op != 2);
        int ssn;
        do {
            System.out.print("Please enter the SSN number of your staff: ");
            ssn = input.nextInt();
        }
        while (checkSSNStaff(ssn) || checkSSNCustomer(ssn));
        System.out.print("Please enter the name of your staff: ");
        String name = input.next();
        char gender = 'a';
        do {
            if (gender != 'a')
                System.out.println("You entered wrong letter, please try again");
            System.out.print("Please enter the gender(f/m) of your staff: ");
            gender = input.next().toCharArray()[0];
        }
        while (gender != 'f' && gender != 'F' && gender != 'm' && gender != 'M');

        System.out.print("Please enter the date of birth of your staff: ");
        String staffBirthDateString = input.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
        Date staffDate = null;
        //Parsing the String
        staffDate = dateFormat.parse(staffBirthDateString);

        System.out.print("Please enter the start date of your staff: ");
        String staffStartDateString = input.next();
        Date staffStartDate = null;
        //Parsing the String
        staffStartDate = dateFormat.parse(staffStartDateString);
        if (op == 1)//senior
        {
            System.out.println("Please enter the yearly gross salary of the senior: ");
            int grossSalary = input.nextInt();
            myStaffs.add(new Senior(ssn, name, gender, staffDate, staffStartDate, grossSalary));
        } else {
            System.out.println("Please enter the monthly salary of the junior: ");
            int monthSalary = input.nextInt();
            System.out.print("Please enter the end date of your junior: ");
            String juniorEndDateString = input.next();
            Date juniorEndDate = null;
            //Parsing the String
            juniorEndDate = dateFormat.parse(juniorEndDateString);
            myStaffs.add(new Junior(ssn, name, gender, staffDate, staffStartDate, monthSalary, juniorEndDate));
        }

    }

    /**
     * @param ssn ssn
     * @return check if that customer in the customer list
     */
    public static boolean checkSSNCustomer(int ssn) {
        for (Customer myCustomer : myCustomers) {
            if (myCustomer.GetSSN() == ssn)
                return true;
        }
        return false;
    }

    /**
     * @param ssn ssn
     * @return check if that staff in the staff list
     */
    public static boolean checkSSNStaff(int ssn) {
        for (Staff myStaff : myStaffs) {
            if (myStaff.GetSSN() == ssn)
                return true;
        }
        return false;
    }
}
