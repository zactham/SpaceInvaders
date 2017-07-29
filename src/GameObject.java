import java.awt.Graphics;

public class GameObject 
{
	
	protected int x = 0;
	protected int y = 0;
	
	private static final int standardSize = 75;
	private static final int projectileSize = 15;
	
	
	
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
	
	public static int getStandardSize()
	{
		return standardSize;
	}
	
	public static int getProjectileSize()
	{
		return projectileSize;
	}
	
	public void draw (Graphics page)
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
