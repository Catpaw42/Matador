package game.fields;

public class Refuge extends Field {

	private int bonus;
	
	public Refuge(int nr, int bonus, String name) {
		super(nr,name);
		this.bonus = bonus;
	}
	
	public int getBonus() {
		return bonus;
	}
	
	@Override
	public void message() {
	System.out.println("You have landed on a Refuge. You have earned a bouns of:" + bonus);
	}

}
