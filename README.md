# hangman-gui-tui
A Java implementation of the classic game Hangman using Model-View-Controller. Three user interfaces are implemented (one TUI and two GUI - Java Swing).

Hangman Implementation Rules and Play:
- The set up for the game asks the file name for the secret words that will be used in the game (make sure this file is in this directory). Then it asks which user interface you would like to use to play the game.
- The word to guess is displayed as a series of underscores ("\_") to show the number of letters in the word.
- Enter a letter you believe is in the word.
- If the letter you guessed was incorrect, a corresponding message is displayed and the letter is added to the designated "Incorrect Guesses" location. In addition, the hangman is drawn to show the number of incorrect guesses so far.
- If the letter you guessed is contained in the word, the corresponding underscores in the displayed word are replaced by that letter and a message is displayed.
- If the letter you guessed was already entered, a corresponding message should be displayed.
- The goal of the game is to correctly guess the word.
- You have a total of 7 incorrect guesses maximum.
- Guessing letters repeats until the game ends when you use all your incorrect guesses or you are able to correctly guess the word.
    - If all incorrect guesses are used and the word is not correctly guessed, you lose and the actual word is displayed.
    - If you are able to correctly fill in the word, you win. 
- In either case, you may play another game.

ControllerGUI.java (Controller)
- the main *Controller* for the ViewGUI1 and ViewGUI2 implementations
- **subclass** of ViewToController interface using ```implements```
- contains the "Business Logic"/actual implementation of the game
- communicates with the Model and ViewGUI components

ControllerTUI.java (Controller)
- the main *Controller* for the ViewTUI implementation
- contains the "Business Logic"/actual implementation of the game
- communicates with the Model and ViewTUI components

ControllerToModel.java
- **interface** for the Controller to communicate with the Model

ControllerToView.java
- **interface** for the Controller to communicate with the various Views

HangmanPanel.java (ViewGUI)
- **subclass** of JPanel using extends
- contains the View logic for constructing the hangman display

Model.java (Model)
- the main *Model* object
- **subclass** of ControllerToModel using implements
- allows the Controller component to store and access data
- contains and mediates access to the correct and incorrect letters, the totalBadGuesses, the number of games won, and the number of games played

PlayHangman.java (Controller)
- starts a Hangman Game by making and running a Controller object in the main method
- asks the user for a filename for the secret words
- asks the user for the type of User Interface they would like to use

ViewButtonClickListener.java (ViewGUI)
- **subclass** of ActionListener using implements
- contains the ViewGUI logic to respond to button clicks in the GUI

ViewGUI1.java (ViewGUI)
- one of the main *ViewGUI* objects
- **subclass** of ControllerToView using implements
- allows the Controller component to display data to user and get input from user

ViewGUI2.java (ViewGUI)
- one of the main *ViewGUI* objects
- **subclass** of ControllerToView using implements
- allows the Controller component to display data to user and get input from user

ViewMode.java (View)
- an enumeration to help distinguish the different type of View objects (ViewGUI1, ViewGUI2, ViewTUI)
- used by the Controller component

ViewTUI.java (ViewTUI)
- one of the main *ViewTUI* objects
- **subclass** of ControllerToView using implements
- allows the Controller component to display data to user and get input from user

ViewToController.java
- **interface** for the ViewGUI components to communicate with the Controller

secretwords.txt
- text file holding different words to be used in the hangman game
- used by the Model 
- one word per line
