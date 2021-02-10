/**
@author Ryan Aday
112754800
REC 30
**/

//For Extra Credit 02

/**
* The <code>PlaylistContainer</code> class creates a PlaylistContainer
* object that allows itself to be instantiated, have a current list set and
* gotten, add a list, and print all of the lists.
*
* NOTE: For Extra Credit 02, a playlistName accessor and mutator are available
* in Playlist.java.
**/

public class PlaylistContainer {
  Playlist FullList[];    //A Playlist array for all of the Playlists.
  int numberOfPlaylists = 0;  //A pointer to help indicate the number of lists.
  final int MAX_LISTS = 10;  //A fixed number of lists the container can store.
  Playlist currentList;  //A Playlist object of the current list.

  // Invariants:
  // The maximum number of playlists cannot exceed 10 playlists.

  /**
  * Constructs an instance of the Playlist class with no SongRecord objects in it.  O(1).
  *
  * <dt>Postcondition:
  *   <dd>This PlaylistContainer has been initialized to an empty list of Playlists.
  **/

  public PlaylistContainer(){
    FullList = new Playlist[MAX_LISTS];
    numberOfPlaylists = 0;
  }

  /**
  * Sets a value for currentList.  O(1).
  *
  * @param list
  *    The new playlist name of the current playlist.
  *
  **/

  public void setCurrentList(Playlist list){
    currentList = list;
  }

  /**
  * Adds a Playlist to the PlaylistContainer.  O(1).
  *
  * @param list
  *   The new Playlist object to add to this PlaylistContainer
  *
  * <dt>Precondition:
  *   <dd>This SongRecord object has been instantiated and 1 <=
  *   numberOfPlaylists <= MAX_LISTS + 1.
  *
  * @throws FullPlaylistListException
  *   Indicates that there is no more room inside of the PlaylistContainer to
  *   store the new SongRecord object.
  *
  **/

  public void addList(Playlist list) throws FullPlaylistListException{
    if (numberOfPlaylists == MAX_LISTS){
      throw new FullPlaylistListException("No more room exists for a new playlist.");
    }

    FullList[numberOfPlaylists] = list;
    numberOfPlaylists++;
  }

  /**
  * Gets a Playlist from the PlaylistContainer.  O(n).
  *
  * @param name
  *   The new Playlist object to add to this PlaylistContainer
  *
  * <dt>Precondition:
  *   <dd>This SongRecord object has been instantiated and 1 <=
  *   numberOfPlaylists <= MAX_LISTS + 1.
  *
  * @throws IllegalArgumentException
  *   Indicates that there is no Playlist that has the name.
  *
  **/

  public Playlist getPlaylist(String name){
    boolean check = true;
    for (Playlist list : FullList){
      try{
        if (list.getPlaylistName().equals(name)){  //must be .equals(), ==
                                                   //refers to location, not
                                                   //value
          return list;
        }
      }catch (NullPointerException e){} //this is for null Playlists that would
                                        //otherwise produce this error
    }
      throw new IllegalArgumentException();
  }

  /**
  * Prints a neatly formatted table of each Playlist in the PlaylistContainer on
  * its own line with its position number as shown in the sample output.  O(n).
  *
  * <dt>Precondition:
  *   <dd>This PlayListContainer object has been instantiated.
  * <dt>Postcondition:
  *   <dd>A neatly formatted table of each Playlist in the PlayListContainer
  *   on its own line with its position number has been displayed to the user.
  *
  **/

    public void printAllLists(){
      String result = "";
      System.out.println(String.format("%-8s%-25s", "Pos.", "Playlist Name" ));
      System.out.println("--------------------------");
      for (int arrayPos = 0; arrayPos < numberOfPlaylists; arrayPos++){
        System.out.println(String.format("%-8d%-25s", arrayPos + 1, FullList[arrayPos].getPlaylistName() ));
      }
      System.out.print("\n");
    }

  }
