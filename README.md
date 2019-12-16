RMIT University Vietnam
Course: INTE2512 Object-Oriented Programming
Semester: 2019C
Assessment: Assignment 2
Student Name: Le Quang Hien
Student ID: s3695516

1. INTRODUCTION
This project is to create a flipping card game. 

2. FEATURES
- Image Display:Display 10 pairs of photo of 10 favorite U22 Vietnam Football Team randomly in 4 rows X 5 columns
- Click and turn: When the user clicks at a photo, it turns and shows the photo for N seconds. 
Maximum 2 photos are clicked and shown at a time. 
If two photos are matched, they stay. Otherwise, each photo turns back after its N seconds elapses.   
- Level: he user can set the game difficulty level which restricts the photo showing time. 
There are 3 levels: (1) N = 3 seconds, (2) N = 2 seconds, (3) N = 1 second.
- Time: The objective of this game is to find the 10 matching pairs of photos in the shortest time within the time allowed.
As such, you have to display the elapsed time in minutes : seconds : hundredths of a second,
e.g. 01 : 39 : 58. The time allowed is 2 minutes. 
In addition, a timeline is also displayed to visualize how much time allowed left. 
When the time allowed reaches, the game stops. 
- Sound : Add sound to the game to make it livelier. The user can choose to turn on or turn off sound. 
- Score : If the user finishes a game in x seconds, he/she receives (120 – x) points. 
When the user plays several games, the score is added up.
- Quit game: player can quit the current game and go back to the main menu while playing
- Play Again: when the game has finished, player is able to play the game again with the same difficulty level
- 
3. INSTALLATION
This software can be run straightly on Intellij using JDK 11 and JavaFX 11
modules javafx.controls,javafx.fxml,javafx.media must be added to the VM options

4. KNOWN BUGS
If the player clicks two time on the same card, he or she cannot flip the second card
and has to wait for the current card faces down.

5. ACKNOWLEDGEMENT
For creating the Time ProgressBar
https://stackoverflow.com/questions/34198190/javafx-progressbar-animation-or-transition
