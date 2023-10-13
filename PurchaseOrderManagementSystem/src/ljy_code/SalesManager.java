package ljy_code;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SalesManager {

    public static void main(String[] args) { // FOR TESTING
        viewPR();
    }

    public static void addPR() {

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
        int qty = 0;
        int year = 0;
        int month = 0;
        int day = 0;
        double price = 0;
        double totalprice = 0;

        System.out.println("------------------------------");
        System.out.println("ADD PR (PURCHASE REQUISITION)");
        System.out.println("------------------------------");

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

    
    public static void viewPR() {

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
            System.out.println("Address: 12, Jalan Kampar, 51200 Kuala Lumpur, Malaysia.");
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

    
    public static void deletePR() {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> PRidList = new ArrayList<>();

        String PRid;

        System.out.println("---------------------------------");
        System.out.println("DELETE PR (PURCHASE REQUISITION)");
        System.out.println("---------------------------------");

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
        
        System.out.println("Please enter the PR ID to delete or enter 0 to exit:");
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
        else if(PRid.equals("0")) {
            System.exit(0);
        }
        else {
            System.out.println("Error: The PR ID entered cannot be found.");
        }
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

        String PRid;
        String itemid = null;
        int newQuantity, newYear, newMonth, newDay;
        double newTotalPriceOfItem;
        double newTotalPrice = 0;

        System.out.println("-------------------------------");
        System.out.println("EDIT PR (PURCHASE REQUISITION)");
        System.out.println("-------------------------------");

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

        System.out.println("Please enter the PR ID to edit or enter 0 to exit.");
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
        else if(PRid.equals("0")) {
            System.exit(0);
        }
        else {
            System.out.println("Error: The PR ID entered cannot be found.");
        }
    }
}
