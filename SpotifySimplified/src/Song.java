
public class Song {
	private String title, artist, file;
	
	public Song (String t, String a) {
		title = t;
		artist = a;
		file = t.replace(" ", "_") + ".mp3";
	}
	
	public void setTitle(String t) {
		title = t;
		file = t.replace(" ", "_") +".mp3";
	}
	
	public void setArtist(String a) {
		artist = a;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getFile() {
		return file;
	}
	
	public String toString() {
		return artist + " - " +title;
	}
	
}
