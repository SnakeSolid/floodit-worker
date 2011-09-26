package Application;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;


public final class TestWindowListenerFactory {
	final private int FIELD_X_OFFSET = 431;
	final private int FIELD_Y_OFFSET = 329;
	final private int BUTTON_X_OFFSET = 297;
	final private int BUTTON_Y_OFFSET = 347;
	
	public ActionListener getFlooditClickListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Robot robot = null;
				
				try {
					robot = new Robot();
				} catch (AWTException ex) {
					System.err.println(ex.getMessage());
					return;
				}
 
				
				FloodLevel colors = new FloodLevel(); 

				for (int move = 0; move < 25; move++) {
					for (int j = 0; j < 14; j++)
						for (int i = 0; i < 14; i++) {
							int color = robot.getPixelColor(FIELD_X_OFFSET + 24 * i, FIELD_Y_OFFSET + 24 * j).getRGB() & 0x00ffffff;
							
							switch (color) {
							case 0x00ed70a1: // розовый 
								colors.setColor(i, j, 1);
								break;

							case 0x00605ca8: // фиолетовый 
								colors.setColor(i, j, 2);
								break;

							case 0x00f3f61d: // желтый 
								colors.setColor(i, j, 3);
								break;

							case 0x00dc4a20: // красный 
								colors.setColor(i, j, 4);
								break;

							case 0x0046b1e2: // синий 
								colors.setColor(i, j, 5);
								break;

							case 0x007e9d1e: // зеленый 
								colors.setColor(i, j, 6);
								break;
								
							default:
								return;
							}
						}
					
					
					if (colors.gameCompleted())
						return;

					
					int[] fill_rate = new int[1296]; // массив с количеством заливаемых определенным цветом пикселей
					
					
					
					for (int i = 0; i < 1296; i++) {
						FloodLevel t = new FloodLevel(colors);
						
						t.fill(i /   1 % 6 + 1);
						t.fill(i /   6 % 6 + 1);
						t.fill(i /  36 % 6 + 1);
						t.fill(i / 216 % 6 + 1);
						fill_rate[i] = t.count();
					}
						
					
					int max_color;
					int max_count;
					
					max_color = 0;
					max_count = 0;
					
					for (int i = 0; i < 1296; i++)
						if (fill_rate[i] > max_count) {
							max_color = i;
							max_count = fill_rate[i];
						}

					
					switch (max_color % 6 + 1) {
					case 1:
						robot.mouseMove(BUTTON_X_OFFSET + 45 * 0, BUTTON_Y_OFFSET + 45 * 0);
						break;

					case 2:
						robot.mouseMove(BUTTON_X_OFFSET + 45 * 1, BUTTON_Y_OFFSET + 45 * 0);
						break;

					case 3:
						robot.mouseMove(BUTTON_X_OFFSET + 45 * 2, BUTTON_Y_OFFSET + 45 * 0);
						break;

					case 4:
						robot.mouseMove(BUTTON_X_OFFSET + 45 * 0, BUTTON_Y_OFFSET + 45 * 1);
						break;

					case 5:
						robot.mouseMove(BUTTON_X_OFFSET + 45 * 1, BUTTON_Y_OFFSET + 45 * 1);
						break;

					case 6:
						robot.mouseMove(BUTTON_X_OFFSET + 45 * 2, BUTTON_Y_OFFSET + 45 * 1);
						break;
					}
					
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
	}
}
