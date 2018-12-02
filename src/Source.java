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
	
	public void correctTitle(String title, int season, String language, int episode) {
		String counterEpisode;
		
		for(int i = 0; i < videoFiles.size(); i++) {
			counterEpisode = String.format(utils.formatEpisode(videoFiles.size()), (i + episode));
			videoFiles.get(i).setTitle(title, season, counterEpisode);
		}
		
		for(int i = 0; i < subtitleFiles.size(); i++) {
			counterEpisode = String.format(utils.formatEpisode(subtitleFiles.size()), (i + episode));
			if(language == null) {
				subtitleFiles.get(i).setTitle(title, season, counterEpisode);
			}else {
				subtitleFiles.get(i).setTitle(title, season, counterEpisode, language);
			}
		}
	}
	
	public void correctTitle(String title, int season, int episode) {
		correctTitle(title, season, null, episode);
	}
	
	public void rename() {
		for(int i = videoFiles.size() - 1; i >= 0; i--) {
			utils.renameFile(videoFiles.get(i));
		}
		for(int i = subtitleFiles.size() - 1; i >= 0; i--) {
			utils.renameFile(subtitleFiles.get(i));
		}
	}
	
	
	public void clear() {
		videoFiles.clear();
		subtitleFiles.clear();
	}
}
