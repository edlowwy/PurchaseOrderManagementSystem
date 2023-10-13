package vivin_code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Login {
    
    public void salesManagerLogInClass(){
        String salesManagerFileName = "salesManagerDetails.txt";
        
        boolean salesManagerLoggedIn = false;
        
        while(!salesManagerLoggedIn){
            try(BufferedReader salesManagerReader = new BufferedReader(new FileReader(salesManagerFileName))){
                Scanner salesManagerLogInScanner = new Scanner(System.in);
                
                System.out.println("Enter the username: ");
                String salesManagerCorrectUsername = salesManagerLogInScanner.nextLine();
                
                System.out.println("Enter the password:");
                String salesManagerCorrectPassword = salesManagerLogInScanner.nextLine();
                
                String salesManagerLine;
                while ((salesManagerLine = salesManagerReader.readLine()) != null){
                    String[] salesManagerUserData = salesManagerLine.split(",");
                    if (salesManagerUserData.length >= 5){
                        String salesManagerName = salesManagerUserData[0].trim();
                        String salesManagerUsernameFromFile = salesManagerUserData[2].trim();
                        String salesManagerPasswordFromFile = salesManagerUserData[3].trim();
                        String roleName = salesManagerUserData[4].trim();
                        
                        if(salesManagerUsernameFromFile.equals(salesManagerCorrectUsername) && salesManagerPasswordFromFile.equals(salesManagerCorrectPassword)){
                            salesManagerLoggedIn = true;
                            System.out.println("Log in Successful " + salesManagerUserData[0].trim());
                            break;
                        }
                    }
                }
                if (!salesManagerLoggedIn){
                    System.out.println("Log in fail. incorrect username and password. Please try again");
                }
            }catch(IOException e){
                System.out.println("An error has occured" + e.getMessage());
            }
        }
    }
    public void purchaseManagerLogInClass(){
        String purchaseManagerFileName = "purchaseManagerDetails.txt";
        
        boolean purchaseManagerLoggedIn = false;
        
        while(!purchaseManagerLoggedIn){
            try (BufferedReader purchaseManagerReader = new BufferedReader(new FileReader(purchaseManagerFileName))){
                Scanner purchaseManagerLogInScanner = new Scanner(System.in);
                
                System.out.println("Enter the username: ");
                String purchaseManagerCorrectUsername = purchaseManagerLogInScanner.nextLine();
                
                System.out.println("Enter the password: ");
                String purchaseManagerCorrectPassword = purchaseManagerLogInScanner.nextLine();
                
                String purchaseManagerLine;
                while ((purchaseManagerLine = purchaseManagerReader.readLine()) != null){
                    String[] purchaseManagerUserData = purchaseManagerLine.split(",");
                    if (purchaseManagerUserData.length >= 5){
                        String purchaseManagerName = purchaseManagerUserData[0].trim();
                        String purchaseManagerUsernameFromFile = purchaseManagerUserData[2].trim();
                        String purchaseManagerPasswordFromFile = purchaseManagerUserData[3].trim();
                        String whichRole = purchaseManagerUserData[4].trim();
                        
                        if (purchaseManagerUsernameFromFile.equals(purchaseManagerCorrectUsername) && purchaseManagerPasswordFromFile.equals(purchaseManagerCorrectPassword)) {
                            purchaseManagerLoggedIn = true;
                            System.out.println("Login successful" + purchaseManagerUserData[0].trim());
                            break;
                        }
                    }
                }
                if (!purchaseManagerLoggedIn){
                    System.out.println("Login Fail. Incorrect username or password.Please Try Again");
                }
            }catch (IOException e){
                System.out.println("An error has occured" + e.getMessage());
            }
        }
    }
    /**
    public void salesManagerLogInClass(){
        
        String expectedUsername = "";
        String expectedPassword = "";
        
        try(BufferedReader salesManagerBufferedReader = new BufferedReader(new FileReader("salesManagerDetails.txt"))){
            String salesManagerLine;
            while ((salesManagerLine = salesManagerBufferedReader.readLine()) != null){
               if (salesManagerLine.contains("Username:")){
                   expectedUsername = salesManagerLine.replace("Username:", "").trim();
               }
               else if (salesManagerLine.contains("Password:")){
                   expectedPassword = salesManagerLine.replace("Password:", "").trim();
               }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the username: ");
        String salesManagerUsername = scanner.nextLine();
        
        System.out.println("Enter the password: ");
        String salesManagerPassword = scanner.nextLine();
        
        if(salesManagerUsername.equals(expectedUsername) && salesManagerPassword.equals(expectedPassword)){
            System.out.println("Log In Successful. Welcome, " + expectedUsername + "!");
        }else{
            System.out.println("Invalid username or password");
        }
    }
    * */
    
    
}
