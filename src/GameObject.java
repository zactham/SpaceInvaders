import java.awt.Graphics;

public class GameObject 
{
	
	private int x = 0;
	private int y = 0;
	
	private final int standardSize = 100;
	private final int projectileSize = 15;
	
	
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int xPos)
	{
		x = xPos;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int yPos)
	{
		y = yPos;
	}
	
	public int getStandardSize()
	{
		return standardSize;
	}
	
	public int getProjectileSize()
	{
		return projectileSize;
	}
	
	public void draw(Graphics page)
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
