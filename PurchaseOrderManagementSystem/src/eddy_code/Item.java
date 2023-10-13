package eddy_code;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Item {

    private String itemid;
    private String itemname;
    private String supplierid;
    private String desc;
    private int qty;
    private int price;
    private String date;
    
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
        }
        
        
        boolean valid_date = false;
        while(!valid_date){
            System.out.println("Enter the date (DD/MM/YYYY): ");
            date = scanner.nextLine();            
            valid_date = date.matches("\\d{2}/\\d{2}/\\d{4}");
            
            if (!valid_date){
                System.out.println("Invalid item code, please refer to the format DD/MM/YYYY.");               
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
    
    
    //View Item List
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
    private void handleAddItemId(String[] supplierFields, Scanner scanner) {
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
    private void handleDeleteItemId(String[] supplierFields, Scanner scanner) {
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
    
    
}
