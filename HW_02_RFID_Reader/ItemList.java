/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>SongRecord</code> class creates an ItemList
* object that acts as a linked list between ItemInfoNodes.
*
**/

public class ItemList{

  private ItemInfoNode head, tail, cursor;
  //NOTE:  The list progresses from head to tail.

  // Invariants:
  // head, tail, and cursor always represents
  // the head, tail, and cursor of the linked list.

  /**
  * Constructs the ItemInfo and sets the head, tail, and cursor to null.
  *
  * O(1) since all methods are O(1).
  *
  **/

  public ItemList(){
    head = null;
    tail = null;
    cursor = null;
  }

  /**
  * Returns the head of the ItemList as an ItemInfoNode.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The head is defined.
  *
  * @return
  *   Returns the ItemInfoNode format of head.
  *
  **/

  public ItemInfoNode getHead(){
    return head;
  }

  /**
  * Returns the cursor of the ItemList as an ItemInfoNode.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The cursor is defined.
  *
  * @return
  *   Returns the ItemInfoNode format of cursor.
  *
  **/

  public ItemInfoNode getCursor(){
    return cursor;
  }

  /**
  * Returns the tail of the ItemList as an ItemInfoNode.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The tail is defined.
  *
  * @return
  *   Returns the ItemInfoNode format of tail.
  *
  **/

  public ItemInfoNode getTail(){
    return tail;
  }

  /**
  * Inserts the info into the list in its correct position based on its rfidTagNumber.
  * O(n).
  *
  * @param name
  *   The name of the item.
  * @param rfidTag
  *   The RFID of the item.
  * @param price
  *   The price of the item.
  * @param initPosition
  *   The initial position of the item.
  *
  * <dt>Postcondition:
  *   <dd>The item is added to the ItemList.
  *
  * @throws RFIDCharsException
  *   The RFID doesn't contain A-F or 0-9 only.
  * @throws RFIDLengthException
  *   The RFID dlength isn't 9 characters long.
  * @throws LocationCharsException
  *   The location doesn't have 0-9 characters after the 's'.
  * @throws LocationStartException
  *   The location doesn't start with 's'.
  * @throws LocationLengthException
  *   The location isn't 6 characters long.
  * @throws IllegalArgumentException
  *   The location is "out".
  *
  *NOTE:
  *   Multiple instances of a given item ARE allowed in the list. When you are
  *   inserting a duplicate item into the list, the order of items of the same
  *   type doesn't matter as long as they are together and, as a group, they
  *   appear in the correct spot in the list.
  **/

  public void insertInfo(String name, String rfidTag, double price, String initPosition) throws RFIDCharsException,
  RFIDLengthException, LocationCharsException, LocationStartException, LocationLengthException{
    //Refreshes cursor to start at head
    cursor = head;

    if (initPosition == "out"){
      throw new IllegalArgumentException("Initial position can't be \"out\"");
    }

    //Sets up InfoNode for its data
    ItemInfoNode newInfoNode = new ItemInfoNode();

    ItemInfo newInfo = new ItemInfo(name, price);
    newInfo.setRFID(rfidTag);
    newInfo.setOriginalLocation(initPosition);

    //Need to add this or else you can't use a move function
    newInfo.setCurrentLocation(initPosition);

    newInfoNode.setInfo(newInfo);

    //If No Node in list
    if (head == null){
      head = newInfoNode;
      cursor = newInfoNode;
      tail = newInfoNode;

    //If only one node in list
    }else if (cursor == head && cursor == tail){

      //IF RFID is smaller than the head
      //NOTE: Long is used instead of Integer since the max value of an Integer
      //is smaller than the maximum value possible with the 9 digits.

      if (Long.parseLong(head.getInfo().getRFID(), 16) < Long.parseLong(rfidTag, 16)){

        //Must set heads and tails to properly sort through entire list
        tail = newInfoNode;
        tail.setPrev(head);
        head.setNext(tail);

      }else{

        newInfoNode.setNext(head);
        head = newInfoNode;
        tail = head.getNext();
        tail.setPrev(head);
      }
    }

    //If there are at least two nodes in the list
    else{
      //Remember that the cursor starts at the head, can't forget;
      if (Long.parseLong(cursor.getInfo().getRFID(), 16) > Long.parseLong(rfidTag, 16)){
        newInfoNode.setNext(cursor);
        head = newInfoNode;

      //If the head is not smaller than the new RFID
      }else{

        while(cursor.getNext() != null && Long.parseLong(cursor.getNext().getInfo().getRFID(), 16) < Long.parseLong(rfidTag, 16)){
          cursor = cursor.getNext();
        }
        //If the new RFID is the biggest in the list
        //cursor is smaller than the rfid
        if (cursor == tail){
          //NOTE:  Must involve the cursor here

          tail.setNext(newInfoNode);
          newInfoNode.setPrev(tail);
          newInfoNode.setNext(null);
          tail = newInfoNode;

        }
        //IF the RFID is in the middle of values in the list
        //Cursor is still smaller than given RFID
        else{

          //Want to set the new node after the cursor
          cursor.getNext().setPrev(newInfoNode);
          newInfoNode.setPrev(cursor);
          newInfoNode.setNext(cursor.getNext());
          cursor.setNext(newInfoNode);

        }

      }

    }
  }

  /**
  * Removes all nodes in the list that have current location listed as "out"
  * and displays a list of all items that have been removed in this fashion.
  * O(n).
  *
  * <dt>Postcondition:
  *   <dd>All items with their current location as "out" are removed; a list
  *       is also printed.
  *
  * @throws RFIDCharsException
  *   The RFID doesn't contain A-F or 0-9 only.
  * @throws RFIDLengthException
  *   The RFID dlength isn't 9 characters long.
  * @throws LocationCharsException
  *   The location doesn't have 0-9 characters after the 's' or 'c'.
  * @throws LocationStartException
  *   The location doesn't start with 's' or 'c;.
  * @throws LocationLengthException
  *   The location isn't 6 characters long for 's' or 4 for 'c'.
  * @throws IllegalArgumentException
  *   The location is "out".
  *
  *NOTE:
      None of the exceptions should trigger if the functions all work properly.
  **/

  public void removeAllPurchased() throws RFIDCharsException,
  RFIDLengthException, LocationCharsException, LocationLengthException,
  LocationStartException{
    //Resets the cursor to the Head
    cursor = head;

    //For printing out the table
    String result = "";
    String header = "";

    String printName, printRFID, printCurrent, printOriginal;
    double printPrice;

    //Header
    header = header + String.format("%-20s%-15s%-12s%-12s%-6s\n", " ", " ", "Original", "Current", " ");
    header = header + String.format("%-20s%-15s%-12s%-12s%-6s\n", "Item Name", "RFID", "Location", "Location", "Price");
    header = header + "---------           ----           --------    --------    -----\n";

    //If the list is empty
    if (head == null){
      System.out.println(header + result);
      return;
    }//If there is only one node in the list
    else if (cursor == head && cursor == tail){
      //Only removes the one node if current location is "out"
      if (cursor.getInfo().getCurrentLocation() == "out"){

        printName = cursor.getInfo().getName();
        printRFID = cursor.getInfo().getRFID();
        printOriginal = cursor.getInfo().getOriginalLocation();
        printCurrent = cursor.getInfo().getCurrentLocation();
        printPrice = cursor.getInfo().getPrice();

        result = result + String.format("%-20s%-15s%-12s%-12s%-6s\n", printName, printRFID, printOriginal, printCurrent, "" + printPrice);

        head = null;
        tail = null;
      }
    }//If there is more than one node in the list
    else{
      //Moves cursor while there are still nodes in front of it
      //This loop will reach the tail after it ends
      while(cursor.getNext() != null){
        System.out.println(cursor.getInfo().getName());

        //For if the cursor has a current location of "out"

        if (cursor.getInfo().getCurrentLocation() == "out"){
          printName = cursor.getInfo().getName();
          printRFID = cursor.getInfo().getRFID();
          printOriginal = cursor.getInfo().getOriginalLocation();
          printCurrent = cursor.getInfo().getCurrentLocation();
          printPrice = cursor.getInfo().getPrice();

          result = result + String.format("%-20s%-15s%-12s%-12s%-6s\n", printName, printRFID, printOriginal, printCurrent, "" + printPrice);

          //If the cursor is at head
          if (cursor == head){
            cursor.getNext().setPrev(null);
            head = cursor.getNext();
          }else{
            //If the cursor is in the middle / in between head and tail
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());

            //NOTE: Check to see whether list gets destroyed.

          }
        }
        //Progresses through list
        cursor = cursor.getNext();

      }//Remember to check the last node
      if (cursor.getInfo().getCurrentLocation() == "out"){
        printName = cursor.getInfo().getName();
        printRFID = cursor.getInfo().getRFID();
        printOriginal = cursor.getInfo().getOriginalLocation();
        printCurrent = cursor.getInfo().getCurrentLocation();
        printPrice = cursor.getInfo().getPrice();

        result = result + String.format("%-20s%-15s%-12s%-12s%-6s\n", printName, printRFID, printOriginal, printCurrent, "" + printPrice);

        //If there's still nodes behind tail
        if (cursor.getPrev() != null){
          cursor.getPrev().setNext(null);
          tail = cursor.getPrev();
        }//If tail is the only node left
        else{
          tail = null;
          cursor = null;
          head = null;
        }

      }

    }
    System.out.println(header + result);
  }

  /**
  * Inserts the info into the list in its correct position based on its rfidTagNumber.
  * O(n).
  *
  * @param rfidTag
  *   The name of the item.
  * @param source
  *   Where the item currently is.
  * @param dest
  *   Where the item is going.
  *
  * <dt>Postcondition:
  *   <dd>The item is moved to the dest.  If the source was the same place the
  *   item was originally found, returns true.  Otherwise false.
  *
  * @throws RFIDCharsException
  *   The RFID doesn't contain A-F or 0-9 only.
  * @throws RFIDLengthException
  *   The RFID dlength isn't 9 characters long.
  * @throws LocationCharsException
  *   The location doesn't have 0-9 characters after the 's' or 'c'.
  * @throws LocationStartException
  *   The location doesn't start with 's' or 'c;.
  * @throws LocationLengthException
  *   The location isn't 6 characters long for 's' or 4 for 'c'.
  * @throws IllegalArgumentException
  *   The location is "out".
  *
  **/

  public boolean moveItem(String rfidTag, String source, String dest) throws LocationCharsException,
  LocationLengthException, LocationStartException, RFIDCharsException, RFIDLengthException{
    //Resets cursor to head
    cursor = head;

    //Check to see if source has the right Format
    ItemInfo check = new ItemInfo();
    check.setCurrentLocation(source);
    check.setRFID(rfidTag);

    //Checks to see if the source or dest is equal to "out"
    if (source == "out" || dest == "out"){
      throw new IllegalArgumentException("The source cannot be \"out\"." );
    }

    //If the list is empty
    if (cursor == null){
      return false;
    }

    //Checks every node in the list
    while (cursor.getNext() != null){

      //Checks if the current cursor has the right RFID
      if (cursor.getInfo().getRFID().equals(rfidTag) ){
        //If the source is equal to the current location, the destination is set
        //and returns true.  If not equal, the desination is still given.

        if (source.equals(cursor.getInfo().getOriginalLocation() )){
          //Returning early in case there are two items with the same RFID
          cursor.getInfo().setCurrentLocation(dest);
          return true;
        }
        //Sets the destination after verifying whether the node was in the
        //specified source.
        cursor.getInfo().setCurrentLocation(dest);
      }
      cursor = cursor.getNext();
    }

    //Need to check the last node (where the cursor currently is)
    if (cursor.getInfo().getRFID().equals(rfidTag)){
      //If the source is equal to the current location, the destination is set
      //and returns true.  If not equal, the desination is still given.

      if (source.equals(cursor.getInfo().getOriginalLocation() )){
        //Returning early in case there are two items with the same RFID
        cursor.getInfo().setCurrentLocation(dest);
        return true;
      }

      //Triggered if the last item with the RFID tag doesn't match after all
      //the others.

      cursor.getInfo().setCurrentLocation(dest);
      return false;

    }else{
      //Sets the destination after verifying whether the node was in the
      //specified source.

      //No nodes in the list have the rfid; result is false as default.
      //throw new NoneInListException("There is no item in the list with the RFID.");
      return false;
    }
  }

  /**
  * Prints a neatly formatted list of all items currently in the list. The
  * table should include each item's name, rfidTagNumber, original location,
  * current location, and price. The list must be sorted in order of
  * rfidTagNumber, although duplicate rfidTagNumber entries may be printed in
  * any order.  O(n).
  *
  * <dt>Postcondition:
  *   <dd>A neat list prints with all the items.
  *
  * @throws LocationCharsException
  *   The location doesn't have 0-9 characters after the 's' or 'c'.
  * @throws LocationLengthException
  *   The location isn't 6 characters long for 's' or 4 for 'c'.
  *
  *NOTE:
  *   There should be no exceptions if the functions were built properly.
  **/

  public void printAll() throws LocationCharsException, LocationLengthException{
    cursor = head;

    String printName, printRFID, printCurrent, printOriginal;
    double printPrice;

    //Header
    System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", " ", " ", "Original", "Current", " "));
    System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", "Item Name", "RFID", "Location", "Location", "Price"));
    System.out.println("---------           ----           --------    --------    -----");

    if (head == null){
      return;
    }

    //For case where only one node
    if (cursor.getNext() == null){
      printName = cursor.getInfo().getName();
      printRFID = cursor.getInfo().getRFID();
      printOriginal = cursor.getInfo().getOriginalLocation();
      printCurrent = cursor.getInfo().getCurrentLocation();
      printPrice = cursor.getInfo().getPrice();

      System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", printName, printRFID, printOriginal, printCurrent, "" + printPrice));
      return;  //Prevents the function from going further
    }

    while (cursor.getNext() != null){
      printName = cursor.getInfo().getName();
      printRFID = cursor.getInfo().getRFID();
      printOriginal = cursor.getInfo().getOriginalLocation();
      printCurrent = cursor.getInfo().getCurrentLocation();
      printPrice = cursor.getInfo().getPrice();

      System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", printName, printRFID, printOriginal, printCurrent, "" + printPrice));

      //Moves up cursor
      cursor = cursor.getNext();

    }

    //For the last node
    printName = cursor.getInfo().getName();
    printRFID = cursor.getInfo().getRFID();
    printOriginal = cursor.getInfo().getOriginalLocation();
    printCurrent = cursor.getInfo().getCurrentLocation();
    printPrice = cursor.getInfo().getPrice();

    System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", printName, printRFID, printOriginal, printCurrent, "" + printPrice));

  }

  /**
  * Prints a neatly formatted list of all items in a specified current location.
  * The table should include each item's name, rfidTagNumber, original location,
  * current location, and price. The list must be sorted in order of
  * rfidTagNumber, although duplicate rfidTagNumber entries may be printed in
  * any order.  O(n).
  *
  * @param location
  *   The current location of items to be targeted.
  *
  * <dt>Postcondition:
  *   <dd>A neat list prints with all the items based on location.
  *
  * @throws LocationCharsException
  *   The location doesn't have 0-9 characters after the 's' or 'c'.
  * @throws LocationLengthException
  *   The location isn't 6 characters long for 's' or 4 for 'c'.
  *
  *NOTE:
  *   There should be no exceptions if the functions were built properly.
  **/

  public void printByLocation(String location) throws LocationCharsException, LocationLengthException{
    cursor = head;

    //Checks the location for errors
    ItemInfo check = new ItemInfo();
    check.setCurrentLocation(location);

    String printName, printRFID, printCurrent, printOriginal;
    double printPrice;

    //Header
    System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", " ", " ", "Original", "Current", " "));
    System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", "Item Name", "RFID", "Location", "Location", "Price"));
    System.out.println("---------           ----           --------    --------    -----");

    //For if there is an emply list
    if (head == null){
      return;
    }

    //For case where only one node
    if (cursor.getNext() == null ){
      if (cursor.getInfo().getCurrentLocation().equals(location)){
        printName = cursor.getInfo().getName();
        printRFID = cursor.getInfo().getRFID();
        printOriginal = cursor.getInfo().getOriginalLocation();
        printCurrent = cursor.getInfo().getCurrentLocation();
        printPrice = cursor.getInfo().getPrice();

        System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", printName, printRFID, printOriginal, printCurrent, "" + printPrice));
        return;  //Prevents the function from going further
      }

    }

    while (cursor.getNext() != null){
      if (cursor.getInfo().getCurrentLocation().equals(location)){
        printName = cursor.getInfo().getName();
        printRFID = cursor.getInfo().getRFID();
        printOriginal = cursor.getInfo().getOriginalLocation();
        printCurrent = cursor.getInfo().getCurrentLocation();
        printPrice = cursor.getInfo().getPrice();

        System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", printName, printRFID, printOriginal, printCurrent, "" + printPrice));
      }

      //Moves up cursor
      cursor = cursor.getNext();

    }

    //For the last node
    if (cursor.getInfo().getCurrentLocation().equals(location)){
      printName = cursor.getInfo().getName();
      printRFID = cursor.getInfo().getRFID();
      printOriginal = cursor.getInfo().getOriginalLocation();
      printCurrent = cursor.getInfo().getCurrentLocation();
      printPrice = cursor.getInfo().getPrice();

      System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", printName, printRFID, printOriginal, printCurrent, "" + printPrice));
    }

  }

  /**
  * Prints a neatly formatted list of all items in a specified current location.
  * The table should include each item's name, rfidTagNumber, original location,
  * current location, and price. The list must be sorted in order of
  * rfidTagNumber, although duplicate rfidTagNumber entries may be printed in
  * any order.  O(n).
  *
  * @param rfid
  *   The rfidTagNumber of items to be targeted.
  *
  * <dt>Postcondition:
  *   <dd>A neat list prints with all the items based on the rfidTagNumber.
  *
  * @throws LocationCharsException
  *   The location doesn't have 0-9 characters after the 's' or 'c'.
  * @throws LocationLengthException
  *   The location isn't 6 characters long for 's' or 4 for 'c'.
  *
  *NOTE:
  *   There should be no exceptions if the functions were built properly.
  **/

  public void printByRFID(String rfid) throws RFIDCharsException, RFIDLengthException{
    cursor = head;

    //Checks to see if there are any rfid formatting errors
    ItemInfo check = new ItemInfo();
    check.setRFID(rfid);

    String printName, printRFID, printCurrent, printOriginal;
    double printPrice;

    //Header
    System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", " ", " ", "Original", "Current", " "));
    System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", "Item Name", "RFID", "Location", "Location", "Price"));
    System.out.println("---------           ----           --------    --------    -----");

    if (head == null){
      return;
    }

    //For case where only one node
    if (cursor.getNext() == null ){
      if (cursor.getInfo().getRFID() == rfid){
        printName = cursor.getInfo().getName();
        printRFID = cursor.getInfo().getRFID();
        printOriginal = cursor.getInfo().getOriginalLocation();
        printCurrent = cursor.getInfo().getCurrentLocation();
        printPrice = cursor.getInfo().getPrice();

        System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", printName, printRFID, printOriginal, printCurrent, "" + printPrice));
        return;  //Prevents the function from going further
      }

    }

    while (cursor.getNext() != null){
      if (cursor.getInfo().getRFID().equals(rfid)){
        printName = cursor.getInfo().getName();
        printRFID = cursor.getInfo().getRFID();
        printOriginal = cursor.getInfo().getOriginalLocation();
        printCurrent = cursor.getInfo().getCurrentLocation();
        printPrice = cursor.getInfo().getPrice();

        System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", printName, printRFID, printOriginal, printCurrent, "" + printPrice));
      }

      //Moves up cursor
      cursor = cursor.getNext();

    }

    //For the last node
    if (cursor.getInfo().getRFID().equals(rfid)){
      printName = cursor.getInfo().getName();
      printRFID = cursor.getInfo().getRFID();
      printOriginal = cursor.getInfo().getOriginalLocation();
      printCurrent = cursor.getInfo().getCurrentLocation();
      printPrice = cursor.getInfo().getPrice();

      System.out.println(String.format("%-20s%-15s%-12s%-12s%-6s", printName, printRFID, printOriginal, printCurrent, "" + printPrice));
    }

  }

  /**
  * Take every item that is currently in the store and on the wrong shelf and
  * places it back where it belongs (its original location). Items that are
  * "out" or currently in a cart are not affected by this command. Display a
  * table for all out of place items moved in this fashion, including each
  * item's name, rfidTagNumber, current location(before the move), original
  * location and price.   O(n).
  *
  * <dt>Postcondition:
  *   <dd>A neat list prints with all the items put back into their original
  *   location.
  *
  * @throws LocationCharsException
  *   The location doesn't have 0-9 characters after the 's' or 'c'.
  * @throws LocationLengthException
  *   The location isn't 6 characters long for 's' or 4 for 'c'.
  *
  *NOTE:
  *   There should be no exceptions if the functions were built properly.
  **/

  public void cleanStore() throws LocationCharsException, LocationLengthException{
    //Reset cursor to heads
    cursor = head;

    //For printing out the table
    String result = "";
    String header = "";

    String printName, printRFID, printCurrent, printOriginal;
    double printPrice;

    //Header
    header = header + String.format("%-20s%-15s%-12s%-12s%-6s\n", " ", " ", "Original", "Current", " ");
    header = header + String.format("%-20s%-15s%-12s%-12s%-6s\n", "Item Name", "RFID", "Location", "Location", "Price");
    header = header + "---------           ----           --------    --------    -----\n";

    //Generates header if not empty list
    if (cursor != null){
      //Parse up until tail
      while (cursor.getNext() != null){
        if (cursor.getInfo().getCurrentLocation() != "out" && cursor.getInfo().getCurrentLocation() != cursor.getInfo().getOriginalLocation()){
          printName = cursor.getInfo().getName();
          printRFID = cursor.getInfo().getRFID();
          printOriginal = cursor.getInfo().getOriginalLocation();
          printCurrent = cursor.getInfo().getCurrentLocation();
          printPrice = cursor.getInfo().getPrice();

          result = result + String.format("%-20s%-15s%-12s%-12s%-6s\n", printName, printRFID, printOriginal, printCurrent, "" + printPrice);
          cursor.getInfo().setCurrentLocation(cursor.getInfo().getOriginalLocation());

        }
        //Moves up cursor
        cursor = cursor.getNext();

      }
      if (cursor.getInfo().getCurrentLocation() != "out" && cursor.getInfo().getCurrentLocation() != cursor.getInfo().getOriginalLocation()){
        printName = cursor.getInfo().getName();
        printRFID = cursor.getInfo().getRFID();
        printOriginal = cursor.getInfo().getOriginalLocation();
        printCurrent = cursor.getInfo().getCurrentLocation();
        printPrice = cursor.getInfo().getPrice();

        result = result + String.format("%-20s%-15s%-12s%-12s%-6s\n", printName, printRFID, printOriginal, printCurrent, "" + printPrice);
        cursor.getInfo().setCurrentLocation(cursor.getInfo().getOriginalLocation());

      }
    }

    //Prints out the completed table
    System.out.println(header + result);

  }

  /**
  * Goes through a given cart and checks out each item (changes its location
  * to "out"). A neatly formatted list of the items checked out is to be
  * printed and it must be sorted in order of rfidTagNumber, although duplicate
  * rfidTagNumber entries may be printed in any order. The return value is the
  * total cost for the items that were in the cart. Throw appropriate exceptions
  * for invalid cart numbers.     O(n).
  *
  * @param cartNumber
  *   The location of the targeted items to be removed from a cart.

  * <dt>Postcondition:
  *   <dd>A neat list prints with all the items that were checked out.  All
  *   items whose current location was in the cartNumber now have it as "out".
  *
  * @return
  *   A double of the sum of the prices.
  *
  * @throws LocationCharsException
  *   The cartNumber doesn't have 0-9 characters after the or 'c'.
  * @throws LocationLengthException
  *   The location isn't 4 characters long for 'c'.
  * @throws NotCartException
  *   The cartNumber is not a cart location.
  *
  **/

  public double checkOut(String cartNumber) throws LocationCharsException,
  LocationLengthException, NotCartException{
    //Function should use cart numbers
    if (cartNumber.charAt(0) != 'c'){
      throw new NotCartException("This is not a cart number- it must start with a 'c'.");
    }

    //Reset cursor to heads
    cursor = head;

    //For the result
    double result = 0.00;

    //For printing out the table
    String resultString = "";
    String header = "";

    String printName, printRFID, printCurrent, printOriginal;
    double printPrice;

    //Header
    header = header + String.format("%-20s%-15s%-12s%-12s%-6s\n", " ", " ", "Original", "Current", " ");
    header = header + String.format("%-20s%-15s%-12s%-12s%-6s\n", "Item Name", "RFID", "Location", "Location", "Price");
    header = header + "---------           ----           --------    --------    -----\n";

    //If the list is empty
    if (cursor != null){

      //Parse up until tail
      while (cursor.getNext() != null){
        if (cursor.getInfo().getCurrentLocation().equals(cartNumber)){
          if (cursor.getInfo().getPrice() < 0.00){
            throw new IllegalArgumentException("The price must be positive.");
          }

          printName = cursor.getInfo().getName();
          printRFID = cursor.getInfo().getRFID();
          printOriginal = cursor.getInfo().getOriginalLocation();
          printCurrent = cursor.getInfo().getCurrentLocation();
          printPrice = cursor.getInfo().getPrice();

          resultString = resultString + String.format("%-20s%-15s%-12s%-12s%-6s\n", printName, printRFID, printOriginal, printCurrent, "" + printPrice);

          cursor.getInfo().setCurrentLocation("out");
          result = result + cursor.getInfo().getPrice();
        }

        //Moves up cursor
        cursor = cursor.getNext();

      }
      if (cursor.getInfo().getCurrentLocation().equals(cartNumber)){
        if (cursor.getInfo().getPrice() < 0.00){
          throw new IllegalArgumentException("The price must be positive.");
        }
        printName = cursor.getInfo().getName();
        printRFID = cursor.getInfo().getRFID();
        printOriginal = cursor.getInfo().getOriginalLocation();
        printCurrent = cursor.getInfo().getCurrentLocation();
        printPrice = cursor.getInfo().getPrice();

        resultString = resultString + String.format("%-20s%-15s%-12s%-12s%-6s\n", printName, printRFID, printOriginal, printCurrent, "" + printPrice);

        cursor.getInfo().setCurrentLocation("out");
        result = result + cursor.getInfo().getPrice();

      }
    }

    //Should return 0.00 if list is empty
    System.out.println(header + resultString);
    return result;


  }


}
