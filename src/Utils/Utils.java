package Utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import MediaFile.MediaFile;

public class Utils {
	
	private ReadFile readFile = new ReadFile();
	
	public void getExtension(MediaFile mediafile) {
		
		//Regular expression to get the extension of a file
		String regex = "\\.[0-9a-z]+$";
		String filename = mediafile.getFilename();
		
		final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		final Matcher matcher = pattern.matcher(filename);
		
		if (matcher.find()) {
			//Gets the string before the extension
			String title = filename.substring(0, matcher.start(0));
			//Gets the string of the extension
			String extenstion = matcher.group(0);
			
			mediafile.setTitle(title);
			mediafile.setExtenstion(extenstion);
		}
	}
	
	public void renameFile(MediaFile mediafile) {
		File file = new File(mediafile.getPath());
		File newFile = new File(mediafile.getParentPath() + "/" + mediafile.getTitle() + mediafile.getExtenstion());
		
		if(file.renameTo(newFile)) {
		}else {
			System.out.println("Failed!!");
		}
	}
	
	public String formatEpisode(int length) {
		String format = "";
		if(length < 10) {
			format = "%01d";
		}else if(length < 100) {
			format = "%02d";
		}else if(length < 1000) {
			format = "%03d";
		}
		return format;
	}
	
	public boolean checkSubtitleWhiteList(MediaFile file) {
		for(int i = 0; i < readFile.getSubtitleList().size(); i++) {
			if(file.getExtenstion().equals(readFile.getSubtitleList().get(i))) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkVideoWhiteList(MediaFile file) {
		for(int i = 0; i < readFile.getVideoList().size(); i++) {
			if(file.getExtenstion().equals(readFile.getVideoList().get(i))) {
				return true;
			}
		}
		
		return false;
	}
}
