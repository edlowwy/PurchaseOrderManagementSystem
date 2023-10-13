package ljy_code;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

public class PurchaseRequisition {
    
    private String PRid; //auto generate
    private String PRdate; // auto generate
    private String SMid; // get from login
    private String SMname; // read from user file
    
    private String supplierid; // read from item file  
    private String itemid; // read from item file
    private String itemname; // read from item file
    private int price; // read from item file
    private int qty; // input
    private int totalpriceofitem; // calculate
    private int totalprice; // calculate
    
    private int day; // input
    private int month; // input
    private int year; // input
    
    
    PurchaseRequisition() {
        PRid = "PR1001";
        
        LocalDateTime timenow = LocalDateTime.now();
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        PRdate = dtf1.format(timenow);
        
        SMid = "SM1001";
        SMname = "John";
        
        supplierid = "S1001";
        itemid = "i1001";
        itemname= "Apple";
        qty = 10;
        price = 5;
        totalpriceofitem = price * qty;
        totalprice = 50;  
        
        day = 20;
        month = 8;
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy");
        year = Integer.valueOf(dtf2.format(timenow));
    }
    
    
    public void viewPR() {
        System.out.println("--------------------------");
        System.out.println("PURCHASE REQUISITION (PR)");
        System.out.println("--------------------------");
        System.out.println("PR ID: " + PRid);
        System.out.println("Date: " + PRdate);
        System.out.println("Sales Manager ID: " + SMid);
        System.out.println("Sales Manager Name: " + SMname);
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.printf("%-13s %-10s %-15s %-11s %-12s %-12s %-10s\n", "SUPPLIER ID", "ITEM ID", "ITEM NAME", "QUANTITY", "PRICE (RM)", "TOTAL (RM)", "DATE REQUIRED");
        System.out.printf("%-13s %-10s %-15s %-11s %-12s %-12s %d-%d-%d\n", supplierid, PRid, itemname, qty, price, totalpriceofitem, day, month, year);
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("Total Amount: RM " + totalprice);
    }
    
}
