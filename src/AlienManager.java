import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class AlienManager 
{
	private ArrayList<Alien> alienList= new ArrayList<Alien>();

	private int alienMaxX;
	private int alienMinX;
	private MyTimer stepTimer = new MyTimer(alienList.size()*20);
	private int rowSpacing = 50;
	private int alienMovement = 10;
	private int alienWidth = 20;

	private boolean soundSwap = true;

	private Sound sound = new Sound();

	private boolean moveRight = true;


	public void init()
	{
		int xStart=140;
		int xSpacing=40;
		int yStart=55;
		int ySpacing=50;
		int alienSize=25;



		Image alien1Image1 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien1_1.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);
		Image alien1Image2 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien1_2.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);

		Image alien2Image1 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien2_1.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);
		Image alien2Image2 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien2_2.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);

		Image alien3Image1 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien3_1.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);
		Image alien3Image2 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien3_2.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);

		for(int x=0; x<=10; x++)
		{
			Alien a1=new Alien1();
			a1.addImage(alien1Image1);
			a1.addImage(alien1Image2);
			a1.setX(xStart+(xSpacing*x));
			a1.setY(yStart);
			addAlien(a1);
		}


		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien a2 = new Alien2();
			a2.addImage(alien2Image1);
			a2.addImage(alien2Image2);
			a2.setX(xStart+(xSpacing*x));
			a2.setY(yStart);
			addAlien(a2);
		}

		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien a2 = new Alien2();
			a2.addImage(alien2Image1);
			a2.addImage(alien2Image2);
			a2.setX(xStart+(xSpacing*x));
			a2.setY(yStart);
			addAlien(a2);
		}

		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien3 a3=new Alien3();
			a3.addImage(alien3Image1);
			a3.addImage(alien3Image2);
			a3.setX(xStart+(xSpacing*x));
			a3.setY(yStart);
			addAlien(a3);
		}

		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{

			Alien a3=new Alien3();
			a3.addImage(alien3Image1);
			a3.addImage(alien3Image2);
			a3.setX(xStart+(xSpacing*x));
			a3.setY(yStart);
			addAlien(a3);
		}



	}

	public void draw(Graphics page)
	{
		for(Alien Alien : alienList)
		{
			Alien.draw(page);
		}
	}

	public void update()
	{
		if(stepTimer.isExpired())
		{
			stepTimer.start();
			stepTimer.setDelay(alienList.size()*20);
			incAlienImage();


			//delete true and false
			if(moveRight)
			{
				moveAliensSideways(true);
			}


			if(getMaxAlienX() >= GameObject.getGameWidth())
			{
				moveAliensDown();
				moveRight = false;
			}

			if(!moveRight)
			{
				moveAliensSideways(false);
			}

			if(getMinAlienX() <= 0)
			{
				moveAliensDown();
				moveRight = true;

			}
			if(soundSwap)
				sound.play("sounds/fastinvader1.wav");
			else
				sound.play("sounds/fastinvader2.wav");
			soundSwap = !soundSwap;
		}
	}

	public void addAlien(Alien a)
	{
		alienList.add(a);
	}

	public void removeAlien(Alien a)
	{
		alienList.remove(a);
	}


	private void incAlienImage()
	{
		for (int i = 0; i < alienList.size(); i++)
		{
			alienList.get(i).incImageIndex();
		}
	}

	private int getMaxAlienX()
	{
		alienMaxX = 0;

		Alien temp = alienList.get(0);

		for (int i = 1; i< alienList.size(); i++)
		{
			if (alienList.get(i).getX()>temp.getX())
			{
				temp = alienList.get(i);
			}
		}
		alienMaxX =  temp.getX() + alienWidth;
		//System.out.println(alienMaxX);
		return alienMaxX ;


	}

	private int getMinAlienX()
	{
		alienMinX = 0;


		Alien temp = alienList.get(0);

		for (int i = 1; i< alienList.size(); i++)
		{
			if (alienList.get(i).getX()<temp.getX())
			{
				temp = alienList.get(i);
			}
		}
		alienMinX =  temp.getX();
		//System.out.println(alienMinX);
		return alienMinX ;
	}

	private void moveAliensSideways(boolean direction)
	{
		if (direction == true)//move right
		{
			for (int i = 0; i < alienList.size(); i++)
			{
				int X = alienList.get(i).getX();
				alienList.get(i).setX(X+alienMovement);
			}
		}

		if (direction == false)//move left
		{
			for (int i = 0; i < alienList.size(); i++)
			{
				int X = alienList.get(i).getX();
				alienList.get(i).setX(X-alienMovement);
			}
		}
	}



	private void moveAliensDown()
	{
		for (int i = 0; i < alienList.size(); i++)
		{
			Alien temp = alienList.get(i);
			int Y = temp.getY();
			Y+=rowSpacing;
			temp.setY(Y);

		}
	}

	public int getNumAliens()
	{
		return alienList.size();
	}

}
