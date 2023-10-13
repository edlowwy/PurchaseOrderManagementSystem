package oodj_assignment;

import java.io.*;
import java.util.*;


public class PurchaseManager extends User {
    
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
    
    //View Supplier List 
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
    
    // Function to view Purchase Requisition.
    public void viewPR() {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> PRidList = new ArrayList<>();
        ArrayList<String> itemsList = new ArrayList<>();

        boolean found = false;
        String PRid;

        System.out.println("-------------------------------");
        System.out.println("VIEW PR (PURCHASE REQUISITION)");
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
            System.out.println("Total Amount: RM " + itemsList.get(4));
        }
        else {
            System.out.println("Error: The PR ID entered cannot be found.");
        }
    }
    
    // Add Purchase Order
    public void addPurchaseOrder(){
        
        String purchaseManagerID;
        String purchaseManagerName;
        
        System.out.println("--------------------------------------------");
        System.out.println("             ADD PURCHASE ORDER             ");
        System.out.println("--------------------------------------------");   
        
        Scanner purchaseOrderScanner = new Scanner(System.in);
        
        try (BufferedReader purchaseOrderReader = new BufferedReader(new FileReader("PurchaseRequisition.txt"));
                BufferedWriter purchaseOrderWriter = new BufferedWriter(new FileWriter("approvedPurchaseOrders.txt"))) {
            
            System.out.println("Enter the Purchase Manager ID: ");
            purchaseManagerID = purchaseOrderScanner.nextLine();
        
            System.out.println("Enter the Purchase Manager Name: ");
            purchaseManagerName = purchaseOrderScanner.nextLine();
            
            String purchaseOrderLine;
            while ((purchaseOrderLine = purchaseOrderReader.readLine()) != null){
                if (purchaseOrderLine.trim().isEmpty()){
                    continue;
                }
                
                System.out.println("Current Line: ");
                System.out.println(purchaseOrderLine);
                
                System.out.println("Enter the Purchase Order ID: ");
                String orderID = purchaseOrderScanner.nextLine();
                
                System.out.println("Approve or reject (A/r): ");
                String decision = purchaseOrderScanner.nextLine();
                
                if(decision.equalsIgnoreCase("A")){
                    purchaseOrderLine += orderID + "," + purchaseManagerID + "," + purchaseManagerName + ",Approve";
                    purchaseOrderWriter.write(purchaseOrderLine + "\n");
                    System.out.println("Line Approved");
                }
                else if (decision.equalsIgnoreCase("R")){
                    purchaseOrderLine += orderID + "," + purchaseManagerID + "," + purchaseManagerName + ",Reject";
                    purchaseOrderWriter.write(purchaseOrderLine + "\n");
                    System.out.println("Line Rejected");
                }
                else{
                    System.out.println("Invalid decison. Line not added");
                }
            }
            
            System.out.println("All lines processed.");
         
        }catch (IOException e){
            System.err.println("Error reading or writing file: " + e.getMessage());
        }
    }
    
    // Edit Purchase Order
    public void editPurchaseOrder(){
        
        System.out.println("--------------------------------------------");
        System.out.println("            EDIT PURCHASE ORDER             ");
        System.out.println("--------------------------------------------");   
        
        try {
            File inputFile = new File("approvedPurchaseOrders.txt");
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            ArrayList<String> lines = new ArrayList<>();
            String line;
            int lineNumber = 1;

            // Read and store the lines from the file
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }

            bufferedReader.close();

            // Prompt the user to select a line to edit
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the line number you want to edit:");
            int lineToEdit = scanner.nextInt();

            // Check if the selected line is valid
            if (lineToEdit < 1 || lineToEdit > lines.size()) {
                System.out.println("Invalid line number.");
                return;
            }

            // Get the line to edit
            String selectedLine = lines.get(lineToEdit - 1);
            String[] fields = selectedLine.split(",");
            String approvalStatus = fields[fields.length - 1].trim();

            // Toggle the approval status
            if (approvalStatus.equalsIgnoreCase("Approve")) {
                approvalStatus = "Reject";
            } else if (approvalStatus.equalsIgnoreCase("Reject")) {
                approvalStatus = "Approve";
            }

            // Update the approval status in the selected line
            fields[fields.length - 1] = approvalStatus;

            // Reconstruct the updated line
            selectedLine = String.join(",", fields);

            // Update the line in the ArrayList
            lines.set(lineToEdit - 1, selectedLine);

            // Write the updated content back to the file
            FileWriter fileWriter = new FileWriter(inputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String updatedLine : lines) {
                bufferedWriter.write(updatedLine + "\n");
            }

            bufferedWriter.close();
            System.out.println("Line " + lineToEdit + " has been updated.");
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Delete Purchase Order
    public void deletePurchaseOrder(){
        
        System.out.println("----------------------------------------------");
        System.out.println("             DELETE PURCHASE ORDER            ");
        System.out.println("----------------------------------------------");  
        
        Scanner deletePurchaseOrderScanner  = new Scanner(System.in);
        
        System.out.println("Enter the Purchase Order ID to delete: ");
        String purchaseOrderIdDelete = deletePurchaseOrderScanner.nextLine();
        
        try {
            BufferedReader deletePurchaseOrderReader = new BufferedReader(new FileReader("approvedPurchaseOrders.txt"));
            BufferedWriter deletePurchaseOrderWriter = new BufferedWriter(new FileWriter("temp.txt"));
            
            String line;
            
            boolean deleted = false;
            
            while ((line = deletePurchaseOrderReader.readLine()) != null){
                if (line.contains(purchaseOrderIdDelete)){
                    deleted = true;
                }else{
                    deletePurchaseOrderWriter.write(line + "\n");
                }
            }
            if(deleted){
                System.out.println("Deleted the line with Purchase Order ID: " + purchaseOrderIdDelete);
            }else{
                System.out.println("Purchase Order ID not found.");
            }
            deletePurchaseOrderWriter.close();
            deletePurchaseOrderReader.close();
        }catch (IOException e){
            System.out.println("Error reading or writing files: " + e.getMessage());
        }
        
        File PurchaseOrderTempFile = new File("temp.txt");
        File approvedPurchaseOrdersFile = new File("approvedPurchaseOrders.txt");
        
        approvedPurchaseOrdersFile.delete();
        if (PurchaseOrderTempFile.renameTo(approvedPurchaseOrdersFile)){
            System.out.println("File updated successfully.");
            
        }else{
            System.out.println("Error: The Purchase Order file is not updated.");
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
