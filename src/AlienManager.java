import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class AlienManager 
{
	private ArrayList<GameObject> alienList= new ArrayList<GameObject>();

	public void init(){
		int xStart=100;
		int xSpacing=80;
		int yStart=100;
		int ySpacing=100;
		
		for(int x=0; x<=10; x++)
		{
			Alien1 a1=new Alien1();
			a1.setX(xStart+(xSpacing*x));
			a1.setY(yStart);
			alienList.add(a1);
		}
		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien1 a1=new Alien1();
			a1.setX(xStart+(xSpacing*x));
			a1.setY(yStart);
			alienList.add(a1);
		}
		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien2 a2 = new Alien2();
			a2.setX(xStart+(xSpacing*x));
			a2.setY(yStart);
			alienList.add(a2);
		}
		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien2 a2 = new Alien2();
			a2.setX(xStart+(xSpacing*x));
			a2.setY(yStart);
			alienList.add(a2);
		}
		yStart+=ySpacing;
		for(int x=0; x<=10; x++)
		{
			Alien3 a3=new Alien3();
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
