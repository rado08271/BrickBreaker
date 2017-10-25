import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	
	//constructor
	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for(int i=0;i < map.length;i++) {
			for(int j=0;j < map[0].length;j++) {
				map[i][j] = 1;	
			}
		}
		brickWidth = 670/col;
		brickHeight = 200/row;
	}
	public void draw(Graphics2D g) {
		for(int i=0;i < map.length;i++) {
			for(int j=0;j < map[0].length;j++) {
				if(map[i][j] > 0) {
					g.setColor(Color.white);
					g.fillRect(j*brickWidth+30, i*brickHeight+50, brickWidth-5, brickHeight-5);
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
		
	}
}
