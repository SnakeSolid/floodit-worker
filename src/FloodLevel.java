package Application;


public final class FloodLevel {
	final private int SIZE  = 14;
	final private int SIZE2 = SIZE * SIZE;
	
	private int[] colors = null;
	
	
	public FloodLevel() {
		colors = new int [SIZE2];
	}
	
	public FloodLevel(FloodLevel prototype) {
		colors = new int [SIZE2];
		
		System.arraycopy(prototype.colors, 0, colors, 0, SIZE2);
	}
	
	public void setColors(int[] new_colors) {
		System.arraycopy(new_colors, 0, colors, 0, SIZE2);
	}

	public void setColor(int i, int j, int color) {
		assert(i >= 0 && i < SIZE);
		assert(j >= 0 && j < SIZE);
		
		colors[i + SIZE * j] = color;
	}

	public int getColor(int i, int j) {
		assert(i >= 0 && i < SIZE);
		assert(j >= 0 && j < SIZE);
		
		return colors[i + SIZE * j];
	}
	
	public void fill(int color) {
		if (colors[0] != color)
			fillRec(0, 0, colors[0], color);
	}
	
	private void fillRec(int i, int j, int scr_color, int color) {
		if (i < 0 || i >= SIZE) return;
		if (j < 0 || j >= SIZE) return;
		
		if (colors[i + SIZE * j] == scr_color) {
			colors[i + SIZE * j] = color;
			
			fillRec(i, j - 1, scr_color, color);
			fillRec(i - 1, j, scr_color, color);
			fillRec(i + 1, j, scr_color, color);
			fillRec(i, j + 1, scr_color, color);
		}
		
		return;
	}

	public int count() {
		int[] fill = new int[SIZE2];
		
		System.arraycopy(colors, 0, fill, 0, SIZE2);
		
		return countRec(fill, 0, 0, colors[0]);
	}

	private int countRec(int[] fill, int i, int j, int k) {
		if (i < 0 || i >= SIZE) return 0;
		if (j < 0 || j >= SIZE) return 0;
		
		int cnt = 0;
		
		if (fill[i + SIZE * j] == k) {
			fill[i + SIZE * j] = 0;

			cnt = 1;
			cnt += countRec(fill, i, j - 1, k);
			cnt += countRec(fill, i - 1, j, k);
			cnt += countRec(fill, i + 1, j, k);
			cnt += countRec(fill, i, j + 1, k);
		}
		
		return cnt;
	}

	public boolean gameCompleted() {
		int basic_color = colors[0];

		for (int i = 1; i < 14 * 14; i++)
			if (basic_color !=  colors[i])
				return false;
		
		return true;
	}
}
