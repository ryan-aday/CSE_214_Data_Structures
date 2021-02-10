/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>Tree</code> class creates Tree
* object that has a 9-ary tree and can add new nodes, get a reference to a
* desired node, displays a preordered list of all of the nodes, and starts a
* dialogue.
**/

//For constructor
import java.io.File;  //Needed for reading .txt file
import java.io.FileNotFoundException;  //Needed when reading the file
import java.util.Scanner;  //Needed for beginSession() and beginLabelSession()



public class Tree{


  //Need a mainRoot for a fixed reference, temptRoot for recursion
  public TreeNode mainRoot, tempRoot;

  //For getTreeNodeReference
  public boolean done = false;  //Used to end recursion early

  //NOTE:  For extra Credit
  public TreeNode priorRoot;  //Used for backtracking

  /**
  * Constructs the Tree. O(nlogn) since it's adding elements to a tree.
  *
  * @param fileName
  *    File name of the file to be read
  *
  * @throws FileNotFoundException
  *    Thrown if file not found in the domain folder
  **/

  public Tree(String fileName) throws FileNotFoundException{

    //for keeping the code runnable
    boolean runnable = true;

    //Creating File instance to reference text file in Java
    File text = new File(fileName);

    //Creating Scanner instnace to read File in Java
    Scanner sc = new Scanner(text);

    //Reading each line of file using Scanner class
    int lineNumber = 1;

    //Parameters for TreeNodes to be added
    //Weird that the code needs these to be initialized first
    String label = "";
    String prompt = "";
    String message = "";

    String location = "";
    int treeIndex = 0;
    int locationTextIndex = 1;

    while(sc.hasNextLine() && runnable){

        String line = sc.nextLine();
        //Stops constructor from running if the text file does not start with
        //root
        if (lineNumber == 1 && !line.trim().equals("root")){
          runnable = false;
          System.out.println(fileName + " must start with \"root\".");
        }

        //For location
        //If the line has the space and ends with one of the 9 lengths
        //Ignores that line and sets it as location for next lines
        else if (line.trim().contains(" 1") ||
        line.trim().contains(" 2") ||
        line.trim().contains(" 3") || line.trim().contains(" 4") ||
        line.trim().contains(" 5") || line.trim().contains(" 6") ||
        line.trim().contains(" 7") || line.trim().contains(" 8") ||
        line.trim().contains(" 9")){
          //Checks for the position of the new location
          //System.out.println("Location added: " + line.trim() + " from Line " + lineNumber);

          location = line.trim();
          locationTextIndex = lineNumber;
          lineNumber++;
        }

        //Ignores blank lines
        else if (line.trim().length() == 0){}

        //Ends constructor if any line is longer than 60 characters
        else if (line.trim().length() > 60){
          System.out.println("Line " + lineNumber + " is more than 60 characters long.");
          runnable = false;
        }

        else{
          //Self-check to look at the line and its position in the text
          //System.out.println("line " + lineNumber + " :" + line.trim() + " " + ((lineNumber - locationTextIndex) % 3));

          //For labels
          if (lineNumber == 1){
            //If there is any space in the label cancel it
            if (line.trim().contains(" ")){
              System.out.println("Label in line " + lineNumber + " has spaces.");
              runnable = false;
            }
            else label = line.trim();
            //Check
            //System.out.println("Label: " + label);
          }

          else if (lineNumber == 2){
            prompt = line.trim();
            //Check
            //System.out.println("Prompt: " + prompt);
          }

          else if (lineNumber == 3){
            message = line.trim();
            //Check
            //System.out.println("Message: " + message);

            //Since it's the last parameter for TreeNode, must add to tree
            //If there is no mainRoot tree yet
            mainRoot = new TreeNode(label, message, prompt);
            //Checks to see if main root made
            //System.out.println("Main root made");

            //Checks to see the mainRoot has the right parameters in the right
            //places
            //System.out.println("Label: " + mainRoot.getLabel());
            //System.out.println("Prompt: " + mainRoot.getPrompt());
            //System.out.println("Message: " + mainRoot.getMessage());

          }

          else if ((lineNumber - locationTextIndex) % 3 == 1){
            //If there is any space in the label cancel it
            if (line.trim().contains(" ")){
              System.out.println("Label in line " + lineNumber + " has spaces.");
              runnable = false;
            }
            else label = line.trim();
            //Check
            //System.out.println("Label: " + label);
          }

          //For prompt
          else if ((lineNumber - locationTextIndex) % 3 == 2){
            prompt = line.trim();
            //Check
            //System.out.println("Prompt: " + prompt);
          }

          //For message
          else if ((lineNumber - locationTextIndex) % 3 == 0){
            message = line.trim();
            //Check
            //System.out.println("Message: " + message);

            //Since it's the last parameter for TreeNode, must add to tree
            //If mainRoot already initialized
            //Resets the tempRoot to mainRoot for every thing that gets added
            //to the tree
            tempRoot = mainRoot;

            addNode(label, prompt, message, location.substring(0, location.length() - 2));

            //Check for how each completed node is added
            //System.out.println("Branch Node: " + label + " added to " + location.substring(0, location.length() - 2));

          }
          else{}

          //Iterates the lineNumber
          lineNumber++;
        }
    }

  }

  /**
  * Adds node to the Tree based off of a parentLabel.  Returns true if
  * parentLabel exists and element added to the node that has it.
  * O(nlogn) since it's adding elements to a tree.
  *
  * @param label
  *    label of the new TreeNode
  * @param prompt
  *    prompt of the new TreeNode
  * @param message
  *    message of the new TreeNode
  * @param parentLabel
  *    label of the targeted node
  *
  * @return
  *   Returns true if node added, false if not
  *
  **/

  public boolean addNode(String label, String prompt, String message,
  String parentLabel){
    //NOTE:  Must declare tempRoot = mainRoot before calling this

    //Creates new node with parameters
    TreeNode newNode = new TreeNode(label, message, prompt);

    //If the tempRoot is not outside of the tree
    if (tempRoot != null){
      //If the tempNode has the same label as the parentLabel
      if (tempRoot.getLabel().equals(parentLabel)){
        tempRoot.addNode(newNode);
        return true;
      }
      //Recurses through the branch if tempNode does not have parentLabel
      else for (TreeNode node : tempRoot.allNodes){
        tempRoot = node;
        addNode(label, prompt, message, parentLabel);
      }
    }
    //If none of the nodes have the parentLabel
    return false;
  }


  /**
  * Returns the TreeNode with the targeted label.
  * O(nlogn) since it's parsing through nodes of a tree.
  *
  * @param label
  *    label of the targeted TreeNode
  *
  * @return
  *   Returns the TreeNode with the label, null if no TreeNode exists.
  *
  **/

  public TreeNode getTreeNodeReference(String label){
    //NOTE:  Must declare tempRoot = mainRoot before calling this
    //If the tempRoot is not outside of the tree

    if (tempRoot != null && !done){
      //If the tempNode has the same label as the label

      //Check
      //System.out.println(tempRoot.getLabel());

      if (tempRoot.getLabel().equals(label)){
        done = true;
        return tempRoot;
      }
      //Recurses through the branch if tempNode does not have label
      else for (TreeNode node : tempRoot.allNodes){
        //If global variable is still false, continue iterating
        if (!done){
          tempRoot = node;
          getTreeNodeReference(label);
        }
      }
    }
    //If none of the nodes have the label after all iterations
    if (!done){
      return null;
    }
    //Need to rereturn this to end recursion
    else{
      return tempRoot;
    }

  }

  /**
  * Prints out the label, prompt, and message formatted for all of the
  * nodes in preorder.  O(nlogn) since it's parsing through a tree.
  *
  **/

  public void preOrder(){
    mainRoot.preOrder();
  }

  /**
  * This option will begin asking questions starting from the root of the tree;
  * if there is no tree set up, display an error message. When displaying a
  * question with answers, display each answer on a separate line with a number
  * associated with it, similar to a menu. Include another option, 0 (zero) to
  * exit the help session and return to the main menu.   O(nlogn) since it's
  * parsing through a tree.
  *
  * NOTE:  Extra Credit added- B allows for going backwards in questions.
  **/

  public void beginSession(){
    //New scanner
    Scanner sc = new Scanner(System.in);

    System.out.println(tempRoot.getMessage());
    //If the recursion has not rached the end of the tree
    if (!tempRoot.isLeaf()){
      int counter = 1;
      //Prints out all prompts
      for (TreeNode node : tempRoot.allNodes){
        if (node != null){
          System.out.println(counter + ") " + node.getPrompt());
          counter++;
        }
      }
      //For exit option
      System.out.println("0) Exit Session.");
      //NOTE: For extra Credit
      System.out.println("B) Go Back.");

      System.out.print("\nChoice> ");

      String operation = sc.nextLine();
      operation = operation.substring(0, operation.length());

      if (operation.equals("B") || operation.equals("b")){
        if (tempRoot.equals(mainRoot)){
          System.out.println("Cannot go back on the first question.");
        }else{
          tempRoot = priorRoot;
          beginSession();
        }
      }
      //Should end if '0' selected
      else if (operation.equals("0")) {
        System.out.println("The inquiry has been closed.");

      }
      else{
        //Continues to recurse with the selection
        priorRoot = tempRoot;
        tempRoot = tempRoot.allNodes[Integer.parseInt(operation) - 1];
        beginSession();
      }

    }

    //Returns when reach a leaf
    else{
      System.out.println("\nThanks for using this automated service!");
    }
  }

  //NOTE: For Extra credit
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
  * @param userLabel
  *    label of the targeted TreeNode
  *
  **/
  
  public void beginLabelSession(String userLabel){
    try{
      //Need to reset the tempRoot
      tempRoot = mainRoot;
      tempRoot = getTreeNodeReference(userLabel.trim());  //Needs trim apparently
      //In case user wants to go back- intentionally set to the targeted node
      priorRoot = tempRoot;
      beginSession();

    }catch (NullPointerException e){
      System.out.println("There are no questions with the label.");
    }
  }
}
