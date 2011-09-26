package Application;


import javax.swing.JButton;
import javax.swing.JFrame;
// import javax.swing.JTextArea;


public final class TestWindow extends JFrame {
	private static final long serialVersionUID = -5378354707237622386L;

	private TestWindowListenerFactory factory = null;
	
	private JButton button = null;
	
	
    private void initComponents() {
    	button = new JButton("Игра во Flood-it");
    	
    	factory = new TestWindowListenerFactory();
    	button.addActionListener(factory.getFlooditClickListener());
    	
    	getContentPane().add(button, "South");

    	setSize(350, 150);
    }
    
	public TestWindow() {
		super("Тестовое окно");
		
		initComponents();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setAlwaysOnTop(true);
	}
}
