import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class AlienManager 
{
	private ArrayList<GameObject> alienList= new ArrayList<GameObject>();

	private int alienMaxX;
	private GameObject farRightAlien;
	private int alienMinX;
	private GameObject farLeftAlien;


	public void init()
	{
		int xStart=140;
		int xSpacing=40;
		int yStart=55;
		int ySpacing=50;
		int alienSize=25;



		Image alien3Image1 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien1_1.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);
		Image alien3Image2 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien1_2.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);

		Image alien2Image1 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien2_1.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);
		Image alien2Image2 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien2_2.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);

		Image alien1Image1 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien3_1.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);
		Image alien1Image2 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien3_2.png").getScaledInstance(alienSize, alienSize, Image.SCALE_DEFAULT);

		for(int x=0; x<=10; x++)
		{
			Alien a1=new Alien1();
			a1.addImage(alien1Image1);
			a1.addImage(alien1Image2);
			a1.setX(xStart+(xSpacing*x));
			a1.setY(yStart);
			alienList.add(a1);
		}

		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{

			Alien a1=new Alien1();
			a1.addImage(alien2Image1);
			a1.addImage(alien2Image2);
			a1.setX(xStart+(xSpacing*x));
			a1.setY(yStart);
			alienList.add(a1);
		}

		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien a2 = new Alien2();
			a2.addImage(alien2Image1);
			a2.addImage(alien2Image2);
			a2.setX(xStart+(xSpacing*x));
			a2.setY(yStart);
			alienList.add(a2);
		}

		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien a2 = new Alien2();
			a2.addImage(alien3Image1);
			a2.addImage(alien3Image2);
			a2.setX(xStart+(xSpacing*x));
			a2.setY(yStart);
			alienList.add(a2);
		}

		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien3 a3=new Alien3();
			a3.addImage(alien3Image1);
			a3.addImage(alien3Image2);
			a3.setX(xStart+(xSpacing*x));
			a3.setY(yStart);
			alienList.add(a3);
		}



	}

	public void draw(Graphics page)
	{
		for(GameObject Alien : alienList)
		{
			Alien.draw(page);
		}
	}

	public void update()
	{
		for(GameObject Alien : alienList)
		{
			Alien.update();
		}
	}

	public void addAlien(GameObject a)
	{
		alienList.add(a);
	}

	public void removeAlien(GameObject a)
	{
		alienList.remove(a);
	}


	private void inceAlienImage()
	{
		for (int i = 0; i < alienList.size(); i++)
		{
			Alien.incImageIndex();
		}
	}

	private int getMaxAlienX()
	{

		farRightAlien.setX(-2);

		for(GameObject Alien: alienList)
		{
			if(Alien.getX()>farRightAlien.getX())
			{
				farRightAlien=Alien;
			}
		}

		alienMaxX  = farRightAlien.getX();
		return alienMaxX;
	}

	private int getMinAlienX()
	{

		farLeftAlien.setX(702);

		for(GameObject Alien: alienList)
		{
			if(Alien.getX()<farLeftAlien.getX())
			{
				farLeftAlien=Alien;
			}
		}
		alienMinX =  farLeftAlien.getX();
		return alienMinX;
	}

	private void moveAliensSideways(int increment)
	{
		for (int i = 0; i < alienList.size(); i++)
		{
			GameObject temp = alienList.get(i);
			int X = temp.getX();
			X+=increment;
			temp.setX(X);
			alienList.set(i, temp);
		}
	}
	
	private void moveAliensDown()
	{
		for (int i = 0; i < alienList.size(); i++)
		{
			GameObject temp = alienList.get(i);
			int Y = temp.getY();
			Y+=50;
			temp.setY(Y);
			alienList.set(i, temp);
		}
	}

	public int getNumAliens()
	{
		return alienList.size();
	}

}
