package oodj_assignment;

import java.io.*;
import java.util.Scanner;


public class Administrator extends User {

    private String fileName;
    
    // User Registration for Administrator
    public void userRegistrationForAdministrator(){
        
        System.out.println("--------------------------------------------");
        System.out.println("         REGISTER NEW ADMINISTRATOR         ");
        System.out.println("--------------------------------------------");
        
        Administrator admin = new Administrator();
        fileName = "adminDetails.txt";
        
        Scanner adminScanner = new Scanner(System.in);
        
        //Enter the name
        System.out.println("Enter the name of the administrator:");
        admin.setName(adminScanner.nextLine());
        
        //Enter the user ID
        System.out.println("Enter the user ID:");
        admin.setUserID(adminScanner.nextLine());
        
        //ENter the username
        System.out.println("Enter the username:");
        admin.setUserName(adminScanner.nextLine());
        
        //Enter the password
        System.out.println("Enter the password:");
        admin.setUserPassword(adminScanner.nextLine());
        
        //Set the user role
        admin.setUserRole("admin");
        
        try{
            FileWriter adminFileWriter = new FileWriter(fileName, true);
            BufferedWriter adminBufferedWriter = new BufferedWriter(adminFileWriter);
            
            adminBufferedWriter.write(admin.getName() + "," + admin.getUserID() + "," + admin.getUserName() + "," + admin.getUserPassword() + "," + admin.getUserRole());
            adminBufferedWriter.newLine();
            
            adminBufferedWriter.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error while uploading into the file");
        }
    }
    
    // User Registration for Sales Manager
    public void userRegistrationForSalesManager(){
        
        System.out.println("--------------------------------------------");
        System.out.println("         REGISTER NEW SALES MANAGER         ");
        System.out.println("--------------------------------------------");
        
        SalesManager sm = new SalesManager();
        fileName = "salesManagerDetails.txt";
        
        Scanner salesManagerScanner = new Scanner(System.in);
        
        //Enter the name
        System.out.println("Enter the name of the sales manager:");
        sm.setName(salesManagerScanner.nextLine());
        
        //Enter the user ID
        System.out.println("Enter the user ID:");
        sm.setUserID(salesManagerScanner.nextLine());
        
        //ENter the username
        System.out.println("Enter the username:");
        sm.setUserName(salesManagerScanner.nextLine());
        
        //Enter the password
        System.out.println("Enter the password:");
        sm.setUserPassword(salesManagerScanner.nextLine());
        
        //Set the user role
        sm.setUserRole("salesmanager");
        
        try{
            FileWriter salesManagerFileWriter = new FileWriter(fileName, true);
            BufferedWriter salesManagerBufferedWriter = new BufferedWriter(salesManagerFileWriter);
            
            salesManagerBufferedWriter.write(sm.getName() + "," + sm.getUserID() + "," + sm.getUserName() + "," + sm.getUserPassword() + "," + sm.getUserRole());
            salesManagerBufferedWriter.newLine();
            
            salesManagerBufferedWriter.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error while uploading into the file");
        }
    }
    
    // User Registration for Purchase Manager
    public void userRegistrationForPurchaseManager(){
        
        System.out.println("--------------------------------------------");
        System.out.println("        REGISTER NEW PURCHASE MANAGER       ");
        System.out.println("--------------------------------------------");
        
        PurchaseManager pm = new PurchaseManager();
        fileName = "purchaseManagerDetails.txt";
        
        Scanner purchaseManagerScanner = new Scanner(System.in);
        
        //Enter the name
        System.out.println("Enter the name of the purchase manager:");
        pm.setName(purchaseManagerScanner.nextLine());
        
        //Enter the user ID
        System.out.println("Enter the user ID:");
        pm.setUserID(purchaseManagerScanner.nextLine());
        
        //Enter the username
        System.out.println("Enter the username:");
        pm.setUserName(purchaseManagerScanner.nextLine());
        
        //Enter the password
        System.out.println("Enter the password:");
        pm.setUserPassword(purchaseManagerScanner.nextLine());
        
        //Set the user role
        pm.setUserRole("purchasemanager");
        
        try{
            FileWriter purchaseManagerWriter = new FileWriter(fileName, true);
            BufferedWriter purchaseManagerBufferedWriter = new BufferedWriter(purchaseManagerWriter);
            
            purchaseManagerBufferedWriter.write(pm.getName() + "," + pm.getUserID() + "," + pm.getUserName() + "," + pm.getUserPassword() + "," + pm.getUserRole());
            purchaseManagerBufferedWriter.newLine();
            
            purchaseManagerBufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("An error while uploading contents into the file");
        }
    }
}
