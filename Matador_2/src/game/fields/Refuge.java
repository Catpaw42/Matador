package game.fields;

public class Refuge extends Field
{
	public Refuge(int nr, String name)
	{
		super(nr, name);
	}

	@Override
	public String getMessage()
	{
		return "have landed on field " + this.getFieldNumber() + "  " + this.getName();
	}
}
