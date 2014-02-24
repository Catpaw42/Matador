package game.fields;

public class Shipping extends Field {

	public Shipping(int nr, String name)
	{
		super(nr, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String message() {
		return "You have landed on " + this.getName();
	}

}
