package game.fields;

public class GoToJail extends Field
{
	public GoToJail(int nr, String name)
	{
		super(nr, name);
	}
	
	@Override
	public String getMessage()
	{
		return "have landed on field " + this.getFieldNumber() + "  " + this.getName();
	}
}
