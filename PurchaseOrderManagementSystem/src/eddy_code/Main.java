package eddy_code;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        
        Item item = new Item();
        Supplier supplier = new Supplier();
        Scanner scanner = new Scanner(System.in);
        
        
        int choice;
        int option;
        
        while(true) {
        System.out.println("--------------------------------------------");
        System.out.println("               SALES MANAGER                ");
        System.out.println("--------------------------------------------");
        System.out.println("Enter your choice:\n1. Item Information Update\n2. Supplier Information Update\n3. View information\n4. Daily Item Sales\n5. Purchase Requisition(PR)\n6. Exit");
        System.out.println("--------------------------------------------");
        
        if(scanner.hasNextInt()) {
        choice = scanner.nextInt();       
        switch(choice){
            case 1:
                System.out.println("--------------------------------------------");
                System.out.println("            ITEM INFORMATION UPDATE         ");
                System.out.println("--------------------------------------------");
                System.out.println("Enter the option that you would like to make:\n1. Add New Item\n2. Delete Exisitng Item\n3. Edit Item Information\n4. Exit");
                System.out.println("--------------------------------------------");
                option = scanner.nextInt();
                scanner.nextLine();
                switch(option) {
                    case 1: 
                        boolean loop = true;
                        do {
                        item.EntryItem();
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
                        item.DeleteItem();
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
                        item.EditItem();
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
                        break;
                   
                }
                break;
                
            case 2:
                
                System.out.println("--------------------------------------------");
                System.out.println("        SUPPLIER INFORMATION UPDATE         ");
                System.out.println("--------------------------------------------");
                System.out.println("Enter the oprion that you would like to make:\n1. Add New Supplier \n2. Delete Existing Supplier\n3. Edit Supplier Informaation\n4. Exit");
                System.out.println("--------------------------------------------");
                option = scanner.nextInt();
                scanner.nextLine();
                switch(option) {
                    case 1:
                        boolean loop = true;
                        do {
                            
                        supplier.EntrySupplier();
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
                        supplier.DeleteSupplier();
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
                        supplier.EditSupplier();
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
                        break;
                }
                break;
    
            case 3:
 
                System.out.println("--------------------------------------------");
                System.out.println("         VIEW SUPPLIER/ITEM LIST            ");
                System.out.println("--------------------------------------------");                
                
                System.out.println("Enter the List that you would like to view on\n1. Item's List\n2. Supplier's List");
                System.out.println("--------------------------------------------");
                option = scanner.nextInt();
                scanner.nextLine();
                switch(option) {
                    case 1:
                        item.ViewItemList();
                        break;
                        
                    case 2:
                        supplier.ViewSupplierList();
                        break;
                } 
                break;
                
            
            case 4:
                System.out.println("--------------------------------------------");
                System.out.println("           DAILY ITEM SALES(DIS)            ");
                System.out.println("--------------------------------------------");
                System.out.println("Enter the oprion that you would like to make:\n1. Add DIS \n2. Edit DIS\n3. Delete DIS\n4. View DIS\n5. Exit");
                System.out.println("--------------------------------------------");
                option = scanner.nextInt();
                scanner.nextLine();

                switch(option) {
                    case 1:
                        boolean loop = true;
                        do {
                            
                        supplier.EntrySupplier();
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
                        supplier.DeleteSupplier();
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
                        supplier.EditSupplier();
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
                        break;
                }
                break;

                
            case 6: 
                return;
                
            default:
                System.out.println("Invalid Entry");
                break;
                
                

                
        }
        
        } else {
            System.out.println("Invalid Entry");
            scanner.nextLine();
        }
        }

    }    
    
}
