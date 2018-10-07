import java.io.File;
import java.util.ArrayList;

public class Scan {
	
	private String[] blackList = {".txt", ".png", ".srt"};
	
	private File[] files;
	private ArrayList<Item> itemList = new ArrayList<Item>();
	
	public Scan(String path) {
		File dir = new File(path);
		files = dir.listFiles();
		convertFile();
	}
	
	public ArrayList<Item> getItems(){
		return itemList;
	}
	
	public String getPrefix() {
		String format = "";
		if(files.length < 10) {
			format = "%01d";
		}else if(files.length < 100) {
			format = "%02d";
		}else if(files.length < 1000) {
			format = "%03d";
		}
		return format;
	}
	
	private void convertFile() {
		for(int i = 0; i < files.length; i++) {
			itemList.add(new Item(files[i]));
			
			for(int j = 0; j < blackList.length; j++) {
				if(itemList.get(i).extenstion.equals(blackList[j])) {
					itemList.get(i).remove = true;
				}
			}
		}
		
		for(int i = itemList.size() - 1; i >= 0 ; i--) {
			if(itemList.get(i).remove) {
				itemList.remove(i);
			}
		}
	}
}
