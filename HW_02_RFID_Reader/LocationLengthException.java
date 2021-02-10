/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>OutOfBounds</code> class creates a custom Exception to be
* called whenever a value exceeds a certain range.
**/

class LocationLengthException extends Exception{

  /**
  * Defines custom exception LocationLengthException, is called whenever a location
  * is not 6 characters or 4 characters long depending on location type.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The LocationLengthException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public LocationLengthException (String message){
      super(message);
    }
}
