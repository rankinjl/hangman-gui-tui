import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class ViewGUI2 implements ControllerToView{

	private final int fontSize = 23;
	private JFrame mainFrame; //main hangman frame
	private JPanel righthandside; //right side of main frame
	private JPanel lefthandside; //left side of main frame
	private JFrame secondaryFrame; //frame to pop up once game finishes
	
	private final JLabel wordToGuessMessage = new JLabel("Word to guess:",JLabel.LEFT);
	private final JLabel entercharhere = new JLabel("Guess a Letter:",JLabel.LEFT);

	private JLabel message; //one of "Welcome to Hangman","Incorrect Guess","Correct Guess"
	private ArrayList<JButton> alphabetButtons = new ArrayList<JButton>();
	private JLabel incorrectLetters; //letter incorrectly guessed
	private JLabel wordToGuess; //Label containing wordUserWillGuess
	private HangmanPanel hangman; //the actual hangman picture
	
	private ControllerGUI myController;
	private String wordUserWillGuess; //the actual dashes and correct letters of the word
	private final String alphabet = "qwertyuiopasdfghjklzxcvbnm";

	
	public ViewGUI2(ControllerGUI myController,String wordToGuess){
		this.myController = myController;
		wordUserWillGuess = wordToGuess;
		
		prepareGUI();
	}
	
	//create, set up, display mainFrame
	private void prepareGUI(){
		mainFrame = new JFrame("Welcome to Hangman!");
	    mainFrame.setSize(1000,475);
	    mainFrame.setLayout(new GridLayout(1, 2));
	    mainFrame.setLocationRelativeTo(null);

	    createRightSide();
	    createLeftSide();
	    
	    mainFrame.addWindowListener(new WindowAdapter() {
		       public void windowClosing(WindowEvent windowEvent){
		          System.exit(0);
		       }        
		});
	    
	    mainFrame.add(lefthandside);
	    mainFrame.add(righthandside);
	    
	    //mainFrame.getRootPane().setDefaultButton(defaultbutton);
	    mainFrame.setVisible(true);  
	}
	
	//create left side with hangman and incorrect guesses
	public void createLeftSide(){
		lefthandside = new JPanel();
	    lefthandside.setLayout(new BoxLayout(lefthandside,BoxLayout.Y_AXIS));

		hangman = new HangmanPanel(75, mainFrame.getHeight()-175);
	    incorrectLetters = createLabel("");       
	    
	    lefthandside.add((JPanel)hangman);
	    lefthandside.add(incorrectLetters);
	}
	
	//create right side with correct guesses, alphabet buttons, message, give up button
	public void createRightSide(){
	    righthandside = new JPanel();
	    righthandside.setLayout(new GridLayout(3,1));

	    JPanel wordStuff = createWordStuff();
	    JPanel charspanel = createAlphaButtons();
	    JPanel bottom = createBottomRight();
	    
	    righthandside.add(wordStuff);
	    righthandside.add(charspanel);
	    righthandside.add(bottom);
	}
	
	//create message and  give up button JPanel
	private JPanel createBottomRight()
	{
		JPanel bottom = new JPanel();
	    bottom.setLayout(new BoxLayout(bottom,BoxLayout.Y_AXIS));
	    
	    JLabel emptyline = createLabel("\n");
	    bottom.add(emptyline);
	    
	    message = createLabel("Welcome to Hangman!");
	    message.setAlignmentX(Component.CENTER_ALIGNMENT);
	    bottom.add(message);
	    
	    JButton giveupbutton = createButton("Give Up");
	    giveupbutton.setSize(100, 100);
	    giveupbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    bottom.add(giveupbutton);
	    
	    return bottom;
	}
	
	//create correct guesses area in JPanel
	private JPanel createWordStuff(){
		JPanel wordStuff = new JPanel();
		wordStuff.setLayout(new GridLayout(3,1));
		entercharhere.setFont(new Font("Arial",Font.BOLD,fontSize));
		wordToGuessMessage.setFont(new Font("Arial",Font.BOLD,fontSize));
		wordToGuess = createLabel(wordUserWillGuess);
		
		wordStuff.add(wordToGuessMessage);
		wordStuff.add(wordToGuess);
		wordStuff.add(entercharhere);
		
		return wordStuff;
	}
	
	//create alphabet buttons area
	private JPanel createAlphaButtons(){
		JPanel alphabetrow1 = new JPanel();
		JPanel alphabetrow2 = new JPanel();
		JPanel alphabetrow3 = new JPanel();
		
		//alphabetrow1.setLayout(new GridLayout(1,10));
		//alphabetrow2.setLayout(new BoxLayout(alphabetrow2,BoxLayout.X_AXIS));
		
		int j = 0;
		for(String letter: alphabet.split("")){
			JButton button = createButton(letter);
			alphabetButtons.add(button);
			if(j<10)
				alphabetrow1.add(button);
			else if(j<19)
				alphabetrow2.add(button);
			else
				alphabetrow3.add(button);
			j++;

		}
	    
	    JPanel temp = new JPanel();
	    temp.setLayout(new GridLayout(3,1));
	    //temp.setPreferredSize(new Dimension(400,300));
	    temp.add(alphabetrow1);
	    temp.add(alphabetrow2);
	    temp.add(alphabetrow3);
		return temp;
	}
	
	//create a Jlabel
	private JLabel createLabel(String msg){
		JLabel label = new JLabel(msg,JLabel.CENTER);
	    label.setFont(new Font("Arial",Font.BOLD,fontSize));
	    return label;
	}
	
	//create a Jbutton
	private JButton createButton(String msg){
		JButton thisbutton = new JButton(msg);
	    thisbutton.setActionCommand(msg);
	    thisbutton.setFont(new Font("Arial",Font.BOLD,fontSize-5));
	    thisbutton.setEnabled(true);
	    thisbutton.addActionListener(new ViewButtonClickListener(myController,this)); 
	    return thisbutton;
	}
	
	//disable button guessed
	public void eraseGuess(String guess){
		int index = alphabet.indexOf(guess);
		System.out.println(guess);
		if(index!=-1)
			alphabetButtons.get(index).setEnabled(false);
	}
	
	//reset the entire gui
	public void reset(String word){
		if(secondaryFrame!=null)
			secondaryFrame.dispose();
		hangman.reset();
		wordUserWillGuess = word;
	    mainFrame.dispose();
	    prepareGUI();
	}

	//game is done: terminate JFrames
	public void endInterface() {
		if(secondaryFrame!=null)
			secondaryFrame.dispose();
		mainFrame.dispose();
	}


	public void gotCorrectLetter() {
		message.setText("Correct Guess!!");
	}

	public void gotIncorrectLetter() {
		message.setText("Incorrect Guess...");
		hangman.badGuess();
	}

	//do nothing
	public char getNextGuess() {
		//ONLY FOR GUI1 - do nothing
		return ' ';
	}

	//ask user if they want to play again in new secondaryFrame
	public boolean playAgain(boolean won, String word) {
		secondaryFrame = new JFrame("Do you want to play again?");
	    secondaryFrame.setSize(500,500);
	    secondaryFrame.setLayout(new GridLayout(4,1)); 
	    secondaryFrame.setLocationRelativeTo(null);

	    secondaryFrame.addWindowListener(new WindowAdapter() {
		       public void windowClosing(WindowEvent windowEvent){
		          System.exit(0);
		       }        
		});
	    
	    JLabel numgames = createLabel("You have won "+myController.getGamesWon()+"/"+myController.getGamesPlayed()+" games so far.");
	    JLabel thismessage;
	    
	    if(won){
	    	thismessage = createLabel("Congratulations! You won!");
		}
	    else{
	    	thismessage = createLabel("Sorry, you lost. The word was "+word+".");
	    }
	    secondaryFrame.add(thismessage);
	    secondaryFrame.add(numgames);
	    
	    JButton playagainbutton = createButton("Play Again");
	    secondaryFrame.add(playagainbutton);
	    secondaryFrame.add(createButton("Exit"));
	    secondaryFrame.getRootPane().setDefaultButton(playagainbutton);

	    secondaryFrame.setVisible(true);

	    return true;
	}

	public void printIncorrectGuesses(String incorrectLetters) {
		this.incorrectLetters.setText(incorrectLetters);
	}

	public void printCorrectLetters(String correctLetters) {
		wordToGuess.setText(correctLetters);
	}

	//shouldn't be used, but might
	public void repeatedGuess() {
		message.setText("You already guessed that. Try again.");
	}


	//TUI ONLY
	public void showMan(int numbadguesses) {
		//do nothing
		
	}

}
