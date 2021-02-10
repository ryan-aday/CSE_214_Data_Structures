/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>Mailbox</code> class creates a Mailbox
* object that has creates an ArrayList of Folders.
**/

//For serializable and for future possible functions
import java.util.ArrayList;  //For the ArrayList
import java.util.Scanner;  //Needed for beginSession() and beginLabelSession()

//For serializable and for future possible functions
import java.io.Serializable;
import java.text.StringCharacterIterator;
import java.util.*;
import java.io.*;

public class Mailbox implements Serializable{
  //These folders should never be renamed or deleted
  private Folder inbox, trash;

  private ArrayList<Folder> folders;

  public static Mailbox mailbox;

  //NOTE:  currentSortingMethod changes according to the current sorting method,
  //descending date is the default.

  /**
  * A constructor that creates new folders for inbox and trash and adds them to
  * folders using ArrayList built-in methods
  * O(1).
  *
  **/

  public Mailbox(){
    folders = new ArrayList<Folder>();
    inbox = new Folder("Inbox");
    trash = new Folder("Trash");
    folders.add(inbox);
    folders.add(trash);
  }

  /**
  * Adds the given folder to the list of custom folders. Note: check to make
  * sure a folder with that given name does not already exist. If it does,
  * return an error in some manner.
  * O(n).
  *
  * @param folder
  *    The Folder to be added to folders.
  *
  * @throws AddingFolderWithSharedNameException
  *    Thrown when there is a folder with the same name already in folders.
  *
  **/

  public void addFolder(Folder folder) throws AddingFolderWithSharedNameException{
    boolean alreadyExists = false;

    for (Folder presentFolder : folders){
      //If a folder with the same name already exists
        if (folder.getName().equals(presentFolder.getName())){
          throw new AddingFolderWithSharedNameException("There is already a folder with the same name.");
        }
    }
    folders.add(folder);
  }

  /**
  * Removes the given folder from the list of custom folders.
  * O(n).
  *
  * @param name
  *    The name of the Folder to be removed from folders.
  *
  **/

  public void deleteFolder(String name){
    for (Folder presentFolder : folders){
      //NOTE:  Must exclude inbox and trash
        if (name.equals(presentFolder.getName()) && !name.equals("Inbox") && !name.equals("Trash")){
          folders.remove(presentFolder);
          return;
        }

    }
  }

  /**
  * Gets user input on the contents of the email and adds it to the inbox.
  * O(1).
  *
  * @param name
  *    The name of the Folder to be removed from folders.
  *
  **/

  public void composeEmail(){
    //New scanner
    Scanner sc = new Scanner(System.in);

    //Email Prompts
    System.out.print("Enter recipient (To): ");
    String to = sc.nextLine();

    System.out.print("Enter carbon copy recipients (CC): ");
    String cc = sc.nextLine();

    System.out.print("Enter blind carbon copy recipients (BCC): ");
    String bcc = sc.nextLine();

    System.out.print("Enter subject line: ");
    String subject = sc.nextLine();

    System.out.print("Enter body: ");
    String body = sc.nextLine();

    Email newEmail = new Email(to, cc, bcc, subject, body);
    inbox.addEmail(newEmail);

    //System.out.println("Email successfully added to Inbox.");

  }

  /**
  * Moves the email to the trash. (This method shouldn’t remove any emails from
  * folders, the method removeEmail should be called and then deleteEmail is
  * called on the result).
  * O(n).
  *
  * @param email
  *    The Email to be deleted.
  *
  **/

  public void deleteEmail(Email email){
    moveEmail(email, trash);
  }

  /**
  * Takes the given email and puts in in the given folder. If the folder cannot
  * be found, instead move it to the Inbox. (Again, this method shouldn’t remove
  * any emails from folders, the method removeEmail should be called and then
  * moveEmail is called on the result).  O(n).
  *
  * @param email
  *    The Email to be moved.
  * @param target
  *    The Folder email is moved to.
  *
  **/

  public void moveEmail(Email email, Folder target){
    Email movingEmail = null;
    boolean stop = false;  //Needed for stopping first search

    for (Folder presentFolder : folders){
      if (presentFolder.contains(email) && stop == false){
        movingEmail = presentFolder.removeEmail(presentFolder.indexOf(email));
        stop = true;
      }
    }
    //For searching for targetFolder
    for (Folder presentFolder : folders){
      //If a folder with the same name already exists
        if (presentFolder.equals(target)){
          presentFolder.addEmail(movingEmail);
          return;
        }

    }
    //For if there is no folder which has the email, including inbox
    //but only adds in case somehow there is an email in inbox that is identical
    if (!inbox.contains(email)){
      inbox.addEmail(email);
    }

  }

  /**
  * Removes all emails from the trash folder by deleting old folder and adding
  * a new one.  O(1).
  *
  **/

  public void clearTrash(){
    folders.remove(trash);
    folders.add(new Folder("Trash"));
  }

  /**
  * Returns a folder by folder name.  O(n).
  *
  * @param name
  *     The name of the Folder.
  *
  * @return
  *      The Folder that shares the same name, or null if it doesn't exist.
  *
  **/

  public Folder getFolder(String name){
    for (Folder presentFolder : folders){
      //If a folder with the same name already exists
        if (name.equals(presentFolder.getName())){
          return presentFolder;
        }

    }
    //If no folders exist
    //NOTE:  Throw an axception when dealing with null
    return null;
  }

  /**
  * Prints a list of all Folders within the mailbox.  O(n).
  *
  *
  **/

  public void printAllFolders(){
    System.out.println("\nMailbox:");
    System.out.println("----------");
    for (Folder folder : folders){
      System.out.println(folder.getName());
    }
  }

}
