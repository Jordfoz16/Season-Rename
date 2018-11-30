import java.util.ArrayList;

import javax.swing.JProgressBar;

import MediaFile.MediaFile;
import MediaFile.SubtitleFile;
import Scan.Scan;
import Utils.Utils;

public class Source {
	
	public ArrayList<MediaFile> videoFiles = new ArrayList<MediaFile>();
	public ArrayList<SubtitleFile> subtitleFiles = new ArrayList<SubtitleFile>();
	
	public JProgressBar progressBar;
	
	public int progress = 0;
	
	Utils utils = new Utils();
	Scan scan;
	
	public void parseFolder(String path) {
		scan = new Scan();
		scan.scanDir(path);
		
		for(int i = 0; i < scan.getFileList().size(); i++) {
			MediaFile temp = new MediaFile();
			temp.setFile(scan.getFileList().get(i));
			
			utils.getExtension(temp);
			
			if(utils.checkSubtitleWhiteList(temp)) {
				subtitleFiles.add(new SubtitleFile(temp));
			}else if (utils.checkVideoWhiteList(temp)) {
				videoFiles.add(temp);
			}
		}
	}
	
	public void correctTitle(String title, int season, String language) {
		int counter = 1;
		String counterEpisode;
		
		for(int i = 0; i < videoFiles.size(); i++) {
			counterEpisode = String.format(utils.formatEpisode(videoFiles.size()), (i + 1));
			videoFiles.get(i).setTitle(title, season, counterEpisode);
			counter++;
		}
		
		counter = 1;
		
		for(int i = 0; i < subtitleFiles.size(); i++) {
			counterEpisode = String.format(utils.formatEpisode(subtitleFiles.size()), (i + 1));
			if(language == null) {
				subtitleFiles.get(i).setTitle(title, season, counterEpisode);
			}else {
				subtitleFiles.get(i).setTitle(title, season, counterEpisode, language);
			}
			counter++;
		}
	}
	
	public void correctTitle(String title, int season) {
		correctTitle(title, season, null);
	}
	
	public void rename() {
		for(int i = 0; i < videoFiles.size(); i++) {
			utils.renameFile(videoFiles.get(i), videoFiles.get(i).getTitle());
		}
		for(int i = 0; i < subtitleFiles.size(); i++) {
			utils.renameFile(subtitleFiles.get(i), subtitleFiles.get(i).getTitle());
		}
	}
	
	public void clear() {
		videoFiles.clear();
		subtitleFiles.clear();
	}
}
