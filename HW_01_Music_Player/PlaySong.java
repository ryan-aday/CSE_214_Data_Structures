/**
@author Ryan Aday
112754800
REC 30
**/

//NOTE:  FOr Extra Credit 01

import java.io.IOException;  //Needed for input/output issues regarding URL.
import java.net.URL;  //Needed for locating the file path for the audio file.
import javax.sound.sampled.*;  //Needed to sample the audio file.
import javax.sound.sampled.LineEvent.Type;  //Needed for seeing whether the audio is finished.

/**
* The <code>PlaySong</code> class creates a PlaySong
* object that plays a .wav file within the same folder as the class given its
* file name, until it stops.
**/

public class PlaySong {
  private AudioListener listener = new AudioListener(); //An AudioListener that
  //will be used to continuously listen to the song file to see when it ends.

  /**
  * Plays a .wav song file until it ends.  O(n) since the synchronized methods
  * are O(n).
  *
  * @param newTitle
  *    The filename of the .wav file as a String.
  *
  * @exception UnsupportedAudioFileException
  *   Indicates that the file is not a .wav file.
  * @exception IOException
  *   Indicates that there are issues regarding the URL for the audio input stream.
  * @exception LineUnavailableException
  *   Indicates that there are no audio lines to be read within the .wav file.
  * @exception InterruptedException
  *   Indicates that the file was interrupted when playing.
  **/

  public PlaySong(String fileName) throws LineUnavailableException,
  InterruptedException, IOException, UnsupportedAudioFileException{

    try {
      //Creates a URL for the location of the sound file.
      URL url = this.getClass().getClassLoader().getResource(fileName);

      //Creates an audio stream based off of the file.
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

      // Clips the audio stream.
      Clip clip = AudioSystem.getClip();

      //Adds a line listener to see when the song ends.
      clip.addLineListener(listener);

      // Plays song until it ends.
      clip.open(audioIn);
      clip.start();
      listener.waitUntilDone();

    } catch (UnsupportedAudioFileException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}

/**
* The <code>AudioListener</code> class uses the LineListener import
* that constantly updates and checks to see whether the audio file has finished
* playing.
**/

class AudioListener implements LineListener {
  private boolean done = false;

  /**
  * Abstract method for waitUntilDone().  O(n).
  *
  * @param event
  *    A LineEvent to be updated.
  *
  **/

  public synchronized void update(LineEvent event) {
    Type eventType = event.getType();
    if (eventType == Type.STOP || eventType == Type.CLOSE) {
      done = true;
      notifyAll();
    }
  }

  /**
  * Allows for PlaySong to fully wait until a song is played to its entirety.
  * O(n).
  *
  * @throws InterruptedException
  *    Throws exception when the sound file gets interrupted when playing.
  */
  public synchronized void waitUntilDone() throws InterruptedException {
    while (!done) {
      wait();
    }
  }
}
