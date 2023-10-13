package vivin_code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AssingmentTwo {
    public static void main(String[] args) {
        Scanner userPickScanner = new Scanner(System.in);
        int userPickInput;
        
        while(true){
            System.out.println("Enter 1 for Admin Pick Page\nEnter 2 for Sales Manager\nEnter 3 for Purchase Manager\nEnter 4 for new Admin\nEnter 5 to quit");
            userPickInput = userPickScanner.nextInt();
            userPickScanner.nextLine();
            
            if (userPickInput == 1){
                System.out.println("You choose Admin Log In Page");
                adminLogInPage();
                break;
            }
            else if (userPickInput == 2){
                System.out.println("You choose Sales Manager Log In page");
                salesManagerLogInPage();
                break;
            }
            else if (userPickInput == 3){
                System.out.println("You choose Purchase Manager Log In Page");
                purchaseManagerLogInPage();
                break;
            }
            else if (userPickInput == 4){
                System.out.println("You choose to register for new admin");
                newAdminSignUpPage();
            }
            else if (userPickInput <= 0){
                System.out.println("Invalid Input. Please try again");
            }
            else if (userPickInput > 5){
                System.out.println("Invalid Input. Please try again");
            }
            else if (userPickInput == 5){
                System.out.println("You choose to quit the session");
                break;
            }
            else {
                System.out.println("Invalid Input. Please try again");
            }
        }
    }
    
    
    public static void adminLogInPage(){
        
        Scanner adminScanner = new Scanner(System.in);
        int adminPickNumber;
        
        while(true){
            System.out.println("Enter 1 for Sales Manager Registration\nEnter 2 for Purchase Manager Registration\nEnter 3 to quit the system");
            adminPickNumber = adminScanner.nextInt();
            adminScanner.nextLine();
            
            if (adminPickNumber == 1){
                UserRegistration salesManagerRegistration = new UserRegistration();
                salesManagerRegistration.userRegistrationForSalesManager();
                break;
            }
            else if (adminPickNumber == 2){
                UserRegistration purchaseManagerRegistration = new UserRegistration();
                purchaseManagerRegistration.userRegistrationForPurchaseManager();
                break;
            }
            else if (adminPickNumber == 3){
                System.out.println("You enter to quit the system");
                break; 
            }
            else if (adminPickNumber <= 0){
                System.out.println("Invalid Pick. Please Try Again\n");
            }
            else if (adminPickNumber > 3){
                System.out.println("Invalid Pick. Please Try Again\n");
            }
            else{
                break;
            }
        }
    }
    
    
    public static void newAdminSignUpPage(){
        
    }
    
    
    
    
    
    public static void salesManagerLogInPage(){
        Scanner salesManagerPickScanner = new Scanner(System.in);
        int salesManagerPick;
        
        while(true){
            System.out.println("Enter 1 to continue log in Sales Manager\nEnter 2 to quit the system");
            salesManagerPick = salesManagerPickScanner.nextInt();
            salesManagerPickScanner.nextLine();
            
            if (salesManagerPick == 1){
                System.out.println("You choose to continue the log in");
                Login salesManagerLogIn = new Login();
                salesManagerLogIn.salesManagerLogInClass();
                salesManagerPickPage();
                break;
            }
            else if (salesManagerPick == 2){
                System.out.println("You choose to quit the system");
                break;
            }
            else if (salesManagerPick <= 0) {
                System.out.println("Invalid Input. Please try again");
            }
            else if (salesManagerPick > 2){
                System.out.println("Invalid Input. Please try again");
            }
            else{
                break;
            }
        }
    }
    
    
    public static void purchaseManagerLogInPage(){
        Scanner purchaseManagerPickScanner = new Scanner(System.in);
        int purchaseManagerPick;
        
        while(true){
            System.out.println("Enter 1 to continue the log in Purchase Manager\nEnter 2 to quit the system");
            purchaseManagerPick = purchaseManagerPickScanner.nextInt();
            purchaseManagerPickScanner.nextLine();
            
            if (purchaseManagerPick == 1){
                System.out.println("You choose to continu the log in");
                Login purchaseManagerLogIn = new Login();
                purchaseManagerLogIn.purchaseManagerLogInClass();
                purchaseManagerPickPage();
                break;
            }
            else if (purchaseManagerPick == 2){
                System.out.println("You choose to quit system");
                break;
            }
            else if (purchaseManagerPick <= 0){
                System.out.println("Invalid Input. Please try again");
            }
            else if (purchaseManagerPick > 2){
                System.out.println("Invalid Input. Please try again");
            }
            else{
                break;
            }
        }

    }
    public static void salesManagerPickPage(){
        Scanner salesManagerPickPageScanner = new Scanner(System.in);
        long salesManagerWhichNumberPick;
        
        while(true){
            System.out.println("Enter 1 for Item Entry Page\nEnter 2 for Supplier Entry Page\nEnter 3 for Daily Item-wise Sales Entry Page\nEnter 4 to Create a Purchase Requisition Page\nEnter 5 to Display Requisition Page\nEnter 6 to List of Purchase orders Page\nEnter 7 to quit the system");
            salesManagerWhichNumberPick = salesManagerPickPageScanner.nextLong();
            salesManagerPickPageScanner.nextLine();
            
            if (salesManagerWhichNumberPick == 1){
                System.out.println("You choose Item Entry Page\n");
                break;
            }
            else if (salesManagerWhichNumberPick == 2){
                System.out.println("You choose Supplier Entry Page\n");
                break;
            }
            else if (salesManagerWhichNumberPick == 3){
                System.out.println("You choose Daily Item-wise Sales Entry Page");
                break;
            }
            else if (salesManagerWhichNumberPick == 4){
                System.out.println("You choose to create a Purchase Requisition Page");
                break;
            }
            else if (salesManagerWhichNumberPick == 5){
                System.out.println("You choose to Display Requisition Page");
                break;
            }
            else if (salesManagerWhichNumberPick == 6){
                System.out.println("You choose to List to Purchase Order Page");
                salesManagerListPage();
                break;
            }
            else if (salesManagerWhichNumberPick == 7){
                System.out.println("You choose to exit the system");
                break;
            }
            else if (salesManagerWhichNumberPick > 7){
                System.out.println("Invalid Input. Please try again");
            }
            else if (salesManagerWhichNumberPick <= 0){
                System.out.println("Invalid input. Please try again");
            }
        }
        
    }
    public static void purchaseManagerPickPage(){
        Scanner purchaseManagerPickScanner = new Scanner(System.in);
        long purchaseManagerWhichNumberPick;
        
        while(true){
            System.out.println("Enter 1 to List the Items\nEnter 2 to List of Suppliers\nEnter 3 for Display Requisition\nEnter 4 to Generate Purchase Order\nEnter 5 to List the Purchase Orders\nEnter 6 to Exit the System");
            purchaseManagerWhichNumberPick = purchaseManagerPickScanner.nextLong();
            purchaseManagerPickScanner.nextLine();
            
            if (purchaseManagerWhichNumberPick == 1){
                System.out.println("You choose to list the items");
                break;
            }
            else if(purchaseManagerWhichNumberPick == 2){
                System.out.println("You choose to list the suppliers");
                break;
            }
            else if (purchaseManagerWhichNumberPick == 3){
                System.out.println("You coose to display requisiton");
                break;
            }
            else if (purchaseManagerWhichNumberPick == 4){
                System.out.println("You choose to generate purchase order");
                generatePurchaseOrderPage();
                break;
            }
            else if (purchaseManagerWhichNumberPick == 5){
                System.out.println("You choose to list the purchase order");
                listAllThePurchaseOrders();
                break;
            }
            else if (purchaseManagerWhichNumberPick == 6){
                System.out.println("You choose to quit the system");
                break;
            }
            else if (purchaseManagerWhichNumberPick <= 0){
                System.out.println("Invalid Pick. Please try again");
            }
            else if (purchaseManagerWhichNumberPick > 6){
                System.out.println("Invalid Pick. Please try again");
            }
            else{
                break;
            }
        }
    }
    public static void generatePurchaseOrderPage(){
        Scanner generatePurchaseOrderScanner = new Scanner(System.in);
        long generatePurchaseOrder;
        
        while(true){
            System.out.println("Enter 1 to approve or reject\nEnter 2 to edit the contents\nEnter 3 to delete the contents from the file");
            generatePurchaseOrder = generatePurchaseOrderScanner.nextLong();
            generatePurchaseOrderScanner.nextLine();
            
            if (generatePurchaseOrder == 1){
                generatePurchaseOrderFunction();
              
            }
            else if (generatePurchaseOrder == 2){
                editPurchaseOrderFunction();
                
            }
            else if (generatePurchaseOrder == 3){
                deletePurchaseOrderFunction();
            }
            else if (generatePurchaseOrder <= 0){
                System.out.println("Invalid number please try again");
            }
            else if (generatePurchaseOrder > 3){
                System.out.println("Invalid Number please try again");
            }
        }
        
    }
    public static void listAllThePurchaseOrders(){
        GeneratePurchaseOrder purchaseOrderView = new GeneratePurchaseOrder();
        purchaseOrderView.viewPurchaseOrder();
    }
    public static void salesManagerListPage(){
        SalesManager salesManagerView = new SalesManager();
        salesManagerView.salesManagerViewPurchaseOrder();
    }
    public static void generatePurchaseOrderFunction(){
        GeneratePurchaseOrder purchaseOrder = new GeneratePurchaseOrder();
        purchaseOrder.promptForManagerInfo();
        purchaseOrder.purchaseOrderPage();
    }
    public static void editPurchaseOrderFunction(){
        GeneratePurchaseOrder purchaseOrderEdit = new GeneratePurchaseOrder();
        purchaseOrderEdit.editPurchaseOrderPage();
    }
    public static void deletePurchaseOrderFunction(){
        GeneratePurchaseOrder purchaseOrderDelete = new GeneratePurchaseOrder();
        purchaseOrderDelete.deletePurchaseOrder();
    }
    
    
}
    
