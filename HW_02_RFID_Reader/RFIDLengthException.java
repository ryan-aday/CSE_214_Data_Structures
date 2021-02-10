/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>RFIDLengthException</code> class creates a custom Exception to be
* called whenever an RFID is not 9 characters long.
**/

class RFIDLengthException extends Exception{

  /**
  * Defines custom exception RFIDLengthException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The RFIDLengthException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public RFIDLengthException (String message){
      super(message);
    }
}
