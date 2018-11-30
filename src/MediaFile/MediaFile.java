package MediaFile;

import java.io.File;

public class MediaFile {
	
	private File file;
	private String filename;
	protected String title;
	private String extenstion;
	
	public MediaFile() {
		
	}
	
	public void setFile(File file) {
		this.file = file;
		filename = file.getName();
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setTitle(String title, int season, String episode) {
		this.title = title + " S" + season + "E" + episode;
	}
	
	public void setExtenstion(String extenstion) {
		this.extenstion = extenstion;
	}
	
	public File getFile() { return file; }
	public String getFilename() { return filename; }
	public String getTitle() { return title; }
	public String getExtenstion() { return extenstion; }
	public String getPath() { return file.getAbsolutePath(); }
	public String getParentPath() { return file.getParent(); }
}
