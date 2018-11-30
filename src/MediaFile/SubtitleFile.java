package MediaFile;

public class SubtitleFile extends MediaFile{
	
	private String language;
	
	public SubtitleFile() {
		
	}
	
	public SubtitleFile(MediaFile file) {
		setFile(file.getFile());
		setExtenstion(file.getExtenstion());
		setTitle(file.getTitle());
	}
	
	public void setTitle(String title, int season, String episode, String language) {
		this.title = title + " S" + season + "E" + episode + " " + language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getLanguage() { return language; }
}
