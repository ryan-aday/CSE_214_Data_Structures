/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>DpeartmentStore</code> class creates an ItemList where a user
* can manipulate items in a store using a UI in the terminal.
*
**/


import java.util.Scanner;  //Needed for scanning the terminal for user input.

public class DepartmentStore{

  //Prints in terminal
  public static void main(String[] args){
    ItemList list = new ItemList();

    boolean exit = false;  //Needed to loop for menu to keep popping up

    //loop keeps program runnning until closes

    while(!exit){

      //Prints Menu
      System.out.print(String.format("\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n", "C - ", "Clean Store", "I - ", "Insert item into list", "L - ", "List By Location", "M - ", "Move an Item in the Store",  "O - ", "Checkout", "P - ", "Print All Items in the Store", "R - ", "Print By RFID Tag Number", "U - ", "Update Inventory System", "Q - ", "Quit" ));

      Scanner sc = new Scanner(System.in);
      System.out.print("\nPlease Select a Menu Option: ");
      char operation = sc.nextLine().charAt(0);
      System.out.print("\n");

      //Operations

      //Inserts an item
      if (operation == 'I' || operation == 'i'){
        //Asks for parameters
        System.out.print("Enter the item name: ");
        String name = sc.nextLine();

        System.out.print("Enter the RFID Tag: ");
        String rfid = sc.nextLine();

        System.out.print("Enter the original location: ");
        String location = sc.nextLine();

        System.out.print("Enter the price: ");
        double price = sc.nextDouble();

        try{
          list.insertInfo(name, rfid, price, location);
        }catch (RFIDCharsException e){
          System.out.println("The RFID must contain A-F or 0-9 only.");
        }catch (RFIDLengthException e){
          System.out.println("The RFID must be 9 characters long.");
        }catch (LocationStartException e){
          System.out.println("The original location must start with 's'.");
        }catch (LocationLengthException e){
          System.out.println("The original location must be 6 characters long.");
        }catch (LocationCharsException e){
          System.out.println("The characters after the 's' must be numerical digits.");
        }catch (IllegalArgumentException e){
          System.out.println("Initial position can't be \"out\".");
        }
      }

      //Moves an Item (Not to "out" though)
      if (operation == 'M' || operation == 'm'){
        System.out.print("Enter the RFID Tag: ");
        String rfid = sc.nextLine();

        System.out.print("Enter the original location: ");
        String originalLocation = sc.nextLine();

        System.out.print("Enter the new location: ");
        String newLocation = sc.nextLine();

        System.out.print("\n");

        try{
          if (list.moveItem(rfid, originalLocation, newLocation) == true){
            System.out.println("Item was found in its original location.");
          }else System.out.println("Item was not found in its original location.");

        }catch (RFIDCharsException e){
          System.out.println("The RFID must contain A-F or 0-9 only.");
        }catch (RFIDLengthException e){
          System.out.println("The RFID must be 9 characters long.");
        }catch (LocationStartException e){
          System.out.println("The original location must start with 's'.");
        }catch (LocationLengthException e){
          System.out.println("The cart location must be 4 characters long and the shelf location 6 characters long.");
        }catch (LocationCharsException e){
          System.out.println("The characters after the 's' or 'c' must be numerical digits.");
        }catch (IllegalArgumentException e){
          System.out.println("The original and current location can't be \"out\" and must start with either a 'c' or an 's'.");
        }
      }

      //Prints all Items in Store Based on Location
      if (operation == 'L' || operation == 'l'){

        System.out.print("Enter the location: ");
        String location = sc.nextLine();

        System.out.print("\n");

        try{
          list.printByLocation(location);
        }catch(LocationCharsException e){
          System.out.println("One of the items has an incorrect character format of a location.");
        }catch(LocationLengthException e){
          System.out.println("The cart location must be 4 characters long and the shelf location 6 characters long.");
        }catch (IllegalArgumentException e){
          System.out.println("The location must start with either a 'c' or an 's'.");
        }
      }

      //Prints all Items in Store
      if (operation == 'P' || operation == 'p'){
        try{
          list.printAll();
        }//These should never be thrown
        catch(LocationCharsException e){
          System.out.println("One of the items has an incorrect character format of a location.");
        }catch(LocationLengthException e){
          System.out.println("One of the items has an incorrect length in the format of their location.");
        }
      }

      //NOTE:  Extra Credit
      //Prints based off of RFID tag
      if (operation == 'R' || operation == 'r'){

        System.out.print("Enter the RFID Tag Number: ");
        String rfidTag = sc.nextLine();

        System.out.print("\n");

        try{
          list.printByRFID(rfidTag);
        }catch(RFIDCharsException e){
          System.out.println("The RFID tag must only have characters of 0 to 9.");
        }catch(RFIDLengthException e){
          System.out.println("The RFID tag must be 9 characters long.");
        }
      }

      //Prints a table with all items to be output and the dollar
      if (operation == 'O' || operation == 'o'){

        System.out.print("Enter the Cart Number: ");
        String cartNumber = sc.nextLine();

        System.out.print("\n");

        try{
          double result = list.checkOut(cartNumber);
          System.out.println("\nThe total cost for all merchandise for cart " + cartNumber.substring(1 , 4) + " was $" + result + ".");
        }catch(NotCartException e){
          System.out.println("The cart number must start with a 'c'.");
        }catch(LocationCharsException e){
          System.out.println("The cart number must have characters of 0 to 9 trailing the 'c'.");
        }catch(LocationLengthException e){
          System.out.println("The cart location must be 4 characters long.");
        }
      }

      //Cleans the store by putting all items not in their original location to their og location
      if (operation == 'C' || operation == 'c'){

        try{
          list.cleanStore();
        }//These should never be thrown
        catch(LocationCharsException e){
          System.out.println("One of the items has an incorrect character format of a location.");
        }catch(LocationLengthException e){
          System.out.println("One of the items has an incorrect length in the format of their location.");
        }
      }

      //Updates inventory system
      if (operation == 'U' || operation == 'u'){

        try{
          list.removeAllPurchased();
        }//These should never be thrown
        catch(LocationLengthException e){
          System.out.println("One of the items has an incorrect length in the format of their location.");
        }catch(LocationCharsException e){
          System.out.println("One of the items has an incorrect character format of a location.");
        }catch(LocationStartException e){
          System.out.println("One of the items' location does not start with a 'c' or an 's'.");
        }catch(RFIDCharsException e){
          System.out.println("One of the RFIDs has an incorrect character format.");
        }catch(RFIDLengthException e){
          System.out.println("One of the RFIDs has an incorrect length.");
        }
      }

      //Closes the Program
      if (operation == 'Q' || operation == 'q'){
        System.out.println("Goodbye!");
        break;
      }
    }

  }
}
