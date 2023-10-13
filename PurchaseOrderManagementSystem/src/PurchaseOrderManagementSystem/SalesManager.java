package oodj_assignment;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class SalesManager extends User {
    
    //Item Variables 
    private String itemid;
    private String itemname;
    private String supplierid;
    private String desc;
    private int qty;
    private int price;
    private String date;
    
    //Supplier Variables 
    private String supplierid2;
    private String suppliername;
    private String itemid2 = "";
    private String address;  
    private String contactperson;
    private int contactphone;
    private String email;
    
    
    ///////////////////Item Entry////////////////////////////////////////////////////
    
    private static final String VIEW_FILE = "item_list.txt";
    
    //Check Existance of the ItemID in txt file (avoid duplication)
    public boolean CheckItemID(String itemID) {
        try(BufferedReader readitem = new BufferedReader(new FileReader("item_list.txt"))) {
            String line;
            while((line = readitem.readLine()) != null) {
                String[] elements = line.split("\\|");
                String ITEMID = elements[0];
                if(ITEMID.equals(itemID)) {
                    return true; // Item ID already exists
                }
            }
        } catch(IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        return false; // Item ID does not exist
    }
    
    //Entry information of the Item
    public void EntryItem(){
        
        System.out.println("--------------------------------------------");
        System.out.println("                ADD NEW ITEM                ");
        System.out.println("--------------------------------------------");        
        
        Scanner scanner = new Scanner(System.in);
       
        boolean valid_itemid = false;
        while(!valid_itemid){
            System.out.println("Enter the item code (IXXXX): ");
            itemid = scanner.nextLine();            
            valid_itemid = itemid.matches("I\\d{4}");
            
            if (!valid_itemid){
                System.out.println("Invalid item code, please refer to the format IXXXX.");               
            }else{
                if(CheckItemID(itemid)){
                    System.out.println("Item ID exists, please proceed to modification to make changes.");
                    return;
                }
            }                      
        }
        
        boolean valid_itemname = false;
        while(!valid_itemname){
            System.out.println("Enter the item name: ");
            itemname = scanner.nextLine();
            
            boolean numeric = false;
            for(char c : itemname.toCharArray()){
                if(Character.isDigit(c)){ 
                    numeric = true;
                }
            }
            
            if(numeric){
                System.out.println("Numeric elements found, only characters are allowed");
            }else{
                valid_itemname = true;
            }
           
        }
        
        boolean valid_supplierid = false;
            while(!valid_supplierid){
            System.out.println("Enter the supplier code (SXXXX): ");
            supplierid = scanner.nextLine();            
            valid_supplierid = supplierid.matches("S\\d{4}");
            
            if (!valid_supplierid){
                System.out.println("Invalid supplier code, please refer to the format SXXXX.");               
            }                      
        }
            
        boolean valid_desc = false;
        while(!valid_desc){
            System.out.println("Enter the description of the item(up to 50 words): ");
            desc = scanner.nextLine();
            
            int wordcnt = desc.trim().length();
            
            if(wordcnt > 50){
                System.out.println("The desciprion exceeds the limit of 50 words");
            }else{
                valid_desc = true;
            }
            
        }
        
        qty = 0;
        boolean valid_qty = false;
        while(!valid_qty){
            System.out.println("Enter the quantity of the item: ");
            if(scanner.hasNextInt()){
                qty = scanner.nextInt();
                valid_qty = true;
            }else{
                System.out.println("Invalid input, only numeric are allowed");
                scanner.nextLine();
            }
           
        }
        
        price = 0;
        boolean valid_price = false;
        while(!valid_price){
            System.out.println("Enter the price of the item(per unit): ");
                if(scanner.hasNextInt()){
                price = scanner.nextInt();
                valid_price = true;
            }else{
                System.out.println("Invalid input, only numeric are allowed");
                scanner.nextLine();
            }
                scanner.nextLine();
        }
        
        
        boolean valid_date = false;
        while(!valid_date){
            System.out.println("Enter the date (DD/MM/YYYY): ");
            date = scanner.nextLine();            
            valid_date = date.matches("\\d{2}/\\d{2}/\\d{4}");
            
            if (!valid_date){
                System.out.println("Invalid Entry, please refer to the format DD/MM/YYYY.");               
            }else{
                break;
            }                   
        }
        
        SaveItem();
         
    }
    
    
    //Append Item Info into the file
    public void SaveItem(){
        
        try{
            FileWriter writeitem = new FileWriter("item_list.txt",true);
            BufferedWriter bufferedwriter = new BufferedWriter(writeitem);
            writeitem.append(itemid+"|"+itemname+"|"+supplierid+"|"+desc+"|"+qty+"|"+price+"|"+ date);
            bufferedwriter.newLine();
            
            bufferedwriter.close();
        }catch(IOException e){
            System.out.println("Error writing the file");
            e.printStackTrace();
            
        }
        
    }
    
    //Delete the entire ItemID info
    public void DeleteItem(){
        
        System.out.println("--------------------------------------------");
        System.out.println("                  DELETE ITEM               ");
        System.out.println("--------------------------------------------");
        
        Scanner scanner = new Scanner(System.in);
        List<String> itemList = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(VIEW_FILE))) {
            String line;
            while((line = reader.readLine()) != null ) {
                itemList.add(line);
            }
            
        } catch (IOException e){
            System.out.println("Error reading the file");
            e.printStackTrace();
            return;   
        }
        
        if(itemList.isEmpty()) {
            System.out.println("No Items Available");
        } else {
            for(String itemLine : itemList) {
                String[] elements = itemLine.split("\\|");
                String ITEMID = elements[0];
                String ITEMNAME = elements[1];
                String SUPPLIERID = elements[2];
                String DESC = elements[3];
                String QTY = elements[4];
                String PRICE = elements[5];
                String DATE = elements[6];
                
                System.out.println("ItemID: "+ITEMID+", Item Name: "+ITEMNAME+", SupplierID: "+SUPPLIERID+", Description: "+DESC+", Quantity: "+QTY+", Price(per unit):RM "+PRICE+", Date Restock: "+DATE);
                        
            }
        }
        
        System.out.println("-------------------------------------");
        System.out.println("Enter the ItemID you want to delete: ");
        String itemid_delete = scanner.nextLine();
        
        int index = -1;
        for (String line : itemList) {
            String[] fields = line.split("\\|");
            if (fields[0].equals(itemid_delete)) {
                index = itemList.indexOf(line);
                break;
            }
        }

        if (index == -1) {
            System.out.println("ItemID doesn't exist.");
            return;
        }
        
        itemList.remove(index);
        
        try (FileWriter writer = new FileWriter(VIEW_FILE);
            BufferedWriter bufferedwriter = new BufferedWriter(writer)) {
            
            for (String itemData : itemList) {
                bufferedwriter.write(itemData);
                bufferedwriter.newLine();
            }
            
            System.out.println("Item information deleted successfully");
            
        } catch (IOException e ) {
            System.out.println("Error writing the file");
            e.printStackTrace();
        }
      
    }
    
    public void EditItem() {
        
    System.out.println("--------------------------------------------");
    System.out.println("                  EDIT ITEM                 ");
    System.out.println("--------------------------------------------"); 
        
    Scanner scanner = new Scanner(System.in);
    List<String> itemList = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(VIEW_FILE))) {
        String line;
        while ((line = reader.readLine()) != null) {
            itemList.add(line);
        }
    } catch (IOException e) {
        System.out.println("Error reading the file");
        e.printStackTrace();
        return;
    }
    
        if(itemList.isEmpty()) {
            System.out.println("No Items Available");
        } else {
            for(String itemLine : itemList) {
                String[] elements = itemLine.split("\\|");
                String ITEMID = elements[0];
                String ITEMNAME = elements[1];
                String SUPPLIERID = elements[2];
                String DESC = elements[3];
                String QTY = elements[4];
                String PRICE = elements[5];
                String DATE = elements[6];
                
                System.out.println("ItemID: "+ITEMID+", Item Name: "+ITEMNAME+", SupplierID: "+SUPPLIERID+", Description: "+DESC+", Quantity: "+QTY+", Price(per unit):RM "+PRICE+", Date Restock: "+DATE);
                        
            }
        }
        
    System.out.println("-----------------------------------");    
    System.out.println("Enter the ItemID you want to edit: ");
    String supplierIdToEdit = scanner.nextLine();
    
    //Read SupplierID from input and match the SupplierID in supplier_list.txt    
    int index = -1;
    for (String line : itemList) {
        String[] fields = line.split("\\|");
        if (fields[0].equals(supplierIdToEdit)) {
            index = itemList.indexOf(line);
            break;
        }
    }

    if (index == -1) {
        System.out.println("Supplier ID doesn't exist.");
        return;
    }

    String[] supplierFields = itemList.get(index).split("\\|");

    System.out.println("what would you like to modify on?");
    System.out.println("1. Item Name\n2. SupplierID\n3. Description\n4, Quantity\n5. Price\n6. Restock Date");

    int choice = scanner.nextInt();
    scanner.nextLine(); 

    if (choice == 1) {
        System.out.println("Enter the new Item Name: ");
        String itemname_new = scanner.nextLine();
        supplierFields[1] = itemname_new;
    } else if (choice == 2) {
        System.out.println("Enter the new SupplierID: ");
        String supplierid_new = scanner.nextLine();
        supplierFields[2] = supplierid_new;
    } else if (choice == 3) {        
        System.out.println("Enter the new Description: ");
        String desc_new = scanner.nextLine();
        supplierFields[3] = desc_new;        
    } else if (choice == 4) {        
        System.out.println("Enter the new Quantity: ");
        String qty_new = scanner.nextLine();
        supplierFields[4] = qty_new;
    } else if (choice == 5) {       
        System.out.println("Enter the new Price(per unit):RM ");
        String price_new = scanner.nextLine();
        supplierFields[5] = price_new;
    } else if (choice == 6) {        
         System.out.println("Enter the new Restock Date: ");
        String date_new = scanner.nextLine();
        supplierFields[6] = date_new;
    } else {
        System.out.println("Invalid choice.");
        return;
    }

    itemList.set(index, String.join("|", supplierFields));

    try (FileWriter writer = new FileWriter(VIEW_FILE);
         BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

        for (String supplierData : itemList) {
            bufferedWriter.write(supplierData);
            bufferedWriter.newLine();
        }

        System.out.println("Supplier information updated successfully.");
        
    } catch (IOException e) {
        System.out.println("Error writing the file");
        e.printStackTrace(); 
    }
    
    }
    
    //Add ItemID into the exsiting line
    private void handleAddItemId2(String[] supplierFields, Scanner scanner) {
        System.out.println("Enter the new ITEMID: ");
        String newItemId = scanner.nextLine();
        if (!newItemId.matches("I\\d{4}")) {
            System.out.println("Invalid ITEMID format. Please use IXXXX format.");
            return;
        }
        if (CheckItemID(newItemId)) {
            System.out.println("ITEMID already exists.");
            return;
        }
        supplierFields[2] += "," + newItemId;
    }
    
    //Delete ItemID from the existing line
    private void handleDeleteItemId2(String[] supplierFields, Scanner scanner) {
        System.out.println("Enter the ITEMID to delete: ");
        String itemIdToDelete = scanner.nextLine();
        String[] itemIds = supplierFields[2].split(",");
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
        supplierFields[2] = updatedItemIds.toString();
    }
    
    /////////////Supplier Entry//////////////////////////////////////
    
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
    
    //View Item Information
    public void ViewItemList(){
        
        System.out.println("--------------------------------------------");
        System.out.println("               VIEW ITEM LIST               ");
        System.out.println("--------------------------------------------");           
        
        String line;
        try(BufferedReader readitem = new BufferedReader(new FileReader("item_list.txt"))){
            while((line = readitem.readLine()) !=null){
                String[] elements = line.split("\\|");
                
                String ITEMID = elements[0];
                String ITEMNAME = elements[1];
                String SUPPLIERID = elements[2];
                String DESC = elements[3];
                String QTY = elements[4];
                String PRICE = elements[5];
                String DATE = elements[6];
                
                System.out.println("ItemID: "+ITEMID+", Item Name: "+ITEMNAME+", SupplierID: "+SUPPLIERID+", Description: "+DESC+", Quantity: "+QTY+", Price(per unit):RM "+PRICE+", Date Restock: "+DATE);
            }
        }catch(IOException e){
            System.out.println("Error reading file");
            e.printStackTrace();
            
        }
        
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
        
        if(supplierList.isEmpty()) {
            System.out.println("No Items Available");
        } else {
            for(String supplierLine : supplierList) {
                
                String[] elements = supplierLine.split("\\|");
                String SUPPLIERID = elements[0];
                String SUPPLIERNAME = elements[1];
                String ITEMID = elements[2];
                String ADDRESS = elements[3];
                String CONTACTPERSON = elements[4];
                String CONTACTPHONE = elements[5];
                String EMAIL = elements[6];
                
                System.out.println("SupplierID: "+SUPPLIERID+", Supplier Name: "+SUPPLIERNAME+", ItemID: "+ITEMID+", Address: "+ADDRESS+", Contact Person: "+CONTACTPERSON+", Contact Number: "+CONTACTPHONE+", Email: "+EMAIL);
                        
            }
        }
        
        System.out.println("--------------------------------------------");                      
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
            handleAddItemId2(supplierElements, scanner);
        } else if (addOrDelete.equalsIgnoreCase("delete")) {
            handleDeleteItemId2(supplierElements, scanner);
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
    
    //View Supplier Information
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
        
        if(supplierList.isEmpty()) {
            System.out.println("No Items Available");
        } else {
            for(String supplierLine : supplierList) {
                
                String[] elements = supplierLine.split("\\|");
                String SUPPLIERID = elements[0];
                String SUPPLIERNAME = elements[1];
                String ITEMID = elements[2];
                String ADDRESS = elements[3];
                String CONTACTPERSON = elements[4];
                String CONTACTPHONE = elements[5];
                String EMAIL = elements[6];
                
                System.out.println("SupplierID: "+SUPPLIERID+", Supplier Name: "+SUPPLIERNAME+", ItemID: "+ITEMID+", Address: "+ADDRESS+", Contact Person: "+CONTACTPERSON+", Contact Number: "+CONTACTPHONE+", Email: "+EMAIL);
                        
            }
        }
        
        System.out.println("-----------------------------------------");        
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
    
    // Function to add Daily Item-wise Sales Entry.
    public void addDIS() {
        
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<String> dailyItemSalesList = new ArrayList<>();
        ArrayList<String> itemsList = new ArrayList<>();
        
        boolean loop_choice1 = true;
        
        String itemid = null;
        String itemname = null;
        int qty = 0;
        int itemDTS; // Daily Total Sales of Item
        String todayDate;
        
        System.out.println("-----------------------");
        System.out.println("ADD DAILY ITEM SALES");
        System.out.println("-----------------------");
        
        // Autogenerate today's date
        LocalDateTime timenow = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        todayDate = dtf.format(timenow);
        
        // Read the item txt file, add to itemsList
        try {
            FileReader fr = new FileReader("item_list.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            
            while((currentLine = br.readLine()) != null) {
                String[] elements = currentLine.split("\\|");
                for(int i = 0; i < elements.length; i++) {
                    itemsList.add(elements[i]);
                }
            }          
            br.close();
            fr.close();
        }
        catch(IOException e) {
            System.out.println("Error: Cannot read item_list.txt text file");
        }
        
        // Display the items in item txt file
        System.out.println("Items in Inventory");
        System.out.println("-------------------");

        System.out.printf("%-9s %-19s %-36s %-11s\n",
            "ITEM ID", "ITEM NAME", "ITEM DESCRIPTION", "QUANTITY");

        for(int i = 0; i < itemsList.size(); i += 7) {
            System.out.printf("%-9s %-19s %-36s %-11s\n",
                itemsList.get(i), itemsList.get(i + 1), itemsList.get(i + 3), itemsList.get(i + 4));
        }
        
        // Read the daily item sales txt file, add to dailyItemSalesList
        try {
            FileReader fr = new FileReader("DailyItemSales.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentLine;

            while((currentLine = br.readLine()) != null) {
                String[] elements = currentLine.split(",");
                for(int i = 0; i < elements.length; i++) {
                    dailyItemSalesList.add(elements[i]);
                }
            }
            br.close();
            fr.close();
        }
        catch(IOException e) {
            System.out.println("Error: Cannot read daily item sales text file");
        }
        
        while(loop_choice1 == true) {
            
            boolean found = false;
            System.out.println("\nPlease select the item by typing the item ID:");
            itemid = scanner.nextLine();
            
            // Check if itemid and today's date match with itemid and date in daily item sales file
            for(int i = 0; i < dailyItemSalesList.size(); i += 4) {
                if(dailyItemSalesList.get(i).equals(itemid) && dailyItemSalesList.get(i + 3).equals(todayDate)) {
                    found = true;                    
                    System.out.println("Error: The daily sales for the specified item has been entered today");
                    break;
                }
            }
            
            if(found == true) {
                continue;
            }
            else {
                for(int i = 0; i < itemsList.size(); i += 7) {
                    if(itemsList.get(i).equals(itemid)) {
                        itemname = itemsList.get(i + 1);
                        qty = Integer.parseInt(itemsList.get(i + 4));
                        loop_choice1 = false;
                        break;
                    }
                }
            }
            
            if(loop_choice1 == true) {
                System.out.println("Error: The item ID cannot be found");
            }
        }
        
        while(true) {
            try {
                System.out.println("Please enter the daily total sales of item:");
                itemDTS = scanner.nextInt();

                if(itemDTS > qty) {
                    System.out.println("Error: The daily total sales is greater than quantity available\n");
                }
                else {
                    break;
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Error: Value entered is not an integer\n");
                scanner.nextLine();
            }
        }
        
        
        // Write the daily item sales to DailyItemSales txt file
        try {
            FileWriter fw = new FileWriter("DailyItemSales.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.print(itemid + "," + itemname + "," + itemDTS + "," + todayDate + "\n");
            pw.flush();
            pw.close();
            bw.close();
            fw.close();

            System.out.println("\nDaily Item Sales Entry is saved successfully!");
        }
        catch(IOException e) {
            System.out.println("\nError: Daily Item Sales Entry is not saved.");
        }
        
        // START EDIT item txt file
        // Edit the quantity in itemsList
        for(int i = 0; i < itemsList.size(); i += 7) {
            if(itemsList.get(i).equals(itemid)){
                int newQuantity = Integer.parseInt(itemsList.get(i + 4)) - itemDTS;
                itemsList.set(i + 4, String.valueOf(newQuantity));
            }
        }
        
        // Edit item_list txt tile
        try {
            FileWriter fw = new FileWriter("item_list.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
                
            for(int i = 0; i < itemsList.size(); i += 7) {
                bw.write(itemsList.get(i) + "|");
                bw.write(itemsList.get(i + 1) + "|");
                bw.write(itemsList.get(i + 2) + "|");
                bw.write(itemsList.get(i + 3) + "|");
                bw.write(itemsList.get(i + 4) + "|");
                bw.write(itemsList.get(i + 5) + "|");
                bw.write(itemsList.get(i + 6));
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("Stock of item ID: " + itemid + " has been updated successfully!");
        }
        catch (IOException e) {
            System.out.println("Error: Stock of item ID: " + itemid + " cannot be updated.");
        }
    }
    
    // Function to view Daily Item-wise Sales Entry.
    public void viewDIS() {
        
        ArrayList<String> dailyItemSalesList = new ArrayList<>();
        
        System.out.println("-----------------------");
        System.out.println("VIEW DAILY ITEM SALES");
        System.out.println("-----------------------");
        
        // Read the daily item sales txt file
        try {
            FileReader fr = new FileReader("DailyItemSales.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentLine;

            while((currentLine = br.readLine()) != null) {
                String[] elements = currentLine.split(",");
                for(int i = 0; i < elements.length; i++) {
                    dailyItemSalesList.add(elements[i]);
                }
            }
            br.close();
            fr.close();
        }
        catch(IOException e) {
            System.out.println("Error: Cannot read daily item sales text file");
        }
        
        // Display the items from daily item sales txt file
        System.out.printf("%-13s %-9s %-19s %-11s\n", "DATE", "ITEM ID", "ITEM NAME", "QUANTITY SOLD");
        
        for(int i = 0; i < dailyItemSalesList.size(); i += 4) {
            System.out.printf("%-13s %-9s %-19s %-11s\n",
                    dailyItemSalesList.get(i + 3), dailyItemSalesList.get(i), dailyItemSalesList.get(i + 1),
                    dailyItemSalesList.get(i + 2));
        }
        System.out.println();
    }
    
    // Function to delete Daily Item-wise Sales Entry.
    public void deleteDIS() {
        
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<String> dailyItemSalesList = new ArrayList<>();
        ArrayList<String> itemsList = new ArrayList<>();
        
        String date;
        String itemid;
        int itemDTS = 0;
        
        System.out.println("-------------------------");
        System.out.println("DELETE DAILY ITEM SALES");
        System.out.println("-------------------------");
        
        // Read the daily item sales txt file
        try {
            FileReader fr = new FileReader("DailyItemSales.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentLine;

            while((currentLine = br.readLine()) != null) {
                String[] elements = currentLine.split(",");
                for(int i = 0; i < elements.length; i++) {
                    dailyItemSalesList.add(elements[i]);
                }
            }
            br.close();
            fr.close();
        }
        catch(IOException e) {
            System.out.println("Error: Cannot read daily item sales text file");
        }
        
        // Display the items from daily item sales txt file
        System.out.printf("%-13s %-9s %-19s %-11s\n", "DATE", "ITEM ID", "ITEM NAME", "QUANTITY SOLD");
        
        for(int i = 0; i < dailyItemSalesList.size(); i += 4) {
            System.out.printf("%-13s %-9s %-19s %-11s\n",
                    dailyItemSalesList.get(i + 3), dailyItemSalesList.get(i), dailyItemSalesList.get(i + 1),
                    dailyItemSalesList.get(i + 2));
        }
        
        int year, month, day;
        boolean found = false;
        System.out.println("Please enter the date:");
        while(true) {
            try {
                System.out.print("Year: ");
                year = scanner.nextInt();
                break;
            }
            catch(InputMismatchException e) {
                System.out.println("Error: Value entered is not an integer\n");
                scanner.nextLine();
            }
        }
        while(true) {
            try {
                System.out.print("Month: ");
                month = scanner.nextInt();
                break;
            }
            catch(InputMismatchException e) {
                System.out.println("Error: Value entered is not an integer\n");
                scanner.nextLine();
            }
        }    
        while(true) {
            try {
                System.out.print("Day: ");
                day = scanner.nextInt();
                break;
            }
            catch(InputMismatchException e) {
                System.out.println("Error: Value entered is not an integer\n");
                scanner.nextLine();
            }
        }
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date = dtf.format(LocalDate.of(year, month, day)); 
        
        scanner.nextLine();
        System.out.println("Please enter the item ID:");
        itemid = scanner.nextLine();
        
        // Check if Daily Item Sales Entry exists
        for(int i = 0; i < dailyItemSalesList.size(); i += 4) {
            if(dailyItemSalesList.get(i).equals(itemid) && dailyItemSalesList.get(i + 3).equals(date)){
                System.out.println("Daily Sales Item Entry Found!");
                itemDTS = Integer.parseInt(dailyItemSalesList.get(i + 2));
                found = true;
            }
        }
        
        if(found == true) {
            
            // Write the undeleted contents back to the DailyItemSales txt file
            try {
                FileWriter fw = new FileWriter("DailyItemSales.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                
                for(int i = 0; i < dailyItemSalesList.size(); i += 4) {
                    if(!(dailyItemSalesList.get(i).equals(itemid) && dailyItemSalesList.get(i + 3).equals(date))) {
                        bw.write(dailyItemSalesList.get(i) + ",");
                        bw.write(dailyItemSalesList.get(i + 1) + ",");
                        bw.write(dailyItemSalesList.get(i + 2) + ",");
                        bw.write(dailyItemSalesList.get(i + 3));
                        bw.newLine();
                    }
                }
                bw.close();
                fw.close();
                System.out.println("\nDaily Item Sales Entry has been deleted successfully!");
            }
            catch(IOException e) {
                System.out.println("\nError: Daily Item Sales Entry is not deleted.");
            }
            
            // START EDIT item txt file
            // Read the item txt file, add to itemsList
            try {
                FileReader fr = new FileReader("item_list.txt");
                BufferedReader br = new BufferedReader(fr);
                String currentLine;

                while((currentLine = br.readLine()) != null) {
                    String[] elements = currentLine.split("\\|");
                    for(int i = 0; i < elements.length; i++) {
                        itemsList.add(elements[i]);
                    }
                }
                br.close();
                fr.close();
            }
            catch(IOException e) {
                System.out.println("Error reading item_list file");
            }

            // Edit the quantity in itemsList
            for(int i = 0; i < itemsList.size(); i += 7) {
                if(itemsList.get(i).equals(itemid)){
                    int newQuantity = Integer.parseInt(itemsList.get(i + 4)) + itemDTS;
                    itemsList.set(i + 4, String.valueOf(newQuantity));
                }
            }
            
            // Edit item_list txt tile
            try {
                FileWriter fw = new FileWriter("item_list.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                
                for(int i = 0; i < itemsList.size(); i += 7) {
                    bw.write(itemsList.get(i) + "|");
                    bw.write(itemsList.get(i + 1) + "|");
                    bw.write(itemsList.get(i + 2) + "|");
                    bw.write(itemsList.get(i + 3) + "|");
                    bw.write(itemsList.get(i + 4) + "|");
                    bw.write(itemsList.get(i + 5) + "|");
                    bw.write(itemsList.get(i + 6));
                    bw.newLine();
                }
                bw.close();
                fw.close();
                System.out.println("Stock of item ID: " + itemid + " has been updated successfully!");
            }
            catch (IOException e) {
                System.out.println("Error: Stock of item ID: " + itemid + " cannot be updated.");
            }
        }
        else {
            System.out.println("\nError: Daily Sales Item Entry is not found");
        }
    }
    
    // Function to edit Daily Item-wise Sales Entry.
    public void editDIS() {
        
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<String> dailyItemSalesList = new ArrayList<>();
        ArrayList<String> itemsList = new ArrayList<>();
        
        String date;
        String itemid;
        int itemDTS = 0;
        int stock = 0;
        int newQuantity;
        
        System.out.println("------------------------");
        System.out.println("EDIT DAILY ITEM SALES");
        System.out.println("------------------------");
        
        // Read the daily item sales txt file
        try {
            FileReader fr = new FileReader("DailyItemSales.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentLine;

            while((currentLine = br.readLine()) != null) {
                String[] elements = currentLine.split(",");
                for(int i = 0; i < elements.length; i++) {
                    dailyItemSalesList.add(elements[i]);
                }
            }
            br.close();
            fr.close();
        }
        catch(IOException e) {
            System.out.println("Error: Cannot read daily item sales text file");
        }
        
        // Display the items from daily item sales txt file
        System.out.printf("%-13s %-9s %-19s %-11s\n", "DATE", "ITEM ID", "ITEM NAME", "QUANTITY SOLD");
        
        for(int i = 0; i < dailyItemSalesList.size(); i += 4) {
            System.out.printf("%-13s %-9s %-19s %-11s\n",
                    dailyItemSalesList.get(i + 3), dailyItemSalesList.get(i), dailyItemSalesList.get(i + 1),
                    dailyItemSalesList.get(i + 2));
        }
        
        int year, month, day;
        boolean found = false;
        System.out.println("Please enter the date:");
        while(true) {
            try {
                System.out.print("Year: ");
                year = scanner.nextInt();
                break;
            }
            catch(InputMismatchException e) {
                System.out.println("Error: Value entered is not an integer\n");
                scanner.nextLine();
            }
        }
        while(true) {
            try {
                System.out.print("Month: ");
                month = scanner.nextInt();
                break;
            }
            catch(InputMismatchException e) {
                System.out.println("Error: Value entered is not an integer\n");
                scanner.nextLine();
            }
        }    
        while(true) {
            try {
                System.out.print("Day: ");
                day = scanner.nextInt();
                break;
            }
            catch(InputMismatchException e) {
                System.out.println("Error: Value entered is not an integer\n");
                scanner.nextLine();
            }
        }
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date = dtf.format(LocalDate.of(year, month, day)); 
        
        scanner.nextLine();
        System.out.println("Please enter the item ID:");
        itemid = scanner.nextLine();
        
        // Check if Daily Item Sales Entry exists
        for(int i = 0; i < dailyItemSalesList.size(); i += 4) {
            if(dailyItemSalesList.get(i).equals(itemid) && dailyItemSalesList.get(i + 3).equals(date)){
                System.out.println("Daily Sales Item Entry Found!");
                itemDTS = Integer.parseInt(dailyItemSalesList.get(i + 2));
                found = true;
            }
        }
        
        if(found == true) {
            // Read the item txt file, add to itemsList
            try {
                FileReader fr = new FileReader("item_list.txt");
                BufferedReader br = new BufferedReader(fr);
                String currentLine;

                while((currentLine = br.readLine()) != null) {
                    String[] elements = currentLine.split("\\|");
                    for(int i = 0; i < elements.length; i++) {
                        itemsList.add(elements[i]);
                    }
                }   
                br.close();
                fr.close();
            }
            catch(IOException e) {
                System.out.println("Error: Cannot read daily item sales text file");
            }
  
            // Get quantity of stock of item
            for(int i = 0; i < itemsList.size(); i += 7) {
                if(itemsList.get(i).equals(itemid)){
                    stock = Integer.parseInt(itemsList.get(i + 4));
                }
            }
            
            while(true) {
                try {
                    System.out.println("Please enter the new quantity sold:");
                    newQuantity = scanner.nextInt();
                    
                    if(newQuantity > stock) {
                        System.out.println("Error: The daily total sales entered is greater than quantity of stock available\n");
                    }
                    else {
                        break;
                    }
                }
                catch(InputMismatchException e) {
                    System.out.println("Error: Value entered is not an integer\n");
                    scanner.nextLine();
                }
            }
            
            // Edit quantity of item in dailyItemSalesList
            for(int i = 0; i < dailyItemSalesList.size(); i += 4) {
                if(dailyItemSalesList.get(i).equals(itemid) && dailyItemSalesList.get(i + 3).equals(date)) {
                    dailyItemSalesList.set(i + 2, String.valueOf(newQuantity));
                }
            }
            
            // Edit DailyItemSales txt tile
            try {
                FileWriter fw = new FileWriter("DailyItemSales.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                
                for(int i = 0; i < dailyItemSalesList.size(); i += 4) {
                    bw.write(dailyItemSalesList.get(i) + ",");
                    bw.write(dailyItemSalesList.get(i + 1) + ",");
                    bw.write(dailyItemSalesList.get(i + 2) + ",");
                    bw.write(dailyItemSalesList.get(i + 3));
                    bw.newLine();
                }
                bw.close();
                fw.close();
                System.out.println("\nDaily Item Sales Entry has been edited successfully!");
            }
            catch (IOException e) {
                System.out.println("\nError: Daily Item Sales Entry is not deleted.");
            }
            
            // Calculate new item stock quantity
            int newStock = stock - (newQuantity - itemDTS);
            
            // START EDIT item txt file
            // Edit the quantity in itemsList
            for(int i = 0; i < itemsList.size(); i += 7) {
                if(itemsList.get(i).equals(itemid)){
                    itemsList.set(i + 4, String.valueOf(newStock));
                }
            }
            
            // Edit item_list txt tile
            try {
                FileWriter fw = new FileWriter("item_list.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                
                for(int i = 0; i < itemsList.size(); i += 7) {
                    bw.write(itemsList.get(i) + "|");
                    bw.write(itemsList.get(i + 1) + "|");
                    bw.write(itemsList.get(i + 2) + "|");
                    bw.write(itemsList.get(i + 3) + "|");
                    bw.write(itemsList.get(i + 4) + "|");
                    bw.write(itemsList.get(i + 5) + "|");
                    bw.write(itemsList.get(i + 6));
                    bw.newLine();
                }
                bw.close();
                fw.close();
                System.out.println("Stock of item ID: " + itemid + " has been updated successfully!");
            }
            catch (IOException e) {
                System.out.println("Error: Stock of item ID: " + itemid + " cannot be updated.");
            }
        }
        else {
            System.out.println("\nError: Daily Sales Item Entry is not found");
        }
    }
    
    // Function to add Purchase Requisition.
    public void addPR() {

        Scanner scanner = new Scanner(System.in);

        // 2D ArrayList to store items
        ArrayList<ArrayList<Object>> itemsList2D = new ArrayList<>();
        ArrayList<Object> itemDetailsList;

        ArrayList<String> itemsList = new ArrayList<>();
        ArrayList<String> salesManagerList = new ArrayList<>();

        boolean loop_choice1 = true;
        boolean loop_choice2 = true;

        String PRid = null;
        String SMname = null;
        String SMid = null;
        String supplierid = null;
        String itemid = null;
        String itemname = null;
        
        int[] days_in_month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int qty = 0;
        int year = 0;
        int month = 0;
        int day = 0;
        double price = 0;
        double totalprice = 0;

        System.out.println("----------------------------");
        System.out.println("ADD PURCHASE REQUISITION");
        System.out.println("----------------------------");

        // Autogenerate the PRid
        try {
            FileReader fr = new FileReader("PurchaseRequisition.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentLine;

            while((currentLine = br.readLine()) != null) {
                String elements[] = currentLine.split(",");
                if(elements[0].startsWith("PR")) {
                    PRid = elements[0];
                }
            }
            br.close();
            fr.close();  
        }
        catch(IOException e) {
            System.out.println("Error: PurchaseRequision.txt file cannot be read");
        }

        if(PRid != null) {
            // Extract the number part of PRid and convert it to an integer
            int numberPart = Integer.parseInt(PRid.substring(2));
            numberPart++;

            // Form the new PRid
            PRid = "PR" + numberPart;
            System.out.println("PR ID: " + PRid);
        }
        else {
            System.out.println("No PR has been created.");
        }

        // Autogenerate the date created for PR
        LocalDateTime timenow = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String PRdate = dtf.format(timenow);

        // Read the Sales Manager txt file
        try {
            FileReader fr = new FileReader("salesManagerDetails.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            
            while((currentLine = br.readLine()) != null) {
                String[] elements = currentLine.split(",");
                for(int i = 0; i < elements.length; i++) {
                    salesManagerList.add(elements[i]);
                }
            }
            br.close();
            fr.close();
        }
        catch(IOException e) {
            System.out.println("Error: salesManagerDetails.txt file cannot be read");
        }
        
        while(loop_choice1 == true) {
            System.out.println("Please enter your Sales Manager ID:");
            SMid = scanner.nextLine();
        
            for(int i = 0; i < salesManagerList.size(); i += 5) {
                if(salesManagerList.get(i + 1).equals(SMid)) {
                    SMname = salesManagerList.get(i);
                    loop_choice1 = false;
                    break;
                }
            }
            
            if(loop_choice1 == true) {
                System.out.println("Error: The Sales Manager ID cannot be found\n");
            }
        }

        // Read the item txt file
        try {
            FileReader fr = new FileReader("item_list.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            
            while((currentLine = br.readLine()) != null) {
                String[] elements = currentLine.split("\\|");
                for(int i = 0; i < elements.length; i++) {
                    itemsList.add(elements[i]);
                }
            }
            br.close();
            fr.close();
        }
        catch(IOException e) {
            System.out.println("Error: item_list.txt file cannot be read");
        }

        while(loop_choice2 == true) {
            
            boolean loop_choice3 = true;
            boolean loop_choice4 = true;

            // Display the items from item_list txt file
            System.out.println("-------------------");
            System.out.println("Items in Inventory");
            System.out.println("-------------------");

            System.out.printf("%-9s %-19s %-13s %-36s %-11s %-12s\n",
                "ITEM ID", "ITEM NAME", "SUPPLIER ID", "DESCRIPTION", "QUANTITY", "PRICE (RM)");

            for(int i = 0; i < itemsList.size(); i += 7) {
                System.out.printf("%-9s %-19s %-13s %-36s %-11s %-12s\n",
                    itemsList.get(i), itemsList.get(i + 1), itemsList.get(i + 2),
                    itemsList.get(i + 3), itemsList.get(i + 4), itemsList.get(i + 5));
            }

            while(loop_choice3 == true) {
                System.out.println("\nPlease select the item by typing the item ID:");
                itemid = scanner.nextLine();

                for(int i = 0; i < itemsList.size(); i += 7) {
                    if(itemsList.get(i).equals(itemid)) {
                        supplierid = itemsList.get(i + 2);
                        itemname = itemsList.get(i + 1);
                        price = Double.parseDouble(itemsList.get(i + 5));
                        loop_choice3 = false;
                        break;
                    }
                }

                if(loop_choice3 == true) {
                    System.out.println("Error: The item ID cannot be found");
                }
            }

            while(true) {
                try {
                    System.out.println("Please enter the quantity of item:");
                    qty = scanner.nextInt();
                    break;
                }
                catch(InputMismatchException e) {
                    System.out.println("Error: Value entered is not an integer\n");
                    scanner.nextLine();
                }
            }

            double totalpriceofitem = price * qty;

            System.out.println("Please enter the date required");
            while(true) {
                try {
                    System.out.println("Year (Enter digit): ");
                    year = scanner.nextInt();
                    break;
                }
                catch(InputMismatchException e) {
                    System.out.println("Error: Value entered is not an integer\n");
                    scanner.nextLine();
                }
            }
            while(true) {
                try {
                    System.out.println("Month (Enter digit): ");
                    month = scanner.nextInt();
                    if(month < 1 || month > 12) {
                        System.out.println("Error: Invalid month was entered\n");
                    }
                    else {
                        break;
                    }
                }
                catch(InputMismatchException e) {
                    System.out.println("Error: Value entered is not an integer\n");
                    scanner.nextLine();
                }
            }
            while(true) {
                try {
                    System.out.println("Day (Enter digit): ");
                    day = scanner.nextInt();
                    if(day < 1 || day > days_in_month[month - 1]) {
                        System.out.println("Error: Invalid day was entered\n");
                    }
                    else {
                        break;
                    }
                }
                catch(InputMismatchException e) {
                    System.out.println("Error: Value entered is not an integer\n");
                    scanner.nextLine();
                }
            }

            itemDetailsList = new ArrayList<>();
            Collections.addAll(itemDetailsList, supplierid, itemid, itemname, qty,
                    price, totalpriceofitem, day, month, year);
            itemsList2D.add(itemDetailsList);

            totalprice += totalpriceofitem;

            while(loop_choice4 == true) {
                try {
                    System.out.println("Do you want to add another item? Enter '1' for Yes, '2' for No.");
                    int choice = scanner.nextInt();

                    if(choice == 1) {
                        loop_choice4 = false;
                        scanner.nextLine();
                    }
                    else if(choice == 2) {
                        loop_choice4 = false;
                        loop_choice2 = false;
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

        // Write the PR details to PR txt file
        try {
            FileWriter fw = new FileWriter("PurchaseRequisition.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.print(PRid + "," + PRdate + "," + SMid + "," + SMname + "," + totalprice + ",");
            
            for(ArrayList<Object> details : itemsList2D) {
                for(Object j : details) {
                    pw.print(j + ",");
                }
            }
            pw.println();
            pw.flush();
            pw.close();
            bw.close();
            fw.close();
            
            System.out.println("\nPurchase Requisition is saved successfully!");
        }
        catch(IOException e) {
            System.out.println("\nError: Purchase Requisition is not saved.");
        }
    }
    
    // Function to view Purchase Requisition.
    public void viewPR() {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> PRidList = new ArrayList<>();
        ArrayList<String> itemsList = new ArrayList<>();

        boolean found = false;
        String PRid;

        System.out.println("-----------------------------");
        System.out.println("VIEW PURCHASE REQUISITION");
        System.out.println("-----------------------------");

        try {
            FileReader fr = new FileReader("PurchaseRequisition.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentLine;

            while((currentLine = br.readLine()) != null) {
                String elements[] = currentLine.split(",");
                if(elements[0].startsWith("PR")) {
                    PRidList.add(elements[0]);
                }
            }
            br.close();
            fr.close();
        }
        catch(IOException e) {
            System.out.println("Error: PurchaseRequision.txt cannot be read.");
        }

        for(String i : PRidList) {
            System.out.println(i);
        }

        System.out.println("Please enter the PR ID to view:");
        PRid = scanner.nextLine();

        if(PRidList.contains(PRid)) {
            try {
                FileReader fr = new FileReader("PurchaseRequisition.txt");
                BufferedReader br = new BufferedReader(fr);
                String currentLine;

                while((currentLine = br.readLine()) != null) {
                    String[] elements = currentLine.split(",");
                    if(elements[0].equals(PRid)) {
                        
                        // Add relevant items to itemsList
                        for(int i = 0; i < elements.length; i++) {
                            itemsList.add(elements[i]);
                        }
                    }
                }
                br.close();
                fr.close();
            }
            catch(IOException e) {
                System.out.println("Error: PurchaseRequision.txt cannot be read.");
            }

            System.out.println("--------------------------");
            System.out.println("PURCHASE REQUISITION (PR)");
            System.out.println("--------------------------");
            System.out.println("SIGMA SDN BHD");
            System.out.println("Address: 12, Jalan Gembira, 81100 Johor Bahru, Malaysia.");
            System.out.println("PR ID: " + PRid);
            System.out.println("Date: " + itemsList.get(1));
            System.out.println("Sales Manager ID: " + itemsList.get(2));
            System.out.println("Sales Manager Name: " + itemsList.get(3));
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-13s %-10s %-15s %-11s %-12s %-12s %-10s\n",
                    "ITEM NO", "SUPPLIER ID", "ITEM ID", "ITEM NAME", "QUANTITY",
                    "PRICE (RM)", "TOTAL (RM)", "DATE REQUIRED");

            for(int i = 5; i < itemsList.size(); i += 9) {
                System.out.printf("%-10s %-13s %-10s %-15s %-11s %-12s %-12s %s-%s-%s\n",
                        (i / 9) + 1, itemsList.get(i), itemsList.get(i + 1), itemsList.get(i + 2),
                        itemsList.get(i + 3), itemsList.get(i + 4), itemsList.get(i + 5), itemsList.get(i + 6),
                        itemsList.get(i + 7), itemsList.get(i + 8));
            }
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("Total Amount: RM " + itemsList.get(4) + "\n");
        }
        else {
            System.out.println("Error: The PR ID entered cannot be found.");
        }
    }
    
    // Function to delete Purchase Requisition.
    public void deletePR() {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> PRidList = new ArrayList<>();

        String PRid;

        System.out.println("-------------------------------");
        System.out.println("DELETE PURCHASE REQUISITION");
        System.out.println("-------------------------------");

        try {
            FileReader fr = new FileReader("PurchaseRequisition.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentLine;

            while((currentLine = br.readLine()) != null) {
                String elements[] = currentLine.split(",");
                if(elements[0].startsWith("PR")) {
                    PRidList.add(elements[0]);
                }
            }
            br.close();
            fr.close();
        }
        catch(IOException e) {
            System.out.println("Error: PurchaseRequision.txt cannot be read.");
        }

        for(String i : PRidList) {
            System.out.println(i);
        }
        
        System.out.println("Please enter the PR ID to delete:");
        PRid = scanner.nextLine();

        // Read content of txt file into fileContent array
        String fileContent = new String();
        if(PRidList.contains(PRid)) {
            try {
                FileReader fr = new FileReader("PurchaseRequisition.txt");
                BufferedReader br = new BufferedReader(fr);
                String currentLine;

                while((currentLine = br.readLine()) != null) {
                    if(!currentLine.startsWith(PRid)) {
                        fileContent += currentLine + "\n";
                    }
                }
                br.close();
                fr.close();
            }
            catch(IOException e) {
                System.out.println("Error: PurchaseRequision.txt cannot be read.");
            }

            // Write the undeleted contents back to the txt file
            try {
                FileWriter fw = new FileWriter("PurchaseRequisition.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.print(fileContent);
                pw.flush();
                pw.close();
                bw.close();
                fw.close();
                
                System.out.println("\n" + PRid + " has been deleted successfully!");
            }
            catch(IOException e) {
                System.out.println("\nError: " + PRid + " is not deleted.");
            }
        }
        else {
            System.out.println("Error: The PR ID entered cannot be found.");
        }
    }
    
    // Function to edit Purchase Requisition.
    public void editPR() {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> PRidList = new ArrayList<>();

        ArrayList<Object> PRdetailsList = new ArrayList<>();
        ArrayList<ArrayList<Object>> itemsList2D = new ArrayList<>();  // ArrayList to store "itemDetailsList" 
        ArrayList<Object> itemDetailsList;
        ArrayList<Double> itemTotalPriceList = new ArrayList<>();

        ArrayList<String> itemsList = new ArrayList<>();

        boolean loop_choice1 = true;
        boolean loop_choice2 = true;

        String PRid;
        String itemid = null;
        int[] days_in_month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int newQuantity, newYear, newMonth, newDay;
        double newTotalPriceOfItem;
        double newTotalPrice = 0;

        System.out.println("-----------------------------");
        System.out.println("EDIT PURCHASE REQUISITION");
        System.out.println("-----------------------------");

        // Read PRid from txt file and store in PRidList ArrayList
        try {
            FileReader fr = new FileReader("PurchaseRequisition.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentLine;

            while((currentLine = br.readLine()) != null) {
                String elements[] = currentLine.split(",");
                if(elements[0].startsWith("PR")) {
                    PRidList.add(elements[0]);
                }
            }
            br.close();
            fr.close();
        }
        catch(IOException e) {
            System.out.println("Error: PurchaseRequision.txt cannot be read.");
        }

        for(String i : PRidList) {
            System.out.println(i);
        }

        System.out.println("Please enter the PR ID to edit:");
        PRid = scanner.nextLine();

        // Read PR from txt file and store selected PR details in itemsList then display selected PR
        if(PRidList.contains(PRid)) {
            try {
                FileReader fr = new FileReader("PurchaseRequisition.txt");
                BufferedReader br = new BufferedReader(fr);
                String currentLine;

                while((currentLine = br.readLine()) != null) {
                    String[] elements = currentLine.split(",");
                    if(elements[0].equals(PRid)) {

                        // Add relevant items to itemsList
                        for(int i = 0; i < elements.length; i++) {
                            itemsList.add(elements[i]);
                        }
                    }
                }     
                br.close();
                fr.close();
            }
            catch(IOException e) {
                System.out.println("Error: PurchaseRequision.txt cannot be read.");
            }

            System.out.println("--------------------------");
            System.out.println("PURCHASE REQUISITION (PR)");
            System.out.println("--------------------------");
            System.out.println("PR ID: " + PRid);
            System.out.println("Date: " + itemsList.get(1));
            System.out.println("Sales Manager ID: " + itemsList.get(2));
            System.out.println("Sales Manager Name: " + itemsList.get(3));
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-13s %-10s %-15s %-11s %-12s %-12s %-10s\n",
                    "ITEM NO", "SUPPLIER ID", "ITEM ID", "ITEM NAME", "QUANTITY",
                    "PRICE (RM)", "TOTAL (RM)", "DATE REQUIRED");
            
            for(int i = 5; i < itemsList.size(); i += 9) {
                System.out.printf("%-10s %-13s %-10s %-15s %-11s %-12s %-12s %s-%s-%s\n",
                        (i / 9) + 1, itemsList.get(i), itemsList.get(i + 1), itemsList.get(i + 2),
                        itemsList.get(i + 3), itemsList.get(i + 4), itemsList.get(i + 5), itemsList.get(i + 6),
                        itemsList.get(i + 7), itemsList.get(i + 8));
            }
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("Total Amount: RM " + itemsList.get(4));
            
            
            // Add PR details to PRdetailsList ArrayList
            for(int i = 0; i < 5; i++) {
                PRdetailsList.add(itemsList.get(i));
            }

            // Add item details to 2D ArrayList
            for(int i = 5; i < itemsList.size(); i += 9) {
                itemDetailsList = new ArrayList<>();
                itemDetailsList.add(itemsList.get(i));
                itemDetailsList.add(itemsList.get(i + 1));
                itemDetailsList.add(itemsList.get(i + 2));
                itemDetailsList.add(Integer.valueOf(itemsList.get(i + 3)));
                itemDetailsList.add(Double.valueOf(itemsList.get(i + 4)));
                itemDetailsList.add(Double.valueOf(itemsList.get(i + 5)));
                itemDetailsList.add(Integer.valueOf(itemsList.get(i + 6)));
                itemDetailsList.add(Integer.valueOf(itemsList.get(i + 7)));
                itemDetailsList.add(Integer.valueOf(itemsList.get(i + 8)));

                itemsList2D.add(itemDetailsList);
            }
 
            System.out.println("\nPlease specify the item to edit: ");
            
            while(loop_choice1 == true) {
                System.out.println("Item ID: ");
                itemid = scanner.nextLine();

                if(itemsList.contains(itemid)) {
                    loop_choice1 = false;
                }
                else {
                    System.out.println("Error: The Item ID cannot be found.\n");
                }
            }

            System.out.println("\nPlease specify the item attribute to edit");

            while(loop_choice2 == true) {
                try {
                    System.out.println("Enter 1 for Quantity, 2 for Date Required: ");
                    int option = scanner.nextInt();

                    // Edit quantity, total price of item and total price of the PR
                    if(option == 1) {
                        loop_choice2 = false;

                        while(true) {
                            try {
                                System.out.println("Enter the new Quantity: ");
                                newQuantity = scanner.nextInt();
                                break;
                            }
                            catch(InputMismatchException e) {
                                System.out.println("Error: Value entered is not an integer\n");
                                scanner.nextLine();
                            }
                        }
                        for(int i = 0; i < itemsList2D.size(); i++) {
                            for(int j = 1; j < itemsList2D.get(i).size(); j += 9) {

                                if((itemsList2D.get(i).get(j)).equals(itemid)) {
                                    // Edit quantity of item
                                    itemsList2D.get(i).set(3, newQuantity);

                                    // Calculate new total price of item 
                                    newTotalPriceOfItem = newQuantity * ((double) itemsList2D.get(i).get(4));

                                    // Edit total price of item
                                    itemsList2D.get(i).set(5, newTotalPriceOfItem);
                                    break;
                                }
                            }
                            // Add total price of each item to "itemTotalPriceList" ArrayList
                            itemTotalPriceList.add((Double) itemsList2D.get(i).get(5));
                        }

                        // Calculate the total price of the PR
                        for(Double i : itemTotalPriceList) {
                            newTotalPrice += i;
                        }
                        PRdetailsList.set(4, newTotalPrice);
                    }
                    
                    // Edit date required of item in PR
                    else if(option == 2) {
                        loop_choice2 = false;

                        System.out.println("Please enter the new date required");
                        while(true) {
                            try {
                                System.out.println("Year (Enter digit): ");
                                newYear = scanner.nextInt();
                                break;
                            }
                            catch(InputMismatchException e) {
                                System.out.println("Error: Value entered is not an integer\n");
                                scanner.nextLine();
                            }
                        }
                        while(true) {
                            try {
                                System.out.println("Month (Enter digit): ");
                                newMonth = scanner.nextInt();
                                if(newMonth < 1 || newMonth > 12) {
                                    System.out.println("Error: Invalid month was entered\n");
                                }
                                else {
                                    break;
                                }
                            }
                            catch(InputMismatchException e) {
                                System.out.println("Error: Value entered is not an integer\n");
                                scanner.nextLine();
                            }
                        }
                        while(true) {
                            try {
                                System.out.println("Day (Enter digit): ");
                                newDay = scanner.nextInt();
                                if(newDay < 1 || newDay > days_in_month[newMonth - 1]) {
                                    System.out.println("Error: Invalid day was entered\n");
                                }
                                else {
                                    break;
                                }
                            }
                            catch(InputMismatchException e) {
                                System.out.println("Error: Value entered is not an integer\n");
                                scanner.nextLine();
                            }
                        }
                        
                        for(int i = 0; i < itemsList2D.size(); i++) {
                            for(int j = 1; j < itemsList2D.get(i).size(); j += 9) {
                                
                                if((itemsList2D.get(i).get(j)).equals(itemid)) {
                                    // Edit year
                                    itemsList2D.get(i).set(8, newYear);

                                    // Edit month
                                    itemsList2D.get(i).set(7, newMonth);

                                    // Edit day
                                    itemsList2D.get(i).set(6, newDay);
                                    break;
                                }
                            }
                        }
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

            // Generate the newLine with updated details
            String newLine = new String();
            for(Object i : PRdetailsList) {
                newLine += i + ",";
            }
            for(ArrayList<Object> items : itemsList2D) {
                for(Object i : items) {
                    newLine += i + ",";
                }
            }

            // Read contents of txt file into fileContent array
            String fileContent = new String();
            try {
                FileReader fr = new FileReader("PurchaseRequisition.txt");
                BufferedReader br = new BufferedReader(fr);
                String currentLine;

                while((currentLine = br.readLine()) != null) {
                    if(currentLine.startsWith(PRid)) {
                        fileContent += newLine + "\n";
                    }
                    else {
                        fileContent += currentLine + "\n";
                    }
                }
                br.close();
                fr.close();
            }
            catch(IOException e) {
                System.out.println("Error: PurchaseRequision.txt cannot be read.");
            }

            // Write the modified contents back to the txt file
            try {
                FileWriter fw = new FileWriter("PurchaseRequisition.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.print(fileContent);
                pw.flush();
                pw.close();
                bw.close();
                fw.close();

                System.out.println("\n" + PRid + " has been edited successfully!");
            }
            catch(IOException e) {
                System.out.println("\nError: " + PRid + " cannot be edited.");
            }
        }
        else {
            System.out.println("Error: The PR ID entered cannot be found.");
        }
    }
    
    // View Purchase Order
    public void viewPurchaseOrder(){
        
        System.out.println("--------------------------------------------");
        System.out.println("            VIEW PURCHASE ORDER             ");
        System.out.println("--------------------------------------------");   
        
        BufferedReader viewPurchaseOrderReader = null;
        
        try{
            viewPurchaseOrderReader = new BufferedReader(new FileReader("approvedPurchaseOrders.txt"));
            String viewOrderLine;
            
            while ((viewOrderLine = viewPurchaseOrderReader.readLine()) != null){
                System.out.println(viewOrderLine);
            }
            viewPurchaseOrderReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
