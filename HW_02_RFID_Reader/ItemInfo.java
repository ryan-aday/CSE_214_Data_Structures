/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>ItemInfo</code> class creates an ItemInfo object that contains
* an RFID, an original location, and a current location with accessor and
* mutator methods.
*
**/

public class ItemInfo{

  //For the constructor
  static String name, rfidTagNumber, originalLocation;
  static double price;

  String currentLocation;

  // Invariants:
  // rfidTagNumber, originalLocation, and currentLocation always represents
  // the RFID, original location, and current location of an item.
  //The name, rfidTagNumber, price, and originalLocation should never change.

  /**
  * Constructs the ItemInfo. For use in ItemInfoNode
  * where the ItemInfo constructor has no fields.
  * O(1) since all methods are O(1).
  *
  **/

  public ItemInfo(){}

  /**
  * Constructs the ItemInfo.
  *
  * @param newName
  *   The new name of the ItemInfo.
  * @param newPrice
  *   The new price of the ItemInfo.
  *
  * O(1) since all methods are O(1).
  *
  **/

  public ItemInfo(String newName, double newPrice){
    name = newName;
    price = newPrice;
  }

  /**
  * Returns the name of the ItemInfo as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The name is defined.
  *
  * @return
  *   Returns the String format of name.
  *
  **/

  public String getName(){
    return name;
  }

  /**
  * Returns the price of the ItemInfo as a double.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The name is defined.
  *
  * @return
  *   Returns the double format of price.
  *
  **/

  public double getPrice(){
    return price;
  }

  /**
  * Returns the name of the rfidTagNumber as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The rfidTagNumber is defined.
  *
  * @return
  *   Returns the String format of rfidTagNumber.
  *
  **/

  public String getRFID(){
    return rfidTagNumber;
  }

  /**
  * Returns the originalLocation of the originalLocation as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The originalLocation is defined.
  *
  * @return
  *   Returns the String format of originalLocation.
  *
  **/

  public String getOriginalLocation(){
    return originalLocation;
  }

  /**
  * Returns the currentLocation of the ItemInfo as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The currentLocation is defined.
  *
  * @return
  *   Returns the String format of currentLocation.
  *
  **/

  public String getCurrentLocation(){
    return currentLocation;
  }

  /**
  * Sets a value for rfidTagNumber.  O(1).
  *
  * @param newRFID
  *    The new rfidTagNumber of the ItemInfo.
  *
  * @throws RFIDCharsException
  *     Indicates the RFID has characters that aren't 0-9.
  * @throws RFIDLengthException
  *     Indicates the RFID is not made of 9 characters.
  *
  **/

  public void setRFID(String newRFID) throws RFIDCharsException, RFIDLengthException{
    //Throws if RFID does not have characters that aren't A-F or 0-9
    if (!newRFID.matches("[A-F0-9]+")){
      throw new RFIDCharsException("The RFID must contain A-F or 0-9 only.");
    //Throws if the RFID is not 9 characters long
    }else if (newRFID.length() != 9){
      throw new RFIDLengthException("The RFID must be 9 characters long.");
    }else{
      rfidTagNumber = newRFID;
    }
  }

  /**
  * Sets a value for originalLocation.  O(1).
  *
  * @param newLocation
  *    The new originalLocation of the ItemInfo.
  *
  * @throws LocationStartException
  *     Indicates the location doesn't begin with 's'.
  * @throws LocationLengthException
  *     Indicates the location isn't 6 characters long.
  * @throws LocationCharsException
  *     Indicates the location doesn't have characters 0-9 after the 's'.
  *
  **/

  public void setOriginalLocation(String newLocation) throws LocationCharsException, LocationStartException, LocationLengthException{

    //Throws if the new location does not start with 's'
    if (newLocation.charAt(0) != 's' && newLocation.charAt(0) != 's'){
      throw new LocationStartException("The original location must start with 's'.");
    //Throws if the RFID is not 9 characters long
    }else if (newLocation.length() != 6){
      throw new LocationLengthException("The original location must be 6 characters long.");
    //Throws if the digits following the 's' are not numbers.
    }else if (!newLocation.substring(1, 6).matches("[0-9]+")){
      System.out.println(newLocation.substring(1, 6));
      throw new LocationCharsException("The characters after the 's' must be numerical digits.");
    }else{
      originalLocation = newLocation;
    }
  }


  /**
  * Sets a value for currentLocation.  O(1).
  *
  * @param newLocation
  *    The new originalLocation of the ItemInfo.
  *
  * @throws LocationStartException
  *     Indicates the location doesn't begin with 's' or 'c'.
  * @throws LocationLengthException
  *     Indicates the location isn't 6 characters long for 's' or 4 for 'c'.
  * @throws LocationCharsException
  *     Indicates the location doesn't have characters 0-9 after the 's' or 'c'.
  * @throws IllegalArgumentException
  *     Indicates the location isn't a proper cart or shelf location.
  *
  **/

  public void setCurrentLocation(String newLocation) throws LocationCharsException, LocationLengthException{

    //If the item is not check out already
    if (newLocation != "out"){

      if (newLocation.charAt(0) == 's'){
        //Throws if the length is not 6.
        if (newLocation.length() != 6){
          throw new LocationLengthException("The original location must be 6 characters long.");
        //Throws if the digits following the 's' are not numbers.
        }else if (!newLocation.substring(1, 6).matches("[0-9]+")){
          System.out.println(newLocation.substring(1, 6));
          throw new LocationCharsException("The characters after the 's' must be numerical digits.");
        }else{
          currentLocation = newLocation;
        }
      }
      //If the new location is in a cart
      else if (newLocation.charAt(0) == 'c'){
        //Throws if length is not 4.
        if (newLocation.length() != 4){
          throw new LocationLengthException("The current location must be 4 characters long.");
        //Throws if the digits following the 's' are not numbers.
        }else if (!newLocation.substring(1, 4).matches("[0-9]+")){
          System.out.println(newLocation.substring(1, 4));
          throw new LocationCharsException("The characters after the 's' must be numerical digits.");
        }else{
        currentLocation = newLocation;
        }
      }
      //If the location doesn't start with either 's' or 'c'
      else throw new IllegalArgumentException("The location must begin with a 's' or 'c'.");

    //If the item is checked out
    }else{
      currentLocation = newLocation;
    }

  }

}
