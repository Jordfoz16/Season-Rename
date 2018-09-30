import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Namer {
	
	public File[] scan(String path) {
		File[] paths;
		File dir = new File(path);
		
		paths = dir.listFiles();
		
		return paths;
	}
	
	public String[] getExtenstion(String filename) {
		
		String[] result = new String[2];
		String regex = "\\.[0-9a-z]+$";
		final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		final Matcher matcher = pattern.matcher(filename);
		
		if (matcher.find()) {
			result[0] = filename.substring(0, matcher.start(0));
			result[1] = matcher.group(0);
		}
		
		return result;
	}
	
	public void rename(String path, String newName, String oldName, String extenstion) {
		
		File file = new File(path + "/" + oldName + extenstion);
		File newFile = new File(path + "/" + newName + extenstion);
		
		if(file.renameTo(newFile)) {
			System.out.println("File Renamed to " + newFile.getName());
		}else {
			System.out.println("Failed!!");
		}
	}
}
