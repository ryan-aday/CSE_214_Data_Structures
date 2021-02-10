/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>Request</code> class creates an ItemInfo object that contains
* an RFID, an original location, and a current location with accessor and
* mutator methods.
*
**/

public class Request{

  public static int sourceFloor, destinationFloor, timeEntered;

  // Invariants:
  // sourceFloor, destinationFloor, and timeEntered always represents
  // randomized start & end floors (they cannot be the same, otherwise what's
  // the point of taking the elevator) and the time unit at which the Request
  // was made.
  // None of these variables should change.

  /**
  * A constructor that takes as a parameter the number of floors in the
  * building.
  * The two integers represent the values for sourceFloor and destinationFloor,
  * which will be randomly generated within this constructor. The random values
  * must be between 1 and the number of floors in the building, inclusive.
  * The timeEntered will be undefined at first (the mutator will set this).
  * O(1) since all methods are O(1).
  *
  * @param numberOfFloors
  *    The numberOfFloors in the Request.
  *
  **/

  public Request (int numberOfFloors){
    sourceFloor = (int)(Math.random() * (double)numberOfFloors + 1.0);

    //To avoid having the destination floor be the source floor
    destinationFloor = sourceFloor;
    while (destinationFloor == sourceFloor){
      sourceFloor = (int)(Math.random() * (double)numberOfFloors + 1.0);
    }
  }

  /**
  * Returns the sourceFloor of the Request as an int.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The sourceFloor is defined.
  *
  * @return
  *   Returns the int format of sourceFloor.
  *
  **/

  public int getSource(){
    return sourceFloor;
  }

  /**
  * Returns the destinationFloor of the Request as an int.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The destinationFloor is defined.
  *
  * @return
  *   Returns the int format of destinationFloor.
  *
  **/

  public int getDest(){
    return destinationFloor;
  }

  /**
  * Returns the timeEntered of the Request as an int.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The timeEntered is defined.
  *
  * @return
  *   Returns the int format of timeEntered.
  *
  **/

  public int getTime(){
    return timeEntered;
  }

  /**
  * Sets a value for timeEntered.  O(1).
  *
  * @param time
  *    The new time when the Request was generated of timeEntered.
  *
  **/

  public void setTime(int time){
    timeEntered = time;
  }

}
