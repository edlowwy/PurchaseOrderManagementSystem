package vivin_code;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class UserRegistration {
    private String name;
    private String userID;
    private String userName;
    private String userPassword;
    private String userRole;
    private String fileName;
    
    public void userRegistrationForSalesManager(){
        fileName = "salesManagerDetails.txt";
        
        Scanner salesManagerScanner = new Scanner(System.in);
        
        //Enter the name
        System.out.println("Enter the name of the user:");
        name = salesManagerScanner.nextLine();
        
        //Enter the user ID
        System.out.println("Enter the user ID:");
        userID = salesManagerScanner.nextLine();
        
        //ENter the username
        System.out.println("Enter the username:");
        userName = salesManagerScanner.nextLine();
        
        //Enter the password
        System.out.println("Enter the password:");
        userPassword = salesManagerScanner.nextLine();
        
        //Enter the user role
        System.out.println("Enter the user role:");
        userRole = salesManagerScanner.nextLine();
        
        
        try{
            FileWriter salesManagerFileWriter = new FileWriter(fileName, true);
            BufferedWriter salesManagerBufferedWriter = new BufferedWriter(salesManagerFileWriter);
            
            salesManagerBufferedWriter.write(name + "," + userID + "," + userName + "," + userPassword + "," + userRole);
            salesManagerBufferedWriter.newLine();
            
            salesManagerBufferedWriter.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error while uploading into the file");
        }
    }
    public void userRegistrationForPurchaseManager(){
        fileName = "purchaseManagerDetails.txt";
        
        Scanner purchaseManagerScanner = new Scanner(System.in);
        
        //Enter the name
        System.out.println("Enter the name:");
        name = purchaseManagerScanner.nextLine();
        
        //Enter the user ID
        System.out.println("Enter the user ID:");
        userID = purchaseManagerScanner.nextLine();
        
        //Enter the username
        System.out.println("Enter the username:");
        userName = purchaseManagerScanner.nextLine();
        
        //Enter the password
        System.out.println("Enter the password:");
        userPassword = purchaseManagerScanner.nextLine();
        
        //Enter the user role
        System.out.println("Enter the user role:");
        userRole = purchaseManagerScanner.nextLine();
        
        try{
            FileWriter purchaseManagerWriter = new FileWriter(fileName, true);
            BufferedWriter purchaseManagerBufferedWriter = new BufferedWriter(purchaseManagerWriter);
            
            purchaseManagerBufferedWriter.write(name + "," + userID + "," + userName + "," + userPassword + "," + userRole);
            purchaseManagerBufferedWriter.newLine();
            
            purchaseManagerBufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("An error while uploading contents into the file");
        }
    }
}
