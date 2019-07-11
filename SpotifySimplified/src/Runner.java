import java.util.ArrayList;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.util.Scanner;

public class Runner{
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner (System.in);
		
		ArrayList<Song> songs = new ArrayList<Song>();
		
		songs.add(new Song("Dream", "Suzy"));
		songs.add(new Song("FANCY", "TWICE"));
		songs.add(new Song("Bad Boy", "Red Velvet"));
		
		Playlist test = new Playlist(songs);
		
		String response = " ";
		
		while (!response.toUpperCase().contentEquals("Q")) {
			System.out.print("\n");
			System.out.println("*****MENU*****");
			System.out.println("(1) Play playlist" + "\n"
								+"(2) Add a song to the playlist" + "\n"
								+"(3) Remove a song from the playlist" + "\n"
								+"(4) Remove duplicates of a song from the playlist" +"\n"
								+"(5) View playlist entries" + "\n"
								+"(Q) Exit");
			System.out.println("Enter the index of the activity you would like to do: ");
			response = keyboard.next();
			
			//keyboard response 1
			if (response.equals("1")) {
				test.play();
			}
			//keyboard response 2
			else if (response.equals("2")) {
				System.out.println("Enter the name of the song you would like to add: ");
				keyboard.nextLine();
				String song = keyboard.nextLine();
				System.out.println("Enter the artist of the song you would like to remove: ");
				String artist = keyboard.nextLine();
				test.addToPlaylist(song, artist);
			}
			//keyboard response 3
			else if (response.equals("3")) {
				System.out.println("Enter the name of the song you would like to remove: ");
				keyboard.nextLine();
				String song = keyboard.nextLine();
				System.out.println("Enter the artist of the song you would like to remove: ");
				String artist = keyboard.nextLine();
				test.removeFromPlaylist(song, artist);
			}
			//keyboard response 4
			else if (response.equals("4")) {
				System.out.println("Enter the name of the song whose duplicates you would like to remove: ");
				keyboard.nextLine();
				String song = keyboard.nextLine();
				System.out.println("Enter the artist of the song whose duplicates you would like to remove: ");
				String artist = keyboard.nextLine();
				test.removeDuplicates(song, artist);
			}
			else if (response.equals("5")) {
				System.out.println(test);
			}
		}
	}
}