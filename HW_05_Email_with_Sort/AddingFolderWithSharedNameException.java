/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>AddingFolderWithSharedNameException</code> class creates a custom Exception
* to be called whenever a folder is attempted to be added to a mailbox if there
* already is a folder with that name.
**/

public class AddingFolderWithSharedNameException extends Exception{

  /**
  * Defines custom exception AddingFolderWithSharedNameException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The AddingFolderWithSharedNameException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public AddingFolderWithSharedNameException (String message){
      super(message);
    }
}
