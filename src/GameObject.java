import java.awt.Graphics;

public class GameObject 
{
	
	private static int x = 0;
	private static int y = 0;
	
	
	
	public static int getX()
	{
		return x;
	}
	
	public void setX(int xPos)
	{
		x = xPos;
	}
	
	public static int getY()
	{
		return y;
	}
	
	public void setY(int yPos)
	{
		y = yPos;
	}
	
	public void draw(Graphics obj)
	{
		
	}
	
	public void update()
	{
		
	}
	
	public GameObjectType getType()
	{
		return GameObjectType.NONE;
	}
	
	

}
