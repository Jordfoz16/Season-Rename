import java.io.File;
import java.util.Scanner;

public class Source {

	public static void main(String[] arg) {
		
		String path, newName;
		File[] filenames;
		
		Scanner input = new Scanner(System.in);
		Namer namer = new Namer();
		
		System.out.println("Enter Folder Dir:");
		
		path = input.nextLine();
		
		filenames = namer.scan(path);
		
		System.out.println("Enter new file name");
		
		newName = input.nextLine();
		
		int counter = 0;
		String format = "";
		if(filenames.length < 10) {
			format = "%01d";
		}else if(filenames.length < 100) {
			format = "%02d";
		}else if(filenames.length < 1000) {
			format = "%03d";
		}
		
		for(int i = 0; i < filenames.length; i++) {
			String oldName = namer.getExtenstion(filenames[i].getName())[0];
			String extenstion = namer.getExtenstion(filenames[i].getName())[1];
			
			if(extenstion.contains("txt")) continue;
			
			counter++;
			String formatted = String.format(format, counter);
			namer.rename(path, newName + formatted, oldName, extenstion);
		}
	}
}
