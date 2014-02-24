package game.fields;

public class Jail extends Field {

	public Jail(int nr, String name) {
		super(nr,name);
		// TODO Auto-generated constructor stub
	}

	private int fængselstakst = 1000;

	@Override
	public String message() {
		return "You have landed on " + this.getName();
	}

	public int getFængselstakst() {
		return fængselstakst;
	}

}