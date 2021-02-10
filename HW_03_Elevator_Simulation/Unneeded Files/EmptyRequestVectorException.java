/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>EmptyRequestVectorException</code> class creates a custom Exception to be
* called whenever the RFID has characters that aren't 0-9.
**/

class EmptyRequestVectorException extends Exception{

  /**
  * Defines custom exception EmptyRequestVectorException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The EmptyRequestVectorException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public EmptyRequestVectorException (String message){
      super(message);
    }
}
