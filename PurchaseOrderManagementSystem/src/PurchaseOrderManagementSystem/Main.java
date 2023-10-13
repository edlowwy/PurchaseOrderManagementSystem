package oodj_assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        
        Scanner userPickScanner = new Scanner(System.in);
        
        int userPickInput;
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("Purchase Order Management System (POM) for Sigma Sdn Bhd");
        System.out.println("-----------------------------------------------------------");
        
        while(true){
            System.out.println("Enter 1 for Administrator\nEnter 2 for Sales Manager\nEnter 3 for Purchase Manager\nEnter 4 to quit");
            userPickInput = userPickScanner.nextInt();
            userPickScanner.nextLine();
            
            if (userPickInput == 1){
                System.out.println("You choose Admin Log In Page");
                AdministratorLogin();
                break;
            }
            else if (userPickInput == 2){
                System.out.println("You choose Sales Manager Log In page");
                SalesManagerLogin();
                break;
            }
            else if (userPickInput == 3){
                System.out.println("You choose Purchase Manager Log In Page");
                PurchaseManagerLogin();
                break;
            }
            else if (userPickInput == 4){
                System.out.println("You choose to quit the system");
                System.exit(0);
            }
            else {
                System.out.println("Invalid Input. Please try again\n");
            }
        }
    }
    
    // Administrator Login
    public static void AdministratorLogin(){
        
        String adminFileName = "adminDetails.txt";
        boolean adminLoggedIn = false;
        
        while(!adminLoggedIn){
            try(BufferedReader adminReader = new BufferedReader(new FileReader(adminFileName))){
                Scanner adminLogInScanner = new Scanner(System.in);
                
                System.out.println("Enter the username: ");
                String adminCorrectUsername = adminLogInScanner.nextLine();
                
                System.out.println("Enter the password:");
                String adminCorrectPassword = adminLogInScanner.nextLine();
                
                String adminLine;
                while ((adminLine = adminReader.readLine()) != null){
                    String[] adminUserData = adminLine.split(",");
                    if (adminUserData.length >= 5){
                        String adminName = adminUserData[0].trim();
                        String adminUsernameFromFile = adminUserData[2].trim();
                        String adminPasswordFromFile = adminUserData[3].trim();
                        String roleName = adminUserData[4].trim();
                        
                        if(adminUsernameFromFile.equals(adminCorrectUsername) && adminPasswordFromFile.equals(adminCorrectPassword)){
                            adminLoggedIn = true;
                            System.out.println("Log in Successful " + adminUserData[0].trim());
                            AdministratorMenu();
                            break;
                        }
                    }
                }
                if (!adminLoggedIn){
                    System.out.println("Log in fail. incorrect username and password. Please try again\n");
                }
            }catch(IOException e){
                System.out.println("An error has occured" + e.getMessage());
            }
        }
    }
    
    // Sales Manager Login
    public static void SalesManagerLogin(){
        
        String salesManagerFileName = "salesManagerDetails.txt";
        boolean salesManagerLoggedIn = false;
        
        while(!salesManagerLoggedIn){
            try(BufferedReader salesManagerReader = new BufferedReader(new FileReader(salesManagerFileName))){
                Scanner salesManagerLogInScanner = new Scanner(System.in);
                
                System.out.println("Enter the username: ");
                String salesManagerCorrectUsername = salesManagerLogInScanner.nextLine();
                
                System.out.println("Enter the password:");
                String salesManagerCorrectPassword = salesManagerLogInScanner.nextLine();
                
                String salesManagerLine;
                while ((salesManagerLine = salesManagerReader.readLine()) != null){
                    String[] salesManagerUserData = salesManagerLine.split(",");
                    if (salesManagerUserData.length >= 5){
                        String salesManagerName = salesManagerUserData[0].trim();
                        String salesManagerUsernameFromFile = salesManagerUserData[2].trim();
                        String salesManagerPasswordFromFile = salesManagerUserData[3].trim();
                        String roleName = salesManagerUserData[4].trim();
                        
                        if(salesManagerUsernameFromFile.equals(salesManagerCorrectUsername) && salesManagerPasswordFromFile.equals(salesManagerCorrectPassword)){
                            salesManagerLoggedIn = true;
                            System.out.println("Log in Successful " + salesManagerUserData[0].trim());
                            SalesManagerMenu();
                            break;
                        }
                    }
                }
                if (!salesManagerLoggedIn){
                    System.out.println("Log in fail. incorrect username and password. Please try again\n");
                }
            }catch(IOException e){
                System.out.println("An error has occured" + e.getMessage());
            }
        }
    }
    
    // Purchase Manager Login
    public static void PurchaseManagerLogin(){
        
        String purchaseManagerFileName = "purchaseManagerDetails.txt";
        boolean purchaseManagerLoggedIn = false;
        
        while(!purchaseManagerLoggedIn){
            try (BufferedReader purchaseManagerReader = new BufferedReader(new FileReader(purchaseManagerFileName))){
                Scanner purchaseManagerLogInScanner = new Scanner(System.in);
                
                System.out.println("Enter the username: ");
                String purchaseManagerCorrectUsername = purchaseManagerLogInScanner.nextLine();
                
                System.out.println("Enter the password: ");
                String purchaseManagerCorrectPassword = purchaseManagerLogInScanner.nextLine();
                
                String purchaseManagerLine;
                while ((purchaseManagerLine = purchaseManagerReader.readLine()) != null){
                    String[] purchaseManagerUserData = purchaseManagerLine.split(",");
                    if (purchaseManagerUserData.length >= 5){
                        String purchaseManagerName = purchaseManagerUserData[0].trim();
                        String purchaseManagerUsernameFromFile = purchaseManagerUserData[2].trim();
                        String purchaseManagerPasswordFromFile = purchaseManagerUserData[3].trim();
                        String whichRole = purchaseManagerUserData[4].trim();
                        
                        if (purchaseManagerUsernameFromFile.equals(purchaseManagerCorrectUsername) && purchaseManagerPasswordFromFile.equals(purchaseManagerCorrectPassword)) {
                            purchaseManagerLoggedIn = true;
                            System.out.println("Login successful" + purchaseManagerUserData[0].trim());
                            PurchaseManagerMenu();
                            break;
                        }
                    }
                }
                if (!purchaseManagerLoggedIn){
                    System.out.println("Login Fail. Incorrect username or password.Please Try Again\n");
                }
            }catch (IOException e){
                System.out.println("An error has occured" + e.getMessage());
            }
        }
    }
    
    // Administrator Menu
    public static void AdministratorMenu() {
        
        Administrator admin = new Administrator();
        
        boolean loop_choice1 = true;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n------------------");
        System.out.println("ADMINISTRATOR");
        System.out.println("------------------");
        System.out.println("1. Register new Administrator");
        System.out.println("2. Register new Sales Manager");
        System.out.println("3. Register new Purchase Manager");
        System.out.println("4. Exit");
        
        while(loop_choice1 == true) {
            try {
                System.out.println("Please select by entering 1, 2, 3 or 4:");
                int choice = scanner.nextInt();
                
                if(choice == 1) {
                    loop_choice1 = false;
                    admin.userRegistrationForAdministrator();
                    AdministratorMenu();
                }
                else if(choice == 2) {
                    loop_choice1 = false;
                    admin.userRegistrationForSalesManager();
                    AdministratorMenu();
                }
                else if(choice == 3) {
                    loop_choice1 = false;
                    admin.userRegistrationForPurchaseManager();
                    AdministratorMenu();
                }
                else if(choice == 4) {
                    System.exit(0);
                }
                else {
                    System.out.println("Error: Invalid input\n");
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Error: Value entered is not an integer\n");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
    
    // Sales Manager Menu
    public static void SalesManagerMenu() {
        
        SalesManager sm = new SalesManager();
        
        Scanner scanner = new Scanner(System.in);
        
        int choice ;
        int option ;
        
        while(true) {
        System.out.println("--------------------------------------------");
        System.out.println("               SALES MANAGER                ");
        System.out.println("--------------------------------------------");
        System.out.println("Enter your choice:\n1. Item Information Update\n2. Supplier Information Update\n3. View information\n4. Daily Item Sales (DIS)\n5. Purchase Requisition (PR)\n6. View Purchase Order List\n7. Exit");
        System.out.println("--------------------------------------------");
        
        
    try{
        if(scanner.hasNextInt()) {
        choice = scanner.nextInt();       
        switch(choice){
            case 1:
                System.out.println("--------------------------------------------");
                System.out.println("            ITEM INFORMATION UPDATE         ");
                System.out.println("--------------------------------------------");
                System.out.println("Enter the option that you would like to make:\n1. Add New Item\n2. Delete Exisitng Item\n3. Edit Item Information\n4. Exit");
                System.out.println("--------------------------------------------");
                
            try {
                if(scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();
                switch(option) {
                    case 1: 
                        boolean loop = true;
                        do {
                        sm.EntryItem();
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("Do you still want to Add New Item?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("-------------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop = true;
                        } else if(cont.equalsIgnoreCase("no")) {
                            loop = false;
                            break;
                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop);
                        break;
                        
                    case 2:
                        boolean loop2 = true;
                        do {
                        sm.DeleteItem();
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("Do you still want to Delete Item?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("------------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop2 = true;
                        } else if (cont.equalsIgnoreCase("no")) {
                            loop2 = false;

                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop2);
                        break;

                        
                    case 3:
                        boolean loop3 = true;
                        do {
                        sm.EditItem();
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("Do you still want to Edit Item?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("----------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop3 = true;
                        } else if(cont.equalsIgnoreCase("no")) {
                            loop3 = false;
                            break;
                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop3);
                        break;

                        
                    case 4:
                        break;
                        
                    default:
                        System.out.println("Invalid Entry");
                        scanner.nextLine();
                        break;
                   
                }
                //break;
                
                } else {
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                    scanner.nextLine();

                }
                
                
            } catch(InputMismatchException e) {
                System.out.println("Invalid Input");
                scanner.nextLine();
            }
            
            break;
                
            case 2:
                
                System.out.println("--------------------------------------------");
                System.out.println("        SUPPLIER INFORMATION UPDATE         ");
                System.out.println("--------------------------------------------");
                System.out.println("Enter the option that you would like to make:\n1. Add New Supplier \n2. Delete Existing Supplier\n3. Edit Supplier Informaation\n4. Exit");
                System.out.println("--------------------------------------------");
                
            try {
                if(scanner.hasNextInt()) {                                
                option = scanner.nextInt();
                scanner.nextLine();
                switch(option) {
                    case 1:
                        boolean loop = true;
                        do {
                            
                        sm.EntrySupplier();
                        //supplier.EntrySupplier();
                        System.out.println("-----------------------------------------------------------------------------");
                        System.out.println("Do you still want to Add New Supplier?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("-----------------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop = true;
                        } else if(cont.equalsIgnoreCase("no")) {
                            loop = false;
                            break;
                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop);
                        break;
                        
                    case 2:
                        boolean loop2 = true;
                        do {
                        sm.DeleteSupplier();
                        System.out.println("----------------------------------------------------------------------------");
                        System.out.println("Do you still want to Delete Supplier?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("----------------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop2 = true;
                        } else if(cont.equalsIgnoreCase("no")) {
                            loop2 = false;

                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop2);
                        break;

                    case 3:
                        boolean loop3 = true;
                        do {
                        sm.EditSupplier();
                        System.out.println("--------------------------------------------------------------------------");
                        System.out.println("Do you still want to Edit Supplier?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("--------------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop3 = true;
                        } else if(cont.equalsIgnoreCase("no")) {
                            loop3 = false;
                            break;
                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop3);
                        break;

                    case 4:
                        break;
 
                    default: 
                        System.out.println("Invalid Entry");
                        scanner.nextLine();
                        break;
                }
                
                }else {
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                    scanner.nextLine();
                }
                //break;
                
            }catch(InputMismatchException e) {
                System.out.println("Invalid Input");
                scanner.nextLine();
            }
            
            break;
    
            case 3:
 
                System.out.println("--------------------------------------------");
                System.out.println("         VIEW SUPPLIER/ITEM LIST            ");
                System.out.println("--------------------------------------------");                
                
                System.out.println("Enter the List that you would like to view on\n1. Item's List\n2. Supplier's List");
                System.out.println("--------------------------------------------");
                
            try {
                if(scanner.hasNextInt()) {                               
                option = scanner.nextInt();
                scanner.nextLine();
                switch(option) {
                    case 1:
                        sm.ViewItemList();
                        break;
                        
                    case 2:
                        sm.ViewSupplierList();
                        break;
                        
                    default:
                        System.out.println("Invalid Entry");
                        scanner.nextLine();
                }
                
                }else {
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                    scanner.nextLine();
                }
                //break;
                
            }catch(InputMismatchException e) {         
                System.out.println("Invalid Input");
                scanner.nextLine();
                
            }
            
            break;
           
                
            
            case 4:
                System.out.println("--------------------------------------------");
                System.out.println("           DAILY ITEM SALES (DIS)            ");
                System.out.println("--------------------------------------------");
                System.out.println("Enter the option that you would like to make:\n1. Add DIS \n2. Edit DIS\n3. Delete DIS\n4. View DIS\n5. Exit");
                System.out.println("--------------------------------------------");

            try {
                if(scanner.hasNextInt()) {                              
                option = scanner.nextInt();
                scanner.nextLine();

                switch(option) {
                    case 1:
                        boolean loop = true;
                        do {
                            
                        sm.addDIS();
                        System.out.println("-----------------------------------------------------------------------------");
                        System.out.println("Do you still want to Add New DIS?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("-----------------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop = true;
                        } else if(cont.equalsIgnoreCase("no")) {
                            loop = false;
                            break;
                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop);
                        break;
                        
                    case 2:
                        boolean loop2 = true;
                        do {
                        sm.editDIS();
                        System.out.println("----------------------------------------------------------------------------");
                        System.out.println("Do you still want to Edit DIS?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("----------------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop2 = true;
                        } else if(cont.equalsIgnoreCase("no")) {
                            loop2 = false;

                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop2);
                        break;

                    case 3:
                        boolean loop3 = true;
                        do {
                        sm.deleteDIS();
                        System.out.println("--------------------------------------------------------------------------");
                        System.out.println("Do you still want to Delete DIS?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("--------------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop3 = true;
                        } else if(cont.equalsIgnoreCase("no")) {
                            loop3 = false;
                            break;
                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop3);
                        break;
                        
                    case 4:                       
                        sm.viewDIS();
                        break;                        
                        
                    case 5:
                        break;
 
                    default: 
                        System.out.println("Invalid Entry");
                        scanner.nextLine();
                        break;
                }
                
                } else {
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                    scanner.nextLine();
                }
                //break;
                
            }catch(InputMismatchException e) {
                System.out.println("Invalid Input");
                scanner.nextLine();
            }
            
            break;
                
            case 5:
                System.out.println("--------------------------------------------");
                System.out.println("          PURCHASE REQUISITION (PR)         ");
                System.out.println("--------------------------------------------");
                System.out.println("Enter the option that you would like to make:\n1. Add PR \n2. Edit PR\n3. Delete PR\n4. View PR\n5. Exit");
                System.out.println("--------------------------------------------");
                
                
            try {
                if(scanner.hasNextInt()) {                
                option = scanner.nextInt();
                scanner.nextLine(); 
       
                switch(option) {
                    case 1:
                        boolean loop = true;
                        do {
                            
                        sm.addPR();
                        System.out.println("-----------------------------------------------------------------------------");
                        System.out.println("Do you still want to Add New PR?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("-----------------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop = true;
                        } else if(cont.equalsIgnoreCase("no")) {
                            loop = false;
                            break;
                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop);
                        break;
                        
                    case 2:
                        boolean loop2 = true;
                        do {
                        sm.editPR();
                        System.out.println("----------------------------------------------------------------------------");
                        System.out.println("Do you still want to Edit PR?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("----------------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop2 = true;
                        } else if(cont.equalsIgnoreCase("no")) {
                            loop2 = false;

                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop2);
                        break;

                    case 3:
                        boolean loop3 = true;
                        do {
                        sm.deletePR();
                        System.out.println("--------------------------------------------------------------------------");
                        System.out.println("Do you still want to Delete PR?\nEnter 'yes' to continue, 'no' to exit");
                        System.out.println("--------------------------------------------------------------------------");
                        String cont = scanner.nextLine();
                        if(cont.equalsIgnoreCase("yes")) {
                            loop3 = true;
                        } else if(cont.equalsIgnoreCase("no")) {
                            loop3 = false;
                            break;
                        } else {
                            System.out.println("Invalid Input");
                            return; 
                        }
                        }while(loop3);
                        break;
                        
                    case 4:                       
                        sm.viewPR();
                        break;                        
                        
                    case 5:
                        break;
 
                    default: 
                        System.out.println("Invalid Entry");
                        scanner.nextLine();
                        break;
                }
                
                }else {
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                    scanner.nextLine();
                }
                //break;
                
            }catch(InputMismatchException e) {
                System.out.println("Invalid Input");
                scanner.nextLine();
            }
            
            break;
                
            case 6:
                System.out.println("--------------------------------------------");
                System.out.println("             PURCHASE ORDER LIST            ");
                System.out.println("--------------------------------------------");                
                sm.viewPurchaseOrder();
                break;

                
            case 7: 
                return;
                
            default:
                System.out.println("Invalid Entry");
                scanner.nextLine();
                break;              
        }  
        
        } else {
            System.out.println("Invalid Entry");
            scanner.nextLine();
        }
        

        
    }catch(InputMismatchException e) {
        System.out.println("Invalid Input");
        scanner.nextLine();
    }
    }  
    }

    // Purchase Manager Menu
    public static void PurchaseManagerMenu() {
        
        PurchaseManager pm = new PurchaseManager();
        
        boolean loop_choice1 = true;
        boolean loop_choice2 = true;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n------------------");
        System.out.println("PURCHASE MANAGER");
        System.out.println("------------------");
        System.out.println("1. View Items");
        System.out.println("2. View Supplier");
        System.out.println("3. View Purchase Requisiton (PR)");
        System.out.println("4. Purchase Order (PO)");
        System.out.println("5. Exit");
        
        while(loop_choice1 == true) {
            try {
                System.out.println("Please select by entering 1, 2, 3, 4 or 5:");
                int choice = scanner.nextInt();
                
                if(choice == 1) {
                    loop_choice1 = false;
                    pm.ViewItemList();
                    PurchaseManagerMenu();
                }
                else if(choice == 2) {
                    loop_choice1 = false;
                    pm.ViewSupplierList();
                    PurchaseManagerMenu();
                }
                else if(choice == 3) {
                    loop_choice1 = false;
                    pm.viewPR();
                    PurchaseManagerMenu();
                }
                else if(choice == 4) {
                    System.out.println("--------------------------------------------");
                    System.out.println("             PURCHASE ORDER (PO)            ");
                    System.out.println("--------------------------------------------");         
                    System.out.println("1. Add PO");
                    System.out.println("2. Edit PO");
                    System.out.println("3. Delete PO");
                    System.out.println("4. View PO");
                    System.out.println("5. Exit");
                    
                    while(loop_choice2 == true){
                        System.out.println("Please select by entering 1, 2, 3, 4 or 5:");
                        int choice2 = scanner.nextInt();

                        if(choice2 == 1) {
                            loop_choice1 = false;
                            loop_choice2 = false;
                            pm.addPurchaseOrder();
                            PurchaseManagerMenu();
                        }
                        else if(choice2 == 2) {
                            loop_choice1 = false;
                            loop_choice2 = false;
                            pm.editPurchaseOrder();
                            PurchaseManagerMenu();
                        }
                        else if(choice2 == 3) {
                            loop_choice1 = false;
                            loop_choice2 = false;
                            pm.deletePurchaseOrder();
                            PurchaseManagerMenu();
                        }
                        else if(choice2 == 4) {
                            loop_choice1 = false;
                            loop_choice2 = false;
                            pm.viewPurchaseOrder();
                            PurchaseManagerMenu();
                        }
                        else if(choice2 == 5) {
                            PurchaseManagerMenu();
                        }
                        else {
                            System.out.println("Error: Invalid input\n");
                        }
                    }
                }
                else if(choice == 5) {
                    System.exit(0);
                }
                else {
                    System.out.println("Error: Invalid input\n");
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Error: Value entered is not an integer\n");
                scanner.nextLine();
            }
        }
    }
}