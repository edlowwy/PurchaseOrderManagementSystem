package ljy_code;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DailyItemSales {
    
    public static void main(String args[]) {
        addDIS();
    }
    
    public static void addDIS() {
        
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
    
    
    public static void viewDIS() {
        
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
        System.out.printf("%-13s %-9s %-19s %-11s\n", "DATE", "ITEM ID", "ITEM NAME", "QUANTITY");
        
        for(int i = 0; i < dailyItemSalesList.size(); i += 4) {
            System.out.printf("%-13s %-9s %-19s %-11s\n",
                    dailyItemSalesList.get(i + 3), dailyItemSalesList.get(i), dailyItemSalesList.get(i + 1),
                    dailyItemSalesList.get(i + 2));
        }
    }
    
    
    public static void deleteDIS() {
        
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
        System.out.printf("%-13s %-9s %-19s %-11s\n", "DATE", "ITEM ID", "ITEM NAME", "QUANTITY");
        
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
    
    
    public static void editDIS() {
        
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
        System.out.printf("%-13s %-9s %-19s %-11s\n", "DATE", "ITEM ID", "ITEM NAME", "QUANTITY");
        
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
                    System.out.println("Please enter the new quantity:");
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
}
