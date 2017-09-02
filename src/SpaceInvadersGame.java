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
import java.util.ArrayList;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SpaceInvadersGame extends JPanel implements KeyListener
{
	private Sound sound;
	private int score = 0;
	private boolean end;

	private int lives = 3;

	private Player player;



	private Barricade[] barricade = new Barricade[4];

	private Explosion exp = new Explosion();

	private UFO ufo = new UFO();

	private InputManager inputManager;
	private AlienManager alienManager;

	private ArrayList <Integer> notLowestAlienX = new ArrayList ();
	private ArrayList <Integer> notLowestAlienY = new ArrayList ();

	//private JFrame restart;
	private boolean gameOver = false;
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
		frame.setTitle("Space Invaders");
		this.setBackground(Color.black);

		JOptionPane.showMessageDialog(start, "Game Instructions");

		initPlayer();

		initBarricades();

		initExplosion();

		alienManager.init();

		initUFOs();

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
		checkKeys();

		player.update();

		for (int i = 0; i < barricade.length; i++)
		{
			barricade[i].update();
		}

		alienManager.update();


		int randomNum = (int) (Math.random()*1000 +1);

		if((randomNum == 1 || inputManager.getKeyPressed(KeyEvent.VK_U)== true) && !ufo.getVisible())
		{
			ufo.start();
			resetufoPos();

		}

		ufo.update();


		if (ufo.getX() <= 0 - GameObject.getufoSize() || ufo.getX() + GameObject.getufoSize() >= GameObject.getGameWidth())
			ufo.stop();

		checkCollisions();

		if(alienManager.getNumAliens() == 0)
			resetAliens();

		exp.update();
	}

	public void drawGame(Graphics page)
	{
		player.draw(page);

		for (int i = 0; i < barricade.length; i++)
		{
			barricade[i].draw(page);
		}

		alienManager.draw(page);

		ufo.draw(page);

		exp.draw(page);

	}

	public void initPlayer()
	{
		Image playerImg = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), "images//player.png").
				getScaledInstance(GameObject.getPlayerWidth(), GameObject.getPlayerHeight(), Image.SCALE_DEFAULT);
		player = new Player();	
		player.setImage(playerImg);
		player.setX(gameboardWidth/2);
		player.setY(gameboardHeight-100);
		player.createBounds(player.getX(),player.getY(),GameObject.getPlayerWidth(), GameObject.getPlayerHeight());
	}

	public void initBarricades()
	{
		Image img = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), "images//barricade.png").getScaledInstance(GameObject.getStandardSize(), GameObject.getStandardSize(), Image.SCALE_DEFAULT);

		for(int x=0; x<=3; x++)
		{
			Barricade b = new Barricade();
			b.setX(((gameboardWidth/4-10) * x+1) + b.getStandardSize());
			b.setY(player.getY()-100);
			barricade[x] = b;
			barricade[x].setImage(img);
			barricade[x].createBounds(b.getX(),b.getY(),b.getStandardSize(),b.getStandardSize());
		}
	}

	public void initExplosion()
	{
		Image img = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(),
				"images//explosion.png").getScaledInstance(GameObject.getExplosionSize(), GameObject.getExplosionSize(), Image.SCALE_DEFAULT);
		exp.setVisible(false);
		exp.setImage(img);
		exp.createBounds(exp.getX(), exp.getY(), AlienManager.getAlienSize(), AlienManager.getAlienSize());

	}

	public void initUFOs()
	{
		Image img = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), "images//ufo.png").getScaledInstance(GameObject.getufoSize(), GameObject.getufoSize()/2, Image.SCALE_DEFAULT);
		ufo.setY(alienManager.getMinAlienY() - alienManager.getRowspacing());		
		ufo.setImage(img);
		ufo.createBounds(ufo.getX(), ufo.getY(), GameObject.getufoSize(), GameObject.getufoSize()/2);
		resetufoPos();
	}


	public void resetufoPos()
	{
		if(ufo.getDirection() == 1)
			ufo.setX(gameboardWidth-GameObject.getufoSize());
		else if (ufo.getDirection() == 2)
			ufo.setX(0);

		ufo.setY(alienManager.getMinAlienY() - alienManager.getRowspacing());		
	}

	public void resetAliens()
	{
		alienManager.init();
	}


	public void checkCollisions()
	{

		if(player.getShot() != null)
		{
			if(!ufocheckCollisions())
			{
				//Checks if the player hits the alien
				for(int i = alienManager.getNumAliens() - 1; i >-1; i--)
				{
					if(alienManager.getAlien(i).getBounds().intersects(player.getShot().getBounds()))
					{

						player.removeShot();
						sound.play("sounds/alien_hit.wav");
						alienHit(alienManager.getAlien(i));
						increaseScore(alienManager.getAlien(i));
						alienManager.removeAlien(alienManager.getAlien(i));


						break;
					}
				}
			}
		}
		//Checks if the alien hits the player
		for(int z = 0; z< alienManager.getNumAliens(); z++)
		{

			if (alienManager.getAlien(z).getAlienShot() != null)
			{
				if(alienManager.getAlien(z).getAlienShot().getBounds().intersects(player.getBounds()))
				{
					playerHit(alienManager.getAlien(z));
					alienManager.getAlien(z).setAlienShot(null);

				}


			}
			if(alienManager.getAlien(z).getBounds().intersects(player.getBounds()))
			{
				playerHit(alienManager.getAlien(z));
				break;
			}


		}


	}

	public void increaseScore(Alien a)
	{
		if (a.getType() == GameObjectType.Alien1)
			score+=10;
		if (a.getType() == GameObjectType.Alien2)
			score+=20;
		if (a.getType() == GameObjectType.Alien3)
			score+=10;
	}

	public void increaseUFOScore(int ran)
	{
		switch(ran)
		{
		case 0: score+=100;
		break;
		case 1: score+=150;
		break;
		case 2: score+=200;
		break;
		case 3: score+=250;
		break;
		case 4: score+=300;
		break;
		}
	}
	public void alienHit(Alien a)
	{
		if (a.getlowestinCol())
		{
			for(int z = 0; z < notLowestAlienX.size(); z++)
			{
				//Needs this in case there are multiple not lowest aliens that were shot
				if(a.getY() - alienManager.getRowspacing() == notLowestAlienY.get(z)  && a.getX() == notLowestAlienX.get(z))
				{
					// set lowest on the alien that has an X + rowspacing above the alien hit
					int newLowestAlienY = a.getY() - alienManager.getRowspacing() - alienManager.getRowspacing();
					for(int i = 0; i < alienManager.getNumAliens(); i++)
					{
						if(alienManager.getAlien(i).getY() == newLowestAlienY)
							alienManager.getAlien(i).setlowestinCol(true);
					}
				}
			}
			for(int i = 0; i < alienManager.getNumAliens(); i++)
			{
				int alienY = alienManager.getAlien(i).getY();
				int alienX = alienManager.getAlien(i).getX();

				if(a.getY() - alienManager.getRowspacing() == alienY  && a.getX() == alienX)
					alienManager.getAlien(i).setlowestinCol(true);

			}
		}
		else
		{
			notLowestAlienX.add(a.getX());
			notLowestAlienY.add(a.getY());
		}


		displayExp(a);


	}


	public void displayExp(GameObject a)
	{
		exp.setVisible(true);
		exp.setX(a.getX());
		exp.setY(a.getY());
		//yeah ik this stuff can go in start but i like it here
		exp.start();	
	}

	public void playerHit(Alien a)
	{
		//The alien physically hits the player
		if(a.getAlienShot() == null)
			displayExp(a);
		else 
		{
			//Alien shoots the player
			exp.setVisible(true);
			exp.setX(a.getAlienShot().getX());
			exp.setY(a.getAlienShot().getY());
			//yeah ik this stuff can go in start but i like it here
			exp.start();
			lives--;
			sound.play("sounds/explosion.wav");
		}

	}

	public void ufoHit(UFO u)
	{
		displayExp(u);
	}

	public boolean ufocheckCollisions()
	{
		if(ufo.getBounds().intersects(player.getShot().getBounds()) && ufo.getVisible() == true)
		{
			int ran = (int) (Math.random()*5);
			ufo.stop();
			player.removeShot();
			sound.play("sounds/alien_hit.wav");
			ufoHit(ufo);
			increaseUFOScore(ran);
			return true;
		}
		return false;
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
		for(int i = alienManager.getNumAliens()-1; i >-1 ; i--)
		{
			alienManager.removeAlien(alienManager.getAlien(i));
		}
		//resetAliens();
		score = 0;
		lives = 3;
		gameOver = false;

		initPlayer();

		alienManager.init();

		initUFOs();
	}

	public void displayScore(Graphics page)
	{
		//Displays the Score
		page.setColor(Color.WHITE);
		page.setFont(new Font("Lucida Sans Typewriter" ,Font.PLAIN, 25));
		page.drawString("SCORE: ", 20, 30);
		page.setColor(Color.green);
		page.drawString(Integer.toString(score), 110, 30);
	}

	public void displayLives(Graphics page)
	{
		page.setColor(Color.WHITE);
		page.setFont(new Font("Lucida Sans Typewriter" ,Font.PLAIN, 25));
		page.drawString("LIVES: ", 400, 20);
		if(lives == 3)
			page.drawImage(player.getImage(), 600, 20, null);
		if(lives>=2)
			page.drawImage(player.getImage(), 535, 20, null);
		if(lives>=1)
			page.drawImage(player.getImage(), 470, 20, null);
		if(lives<1)
		{
			gameOver = true;
			gameEnding();

		}

	}
	@Override
	protected void paintComponent(Graphics page)
	{
		super.paintComponent(page);		// paint baseclass members too

		displayScore(page);

		if(!gameOver)
			displayLives(page);

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

