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

	private boolean live1 = true;
	private boolean live2 = true;
	private boolean live3 = true;

	private Player player;



	private Barricade[] barricade = new Barricade[4];

	private Explosion exp = new Explosion();

	private UFO ufo = new UFO();

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


		if (ufo.getX() <= 0 - GameObject.getufoSize())
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
				getScaledInstance(GameObject.getPlayerHeight(), GameObject.getPlayerWidth(), Image.SCALE_DEFAULT);
		player = new Player();	
		player.setImage(playerImg);
		player.setX(gameboardWidth/2);
		player.setY(gameboardHeight-100);
		player.createBounds(player.getX(),player.getY(),player.getPlayerHeight(), player.getPlayerWidth());
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
		if(ufo.getDirection()==1){
			ufo.setX(gameboardWidth-GameObject.getufoSize());
		}
		else if(ufo.getDirection()==2){
			ufo.setX(0);
		}
		ufo.setY(alienManager.getMinAlienY() - alienManager.getRowspacing());		
		ufo.setImage(img);
		ufo.createBounds(ufo.getX(), ufo.getY(), GameObject.getufoSize(), GameObject.getufoSize()/2);
		resetufoPos();
	}


	public void resetufoPos()
	{
		ufo.setX(gameboardWidth-GameObject.getufoSize());
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

				if(ufo.getBounds().intersects(player.getShot().getBounds())&& ufo.getVisible() == true)
				{
					int ran=(int) (Math.random()*6);
					ufo.stop();
					player.removeShot();
					sound.play("sounds/alien_hit.wav");
					ufoHit(ufo);
					switch(ran){
					case 1: score+=100;
					break;
					case 2: score+=50;
					break;
					case 3: score+=200;
					break;
					case 4: score+=250;
					break;
					case 5: score+=300;
					break;
					}
					break;
				}

				if (alienManager.getAlien(i).getAlienFired())
				{
					if(alienManager.getAlien(i).getAlienShot().getBounds().intersects(player.getBounds()))
					{
						System.out.println("Player has been hit");
						playerHit();

					}
				}
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

	public void alienHit(Alien a)
	{

		for(int i = 0; i < GameObject.getAlienSize(); i++)
		{
			int alienY = alienManager.getAlien(i).getY();
			if (alienManager.getAlien(i).getlowestinCol())
			{
				for(int z = 0; z < GameObject.getAlienSize(); i++)
				{
					if(alienManager.getAlien(z).getY() == alienY - 50)
						alienManager.getAlien(z).setlowestinCol(true);
				}
			}
		}

		exp.setVisible(true);
		exp.setX(a.getX());
		exp.setY(a.getY());
		//yeah ik this stuff can go in start but i like it here
		exp.start();


	}

	public void playerHit()
	{
		if(!live1)
			live2 = false;
		else if(!live2)
			live3 = false;

		live1 = false;

	}

	public void ufoHit(UFO u)
	{
		exp.setVisible(true);
		exp.setX(u.getX());
		exp.setY(u.getY());
		//yeah ik this stuff can go in start but i like it here
		exp.start();


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
		page.drawString("SCORE: ", 20, 30);
		page.setColor(Color.green);
		page.drawString(Integer.toString(score), 110, 30);
	}

	public void displayLives(Graphics page)
	{
		page.setColor(Color.WHITE);
		page.setFont(new Font("Lucida Sans Typewriter" ,Font.PLAIN, 25));
		page.drawString("LIVES: ", 400, 20);
		if(live1)
			page.drawImage(player.getImage(), 600, 20, null);
		if(live1 && live2)
			page.drawImage(player.getImage(), 535, 20, null);
		if(live1 && live2 && live3)
			page.drawImage(player.getImage(), 470, 20, null);

	}

	@Override
	protected void paintComponent(Graphics page)
	{
		super.paintComponent(page);		// paint baseclass members too

		displayScore(page);
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

