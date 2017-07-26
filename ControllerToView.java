
public interface ControllerToView {
	
	//purpose: end game and exit 
	//inputs: None
	//assumptions: None
	//post: goodbye message displayed and exit interactive mode
	public void endInterface();
	
	//purpose: notify user of correct guess
	//inputs: None
	//assumptions: None
	//post: correct guess message displayed
	public void gotCorrectLetter();
	
	//purpose: notify user of incorrect guess
	//inputs: None
	//assumptions: None
	//post: Incorrect guess message displayed
	public void gotIncorrectLetter();
	
	//purpose: notify user of win or loss and ask if play again
	//inputs: won - true if user won game, false ow
			// word - word user was trying to guess
	//assumptions: None
	//post: user notified of win/loss (TUI: return true if want to play again, false otherwise)
	public boolean playAgain(boolean won, String word);
	
	//purpose: notify user of all current incorrect guesses for this game
	//inputs: incorrectLetters - string of incorrect letters to be sent to user
	//assumptions: None
	//post: user notified of all current incorrect guesses
	public void printIncorrectGuesses(String incorrectLetters);
	
	//purpose: notify user of all current correct guesses for this game
	//inputs: correctLetters - string of correct letters user has guessed
	//assumptions: None
	//post: user notified of all current correct guesses
	public void printCorrectLetters(String correctLetters);
	
	//purpose: notify user of repeated guess
	//inputs: None
	//assumptions: user has repeated a guess
	//post: user notified of repeated guess
	public void repeatedGuess();

	
	//TUI-specific methods
	
	//purpose: show the hangman so far
	//inputs: the number of bad guesses
	//assumptions: numbadguesses >=0
	//post: hangman showed to user
	public void showMan(int numbadguesses);
	
	
	
	//GUI-specific methods
	
	//purpose: erase the guess on the user interface
	//inputs: the string to be erased (may not be used)
	//assumptions: tobeerasedifapplicable is not null
	//post: guess erased if applicable
	public void eraseGuess(String tobeerasedifapplicable);
	
	//purpose: reset the user interface
	//inputs: word - the next word the user will guess
	//assumptions: word is not null
	//post: user interface reset if applicable
	public void reset(String word);
	
	
	//GUI1,TUI ONLY
	
	//purpose: get user's next guess
	//inputs: None
	//assumptions: None
	//post: prompt user for next letter guess and return
	public char getNextGuess();
	

}
