//methods implemented in the ControllerGUI for ViewGUI to access
public interface ViewToController {
	
	//a guess was given: check validity and notify user
	public void guessGiven(char guess);
	
	//returns the word to guess
	public String getWord();
	
	//user wants to play again: reset the game
	public void playAgain();
}
