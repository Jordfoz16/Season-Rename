# Season-Renamer

### Overview:

Season renamer will rename a season of a TV show with the correct title needed for media servers like Plex. Example files named "TheWalkingDeadS1E1.1080p[Bluray].mkv" would be renamed to "The Walking Dead S1E1.mkv" making it easier for plex to find a match and download the correct metadata about the episode.

### How to use:

#### Using the application

Make sure you have the java runtime enviroment installed.
Run the "Season Rename.jar", this will create a whitelist.json file
Enter the path to the folder where your season is located
Enter the series name
Enter the season number and episode starting number
Enter subtitle language if needed
Scan the folder to check it can open the folder
Click the rename button to rename the folder

#### Editing the whitelist.json

Inside the whitelist.json are the file extension that can be renamed. So if a video or subtitle format is not there then it can be added by using the json format. If you have made a change to the whitelist.txt the program will need restarting. To get the defualt whitelist.txt delete the current file and relaunch the application.
