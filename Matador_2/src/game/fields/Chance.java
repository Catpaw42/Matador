package game.fields;

public class Chance extends Field{

	public Chance(int nr, String name) {
		super(nr, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String message() {
		return "You have landed on " + this.getName();
	}

}
