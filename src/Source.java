
import java.util.ArrayList;
import java.util.Scanner;

public class Source {

	public static void main(String[] arg) {
		
		while(true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter Path: ");
			String path = input.nextLine();
			
			Scan scanFolder = new Scan(path);
			
			ArrayList<Item> items = new ArrayList<Item>();
			items = scanFolder.getItems();
			
			System.out.println("Enter new series name: ");
			String seriesName = input.nextLine();
			
			System.out.println("Enter season number: ");
			int seasonNumber = input.nextInt();
			
			for(int i = 0; i < items.size(); i++) {
				String prefixEpisode = "E" + String.format(scanFolder.getPrefix(), (i + 1));
				String newName = seriesName + " " + "S" + seasonNumber + prefixEpisode;
				
				items.get(i).rename(newName);
			}
		}
	}
}
