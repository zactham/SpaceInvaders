import java.awt.Graphics;

public class GameObject 
{

	protected int x = 0;
	protected int y = 0;

	private static final int standardSize = 75;
	protected static final int projectileWidth = getGameWidth();
	private static final int projectileHeight = getGameHeight();

	private static int gameWidth = 0;
	private static int gameHeight = 0;



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

	public static int getGameWidth()
	{
		return gameWidth;
	}

	public static void setGameWidth(int gw)
	{
		gameWidth = gw;
	}

	public static int getGameHeight()
	{
		return gameHeight;
	}

	public static void setGameHeight(int gh)
	{
		gameHeight = gh;
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
