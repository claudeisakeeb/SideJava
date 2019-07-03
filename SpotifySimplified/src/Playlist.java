import java.util.ArrayList;
import java.util.Scanner;

public class Playlist {
	
	private ArrayList<String> titleList, fileList;
	Scanner keyboard = new Scanner(System.in);
	
	public Playlist(ArrayList<String> titles) {
		titleList = new ArrayList<String>();
		fileList = new ArrayList<String>();
		for (String title : titles) {
			titleList.add(title);
		}
		for (String title : titleList) {
			fileList.add(title.replace(" ", "_") + ".mp3");
		}
	}
	
	public void addToPlaylist(String songName) {
		boolean present = false;
		for (int i = 0; i < titleList.size(); i++) {
			if (titleList.get(i).equals(songName)) {
				present = true;
				System.out.println(songName +" is already in your playlist, add anyways? Type (y/n)");
				String addDupe = keyboard.next();
				if (addDupe.toUpperCase().contentEquals("Y")) {
					titleList.add(songName);
					fileList.add(songName.replace(" ", "_") + ".mp3");
					System.out.println(songName +" has been added to your playlist!");
				}
				else {
					System.out.println("No songs were added to your playlist");

				}
				break;
			}
		}
		if (present == false) {
			titleList.add(songName);
			fileList.add(songName.replace(" ", "_") + ".mp3");
			System.out.println(songName +" has been added to your playlist!");
		}
	}
	
	public void removeFromPlaylist(String songName) {
		boolean present = false;
		
		for (int i = 0; i < titleList.size(); i++) {
			if (titleList.get(i).equals(songName)){
				present = true;
				titleList.remove(i);
				fileList.remove(i);
				System.out.println("Song removal successful," +songName +" has been removed from your playlist!");
			}
		}
		if (present == false) {
			System.out.println("Song removal failed successfully - song not found");
		}
	}
	
	public void removeDuplicates(String songName) {
		boolean dupes = false;
		int counter = 0;
		int startpos = -1;
		
		for (int i = 0; i < titleList.size(); i++) {
			if (titleList.get(i).equals(songName)) {
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
			System.out.println("No duplicates were found! Consider using the removeFromPlaylist function instead!");
		}
		else {
			for (int i = startpos + 1; i < titleList.size(); i++) {
				if (titleList.get(i).equals(songName)) {
					titleList.remove(i);
					fileList.remove(i);
				}
			}
			System.out.println("Duplicates of " +songName + " were removed successfully");
		}
	}
	
	public void play() {
		boolean shuffle;
		
		System.out.println("Shuffle? Type (y/n): ");
		String shuffleResponse = keyboard.next();
		if (shuffleResponse.toUpperCase().equals("Y")) {
			shuffle = true;
		}
		else {
			shuffle = false;
		}
		
		if (shuffle == false) {
			for (int i = 0; i < fileList.size(); i++) {
				System.out.println("Now playing: " + titleList.get(i));
			}
		}
		else {
			
		}
	}
	
	
	public String toString() {
		return titleList + "\n" + fileList;
	}
}
