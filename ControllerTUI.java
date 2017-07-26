import java.io.IOException;

public class ControllerTUI {

	private Model myModel;
	private ViewTUI myView;
	
	public ControllerTUI(String filename) throws IOException
	{
		myModel = new Model(filename);
		myView = new ViewTUI();
	}
	
	//start hangman game
	public void go(){
		myView.welcome();
		
		boolean play = false;
		do {
			String word = myModel.getRandomWord();
			//gets Random word and sets basic correctLetters and incorrectGuesses
				
			char guess;
			boolean previous;
			
			while (!myModel.maxBadGuessReached() && !myModel.wordGuessed()) {
				myView.printCorrectLetters(myModel.correctLettersToString());
				myView.printIncorrectGuesses(myModel.incorrectGuessesToString());
				guess = myView.getNextGuess();
				previous = myModel.previousGuess(guess);
				
				if(previous){
					myView.repeatedGuess();
				}else{
					boolean correct = fillInGuess(word, guess);
					if (correct){
						myView.gotCorrectLetter();
					}else {
						myView.gotIncorrectLetter();					
						myView.showMan(myModel.getTotalBadGuesses());
					}
				}
				
			}
			boolean won = myModel.wordGuessed();
			if (won) {
				myView.printCorrectLetters(myModel.correctLettersToString());
			}

			play = myView.playAgain(won,word);
			
		} while (play);
		myView.endInterface();
	}
	
	
	//if guess in word, return true. else false
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
	

}