import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Barricade extends GameObject
{

	private Image barricadeImage;
	
	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Barricade;
	}
	
	@Override
	public void draw(Graphics page)
	{	
		super.draw(page);
		page.drawImage(barricadeImage, getX(), getY(),null);	
	}
	
	public void setImage(Image i)
	{
		barricadeImage=i;
	}
	
	public Image getImage()
	{
		return barricadeImage;
	}
	
	@Override
	public void update()
	{
		super.update();
		
		updateBounds();
	}
}
