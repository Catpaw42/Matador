package game.fields;

public class Street extends Field {

	public Street(int nr, String name)
	{
		super(nr, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String message() {
		return "You have landed on " + this.getName();
	}

}
