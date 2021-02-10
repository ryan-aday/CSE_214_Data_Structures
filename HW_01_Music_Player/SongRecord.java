/**
@author Ryan Aday
112754800
REC 30
**/

/**
* The <code>SongRecord</code> class creates a SongRecord
* object that has the title, artist, minutes, and seconds
* of a song to be implemented in a Playlist class.
**/

public class SongRecord{
  String title, artist;
  int songMin, songSec;  //The minute and second times of the song.

  //NOTE: For Extra Credit 01
  String audioFile;  //The file name for the audio file to be played.

  // Invariants:
  // title, artist, songMin, and songSec always represents
  // the title, artist, minutes and seconds of a song.
  // audioFile always represents the file name of the song file.

  /**
  * Returns an instance of <code>SongRecord</code>.  O(1) since all methods are O(1).
  *
  * @param newTitle
  *    The title of the new SongRecord.
  * @param newArtist
  *    The artist of the new SongRecord.
  * @param newSongMin
  *    The minutes of the new SongRecord.
  * @param newSongSec
  *    The seconds of the new SongRecord.
  *
  **/

  public SongRecord(String newTitle, String newArtist, int newSongMin, int newSongSec) throws OutOfBoundsException{
    setTitle(newTitle);
    setArtist(newArtist);
    setSongMin(newSongMin);
    setSongSec(newSongSec);
  }

  /**
  * Returns the string format of the SongRecord.  O(1) since all methods are O(1).
  *
  * <dt>Precondition:
  *   <dd>The title, artist, songMin, and songSec variables are all defined.
  *
  * @return
  *   Returns the string format of SongRecord.
  *
  **/

  public String toString(){
    String result = String.format("%-25s%-25s%5s", getTitle(), getArtist(), getSongTime());
    return result;
  }

  /**
  * Returns the title of the SongRecord as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The title is defined.
  *
  * @return
  *   Returns the String format of title.
  *
  **/

  public String getTitle(){
    return title;
  }

  /**
  * Returns the artist of the SongRecord as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The artist is defined.
  *
  * @return
  *   Returns the String format of artist.
  *
  **/

  public String getArtist(){
    return artist;
  }

  /**
  * Returns the minutes of the SongRecord as an integer.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The songMin is defined.
  *
  * @return
  *   Returns the int format of songMin.
  *
  **/

  public int getSongMin(){
    return songMin;
  }

  /**
  * Returns the seconds of the SongRecord as an integer.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The songSec is defined.
  *
  * @return
  *   Returns the int format of songSec.
  *
  **/

  public int getSongSec(){
    return songSec;
  }

  /**
  * Returns a string with the songMin and songSec formatted with a colon.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The songSec is defined.
  *   <dd>The songSec is defined.
  *
  * @return
  *   Returns a string with a colon separating the songMin and songSec.
  *
  **/

  public String getSongTime(){
    String tempSec = String.valueOf(getSongSec());
    if (getSongSec() < 10){
      //Adds a 0 at the beginning of songSec if the seconds are only 1 digit.
      tempSec = "0" + getSongSec();
    }
    return songMin + ":" + tempSec;
  }

  /**
  * Sets a value for title.  O(1).
  *
  * @param newTitle
  *    The new title of the SongRecord.
  *
  **/

  public void setTitle(String newTitle){
    title = newTitle;
  }

  /**
  * Sets a value for title.  O(1).
  *
  * @param newArtist
  *    The new artist of the SongRecord.
  *
  **/

  public void setArtist(String newArtist){
    artist = newArtist;
  }

  /**
  * Returns a value for the minutes of the song.  O(1).
  *
  * @param newSongMin
  *   The new songMin of the SongRecord.
  * @throws OutOfBoundsException
  *   Throws exception when the minutes of the song must be greater than 0.
  *
  **/

  public void setSongMin(int newSongMin) throws OutOfBoundsException{
    if (newSongMin < 0){
      throw new OutOfBoundsException("The minutes for the song must be greater than 0.");
    }else songMin = newSongMin;
  }

  /**
  * Returns a value for the seconds of the song.  O(1).
  *
  * @param newSongMin
  *   The new songSec of the SongRecord.
  * @exception OutOfBoundsException
  *   Throws exception when the seconds of the song must be greater than 0.
  *
  **/

  public void setSongSec(int newSongSec) throws OutOfBoundsException{
    if (newSongSec < 0 || newSongSec > 59){
      throw new OutOfBoundsException("The seconds for the song must be greater than 0 or less than 59.");
    }else{
      songSec = newSongSec;
    }
  }

  //Extra Credit 01

  /**
  * Returns an instance of <code>SongRecord</code>.  O(1) since all methods used are O(1).
  *
  * @param newTitle
  *    The title of the new SongRecord.
  * @param newArtist
  *    The artist of the new SongRecord.
  * @param newSongMin
  *    The minutes of the new SongRecord.
  * @param newSongSec
  *    The seconds of the new SongRecord.
  * @param fileName
  *    The audio file name of the new SongRecord.
  *
  **/

  public SongRecord(String newTitle, String newArtist, int newSongMin, int newSongSec, String fileName) throws OutOfBoundsException{
    setTitle(newTitle);
    setArtist(newArtist);
    setSongMin(newSongMin);
    setSongSec(newSongSec);
    setFile(fileName);
  }

  /**
  * Returns the audio file of the SongRecord as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The audioFile is defined.
  *
  * @return
  *   Returns the String format of audioFile.
  *
  **/

  public String getFile(){
    return audioFile;
  }

  /**
  * Sets a value for audioFile.  O(1).
  *
  * @param newFile
  *    The new audio file of the SongRecord.
  *
  **/

  public void setFile(String newFile){
    audioFile = newFile;
    return;
  }
}
