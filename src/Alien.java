import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Alien extends GameObject
{
	private  ArrayList<Image> alienList= new ArrayList<Image>();
	private static int imageIndex = 0;

	public Alien()
	{
		
	}
	
	public void incImageIndex()
	{
		imageIndex++;
		if(imageIndex>alienList.size()-1)
		{
			imageIndex = 0;
		}
	}

	public void addImage(Image i)
	{
		alienList.add(i);
	}

	@Override
	public void draw(Graphics page)
	{
		super.draw(page);
		page.drawImage(alienList.get(imageIndex), getX(), getY(),null);
	}
	
	
	public void update()
	{
		super.update();
		updateBounds();
	}

	
}
