package eddy_code;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Supplier {
    
    
    private String supplierid2;
    private String suppliername;
    private String itemid2 = "";
    private String address;  
    private String contactperson;
    private int contactphone;
    private String email;
    
    //To check the existance of the SupplierID in txt file (avoid duplication)
    public boolean CheckSupplierID(String supplierid) {
        try(BufferedReader readsupplier = new BufferedReader(new FileReader("supplier_list.txt"))) {
            String line;
            while((line = readsupplier.readLine()) != null) {
                String[] elements = line.split("\\|");
                String SUPPLIERID = elements[0];
                if(SUPPLIERID.equals(supplierid)) {
                    return true;
                }
            }
        }catch(IOException e) {
            System.out.println("Error reading the file");
            e.printStackTrace();
            
        }
        return false;
    }
    
    //To check the existance of the ItemID in txt file (avoid duplication)
    public boolean CheckDupItemID(String itemid) {
        try(BufferedReader readitem = new BufferedReader(new FileReader("supplier_list.txt"))) {
            String line;
            while((line = readitem.readLine())!= null) {
                String[] elements = line.split("\\|");
                String ITEMID = elements[2];
                if(ITEMID.matches(itemid)) {
                    return true;
                }
            }
        }catch(IOException e) {
            System.out.println("Error reading the file");
            e.printStackTrace();
        }
        return false;
    }
    
    //Append information of the Supplier to txt file
    public void SaveSupplier(List<String> itemids) {
        String line = supplierid2 + "|" + suppliername + "|" + String.join(",", itemids) + "|" + address + "|" + contactperson + "|" + contactphone + "|" + email;
        try(FileWriter writesupplier = new FileWriter("supplier_list.txt",true);
                BufferedWriter bw = new BufferedWriter(writesupplier)) {
            bw.write(line);
            bw.newLine();
            System.out.println("Supplier Information added successfully");
            
        }catch(IOException e) {
            System.out.println("Error writing the file");
            e.printStackTrace();
        }
        
    }
            
    //Entry information of the Supplier
    public void EntrySupplier() {
        
        System.out.println("--------------------------------------------");
        System.out.println("            ADD NEW SUPPLIER                ");
        System.out.println("--------------------------------------------");
        
        Scanner scanner = new Scanner(System.in);
        
        boolean valid_supplierid = false;
        while(!valid_supplierid) {
            System.out.println("Enter the SupplierID (SXXXX): ");
            supplierid2 = scanner.nextLine();
            valid_supplierid = supplierid2.matches("S\\d{4}");
            
            if(!valid_supplierid) {
                System.out.println("Invalid SupplierID, please refer to the format (SXXXX)");
            }else {
                if(CheckSupplierID(supplierid2)) {
                    System.out.println("SupplierID existed, please proceed to modification to make changes.");
                    return;
                }
            }
        }
        
        boolean valid_suppliername = false;
        while(!valid_suppliername) {
            System.out.println("Enter the Supplier Name: ");
            suppliername = scanner.nextLine();
            
            boolean numeric = false;
            for(char c : suppliername.toCharArray()) {
                if(Character.isDigit(c)){ 
                    numeric = true;
                }
            }
            
            if(numeric) {
                System.out.println("Numeric elements found, only characters are allowed");
            }else {
                valid_suppliername = true;
            }
           
        }
        
        List<String> itemids = new ArrayList<>();
        boolean repeat = false;
        while(!repeat) {
            
            boolean valid_itemid = false;
            while(!valid_itemid) {
            System.out.println("Enter the ItemID (IXXXX), enter 'no' to terminate the input");
            itemid2 = scanner.nextLine();
            if(itemid2.equalsIgnoreCase("no")) {
                repeat = true;
                break;
            }
            
            valid_itemid = itemid2.matches("I\\d{4}");
            
            if(!valid_itemid) {
                System.out.println("Invalid ItemID, please refer to the format (IXXXX)");
            }
            
            if(!repeat) {
                if(!itemids.contains(itemid2) && !CheckDupItemID(itemid2)) {
                    itemids.add(itemid2);
                }else {
                    System.out.println("Duplicated ItemID, ItemID already existed");
                }
                
            }
                    
            }

        }
        
        System.out.println("Enter the address of the supplier: ");
        address = scanner.nextLine();
        
        boolean valid_contactperson = false;
        while(!valid_contactperson){
            System.out.println("Enter the name of the contact person: ");
            contactperson = scanner.nextLine();

            boolean numeric = false;
            for(char c : contactperson.toCharArray()){
                if(Character.isDigit(c)){ 
                    numeric = true;
                }
            }
            
            if(numeric){
                System.out.println("Numeric elements found, only characters are allowed");
            }else{
                valid_contactperson = true;
            }            
            
        }
        
        boolean valid_contactphone = false;
        while(!valid_contactphone){
            System.out.println("Enter the contact number: ");
            String number = scanner.nextLine();
            try{
                contactphone = Integer.parseInt(number);
                valid_contactphone = true;              
            }catch(NumberFormatException e){
                System.out.println("Invalid entry, only numeric are allowed");
            }
            
        }
        
        System.out.println("Enter the email of the supplier: ");
        email = scanner.nextLine();   
        
        SaveSupplier(itemids);
      
    }
    
    //View information of the Supplier List 
    public void ViewSupplierList(){
        
        System.out.println("--------------------------------------------");
        System.out.println("              VIEW SUPPLIER LIST            ");
        System.out.println("--------------------------------------------");        
        
        String line;
        try(BufferedReader readsupplier = new BufferedReader(new FileReader("supplier_list.txt"))){
            while((line = readsupplier.readLine()) !=null){
                String[] elements = line.split("\\|");
                
                String SUPPLIERID = elements[0];
                String SUPPLIERNAME = elements[1];
                String ITEMID = elements[2];
                String ADDRESS = elements[3];
                String CONTACTPERSON = elements[4];
                String CONTACTPHONE = elements[5];
                String EMAIL = elements[6];
                
                System.out.println("SupplierID: "+SUPPLIERID+", Supplier Name: "+SUPPLIERNAME+", ItemID: "+ITEMID+", Address: "+ADDRESS+", Contact Person: "+CONTACTPERSON+", Contact Number: "+CONTACTPHONE+", Email: "+EMAIL);
            }
        }catch(IOException e){
            System.out.println("Error reading file");
            e.printStackTrace();
            
        }
        
    }
    
    //Add ITEMID into the exsiting line
    private void handleAddItemId(String[] supplierElements, Scanner scanner) {
        System.out.println("Enter the new ITEMID: ");
        String newItemID = scanner.nextLine();
        if (!newItemID.matches("I\\d{4}")) {
            System.out.println("Invalid ITEMID format. Please use IXXXX format.");
            return;
        }
        if (CheckDupItemID(newItemID)) {
            System.out.println("ITEMID already exists.");
            return;
        }
        supplierElements[2] += "," + newItemID;
    }
    
    //Delete ItemID from the existing line
    private void handleDeleteItemId(String[] supplierElements, Scanner scanner) {
        System.out.println("Enter the ITEMID to delete: ");
        String itemIdToDelete = scanner.nextLine();
        String[] itemIds = supplierElements[2].split(",");
        boolean found = false;
        StringBuilder updatedItemIds = new StringBuilder();

        for (String itemId : itemIds) {
            if (!itemId.equals(itemIdToDelete)) {
                updatedItemIds.append(itemId).append(",");
            } else {
                found = true;
            }
        }

        if (!found) {
            System.out.println("ITEMID not found.");
            return;
        }

        // Remove trailing comma if there are multiple itemids left
        if (updatedItemIds.length() > 0) {
            updatedItemIds.deleteCharAt(updatedItemIds.length() - 1);
        }
        supplierElements[2] = updatedItemIds.toString();
    }
    
    //Edit information of the Supplier
    public void EditSupplier() {
        System.out.println("--------------------------------------------");
        System.out.println("                EDIT SUPPLIER               ");
        System.out.println("--------------------------------------------");
        
        Scanner scanner = new Scanner(System.in);
        List<String> supplierList = new ArrayList<>();
        
        try(BufferedReader readsupplier = new BufferedReader(new FileReader("supplier_list.txt"))) {
            String line;
            while((line = readsupplier.readLine()) != null) {
                supplierList.add(line);
            }
        }catch(IOException e) {
            System.out.println("Error reading the file");
            e.printStackTrace();
            return; 
        }
        
        System.out.println("Enter the SupplierID that you want to edit: ");
        String editSupplier = scanner.nextLine();
        
        int element = -1;
        for(String line : supplierList) {
            String[] elements = line.split("\\|");
            if(elements[0].equals(editSupplier)) {
                element = supplierList.indexOf(line);
            }
            
        }
        
        if(element == -1) {
            System.out.println("SupplierID doesn't exist");
            return;
        }
        
        String[] supplierElements = supplierList.get(element).split("\\|");
        System.out.println("What would you like to modify on?");
        System.out.println("1. Supplier Name\n2. ItemID\n3. Address\n4, Contact Person\n5. Contact Phone\n6. Email");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
    if (choice == 1) {
        System.out.println("Enter the new Supplier Name: ");
        String suppliername_new = scanner.nextLine();
        supplierElements[1] = suppliername_new;
    } else if (choice == 2) {
        System.out.println("Do you want to add or delete an ITEMID? (add/delete/no)");
        String addOrDelete = scanner.nextLine();

        if (addOrDelete.equalsIgnoreCase("add")) {
            handleAddItemId(supplierElements, scanner);
        } else if (addOrDelete.equalsIgnoreCase("delete")) {
            handleDeleteItemId(supplierElements, scanner);
        } else if (!addOrDelete.equalsIgnoreCase("no")) {
            System.out.println("Invalid choice.");
            return;
        }
    } else if (choice == 3) {        
        System.out.println("Enter the new Address: ");
        String address_new = scanner.nextLine();
        supplierElements[3] = address_new;        
    } else if (choice == 4) {        
        System.out.println("Enter the new Contact Person: ");
        String address_new = scanner.nextLine();
        supplierElements[4] = address_new;
    } else if (choice == 5) {       
        System.out.println("Enter the new Contact Phone: ");
        String contactphone_new = scanner.nextLine();
        supplierElements[5] = contactphone_new;
    } else if (choice == 6) {        
         System.out.println("Enter the new Email: ");
        String email_new = scanner.nextLine();
        supplierElements[6] = email_new;
    } else {
        System.out.println("Invalid choice.");
        return;
    }
    
    supplierList.set(element, String.join("|", supplierElements));
    try(FileWriter writesupplier = new FileWriter("supplier_list.txt");
            BufferedWriter bw = new BufferedWriter(writesupplier)) {
        
        for (String NewSupplier : supplierList) {
            bw.write(NewSupplier);
            bw.newLine();
        }
        
    }catch(IOException e) {
        System.out.println("Error writing the file");
        e.printStackTrace();
    }
        
        
        
    }
    
    //Delete the entire line of the specific supplier information 
    public void DeleteSupplier() {
        
        System.out.println("--------------------------------------------");
        System.out.println("               DELETE SUPPLIER              ");
        System.out.println("--------------------------------------------");
        
        Scanner scanner = new Scanner(System.in);
        List<String> supplierList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("supplier_list.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                supplierList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file");
            e.printStackTrace();
            return;
        }

        System.out.println("Enter the SupplierID you want to delete: ");
        String supplierid_delete = scanner.nextLine();

        int element = -1;
        for (int i = 0; i < supplierList.size(); i++) {
            String line = supplierList.get(i);
            String[] elements = line.split("\\|");
            if (elements[0].equals(supplierid_delete)) {
                element = i;
                break;
            }
        }

        if (element == -1) {
            System.out.println("Supplier ID doesn't exist.");
            return;
        }

        supplierList.remove(element);

        try (FileWriter writer = new FileWriter("supplier_list.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            for (String supplierData : supplierList) {
                bufferedWriter.write(supplierData);
                bufferedWriter.newLine();
            }

            System.out.println("Supplier information deleted successfully.");
            
        } catch (IOException e) {
            System.out.println("Error writing the file");
            e.printStackTrace();
        }
    }        
    
}
