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
	}//end add to playlist
	
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
	}//end remove from playlist
	
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
	}//end remove duplicates
	
	public void play() {
		String playResponse = "";
		ArrayList<Song> playingList = new ArrayList<Song>();
		ArrayList<Song> discardList = new ArrayList<Song>();

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
		for (int i = 0; i < playlist.size(); i++) {
			playingList.add(playlist.get(i));
		}
		
		while (!playResponse.toUpperCase().equals("Q")) {
			System.out.println();
			System.out.println("Current queue: " + playingList);
			System.out.println("Now playing: " +playingList.get(0));
			System.out.println("*****PLAYING MENU*****");
			System.out.println("(1) Next song" + "\n"
							 + "(2) Previous song" + "\n"
							 + "(3) Add a song to queue" + "\n"
							 + "(4) Shuffle playlist" + "\n"
							 + "(5) View play history" + "\n"
							 + "(Q) Quit playing");
			playResponse = keyboard.next();
			
			//next song
			if (playResponse.contentEquals("1")) {
				if (playingList.size() > 1) {
					discardList.add(playingList.remove(0));
					System.out.println("Skip to next song successful!");
				}
				else {
					System.out.println("There is no next song!");
				}
			}//end next song
			
			//previous song
			else if (playResponse.equals("2")) {
				if (discardList.size() < 1) {
					System.out.println("There is no previous song!");
				}
				else {
					playingList.add(0, discardList.remove(discardList.size()-1));
					System.out.println("Revert to previous song successful!");
				}
			}//end previous song
			
			//add song to queue
			else if (playResponse.equals("3")) {
			System.out.println("Input the name of the song you would like to add to queue: ");
			keyboard.nextLine();
			String name = keyboard.nextLine();
			System.out.println("Input the artist of the song you would like to add to queue: ");
			String artist = keyboard.nextLine();
			int startpos = -1;
			for (int i = 0; i < playlist.size(); i++) {
				if (playlist.get(i).getTitle().toUpperCase().equals(name.toUpperCase()) && playlist.get(i).getArtist().toUpperCase().equals(artist.toUpperCase())) {
						for (int j = 1; j < playingList.size(); j++) {
						if (playingList.get(j).getQueue() == false) {
							startpos = j;
							break;
							}
						}
					
				}
			}
			if (startpos < 0) {
				System.out.println("The song " + name + " by " +artist +" was not found.");
				}
			else {
				playingList.add(startpos, new Song(name, artist));
				playingList.get(startpos).setQueueOn();
				}
			}//end add song to queue
			
			//shuffle playlist
			else if (playResponse.contentEquals("4")) {
				if (playingList.size() < 3) {
					System.out.println("There aren't enough songs to shuffle (At least 2 in queue required)");
				}
				else {
				int startpos = 1;
				for (int i = 1; i < playingList.size(); i ++) {
					if (playingList.get(i).getQueue() == false);
					startpos = i;
					break;
				}
				
				for (int pos = startpos; pos < playingList.size(); pos ++) {
					int tempr = (int) (Math.random() * (playingList.size()-pos) + pos);
					Song temporary = playingList.get(tempr);
					playingList.set(tempr, playlist.get(pos));
					playingList.set(pos, temporary);
				}
				}//end else
			}
			
			//view play history
			else if (playResponse.contentEquals("5")) {
				System.out.println("Play history: " +discardList);
			}//end view play history
			
			}//end while loop
	}//end play
	
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
	}//end sort by title
	
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
	}//end sort by artist
	
	public String toString() {
		return playlist.toString();
	}
}
