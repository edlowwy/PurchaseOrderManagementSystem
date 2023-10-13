package ljy_code;

import java.io.*;
import java.text.*;
import java.util.*;
  
class Item {
    String id;
    String name;
    int quantity;
    Date date;

    Item(String id, String name, int quantity, Date date) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.date = date;
    }
}

class EditTextFile {
        
        public static void main(String[] args) {

            String filePath = "DailyItemSales.txt";
            String itemIdToEdit = "I0001";
            String dateToCheck = "16-08-2023"; // The date to check
            String newQuantity = "10"; // The new quantity value

            List<Item> items = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                
                String currentLine;
                while ((currentLine = br.readLine()) != null) {
                    
                    String[] parts = currentLine.split(",");
                    String id = parts[0];
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    Date date = dateFormat.parse(parts[3]);

                    items.add(new Item(id, name, quantity, date));
                }
            }
            catch (IOException e) {
                System.out.println("Error");
            }
            catch (ParseException e) {
                System.out.println("Error");
            }

            boolean found = false;
            for (Item item : items) {
                if (item.id.equals(itemIdToEdit) && dateFormat.format(item.date).equals(dateToCheck)) {
                    item.quantity = Integer.parseInt(newQuantity);
                    found = true;
                    break;
                }
            }

            if(found == true) {
                
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                    for(Item item : items) {
                        bw.write(item.id + "," + item.name + "," + item.quantity + "," + dateFormat.format(item.date));
                        bw.newLine();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Quantity updated successfully!");
            }
            else {
                System.out.println("Item ID not found or date does not match.");
            }
    
        }

}
