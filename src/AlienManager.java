import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class AlienManager extends GameObject
{
	private ArrayList<GameObject> alienList= new ArrayList<GameObject>();

	@Override
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
