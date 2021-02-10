/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>InvalidLengthException</code> class creates a custom Exception to be
* called whenever the length of the simulation is not greater than 0.
**/

class InvalidLengthException extends Exception{

  /**
  * Defines custom exception InvalidLengthException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The InvalidLengthException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public InvalidLengthException (String message){
      super(message);
    }
}
