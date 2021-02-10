/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>LocationCharsException</code> class creates a custom Exception to be
* called whenever a location has the wrong chars after the first character.
**/

class LocationCharsException extends Exception{

  /**
  * Defines custom exception LocationCharsException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The OutOfBoundsException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public LocationCharsException (String message){
      super(message);
    }
}
