/**
@author Ryan Aday
112754800
REC 30
**/

/**
* The <code>OutOfBounds</code> class creates a custom Exception to be
* called whenever a value exceeds a certain range.
**/

class OutOfBoundsException extends Exception{

  /**
  * Defines custom exception OutOfBoundsException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The OutOfBoundsException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public OutOfBoundsException (String message){
      super(message);
    }
}
