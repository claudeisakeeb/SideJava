import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.util.Scanner;

public class Playlist {
	
	private ArrayList<Song> playlist;
	Scanner keyboard = new Scanner(System.in);
	
	public Playlist(ArrayList<Song> p) {
		playlist = new ArrayList<Song>();
		for (Song s : p)	{
			playlist.add(s);
		}
	}
	
	public void addToPlaylist(String t, String a) {
		boolean present = false;
		for (int i = 0; i < playlist.size(); i++) {
			if (playlist.get(i).getTitle().toUpperCase().equals(t.toUpperCase()) && playlist.get(i).getArtist().toUpperCase().equals(a.toUpperCase())) {
				present = true;
				System.out.println(t +" by " +a +" is already in your playlist, add anyways? Type (y/n)");
				String addDupe = keyboard.next();
				if (addDupe.toUpperCase().contentEquals("Y")) {
					playlist.add(new Song(t, a));
					System.out.println(t + " by " +a +" has been added to your playlist!");
				}
				else {
					System.out.println("No songs were added to your playlist");

				}
				break;
			}
		}
		if (present == false) {
			playlist.add(new Song(t, a));
			System.out.println(t + " by " +a +" has been added to your playlist!");
		}
	}
	
	public void removeFromPlaylist(String t, String a) {
		boolean present = false;
		
		for (int i = 0; i < playlist.size(); i++) {
			if (playlist.get(i).getTitle().toUpperCase().equals(t.toUpperCase()) && playlist.get(i).getArtist().toUpperCase().equals(a.toUpperCase())){
				present = true;
				playlist.remove(i);
			}
		}
		if (present == false) {
			System.out.println("Song removal failed successfully - song not found");
		}
		else {
			System.out.println("Song removal successful - all copies of " +t +" by " +a +" have been removed from your playlist!");
		}
	}
	
	public void removeDuplicates(String t, String a) {
		boolean dupes = false;
		int counter = 0;
		int startpos = -1;
		
		for (int i = 0; i < playlist.size(); i++) {
			if (playlist.get(i).getTitle().toUpperCase().equals(t.toUpperCase()) && playlist.get(i).getArtist().toUpperCase().equals(a.toUpperCase())) {
				if (startpos == -1) {
					startpos = i;
				}
				counter ++;
			}
			if (counter == 2) {
				dupes = true;
				break;
			}
		}
		
		if (dupes == false) {
			System.out.println("No duplicates of " + t +" by " +a +" were found! Consider using the removeFromPlaylist function instead!");
		}
		else {
			for (int i = startpos + 1; i < playlist.size(); i++) {
				if (playlist.get(i).getTitle().toUpperCase().equals(t.toUpperCase()) && playlist.get(i).getArtist().toUpperCase().contentEquals(a.toUpperCase())) {
					playlist.remove(i);
				}
			}
			System.out.println("Duplicates of " +t +" by " +a + " were removed successfully");
		}
	}
	
	public void play() {
		boolean shuffle;
		String playResponse = "";
		ArrayList<Song> playingList = new ArrayList<Song>();
		ArrayList<Song> temp = new ArrayList<Song>();
		
		for (Song s : playlist) {
			temp.add(s);
		}
		System.out.println("Shuffle? Type (y/n): ");
		String shuffleResponse = keyboard.next();
		if (shuffleResponse.toUpperCase().equals("Y")) {
			shuffle = true;
		}
		else {
			shuffle = false;
		}
		
		if (shuffle == false) {
			//plays audio
			
			/*
			for(int i = 0; i < playlist.size(); i++) {
				
			try {
			FileInputStream fileInputStream = new FileInputStream(playlist.get(i).getFile());
			AdvancedPlayer player = new AdvancedPlayer(fileInputStream);
			System.out.println("Now playing: " +playlist.get(i).getTitle());
			boolean playing = true;		
			player.play();			
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(JavaLayerException e) {
				e.printStackTrace();
			}
			}
			*/
		
		while (!playResponse.toUpperCase().equals("Q")) {
			System.out.println(playingList);
			System.out.println("*****PLAYING MENU*****");
			System.out.println("(1) Add a song to queue" + "\n"
							 + "(2) Skip song" + "\n"
							 + "(Q) Quit playing");
			playResponse = keyboard.nextLine();
			
			//add song to queue
			System.out.println("Input the name of the song you would like to add to queue: ");
			keyboard.nextLine();
			String name = keyboard.nextLine();
			System.out.println("Input the artist of the song you would like to add to queue: ");
			String artist = keyboard.nextLine();
			System.out.println(name + artist);
		}
		}
		else {
			int len = playlist.size();
			for (int i = len-1; i > -1; i--) {
				int pos = (int) Math.floor((Math.random() *(i+1)));
				playingList.add(0, temp.get(pos));
				temp.remove(pos);
			}
			System.out.println(playingList);
			
			/*
			for(int i = 0; i < playingList.size(); i++) {
				
				try {
				FileInputStream fileInputStream = new FileInputStream(playingList.get(i).getFile());
				Player player = new Player(fileInputStream);
				System.out.println("Now playing: " +playingList.get(i).getTitle());
				player.play();
				
				} catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(JavaLayerException e) {
					e.printStackTrace();
				}
				}
				*/
		}
	}
	
	public void sortByTitle(ArrayList<Song> playlist) {
		for (int i = 0; i < playlist.size(); i++) {
			for (int j = 0; j < playlist.size()-1;j++) {
				if (playlist.get(j).getTitle().compareTo(playlist.get(j+1).getTitle()) > 0) {
					Song temp = playlist.get(j);
					playlist.set(j, playlist.get(j+1));
					playlist.set(j+1, temp);
				}
			}
		}
		System.out.println("Your playlist has been successfully sorted by song title!");
		System.out.println(playlist);
	}
	
	public void sortByArtist(ArrayList<Song> playlist) {
		for (int i = 0; i < playlist.size(); i++) {
			for (int j = 0; j < playlist.size()-1;j++) {
				if (playlist.get(j).getArtist().compareTo(playlist.get(j+1).getArtist()) > 0) {
					Song temp = playlist.get(j);
					playlist.set(j, playlist.get(j+1));
					playlist.set(j+1, temp);
				}
			}
		}
		System.out.println("Your playlist has been successfully sorted by artist!");
		System.out.println(playlist);
	}
	
	public String toString() {
		return playlist.toString();
	}
}
