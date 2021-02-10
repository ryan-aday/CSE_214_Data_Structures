/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>InvalidFloorsException</code> class creates a custom Exception to be
* called whenever input for the number of floors is not greater than 0.
**/

class InvalidFloorsException extends Exception{

  /**
  * Defines custom exception InvalidFloorsException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The InvalidFloorsException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public InvalidFloorsException (String message){
      super(message);
    }
}
