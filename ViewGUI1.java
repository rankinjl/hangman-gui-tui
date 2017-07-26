import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewGUI1 implements ControllerToView{

	private final int fontSize = 23;
	private JFrame mainFrame; //main hangman frame
	private JPanel righthandside; //right side of main frame
	private JPanel lefthandside; //left side of main frame
	private JFrame secondaryFrame; //frame to pop up once game finishes

	private final JLabel wordToGuessMessage = new JLabel("Word to guess:",JLabel.CENTER);
	private final JLabel entercharhere = new JLabel("Enter character guess here:");

	private JLabel message; //one of "Welcome to Hangman","Incorrect Guess","Correct Guess"
	private JTextField enterchars; //text field to enter characters into
	private JLabel incorrectLetters;//letter incorrectly guessed
	private JLabel wordToGuess; //JLabel to hold wordUserWillGuess
	private HangmanPanel hangman; //actual hangman panel
	
	private ControllerGUI myController;
	private String wordUserWillGuess; //the actual dashes and correct letters of the word
	
	public ViewGUI1(ControllerGUI myController,String wordToGuess){
		this.myController = myController;
		wordUserWillGuess = wordToGuess;
		prepareGUI();
	}
	
	//create, set up, display mainFrame
	private void prepareGUI(){
		mainFrame = new JFrame("Welcome to Hangman!");
	    mainFrame.setSize(900,500);
	    mainFrame.setLayout(new GridLayout(1, 2));
	    mainFrame.setLocationRelativeTo(null);

	    JButton defaultbutton = createRightSide();
	    createLeftSide();
	    
	    mainFrame.addWindowListener(new WindowAdapter() {
		       public void windowClosing(WindowEvent windowEvent){
		          System.exit(0);
		       }        
		});
	    
	    mainFrame.add(lefthandside);
	    mainFrame.add(righthandside);
	    
	    mainFrame.getRootPane().setDefaultButton(defaultbutton);
	    mainFrame.setVisible(true);  
	}
	
	//create left side with hangman and incorrect guesses
	public void createLeftSide(){
		lefthandside = new JPanel();
	    lefthandside.setLayout(new BoxLayout(lefthandside,BoxLayout.Y_AXIS));

		hangman = new HangmanPanel(75, mainFrame.getHeight()-200);
	    incorrectLetters = createLabel("");       
	    
	    lefthandside.add((JPanel)hangman);
	    lefthandside.add(incorrectLetters);
	}
	
	//create right side with correct guesses, entry box, message, give up button
	public JButton createRightSide(){
	    righthandside = new JPanel();
	    righthandside.setLayout(new BoxLayout(righthandside,BoxLayout.Y_AXIS));

	    entercharhere.setFont(new Font("Arial",Font.BOLD,fontSize));
	    wordToGuessMessage.setFont(new Font("Arial",Font.BOLD,fontSize));
	    
	    message = createLabel("");
	    wordToGuess = createLabel(wordUserWillGuess);	    
	    enterchars = new JTextField(1); //1 column for 1 character guess
	    
	    enterchars.setFont(new Font("Arial",Font.BOLD,fontSize-3));
	    enterchars.setMaximumSize(new Dimension(Integer.MAX_VALUE,enterchars.getPreferredSize().height));
	    
	    JButton enterbutton = createButton("Enter");
	    
	    righthandside.add(wordToGuessMessage);
	    righthandside.add(wordToGuess);
	    righthandside.add(entercharhere);
	    righthandside.add(enterchars);       
	    righthandside.add(enterbutton);
	    righthandside.add(createButton("Give Up"));
	    righthandside.add(message);

	    return enterbutton;
	}
	
	private JLabel createLabel(String msg){
		JLabel label = new JLabel(msg,JLabel.CENTER);
	    label.setFont(new Font("Arial",Font.BOLD,fontSize));
	    return label;
	}
	
	private JButton createButton(String msg){
		JButton thisbutton = new JButton(msg);
	    thisbutton.setActionCommand(msg);
	    thisbutton.setFont(new Font("Arial",Font.BOLD,fontSize-5));
	    thisbutton.addActionListener(new ViewButtonClickListener(myController,this)); 
	    return thisbutton;
	}
	
	//guess was made: erase text in the textbox
	public void eraseGuess(String notimportant){
		enterchars.setText("");
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

	//try to get the next guess from the textfield (WILL ACCEPT MORE THAN ONE AND/OR NONALPHABET CHARACTERS)
	public char getNextGuess() {
		if(enterchars!=null){
			try{
				return enterchars.getText().charAt(0);
			}catch(Exception e){
				message.setText("Please enter a letter.");
			}
		}
		return ' ';
	}

	//ask user if they want to play again
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

	public void repeatedGuess() {
		message.setText("You already guessed that. Try again.");
	}


	//TUI ONLY
	public void showMan(int numbadguesses) {
		//do nothing
		
	}

}
