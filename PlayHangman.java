import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayHangman {

	public static void main(String[] args) {
		//get words for hangman
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a file of secret words: ");
		String filename = input.nextLine();
		
		//get user interface preference
		System.out.println("Which user interface?");
		System.out.println("0 - TUI");
		System.out.println("1 - GUI1 with typing");
		System.out.println("2 - GUI2 with buttons");
		
		ViewMode vmode = ViewMode.TUI; //default TUI
		try
		{
			int v = input.nextInt();
			if(v==ViewMode.GUI2.getValue())
			{
				vmode = ViewMode.GUI2;
				ControllerGUI myController = new ControllerGUI(filename);
				myController.go(vmode);
			}
			else if(v==ViewMode.GUI1.getValue())
			{
				vmode = ViewMode.GUI1;
				ControllerGUI myController = new ControllerGUI(filename);
				myController.go(vmode);
			}
			else
			{
				ControllerTUI myController = new ControllerTUI(filename);
				myController.go();
			}
			
		}
		catch(InputMismatchException ime)
		{ 
			System.out.println("Invalid Input.");
		}
		catch(IOException ioe)
		{
			System.out.println("File does not exist.");
		}
		catch(Exception e)
		{
			System.out.println("Unknown Error");
		}
		
		input.close();
	}
}
