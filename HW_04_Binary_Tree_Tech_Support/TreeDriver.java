/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>TreeDriver</code> class creates an Tree where a user
* can view a tree from a .txt file using a UI in the terminal.
*
**/

import java.io.File;  //Needed for reading .txt file
import java.io.FileNotFoundException;  //Needed when reading the file
import java.util.Scanner;  //Needed for beginSession() and beginLabelSession()

public class TreeDriver{

  //Prints in terminal
  public static void main(String[] args){
    boolean exit = false;  //Needed to loop for menu to keep popping up

    Tree tree = null;  //Must initalize, so with null value

    //loop keeps program runnning until closes

    while(!exit){

      //Prints Menu
      System.out.print(String.format("\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n%-5s%-25s\n", "L - ", "Load a Tree", "H - ", "Begin a Help Session", "T - ", "Traverse in Preorder", "F - ", "Find Questions from Label", "Q - ", "Quit"));

      Scanner sc = new Scanner(System.in);
      System.out.print("\nChoice> ");
      char operation = sc.nextLine().charAt(0);
      System.out.print("\n");

      //Operations
      if (operation == 'L' || operation == 'l'){
        System.out.print("Enter the file name> ");
        String fileName = sc.nextLine();
        System.out.print("\n");

        try{
          tree = new Tree(fileName);
          System.out.println("Tree loaded successfully!");
        }catch (FileNotFoundException e){
          System.out.println("The file was not found in this folder.");
        }
      }

      //Starts help session
      if (operation == 'H' || operation == 'h'){
        if (tree == null){
          System.out.println("There is no tree set up.");
        }
        else{
          tree.tempRoot = tree.mainRoot;

          //NOTE:  For extra credit
          tree.priorRoot = tree.mainRoot;

          tree.beginSession();
        }
      }

      //Traverses in preorder
      if (operation == 'T' || operation == 't'){
        if (tree == null){
          System.out.println("There is no tree set up.");
        }
        else{
          try{
            tree.preOrder();
          }catch (NullPointerException e){
            System.out.println("There is an issue with the imported file with errors listed above.");
          }
        }
      }

      //NOTE:  For extra Credit
      //See Tree.java for same documentation
      
      /**
      * This option is similar to the beginSession(), except when customers have a
      * specific label in mind (if that label were actually a situation/product).
      * After the selection of the label, it will begin asking questions starting
      * from the root of the tree;
      * if there is no tree set up, display an error message. When displaying a
      * question with answers, display each answer on a separate line with a number
      * associated with it, similar to a menu. Include another option, 0 (zero) to
      * exit the help session and return to the main menu.   O(nlogn) since it's
      * parsing through a tree.
      *
      **/

      if (operation == 'F' || operation == 'f'){
        System.out.print("Enter the label> ");
        String label = sc.nextLine();
        System.out.print("\n");

        if (tree == null){
          System.out.println("There is no tree set up.");
        }else{
          tree.beginLabelSession(label);
        }
      }

      //Closes the Program
      if (operation == 'Q' || operation == 'q'){
        System.out.println("Thank you for using our services!");
        break;
      }
    }

  }
}
