/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>Folder</code> class creates a Folder
* object that has creates an ArrayList of Emails and manifulates/sorts them.
**/

//For serializable and for future possible functions
import java.util.ArrayList;

//For serializable and for future possible functions
import java.io.Serializable;
import java.text.StringCharacterIterator;
import java.util.*;
import java.io.*;

//For GregorianCalendar
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class Folder implements Serializable{

  private ArrayList<Email> emails;
  private String name;

  //NOTE:  currentSortingMethod changes according to the current sorting method,
  //descending date is the default.
  private String currentSortingMethod = "dateDescending";

  /**
  * A constructor that creates a new ArrayList<Email> for emails and names
  * the folder based off of the parameter.
  * O(1).
  *
  * @param newName
  *    The name of the Folder.
  *
  **/

  public Folder(String newName){
    emails = new ArrayList<Email>();
    name = newName;
  }

  /**
  * Adds an email to the folder according to the current sorting method.
  * Also uses built-in ArrayList methods.
  * O(logN).
  *
  * @param email
  *    The Email to be added.
  *
  **/

  public void addEmail(Email email){
    //Must go through all 4 options before adding emails

    //NOTE: Due to consideration with the fact that it's the fastest- O(logN) on
    //avg with the worst case being if the emails are already sorted,
    //Quick Sort is used for all four sorting methods


    //Default
    if (currentSortingMethod == "dateDescending"){
      emails.add(email);
      sortByDateDescending();
    }
    else if (currentSortingMethod == "dateAscending"){
      emails.add(email);
      sortByDateAscending();
    }
    else if (currentSortingMethod == "subjectDescending"){
      emails.add(email);
      sortByDateDescending();
    }
    else if (currentSortingMethod == "subjectAscending"){
      emails.add(email);
      sortByDateAscending();
    }//Should never reach here
    else{}

  }

  /**
  * Removes an email from the folder.  Uses built-in ArrayList methods.
  * O(1).
  *
  * @param index
  *    The index of the Email to be removed.
  8 @throws IndexOutOfBoundsException
  *     Thrown when the parameter is not within range of 0 to the last element
  *      of the ArrayList.
  *
  **/

  public Email removeEmail(int index){
    //Uses built-in ArryList .remove() function
    if (index < 0 || index > emails.size() -1){
      System.out.println(index);
      throw new IndexOutOfBoundsException();
    }
    Email tempEmail = emails.get(index);
    emails.remove(index);
    return tempEmail;
  }

  /**
  * Sorts the emails alphabetically by subject in ascending order.
  * O(n^2).
  *
  **/

  public void sortBySubjectAscending(){
    currentSortingMethod = "subjectAscending";
    subjectInsertionSort(emails, emails.size());
  }

  /**
  * A helper method that helps sort the emails by subject in ascending order.
  * O(n^2).
  *
  * @param emails
  *    The Arraylist<Emails> of the Folder.
  * @param n
  *    The number of emails in the Arraylist.
  *
  **/

  public void subjectInsertionSort(ArrayList<Email> emails, int n){
    /*
    int left, right;
    Email pivot;
    if (first >= last) return;
    left = first;
    right = last;
    pivot = emails.get( (first + last) / 2 );

    //NOTE;  do-while loops check for the condition at the end of the loop- a
    //regular while-loop checks constantly
    do{
      while (emails.get(left).getSubject().compareTo(pivot.getSubject()) < 0) left++;
      while (emails.get(right).getSubject().compareTo(pivot.getSubject()) > 0) right--;
      if (left <= right) swap(emails, left, right);
    }while (left <= right);   //Needed to end do-while loop

    subjectInsertionSort(emails, first, right);
    subjectInsertionSort(emails, left, last);
    */
    int i, j;
    Email item;
    for (i = 1; i <= n - 1; i++){
      item = emails.get(i);
      j = i;
      while (j > 0 && emails.get(j - 1).getSubject().compareTo(item.getSubject()) > 0){
        emails.set(j, emails.get(j - 1));
        j--;
      }
      emails.set(j, item);
    }
  }

  /**
  * Sorts the emails alphabetically by subject in descending order.
  * O(n^2).
  *
  **/

  public void sortBySubjectDescending(){
    currentSortingMethod = "subjectDescending";
    subjectReverseInsertionSort(emails, emails.size());
  }

  /**
  * A helper method that helps sort the emails by subject in ascending order.
  * O(n^2).
  *
  * @param emails
  *    The Arraylist<Emails> of the Folder.
  * @param n
  *    The number of emails in the Arraylist.
  *
  **/

  public void subjectReverseInsertionSort(ArrayList<Email> emails, int n){
    /*
    int left, right;
    Email pivot;
    if (first >= last) return;
    left = first;
    right = last;
    pivot = emails.get( (first + last) / 2 );

    //NOTE;  do-while loops check for the condition at the end of the loop- a
    //regular while-loop checks constantly
    do{
      while (emails.get(left).getSubject().compareTo(pivot.getSubject()) > 0) left++;
      System.out.println(emails.get(left).getSubject().compareTo(pivot.getSubject()));
      while (emails.get(right).getSubject().compareTo(pivot.getSubject()) < 0) right--;
      System.out.println(emails.get(right).getSubject().compareTo(pivot.getSubject()));
      if (left <= right) swap(emails, left, right);
    }while (left <= right);   //Needed to end do-while loop

    subjectReverseInsertionSort(emails, first, right);
    subjectReverseInsertionSort(emails, left, last);
    */
    int i, j;
    Email item;
    for (i = 1; i <= n - 1; i++){
      item = emails.get(i);
      j = i;
      while (j > 0 && emails.get(j - 1).getSubject().compareTo(item.getSubject()) < 0){
        emails.set(j, emails.get(j - 1));
        j--;
      }
      emails.set(j, item);
    }
  }

  /**
  * Sorts the emails by date in ascending order.
  * O(n^2).
  *
  **/

  public void sortByDateAscending(){
    currentSortingMethod = "dateAscending";
    dateInsertionSort(emails, emails.size());

    //Use "compareTo" to compare the dates
  }

  /**
  * A helper method that helps sort the emails by date in ascending order.
  * O(n^2).
  *
  * @param emails
  *    The Arraylist<Emails> of the Folder.
  * @param n
  *    The number of emails in the Arraylist.
  *
  **/

  public void dateInsertionSort(ArrayList<Email> emails, int n){
    /*
    int left, right;
    Email pivot;
    if (first >= last) return;
    left = first;
    right = last;
    pivot = emails.get( (first + last) / 2 );

    //NOTE;  do-while loops check for the condition at the end of the loop- a
    //regular while-loop checks constantly
    do{
      while (emails.get(left).getTimestamp().compareTo(pivot.getTimestamp()) < 0) left++;
      while (emails.get(right).getTimestamp().compareTo(pivot.getTimestamp()) > 0) right--;
      if (left <= right) swap(emails, left, right);
    }while (left <= right);   //Needed to end do-while loop

    dateInsertionSort(emails, first, right);
    dateInsertionSort(emails, left, last);
    */
    int i, j;
    Email item;
    for (i = 1; i <= n - 1; i++){
      item = emails.get(i);
      j = i;
      while (j > 0 && emails.get(j - 1).getTimestamp().compareTo(item.getTimestamp()) > 0){
        emails.set(j, emails.get(j - 1));
        j--;
      }
      emails.set(j, item);
    }
  }

  /**
  * Sorts the emails by date in descending order.
  * O(n^2).
  *
  **/

  public void sortByDateDescending(){
    currentSortingMethod = "dateDescending";
    dateReverseInsertionSort(emails, emails.size());

  }

  /**
  * A helper method that helps sort the emails by date in descending order.
  * O(n^2).
  *
  * @param emails
  *    The Arraylist<Emails> of the Folder.
  * @param n
  *    The number of emails in the Arraylist.
  *
  **/

  public void dateReverseInsertionSort(ArrayList<Email> emails, int n){
    /*
    int left, right;
    Email pivot;
    // || first < 0 || first > emails.size() - 1 || last < 0 ||last > emails.size() - 1

    if (first >= last) return;

    left = first;
    right = last;
    pivot = emails.get( (first + last) / 2 );

    //NOTE;  do-while loops check for the condition at the end of the loop- a
    //regular while-loop checks constantly
    do{
      while (emails.get(left).getTimestamp().compareTo(pivot.getTimestamp()) > 0) left++;
      while (emails.get(right).getTimestamp().compareTo(pivot.getTimestamp()) < 0) right--;
      if (left <= right) swap(emails, left, right);
    }while (left <= right);   //Needed to end do-while loop

    dateReverseInsertionSort(emails, first, right);
    dateReverseInsertionSort(emails, left, last);
    */

    int i, j;
    Email item;
    for (i = 1; i <= n - 1; i++){
      item = emails.get(i);
      j = i;
      while (j > 0 && emails.get(j - 1).getTimestamp().compareTo(item.getTimestamp()) < 0){
        emails.set(j, emails.get(j - 1));
        j--;
      }
      emails.set(j, item);
    }

  }

  /**
  * A helper method that swaps the position of two elements in emails.
  * O(N).
  * NOTE: This function is no longer used after switching to insertionSort.
  *
  * @param emails
  *    The Arraylist<Emails> of the Folder.
  * @param indexA
  *    One of the indices to be swapped.
  * @param indexB
  *    One of the indices to be swapped.
  *
  **/

  public void swap(ArrayList<Email> emails, int indexA, int indexB){
    //creates an email object that references the email in the first index
    Email tempEmail = emails.get(indexA);
    //Moves reference from indexA to indexB for indexA
    emails.set(indexA, emails.get(indexB));
    emails.set(indexB, tempEmail);
  }

  /**
  * Returns the name of the Folder as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>name is defined.
  *
  * @return
  *   Returns the String format of name.
  *
  **/

  public String getName(){
    return name;
  }

  /**
  * Sets a value for name.  O(1).
  *
  * @param newName
  *    The new name of the Folder.
  *
  **/

  public void setName(String newName){
    name = newName;
  }

  /**
  * Uses built-in method for folders as an ArrayList.  O(1).
  *
  * @param email
  *    The email to be checked within emails to see whether it is contained.
  *
  * @return
  *    True if email is in emails, false if not
  *
  **/

  public boolean contains(Email email){
    return emails.contains(email);
  }

  /**
  * Uses built-in method for folders as an ArrayList.  O(1).
  *
  * @param email
  *    The email in emails for the index to be returned.
  *
  * @return
  *    The index of the email, -1 if not present
  *
  **/

  public int indexOf(Email email){
    return emails.indexOf(email);
  }

  /**
  * Prints a list of all emails within the folder.  O(n).
  *
  *
  **/

  public void printAllEmails(){
    //Header
    System.out.println(String.format("%10s%37s%12s", "Index  |", "Time                ", "| Subject"));
    System.out.println("-------------------------------------------------------------");
    int counter = 1;
    for (Email email : emails){
      int index = counter;
      GregorianCalendar timestamp = email.getTimestamp();
      String subject = email.getSubject();

      GregorianCalendar calendar = email.getTimestamp();
      SimpleDateFormat fmt = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
      fmt.setCalendar(calendar);
      String dateFormatted = fmt.format(calendar.getTime());

      System.out.println(String.format("%-10s%-37s%-12s", index, dateFormatted, "     " + subject));
      counter++;

    }

  }

  /**
  * Uses built-in method for folders as an ArrayList to find Email.  O(1).
  *
  * @param index
  *    The index of the Email in emails to be returned.
  *
  * @return
  *    The Email at the index.
  *
  **/

  public Email getEmail(int index){
    return emails.get(index);
  }

}
