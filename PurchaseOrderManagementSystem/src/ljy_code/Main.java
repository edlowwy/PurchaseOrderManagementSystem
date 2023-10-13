package ljy_code;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PurchaseManagerMenu();
    }
    
    
    public static void PurchaseManagerMenu() {
        
        oodj_assignment.PurchaseManager pm = new oodj_assignment.PurchaseManager();
        
        boolean loop_choice1 = true;
        boolean loop_choice2 = true;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n-----------------");
        System.out.println("PRODUCT MANAGER");
        System.out.println("-----------------");
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
                    System.out.println("1. Approve Purchase Requisition (PR)");
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
                            System.out.println("Approve PR");
                            PurchaseManagerMenu();
                        }
                        else if(choice2 == 2) {
                            loop_choice1 = false;
                            loop_choice2 = false;
                            System.out.println("Edit PO");
                            PurchaseManagerMenu();
                        }
                        else if(choice2 == 5) {
                            PurchaseManagerMenu();
                        }
                    }
                    //Vivin's Code
                    
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
        scanner.close();

    }
    
}
