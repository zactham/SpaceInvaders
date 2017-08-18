import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class AlienManager 
{
	private static ArrayList<Alien> alienList= new ArrayList<Alien>();

	private int alienMaxX;
	private int alienMinX;
	private int alienMaxY;
	private int alienMinY;
	private MyTimer stepTimer = new MyTimer(alienList.size()*20);
	private int rowSpacing = 50;
	private int alienMovement = 10;
	private int alienWidth = 20;

	private int soundSwap = 0;

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


			if(moveRight)
			{
				moveAliensSideways(moveRight);
			}


			if(getMaxAlienX() >= GameObject.getGameWidth())
			{
				moveAliensDown();
				
				moveRight = false;
			}

			if(!moveRight)
			{
				moveAliensSideways(moveRight);
			}

			if(getMinAlienX() <= 0)
			{
				moveAliensDown();
				moveRight = true;

			}
			if(soundSwap == 0)
				sound.play("sounds/fastinvader1.wav");
			else if(soundSwap == 1)
				sound.play("sounds/fastinvader2.wav");
			else if(soundSwap == 2)
				sound.play("sounds/fastinvader3.wav");
			else if(soundSwap == 3)
				sound.play("sounds/fastinvader4.wav");

			soundSwap++;
			soundSwap%=4;
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
	
	public int getRowspacing()
	{
		return rowSpacing;
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

	
	public int getMinAlienY()
	{
		alienMinY = 0;

		Alien temp = alienList.get(0);

		for (int i = 1; i< alienList.size(); i++)
		{
			if (alienList.get(i).getY()<temp.getY())
			{
				temp = alienList.get(i);
			}
		}
		alienMinY =  temp.getY();
		//System.out.println(alienMinX);
		return alienMinY ;


	}
	
	
	public int getMaxAlienY()
	{
		alienMaxY = 0;
		
		Alien temp = alienList.get(0);

		for (int i = 1; i< alienList.size(); i++)
		{
			if (alienList.get(i).getY()>temp.getY())
			{
				temp = alienList.get(i);
			}
		}
		alienMaxY =  temp.getY() + alienWidth;
		//System.out.println(alienMaxX);
		return alienMaxY ;


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
		for (int i = 0; i < alienList.size(); i++)
		{
			int X = alienList.get(i).getX();
			if(direction)
				alienList.get(i).setX(X+alienMovement);
			else
				alienList.get(i).setX(X-alienMovement);

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
