/**
@author Ryan Aday
112754800
REC 30
**/

/**
* The <code>FullPlaylistList</code> class creates a custom Exception to be
* called whenever the number of Playlist classes exceeds a PlaylistContainer's
* array size.
**/

class FullPlaylistListException extends Exception{

  /**
  * Defines custom exception FullPlaylistException.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The FullPlaylistListException is thrown.
  *
  * @return
  *   Returns a message when the printStackTrace() is called.
  *
  **/

    public FullPlaylistListException (String message){
      super(message);
    }
}
