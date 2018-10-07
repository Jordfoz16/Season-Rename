import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item {
	
	private File file;
	private String fileName;
	
	public String title, extenstion;
	public boolean remove = false;
	
	public Item(File file) {
		this.file = file;
		fileName = file.getName();
		
		getExtenstion();
	}
	
	public void rename(String newName) {
		File file = new File(this.file.getAbsolutePath());
		File newFile = new File(this.file.getParentFile() + "/" + newName + extenstion);
		
		if(file.renameTo(newFile)) {
			System.out.println("File Renamed to " + newFile.getName());
		}else {
			System.out.println("Failed!!");
		}
	}
	
	private void getExtenstion() {
		String regex = "\\.[0-9a-z]+$";
		final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		final Matcher matcher = pattern.matcher(fileName);
		
		if (matcher.find()) {
			title = fileName.substring(0, matcher.start(0));
			extenstion = matcher.group(0);
		}
	}
	
}
