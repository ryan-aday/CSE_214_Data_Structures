/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>EmptyQueueException</code> class creates a custom Exception to be
* called whenever RequestQueue is empty and a dequeue() method is thrown.
**/

class EmptyQueueException extends Exception{

  /**
  * Defines custom exception EmptyQueueException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The EmptyQueueException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public EmptyQueueException (String message){
      super(message);
    }
}
