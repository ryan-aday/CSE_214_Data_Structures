/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>Elevator</code> class creates an Elevator object that contains
* a current floor, an elevator state, and a temporary wait time for
* Simulator to use to calculate the total wait time, with accessor and
* mutator methods.
*
**/

public class Elevator{

  public int currentFloor;  //For the current floor of the elevator
  public int elevatorState; //For the state of the elevator (described below)
  public int tempWaitTime;  //For Simulator to callculate totalWaitTime
  public Request request;

  //Must use static final to create a constant since const final not allowed
  static final int IDLE = 0;
  static final int TO_SOURCE = 1;
  static final int TO_DESTINATION = 2;

  // Invariants:
  // IDLE is for when the elevator has reached its destination and is looking
  // for a new passenger.
  // TO_SOURCE is for when the elevator is heading to pick up its new passenger.
  // TO_DESTINATION is for when the elevator is headed towards its passenger's
  // destination.
  // None of these vars should change.

  /**
  * Constructs the Elevator with no requests, an IDLE state, and at the bottom
  * floor.  O(1).
  *
  **/

  public Elevator(){
    request = null;
    elevatorState = IDLE;
    currentFloor = 1;
  }

  /**
  * Returns the currentFloor of the Elevator as an int.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The currentFloor is defined.
  *
  * @return
  *   Returns the int format of currentFloor.
  *
  **/

  public int getCurrentFloor(){
    return currentFloor;
  }

  /**
  * Returns the elevatorState of the Elevator as an int.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The elevatorState is defined.
  *
  * @return
  *   Returns the int format of elevatorState.
  *
  **/

  public int getCurrentState(){
    return elevatorState;
  }

  /**
  * Returns the request of the Elevator as a Request.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The request is defined.
  *
  * @return
  *   Returns the Request format of request.
  *
  **/

  public Request getRequest(){
    return request;
  }

  /**
  * Returns the tempWaitTime of the Elevator as an int.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The tempWaitTime is defined.
  *
  * @return
  *   Returns the int format of tempWaitTime.
  *
  **/

  public int getTempWaitTime(){
    return tempWaitTime;
  }

  /**
  * Sets a value for currentFloor.  O(1).
  *
  * @param newFloor
  *    The new floor for where the Elevator is.
  *
  **/

  public void setCurrentFloor(int newFloor){
    currentFloor = newFloor;
  }

  /**
  * Sets a value for elevatorState.  O(1).
  *
  * @param newState
  *    The new Elevator state.
  *
  **/
  public void setCurrentState(int newState){
    elevatorState = newState;
  }

  /**
  * Sets a value for request.  O(1).
  *
  * @param newRequest
  *    The new Request of the Elevator.
  *
  **/

  public void setRequest(Request newRequest){
    request = newRequest;
  }

  /**
  * Sets a value for tempWaitTime.  O(1).
  *
  * @param time
  *    The new time of the Elevator tempWaitTime.
  *
  **/


  public void setTempWaitTime(int time){
    tempWaitTime = time;
  }

}
