import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ViewButtonClickListener implements ActionListener{

	private ControllerGUI myController;
	private ControllerToView myView;
	
	public ViewButtonClickListener(ControllerGUI myController,ControllerToView myView){
		this.myController = myController;
		this.myView = myView;
	}
	
	//listen to buttons pushed in the ViewGUI
	public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();  
        
        if( command.equals( "Enter" )){
        	char guess = myView.getNextGuess();
        	myView.eraseGuess(String.valueOf(guess));
        	if(guess!=' '){
        		myController.guessGiven(guess);
        	}
        }else if(command.equals("Give Up")){
        	myView.playAgain(false, myController.getWord());
        }else if(command.equals("Exit")){
        	myView.endInterface();
        }else if(command.equals("Play Again")){ 
        	myController.playAgain();
        }else{//alphabet button for ViewGUI2
        	//myView.eraseGuess(command); THIS DOES NOT DISABLE THE BUTTON FOR SOME REASON
        				//PLEASE LET ME KNOW IF YOU FIGURE IT OUT
        	JButton buttonPushed = (JButton)e.getSource();
            buttonPushed.setEnabled( false );
        	myController.guessGiven(command.charAt(0));
        }
	}

}
