package vivin_code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SalesManager {

    private String[] args;
    
    public void salesManagerViewPurchaseOrder(){
        
        BufferedReader salesManagerViewPurchaseOrderReader = null;
        BufferedReader salesManagerUserInput = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            salesManagerViewPurchaseOrderReader = new BufferedReader(new FileReader("ApprovedRequisition.txt"));
            String salesManagerViewOrderLine;
            
            while ((salesManagerViewOrderLine = salesManagerViewPurchaseOrderReader.readLine()) != null){
                System.out.println(salesManagerViewOrderLine);
            }
            System.out.println("Enter 'satisfy' to continue the system or enter 'quit' to quit the whole system: ");
            String salesManagerPurchaseOrderInput = salesManagerUserInput.readLine();
            
            while (!salesManagerPurchaseOrderInput.equalsIgnoreCase("satisfy") && !salesManagerPurchaseOrderInput.equalsIgnoreCase("quit")){
                System.out.println("Invalid Input. Please enter 'satisfy' or 'quit': ");
                salesManagerPurchaseOrderInput = salesManagerUserInput.readLine();
            }
            if (salesManagerPurchaseOrderInput.equals("satisfy")){
                System.out.println("Now you will be entering back to the system\n");

            }
            else if (salesManagerPurchaseOrderInput.equalsIgnoreCase("quit")){
                System.out.println("You choose to quit the system");
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if (salesManagerViewPurchaseOrderReader != null){
                    salesManagerViewPurchaseOrderReader.close();
                }
                salesManagerUserInput.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
}
