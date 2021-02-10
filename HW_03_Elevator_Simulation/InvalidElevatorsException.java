/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>InvalidElevatorsException</code> class creates a custom Exception
* to be called whenever the input for the number of elevators is smaller than 1.
**/

class InvalidElevatorsException extends Exception{

  /**
  * Defines custom exception InvalidElevatorsException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The InvalidElevatorsException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public InvalidElevatorsException (String message){
      super(message);
    }
}
