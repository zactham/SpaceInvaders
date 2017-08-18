import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SpaceInvadersGame extends JPanel implements KeyListener
{
	private Sound sound;
	private int score = 0;
	private boolean end;
	
	private boolean UFOAppearance = false;

	private Player player;

	private Barricade[] barricade = new Barricade[4];
	
	public static UFO u = new UFO();

	private InputManager inputManager;
	private AlienManager alienManager;

	//private JFrame restart;
	private JFrame gameOver;
	private JFrame start;

	private final int gameboardWidth = 700;
	private final int gameboardHeight = 800;

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

		setPreferredSize(new Dimension(gameboardWidth, gameboardHeight));

		GameObject.setGameHeight(gameboardHeight);
		GameObject.setGameWidth(gameboardWidth);

		// launch game
		JFrame frame = new JFrame("Sample Frame");
		frame.getContentPane().add(this);
		frame.setTitle("Game Title");
		this.setBackground(Color.black);

		JOptionPane.showMessageDialog(start, "Game Instructions");

		player = new Player();
		Image playerImg = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), "images//player.png").getScaledInstance(75, 75, Image.SCALE_DEFAULT);
		player.setImage(playerImg);
		player.setX(gameboardWidth/2);
		player.setY(gameboardHeight-100);

		initBarricades();

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

		for (int i = 0; i < barricade.length; i++)
		{
			barricade[i].update();
		}

		alienManager.update();
		checkKeys();
		/*
		int randomNum = (int) (Math.random()*10 +1);
		
		if(randomNum == 1 && !UFOAppearance)
		{
			UFOAppearance = true;
			
			initUFOs();
		}
		*/
		
		initUFOs();
	}

	public void drawGame(Graphics page)
	{
		player.draw(page);

		for (int i = 0; i < barricade.length; i++)
		{
			barricade[i].draw(page);
		}

		alienManager.draw(page);
	}

	public void initBarricades()
	{
		Image img = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), "images//barricade.png").getScaledInstance(75, 75, Image.SCALE_DEFAULT);

		for(int x=0; x<=3; x++)
		{
			Barricade b = new Barricade();
			b.setX(((gameboardWidth/4-10) * x+1) + b.getStandardSize());
			b.setY(player.getY()-100);
			barricade[x] = b;

			barricade[x].setImage(img);
		}
	}

	public void initUFOs()
	{
		Image img = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), "images//UFO.png").getScaledInstance(75, 75, Image.SCALE_DEFAULT);

		u.setX(gameboardWidth + u.getStandardSize());
		u.setY(AlienManager.getMaxAlienY() - AlienManager.getRowspacing());		
		u.setImage(img);

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

