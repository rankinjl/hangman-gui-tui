import java.io.IOException;

public class ControllerGUI implements ViewToController {
	
	private Model myModel;
	private ControllerToView myView;
	private String word;
	
	public ControllerGUI(String filename) throws IOException //from file
	{
		myModel = new Model(filename);
			
	}	
	
	//start the game
	public void go(ViewMode viewMode){
		word = myModel.getRandomWord();
		
		switch(viewMode)
		{
		case GUI2:
			myView = new ViewGUI2(this,myModel.correctLettersToString());
			break;
		case GUI1:
			myView = new ViewGUI1(this, myModel.correctLettersToString());
			break;
		default:
			return; //error
			
		}
	}
	
	//a guess was given: fill in the empty spaces of the word to guess
	private boolean fillInGuess(String word, char guess) {
		boolean found = false;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guess) {
				found = true;
				myModel.foundGuessInWord(word,guess,i);
			}
		}
		if (!found) {
			myModel.addBadGuess(guess);
		}
	
		return found;
	}
	
	public int getGamesWon(){
		return myModel.getGamesWon();
	}
	
	public int getGamesPlayed(){
		return myModel.getGamesPlayed();
	}

	//a guess was given: check validity and notify user
	public void guessGiven(char guess){
		if (!myModel.maxBadGuessReached() && !myModel.wordGuessed()){
			boolean previous = myModel.previousGuess(guess);
			
			if(previous){
				myView.repeatedGuess();
			}else{
				boolean correct = fillInGuess(word, guess);
				if (correct){
					myView.gotCorrectLetter();
					myView.printCorrectLetters(myModel.correctLettersToString());
				}else {
					myView.gotIncorrectLetter();
					myView.printIncorrectGuesses(myModel.incorrectGuessesToString());
				}
			}
		}
		boolean won = myModel.wordGuessed();

		if (won) {
			myView.playAgain(true,word);//won
		}
		else if(myModel.maxBadGuessReached()){
			myView.playAgain(false, word);//lost
		}
		
	}
	
	public String getWord(){
		return word;
	}
	
	//user wants to play again
	public void playAgain(){
		word = myModel.getRandomWord();
		myView.reset(myModel.correctLettersToString());
	}
	
	

}