import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePlay extends JPanel implements KeyListener, ActionListener {
	
	//not to start the game itself
	private boolean startGame = false;
	private int score = 0;
	private int hearts = 3;
	
	private int totalBricks = 21;
	
	//moves the game
	private Timer time;
	//speed of the game
	private int speed = 8;

	private int playerXPos = 300;
	
	private int ballPosX = 340;
	private int ballPosY = 435;
	private int ballDirX = -2;
	private int ballDirY = -1;
	
	private MapGenerator map;
	

	
	public GamePlay() {
		map = new MapGenerator(3, 7);
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		time = new Timer(speed, this);
		time.start();
		
	}
	//This creates GameUI
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(0, 0, 720, 480);
		
		//map
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.white);
		g.fillRect(0, 0, 3, 470); //left
		g.fillRect(0, 0, 715, 3); //top
		g.fillRect(720, 0, 3, 470); //right
		
		//players paddle
		g.setColor(Color.white);
		g.fillRect(playerXPos, 460, 100, 10);
		
		//ball
		g.setColor(Color.white);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		//score
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD,25));
		g.drawString("Score: " + score, 550, 40);
		
		//restart game
		if(ballPosY > 480) {

			startGame = false;
			ballPosX = 340;
			//ballPosY = 435;
			playerXPos = 300;
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD,40));
			g.drawString("GAME OVER", 230, 350);
			g.setFont(new Font("serif", Font.PLAIN,20));
			g.drawString("Press ENTER to continue", 250, 390);
			g.drawString("Press R to restart", 290, 420);
			

		}
		if (totalBricks == 0) {
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD,40));
			g.drawString("YOU WON", 250, 350);
			startGame = false;
		}


		//changes everything inside the paint anytime it is called by ActionEvent
		g.dispose();
	}
	
	
	//call paint and repaint all the graphics
	@Override
	public void actionPerformed(ActionEvent e) {
		 time.start();
		
		//if you pressed the left|right key game starts and ball moves
		if(startGame) {
			
			//moves the ball
			ballPosX += ballDirX;
			ballPosY += ballDirY;
			//left border collision
			if(ballPosX < 0) {
				ballDirX = -ballDirX;
			}
			//top border collision
			if(ballPosY < 0) {
				ballDirY = -ballDirY;
			}
			//bottom border collision
			if(ballPosX > 705) {
				ballDirX = -ballDirX;
			}
		
		}
		//intersects with paddle
		Rectangle paddleRact = new Rectangle(playerXPos, 460, 100, 10);
		Rectangle ballRact = new Rectangle(ballPosX,ballPosY,20,20);
		if(paddleRact.intersects(ballRact)) {
			ballDirY = -ballDirY;
			System.out.println("intersection");
		}
		
		
		//intersects with brick
		A: for(int i=0;i < map.map.length;i++) {
			for(int j=0;j < map.map[0].length;j++) {
				if(map.map[i][j] > 0) {
					int brickX = j*map.brickWidth+30;
					int brickY = i*map.brickHeight+50;
					int brickWidth = map.brickWidth-5;
					int brickHeight = map.brickHeight-5;
					
					Rectangle ract= new Rectangle(brickX, brickY,brickWidth,brickHeight);
					Rectangle brickRectangle = ract;
					if(ballRact.intersects(brickRectangle)) {
						map.setBrickValue(0, i, j);
						totalBricks --;
						score +=5;
						System.out.println("Score: "+ score);
						
						if(ballPosX + 19 <= brickRectangle.x || ballPosX + 1 >= brickRectangle.x + brickRectangle.width) {
							ballDirX = - ballDirX;
						}
						else {
							ballDirY = -ballDirY;
						}
						break A;
					}
					
				}
					
			}
		}
		repaint();		
	}
	
	//controls
	@Override
	public void keyPressed(KeyEvent e) {

		//unables paddle to go over border
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			ballPosY = 435;
		
		}
		
		// if you want to restart and create new map write this code
		 if(e.getKeyCode() == KeyEvent.VK_R) {
		 		if(!startGame) {
		 			startGame = true;
		 			ballPosX = 340;
					ballPosY = 435;
					ballDirX = -2;
					ballDirY = -1;
		 			playerXPos = 300;
					score = 0;
		 			totalBricks = 21;
		 			map = new MapGenerator(3,7);
		 			repaint();
		 		}
		  }
		 
		
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			if(playerXPos >= 600) {
				playerXPos = 600;
			}
			else {
				moveRight();
			}
		}
		//unables paddle to go over border
		if(e.getKeyCode() == KeyEvent.VK_A) {
			if(playerXPos <= 10) {
				playerXPos = 10;
			}
			else {
				moveLeft();
			}
		}		
	}

	//moves right
	public void moveRight() {
		startGame = true;
		playerXPos += 10;
			
	}
	//moves left
	public void moveLeft() {
		startGame = true;
		playerXPos -= 10;
			
	}
	
	
	
	
	//Won't be used
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}

}
