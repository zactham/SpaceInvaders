import java.awt.Color;
import java.awt.Graphics;

public class Alien2 extends Alien
{
	private Sound sound = new Sound();

	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Alien2;
	}
	
}