package game.fields;

import game.Account;

public class Refuge extends Field {

	int bonus;
	
	public Refuge(int nr, int bonus) {
		super(nr);
		this.bonus = bonus;
	}
	
	public int getBonus() {
		return bonus;
	}
	
	@Override
	public void message() {
	System.out.println("You hale landed on a Refuge. You have earned a bouns of:" + bonus);
	}

}
