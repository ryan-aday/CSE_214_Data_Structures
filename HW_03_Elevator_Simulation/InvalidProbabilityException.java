/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>InvalidProbabilityException</code> class creates a custom Exception
* to be called whenever the probability input is not within 0.00 or 1.00
* inclusive.
**/

class InvalidProbabilityException extends Exception{

  /**
  * Defines custom exception InvalidProbabilityException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The InvalidProbabilityException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public InvalidProbabilityException (String message){
      super(message);
    }
}
