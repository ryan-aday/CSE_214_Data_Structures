/**
@author Ryan Aday
112754800
REC 30
**/


//**NOTE:**  size() and getSong() should be both O(1), everything else O(n).


/**
* The <code>Playlist</code> class creates a Playlist
* object that allows itself to be instantiated, cloned, compared with another
* Playlist, have songs added and removed from it, print songs, and return a
* playlist with only a specific artist.
*
* NOTE: For Extra Credit 02, a playlistName accessor and mutator are available.
**/


public class Playlist{

  SongRecord songRecords[];  //An array for SongRecords.
  int songsCurrentlyInPlaylist = 0;  //A counter for the number of songs in
  //a playlist.
  final int MAX_SONGS = 50;  //A fixed number for the maxiumum possible
  //number of songs.

  // Invariants:
  // The maximum number of songs cannot exceed 50 songs.

  //NOTE: For Extra Credit 02
  String playlistName = "Default"; //A playlist name set to "Default" for
  //its default name.

  /**
  * Constructs an instance of the Playlist class with no SongRecord objects in it.  O(1).
  *
  * <dt>Postcondition:
  *   <dd>This Playlist has been initialized to an empty list of SongRecords.
  **/

  public Playlist(){
    //The SongRecord array is limited to only 50 songs.
    songRecords = new SongRecord[MAX_SONGS];
    songsCurrentlyInPlaylist = 0;
  }

  /**
  * Returns an instance of this <code>Playlist</code>.  O(1).
  *
  *@return
  *    The return value is a copy of this Playlist. Subsequent changes to
  *    the copy will not affect the original, nor vice versa. Note that the
  *    return value must be typecast to a Playlist before it can be used.
  *
  *@exception OutOfBoundsException
  *    Happens when the songSec or songMin are out of their appropriate range.
  *@exception FullPlaylistException
  *    Happens when there is no more space in the SongRecords array.
  **/

  public Object clone(){
    Playlist playlistCopy = new Playlist();
    for (int counter = 1; counter < this.size() + 1; counter++){
      SongRecord song = songRecords[counter - 1];

      try{
        SongRecord newRecord = new SongRecord(song.getTitle(), song.getArtist(), song.getSongMin(), song.getSongSec());
        playlistCopy.addSong(newRecord, counter);
      }catch (OutOfBoundsException e){
        System.out.println("Invalid song length.");
      }catch (FullPlaylistException e)
      {
        System.out.println("The playlist is full.");
      }

    }
    return (Object)playlistCopy;  //Needs to be typecast into Object
  }

  /**
  * Compares this Playlist to another object for equality.  O(n).
  *
  *@param obj
  *    An object in which this playlist is compared.
  *@return
  *    A return value of true indicates that obj refers to a Playlist object
  *   with the same SongRecords in the same order as this Playlist. Otherwise,
  *   the return value is false.
  *
  *NOTE:
  *   If obj is null or it is not a Playlist object, then the return value
  *   is false.
  **/

  public boolean equals(Object obj){
    if (obj instanceof Playlist && obj != null){
      Playlist tempP = (Playlist)obj; //A temporary Playlist.
      boolean pResult = true;  //A boolean that will be returned for the result.
      int counter = 0;  ///A counter for the location of the targeted song.
      for (SongRecord song : songRecords){
        if (song == null){
          if (tempP.songRecords[counter] == null){
            pResult = true;
          }
          else pResult = false;  //If a song is null
        }
        counter++;
      }
      return pResult && this.songsCurrentlyInPlaylist == tempP.songsCurrentlyInPlaylist && this.MAX_SONGS == tempP.MAX_SONGS;
    }
    return false;  //If obj is null
  }

  /**
  * Determines the number of SongRecords currently in this Playlist.
  * O(1).
  *
  * <dt>Precondition:
  *   <dd>This PlayList object has been instantiated.
  *
  *@return
  *    The number of SongRecords in this Playlist.
  *
  **/

  public int size(){
    return songsCurrentlyInPlaylist;
  }

  /**
  * Adds a song to the Playlist.  O(n).
  *
  * @param song
  *   The new SongRecord object to add to this Playlist
  * @param position
  *   The position in the playlist where the song will be inserted
  *
  * <dt>Precondition:
  *   <dd>This SongRecord object has been instantiated and 1 <= position <=
  *   songs_currently_in_playlist + 1. The number of SongRecord objects in this
  *   Playlist is less than max_songs.
  * <dt>Postcondition:
  *   <dd>The new SongRecord is now stored at the desired position in the
  *   Playlist. All SongRecords that were originally in positions greater than
  *   or equal to position are moved back one position. (Ex: If there are 5
  *   songs in a Playlist, positions 1-5, and you insert a new SongRecord at
  *   position 4, the new SongRecord will now be at position 4, the SongRecord
  *   that was at position 4 will be moved to position 5, and the SongRecord
  *   that was at position 5 will be moved to position 6).
  *
  * @throws IllegalArgumentException
  *   Indicates that position is not within the valid range.
  * @throws FullPlaylistException
  *   Indicates that there is no more room inside of the Playlist to store
  *   the new SongRecord object.
  *
  * NOTE: position refers to the position in the Playlist and not the position
  * inside the array.
  * NOTE: Inserting a song to position (songs_currently_in_playlist + 1) is
  * effectively the same as adding a song to the end of the Playlist.
  *
  **/

  public void addSong(SongRecord song, int position) throws FullPlaylistException{
    int arrayPos = position - 1;
    if (position > songsCurrentlyInPlaylist + 1 || position < 1){
      throw new IllegalArgumentException("Position is not within 1 to the current number of songs.");
    }
    if (MAX_SONGS == songsCurrentlyInPlaylist){
      throw new FullPlaylistException("No more room exists for a new song to be added.");
    }

    //For when the song is inserted between two other songs.
    if (songRecords[arrayPos] != null){
      for (int cPos = songRecords.length - 1; cPos > arrayPos; cPos--){
        songRecords[cPos] = songRecords[cPos - 1];
      }
    }
    songRecords[arrayPos] = song;
    songsCurrentlyInPlaylist++;
  }

  /**
  * Removes a song from the Playlist.  O(n).
  *
  * @param position
  *   The position in the playlist where the song will be removed from.
  *
  * <dt>Precondition:
  *   <dd>This PlayList object has been instantiated and 1 =< position <=
  *   songs_currently_in_playlist.
  * <dt>Postcondition:
  *   <dd>The SongRecord at the desired position in the Playlist has been
  *   removed. All SongRecords that were originally in positions greater than
  *   or equal to position are moved forward one position. (Ex: If there are 5
  *   songs in a Playlist, positions 1-5, and you remove the SongRecord at
  *   position 4, the SongRecord that was at position 5 will be moved to
  *   position 4).
  *
  * @throws IllegalArgumentException
  *     Indicates that position is not within the valid range.
  *
  * NOTE: position refers to the position in the Playlist and not the position
  * inside the array.
  *
  **/

  public void removeSong(int position){
    int arrayPos = position - 1;

    if (position > songsCurrentlyInPlaylist || position < 1){
      throw new IllegalArgumentException("Position is not within 1 to the current number of songs.");
    }
    for (int cPos = arrayPos; cPos < songRecords.length-1; cPos++){
      songRecords[cPos] = songRecords[cPos + 1];
    }
    songsCurrentlyInPlaylist--;
  }

  /**
  * Get the SongRecord at the given position in this Playlist object.  O(1).
  *
  * @param position
  *   The position in the playlist where the song will be removed from.
  *
  * <dt>Precondition:
  *   <dd>This PlayList object has been instantiated and 1 =< position <=
  *   songs_currently_in_playlist.
  * <dt>Postcondition:
  *   <dd>The SongRecord at the specified position in this Playlist object.
  *
  * @return
  *     The SongRecord at the specified position in this Playlist object.

  * @throws IllegalArgumentException
  *     Indicates that position is not within the valid range.
  *
  * NOTE: position refers to the position in the Playlist and not the position
  * inside the array.
  *
  **/

  public SongRecord getSong(int position){
    int arrayPos = position - 1;
    if (position < 1){
      throw new IllegalArgumentException("The position selected cannot be less than 1");
    }else if (position > songsCurrentlyInPlaylist){
      throw new IllegalArgumentException("The position selected cannot be larger than the songs currently in the playlist, " + songsCurrentlyInPlaylist);
    }else return songRecords[arrayPos];
  }

  /**
  * Prints a neatly formatted table of each SongRecord in the Playlist on its
  * own line with its position number as shown in the sample output.  O(n).
  *
  * <dt>Precondition:
  *   <dd>This PlayList object has been instantiated.
  * <dt>Postcondition:
  *   <dd>A neatly formatted table of each SongRecord in the Playlist on its
  *   own line with its position number has been displayed to the user.
  *
  * NOTE:  position refers to the position in the Playlist and not the position
  * inside the array.
  *
  **/

  public void printAllSongs(){
    System.out.println(String.format("%-8s%-25s%-25s%5s", "Song#", "Title", "Artist", "Length" ));
    System.out.println("-----------------------------------------------------------------");

    for (int arrayPos = 0; arrayPos < songsCurrentlyInPlaylist; arrayPos++){
      try{
        System.out.println(String.format("%-8d%55s", arrayPos + 1, songRecords[arrayPos].toString() ));
      }catch (NullPointerException e){}

    }
  }

    /**
    * Generates a new Playlist containing all SongRecords in the original
    * Playlist performed by the specified artist.  O(n).
    *
    * <dt>Precondition:
    *   <dd>The Playlist referred to by originalList has been instantiated.
    *
    * @param originalList
    *   The original Playlist.
    * @param artist
    *   The name of the artist.
    * @return
    *   A new Playlist object containing all SongRecords in the original
    *   Playlist performed by the specified artist.
    *
    * NOTE: The return value is null if either originalList or artist is null.
    * NOTE: The order of the SongRecords in the new Playlist should relate to
    * the order of the SongRecords in the old Playlist. For example, if the
    * original Playlist has 8 SongRecords, positions 1-8, and SongRecords 3, 6,
    * and 8 were performed by the specified artist, the new Playlist should
    * have the SongRecord originally at position 3 placed at location 1, the
    * SongRecord originally at position 6 placed at location 2, and the
    * SongRecord originally at position 8 placed at location 3.
    *
    **/

    public static Playlist getSongsByArtist(Playlist originalList, String artist){
      if (originalList == null){
        System.out.println("Original list has no songs.");
        return null;  //Refer to NOTE 2.
      }
      if (artist == null){
        System.out.println("No proper artist has been entered.");
        return null;  //Refer to NOTE 2.
      }

      Playlist artistList = new Playlist();
      int artistCounter = 0;

      for (int aCounter = 0; aCounter < originalList.size(); aCounter++){
        //NOTE:  .equals() must be used, not == since the latter refers to
        //location, not value.
        if (artist.equals(originalList.songRecords[aCounter].getArtist())){
          artistList.songRecords[artistCounter] = originalList.songRecords[aCounter];
          artistCounter++;
        }
      }

      artistList.songsCurrentlyInPlaylist = artistCounter;
      return artistList;

    }

    /**
    * Returns the string format of the Playlist.  O(n).
    *
    * <dt>Precondition:
    *   <dd>The title, artist, songMin, and songSec variables are all defined
    *   for each song.
    *
    * @return
    *   Returns the string format of Playlist.
    *
    **/

    public String toString(){
      String result = "";
      result = result + String.format("%-8s%-25s%-25s%5s", "Song#", "Title", "Artist", "Length" );
      result = result + "\n-----------------------------------------------------------------";
      for (int arrayPos = 0; arrayPos < songsCurrentlyInPlaylist; arrayPos++){
        result = result + "\n" + String.format("%-8d%55s", arrayPos + 1, songRecords[arrayPos].toString() );
      }
      return result;
    }

    //Extra Credit 02
    /**
    * Returns the playlist name of the Playlist as a String.  O(1).
    *
    * <dt>Precondition:
    *   <dd>The playlistName is defined.
    *
    * @return
    *   Returns the String format of playlistName.
    *
    **/

    public String getPlaylistName(){
      return playlistName;
    }

    /**
    * Sets a value for playlistName.  O(1).
    *
    * @param newName
    *    The new playlist name of the Playlist.
    *
    **/

    public void setPlaylistName(String newName){
      playlistName = newName;
    }
  }
