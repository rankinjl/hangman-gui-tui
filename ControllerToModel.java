
public interface ControllerToModel {

	//purpose: find if guess previously guessed
	//inputs: guess - char the player guessed
	//assumptions: correctLetters and incorrectLetters instantiated
	//post: true returned if this char previously guessed, false ow
	public boolean previousGuess(char guess);
	
	//purpose: get the total number of bad guesses so far
	//inputs: None
	//assumptions: None
	//post: totalBadGuesses returned
	public int getTotalBadGuesses();
	
	//purpose: find if max number of bad guesses guessed already
	//inputs: None
	//assumptions: None
	//post: true returned if max num of bad guesses reached, false ow
	public boolean maxBadGuessReached();
	
	//purpose: get a random word for the user to guess in the game
	//inputs: None
	//assumptions: None
	//post: random word returned from list of secret_words
	public String getRandomWord();
	
	//purpose: add the correctly guessed letter
	//inputs: word - word user is trying to guess
			//guess - the guess the user made
			//i - the index in the word that the guess was found
	//assumptions: correctLetters already set
	//post: if guess in word, put in correctLetters
	public void foundGuessInWord(String word,char guess,int i);
	
	//purpose: add an incorrect guess and check if all incorrect guesses used
	//inputs: letterGuessed - the char the user guessed
	//assumptions: incorrectLetters instantiated
	//post: true returned if all bad guesses used, false ow
	public boolean addBadGuess(char letterGuessed);
	
	//purpose: check if word guessed yet
	//inputs: None
	//assumptions: correctLetters instantiated
	//post: true if entire word guessed, false otherwise
	public boolean wordGuessed();
	
	//purpose: get the incorrect guesses as a string
	//inputs: None
	//assumptions: incorrectLetters instantiated
	//post: string of incorrectLetters returned
	public String incorrectGuessesToString();
	
	//purpose: get the correct guesses in the word as a string
	//inputs: None
	//assumptions: correctLetters instantiated
	//post: string of correctLetters returned
	public String correctLettersToString();
	
}
