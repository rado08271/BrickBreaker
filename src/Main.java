/* BRICK BREAKER
 * created by rafig
 *  
 * game is repeating...must be reprogrammed by setting the 
 * angles of where the ball touches the paddle
 */



import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//Window
		JFrame panel = new JFrame();
		GamePlay gamePlay = new GamePlay();
		panel.setBounds(10, 10, 730, 510);
		panel.setResizable(false);
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setTitle("BRICK BREAKER");
		
		//Adds gameplay to the window panel
		panel.add(gamePlay);
		panel.setVisible(true);
	}
	
}
