/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>FullQueueException</code> class creates a custom Exception to be
* called whenever the RFID has characters that aren't 0-9.
**/

class FullQueueException extends Exception{

  /**
  * Defines custom exception FullQueueException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The FullQueueException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public FullQueueException (String message){
      super(message);
    }
}
