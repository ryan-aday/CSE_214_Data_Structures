/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>IllegalEmailException</code> class creates a custom Exception
* to be called whenever an email does not have the right format.
**/

public class IllegalEmailException extends Exception{

  /**
  * Defines custom exception IllegalEmailException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The IllegalEmailException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public IllegalEmailException (String message){
      super(message);
    }
}
