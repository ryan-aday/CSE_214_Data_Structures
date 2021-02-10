/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>RFIDCharsException</code> class creates a custom Exception to be
* called whenever the RFID has characters that aren't 0-9.
**/

class RFIDCharsException extends Exception{

  /**
  * Defines custom exception RFIDCharsException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The RFIDCharsException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public RFIDCharsException (String message){
      super(message);
    }
}
