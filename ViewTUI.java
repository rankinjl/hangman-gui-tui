import java.util.Scanner;

public class ViewTUI implements ControllerToView{

	private Scanner keyboard;
	
	
	public ViewTUI(){
		keyboard = new Scanner(System.in);
	}
	
	//purpose: welcome user to game
	//inputs: None
	//assumptions: None
	//post: welcome message displayed
	public void welcome(){
		System.out.println("Welcome to Hangman!\n");
	}
	
	//purpose: end game and exit 
	//inputs: None
	//assumptions: None
	//post: goodbye message displayed and exit interactive mode
	public void endInterface(){
		System.out.println("GoodBye");
		keyboard.close();
	}
	
	//purpose: notify user of correct guess
	//inputs: None
	//assumptions: None
	//post: correct guess message displayed
	public void gotCorrectLetter(){
		System.out.println("Good guess");
	}
	
	//purpose: notify user of incorrect guess
	//inputs: None
	//assumptions: None
	//post: Incorrect guess message displayed
	public void gotIncorrectLetter(){
		System.out.println("Sorry incorrect guess");
		
	}

	//purpose: get user's next guess
	//inputs: None
	//assumptions: None
	//post: prompt user for next letter guess and return
	public char getNextGuess(){
		System.out.println("Enter a letter as your guess:");
		String input = keyboard.next().toLowerCase();
		return input.charAt(0);
	}
	
	//purpose: notify user of win or loss and ask if play again
	//inputs: won - true if user won game, false ow
			// word - word user was trying to guess
	//assumptions: None
	//post: user notified of win/loss
	public boolean playAgain(boolean won, String word){
		if(won)
			System.out.println("You Win!");
		else{
			System.out.println("Sorry too many incorrect guess, you lose");
			System.out.println("Correct word was "+ word);
		}
		
		while(true){
			System.out.println("Would you like to play again? Y or N");
			char answer =  keyboard.next().charAt(0);
			if(answer == 'Y' || answer =='y')
				return true;
			else if(answer == 'N' || answer == 'n')
				return false;
		}
		
	}
	
	//purpose: notify user of all current incorrect guesses for this game
	//inputs: incorrectLetters - string of incorrect letters to be sent to user
	//assumptions: None
	//post: user notified o all current incorrect guesses
	public void printIncorrectGuesses(String incorrectGuesses) {
		System.out.printf("Incorrect Guesses: %s\n",incorrectGuesses);
	}
	
	//purpose: notify user of all current correct guesses for this game
	//inputs: correctLetters - string of correct letters user has guessed
	//assumptions: None
	//post: user notified of all current correct guesses
	public void printCorrectLetters(String correctLetters){
		//System.out.println("*****************************");
		System.out.println("Your word is ...");
		System.out.println(correctLetters);
	}
	
	//purpose: notify user of repeated guess
	//inputs: None
	//assumptions: user has repeated a guess
	//post: user notified of repeated guess
	public void repeatedGuess(){
		System.out.println("You already guessed that!");
	}
	
	//purpose: show the hangman
	//inputs: badGuessesCount - number of incorrect guesses this game
	//assumptions: None
	//post: hangman displayed
	public void showMan(int badGuessesCount) {
		int poleLines = 6; // number of lines for hanging pole
		System.out.println("  ____");
		System.out.println("  |  |");
	
		if (badGuessesCount == 7) {
			System.out.println("  |  |");
			System.out.println("  |  |");
		}
	
		if (badGuessesCount > 0) {
			System.out.println("  |  O");
			poleLines = 5;
		}
	
		if (badGuessesCount > 1) {
			poleLines = 4;
			if (badGuessesCount == 2) {
				System.out.println("  |  |");
			} else if (badGuessesCount == 3) {
				System.out.println("  | \\|");
			} else if (badGuessesCount >= 4) {
				System.out.println("  | \\|/");
			}
		}
	
		if (badGuessesCount > 4) {
			poleLines = 3;
			System.out.println("  |  |");
		}
	
		if (badGuessesCount == 6) {
			poleLines = 2;
			System.out.println("  | /");
		} else if (badGuessesCount >= 7) {
			poleLines = 0;
			System.out.println("  | / \\");
		}
	
		for (int k = 0; k < poleLines; k++) {
			System.out.println("  |");
		}
		System.out.println("__|__");
		System.out.println("*****************************");
			
	}

	//GUI ONLY
	public void eraseGuess(String tobeerasedifapplicable) {
		// do nothing
		
	}

	//GUI ONLY
	public void reset(String word) {
		// do nothing
		
	}
	
}
