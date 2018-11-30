package Scan;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Scan {
	
	private ArrayList<File> files = new ArrayList<File>();
	
	private ArrayList<String> folderPaths = new ArrayList<String>();
	
	public void scanDir(String path) {
		files.addAll(Arrays.asList(new File(path).listFiles()));
		for(int i = 0; i < files.size(); i++) {
			if(files.get(i).isDirectory()) {
				folderPaths.add(files.get(i).getAbsolutePath());
				files.remove(i);
				i--;
			}
		}
	}
	
	public ArrayList<File> getFileList(){
		return files;
	}
	
	public ArrayList<String> getFolders(){
		return folderPaths;
	}
	
	public void clear() {
		files.clear();
		folderPaths.clear();
	}
}
