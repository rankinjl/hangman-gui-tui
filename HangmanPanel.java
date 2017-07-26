import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

public class HangmanPanel extends JPanel{

	private int x = 0;//where to start drawing top left corner of hangman base
	private int y = 0; //where to start drawing top left corner of hangman base
	private int width = 250; //width of hangman base
	private int height = 65; //height of hangman base
	
	private int badGuesses; //number of incorrect guesses the user has made so far
	
	public HangmanPanel(int xcoord, int ycoord){
		x = xcoord;
		y = ycoord;
		badGuesses = 0;
	}
	
	//reset the hangman panel
	public void reset(){
		badGuesses=0;
	}
	
	//draw the hangman panel
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(x,y,width,height);//base
		drawVertLine(-height*4,x+width/4,y,g);//pole up
		//g.drawLine(x+width/4, y, x+width/4, y-height*4);//pole up
		drawHorizLine(5*width/12,x+width/4,y-height*4,g); //horizontal
		//g.drawLine(x+width/4, y-height*4, x+2*(width/3), y-height*4);//horizontal
		drawVertLine(width/6,x+2*width/3,y-height*4,g);//rope
		//g.drawLine(x+2*(width/3), y-height*4, x+2*(width/3), y-height*3);//rope
		if(badGuesses>0){
			drawbody(g);
		}
	}
	
	//draw a vertical line
	private void drawVertLine(int length, int xcoord, int ycoord, Graphics g){
		g.drawLine(xcoord, ycoord, xcoord, ycoord+length);
	}
	
	//draw a horizontal line
	private void drawHorizLine(int length, int xcoord,int ycoord, Graphics g){
		g.drawLine(xcoord, ycoord, xcoord+length, ycoord);
	}
	
	//draw the body of the hangman
	private void drawbody(Graphics g){
		g.drawArc(x+2*(width/3)-3*(height/8), y-height*4+width/6, 3*height/4, 3*height/4, 90, 360); //head
		if(badGuesses>1){//main body
			drawVertLine(height,x+2*width/3,y-height*4+width/6+3*height/4,g);
			if(badGuesses>2){//draw arm
				drawHorizLine(-width/10,x+2*width/3,y-height*4+width/6+5*height/4,g);
				if(badGuesses>3){//other arm
					drawHorizLine(width/10,x+2*width/3,y-height*4+width/6+5*height/4,g);
					if(badGuesses>4){//draw leg
						g.drawLine(x+2*width/3, y-height*3+width/6+3*height/4, (int)Math.round((x+2*width/3)+height/2*Math.sin(Math.PI/6)), (int)Math.round((y-height*3+width/6+3*height/4)+height/2*Math.cos(Math.PI/6)));
						if(badGuesses>5){//other leg
							g.drawLine(x+2*width/3, y-height*3+width/6+3*height/4, (int)Math.round((x+2*width/3)+height/2*Math.sin(-Math.PI/6)), (int)Math.round((y-height*3+width/6+3*height/4)+height/2*Math.cos(-Math.PI/6)));
						}
					}
				}
			}
		}
	}


	//user has made an incorrect guess: redraw hangman
	public void badGuess() {
		badGuesses++;
		repaint();
	}
	
}
