import java.util.ArrayList;
import java.util.Scanner;

public class Runner{
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner (System.in);
		
		ArrayList<String> songTitles = new ArrayList<String>();
		
		songTitles.add("One Man");
		songTitles.add("FANCY");
		songTitles.add("SunnySideUp");
		
		Playlist test = new Playlist(songTitles);
		
		String response = " ";
		
		while (!response.contentEquals("Q")) {
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
				String song = keyboard.next();
				test.addToPlaylist(song);
			}
			//keyboard response 3
			else if (response.equals("3")) {
				System.out.println("Enter the name of the song you would like to remove: ");
				String song = keyboard.next();
				test.removeFromPlaylist(song);
			}
			//keyboard response 4
			else if (response.equals("4")) {
				System.out.println("Enter the name of the song whose duplicates you would like to remove: ");
				String song = keyboard.next();
				test.removeDuplicates(song);
			}
			else if (response.equals("5")) {
				System.out.println(test);
			}
		}
	}
}