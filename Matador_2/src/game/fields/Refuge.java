package game.fields;

public class Refuge extends Field
{

	public Refuge(int nr, String name)
	{
		super(nr, name);
	}

	@Override
	public String message()
	{
		return "You have landed on " + this.getName();
	}
}
