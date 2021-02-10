/**
@author Ryan Aday
112754800
REC 30
**/

/**
* The <code>FullPlaylistException</code> class creates a custom Exception to be
* called whenever the number of songs in a playlist exceeds the space the playlist has.
**/

class FullPlaylistException extends Exception{

  /**
  * Defines custom exception FullPlaylistException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The FullPlaylistException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public FullPlaylistException (String message){
      super(message);
    }
}
