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
		songs.add(new Song("ICY", "ITZY"));
		songs.add(new Song("LOVE SCENARIO", "IKON"));
		
		Playlist test = new Playlist(songs);
		
		String response = " ";
		
		while (!response.toUpperCase().contentEquals("Q")) {
			System.out.print("\n");
			System.out.println("*****MENU*****");
			System.out.println("(1) Play playlist" + "\n"
								+"(2) Add a song to the playlist" + "\n"
								+"(3) Remove a song from the playlist" + "\n"
								+"(4) Remove duplicates of a song from the playlist" +"\n"
								+"(5) Sort playlist" + "\n"
								+"(6) View playlist entries" + "\n"
								+"(Q) Exit");
			System.out.println("Enter the index of the activity you would like to do: ");
			response = keyboard.next();
			
			//play playlist
			if (response.equals("1")) {
				test.play();
			}
			//add song to playlist
			else if (response.equals("2")) {
				System.out.println("Enter the name of the song you would like to add: ");
				keyboard.nextLine();
				String song = keyboard.nextLine();
				System.out.println("Enter the artist of the song you would like to remove: ");
				String artist = keyboard.nextLine();
				test.addToPlaylist(song, artist);
			}
			//remove song from playlist
			else if (response.equals("3")) {
				System.out.println("Enter the name of the song you would like to remove: ");
				keyboard.nextLine();
				String song = keyboard.nextLine();
				System.out.println("Enter the artist of the song you would like to remove: ");
				String artist = keyboard.nextLine();
				test.removeFromPlaylist(song, artist);
			}
			//remove duplicates from playlist
			else if (response.equals("4")) {
				System.out.println("Enter the name of the song whose duplicates you would like to remove: ");
				keyboard.nextLine();
				String song = keyboard.nextLine();
				System.out.println("Enter the artist of the song whose duplicates you would like to remove: ");
				String artist = keyboard.nextLine();
				test.removeDuplicates(song, artist);
			}
			//sort playlist
			else if (response.contentEquals("5")) {
				System.out.println("Would you like to sort by (1) Song title or (2) Artist?");
				int sort = keyboard.nextInt();
				if (sort == 1) {
					test.sortByTitle(songs);
				}
				else if (sort == 2) {
					test.sortByArtist(songs);
				}
				else {
					System.out.println("Invalid input, please try again.");
				}
			}
			//view playlist entries
			else if (response.equals("6")) {
				System.out.println(test);
			}
		}
	}
}