/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>NotCartException</code> class creates a custom Exception to be
* called when there are no items in the list with the right RFID for moveItem()
in ItemList.
**/

class NotCartException extends Exception{

  /**
  * Defines custom exception NotCartException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The NotCartException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public NotCartException (String message){
      super(message);
    }
}
