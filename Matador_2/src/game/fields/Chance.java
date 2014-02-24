package game.fields;

public class Chance extends Field{

	public Chance(int nr, String name) {
		super(nr, name);

		
	}

	@Override
	public String message() {
		
		return "You have landed on a " + this.getName();
	}

}
