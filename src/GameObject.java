import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject 
{

	protected int x = 0;
	protected int y = 0;
	

	private static final int standardSize = 75;
	protected static final int projectileWidth = 5;
	protected static final int projectileHeight = 18;
	public static final int ufoSize = 40;
	public static final int expSize = 20;
	private static final int alienSize = 30;
	private Rectangle bounds = null;
	
	
	private static final int playerWidth = standardSize - 25;//50
	private static final int playerHeight = standardSize - 50;//25


	private static int gameWidth = 700;
	private static int gameHeight = 800;



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

	public static int getufoSize()
	{
		return ufoSize;
	}
	
	public static int getExplosionSize()
	{
		return expSize;
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
	
	public static int getPlayerHeight()
	{
		return playerHeight;
	}
	
	public static int getPlayerWidth()
	{
		return playerWidth;
	}
	
	public static int getAlienSize()
	{
		return alienSize;
	}
	
	
	
	

	public Rectangle getBounds()
	{
		return bounds;
	}

	public void createBounds(int x, int y, int width, int height)
	{
		if(getBounds() == null)
		{
			bounds = new Rectangle(x, y, width, height);

		}
	}

	private void drawBounds(Graphics page)
	{
		//page.setColor(Color.white);
		//page.drawRect((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int) bounds.getHeight());

	}

	public void updateBounds()
	{
		if (getBounds() != null)
			bounds.setLocation(x, y);


		//updateBounds();
	}

	public void draw (Graphics page)
	{
		drawBounds(page);
	}

	public void update()
	{

	}

	public GameObjectType getType()
	{
		return GameObjectType.NONE;
	}



}
