package ljy_code;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class editPR {
    
    public static void main(String args[]){
        editPR();
    }


    public static void editPR() {
        
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<String> PRidList = new ArrayList<>();
        
        ArrayList<Object> PRdetailsList = new ArrayList<>();
        ArrayList<ArrayList<Object>> itemsList2D = new ArrayList<>();  // Store "itemDetailsList" ArrayList
        ArrayList<Object> itemDetailsList;
        ArrayList<Double> itemTotalPriceList = new ArrayList<>();
        
        ArrayList<String> itemsList = new ArrayList<>();
        
        boolean loop_choice1 = true;
        boolean loop_choice2 = true;
        //boolean loop_choice3 = true;
        String itemid = null;
        int newQuantity, newYear, newMonth, newDay;
        double newTotalPriceOfItem;
        double newTotalPrice = 0;
        String newLine = new String();
        String fileContent = new String();
        
        System.out.println("-------------------------------");
        System.out.println("EDIT PR (PURCHASE REQUISITION)");
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
        catch (FileNotFoundException e) {
            System.out.println("Error: PurchaseRequision.txt cannot be found.");
        }
        catch(IOException e) {
            System.out.println("Error: PurchaseRequision.txt cannot be read.");
        }
        
        for(String i : PRidList) {
            System.out.println(i);
        }
        
        System.out.println("Please enter the PR ID to edit or enter 0 to exit.");
        String PRid = scanner.nextLine();
        
        // Read PR from txt file and display selected PR
        if(PRidList.contains(PRid)){
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
            }
            catch(FileNotFoundException e) {
                System.out.println("Error: PurchaseRequision.txt cannot be found.");
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
            
            for (int i = 5; i < itemsList.size(); i += 9) {
                System.out.printf("%-10s %-13s %-10s %-15s %-11s %-12s %-12s %s-%s-%s\n",
                                 (i/9) + 1, itemsList.get(i), itemsList.get(i + 1), itemsList.get(i + 2),
                                 itemsList.get(i + 3),itemsList.get(i + 4), itemsList.get(i + 5), itemsList.get(i + 6),
                                 itemsList.get(i + 7), itemsList.get(i + 8));
            }
            
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("Total Amount: RM " + itemsList.get(4));
            
            // Read itemDetails into 2D ArrayList
            try {
                FileReader fr = new FileReader("PurchaseRequisition.txt");
                BufferedReader br = new BufferedReader(fr);
                String currentLine;

                while((currentLine = br.readLine()) != null) {
                    String[] elements = currentLine.split(",");
                    if(elements[0].equals(PRid)) {
                        
                        for(int i = 0; i < 5; i++) {
                            PRdetailsList.add(elements[i]);
                        }
                        
                        // Add items details to 2D array
                        for(int i = 5; i < elements.length; i += 9) {
                            itemDetailsList = new ArrayList<>();
                            itemDetailsList.add(elements[i]);
                            itemDetailsList.add(elements[i+1]);
                            itemDetailsList.add(elements[i+2]);
                            itemDetailsList.add(Integer.valueOf(elements[i+3]));
                            itemDetailsList.add(Double.valueOf(elements[i+4]));
                            itemDetailsList.add(Double.valueOf(elements[i+5]));
                            itemDetailsList.add(Integer.valueOf(elements[i+6]));
                            itemDetailsList.add(Integer.valueOf(elements[i+7]));
                            itemDetailsList.add(Integer.valueOf(elements[i+8]));
                            
                            itemsList2D.add(itemDetailsList);
                        }
                    }
                }
            }
            catch(FileNotFoundException e) {
                System.out.println("Error: PurchaseRequision.txt cannot be found.");
            }
            catch(IOException e) {
                System.out.println("Error: PurchaseRequision.txt cannot be read.");
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
                            for(int j = 0; j < itemsList2D.get(i).size(); j++) {

                                if((itemsList2D.get(i).get(j)).equals(itemid)){                    
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
                                System.out.print("Year: ");
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
                                System.out.print("Month: ");
                                newMonth = scanner.nextInt();
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
                                newDay = scanner.nextInt();
                                break;
                            }
                            catch(InputMismatchException e) {
                                System.out.println("Error: Value entered is not an integer\n");
                                scanner.nextLine();
                            }
                        }
                        for(int i = 0; i < itemsList2D.size(); i++) {
                            for(int j = 0; j < itemsList2D.get(i).size(); j++) {

                                if((itemsList2D.get(i).get(j)).equals(itemid)){                    
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
                        System.out.println("Error: Invalid input.\n");
                    }
                }
                catch(InputMismatchException e) {
                    System.out.println("Error: Value entered is not an integer\n");
                    scanner.nextLine();
                }
            }
            
            // Generate the newLine with updated details
            for(Object i : PRdetailsList) {
                newLine += i + ",";
            }
            for(ArrayList<Object> items : itemsList2D) {
                for(Object j : items){
                    newLine += j + ",";
                }
            }
            
            // Read contents of file into fileContent array
            try {
                FileReader fr = new FileReader("PurchaseRequisition.txt");
                BufferedReader br = new BufferedReader(fr);
                
                String currentLine;

                while((currentLine = br.readLine()) != null) {
                    if(currentLine.startsWith(PRid)){
                        fileContent += newLine + "\n";
                    }
                    else {
                        fileContent += currentLine + "\n";
                    }
                }
                
                br.close();
                fr.close();
            }
            catch(FileNotFoundException e) {
                System.out.println("Error: PurchaseRequision.txt cannot be found.");
            }
            catch(IOException e) {
                System.out.println("Error: PurchaseRequision.txt cannot be read.");
            }
        
            System.out.println(fileContent);
        
            // Write the modified contents back to the file
            try {
                    FileWriter fw = new FileWriter("PurchaseRequisition.txt", false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.print(fileContent);

                    pw.flush();
                    pw.close();

                    System.out.println("\n" + PRid + " has been edited successfully!");
                }
            catch (IOException e) {
                System.out.println("\nError: " + PRid + " cannot be edited.");
            }
        }
        else if(PRid.equals("0")) {
            System.exit(0);
        }
        else {
            System.out.println("Error: The PR ID entered cannot be found.");
        }
    }
}
