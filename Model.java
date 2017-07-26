import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Model implements ControllerToModel{
	private static ArrayList<String> SECRET_WORDS;
	private static final int MAX_BAD_GUESSES = 7;
	private int totalBadGuesses;
	
	// An array keeping track of letters of word that have been guessed.
	// Letters not guessed are represented with '_'
	private char correctLetters[];
		
	// An array keeping track of the incorrectly guessed letters
	private char incorrectLetters[];
		
	private static int gameswon;
	private static int gamesplayed;
	
	public Model(String secretWordFile) throws IOException
	{
		totalBadGuesses = 0;
		gameswon = 0;
		gamesplayed = 0;
		SECRET_WORDS = new ArrayList<String>();
		
		Scanner fileReader = null;
		try{
			fileReader = new Scanner(new File(secretWordFile));
			while(fileReader.hasNextLine()){
				String line = fileReader.nextLine(); //the current line
				//add the current line into the Arraylist SECRET_WORDS
				SECRET_WORDS.add(line);
			}
		}catch(IOException e){
			System.out.println("Encountered an Input/Output Error.");
			throw e;
		}finally{
			if(fileReader!=null)
				fileReader.close();
		}
	}
	
	public int getGamesWon(){
		return gameswon;
	}
	
	public int getGamesPlayed(){
		return gamesplayed;
	}

	//purpose: find if guess previously guessed
	//inputs: guess - char the player guessed
	//assumptions: correctLetters and incorrectLetters instantiated
	//post: true returned if this char previously guessed, false ow
	public boolean previousGuess(char guess){
		for (char letter: correctLetters) {
			if(letter == guess){
				return true;
			}
		}
		for (char letter2: incorrectLetters) {
			if(letter2 == guess){
				return true;
			}
		}
		return false;
	}
	
	//purpose: get the total number of bad guesses so far
	//inputs: None
	//assumptions: None
	//post: totalBadGuesses returned
	public int getTotalBadGuesses(){
		return totalBadGuesses;
	}
	
	//purpose: find if max number of bad guesses guessed already
	//inputs: None
	//assumptions: None
	//post: true returned if max num of bad guesses reached, false ow
	public boolean maxBadGuessReached(){
		if(totalBadGuesses==MAX_BAD_GUESSES)
			return true;
		else
			return false;
	}

	//purpose: get a random word for the user to guess in the game
	//inputs: None
	//assumptions: None
	//post: random word returned from list of secret_words
	//Also, sets/resets correctLetters and incorrectLetters
	public String getRandomWord(){
		Random randgen = new Random();
		String word = SECRET_WORDS.get(randgen.nextInt(SECRET_WORDS.size()));
		setCorrectLetters(word);
		setIncorrectLetters(word);
		totalBadGuesses=0;
		gamesplayed++;
		return word;
	}
	
	//purpose: set/reset correct letters for this word
	//inputs: word - word for user to guess
	//assumptions: word is instantiated
	//post: correctLetters set and filled
	private void setCorrectLetters(String word){
		//fill and/or reset correctLetters[]
		correctLetters = new char[word.length()];
		for (int i = 0; i < correctLetters.length; i++) {
			correctLetters[i] = '_';
		}
	}
	
	//purpose: set/reset incorrect letters for this word
	//inputs: word - word for user to guess
	//assumptions: word is instantiated
	//post: incorrectLetters set and filled
	private void setIncorrectLetters(String word){
		incorrectLetters = new char[MAX_BAD_GUESSES];
		for (int i = 0; i < incorrectLetters.length; i++) {
			incorrectLetters[i] = ' ';
		}
		
	}
	
	//purpose: add the correctly guessed letter
	//inputs: word - word user is trying to guess
			//guess - the guess the user made
			//i - the index in the word that the guess was found
	//assumptions: correctLetters already set
	//post: if guess in word, put in correctLetters
	public void foundGuessInWord(String word,char guess,int i){
		if(0<=i && i< word.length() && word.charAt(i)==guess)
			correctLetters[i] = guess;
	}
	
	//purpose: add an incorrect guess and check if all incorrect guesses used
	//inputs: letterGuessed - the char the user guessed
	//assumptions: incorrectLetters instantiated
	//post: true returned if all bad guesses used, false ow
	public boolean addBadGuess(char letterGuessed){
		if(incorrectLetters[totalBadGuesses]==' '){
			incorrectLetters[totalBadGuesses] = letterGuessed;
			totalBadGuesses++;
		}
		
		if(incorrectLetters[incorrectLetters.length-1] == ' ' && totalBadGuesses<MAX_BAD_GUESSES)
			return false; //not all bad guesses used up
		else
			return true; //true that all bad guesses used up
	}
	
	//purpose: check if word guessed yet
	//inputs: None
	//assumptions: correctLetters instantiated
	//post: true if entire word guessed, false otherwise
	public boolean wordGuessed() {
		boolean found = true;
		for (int i = 0; i < correctLetters.length; i++) {
			if (correctLetters[i] == '_')
				found = false;
		}
		if(found){
			gameswon++;
		}
		return found;
	
	}
	
	//purpose: get the incorrect guesses as a string
	//inputs: None
	//assumptions: incorrectLetters instantiated
	//post: string of incorrectLetters returned
	public String incorrectGuessesToString(){
		String incorrectGuesses = "";
		for (int i = 0; i < incorrectLetters.length; i++) {
			incorrectGuesses += incorrectLetters[i] + " ";
		}
		return incorrectGuesses;
	}
	
	//purpose: get the correct guesses in the word as a string
	//inputs: None
	//assumptions: correctLetters instantiated
	//post: string of correctLetters returned
	public String correctLettersToString() {
		String corrects = "";
	
		for (int i = 0; i < correctLetters.length; i++) {
			corrects += correctLetters[i] + "   ";
		}
		return corrects;
	
	}
	
}
