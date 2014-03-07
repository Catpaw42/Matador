package game.fields;

public class Chance extends Field
{
	public Chance(int nr, String name)
	{
		super(nr, name);
	}

	@Override
	public String getMessage()
	{
		return "have landed on " + this.getName();
	}
}
