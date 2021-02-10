/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>LocationStartException</code> class creates a custom Exception to be
* called whenever the first character of a location is incorrect.
**/

class LocationStartException extends Exception{

  /**
  * Defines custom exception LocationStartException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The LocationStartException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public LocationStartException (String message){
      super(message);
    }
}
