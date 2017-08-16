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

			boolean moveRight = true;

			while(moveRight)
			{
				moveAliensSideways(true);

				if(getMaxAlienX() >= GameObject.getGameWidth())
				{
					moveAliensDown();
					moveRight = false;

				}
			}

			while(!moveRight)
			{
				moveAliensSideways(false);

				if(getMinAlienX() <= GameObject.getGameWidth())
				{
					moveAliensDown();
					moveRight = true;
					
				}
			}



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


		for (int i = 1; i< alienList.size(); i++)
		{
			if (alienList.get(i-1).getX()>alienList.get(i).getX())
			{
				alienMaxX = alienList.get(i-1).getX();
			}
		}

		return alienMaxX;
	}

	private int getMinAlienX()
	{

		for (int i = 1; i< alienList.size(); i++)
		{
			if (alienList.get(i-1).getX()<alienList.get(i).getX())
			{
				alienMinX = alienList.get(i-1).getX();
			}
		}

		return alienMaxX;
	}

	private void moveAliensSideways(boolean direction)
	{
		if (direction == true)//move right
		{
			for (int i = 0; i < alienList.size(); i++)
			{
				Alien temp = alienList.get(i);
				int X = temp.getX();
				X+=rowSpacing;
				temp.setX(X);

			}
		}

		if (direction == false)//move left
		{
			for (int i = 0; i < alienList.size(); i++)
			{
				Alien temp = alienList.get(i);
				int X = temp.getX();
				X-=rowSpacing;
				temp.setX(X);

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
