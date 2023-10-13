package vivin_code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;



public class GeneratePurchaseOrder {
    
    private static String purchaseManagerID;
    private static String purchaseManagerName;
    
    public void promptForManagerInfo(){
        Scanner promptForManagerInfoScanner = new Scanner(System.in);
        
        System.out.println("Enter the Purchase Manager ID: ");
        purchaseManagerID = promptForManagerInfoScanner.nextLine();
        
        System.out.println("Enter the Purchase Manager Name: ");
        purchaseManagerName = promptForManagerInfoScanner.nextLine();
        
        
    }
    
    
    public void purchaseOrderPage(){
        Scanner purchaseOrderScanner = new Scanner(System.in);
        
        try (BufferedReader purchaseOrderReader = new BufferedReader(new FileReader("C:\\Users\\user\\OneDrive\\Desktop\\PurchaseRequisition.txt"));
             BufferedWriter purchaseOrderWriter = new BufferedWriter(new FileWriter("approvedPurchaseOrders.txt"))) {
            
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
    
    public void editPurchaseOrderPage(){
        try {
            // Replace with the path to your approvedPurchaseOrders.txt file
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
            System.out.print("Enter the line number you want to edit: ");
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
    
    public void deletePurchaseOrder(){
        Scanner deletePurchaseOrderScanner  = new Scanner(System.in);
        
        System.out.println("Enter the Purchase Order ID to delete: ");
        String purchaseOrderIdDelete = deletePurchaseOrderScanner.nextLine();
        
        try(BufferedReader deletePurchaseOrderReader = new BufferedReader(new FileReader("approvedPurchaseOrders.txt"));
                BufferedWriter deletePurchaseOrderWriter = new BufferedWriter(new FileWriter("temp.txt"))){
            
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
        }catch (IOException e){
            System.out.println("Error reading or writign files: " + e.getMessage());
        }
        
        File deletePurchaseOrderTempFile = new File("temp.txt");
        File approvedPurchaseOrdersFile = new File("approvedPurchaseOrders.txt");
        
        if (deletePurchaseOrderTempFile.renameTo(approvedPurchaseOrdersFile)){
            System.out.println("File updated successfully.");
            
        }else{
            System.out.println("Success");
        }
    }


    
    
    public void viewPurchaseOrder(){
        
        BufferedReader viewPurchaseOrderReader = null;
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            viewPurchaseOrderReader = new BufferedReader(new FileReader("C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\AssingmentTwo\\ApprovedRequisition.txt"));
            String viewOrderLine;
            
            while ((viewOrderLine = viewPurchaseOrderReader.readLine()) != null){
                System.out.println(viewOrderLine);
            }
            
            System.out.println("Enter 'satisfy' to continue the system or 'quit' to exit: ");
            String purchaseOrderInput = userInputReader.readLine();
            
            while (!purchaseOrderInput.equalsIgnoreCase("satisfy") && !purchaseOrderInput.equalsIgnoreCase("quit")){
                System.out.println("Invalid Input. Please enter 'satisfy' or 'quit': ");
                purchaseOrderInput = userInputReader.readLine();
            }
            if (purchaseOrderInput.equals("satisfy")){
                System.out.println("Now you will be entering back to the system\n");
                        
            }
            else if(purchaseOrderInput.equalsIgnoreCase("quit")){
                System.out.println("You choose to quit the system");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (viewPurchaseOrderReader != null){
                    viewPurchaseOrderReader.close();
                }
                userInputReader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        
    }
    
    
}

