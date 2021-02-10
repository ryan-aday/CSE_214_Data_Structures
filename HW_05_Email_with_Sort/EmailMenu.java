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

//All needed for creating/reading/writing .obj files
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EmailMenu{

  private static Mailbox mailbox;
  private static Folder targetFolder;

  //Prints in terminal
  public static void main(String[] args){

    Mailbox mailbox = null;
    Folder targetFolder = null;

    //Initializes Mailbox early
    //Needed for subMenu, parameter is
    //arbitrary

    try{
      //For reading a file that exists

      File file = new File("mailbox.obj");
      //If file doesn't exist
      if (!file.exists()) {
        file.createNewFile();
        System.out.println("New mailbox.obj file has been created.");

        //Writes after the file is created
        mailbox = new Mailbox();

        FileOutputStream fos = new FileOutputStream("mailbox.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(mailbox);
        oos.close();

        //if file exists
      }else{
        FileInputStream fis = new FileInputStream("mailbox.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        mailbox = (Mailbox) ois.readObject();
        ois.close();

        System.out.println("Existing mailbox read.");

      }

    }catch (FileNotFoundException e){
      System.out.println("mailbox.obj was never instantiated and/or found.");
    }catch (EOFException e) {
      System.out.println("mailbox.obj has been fully read.");
    }catch (IOException e) {
      System.out.println("Errors with reading/writing the file.");
    }catch (ClassNotFoundException e) {
      System.out.println("A class needed for the email menu to work is not present.");
    }

    boolean exit = false;  //Needed to loop for menu to keep popping up
    boolean subMenu = false;
    //loop keeps program runnning until closes

    while(!exit){

      if (subMenu == false){
        //Prints all folders
        mailbox.printAllFolders();
        //targetFolder = mailbox.getFolder("Inbox");

        //Prints Menu
        System.out.print(String.format("\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n", "A - ", "Add Folder", "R - ", "Remove folder", "C - ", "Compose Email", "F - ", "Open folder",  "I - ", "Open Inbox", "T - ", "Open Trash",  "E - ", "Empty Trash", "Q - ", "Quit" ));

        Scanner sc = new Scanner(System.in);
        System.out.print("\nPlease Select a Menu Option: ");
        char operation = sc.nextLine().charAt(0);
        System.out.print("\n");

        //Operations

        //Adds a folder- works and tested
        if (operation == 'A' || operation == 'a'){
          try{
            System.out.print("Enter folder name: ");
            String name = sc.nextLine();

            Folder folder = new Folder(name);
            mailbox.addFolder(folder);
          }
          catch (AddingFolderWithSharedNameException e){
            System.out.println("There already exists a folder with the same name.");
          }
        }

        //Removes a folder - works and tested
        if (operation == 'R' || operation == 'r'){
          System.out.print("Enter folder name: ");
          String name = sc.nextLine();

          mailbox.deleteFolder(name);
        }

        //Compses an email
        if (operation == 'C' || operation == 'c'){
          mailbox.composeEmail();
          System.out.println("Attempted to compose email to Inbox; check for error statements above.");
        }

        //View any folder
        if (operation == 'F' || operation == 'f'){
          System.out.print("Enter folder name: ");
          String name = sc.nextLine();

          targetFolder = mailbox.getFolder(name);
          subMenu = true;
        }

        //View Inbox
        if (operation == 'I' || operation == 'i'){
          targetFolder = mailbox.getFolder("Inbox");
          subMenu = true;
        }

        //View Trash
        if (operation == 'T' || operation == 't'){
          targetFolder = mailbox.getFolder("Trash");
          subMenu = true;
        }

        //Empty Trash
        if (operation == 'E' || operation == 'e'){
          mailbox.clearTrash();
        }


        //Closes the Program
        if (operation == 'Q' || operation == 'q'){
          try{
            //Replaces file content with this
            FileOutputStream fos = new FileOutputStream("mailbox.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mailbox);
            oos.close();

            System.out.println("Program successfully exited and program saved.");
            exit = true;
            break;

          }catch (FileNotFoundException e){
            System.out.println("mailbox.obj was never instantiated and/or found.");
          }catch (EOFException e) {
            System.out.println("mailbox.obj has been fully read.");
          }catch (IOException e) {
            System.out.println("Errors with reading/writing the file.");
          }//Prints Menu
          System.out.print(String.format("\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n", "A - ", "Add Folder", "R - ", "Remove folder", "C - ", "Compose Email", "F - ", "Open folder",  "I - ", "Open Inbox", "T - ", "Open Trash",  "E - ", "Empty Trash", "Q - ", "Quit" ));

        }
      }
      //For subMenu
      else{
        targetFolder.printAllEmails();

        //Prints Menu
        System.out.print(String.format("\n%-5s%-35s\n%-5s%-35s\n%-5s%-35s\n%-5s%-35s\n%-5s%-35s\n%-5s%-35s\n%-5s%-35s\n%-5s%-35s\n", "M - ", "Move Email", "D - ", "Delete email", "V - ", "View Email Contents", "SA - ", "Sort by subject line in ascending order",  "SD - ", "Sort by subject line in descending order", "DA - ", "Sort by date in ascending order",  "DD - ", "Sort by date in descending order", "R - ", "Return to mailbox" ));
        //NOTE: Remember that the indexes are advanced by 1.

        Scanner sc = new Scanner(System.in);
        System.out.print("\nPlease Select a Menu Option: ");
        String subOperation = sc.nextLine().trim();
        System.out.print("\n");

        //Move email
        if (subOperation.equals("M") || subOperation.equals("m")){
          System.out.print("Enter email index: ");
          int index = Integer.parseInt(sc.nextLine());

          mailbox.printAllFolders();
          System.out.print("\nEnter folder to move to: ");
          String folderName = sc.nextLine();

          mailbox.moveEmail(targetFolder.getEmail(index - 1), mailbox.getFolder(folderName));
        }

        //Delete email
        if (subOperation.equals("D") || subOperation.equals("d")){
          System.out.print("Enter email index: ");
          int index= sc.nextInt();

          mailbox.deleteEmail(targetFolder.getEmail(index - 1));
        }

        //View email
        if (subOperation.equals("V") || subOperation.equals("v")){
          System.out.print("Enter email index: ");
          int index= sc.nextInt();

          Email emailViewed = targetFolder.getEmail(index - 1);
          System.out.println("To: " + emailViewed.getTo());
          System.out.println("CC: " + emailViewed.getCC());
          System.out.println("BCC: " + emailViewed.getBCC());
          System.out.println("Subject: " + emailViewed.getSubject());
          System.out.println("Body: " + emailViewed.getBody());

          System.out.println("");
        }

        //sortBySubjectAscending
        if (subOperation.equals("SA") || subOperation.equals("sa")){
          targetFolder.sortBySubjectAscending();
        }
        //sortBySubjectDescending
        if (subOperation.equals("SD") || subOperation.equals("sd")){
          targetFolder.sortBySubjectDescending();
        }
        //sortByDateAscending
        if (subOperation.equals("DA") || subOperation.equals("da")){
          targetFolder.sortByDateAscending();
        }
        //sortBySubjectDescending
        if (subOperation.equals("DD") || subOperation.equals("DD")){
          targetFolder.sortByDateDescending();
        }

        //Returns to mailbox
        if (subOperation.equals("R") || subOperation.equals("r")){
          subMenu = false;
        }

      }

    }

  }

}
