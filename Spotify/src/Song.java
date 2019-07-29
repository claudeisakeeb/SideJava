
public class Song {
	private String title, artist, file;
	private boolean queued;
	
	public Song (String t, String a) {
		title = t;
		artist = a;
		file = t.replace(" ", "_") + ".mp3";
		queued = false;
	}
	
	public void setTitle(String t) {
		title = t;
		file = t.replace(" ", "_") +".mp3";
	}
	
	public void setArtist(String a) {
		artist = a;
	}
	
	public void setQueueOn() {
		queued = true;
	}
	
	public void setQueueOff() {
		queued = false;
	}
	
	public boolean getQueue() {
		return queued;
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
