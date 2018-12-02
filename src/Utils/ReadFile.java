package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadFile {
	
	private ArrayList<String> videoList = new ArrayList<String>();
	private ArrayList<String> subtitleList = new ArrayList<String>();
	
	private File whitelist = new File("whitelist.json");
	
	private String template = 
			"{  \r\n" + 
			"   \"video\":[  \r\n" + 
			"      \".mkv\",\r\n" + 
			"      \".mp4\",\r\n" + 
			"      \".avi\"\r\n" + 
			"   ],\r\n" + 
			"   \"subtitle\":[  \r\n" + 
			"      \".srt\"\r\n" + 
			"   ]\r\n" + 
			"}";
			
	
	public ReadFile() {
		checkForFile();
		readFile();
	}
	
	public void checkForFile() {
		
		if(!whitelist.exists()) {
			System.out.println("Creating Whitelist File");
			try {
				PrintWriter writer = new PrintWriter("whitelist.json", "UTF-8");
				writer.write(template);
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Whitelist File Already Exists ");
		}
	}
	
	public void readFile() {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("whitelist.json"));
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray videoFormat = (JSONArray) jsonObject.get("video");
			Iterator<String> iteratorVideo = videoFormat.iterator();
			
			JSONArray subtitleFormat = (JSONArray) jsonObject.get("subtitle");
			Iterator<String> iteratorSubtitle = subtitleFormat.iterator();
			
			while (iteratorVideo.hasNext()) {
				videoList.add(iteratorVideo.next());
            }
			
			while (iteratorSubtitle.hasNext()) {
				subtitleList.add(iteratorSubtitle.next());
            }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getVideoList(){
		return videoList;
	}
	
	public ArrayList<String> getSubtitleList(){
		return subtitleList;
	}
}
