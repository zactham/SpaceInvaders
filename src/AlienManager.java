import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class AlienManager 
{
	private ArrayList<GameObject> alienList= new ArrayList<GameObject>();

	public void init()
	{
		int xStart=35;
		int xSpacing=60;
		int yStart=55;
		int ySpacing=65;



		Image alienImage1 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien1_1.png").getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		Image alienImage2 = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), 
				"images//alien1_2.png").getScaledInstance(50, 50, Image.SCALE_DEFAULT);

		for(int x=0; x<=10; x++)
		{
			Alien a1=new Alien1();
			a1.addImage(alienImage1);
			a1.addImage(alienImage2);
			a1.setX(xStart+(xSpacing*x));
			a1.setY(yStart);
			alienList.add(a1);
		}

		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{

			Alien a1=new Alien1();
			a1.addImage(alienImage1);
			a1.addImage(alienImage2);
			a1.setX(xStart+(xSpacing*x));
			a1.setY(yStart);
			alienList.add(a1);
		}

		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien a2 = new Alien2();
			a2.addImage(alienImage1);
			a2.addImage(alienImage2);
			a2.setX(xStart+(xSpacing*x));
			a2.setY(yStart);
			alienList.add(a2);
		}

		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien a2 = new Alien2();
			a2.addImage(alienImage1);
			a2.addImage(alienImage2);
			a2.setX(xStart+(xSpacing*x));
			a2.setY(yStart);
			alienList.add(a2);
		}

		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien3 a3=new Alien3();
			a3.addImage(alienImage1);
			a3.addImage(alienImage2);
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

	public int getNumAliens()
	{
		return alienList.size();
	}
}
