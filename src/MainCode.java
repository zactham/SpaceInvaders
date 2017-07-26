import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainCode extends JPanel implements KeyListener

{

	private boolean runAgain = true;
	public final int max = 100;
	public int[] randomNumbers = new int[max];// makes 20 randomNumbers 

	private int totalRounds = -2;// total turns, everytime a circle gets colored
	private int score = 0;
	boolean square1 = false;
	boolean square2 = false;
	boolean square3 = false;
	boolean square4 = false;
	boolean square5 = false;
	boolean square6 = false;
	boolean square7 = false;
	boolean square8 = false;
	boolean square9 = false;

	int counter = 1;
	boolean scored = false;

	int round = 0;

	int highlightedCircle = -1;


	private boolean soundPlaying = true;

	public boolean correct = false;


	public MyTimer timer;
	public int turnTime = 2500;

	public JFrame restart;
	public JFrame gameOver;
	public JFrame start;



	// Constructor
	public MainCode()
	{
		setFocusable(true);
		// Register for mouse events on the panel
		addKeyListener(this);


		runAgain = true;

		randomNumbers = new int[max];// makes 20 randomNumbers 

		totalRounds = -2;// total turns, everytime a circle gets colored
		score = 0;
		square1 = false;
		square2 = false;
		square3 = false;
		square4 = false;
		square5 = false;
		square6 = false;
		square7 = false;
		square8 = false;
		square9 = false;

		counter = 1;
		scored = false;

		round = 0;

		highlightedCircle = -1;


		soundPlaying = true;

		correct = false;

		turnTime = 2500;


	}



	public void init() throws InterruptedException
	{

		// launch game
	
		JFrame frame = new JFrame("Sample Frame");

		frame.add(this);

		frame.setTitle("Whac - A - Mole");


		JOptionPane.showMessageDialog(start, "Use the 9 num pad on the right side of the keyboard to whack the moles and use S to toggle the music");


		//Sets the speed of the game for each mode
		if (TitleScreen.easy == true)

			turnTime = 800;

		if (TitleScreen.med == true)

			turnTime = 500;

		if (TitleScreen.hard == true)

			turnTime = 400;

		timer = new MyTimer(turnTime);

		timer.start();

		try

		{
			playMusicMain();
		}

		catch (Exception err)
		{
			//System.out.println("2. " + err);
		}

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		round = 0;
		centerWindow();
		frame.setSize(400, 400);
		setColors();

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

	public void MainLoop() // throws InterruptedException

	{
		
		if (round < max && runAgain)
		{
			if (timer.isExpired())
			{
				timer.start();
				setColors();
				boardTurn(round);
				round++;
			}
			repaint();
		}

	}

	public class AL implements ActionListener
	{


		public final void actionPerformed(ActionEvent e)
		{

			if (soundPlaying)
			{
				Sound.audioClip.stop();
				soundPlaying = false;
			}
			else
			{
				Sound.audioClip.start();
				soundPlaying = true;
			}
		}
	}

	public void playInGameMusic() throws InterruptedException

	{
		Sound.play("IngameMusic.wav");

	}

	public void playMusicMain() throws InterruptedException

	{
		playInGameMusic();
	}

	public void playSoundEffect() throws InterruptedException

	{
		Sound.play("SMACK Sound Effect.wav");
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

	public void setColors()

	{

		totalRounds++;

	}

	public void rounds()

	{
		runAgain = true;

	}


	// returns true if next turn
	public void boardTurn(int round)
	{

		scored = false;

		gameEnding();
	}

	public void gameEnding()
	{
		//When the game ends
		if (round==max-1)
		{
			if (soundPlaying)
			{
				Sound.audioClip.stop();
				soundPlaying = false;
			}
			else
			{
				Sound.audioClip.start();
				soundPlaying = true;
			}

			//Game Over Message
			JOptionPane.showMessageDialog(gameOver,
					"Click the X and then hit F11 to RESTART or Click the X in the top right to QUIT\n Your Percentage:\t " + score + "%");
		}
	}


	public void displayScore(Graphics page)
	{
		//Displays the Score
		page.setColor(Color.black);
		page.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
		page.drawString("SCORE: ", 75, 350);
		page.drawString(Integer.toString(score), 275, 350);
	}

	@Override
	protected void paintComponent(Graphics page)
	{
		displayScore(page);
	}

	public int getScore()
	{
		return score;
	}




	public void keyPressed(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		int c = arg0.getKeyCode();

		//Pressing the keys 1 2 3 on the num pad on the right side of the keyboard
		if (c == KeyEvent.VK_NUMPAD1)
		{

		}

		if (c == KeyEvent.VK_NUMPAD2) 
		{

		}

		if (c == KeyEvent.VK_NUMPAD3)
		{

		}

		//When S is pressed the music stops
		if (c == KeyEvent.VK_S) {
			if (soundPlaying)
			{
				Sound.audioClip.stop();
				soundPlaying = false;
			}
			else
			{
				Sound.audioClip.start();
				soundPlaying = true;
			}
		}
	}



	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void update(Graphics g) 
	{
		paint(g);
	}
}

