import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class TitleScreen extends JApplet
{
	private Sound sound;
	private boolean easy = true;
	private boolean med = false;
	private boolean hard = false;
	public static TitleScreen theApp;
	
	private SpaceInvadersGame board;

	private ImageIcon titleScreenImage;//image


	//All buttons

	//Image button
	public JButton 	titleScButton;

	//Main Buttons
	private JFrame help;

	public void init () 
	{		
		sound = new Sound();
		theApp = this;
		playMusic();


		//Adds the image and creates a button out of it
		titleScreenImage = new ImageIcon(this.getClass().getResource("TSImage.jpg"));//image	
		titleScButton = new JButton (titleScreenImage);//image button
		getContentPane().add(titleScButton);
		setSize(320,240);
		centerWindow();//centers the window


		titleScButton.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent arg0) 
			{
				// TODO Auto-generated method stub
				int c = arg0.getKeyCode();

				//Pressing the keys 1 2 3 on the num pad on the right side of the keyboard
				if (c == KeyEvent.VK_E || c == KeyEvent.VK_ENTER)
				{
					//System.out.println("Easy");

					easy = true;
					sound.stop();
					addMainBoard();					
				}
			}
		});


		//Based on where they click in easy medium or hard something happens
		titleScButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//Music Toggle
				if (e.getX() > 0 && e.getX() < 27 && e.getY() > 745 && e.getY() < 769)
				{

					//System.out.println("Music toggle");
					if (sound.isPlaying())
					{
						sound.stop();
					}
					else
					{
						sound.resume();
					}

				}
				//Help
				if (e.getX() > 806 && e.getX() < 830 && e.getY() > 752 && e.getY() < 775)
				{

					//	System.out.println("Help");
					JOptionPane.showMessageDialog(help,
							"Use the 9 num pad on the right side of the keyboard to whack the moles.");


				}

			}

			public void mouseEntered(MouseEvent arg0) {

			}

			public void mouseExited(MouseEvent arg0) {

			}

			public void mousePressed(MouseEvent arg0) {

			}

			public void mouseReleased(MouseEvent arg0) {

			}
		});
	}


	public void addMainBoard()
	{
		hideWindow();
		
		int level = 1;
		if (med)
			level = 2;
		else
			if (hard)
				level = 3;
		
		board = new SpaceInvadersGame();
		board.init(level);
	}


	public void playMusic()
	{
		sound.play("TitleScreenMusic.wav");
	}

	public void hideWindow()
	{
		Container c = getParent();
		while (c.getParent()!=null) 
			c = c.getParent();
		c.setVisible(false);		
	}
	
	//Centers the window
	public void centerWindow()
	{
		//gets top level window
		Window window;
		Container c = getParent();
		while (c.getParent()!=null) 
			c = c.getParent();

		// center window
		if (c instanceof Window)//if it is the top window...
		{
			//centers it
			window = (Window)c;
			window.pack();
			window.setLocationRelativeTo(null);					
		}
	}

}

