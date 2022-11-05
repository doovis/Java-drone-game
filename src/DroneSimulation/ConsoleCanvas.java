package DroneSimulation;


public class ConsoleCanvas {	// CLASS FOR PRINTING INFO
	int width, height;
	char[][] arr;
	
	ConsoleCanvas(int w, int h) {
		this.width = w + 2;		// extra size for 2 side borders
		this.height = h + 2;	// extra size for upper and lower borders
		this.arr = new char[this.height][this.width];
		
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (j == 0 || j >= this.width - 1) {
					arr[i][j] = '#';
				} else if (i == 0 || i == this.height - 1) {	
					arr[i][j] = '#';
				} else {
					arr[i][j] = ' ';
				}
			}
		}
	}
	
	public void showIt(int x, int y, char ch) {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (j == x && i == y) {					
					arr[i][j] = ch;
				}
			}
		}
	}
	
	public String toString() {
		String str = "";
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				str += arr[i][j];
			}
			str += "\n";
		}
		return str;
	}
	
	public static void main(String[] args) {
		ConsoleCanvas c = new ConsoleCanvas(10, 5);
		c.showIt(4, 3, 'D');
		System.out.println(c.toString());
	}
	
}
