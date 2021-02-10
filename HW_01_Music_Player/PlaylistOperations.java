/**
@author Ryan Aday
112754800
REC 30
**/

import java.util.Scanner;  //Needed for scanning the terminal for user input.

//NOTE: For Extra Credit 01
import java.io.IOException;  //Needed for input/output issues regarding URL.
import javax.sound.sampled.*;  //Needed to sample the audio file.

public class PlaylistOperations{

  public static void main(String[] args){
    PlaylistContainer container = new PlaylistContainer();

    //Inititalize the Playlist
    Playlist pL = new Playlist();
    pL.setPlaylistName("Default");

    //Initialize the PlaylistContainer from Extra Credit 02
    try{
      container.addList(pL);
      container.setCurrentList(pL);
    }catch (FullPlaylistListException e){
      System.out.println("Program didn't initialize correctly.");
    }

    boolean exit = false;  //Needed to loop for menu to keep popping up

    //loop keeps program runnning until closes

    while(!exit){

      //Prints Menu

      System.out.print(String.format("%-25s%5s\n%-25s%5s\n%-25s%5s\n%-25s%5s\n%-25s%5s\n%-25s%5s\n%-25s%5s\n", "Add Song: ", "A", "Get Song: ", "G", "Remove Song: ", "R", "Print All Songs: ", "P",  "Print Songs By Artist: ", "B", "Size: ", "S", "Quit: ", "Q" ));

      System.out.println("\nExtra Credit 01");
      System.out.print(String.format("%-25s%5s\n%-25s%5s\n", "Add Song with File", "L", "Play Song: ", "O"));

      System.out.println("\nExtra Credit 02");
      System.out.print(String.format("%-25s%5s\n%-25s%5s\n%-25s%5s\n%-25s%5s\n%-25s%5s\n", "Create New Playlist: ", "N", "Change Current Playlist: ", "V", "Copy Current Playlist: ", "C", "Compare Playlists: ", "E", "Display All Playlists: ", "D"));

      Scanner sc = new Scanner(System.in);
      System.out.print("\nSelect a Menu Option: ");
      char operation = sc.nextLine().charAt(0);
      System.out.print("\n");

      //Operations

      //Add song (normal)
      if (operation == 'A' || operation == 'a'){
        //Asks for parameters
        System.out.print("Enter the song title: ");
        String title = sc.nextLine();

        System.out.print("Enter the song artist: ");
        String artist = sc.nextLine();

        System.out.print("Enter the song length (minutes): ");
        int songMin = sc.nextInt();

        System.out.print("Enter the song length (seconds): ");
        int songSec = sc.nextInt();

        System.out.print("Enter the position: ");
        int position = sc.nextInt();

        try {
          SongRecord song = new SongRecord(title, artist, songMin, songSec);
          pL.addSong(song, position);
          System.out.print("\n");
          System.out.print("Song Added: "+ title + " By "+ artist);
          System.out.print("\n");

        }catch (OutOfBoundsException e){
          System.out.println("Invalid song length.\n");
        }catch (IllegalArgumentException e){
          System.out.println("Invalid position for adding the new song.\n");
        }catch (FullPlaylistException e){
          System.out.println("The playlist is full.\n");
        }
      }

      //Print Songs by Artist
      if (operation == 'B' || operation == 'b'){
        //Asks for parameters
        System.out.print("Enter the song artist: ");
        String artist = sc.nextLine();
        System.out.print("\n");

        Playlist artistPlaylist = pL.getSongsByArtist(pL, artist);
        artistPlaylist.printAllSongs();
        System.out.print("\n");
      }

      //Get Specific Song
      if (operation == 'G' || operation == 'g'){
        //Asks for parameters
        System.out.print("Enter the position: ");
        int position = sc.nextInt();
        System.out.print("\n");

        try{
          SongRecord checkL = pL.getSong(position);

          //Initializes the header
          System.out.println(String.format("%-8s%-25s%-25s%5s", "Song#", "Title", "Artist", "Length" ));
          System.out.println("-----------------------------------------------------------------");
          System.out.println(String.format("%-8d%55s", position, checkL ));
          System.out.print("\n");
        }catch (IllegalArgumentException e){
          System.out.println("The position chosen is not within a valid range.\n");

        }
      }

      //Remove Specific Song
      if (operation == 'R' || operation == 'r'){
        //Asks for parameters
        System.out.print("Enter the position: ");
        int position = sc.nextInt();
        System.out.print("\n");

        try{
          pL.removeSong(position);
          System.out.println("Song Removed at position " + position);
          System.out.print("\n");
        }catch (IllegalArgumentException e){
          System.out.println("There are no songs at position " + position + " to remove.\n");
        }

      }

      //Prints All Songs
      if (operation == 'P' || operation == 'p'){
        pL.printAllSongs();
        System.out.print("\n");
      }

      //Prints How Many Songs in Current Playlist
      if (operation == 'S' || operation == 's'){
        System.out.println("There are " + pL.size() + " song(s) in the current playlist.");
        System.out.print("\n");
      }

      //Closes the Program
      if (operation == 'Q' || operation == 'q'){
        System.out.println("System terminating normally...");
        break;
      }


      //Extra Credit 01

      //Add song (with song file name)
      if (operation == 'L' || operation == 'l'){
        //Asks for parameters
        System.out.print("Enter the song title: ");
        String title = sc.nextLine();

        System.out.print("Enter the song artist: ");
        String artist = sc.nextLine();

        System.out.print("Enter the song file (Press Enter if no file to add): ");
        String file = sc.nextLine();

        System.out.print("Enter the song length (minutes): ");
        int songMin = sc.nextInt();

        System.out.print("Enter the song length (seconds): ");
        int songSec = sc.nextInt();

        System.out.print("Enter the position: ");
        int position = sc.nextInt();
        //System.out.println("\n");

        try {
          if (file != null){
            SongRecord song = new SongRecord(title, artist, songMin, songSec, file);
            pL.addSong(song, position);
            System.out.print("\n");
            System.out.print("Song Added: "+ title + " By "+ artist);
            System.out.print("\n");
          }
          else{
            SongRecord song = new SongRecord(title, artist, songMin, songSec);
            pL.addSong(song, position);
            System.out.print("\n");
            System.out.print("Song Added: "+ title + " By "+ artist);
            System.out.print("\n");
          }

        }catch (OutOfBoundsException e){
          System.out.println("Invalid song length.\n");
        }catch (IllegalArgumentException e){
          System.out.println("Invalid position for adding the new song.\n");
        }catch (FullPlaylistException e){
          System.out.println("The playlist is full.\n");
        }
      }

      //Play a Song
      if (operation == 'O' || operation == 'o'){
        //Asks for parameters
        System.out.print("Enter the position: ");
        int position = sc.nextInt();
        System.out.print("\n");

        SongRecord song = pL.getSong(position);

        try{
          new PlaySong(song.getFile());
        }catch (LineUnavailableException e){
          System.out.println("Song interrupted.\n");
        } catch (IOException e) {
          System.out.println("Input/output stream error.\n");
        } catch (InterruptedException e) {
          System.out.println("Song file interrupted.\n");
        } catch (NullPointerException e) {
          System.out.println("No song file.\n");
        } catch (UnsupportedAudioFileException e){
          System.out.println("Song file is not a .wav file.\n");
        }

      }

      //Extra Credit 02
      //Create New Current Playlist
      if (operation == 'N' || operation == 'n'){
        //Ask parameters
        System.out.print("Enter the new playlist name: ");
        String name = sc.nextLine();
        System.out.print("\n");

        Playlist newPlaylist = new Playlist();
        newPlaylist.setPlaylistName(name);

        try{
          container.addList(newPlaylist);
          container.setCurrentList(newPlaylist);
          pL = newPlaylist;
        }catch (FullPlaylistListException e){
          System.out.println("No more playlists can be made.");
        }

        System.out.print("Playlist Added: "+ name);
        System.out.println("\n");
      }

      //Change the Current Playlist
      if (operation == 'V' || operation == 'v'){
        //Ask parameters
        System.out.print("Enter the playlist name: ");
        String name = sc.nextLine();
        System.out.print("\n");

        try{
          container.currentList = container.getPlaylist(name);
          pL = container.currentList;
        }catch (IllegalArgumentException e){
          System.out.println("There is no playlist with that name.\n");
        }

        System.out.print("Current Playlist: " + name);
        System.out.println("\n");
      }

      //Clones Current Playlist
      //NOTE:  When cloning the current playlist doesn't change from the
      //original Playlist.
      if (operation == 'C' || operation == 'c'){
        //Ask parameters
        System.out.print("Enter the playlist name of the cloned list: ");
        String name = sc.nextLine();
        System.out.print("\n");

        Playlist clonedList = (Playlist)pL.clone();
        clonedList.setPlaylistName(name);

        try{
          container.addList(clonedList);
        }catch (FullPlaylistListException e){
          System.out.println("No more playlists can be made.");
        }
        System.out.print("Cloned playlist successful as " + name + ".");
        System.out.println("\n");
      }

      //Compare Playlists to See if They Are Equal
      if (operation == 'E' || operation == 'e'){
        //Ask parameters
        System.out.print("Enter the playlist name to be compared to the current list: ");
        String name = sc.nextLine();
        System.out.print("\n");

        System.out.println("Current playlist is " + pL.getPlaylistName() + "\n");

        try{
          Playlist testPlaylist = container.getPlaylist((String)name);
          boolean same = pL.equals((Object)testPlaylist);
          System.out.println("It is "+ String.valueOf(same) + " that the current playlist and " + name + " are the same.\n\n");
        }catch (IllegalArgumentException e){
          System.out.println("The playlist to compare does not exist.\n");
        }

      }

      //Prints ALl Playlists with Names and Position No.
      if (operation == 'D' || operation == 'd'){
        System.out.println("\n");
        container.printAllLists();
      }
    }
  }
}
