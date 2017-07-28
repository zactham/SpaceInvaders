import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SpaceInvadersGame extends JPanel implements KeyListener
{
	Sound sound;
	private int score = 0;
	private boolean end;
	
	private Player player;
	
	private InputManager inputManager;
	private AlienManager alienManager;

	//private JFrame restart;
	private JFrame gameOver;
	private JFrame start;

	private final int gameboardSize = 1000;


	// Constructor
	public SpaceInvadersGame()
	{
		setFocusable(true);
		// Register for mouse events on the panel
		addKeyListener(this);
	}


	public void init(int level)
	{
		sound = new Sound();
		inputManager = new InputManager();
		alienManager = new AlienManager();
		
		setPreferredSize(new Dimension(gameboardSize, gameboardSize));

		// launch game
		JFrame frame = new JFrame("Sample Frame");
		frame.getContentPane().add(this);
		frame.setTitle("Game Title");
		this.setBackground(Color.black);

		JOptionPane.showMessageDialog(start, "Game Instructions");

		player = new Player();
		player.setX(gameboardSize/2);
		player.setY(gameboardSize-100);
		
		alienManager.init();
		
		//Sets the speed of the game for each mode
		if (level == 1)		// easy
		{

		}

		if (level == 2)		// medium
		{

		}

		if (level == 3)		// hard
		{

		}

		playMusicMain();

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centerWindow();
		frame.setLocationRelativeTo(TitleScreen.theApp);

		// runs the mainLoop
		ActionListener timerAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainLoop();

			}

		};

		// Frame rate, updates the frame every 15ms --- 60fps
		Timer timer = new Timer(15, timerAction);
		timer.setRepeats(true);
		timer.start();


	}

	public void MainLoop()
	{
		updateGame();
		repaint();
	}
	
	public void updateGame()
	{
		player.update();
		alienManager.update();
		checkKeys();
	}
	
	public void drawGame(Graphics page)
	{
		player.draw(page);
		alienManager.draw(page);
	}


	public void playMusicMain()
	{
		sound.play("IngameMusic.wav");
	}

	public void playSoundEffect()
	{
		//sound.play("SMACK Sound Effect.wav");
	}

	// Centers the window
	public void centerWindow()
	{
		// gets top level window
		Window window;
		Container c = getParent();
		while (c.getParent() != null)
			c = c.getParent();

		// center window
		if (c instanceof Window)// if it is the top window...
		{
			// centers it
			window = (Window) c;
			window.pack();
			window.setLocationRelativeTo(null);
		}
	}

	//
	//When the game ends
	//
	public void gameEnding()
	{

		sound.stop();

		int result = JOptionPane.showConfirmDialog(this, 
				"Your Score: " + score + " - Play Again?", 
				"Game Over", JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.NO_OPTION)
		{
			// no
			System.exit(0);
		}
		else
		{
			// yes, play again
			resetGame();
		}
	}

	private void resetGame()
	{

	}

	public void displayScore(Graphics page)
	{
		//Displays the Score
		page.setColor(Color.WHITE);
		page.setFont(new Font("Lucida Sans Typewriter" ,Font.PLAIN, 25));
		page.drawString("SCORE: ", 15, 30);
		page.drawString(Integer.toString(score), 15, 55);
	}

	@Override
	protected void paintComponent(Graphics page)
	{
		super.paintComponent(page);		// paint baseclass members too

		displayScore(page);
		
		drawGame(page);
	}

	public int getScore()
	{
		return score;
	}


	//To quickly change the direction of the player(without the player "Hesitating") you have to release
	//the previous key before pressing the next one(More to this- press both keys, not at the same time, then release one and watch what happens).
	//Maybe there is some way to remove the hesitation/continue the flow?

	public void checkKeys()
	{


		
		//Pressing the keys
		if (inputManager.getKeyPressed(KeyEvent.VK_LEFT)==true)
		{
			player.setDirection(PlayerDirection.LEFT);
		}
		else if (inputManager.getKeyPressed(KeyEvent.VK_RIGHT)==true) 
		{
			player.setDirection(PlayerDirection.RIGHT);
		}
		else
		{
			player.setDirection(PlayerDirection.NONE);
		}

		if(inputManager.getKeyPressed(KeyEvent.VK_SPACE))
		{
			player.fire();
		}

		//When S is pressed the music stops
		if (inputManager.getKeyPressed(KeyEvent.VK_S)==true) {
			if (sound.isPlaying())
			{
				sound.stop();
			}
			else
			{
				sound.resume();
			}
		}
	}
	
	public void keyPressed(KeyEvent arg0) 
	{
		int c = arg0.getKeyCode();
		inputManager.setKeyPressed(c, true);
	}



	public void keyReleased(KeyEvent arg0) 
	{
		int c = arg0.getKeyCode();
		inputManager.setKeyPressed(c, false);
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

