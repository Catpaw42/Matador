package game.fields;

public class Refuge extends Field
{
	private int fængselstakst = 1000;
	
	public Refuge(int nr, String name)
	{
		super(nr, name);
	}

	@Override
	public String getMessage()
	{
		return "have landed on " + this.getName();
	}
	
	public int getFængselstakst() {
		return fængselstakst;
	}
}
